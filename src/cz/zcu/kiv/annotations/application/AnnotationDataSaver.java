package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.FileSavedAnnotAttr;
import cz.zcu.kiv.annotations.data.FileSavedAnnotClass;
import cz.zcu.kiv.annotations.data.FileSavedAnnotation;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * This class saves all user added annoations in project
 * to file annotations.dat which is save in packed project file.
 *
 * @author Filip Markvart
 */
public class AnnotationDataSaver {

    /**
     * Method saves all gathered FileSavedAnnotations to file Annottations.dat
     * and returns this file pointer
     * 
     * @param annotations List of FileSavedAnnotations
     * @return saved file
     */
    public static File saveAnnotations(List<FileSavedAnnotation> annotations) throws IOException {

        File outputFile = new File(Constants.PROJECT_DATA);

        FileWriter outputFileWriter = new FileWriter(outputFile);

        for (FileSavedAnnotation annotation: annotations) {

            if (annotation.getClass().equals(FileSavedAnnotClass.class)) {

                outputFileWriter.write(createClassAnnotationItem((FileSavedAnnotClass) annotation));
            }

            if (annotation.getClass().equals(FileSavedAnnotAttr.class)) {

                outputFileWriter.write(createAttrAnnotationItem((FileSavedAnnotAttr) annotation));
            }

        }

        outputFileWriter.close();

        return outputFile;
    }

    /**
     * Method creates from gathered FileSavedAnnotation a String item
     * containing annotation informations if appropriate format to save
     * it to file
     *
     * @param annotation input annotation
     * @return String item annoation
     */
    private static String createClassAnnotationItem(FileSavedAnnotClass annotation) {

        String fileItem;

        if (annotation.getAnnotValue()== null) { // no-param annotation

            fileItem = "N" + Constants.PARSER_CHAR;
        }else {
            fileItem = "P" + Constants.PARSER_CHAR;
        }

        fileItem = fileItem + Constants.CLASS_ANNOT + Constants.PARSER_CHAR;

        fileItem = fileItem + annotation.getClassName() + Constants.PARSER_CHAR;
        fileItem = fileItem + annotation.getAnnotName() + Constants.PARSER_CHAR;
        
        if (annotation.getAnnotValue()!= null) { // only parama Annotation
            fileItem = fileItem + annotation.getAnnotValue() + Constants.PARSER_CHAR;
        }

        return fileItem + "\n";
    }

    /**
     * Method creates from gathered FileSavedAnnotation a String item
     * containing annotation informations if appropriate format to save
     * it to file
     *
     * @param annotation input annotation
     * @return String item annoation
     */
    private static String createAttrAnnotationItem(FileSavedAnnotAttr annotation) {
        
        String fileItem;

        if (annotation.getAnnotValue()== null) { // no-param annotation

            fileItem = "N" + Constants.PARSER_CHAR;
        }else {
            fileItem = "P" + Constants.PARSER_CHAR;
        }

        fileItem = fileItem + Constants.ATTR_ANNOT + Constants.PARSER_CHAR;
        fileItem = fileItem + annotation.getClassName() + Constants.PARSER_CHAR;
        fileItem = fileItem + annotation.getAttrName() + Constants.PARSER_CHAR;
        fileItem = fileItem + annotation.getAnnotName() + Constants.PARSER_CHAR;
        
        if (annotation.getAnnotValue()!= null) { // only parama Annotation
            fileItem = fileItem + annotation.getAnnotValue() + Constants.PARSER_CHAR;
        }
        fileItem = fileItem + annotation.getAnnotType() + Constants.PARSER_CHAR;
        return fileItem  + "\n";
    }
}
