package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.FileSavedAnnotAttr;
import cz.zcu.kiv.annotations.data.FileSavedAnnotClass;
import cz.zcu.kiv.annotations.data.FileSavedAnnotation;
import cz.zcu.kiv.annotations.data.IclassItem;
import java.io.File;
import java.util.List;

/**
 * This class uses SaveDataLoader to load file saved annotations
 * and add these annotations values to propriate annoatations
 * in data project ClassItems
 *
 * @author Filip Markvart
 */
public class LoadedAnnotSetter {


    /**
     * Method load annotations saved in file and add them to ClassItems
     *
     * @param items List of ClassItems
     * @param file input file
     */
    public void loadAnnotations(List<IclassItem> items, File file) throws Exception {

        IsaveDataLoader sdLoader = new SavedDataLoader();

        List<FileSavedAnnotation> annoations = sdLoader.getSavedAnnotations(file);

        for (FileSavedAnnotation item: annoations) {
            
            if (item.getClass().equals(FileSavedAnnotClass.class)) {
                setClassAnnoataion(items, (FileSavedAnnotClass) item);
            }
            if (item.getClass().equals(FileSavedAnnotAttr.class)) {
                setAttrAnnoataion(items, (FileSavedAnnotAttr) item);
            }
        }
    }

    /**
     * Method set class annotation value in propriate ClassItem
     *
     * @param items ClassItems
     * @param annot added FileSavedAnnotation
     */
    private void setClassAnnoataion(List<IclassItem> items, FileSavedAnnotClass annot) {

        for (IclassItem classItem: items) {

            if (classItem.getName().equals(annot.getClassName())){

                if (annot.getAnnotValue() != null) {

                    classItem.changeClassAnnoatation(annot.getAnnotName(), annot.getAnnotValue());
                }else {
                    classItem.changeClassNoParAnnotation(annot.getAnnotName(), true);
                }
            }
        }
    }

    /**
     * Method set attribute annotation value in propriate ClassItem
     *
     * @param items ClassItems
     * @param annot added FileSavedAnnotation
     */
    private void setAttrAnnoataion(List<IclassItem> items, FileSavedAnnotAttr annot) {

        for (IclassItem classItem: items) {
        
            if (classItem.getName().equals(annot.getClassName())){

                if (annot.getAnnotValue() != null) {

                    classItem.changeAttributeAnnotation(annot.getAttrName(), annot.getAnnotName(), annot.getAnnotValue());
            
                }else {
                    classItem.chageAttrNoParAnnotation(annot.getAttrName(), annot.getAnnotName(), true);
                }
            }
        }
    }
}
