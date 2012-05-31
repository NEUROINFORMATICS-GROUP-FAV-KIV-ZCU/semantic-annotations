package cz.zcu.kiv.annotations.data;

import java.io.File;
import java.util.List;

/**
 * ClassItem represents one Class with all attributes and annotations
 * saved in project data
 *
 * @author Filip Markvart
 */
public class ClassItem implements IclassItem{

    private String className;
    private String packageName;
    private File classFile;
    private List<Iannotation> classAnnotations;
    private List<Iattribute> classAttributes;

    public ClassItem(String name, String packageName, File classFile, List<Iannotation> classAnnotation, List<Iattribute> attributes) {

        this.className = name;
        this.packageName = packageName;
        this.classFile = classFile;
        this.classAnnotations = classAnnotation;
        this.classAttributes = attributes;
    }

    /**
     * Method returns name of the Class
     * 
     * @return String class name
     */
    public String getName() {

        return className;
    }
    /**
     * Method returns File representation of the class
     *
     * @return Class File on the disk
     */
    public File getFile() {

        return classFile;
    }
    /**
     * Method returns all contained class annotations
     *
     * @return List of class Annotations
     */
    public List<Iannotation> getClassAnnotations() {

        return classAnnotations;
    }
    /**
     * Method change one annotation of the class to defined value
     *
     * @param name Name of the annotation
     * @param value Value of the annoatation
     */
    public void changeClassAnnoatation(String name, String value) {

        for (Iannotation item: classAnnotations) {

            if (item.getName().equals(name)) {
                item.setValue(value);
            }
        }
    }
    /**
     * Method returns all class attributes
     *
     * @return List of class Attribute
     */
    public List<Iattribute> getClassAttributes() {

        return classAttributes;
    }
    /**
     * Method returns all annotations contained in defined
     * class attribute
     *
     * @param name String name of the attribut
     *
     * @return List of Annotations
     */
    public List<Iannotation> getClassesAttriuteAnnotations(String name) {

        for (Iattribute attribute: classAttributes) {

            if (attribute.getName().equals(name)) {

                return attribute.getAttrAnnotations();
            }
        }
        return null;
    }
     /**
     * Method change annotation value in selected annotation in selected
     * attribute
     *
     * @param attrName String name of the attribute
     * @param attrAnnotation String name of the annotation
     * @param value String value of the annotation
     */
    public void changeAttributeAnnotation(String attrName, String attrAnnotation, String value) {

        for (Iattribute attribute: classAttributes) {

            if (attribute.getName().equals(attrName)) {
                
                attribute.setAttrAnnotationValue(attrAnnotation, value);
            }
        }
    }

    public void changeClassNoParAnnotation(String name, boolean set) {

        for (Iannotation item: classAnnotations) {

            if (item.getName().equals(name)) {
                item.setNoParam(set);
            }
        }
    }

    public void chageAttrNoParAnnotation(String attrName, String attrAnnotation, Boolean set) {
        for (Iattribute attribute: classAttributes) {

            if (attribute.getName().equals(attrName)) {

                attribute.setNoParAttrAnnotValue(attrAnnotation, set);
            }
        }
    }

    /**
     * Method returns parent package name of this class
     *
     * @return Name of the java package
     */
    public String getPackageName() {

        return packageName;
    }
    
    public String toString(){
        return className;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

}
