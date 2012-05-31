package cz.zcu.kiv.annotations.application;

import com.sun.mirror.declaration.Declaration;
import cz.zcu.kiv.annotations.data.Attribute;
import cz.zcu.kiv.annotations.data.FileSavedAnnotAttr;
import cz.zcu.kiv.annotations.data.Iattribute;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.TokenMgrError;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.ImportDeclaration;
import japa.parser.ast.body.ConstructorDeclaration;
import japa.parser.ast.body.FieldDeclaration;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.body.Parameter;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.type.*;
import japa.parser.ast.type.PrimitiveType.Primitive;
import japa.parser.ast.visitor.VoidVisitorAdapter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * This class uses reflection to gather atributes of classes stored in
 * defined package and creates Attribute structure which contains all
 * classes attributes with them annotations
 *
 *
 * @author Filip Markvart
 */
public class AttributeCreator {


    /**
     * Method returns List of Attribute for selected class using reflection
     * to get attributes names a AttrAnnotationCreator to get all possible
     * annotations for each attribute
     *
     * @param className String name of the class
     * @param packageName String name of the package
     * @return List of Attribute
     * @throws Exception Reflection problem exception
     */                                                                               //ini File
    public List<Iattribute> getAttributes(String className, String packageName, String classAnnotIniFile, boolean sources) throws FileNotFoundException, NoClassDefFoundError, ClassFormatError, Exception, TokenMgrError {

        IannotationCreator attrAnnotationCreator = new AttrAnnotationCreator();

        List<Iattribute> attributes = new ArrayList<Iattribute>();

        Iattribute attribute;

        if (sources){

            for(String attrName: getClassesAtributes(className)) {
                char typeChar = attrName.charAt(0);
                int type = 0;
                switch (typeChar){
                    case 'F':
                        type=FileSavedAnnotAttr.TYPE_FIELD;
                        break;
                    case 'M':
                        type=FileSavedAnnotAttr.TYPE_METHD;
                        break;
                    case 'C':
                        type=FileSavedAnnotAttr.TYPE_CONST;
                        break;
                }
                //System.out.println(type);
                attribute = new Attribute(attrName.substring(1), attrAnnotationCreator.getAnnotations(classAnnotIniFile),type);
                attributes.add(attribute);
            }

        }else{

            for(String attrName: getClassesAtributes(className, packageName)) {
                char typeChar = attrName.charAt(0);
                int type = 0;
                switch (typeChar){
                    case 'F':
                        type=FileSavedAnnotAttr.TYPE_FIELD;
                        break;
                    case 'M':
                        type=FileSavedAnnotAttr.TYPE_METHD;
                        break;
                    case 'C':
                        type=FileSavedAnnotAttr.TYPE_CONST;
                        break;
                }
                attribute = new Attribute(attrName.substring(1), attrAnnotationCreator.getAnnotations(classAnnotIniFile),type);
                attributes.add(attribute);
            }
        }

        return attributes;
    }


    /**
     * Method uses reflection to gather classes atributes from
     * selected class saved in selected package and returns List of
     * String name represented attributes
     *
     * @param className Target class name
     * @param packageName Name of the containing package
     * @return List with Strng atributes names
     */
    private List<String> getClassesAtributes(String className, String packageName) throws ClassFormatError, Exception, NoClassDefFoundError {

        List attrNames = new ArrayList<String>();

        File directory = new File(Constants.ROOT_PATH);

        URL url = directory.toURI().toURL();
	URL[] urls = new URL[] { url };

	ClassLoader classLoader = new URLClassLoader(urls);

        String dataPathClass = className;

        if (packageName != null) {

            dataPathClass = packageName + "." + className;
        }else{
            dataPathClass = className;
        }

        try{
            Class targetClass = classLoader.loadClass(dataPathClass);
        
            Constructor[] constructors = targetClass.getDeclaredConstructors();
            Method[] methods = targetClass.getDeclaredMethods();
            Field fields[] = targetClass.getDeclaredFields();

            for (int i = 0; i < fields.length; i++) {
                attrNames.add("F"+fields[i].getName());
            }

            for (int i = 0; i < constructors.length; i++) {
                attrNames.add("C"+constructFullName(constructors[i]));
            }

            for (int i = 0; i < methods.length; i++) {
                attrNames.add("M"+constructFullName(methods[i]));
            }
        }catch (NoClassDefFoundError e){
            System.out.println(dataPathClass+" could not be loaded, probably an inner class");
        }
	

        return attrNames;
    }


