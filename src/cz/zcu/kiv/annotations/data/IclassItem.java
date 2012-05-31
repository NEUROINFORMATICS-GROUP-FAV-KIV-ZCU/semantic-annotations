package cz.zcu.kiv.annotations.data;

import java.io.File;
import java.util.List;

/**
 * Interface defines all neccessary methods for class ClassItem
 * which contains all classes annotations and attributes.
 *
 * @author Filip Markvart
 */
public interface IclassItem {

    /**
     * Method returns name of the Class
     * 
     * @return String class name
     */
    public String getName();
    
    /**
     * Method returns File representation of the class
     * 
     * @return Class File on the disk
     */
    public File getFile();

    /**
     * Method returns all contained class annotations
     *
     * @return List of class Annotations
     */
    public List<Iannotation> getClassAnnotations();

    /**
     * Method change one annotation of the class to defined value
     * 
     * @param name Name of the annotation
     * @param value Value of the annoatation
     */
    public void changeClassAnnoatation(String name, String value);

    /**
     * Method sets a no-param annotation to class
     * @param name Name of the annotation
     * @param set true if annotation will be added
     */
    public void changeClassNoParAnnotation(String name, boolean set);

    /**
     * Method returns all class attributes
     *
     * @return List of class Attribute
     */
    public List<Iattribute> getClassAttributes();

    /**
     * Method returns all annotations contained in defined
     * class attribute
     *
     * @param name String name of the attribut
     *
     * @return List of Annotations
     */
    public List<Iannotation> getClassesAttriuteAnnotations(String name);

    /**
     * Method change annotation value in selected annotation in selected
     * attribute
     *
     * @param attrName String name of the attribute
     * @param attrAnnotation String name of the annotation
     * @param value String value of the annotation
     */
    public void changeAttributeAnnotation(String attrName, String attrAnnotation, String value);

    /**
     * Method change no-param annotation in selected attribute
     *
     * @param attrName String name of target attribute
     * @param attrAnnotation String name of the annotation
     * @param set True if annotation will be added
     */
    public void chageAttrNoParAnnotation(String attrName, String attrAnnotation, Boolean set);


    /**
     * Method returns parent package name of this class
     * 
     * @return Name of the java package
     */
    public String getPackageName();

    public void setPackageName(String packageName);

}
