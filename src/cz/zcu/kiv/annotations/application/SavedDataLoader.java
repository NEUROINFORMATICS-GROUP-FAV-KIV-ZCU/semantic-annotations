package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.FileSavedAnnotAttr;
import cz.zcu.kiv.annotations.data.FileSavedAnnotClass;
import cz.zcu.kiv.annotations.data.FileSavedAnnotation;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class gather all annoatations saved in selected file
 * and returns them as List of FileSavedAnnotations
 *
 * @author Filip Markvart
 */
public class SavedDataLoader implements IsaveDataLoader{

    /**
     * Method reads input file and creates FileSavedAnnotation items
     * containing annotaions stored in input file
     *
     * @param file input file
     * @return List of FileSavedAnnotations
     *
     * @throws Exception Read file exception
     */
    public List<FileSavedAnnotation> getSavedAnnotations(File file) throws Exception {

        List<FileSavedAnnotation> annotation = new ArrayList<FileSavedAnnotation>();

        BufferedReader reader = openFileStream(file);

        String item;
        String parsedData[];

        while ((item = reader.readLine()) != null) {

            if (item.startsWith("P")) { // param annotation

                if (item.charAt(2) == (Constants.CLASS_ANNOT)) {

                    parsedData = itemParser(item);
                    annotation.add(createFileSavedAnnot(parsedData[2], parsedData[3], parsedData[4]));

                }
                if (item.charAt(2) == (Constants.ATTR_ANNOT)) {

                    parsedData = itemParser(item);
                    annotation.add(createFileSavedAnnot(parsedData[2], parsedData[3], parsedData[4], parsedData[5], Integer.valueOf(parsedData[6])));
                }

            } else if (item.startsWith("N")) {// no-param attr
            
                if (item.charAt(2) == (Constants.CLASS_ANNOT)) {

                    parsedData = itemParser(item);
                    annotation.add(createFileSavedAnnot(parsedData[2], parsedData[3], null));

                }
                if (item.charAt(2) == (Constants.ATTR_ANNOT)) {

                    parsedData = itemParser(item);
                    annotation.add(createFileSavedAnnot(parsedData[2], parsedData[3], parsedData[4], null, Integer.valueOf(parsedData[5])));
                }

            }
        }

        reader.close();

        return annotation;
    }

    /**
     * Method parses one String line saved in file by special
     * character
     * 
     * @param item Sring item
     * @return parsed items
     */
    private String[] itemParser(String item) {

        return item.split(Constants.PARSER_CHAR, -1);

    }

    /**
     * Method opens file input stream and returns it
     *
     * @param fileName opening File
     *
     * @return pointer to file reader
     */
    private BufferedReader openFileStream(File fileName) throws FileNotFoundException {

        InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));

        return (new BufferedReader(reader));
    }




    /**
     * Method create one class annoation
     *
     * @param className String name of class
     * @param annotName String name of annotation
     * @param annotValue String value of annotation
     *
     * @return created FileSavedAnnotClass
     */
    private FileSavedAnnotation createFileSavedAnnot(String className, String annotName, String annotValue){

        FileSavedAnnotation annotation = new FileSavedAnnotClass("", className, annotName, annotValue);
        return annotation;
    }

    /**
     * Method create one attr annoation
     *
     * @param className String name of class
     * @param annotName String name of annotation
     * @param annotValue String value of annotation
     * @param attrName String name of attribute
     *
     * @return created FileSavedAnnotClass
     */
    private FileSavedAnnotation createFileSavedAnnot(String className, String attrName, String annotName, String annotValue, int type){

        FileSavedAnnotation annotation = new FileSavedAnnotAttr("", className, annotName, annotValue, attrName, type);
        return annotation;
    }

}
