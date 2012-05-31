package cz.zcu.kiv.annotations.application;

import japa.parser.ParseException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import cz.zcu.kiv.annotations.data.ClassItem;
import cz.zcu.kiv.annotations.data.Iannotation;
import cz.zcu.kiv.annotations.data.Iattribute;
import cz.zcu.kiv.annotations.data.IclassItem;
import japa.parser.JavaParser;
import japa.parser.TokenMgrError;
import japa.parser.ast.CompilationUnit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;


/**
 * ClassItemsGenerator creates a List of ClassItems using ClassAnnotationCreator
 * and Attribute creator from a List of class files and package name which is
 * neccessary to gather all classes attributes
 *
 * @author Filip Markvart
 */
public class ClassItemsGenerator {
                                                                                        //ini file
    public List<IclassItem> getClassItems(List<File> classes, Map <String, String> packagename, String classAnnotIniFile, String attrAnnotIniFile, boolean sources) throws FileNotFoundException, NoClassDefFoundError, ClassFormatError, Exception, TokenMgrError {

        IannotationCreator cAnnotCr = new ClassAnnotationCreator();
        AttributeCreator attrCreator = new AttributeCreator();

        List<IclassItem> classItems = new ArrayList<IclassItem>();

        String pckgName;

        for (File file: classes) {

            // Name of the class without suffix
            String className = file.getName().split("\\.")[0];

            if (sources) {
                pckgName = getPackageName(file);
            }else{
                pckgName = packagename.get(className);
            }

            List<Iannotation> classAnnotations = cAnnotCr.getAnnotations(classAnnotIniFile);
            List<Iattribute> attributes = attrCreator.getAttributes(className, pckgName, attrAnnotIniFile, sources);

            ClassItem newItem = new ClassItem(className, pckgName ,file, classAnnotations, attributes);
            
            classItems.add(newItem);
        }
        return classItems;
    }



    public List<IclassItem> getClassItems(List<File> classes, String packagename, String classAnnotIniFile, String attrAnnotIniFile, boolean sources) throws FileNotFoundException, NoClassDefFoundError, ClassFormatError, Exception, TokenMgrError {

        IannotationCreator cAnnotCr = new ClassAnnotationCreator();
        AttributeCreator attrCreator = new AttributeCreator();

        List<IclassItem> classItems = new ArrayList<IclassItem>();

        if (packagename == null) {
            packagename = getPackageName(classes.get(0));
        }

        for (File file: classes) {

            // Name of the class without suffix
            
            String className = file.getName().split("\\.")[0];

            List<Iannotation> classAnnotations = cAnnotCr.getAnnotations(classAnnotIniFile);
            List<Iattribute> attributes = attrCreator.getAttributes(className, packagename, attrAnnotIniFile, sources);

            ClassItem newItem = new ClassItem(className, packagename, file, classAnnotations, attributes);

            classItems.add(newItem);
        }
        return classItems;
    }

    /**
     * Method uses Java parser library to gather package name from
     * selected class saved in selected package and returns
     * String name represented package name
     *
     * @param className Target class name
     * @return String package name
     */
    private String getPackageName(File className) throws FileNotFoundException, IOException, ParseException, Exception, TokenMgrError {


        FileInputStream in = new FileInputStream(className);

        CompilationUnit cu;

        cu = JavaParser.parse(in);

        String result = cu.getPackage().getName().toString();

        in.close();
        
        return result;
    }



}
