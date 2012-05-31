package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.Iannotation;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;

/**
 * This class creates a List of Annotations that are allowed in
 * attribute item
 *
 * @author Filip Markvart
 */
public class AttrAnnotationCreator implements IannotationCreator{

    /**
     * Method returns List of all possible Annotations in
     * ClassItem
     *
     * @return List of Annotations
     */
    public List<Iannotation> getAnnotations(String classAnnotIniFile) throws FileNotFoundException {

        List annotations = new ArrayList<Iannotation>();

        return LoadAvailableAnnot.readAnnotaionList(annotations, classAnnotIniFile);
    }




}
