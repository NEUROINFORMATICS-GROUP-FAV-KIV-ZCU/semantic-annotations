package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.Iannotation;
import java.util.List;
import java.io.FileNotFoundException;

/**
 * This intereface contains methods to enable creating a list
 * of all kinds of annotations that are allowed in ClassItem or
 * in attribute item
 *
 * @author Filip Markvart
 */
public interface IannotationCreator {

    /**
     * Method returns List of all possible Annotations in item
     *
     * @return List of Annotations
     */
    public List<Iannotation> getAnnotations(String classAnnotIniFile) throws FileNotFoundException;
}
