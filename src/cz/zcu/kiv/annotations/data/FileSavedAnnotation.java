package cz.zcu.kiv.annotations.data;

/**
 * FileSavedAnnotation is data class containig one annotation item
 * stored in project file
 * Class is abstract because of existing 2 types of annotation
 * represented FileSavedAnnotClass and FileSavedAnnotAttr which
 * extends from this class
 *
 * @author Filip Markvart
 */
public abstract class FileSavedAnnotation {

    private String packageName;
    private String className;
    private String annotationName;
    private String annotationValue; //null if annoation is no-param

    public FileSavedAnnotation(String packageName, String className, String annotName, String annotVal) {

        this.packageName = packageName;
        this.className = className;
        this.annotationName = annotName;
        this.annotationValue = annotVal;
    }


    /**
     * Method returns name of parent package
     *
     * @return String name of package
     */
    public String getPacakgeName() {

        return packageName;
    }

    /**
     * Method returns name of parent class.
     *
     * @return String name of class
     */
    public String getClassName() {

        return className;
    }
    /**
     * Method returns name of annotation
     *
     * @return String name of annotation
     */
    public String getAnnotName() {

        return annotationName;
    }

    /**
     * Method returns value of the annotation
     *
     * @return String value of annotation
     */
    public String getAnnotValue(){

        return annotationValue;
    }
}
