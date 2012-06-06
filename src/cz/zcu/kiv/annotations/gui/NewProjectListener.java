/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.zcu.kiv.annotations.gui;



import cz.zcu.kiv.annotations.application.JarExtractor;
import cz.zcu.kiv.annotations.data.IclassItem;
import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author Tomáš
 */
public class NewProjectListener implements ActionListener{
    private MainWindow master;
    public final int PACKAGE_LENGTH = 7;

    NewProjectListener(MainWindow mainWindow) {

        this.master = mainWindow;

    }



    /**
     *  
     * 
     * @return selected Returning selected files
     */
    
    private File[] getSelectedFiles() {


        JFileChooser fileChooser = new JFileChooser(master.lastDir);

       
            fileChooser.setMultiSelectionEnabled(true);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
            fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            
            fileChooser.setFileFilter(new SelectedFilter("jar"));
            fileChooser.setFileFilter(new SelectedFilter("class"));
            fileChooser.setFileFilter(new SelectedFilter("java"));
            fileChooser.setFileFilter(new SelectedFilter("jar", "class", "java")); 
            
            if ((fileChooser.showDialog(master, "Select file/directory") == 0)) {
                File[] selectedFiles = fileChooser.getSelectedFiles();
                List <File> returnFiles = new ArrayList<File>();
                for(File file : selectedFiles){
                    if(file.isFile()){
                        returnFiles.add(file);
                    }else if(file.isDirectory()){
                        recursiveFileAdd(file, returnFiles);
                    }
                }
                master.lastDir = fileChooser.getCurrentDirectory();
                return returnFiles.toArray(new File[0]);

            }else {
              return null;
            }

        
    }
    
    public void recursiveFileAdd(File root, List <File> returnList){
        File[] listFiles = root.listFiles();
        for(File file : listFiles){
            if(file.isFile()){
                if(isViableFile(file)){
                    returnList.add(file);
                }                
            }else if(file.isDirectory()){
                recursiveFileAdd(file, returnList);
            }
        }
    }
    
    public boolean isViableFile(File file){
        if(file.getName().endsWith(".java")) return true;
        if(file.getName().endsWith(".class")) return true;
        if(file.getName().endsWith(".jar")) return true;
        return false;
    }
    
