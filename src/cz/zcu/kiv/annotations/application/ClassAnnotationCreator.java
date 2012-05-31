package cz.zcu.kiv.annotations.application;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import cz.zcu.kiv.annotations.data.Annotation;
import cz.zcu.kiv.annotations.data.Iannotation;

/**
 * This class creates a List of Annotations that are allowed in
 * ClassItem
 *
 * @author Filip Markvart
 */
public class ClassAnnotationCreator implements IannotationCreator{

    /**
     * Method returns List of all possible Annotations in
     * ClassItem
     * 
     * @return List of Annotations
     */
    public List<Iannotation> getAnnotations(String classAnnotIniFile) throws FileNotFoundException {

        List annotations = new ArrayList<Annotation>();

        return LoadAvailableAnnot.readAnnotaionList(annotations, classAnnotIniFile);
    }

}
