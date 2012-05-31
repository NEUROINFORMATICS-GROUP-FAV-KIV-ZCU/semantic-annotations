package cz.zcu.kiv.annotations.data;

/**
 * This interface sets all neccessary methods for
 * Data classes ClassAnnotations and AttrAnnotation
 *
 * @author Filip Markvart
 */
public interface Iannotation {

    /**
     *  Method gets the name of Annotation.
     * 
     * @return Annotation name
     */
    public String getName();

    /**
     * Method returns Annotation value set by user.
     *
     * @return Annotation value
     */
    public String getValue();

    /**
     * Method returns true, if the annotation value
     * was changed by user or false if not
     *
     * @return if annotation was changed
     */
    public boolean isChangen();

    /**
     * Method sets a new value of the annotation.
     *
     */
    public void setValue(String value);

    /**
     * Tasks if annotation has a String parametr
     */
    public boolean isParam();

    /**
     * Sets a no param annotation as added or not
     */
    public void setNoParam(boolean status);
}
