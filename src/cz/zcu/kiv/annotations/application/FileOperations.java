package cz.zcu.kiv.annotations.application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This class contains methods to copy files and delete directories
 * with included files
 *
 * @author Filip Markvart
 */
public class FileOperations {

    private static final int BUFFER = 4096;

    /**
     * Method copy a source file to destPath directory.
     *
     * @param source Source file
     * @param destDirectory Destination path directory
     * @throws IOException Copy exception
     */
    public static void fileCopy(File source, String destDirectory) throws IOException {

        File sourceFile = source;

        if (!new File(destDirectory).isDirectory()) createDir(destDirectory);

        File destFile = new File(destDirectory + "/" + source.getName());

        InputStream inStream = new FileInputStream(sourceFile);
        OutputStream outStream = new FileOutputStream(destFile);

        byte[] buffer = new byte[BUFFER];
        int length;

        while ((length = inStream.read(buffer)) > 0) {

            outStream.write(buffer, 0, length);
        }

        inStream.close();
        outStream.close();
    }

    private static void createDir(String dest){

       File directory = new File(dest);
       directory.mkdirs();

    }

    /**
     * Method delete all files in selected path
     * @param path Path of deleted directory tree
     * @return true if delete was successfull
     */
    public static boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }

    /**
     * Method merges two annotation ini files so the result is two ini files
     * that contains annotations that contains both recent files, but items
     * do not repeate
     * Class annotations and attribute annotaons are still separated !
     * 
     * @param classIni Class annotations ini file name
     * @param annotIni Attribute annotations ini file name
     * @return
     */
    public static File[] mergeIniFiles(String classIni, String attrIni) throws FileNotFoundException, IOException{

        Set<String> classAnnots = new HashSet<String>();
        Set<String> attrAnnots = new HashSet<String>();

        Scanner input = new Scanner(new File(classIni));

        while (input.hasNext()) {
            classAnnots.add(input.nextLine());
        }
        input.close();

        input = new Scanner(new File(attrIni));

        while (input.hasNext()) {
            attrAnnots.add(input.nextLine());
        }
        input.close();

        File recentClassIni = new File("temp/" + classIni);

        if (recentClassIni.exists()) {

            input = new Scanner(recentClassIni);

            while (input.hasNext()) {
                classAnnots.add(input.nextLine());
            }
            input.close();
        }

        File recentAttrIni = new File("temp/" + attrIni);

        if (recentAttrIni.exists()) {

            input = new Scanner(recentAttrIni);

            while (input.hasNext()) {
                attrAnnots.add(input.nextLine());
            }
            input.close();
        }

        createIniDir();
        File targetClassIni = new File("temp/inis/" + classIni);
        File targetAttrIni = new File("temp/inis/" + attrIni);

        FileWriter outputFileWriter = new FileWriter(targetClassIni);

        for(String item: classAnnots) {
            outputFileWriter.write(item + "\n");
        }
        outputFileWriter.flush();
        outputFileWriter.close();

        outputFileWriter = new FileWriter(targetAttrIni);

        for(String item: attrAnnots) {
            outputFileWriter.write(item + "\n");
        }
        outputFileWriter.flush();
        outputFileWriter.close();

        File[] targetFiles = new File[2];
        targetFiles[0] = targetClassIni;
        targetFiles[1] = targetAttrIni;

        return targetFiles;
    }


    private static void createIniDir() {

        File temp = new File("temp/inis");

        if (!temp.isDirectory()) {
            temp.mkdirs();
        }
    }

}
