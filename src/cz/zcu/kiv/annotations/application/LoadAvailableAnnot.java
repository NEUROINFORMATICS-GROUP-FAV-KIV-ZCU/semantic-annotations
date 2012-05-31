package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.Annotation;
import cz.zcu.kiv.annotations.data.Iannotation;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class loads all allowed annotations from ini file for classes
 * or attributes.
 *
 * @author Filip Markvart
 */
public class LoadAvailableAnnot {

    /**
     * Method reads all allowed attribute annotations from .ini file
     *
     * @return List of allowed annotations
     */
    public static List<Iannotation> readAnnotaionList(List<Iannotation> annotations, String fileName) throws FileNotFoundException {

        Scanner input = new Scanner(new File(fileName));

        String line;

        while (input.hasNext()) {

            line = input.nextLine();

            addAnnotItem(annotations, line);
            
        }

        input.close();

        addNewAnnotations(annotations, fileName);

        return annotations;
    }

    /**
     * Method tests if a new .ini annotation lists file contains annotations,
     * that are not supported in existing project and add allows them adding
     * into project classes.
     * 
     * @param annotations
     * @param fileName
     * @return
     * @throws FileNotFoundException
     */
    private static void addNewAnnotations(List<Iannotation> annotations, String fileName) throws FileNotFoundException{

        String sourceFile = null;

        if (fileName.contains(Constants.classAnnotFile)) { // classes annoations

            sourceFile = Constants.classAnnotFile;
        }

        if (fileName.contains(Constants.attrAnnotFile)){ // attr annotations

            sourceFile = Constants.attrAnnotFile;
        }

        Scanner input = new Scanner(new File(sourceFile));

        List<String> newAnnots = new ArrayList<String>();

        String actualAnnot;

        while (input.hasNext()) { //load file annotations

            actualAnnot = input.nextLine();
            newAnnots.add(actualAnnot);
        }

        input.close();

        boolean newOne = true;

        for (String newA: newAnnots) { // trought potentially new annotaions

            newOne = true;

            for (Iannotation annot: annotations) { // trought existing annotaions

                if (newA.contains(annot.getName())) {

                    newOne = false;
                    break;
                }
            }
            if (newOne) { addAnnotItem(annotations, newA); }
        }

    }

    /**
     * Method parses one String line saved in file by special
     * character
     *
     * @param item Sring item
     * @return parsed items
     */
    private static String itemParser(String item) {

        String [] parsed = item.split(Constants.PARSER_CHAR);

        if (parsed.length == 2) {
            return parsed[1];
        }else {
            return null;
        }

    }
    /**
     * Method create a new annotation based on file gathered data and
     * put it to existing annoatation list
     * 
     * @param newA String annoataion
     * @return
     */
    private static void addAnnotItem(List<Iannotation> annotations, String line) {

        Annotation annotation;
        String name;

        if (line.startsWith("#")) return;

            if (line.startsWith("P")) { //parametric annotation

                name = itemParser(line);

                if (name != null) {
                    annotation = new Annotation(name, true);
                    annotations.add(annotation);
                    return;
                }
            }
            if (line.startsWith("N")) { // non-prarametric annotation

                name = itemParser(line);

                if (name != null) {
                    annotation = new Annotation(name, false);
                    annotations.add(annotation);
                }
            }


    }
}