    /**
     * Method uses Java parser library to gather source classes atributes from
     * selected class saved in selected package and returns List of
     * String name represented attributes
     *
     * @param className Target class name
     * @return List with String atributes names
     */
    private List<String> getClassesAtributes(String className) throws FileNotFoundException, ParseException, IOException, Exception, TokenMgrError {

        File target = new File(Constants.ROOT_PATH + "/" + Constants.SOURCES + "/" + className + ".java");

        FileInputStream in = new FileInputStream(target);

        CompilationUnit cu;

        cu = JavaParser.parse(in);
        MethodVisitor mv = new MethodVisitor();
        mv.visit(cu, null);

        List<String> result = new ArrayList<String>();
        
        result.addAll(mv.getAttributes());
        result.addAll(mv.getConstructors());
        result.addAll(mv.getMethods());
        in.close();
        
        return result;

    }

    private class MethodVisitor extends VoidVisitorAdapter {

        private List attributeNames;
        private List methodNames;
        private List constructorNames;

        public MethodVisitor() {

            attributeNames = new ArrayList<String>();
            methodNames = new ArrayList<String>();
            constructorNames = new ArrayList<String>();
            
        }

        @Override
        public void visit(FieldDeclaration n, Object arg) {
            attributeNames.add("F"+n.getVariables().get(0).getId().getName());
        }
        
        @Override
        public void visit(MethodDeclaration n, Object arg) {
            methodNames.add("M"+constructFullName(n));
        }
        
        @Override
        public void visit(ConstructorDeclaration n, Object arg) {
            constructorNames.add("C"+constructFullName(n));
        }
        
        public List<String> getAttributes(){
            return attributeNames;
        }
        
        public List<String> getConstructors(){
            return constructorNames;
        }
        
        public List<String> getMethods(){
            return methodNames;
        }

    }
    
