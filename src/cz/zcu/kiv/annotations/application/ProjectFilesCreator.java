package cz.zcu.kiv.annotations.application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ProjectFilesCreator creates a directory tree which includes
 * all project classes located in package directory tree where
 * they can be reloaded to gather all attributes by reflection
 * and the same classes are store in secondary path as backup
 * while the original files are modified by annotations
 *
 * @author Filip Markvart
 */
public class ProjectFilesCreator {

    private static String destPath;

    /**
     * Method creates a directory tree and save there all project files
     * so the classes can be reflected and annotated.
     * 
     * @param files original project files
     * @param packageName String name of the package classes containing
     * @return project files saved in project directory tree
     */
    public static List<File> createProjectFilesTree(List<File> files, Map<String, String> packageName) throws IOException {

        Set <String> pckgDirs = new HashSet<String>();

        if (packageName == null || packageName.isEmpty()) {

            for (File item: files){
                packageName.put(item.getName().split("\\.")[0], Constants.SOURCES);
            }
        }

        pckgDirs.addAll(packageName.values());


        for(String item: pckgDirs){
            boolean created = createProjectDir(item);
            if (!created) {
                
                return null;
            }
        }

        for (File fileItem: files) {

            String className = fileItem.getName().split("\\.")[0];

            FileOperations.fileCopy(fileItem, Constants.ROOT_PATH + "/" + Constants.ORIG_FILES);
            if(packageName.containsKey(className))
            FileOperations.fileCopy(fileItem, Constants.ROOT_PATH + "/" + packageName.get(className).replace('.', '/'));
        }

       return getPackageFiles();
    }

    public static List<File> createProjectFilesTree(List<File> files, String packageName) throws IOException {


        if (packageName == null) {

            packageName = Constants.SOURCES;
        }


        if (!createProjectDir(packageName)) {
            
            return null;
        }

        for (File fileItem: files) {

            FileOperations.fileCopy(fileItem, Constants.ROOT_PATH + "/" + Constants.ORIG_FILES);
            FileOperations.fileCopy(fileItem, Constants.ROOT_PATH + "/" + packageName.replace('.', '/'));
        }

       return getPackageFiles();
    }




    /**
     * Method creates a root directory and subdirectoryes
     * where all project files will be saved while the program
     * is running.
     *
     * @return boolean if creating was successfull
     */
    private static boolean createProjectDir(String packageName) {

        File rootDir = new File(Constants.ROOT_PATH);
        File originalDir = new File(Constants.ROOT_PATH + "/" + Constants.ORIG_FILES);

        if (rootDir.isDirectory()) { // if directory already exists - delete it

            if (!FileOperations.deleteDirectory(rootDir)) {
                
                return false;
            }
        }

        if (rootDir.mkdir()) {

            if (originalDir.mkdir()) {

                destPath = Constants.ROOT_PATH;
                createPackages(packageName);
                return true;
            }else {
                
                return false;
            }

        }else {
            
            return false;
        }

    }
    
     /**
     * Method creates package structure in actual project
     * to enable copy class files to original path.
     * 
     */
    private static void createPackages(String packageName) {

        String [] folders = packageName.split("\\.");

        if (folders.length == 0) return;

        File part;

        for (int i = 0; i < folders.length; i ++ ){

           destPath += "/" + folders[i];
           part = new File(destPath);
           
           part.mkdir();
        }
    }

    /**
     * Methods returns all files contained in class package directory
     * created by this class
     * 
     * @return List Files
     */
    private static List<File> getPackageFiles() {

        List files = new ArrayList<File>();

        File packageFile = new File(Constants.ROOT_PATH + "/" + Constants.ORIG_FILES);

        File [] listFiles = packageFile.listFiles();

        for (int i = 0; i < listFiles.length; i ++) {

            files.add(listFiles[i]);
        }
        
        return files;
    }
}
