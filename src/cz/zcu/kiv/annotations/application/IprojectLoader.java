package cz.zcu.kiv.annotations.application;

import java.io.File;
import cz.zcu.kiv.annotations.data.Project;
import java.io.FileNotFoundException;
import java.util.Map;

/**
 * This interface ensures method to create a new project by importing
 * class files or JAR packed files and opening existing project
 *
 * @author Filip Markvart
 */
public interface IprojectLoader {

    /**
     * Method opens existing project and returns a new ProjectData
     * 
     * @param file Project file
     * @return new ProjectData
     */
    public Project openExistingProject(File file) throws FileNotFoundException, Exception;

     /**
     * Method creates a new project importing classes int JAR
     * and returns a new ProjectData
     *
     * @param file Project file
     * @return new ProjectData
     */
    public Project createProject(File jarFile, String packageName, boolean isSource) throws FileNotFoundException, Exception;

     /**
     * Method create a new project importing single classes
     * and returns a new ProjectData
     *
     * @param file Project file
     * @return new ProjectData
     */
    public Project createProject(Map<File, String> classes, boolean isSource) throws FileNotFoundException, Exception;
    
    
}
