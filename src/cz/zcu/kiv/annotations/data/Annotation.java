package cz.zcu.kiv.annotations.data;

/**
 * Class Annotation implements IAnnotation to allows
 * methods operating over one annotation item.
 *
 * @author Filip Markvart
 */
public class Annotation implements Iannotation{

    private String annotationName;
    private String annotationValue;
    private boolean annotationChanged;
    private boolean isParamAnnotation; // true if has parametr
    
    /**
     * Constructor creates one annotaion item with annotaion name
     * 
     * @param name Name of created annotaion
     */
    public Annotation(String name, boolean hasParam) {

        this.annotationName = name;
        this.annotationValue = "";
        this.annotationChanged = false;
        this.isParamAnnotation = hasParam;
    }
    /**
     * Method returns a name of the annotation.
     *
     * @return String name of annotation
     */
    public String getName() {

        return annotationName;
    }

    /**
     * Method returns annotation value.
     *
     * @return String annotation value
     *
     */
    public String getValue() {

        return annotationValue;
    }

    /**
     * Method returns boolean if annotaion value was changed
     *
     * @return if annotation value was changed
     */
    public boolean isChangen() {

        return annotationChanged;
    }

    /**
     * Method sets a value of the annotation
     *
     * @param value String value of the annotation
     */
    public void setValue(String value) {
        
        if (value != null) {

            annotationValue = value;
            annotationChanged = true;
        }else {
            annotationChanged = false;
        }
    }

    /**
     * Returns if annotation has a parametr
     * @return
     */
    public boolean isParam() {
        return isParamAnnotation;
    }

    /**
     * Sets no-parametr annotation as added or not
     */
    public void setNoParam(boolean status) {

        annotationChanged = status;
    }
    
    public String toString(){
        if(annotationValue.equals("")){
            return annotationName;
        }
        else{
            return annotationName+" :"+annotationValue;
        }
    }

   

}
