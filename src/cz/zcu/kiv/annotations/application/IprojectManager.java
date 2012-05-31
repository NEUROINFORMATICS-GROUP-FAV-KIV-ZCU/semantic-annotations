package cz.zcu.kiv.annotations.application;

import java.io.File;
import java.util.List;
import cz.zcu.kiv.annotations.data.Iannotation;
import cz.zcu.kiv.annotations.data.Iattribute;
import cz.zcu.kiv.annotations.data.IclassItem;
import cz.zcu.kiv.annotations.data.Project;
import java.util.Map;

/**
 * Project interface contains all methods neccessary to operate over
 * the project - create or open project and save it
 *             - export annoatated classes
 *             - all methods neccassary to get project data in presentation
 *               layer
 *
 * @author Filip Markvart
 */
public interface IprojectManager {

    /**
     * Method opens existing project and returns if successfull
     *
     * @param file Project file
     * @return if successfull
     */
    public int openExistingProject(File file);

     /**
     * Method creates a new project importing classes int JAR
     * and returns if successfull
     *
     * @param file Project file
     * @return if successfull
     */
    public int createProject(File jarFile, String packageName, boolean isSource);

     /**
     * Method create a new project importing single classes
     * and returns if successfull
     *
     * @param file Project file
     * @return if successfull
     */
    public int createProject(Map<File, String> classes, boolean isSource);

    /**
     * Method save opened project to disk and return if successfull
     *
     * @param saveFile Name of the saved file
     *
     * @return if project save was successfull
     */
    public boolean saveProject(File saveFile);


    /**
     * Method export project annotated classes to selected target
     *
     * @param target target directory
     * @return if successfull
     */
    public boolean exportClasses(File target);

    /**
     * Method returns all classes contained in the project.
     *
     * @return List of ClassItem
     */
    public List<IclassItem> getClasses();


    /**
     * Method returns all annotations contained in selected class.
     *
     * @param name Name of the class
     * @return List of Annotations
     */
    public List<Iannotation> getClassesAnnotations(String name);


    /**
     * Method returns all attributes of selected classItem
     *
     * @param name Class name
     * @return List of Attribute
     */
    public List<Iattribute> getClassesAttribute(String name);

    /**
     * Method returns all annotation of selected attribute of selected
     * class
     *
     * @param className Name of the class
     * @param attrName Name of the attribute
     * @return List of Annotations
     */
    public List<Iannotation> getClassesAttrAnnotations(String className, String attrName);

    /**
     * Method change annotation value in selected annotation
     * in selected class.
     *
     * @param className Name of the class
     * @param annotationName Name of the annotation
     * @param value New annotation value
     */
    public void changeClassAnnotation(String className, String annotationName, String value);

    /**
     * Method change one annotation value in selected annotation in selected
     * attribute in selected class
     *
     * @param className Name of the class
     * @param attrName Name of the attrbute
     * @param AnnotationName Name of the annotation
     * @param value New annotation value
     */
    public void changeAttributeAnnotation(String className, String attrName, String AnnotationName, String value);

    /**
     * Method set no-par annotation in selected annotation
     * in selected class.
     *
     * @param className Name of the class
     * @param annotationName Name of the annotation
     * @param set If annotation will be set
     */
    public void changeNoParClassAnnotation(String className, String annotationName, boolean set);

    /**
     * Method change one no-par annotation in selected annotation in selected
     * attribute in selected class
     *
     * @param className Name of the class
     * @param attrName Name of the attrbute
     * @param AnnotationName Name of the annotation
     * @param set If annotation will be set
     */
    public void changeNoParAttributeAnnotation(String className, String attrName, String AnnotationName, boolean set);


    public boolean isClassAnnotParam(String className, String annotName);

    public boolean isAttrAnnotParam(String className, String attrName, String annotName);

    public boolean isSource();

    public void setProjectData(Project project);

    public void clear();
}