    private String constructFullName(MethodDeclaration method){
        String fullName =  method.getName() +"(";
        List<Parameter> parameters = method.getParameters();
        if(parameters == null) parameters = Collections.emptyList();
        for(Parameter param : parameters){
            if(param.getType() instanceof PrimitiveType){
                String typeName = "V";
                Primitive type = ((PrimitiveType)param.getType()).getType();
                if (type.equals(Primitive.Boolean))  typeName = "Z";
                if (type.equals(Primitive.Byte))  typeName = "B";
                if (type.equals(Primitive.Char))  typeName = "C";
                if (type.equals(Primitive.Double))  typeName = "D";
                if (type.equals(Primitive.Float))  typeName = "F";
                if (type.equals(Primitive.Long))  typeName = "I";
                if (type.equals(Primitive.Int))  typeName = "J";
                if (type.equals(Primitive.Short))  typeName = "S";
                fullName += typeName;
            }else if(param.getType() instanceof ReferenceType){
                Type ref = (Type) param.getType();
                int arrayCount = ((ReferenceType)ref).getArrayCount();
                for(int i = 0;i<arrayCount;i++){
                    fullName += "[";
                }
                ref = ((ReferenceType)ref).getType();
                if(ref instanceof PrimitiveType){
                    String typeName = "V";
                    Primitive type = ((PrimitiveType)ref).getType();
                    if (type.equals(Primitive.Boolean))  typeName = "Z";
                    if (type.equals(Primitive.Byte))  typeName = "B";
                    if (type.equals(Primitive.Char))  typeName = "C";
                    if (type.equals(Primitive.Double))  typeName = "D";
                    if (type.equals(Primitive.Float))  typeName = "F";
                    if (type.equals(Primitive.Long))  typeName = "I";
                    if (type.equals(Primitive.Int))  typeName = "J";
                    if (type.equals(Primitive.Short))  typeName = "S";
                    fullName += typeName;
                }else if(ref instanceof ClassOrInterfaceType){
                    fullName += "L"+((ClassOrInterfaceType) ref).getName()+";";                  
                }
            }           
        }
        fullName += ")";
        Type param = method.getType();
        if(param instanceof PrimitiveType){
            String typeName = "V";
            Primitive type = ((PrimitiveType)param).getType();
            if (type.equals(Primitive.Boolean))  typeName = "Z";
            if (type.equals(Primitive.Byte))  typeName = "B";
            if (type.equals(Primitive.Char))  typeName = "C";
            if (type.equals(Primitive.Double))  typeName = "D";
            if (type.equals(Primitive.Float))  typeName = "F";
            if (type.equals(Primitive.Long))  typeName = "I";
            if (type.equals(Primitive.Int))  typeName = "J";
            if (type.equals(Primitive.Short))  typeName = "S";
            fullName += typeName;
        }else if(param instanceof ReferenceType){
            Type ref = (Type) param;
            int arrayCount = ((ReferenceType)ref).getArrayCount();
            for(int i = 0;i<arrayCount;i++){
                fullName += "[";
            }
            ref = ((ReferenceType)ref).getType();
            if(ref instanceof PrimitiveType){
                String typeName = "V";
                Primitive type = ((PrimitiveType)ref).getType();
                if (type.equals(Primitive.Boolean))  typeName = "Z";
                if (type.equals(Primitive.Byte))  typeName = "B";
                if (type.equals(Primitive.Char))  typeName = "C";
                if (type.equals(Primitive.Double))  typeName = "D";
                if (type.equals(Primitive.Float))  typeName = "F";
                if (type.equals(Primitive.Long))  typeName = "I";
                if (type.equals(Primitive.Int))  typeName = "J";
                if (type.equals(Primitive.Short))  typeName = "S";
                fullName += typeName;
            }else if(ref instanceof ClassOrInterfaceType){
                fullName += "L"+((ClassOrInterfaceType) ref).getName()+";";                  
            }
        }else if(param instanceof VoidType){
            fullName += "V";
        }      
        //System.out.println(fullName);
        return fullName;
    }
    
    private String constructFullName(ConstructorDeclaration constructor){
        String fullName =  "(";
        List<Parameter> parameters = constructor.getParameters();
        if(parameters == null) parameters = Collections.emptyList();
        for(Parameter param : parameters){
            if(param.getType() instanceof PrimitiveType){
                String typeName = "V";
                Primitive type = ((PrimitiveType)param.getType()).getType();
                if (type.equals(Primitive.Boolean))  typeName = "Z";
                if (type.equals(Primitive.Byte))  typeName = "B";
                if (type.equals(Primitive.Char))  typeName = "C";
                if (type.equals(Primitive.Double))  typeName = "D";
                if (type.equals(Primitive.Float))  typeName = "F";
                if (type.equals(Primitive.Long))  typeName = "J";
                if (type.equals(Primitive.Int))  typeName = "I";
                if (type.equals(Primitive.Short))  typeName = "S";
                fullName += typeName;
            }else if(param.getType() instanceof ReferenceType){
                Type ref = (Type) param.getType();
                int arrayCount = ((ReferenceType)ref).getArrayCount();
                for(int i = 0;i<arrayCount;i++){
                    fullName += "[";
                }
                ref = ((ReferenceType)ref).getType();
                if(ref instanceof PrimitiveType){
                    String typeName = "V";
                    Primitive type = ((PrimitiveType)ref).getType();
                    if (type.equals(Primitive.Boolean))  typeName = "Z";
                    if (type.equals(Primitive.Byte))  typeName = "B";
                    if (type.equals(Primitive.Char))  typeName = "C";
                    if (type.equals(Primitive.Double))  typeName = "D";
                    if (type.equals(Primitive.Float))  typeName = "F";
                    if (type.equals(Primitive.Long))  typeName = "J";
                    if (type.equals(Primitive.Int))  typeName = "I";
                    if (type.equals(Primitive.Short))  typeName = "S";
                    fullName += typeName;
                }else if(ref instanceof ClassOrInterfaceType){
                    fullName += "L"+((ClassOrInterfaceType) ref).getName()+";";                  
                }
            }           
        }
        fullName += ")V";
        //System.out.println(fullName);
        return fullName;
    }
    
