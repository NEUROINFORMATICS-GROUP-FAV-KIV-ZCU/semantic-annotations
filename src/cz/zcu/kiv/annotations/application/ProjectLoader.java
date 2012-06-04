package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.IclassItem;
import cz.zcu.kiv.annotations.data.Project;
import japa.parser.TokenMgrError;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Project Loader loads existing project or create a new one
 * ant returns it as ProjectData instance
 *
 * @author Filip Markvart
 */
public class ProjectLoader implements IprojectLoader{

    private ClassItemsGenerator classIGenerator;
    private Project projectData;
    private LoadedAnnotSetter loadAnnot;

    public ProjectLoader() {
        init();
    }
    
    public void init(){
        classIGenerator = new ClassItemsGenerator();
        loadAnnot = new LoadedAnnotSetter();
    }

    /**
     * Method opens an existing file
     * 
     * @param file Project file
     * @return new ProjectData instance
     */
    public Project openExistingProject(File file) throws FileNotFoundException, Exception{
            
            boolean sources = false;

            List<File> projectFiles = JarExtractor.extractJar(file, false);

            String[] inis = getAnnotIniFiles(projectFiles);

            for (File filePckg: projectFiles) {
                if (filePckg.getName().equals(Constants.PCKG_FILE)) {

                    Map<String, String> pckgNames = loadPckgName(filePckg);
                    sources = pckgNames.isEmpty();
                    // copy project files
                    List<File> projectFile = ProjectFilesCreator.createProjectFilesTree(projectFiles, pckgNames);
                    //creates class items
                    List<IclassItem> classItems = classIGenerator.getClassItems(selectClasses(projectFile), pckgNames, inis[0], inis[1], sources);
                    // load saved annotation values
                    
                    for (File projDataSave: projectFile) {
                        
                        if (projDataSave.getName().equals(Constants.PROJECT_DATA)) {

                          
                           loadAnnot.loadAnnotations(classItems, projDataSave);
                           
                           projectData = new Project(classItems, sources);
                            
                           return projectData; 
                        }
                    }
                }
            }
            
            return null;
    }

    public Project createProject(File jarFile, String packageName, boolean isSource) throws FileNotFoundException, Exception, TokenMgrError {
            init();
            List<File> projectFiles = JarExtractor.extractJar(jarFile, true);
            // copy project files
            List<File> projectFile = ProjectFilesCreator.createProjectFilesTree(projectFiles, packageName);
            //creates class items
            List<IclassItem> classItems = classIGenerator.getClassItems(selectClasses(projectFile), packageName, Constants.classAnnotFile, Constants.attrAnnotFile, isSource);

            projectData = new Project(classItems, isSource);

            return projectData;
    }

    public Project createProject(Map<File, String> classes, boolean isSource) throws FileNotFoundException, Exception, NoClassDefFoundError {
            init();
            Map <String, String> packageNames = new HashMap<String, String>();
            List <File> files = new ArrayList<File>();


            for (Map.Entry<File, String> item: classes.entrySet()){
                files.add(item.getKey());

                if (item.getValue() != null && item.getValue().length() > 0) {

                    packageNames.put(item.getKey().getName().split("\\.")[0], item.getValue());
                }
            }

            // copy project files
            List<File> projectFile = ProjectFilesCreator.createProjectFilesTree(files, packageNames);
            //creates class items
            List<IclassItem> classItems = classIGenerator.getClassItems(selectClasses(projectFile), packageNames, Constants.classAnnotFile, Constants.attrAnnotFile, isSource);

            projectData = new Project(classItems, isSource);

            return projectData;
    }

    /**
     * Method opens packageName containing file and returns this value as String
     *
     *
     * <ClassName><PackageName>
     *
     * @param pckgFile input file
     * @return String value of package name
     * @throws FileNotFoundException
     */
    private Map<String, String> loadPckgName(File pckgFile) throws FileNotFoundException, ArrayIndexOutOfBoundsException {
        Scanner input = new Scanner(pckgFile);

        Map<String, String> pckgNames = new HashMap<String, String>();

        while (input.hasNext()){

            String pckgName = input.next();

            String[] parsed = pckgName.split(Constants.PARSER_CHAR);

            pckgNames.put(parsed[0], parsed[1]);
            
        }

        input.close();

        return pckgNames;
    }

    /**
     * Method select class files from input files in List and returns them
     *
     * @param files input files
     * @return class files
     */
    private List<File> selectClasses(List<File> files) {
        List<File> classFiles = new ArrayList<File>();

        for (File file: files) {

            if (file.getName().endsWith(".class") || file.getName().endsWith(".java")) {
                classFiles.add(file);
            }
        }
        return classFiles;
    }

    /**
     * Method returns an array of files that contains list of allowed
     * annotations to classes and their attributes (classes ini first)
     *
     * @param projectFiles Project files
     * @return Array of ini files
     */
    private String[] getAnnotIniFiles(List<File> projectFiles) {
        String[] iniFiles = new String[2];

        for (File fileIni: projectFiles) {

            if (fileIni.getName().equals(Constants.classAnnotFile)) {
               iniFiles[0] = fileIni.getAbsolutePath();
            }

            if (fileIni.getName().equals(Constants.attrAnnotFile)) {
               iniFiles[1] = fileIni.getAbsolutePath();
            }
        }
        return iniFiles;
    }
}
