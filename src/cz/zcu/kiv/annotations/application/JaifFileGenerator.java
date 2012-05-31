package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.FileSavedAnnotAttr;
import cz.zcu.kiv.annotations.data.FileSavedAnnotClass;
import cz.zcu.kiv.annotations.data.FileSavedAnnotation;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class generates a JAIF file, which is a specific text file containing
 * instructions for Annotation tool library to add a specific annotations
 * to selected classes.
 * This files is used as an input to Annotation tool together with annotated
 * class.
 *
 * @author Filip Markvart
 */
public class JaifFileGenerator {

    private static FileWriter jaifWriter;

    /**
     * Method makes a Jaif file that contains all annotation informations
     * to annotate classes using Annotation Tool library
     * 
     * @param annotations List of annotaions
     * @param packageName project package name
     * @throws IOException
     */
    public static void generateJaif(List<FileSavedAnnotation> annotations) throws IOException {

        jaifWriter = new FileWriter(Constants.jaifFileName);

        Set<String[]> annotNames = getAnnotationNames(annotations);

        writeJaifHeader(annotNames);
        writeAnnotItems(annotations);

        jaifWriter.close();

    }

    /**
     * Method gather froma FileSavedAnnotations List names of existing
     * annotations and returns these names in a Set
     * 
     * @param annotations List of annotations
     * @return Set of annotation names
     */
    private static Set<String[]> getAnnotationNames(List<FileSavedAnnotation> annotations) {

        Set<String[]> nameList = new HashSet<String[]>();

        boolean exist = false;

        for (FileSavedAnnotation item : annotations) {

            String[] newOne = new String[2];
            newOne[0] = item.getAnnotName();
            newOne[1] = "P";

            if (item.getAnnotValue() == null) {
                newOne[1] = "N";
            }
            exist = false;
            for (String[] listItem : nameList) {
                if (listItem[0].equals(newOne[0])) {
                    exist = true;
                    break;
                }
            }

            if (!exist) {
                nameList.add(newOne);
            }

        }
        return nameList;
    }

    /**
     * Method writes the header of Jaif file which contains definitions
     * of annotations and their types
     *
     * @param annotationNames Set of annotation names and type
     */
    private static void writeJaifHeader(Set<String[]> annotationNames) throws IOException {

        jaifWriter.write(Constants.headerPack + "\n");

        for (String[] name : annotationNames) {

            jaifWriter.write(Constants.annotDefBegin);
            jaifWriter.write(name[0]);
            jaifWriter.write(Constants.annotDefEnd + "\n");

            if (name[1].equals("P")) {
                jaifWriter.write(Constants.annotDefType + "\n");
            }
            jaifWriter.write("\n");
        }

    }

    /**
     * Method writes all annotain items to JAIF file by values from
     * FileSavedAnnoation List
     *
     * @param annotations List of FileSavedAnnotaions
     */
    private static void writeAnnotItems(List<FileSavedAnnotation> annotations) throws IOException {

        for (FileSavedAnnotation annot : annotations) {

            jaifWriter.write(Constants.annotItemPack);
            jaifWriter.write(annot.getPacakgeName());
            jaifWriter.write(Constants.annotItemPackEnd + "\n");

            // class annotation
            if (annot.getClass().equals(FileSavedAnnotClass.class)) {
                writeClassAnnot((FileSavedAnnotClass) annot);
                // attribute annotation
            } else {
                writeAttrAnnot((FileSavedAnnotAttr) annot);
            }
        }
    }

    /**
     * Method writes one class annotation to Jaif file
     *
     * @param annot FileSavedAnnotClass annotation
     */
    private static void writeClassAnnot(FileSavedAnnotClass annot) throws IOException {

        jaifWriter.write(Constants.annotItemBeg);
        jaifWriter.write(annot.getClassName());
        jaifWriter.write(Constants.annotItemMidd);
        jaifWriter.write(annot.getAnnotName());

        if (annot.getAnnotValue() != null) {

            jaifWriter.write(Constants.annotItemEnd1);
            jaifWriter.write(annot.getAnnotValue());
            jaifWriter.write(Constants.annotItemEnd2);
        }

        jaifWriter.write("\n");

    }

    /**
     * Method writes one attribute annotation to Jaif file
     *
     * @param annot FileSavedAnnotAttr annotation
     */
    private static void writeAttrAnnot(FileSavedAnnotAttr annot) throws IOException {

        jaifWriter.write(Constants.annotItemBeg);
        jaifWriter.write(annot.getClassName());
        jaifWriter.write(Constants.annotItemPackEnd + "\n");
        int type = annot.getAnnotType();
        switch(type){
            case FileSavedAnnotAttr.TYPE_FIELD:
                jaifWriter.write(Constants.annotItemBegField);
                break;
            case FileSavedAnnotAttr.TYPE_CONST:
                jaifWriter.write(Constants.annotItemBegConst);
                break;
            case FileSavedAnnotAttr.TYPE_METHD:
                jaifWriter.write(Constants.annotItemBegMethd);
                break;
        }
        jaifWriter.write(annot.getAttrName());
        jaifWriter.write(Constants.annotItemMidd);
        jaifWriter.write(annot.getAnnotName());

        if (annot.getAnnotValue() != null) {

            jaifWriter.write(Constants.annotItemEnd1);
            jaifWriter.write(annot.getAnnotValue());
            jaifWriter.write(Constants.annotItemEnd2);
        }
        jaifWriter.write("\n");
    }
        
    
}