    private String constructFullName(Method method){
        String fullName =  method.getName() +"(";
        Class<?>[] parameters = method.getParameterTypes();
        if(parameters == null) parameters = new Class<?>[0];
        for(Class<?> param : parameters){
            String canonName = param.getCanonicalName();
            int arrayCount = 0;
            while(canonName.substring(canonName.length()-2, canonName.length()).equals("[]")){
                arrayCount++;
                canonName = canonName.substring(0,canonName.length()-2);
            }
            if (canonName.equals("boolean"))  canonName = "Z";
            if (canonName.equals("byte"))  canonName = "B";
            if (canonName.equals("char"))  canonName = "C";
            if (canonName.equals("double"))  canonName = "D";
            if (canonName.equals("float"))  canonName = "F";
            if (canonName.equals("long"))  canonName = "J";
            if (canonName.equals("int"))  canonName = "I";
            if (canonName.equals("short"))  canonName = "S";
            if (canonName.length()>1){
                canonName = canonName.replace('.', '/');
                canonName = "L" + canonName + ";";
            }
            while(arrayCount>0){
                canonName = "["+canonName;
                arrayCount--;
            }
            //System.out.println(canonName);
            fullName+=canonName;
        }           
        fullName += ")";
        String canonName = method.getReturnType().getCanonicalName();
        int arrayCount = 0;
        while(canonName.substring(canonName.length()-2, canonName.length()).equals("[]")){
            arrayCount++;
            canonName = canonName.substring(0,canonName.length()-2);
        }
        if (canonName.equals("boolean"))  canonName = "Z";
        if (canonName.equals("byte"))  canonName = "B";
        if (canonName.equals("char"))  canonName = "C";
        if (canonName.equals("double"))  canonName = "D";
        if (canonName.equals("float"))  canonName = "F";
        if (canonName.equals("long"))  canonName = "J";
        if (canonName.equals("int"))  canonName = "I";
        if (canonName.equals("short"))  canonName = "S";
        if (canonName.equals("void"))  canonName = "V";
        if (canonName.length()>1){
            canonName = canonName.replace('.', '/');
            canonName = "L" + canonName + ";";
        }
        while(arrayCount>0){
            canonName = "["+canonName;
            arrayCount--;
        }
        //System.out.println(canonName);
        fullName+=canonName;
        //System.out.println(fullName);
        return fullName;
    }
    
    private String constructFullName(Constructor method){
        //System.err.println(method.getName());
        //System.err.flush();
        String fullName =  "(";
        Class<?>[] parameters = method.getParameterTypes();
        if(parameters == null) parameters = new Class<?>[0];
        for(Class<?> param : parameters){
            String canonName = param.getCanonicalName();
            int arrayCount = 0;
            //if(method.getName().equals("cz.zcu.kiv.annotations.gui.SaveAsProjectListener$SelectedFilter")){
            //    System.out.print("derl");
            //}
            if(canonName == null){ //if canon name fails to load, attempt to substitute the other name format
                canonName = param.getName();
            }
            while(canonName.substring(canonName.length()-2, canonName.length()-1).equals("[]")){
                arrayCount++;
                canonName = canonName.substring(0,canonName.length()-3);
            }
            if (canonName.equals("boolean"))  canonName = "Z";
            if (canonName.equals("byte"))  canonName = "B";
            if (canonName.equals("char"))  canonName = "C";
            if (canonName.equals("double"))  canonName = "D";
            if (canonName.equals("float"))  canonName = "F";
            if (canonName.equals("long"))  canonName = "J";
            if (canonName.equals("int"))  canonName = "I";
            if (canonName.equals("short"))  canonName = "S";
            if (canonName.length()>1){
                canonName = canonName.replace('.', '/');
                canonName = "L" + canonName + ";";
            }
            while(arrayCount>0){
                canonName = "["+canonName;
            }
            //System.out.println(canonName);
            fullName+=canonName;
        }           
        fullName += ")V";
        //System.out.println(fullName);
        return fullName;
    }
}