    public String getPackageNameFromJava(File selected1) throws FileNotFoundException, IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader(selected1)); // reader
        String line = null; // set "line" to null
        String name = null; // set "name" to null

        while(bufferedReader.ready()){
            line = bufferedReader.readLine();
            if(line.startsWith("package ")){ // if name contains word "package"
                name = line.substring(PACKAGE_LENGTH, (line.length()-1)); // "name" minus word "package" and ";"
            }
        }
        if(name == null){
            name = "default package"; // if there is no package set "name" to "default package"
        }

        return name;
    }
    
    public String getPackageNameFromClass(File file) throws FileNotFoundException, IOException{
        String name = "";
        /*the principle is as following:
        * every class file contains the complete class name in it, 
        * which in itself then contains the package name.
        * The class name is stored in the constants table, located at a
        * set point within the file structure. Therefore all we need to do
        * is load this field into memory and then access the relevant entry.
        */
        FileInputStream byteStream = new FileInputStream(file);
        //the first eight bytes contain a magic number and version numbers
        //we will assume the file is infact a class file
        for(int i = 0;i<8;i++){
            byteStream.read();
        }
        //next we need to read the length of the constant field
        int read1 = byteStream.read();
        int read2 = byteStream.read();
        int fieldLength = (read1 << 8)+read2;
        //next we load the constants into memory
        //specifically, we need the package name, so we only have to worry
        //about strings
        //with the rest, we only have to read past their bytes
        int classIndex = -1;
        Map <Integer, String> stringFields = new HashMap<Integer, String>();
        Map <Integer, Integer> classIndices = new HashMap<Integer, Integer>();
        //the field is indexed from 1
        for(int i=1;i<fieldLength;i++){
            int readByte = byteStream.read();
            switch(readByte){
                case 1: //CONSTANT_Utf8
                    read1 = byteStream.read();
                    read2 = byteStream.read();
                    int stringLength = (read1 << 8)+read2;
                    byte [] bytes = new byte[stringLength];
                    for(int j=0;j<stringLength;j++){
                        bytes[j]=(byte) byteStream.read();
                    }
                    String val = new String(bytes);
                    stringFields.put(i, val);
                    break;
                case 3://CONSTANT_Integer
                case 4://CONSTANT_Float
                    byteStream.read(new byte[4]);
                    break;
                case 5://CONSTANT_Long
                case 6://CONSTANT_Double
                    byteStream.read(new byte[8]);
                    i++;
                    break;
                case 7://CONSTANT_Class
                    read1 = byteStream.read();
                    read2 = byteStream.read();
                    classIndex = (read1 << 8)+read2;
                    classIndices.put(i, classIndex);
                    break;
                case 8://CONSTANT_String
                    byteStream.read(new byte[2]);
                    break;
                case 9://CONSTANT_Fieldref
                case 10://CONSTANT_Methodref
                case 11://CONSTANT_InterfaceMethodref               
                case 12://CONSTANT_NameAndType
                    byteStream.read(new byte[4]);
                    break;
            }
        }
        //next read past the access flags
        byteStream.read(new byte[2]);
        //now read the index of the string that is the name of this class
        read1 = byteStream.read();
        read2 = byteStream.read();
        classIndex = (read1 << 8)+read2;
        //now just replace internal /s with .s and remove the filename
        name = stringFields.get(classIndices.get(classIndex));
        System.out.println(name);
        name = name.replace("/", ".");
        String[] split = name.split("\\.");
        System.out.println(name);
        if(split.length==0){
            return "";
        }
        name=split[0];
        for(int i=1;i<split.length-1;i++){
            name += "."+split[i];
        }
        System.out.println(name);
        return name;
    }
    
    public int classCreate(File [] selected){
        Map<File, String> map = new HashMap<File, String>();
        for(int i=0;i<selected.length;i++){
            try {
                map.put(selected[i], getPackageNameFromClass(selected[i]));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NewProjectListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NewProjectListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        /*
            * for some reason, the original tool doesnt 
            * actually work if you feed it the package
            * names.
            */
        int pPrepare = master.project.createProject(map, false);
        List<IclassItem> classes = master.project.getClasses();
        for(IclassItem item : classes){
            System.out.println(item.getName());
        }
//            for(IclassItem classItem : classes){
//                try {
//                    classItem.setPackageName(getPackageNameFromJava(classItem.getFile()));
//                } catch (FileNotFoundException ex) {
//                    Logger.getLogger(NewProject.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IOException ex) {
//                    Logger.getLogger(NewProject.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
        return pPrepare;
    }
    
    public int javaCreate(File [] selected){
        Map<File, String> map = new HashMap<File, String>();
        for(int i=0;i<selected.length;i++){
            map.put(selected[i], null);
        }
        /*
         * for some reason, the original tool doesnt 
            * actually work if you feed it the package
            * names.
            */
        int pPrepare =master.project.createProject(map, true);
        List<IclassItem> classes = master.project.getClasses();
        for(IclassItem classItem : classes){
            try {
                classItem.setPackageName(getPackageNameFromJava(classItem.getFile()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(NewProjectListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(NewProjectListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pPrepare;
    }
    
    public void actionPerformed(ActionEvent e) {
        master.disableNewProjectButton();
        int pPrepare = -1;
        File [] selected;
        String packageName = ""; 
        selected = null;
        selected = getSelectedFiles();
        String name = "";
        
        if (selected  == null)
            return;
        
        try {
            master.setCursorCus(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            master.project.clear();
            name = selected[0].toString();
            
            if (selected[0].toString().endsWith("jar")) {                
                
                
                
                System.out.println("it is jar");
                
                List<File> files = null;
                try {
                    files = JarExtractor.extractJar(selected[0], true);
                } catch (Exception ex) {
                    Logger.getLogger(NewProjectListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                boolean containsJavas = false;
                for (File file : files) {
                    System.out.println(file.getName());
                    if (file.getName().endsWith(".java")) {
                        containsJavas = true;
                        break;
                    }
                }
                if (containsJavas) {
                    List<File> javaFiles = new LinkedList<File>();
                    for (File file : files) {
                        if (file.getName().endsWith(".java")) {
                            javaFiles.add(file);
                        }
                    }
                    pPrepare = javaCreate(javaFiles.toArray(new File[0]));
                } else {
                    pPrepare = classCreate(files.toArray(new File[0]));
                }
                
            }
            
            if (selected[0].toString().endsWith("class")) {
                System.out.println("it is class ");                
                pPrepare = classCreate(selected);
            }
            
            if (selected[0].toString().endsWith("java")) {
                System.out.println("it is java");
                pPrepare = javaCreate(selected);
                
            }
            
            System.out.println("pPrepare=" + pPrepare);            
            
            if (pPrepare == 0) {
                // view data, ok
                
                
                master.enableSavingAs();
                master.jtreeWrapper.update(true);
                master.enableGeneration();
                
                
            } else { // else show error
                System.out.println(pPrepare + " number error");
                master.disableSaving();
                
                
                if (pPrepare == 1) {//can not find ini file
                    JOptionPane.showMessageDialog(master, "cannot find ini files");
                } else if (pPrepare == 2) { // can not load classes
                    JOptionPane.showMessageDialog(master, "can not load classes");
                } else {
                    JOptionPane.showMessageDialog(master, "Class reference to unavailable class or bad package name");
                }
            }
        } 
        finally{
            master.setCursorCus(Cursor.getDefaultCursor());
        }
        master.enableNewProjectButton();
    }
    
    
    
    
}
