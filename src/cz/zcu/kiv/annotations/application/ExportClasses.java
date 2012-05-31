package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.FileSavedAnnotation;
import cz.zcu.kiv.annotations.data.IclassItem;
import cz.zcu.kiv.annotations.data.Project;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class contains methods to export classes annotated
 * by user defined annotations in project data
 *
 * @author Filip Markvart
 */
public class ExportClasses {

    private ChangeAnnotationsGather cAnnotGather;

    public ExportClasses() {

        cAnnotGather = new ChangeAnnotationsGather();
    }

    /**
     * Method generates a Jaif file containig all annotations and call annotation
     * tool that add all annotations to their classes and export classe to
     * selected target
     *
     * @param data Project data
     * @param targetDir Target directory to export classes
     * @return result of operation
     */
    public boolean exportAnnotatedClasses(Project data, File targetDir) {

        List<FileSavedAnnotation> savedAnnots = cAnnotGather.getChangedAnnotations(data.getClasses());

        try {
            JaifFileGenerator.generateJaif(savedAnnots);
            copyProjectClasses(data);

            Set<IclassItem> changed = getChangedClasses(savedAnnots, data.getClasses());
            annotateClasses(changed, data.isSource());

            saveClasses(targetDir, data);

        } catch (IOException ex) {
            return false;
        }

        return true;
    }

    /**
     * Method gather froma FileSavedAnnotations Set of ClassItems that were
     * changed by annotations and need to be exported as annotated
     *
     * @param annotations List of annotations
     * @param classes project classes
     * @return Set of ClassItems that should be annotated
     */
    private Set<IclassItem> getChangedClasses(List<FileSavedAnnotation> annotations, List<IclassItem> classes){

        Set<String> nameList = new HashSet<String>();
        Set<IclassItem> classList = new HashSet<IclassItem>();

        for (FileSavedAnnotation item: annotations){
            
            nameList.add(item.getClassName());
        }

        for (IclassItem itemClass: classes){

            for (String className: nameList) {

                if (itemClass.getName().equals(className)) {
                    
                    classList.add(itemClass);
                }
            }
        }

        return classList;
    }

   /**
    * Method copy all project classes from directory original
    * to directory of project package - so the original which do not
    * have annotaions will have only user defined annotations even user
    * delete these annoataion values after some annoataion export
    *
    * @param packageName Name of project package
    */
    private void copyProjectClasses(Project data) throws IOException {

        File original = new File(Constants.ROOT_PATH + "/" + Constants.ORIG_FILES);
        File [] listFiles = original.listFiles();

        for (int i = 0; i < listFiles.length; i ++) {

            if (!listFiles[i].getName().endsWith(".class") || !listFiles[i].getName().endsWith(".java")) continue;

            String className = listFiles[i].getName().split(".")[0];
            String packageName = data.getSpecClass(className).getPackageName();

            FileOperations.fileCopy(listFiles[i], Constants.ROOT_PATH + "/" + (packageName.replace(".", "/")));
        }
    }

    /**
     * Method calls Annotation Tool library to annotate selected classes
     * by annoatations saved in Jaif file
     * 
     * @param classes Selected classes
     */
    private void annotateClasses(Set<IclassItem> classes, boolean source) throws IOException {
    
        String [] input = new String[2];

        input[1] = Constants.jaifFileName;

        for (IclassItem item: classes) {
            
            input[0] = new File(Constants.ROOT_PATH + "/" + (item.getPackageName().replace(".", "/")) + "/" + item.getName() + ".class").getAbsolutePath();

            if (source) {
                input[0] = new File(Constants.ROOT_PATH + "/" + Constants.SOURCES + "/" + item.getName() + ".java").getAbsolutePath();
                annotator.Main.main(input);
            }else{
                annotations.io.classfile.ClassFileWriter.main(input);
            }
        }
    }

    /**
     * Method saves all annotated project classes to selected target
     *
     * @param target Target path
     */
    private void saveClasses(File target, Project data) throws IOException {

        if (data.isSource()) { // copy all annotated source classes and delete source directory

            File annotated = new File("./annotated");

            File[] sourceClasses = annotated.listFiles();

            for (File item: sourceClasses) {
                FileOperations.fileCopy(item, Constants.ROOT_PATH + "/" + Constants.SOURCES);
            }

            FileOperations.deleteDirectory(annotated);

        }

        for (IclassItem item : data.getClasses()) {

            File source = new File(Constants.ROOT_PATH + "/" + (item.getPackageName().replace(".", "/")) + "/" + item.getName() + ".class");

            if (data.isSource()) {
                source = new File(Constants.ROOT_PATH + "/" + Constants.SOURCES + "/" + item.getName() + ".java");
            }

            FileOperations.fileCopy(source, target.getAbsolutePath() + "/" + (item.getPackageName().replace(".", "/")) );

        }

    }
}
