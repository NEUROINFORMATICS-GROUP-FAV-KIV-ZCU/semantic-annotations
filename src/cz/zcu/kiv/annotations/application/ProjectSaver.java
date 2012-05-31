package cz.zcu.kiv.annotations.application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import cz.zcu.kiv.annotations.data.FileSavedAnnotation;
import cz.zcu.kiv.annotations.data.IclassItem;
import cz.zcu.kiv.annotations.data.Project;

/**
 * Project saver contains methods to save acual project data to
 * selected file on the disk.
 *
 * @author Filip Markvart
 */
public class ProjectSaver  implements IprojectSaver{

    private ChangeAnnotationsGather annotGather;

    public ProjectSaver() {
        
        annotGather = new ChangeAnnotationsGather();
    }
    
    /**
     * Method save selected project to disk and return true if successfully
     * 
     * @param data saved data
     * @param saveFile target save file
     * @return successfullity
     */
    public boolean saveProject(Project data, File saveFile) {

        List<FileSavedAnnotation> fSavedAnnot = annotGather.getChangedAnnotations(data.getClasses());

        try {
            File annotationFile = AnnotationDataSaver.saveAnnotations(fSavedAnnot);
            File savePackage = savePackageName(data);
            
            List<File> projectFiles = getProjectClassFiles();

            File[] mergedIniFiles = FileOperations.mergeIniFiles(Constants.classAnnotFile, Constants.attrAnnotFile);

            projectFiles.add(mergedIniFiles[0]);
            projectFiles.add(mergedIniFiles[1]);
            projectFiles.add(annotationFile);
            projectFiles.add(savePackage);

            JarCreator.createJar(projectFiles, saveFile);
        } catch (Exception ex) {
            
            return false;
        }

    return true;
    }

    /**
     * Method save to file package.dat name of project containing package
     * and return pointer to file
     *
     * @param packageName Name of the package
     * @return point to file
     * @throws IOException
     */
    private File savePackageName(Project data) throws IOException {

        String pckgPath = Constants.ROOT_PATH + "/" + Constants.ORIG_FILES + "/" +Constants.PCKG_FILE;

        File savedFile = new File(pckgPath);

        FileWriter fwriter = new FileWriter(savedFile);

        if (data.isSource()) {

            fwriter.close();
            return savedFile;
        }

        for (IclassItem item: data.getClasses()){

            fwriter.write(item.getName() + Constants.PARSER_CHAR + item.getPackageName() + "\n");
        }
        
        fwriter.close();

        return savedFile;
    }

    /**
     * Method returns a List of all project class files
     * 
     * @return List of class files
     */
    private List<File> getProjectClassFiles() {

        List<File> classFiles = new ArrayList<File>();

        File fileList = new File(Constants.ROOT_PATH + "/" + Constants.ORIG_FILES);

        File [] allFiles = fileList.listFiles();

        for (int i = 0; i < allFiles.length; i++) {

            if (allFiles[i].getName().endsWith(".class") || allFiles[i].getName().endsWith(".java")) {

                classFiles.add(allFiles[i]);
            }
        }
        return classFiles;
    }

}
