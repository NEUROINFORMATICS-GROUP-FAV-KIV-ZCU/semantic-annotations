package cz.zcu.kiv.annotations.data;

import java.util.List;

/**
 * ProjectData contains all project classes and package
 * settings
 * This class has methods operating over the included classes
 *
 * @author Filip Markvart
 */
public class Project {

    private boolean isSource;

    private List<IclassItem> classItems;

    public Project(List<IclassItem> classItems, boolean source) {

        this.classItems = classItems;
        this.isSource = source;
    }

    /**
     * Method returns all classes contained in the project.
     *
     * @return List of ClassItem
     */
    public List<IclassItem> getClasses(){

        return classItems;
    }

    /**
     * Method returns specified class contained in the project.
     *
     * @return List of ClassItem
     */
    public IclassItem getSpecClass(String name){

        for (IclassItem item: classItems){

            if (item.getName().equals(name)) return item;
        }

        return null;
    }

    /**
     * Method returns all annotations contained in selected class.
     * 
     * @param name Name of the class
     * @return List of Annotations
     */
    public List<Iannotation> getClassesAnnotations(String name) {

        for (IclassItem classItem: classItems) {

            if (classItem.getName().equals(name)) {

                return classItem.getClassAnnotations();
            }
        }
        return null;
    }


    /**
     * Method returns all attributes of selected classItem
     *
     * @param name Class name
     * @return List of Attribute
     */
    public List<Iattribute> getClassesAttribute(String name) {

        for (IclassItem classItem: classItems) {

            if (classItem.getName().equals(name)) {

                return classItem.getClassAttributes();
            }
        }
        return null;
    }

    /**
     * Method returns all annotation of selected attribute of selected
     * class
     * 
     * @param className Name of the class
     * @param attrName Name of the attribute
     * @return List of Annotations
     */
    public List<Iannotation> getClassesAttrAnnotations(String className, String attrName) {

        for (IclassItem classItem: classItems) {

            if (classItem.getName().equals(className)) {

                return classItem.getClassesAttriuteAnnotations(attrName);
            }
        }
        return null;
    }

    /**
     * Method change annotation value in selected annotation
     * in selected class.
     *
     * @param className Name of the class
     * @param annotationName Name of the annotation
     * @param value New annotation value
     */
    public void changeClassAnnotation(String className, String annotationName, String value) {

        for (IclassItem classItem: classItems) {

            if (classItem.getName().equals(className)) {

                classItem.changeClassAnnoatation(annotationName, value);
            }
        }
    }

    /**
     * Method changes no-par annotation in selected annotation
     * in selected class.
     *
     * @param className Name of the class
     * @param annotationName Name of the annotation
     * @param set True if annotation will be added
     */
    public void changeClassNoParAnnotation(String className, String annotationName, boolean set) {

        for (IclassItem classItem: classItems) {

            if (classItem.getName().equals(className)) {

                classItem.changeClassNoParAnnotation(annotationName, set);
            }
        }
    }

    /**
     * Method change one annotation value in selected annotation in selected
     * attribute in selected class
     *
     * @param className Name of the class
     * @param attrName Name of the attrbute
     * @param AnnotationName Name of the annotation
     * @param value New annotation value
     */
    public void changeAttributeAnnotation(String className, String attrName, String AnnotationName, String value) {

        for (IclassItem classItem: classItems) {

            if (classItem.getName().equals(className)) {
                
                classItem.changeAttributeAnnotation(attrName, AnnotationName, value);
            }
        }
    }

    /**
     * Method change one no-par annotation in selected annotation in selected
     * attribute in selected class
     *
     * @param className Name of the class
     * @param attrName Name of the attrbute
     * @param AnnotationName Name of the annotation
     * @param set True if annotation will be added
     */
    public void changeAttributeNoParAnnotation(String className, String attrName, String AnnotationName, boolean set) {

        for (IclassItem classItem: classItems) {

            if (classItem.getName().equals(className)) {
                
                classItem.chageAttrNoParAnnotation(attrName, AnnotationName, set);
            }
        }
    }
    /**
     * Method returns specified class annotation.
     *
     * @param className Name of the class
     * @param annotName Name of the annotation
     * @return specified annotation
     */
    public Iannotation getClassAnnotation(String className, String annotName) {

        for (IclassItem classItem: classItems) {

            if (classItem.getName().equals(className)) {

                for (Iannotation anot: classItem.getClassAnnotations()) {
                    if (anot.getName().equals(annotName)) {
                        return anot;
                    }
                }
            }
        }
        return null;

    }

    /**
     * Method returns specified class attributes annotation.
     *
     * @param className Name of the class
     * @param attrName Name of the attribute
     * @param annotName Name of the annotation
     * @return specified annotation
     */
    public Iannotation getAttrAnnotation(String className, String attrName, String annotName) {

        for (IclassItem classItem: classItems) {

            if (classItem.getName().equals(className)) {

                for (Iattribute attr: classItem.getClassAttributes()) {
                    if (attr.getName().equals(attrName)) {

                        for (Iannotation annot: attr.getAttrAnnotations()) {

                            if (annot.getName().equals(annotName))
                                return annot;
                        }
                    }
                }
            }
        }
        return null;

    }

    /**
     * Method returns if project contations source files or compiled
     *
     * @return true if ClassFiles are sources
     */
    public boolean isSource() {

        return isSource;
    }

}

