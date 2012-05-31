package cz.zcu.kiv.annotations.application;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This class extract a jar zipped file to temporary directory
 * and returns List containing extracted Files
 *
 * @author Filip Markvart
 */
public class JarExtractor {

    private static final int BUFFER = 1024; // size of buffer

    /**
     * Method returns a List of extracted files
     * contained in selected jarFile
     * 
     * @param jarFile Zipped files
     * @return List of extracted files
     */
    public static List<File> extractJar(File jarFile, boolean codeFilesOnly) throws Exception {

        if (!prepareTemp()) {
            System.err.println("Failed to create/wipe temp directory");
            throw new Exception();
        }
        List files = new ArrayList<File>();
        BufferedOutputStream destStream = null;
        FileInputStream fileInputStream = new FileInputStream(jarFile);

        ZipInputStream zipStream = new ZipInputStream(new BufferedInputStream(fileInputStream));
        ZipEntry entry;

        // cycle trough all files in JAR
        while((entry = zipStream.getNextEntry()) != null) {

            int count;
            byte data[] = new byte[BUFFER];
            
            System.out.println(Constants.TEMP_PATH + "/" + entry.getName());
            File outFile = new File(Constants.TEMP_PATH + "/" + entry.getName());
            if((outFile.getPath().endsWith(".class") || outFile.getPath().endsWith(".java"))||(!codeFilesOnly)){
                System.out.println(outFile.getName()+" is viable file");
                // write the files to disk
                outFile.getParentFile().mkdirs();
                outFile.createNewFile();
                FileOutputStream foStream = new FileOutputStream(outFile);
                destStream = new BufferedOutputStream(foStream, BUFFER);

                // cycle trough one class file
                while ((count = zipStream.read(data, 0, BUFFER)) != -1) {

                    destStream.write(data, 0, count);
                }

                destStream.flush();
                destStream.close();
                files.add(outFile);
            }
            
        }

        zipStream.close();
        return files;
    }

    private static List<File> getFileList() {

        List files = new ArrayList<File>();

        File tempFile = new File(Constants.TEMP_PATH);

        File [] arrayFile = tempFile.listFiles();

        for (int i = 0; i < arrayFile.length; i ++) {

            files.add(arrayFile[i]);
        }
        return files;
    }

    /**
     * Method extracts all classes zipped in the
     * JAR file to temp directory.
     *
     */
    private static boolean prepareTemp() {

        File temp = new File(Constants.TEMP_PATH);

        if (temp.isDirectory()) {

            FileOperations.deleteDirectory(temp);
        }
        
        return temp.mkdir();
    }


}
