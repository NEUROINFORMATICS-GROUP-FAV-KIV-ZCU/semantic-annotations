package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.Iannotation;
import cz.zcu.kiv.annotations.data.Iattribute;
import cz.zcu.kiv.annotations.data.IclassItem;
import cz.zcu.kiv.annotations.data.Project;
import cz.zcu.kiv.annotations.gui.MainWindow;
import java.io.File;
import java.io.FileNotFoundException;

import japa.parser.TokenMgrError;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 * Project class ensures all project operations over project data.
 *
 * @author Filip Markvart
 */
public class ProjectManager implements IprojectManager{

    private Project projectData;
    private IprojectLoader projectLoader;
    private IprojectSaver projectSaver;
    private ExportClasses exportClasses;
    private MainWindow master;

    public ProjectManager() {
    
        projectLoader = new ProjectLoader();        
        projectSaver = new ProjectSaver();
        exportClasses = new ExportClasses();

        projectData = null;
    }
    
    public void setProjectData(Project project) {
        projectData = project;
    }
    
    /**
     * Method opens an existing project file
     * 
     * @param file project file
     * @return if successfull
     */
    public int openExistingProject(File file) {
        try {
            projectData = projectLoader.openExistingProject(file);

        } catch (FileNotFoundException ex) {
            
            return 1; // can not find ini file
        } catch (Exception ex) {
            
            return 2; // can not load project
        }

        if (projectData == null) return 2;

        return 0;
    }

    /**
     * Method creates a new projet by importing project files in Jar
     *
     * @param jarFile input jar file
     * @param packageName name of classes contatining package
     * @return if successfull
     */
    public int createProject(File jarFile, String packageName, boolean isSource) {
        try {
            projectData = projectLoader.createProject(jarFile, packageName, isSource);

        } catch (FileNotFoundException ex) {
            
            return 1; // can not find ini file
        } catch (Exception ex) {
            
            return 2; // can not load import classes data
        
        } catch (TokenMgrError tm) {
            
            return 2; // can not load import classes data
        }


        return 0;
        
    }

    /**
     * Method creates a new projet by importing project classes
     *
     * @param classes input classes with package names
     * @param packageName name of classes contatining package
     * @return if successfull
     */
    public int createProject(Map<File, String> classes,  boolean isSource) {
        try {
            projectData = projectLoader.createProject(classes, isSource);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            try {
                
                projectData = projectLoader.createProject(classes, isSource);
            } catch (FileNotFoundException ex1) {
                Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex1);
            } catch (Exception ex1) {
               ex1.printStackTrace();
               JOptionPane.showMessageDialog(master, "Cannot open file \nTry it again, please");
            }
        }
        return 0;
    }
           

    public boolean saveProject(File saveFile) {

        if (projectData != null){

            return projectSaver.saveProject(projectData, saveFile);
        }
        return false;
    }

    /**
     * Method exports project annoatated classes
     *
     * @param target Targer directory
     *
     * @return if successfull
     */
    public boolean exportClasses(File target) {
        
        if (projectData != null){

            return exportClasses.exportAnnotatedClasses(projectData, target);
        }
        return false;

    }

    /**
     * Method returns project classItems
     *
     * @return List of ClassItems
     */
    public List<IclassItem> getClasses() {
        if(projectData!=null){
            return projectData.getClasses();
        }
        return new ArrayList<IclassItem>();
    }

    /**
     * Method returns projcet ClassItems annotations
     * @param name String name of classItem
     * @return List of ClassItems
     */
    public List<Iannotation> getClassesAnnotations(String name) {

        return projectData.getClassesAnnotations(name);
    }

    /**
     * Method returns all ClassItems attributes
     * @param name Name of the ClassItem
     * @return List of ClassItems
     */
    public List<Iattribute> getClassesAttribute(String name) {

        return projectData.getClassesAttribute(name);
    }

    /**
     * Method returns Annotations of selected attribute of class
     *
     * @param className Name of ClassItem
     * @param attrName Name of attribute
     * @return Listi of annotations
     */
    public List<Iannotation> getClassesAttrAnnotations(String className, String attrName) {

        return projectData.getClassesAttrAnnotations(className, attrName);
    }

    /**
     * Method sets annotation value of selected annotation of class
     * @param className Name of class
     * @param annotationName Name of annotation
     * @param value New annotation value
     */
    public void changeClassAnnotation(String className, String annotationName, String value) {

        projectData.changeClassAnnotation(className, annotationName, value);
    }

    /**
     * Method change annotation of seleceted attribute
     *
     * @param className Name of class
     * @param attrName Name of attribute
     * @param AnnotationName Name of annotation
     * @param value New annotation value
     */
    public void changeAttributeAnnotation(String className, String attrName, String AnnotationName, String value) {
        
        projectData.changeAttributeAnnotation(className, attrName, AnnotationName, value);
    }

    /**
     * Method sets no-par annotation of selected annotation of class
     * @param className Name of class
     * @param annotationName Name of annotation
     * @param set If annotaion will be added
     */
    public void changeNoParClassAnnotation(String className, String annotationName, boolean set) {
        projectData.changeClassNoParAnnotation(className, annotationName, set);
    }

    /**
     * Method change no-par annotation of seleceted attribute
     *
     * @param className Name of class
     * @param attrName Name of attribute
     * @param AnnotationName Name of annotation
     * @param set If annotaion will be added
     */
    public void changeNoParAttributeAnnotation(String className, String attrName, String AnnotationName, boolean set) {

        projectData.changeAttributeNoParAnnotation(className, attrName, AnnotationName, set);
    }

    public boolean isClassAnnotParam(String className, String annotName) {
        return projectData.getClassAnnotation(className, annotName).isParam();
    }

    public boolean isAttrAnnotParam(String className, String attrName, String annotName) {
        return projectData.getAttrAnnotation(className, attrName, annotName).isParam();
    }

    public boolean isSource() {

        return projectData.isSource();
    }
    
    @Override
    public String toString(){
        return "Project";
    }

    public void clear() {
        projectData = null;
        projectLoader = new ProjectLoader();        
        projectSaver = new ProjectSaver();
        exportClasses = new ExportClasses();
    }



}
