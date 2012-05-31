package cz.zcu.kiv.annotations.data;

/**
 * Data class containing one annotation fixed to one class
 *
 * @author Filip Markvart
 */
public class FileSavedAnnotClass extends FileSavedAnnotation{

    public FileSavedAnnotClass(String packageName, String className, String annotName, String annotVal) {

        super(packageName, className, annotName, annotVal);
    }
}
