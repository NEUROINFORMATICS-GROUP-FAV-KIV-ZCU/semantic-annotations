package cz.zcu.kiv.annotations.data;

import java.util.List;

/**
 * This class represents a one Classes attribute
 * containing his name and all annotations
 *
 * @author Filips
 */
public class Attribute implements Iattribute{

    private String name;
    private List<Iannotation> annotations;
    private int type;

    /**
     * Constructor sets attributes name and all contained
     * annotations
     *
     * @param name String attribute name
     * @param annotations List annotations
     */
    public Attribute(String name, List<Iannotation> annotations, int type) {

        this.name = name;
        this.annotations = annotations;
        this.type = type;
    }

    /**
     * Method returns attributes name
     *
     * @return String name of the attribute
     */
    public String getName() {

        return name;
    }

    /**
     * Method returns all attributes annotations
     *
     * @return List of Annotations
     */
    public List<Iannotation> getAttrAnnotations() {

        return annotations;
    }

    /**
     * Method sets annotation value to specified annotation
     *
     * @param name annotation name
     * @param value annotation value
     */
    public void setAttrAnnotationValue(String name, String value) {

        for (Iannotation item: annotations) {

            if (item.getName().equals(name)) {
                
                item.setValue(value);
            }
        }
    }

    public void setNoParAttrAnnotValue(String name, boolean set) {

        for (Iannotation item: annotations) {

            if (item.getName().equals(name)) {

                item.setNoParam(set);
            }
        }
    }
    
    public String toString(){
        return name;
    }

    public int getType() {
        return type;
    }


}
