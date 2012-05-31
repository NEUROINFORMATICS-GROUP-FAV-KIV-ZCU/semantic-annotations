package cz.zcu.kiv.annotations.application;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Filip Markvart
 */
public class FileOperationsTest {

    public FileOperationsTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of fileCopy method, of class FileOperations.
     */
    @Test
    public void testFileCopy() throws Exception {
        System.out.println("fileCopy");

        String fileName = "CopyTestFile";
        File source = new File("./test/testFiles/" + fileName);
        String destDirectory = "./";

        FileOperations.fileCopy(source, destDirectory);

        File targetFile  = new File(destDirectory + fileName);

        boolean result = targetFile.exists();

        assertTrue(result);

        targetFile.delete();
    }

    /**
     * Test of deleteDirectory method, of class FileOperations.
     */
    @Test
    public void testDeleteDirectory() {
        System.out.println("deleteDirectory");
        
        File path = new File("./testDir");
        path.mkdir();

        boolean expResult = true;
        boolean result = FileOperations.deleteDirectory(path);
        assertEquals(expResult, result);
    }

    /**
     * Test of mergeIniFiles method, of class FileOperations.
     */
    @Test
    public void testMergeIniFiles() throws Exception {
        System.out.println("mergeIniFiles");

        File iniDir = new File("temp/inis");
        if (!iniDir.exists()) iniDir.mkdir();

        FileOperations.fileCopy(new File("test/testFiles/classAnnots.ini"), "temp/inis");
        FileOperations.fileCopy(new File("test/testFiles/attrAnnots.ini"), "temp/inis");

        String classIni = "classAnnots.ini";
        String attrIni = "attrAnnots.ini";

        File[] result = FileOperations.mergeIniFiles(classIni, attrIni);
        assertNotNull(result);
    }

}