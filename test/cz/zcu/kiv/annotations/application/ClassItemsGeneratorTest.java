package cz.zcu.kiv.annotations.application;

import java.util.ArrayList;
import java.io.File;
import java.util.List;
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
public class ClassItemsGeneratorTest {

    public ClassItemsGeneratorTest() {
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
     * Test of getClassItems method, of class ClassItemsGenerator.
     */
    @Test
    public void testGetClassItems() throws Exception {
        System.out.println("getClassItems");
        
        List<File> classes = new ArrayList<File>();
        
        File path = new File("actProject/tabulka");
        if (!path.exists()) path.mkdirs();
        
        FileOperations.fileCopy(new File("./test/testFiles/Data.class"), path.getAbsolutePath());
        
        classes.add((new File("actProject/tabulka/Data.class")));

        String packageName = "tabulka";
        String classAnnotIniFile = "classAnnots.ini";
        String attrAnnotIniFile = "attrAnnots.ini";

        ClassItemsGenerator instance = new ClassItemsGenerator();
       
        boolean result = (instance.getClassItems(classes, packageName, classAnnotIniFile, attrAnnotIniFile, false).size() > 0);
        assertTrue(result);

        FileOperations.deleteDirectory(path);
    }

}