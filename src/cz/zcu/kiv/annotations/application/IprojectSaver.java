package cz.zcu.kiv.annotations.application;

import java.io.File;
import cz.zcu.kiv.annotations.data.Project;

/**
 * This interface ensures method to save a project to selected path
 *
 * @author Filip Markvart
 */
public interface IprojectSaver {

    /**
     * Method save opened project to disk and return if successfull
     *
     * @param data Project data
     * @param saveFile Name of the saved file
     *
     * @return if project save was successfull
     */
    public boolean saveProject(Project data, File saveFile);


}
