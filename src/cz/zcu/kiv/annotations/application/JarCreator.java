package cz.zcu.kiv.annotations.application;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * This class contains methods to create a zip file containing all
 * project files a save it to selected location.
 *
 * @author Filip Markvart
 */
public class JarCreator {

    private static final int BUFFER = 1024; // size of buffer

    /**
     * Method creates a zipped file containing projectFiles and save
     * it as targetFile
     * 
     * @param projectFiles All project files that may be zipped
     * @param targetFile Stored file
     */
    public static void createJar(List<File> projectFiles, File targetFile) throws Exception {

        File outJar = targetFile.getAbsoluteFile();

        if (!outJar.getName().endsWith(Constants.PROJECT_TYPE)) {
            outJar = new File(targetFile.getAbsoluteFile() + "." + Constants.PROJECT_TYPE);
        }

        ZipOutputStream outStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outJar)));
        BufferedInputStream inStream = null;

        byte[] data = new byte[BUFFER];

        for (File FileItem: projectFiles) {

            inStream = new BufferedInputStream(new FileInputStream(FileItem));
            outStream.putNextEntry(new ZipEntry(FileItem.getName()));

            int count;

            while ((count = inStream.read(data, 0, BUFFER)) != -1) {

                outStream.write(data, 0, count);
            }
            outStream.closeEntry();
            inStream.close();
        }
        outStream.flush();
        outStream.close();
        inStream.close();
    }
}
