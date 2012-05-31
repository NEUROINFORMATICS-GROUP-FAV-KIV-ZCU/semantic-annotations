package cz.zcu.kiv.annotations.application;

import java.util.ArrayList;
import java.util.List;
import cz.zcu.kiv.annotations.data.FileSavedAnnotAttr;
import cz.zcu.kiv.annotations.data.FileSavedAnnotClass;
import cz.zcu.kiv.annotations.data.FileSavedAnnotation;
import cz.zcu.kiv.annotations.data.Iannotation;
import cz.zcu.kiv.annotations.data.Iattribute;
import cz.zcu.kiv.annotations.data.IclassItem;

/**
 * This class gather all changed annotations in project
 * and creates FileSavedAnnoation items that contain annoation
 * data and returns them in a List
 *
 * @author Filip Markvart
 */
public class ChangeAnnotationsGather {

    private List<FileSavedAnnotation> saveAnnotaion;

    /**
     * This method generates a List of FileSavedAnnotations that contains
     * all changed annotations in the project
     *
     * @param classItems Selected classItems
     * @return List of changed annotations
     */
    public List<FileSavedAnnotation> getChangedAnnotations(List<IclassItem> classItems) {

        this.saveAnnotaion = new ArrayList<FileSavedAnnotation>();

        for (IclassItem item: classItems) {

            addClassAnnotation(item.getClassAnnotations(), item.getPackageName(), item.getName());
            addAttrAnnotation(item.getClassAttributes(), item.getPackageName(), item.getName());
        }
        return saveAnnotaion;
    }


   /**
    * Method gahter all changed annotations from List and creates
    * FileSavedAnnoations which adds to the List of annoatations
    *
    * @param annotations ClassesAnnoatations
    * @param className name of parent class
    */
    private void addClassAnnotation(List<Iannotation> annotations, String packageName, String className) {

        FileSavedAnnotClass annotClass;

        for (Iannotation item: annotations) {

            if (item.isChangen()) {

                if (item.isParam()) {

                    annotClass = new FileSavedAnnotClass(packageName, className, item.getName(), item.getValue());
                } else {
                    
                    annotClass = new FileSavedAnnotClass(packageName, className, item.getName(), null);
                }

                saveAnnotaion.add(annotClass);
            }
        }
    }

    /**
    * Method gahter all changed annotations from List and creates
    * FileSavedAnnoations which adds to the List of annoatations
    *
    * @param attributes List of classes attributes
    * @param className name of parent class
    */
    private void addAttrAnnotation(List<Iattribute> attributes, String packageName, String className) {

        FileSavedAnnotAttr annotAttr;

        for (Iattribute itemAttr: attributes) {

            for (Iannotation itemAnnot: itemAttr.getAttrAnnotations()) {

                if (itemAnnot.isChangen()) {

                    if (itemAnnot.isParam()) {

                        annotAttr = new FileSavedAnnotAttr(packageName, className, itemAnnot.getName(), itemAnnot.getValue(), itemAttr.getName(), itemAttr.getType());
                    }else {

                        annotAttr = new FileSavedAnnotAttr(packageName, className, itemAnnot.getName(), null, itemAttr.getName(), itemAttr.getType());
                    }

                saveAnnotaion.add(annotAttr);
                }
            }
        }
    }

}
