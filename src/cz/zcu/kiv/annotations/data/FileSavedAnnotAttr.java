package cz.zcu.kiv.annotations.data;

/**
 * Data class containing one annotation fixed to attribute
 *
 * @author Filip Markvart
 */
public class FileSavedAnnotAttr extends FileSavedAnnotation {

    private String AttrName;
    private int type;
    
    public static final int TYPE_FIELD = 1;
    public static final int TYPE_CONST = 2;
    public static final int TYPE_METHD = 3;

    public FileSavedAnnotAttr(String packageName, String className, String annotName, String annotVal, String attrName, int type) {
        super(packageName, className, annotName, annotVal);
        this.AttrName = attrName;
        this.type = type;
    }

    public String getAttrName() {

        return AttrName;
    }

    public int getAnnotType() {
        return type;
    }
}
