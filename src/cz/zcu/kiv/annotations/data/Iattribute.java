package cz.zcu.kiv.annotations.data;

import java.util.List;

/**
 * Interface defines methods over Class Attribute
 * which contais one Class attribute and their annotations
 *
 * @author Filip Markvart
 */
public interface Iattribute {

    /**
     * Getter returns attribute name
     * 
     * @return String name of the attribute
     */
    public String getName();

    /**
     * Method returns List representations of all
     * attribute annotations
     *
     * @return List of Annotations
     */
    public List<Iannotation> getAttrAnnotations();

    /**
     * Setter sets attributes annotation value
     *
     * @param name Annotation name
     * @param value Annotation value
     */
    public void setAttrAnnotationValue(String name, String value);

    /**
     * Method sets a no parametr annoation as added or not depending attr set
     *
     * @param set If annoation will be added or not
     */
    public void setNoParAttrAnnotValue(String name, boolean set);

    public int getType();
}

