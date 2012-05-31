package cz.zcu.kiv.annotations.application;

import java.io.File;
import java.util.List;
import cz.zcu.kiv.annotations.data.FileSavedAnnotation;

/**
 * This interface ensures method to gather anotations saved in file
 * in propriate data format which can be used in application
 *
 * @author Filip Markvart
 */
public interface IsaveDataLoader {

    /**
     * Method gets all annoations saved in File file in propriate 
     * format
     * 
     * @param file File where annoations are saved
     * @return List of FileSavedAnnoations
     */
    public List<FileSavedAnnotation> getSavedAnnotations(File file) throws Exception;
}
