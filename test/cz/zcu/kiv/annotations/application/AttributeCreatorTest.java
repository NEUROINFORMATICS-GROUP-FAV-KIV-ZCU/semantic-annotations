package cz.zcu.kiv.annotations.application;

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
public class AttributeCreatorTest {

    public AttributeCreatorTest() {
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
     * Test of getAttributes method, of class AttributeCreator.
     */
    @Test
    public void testGetAttributes() throws Exception {
        System.out.println("getAttributes");
        
        File path = new File("actProject/tabulka");
        if (!path.exists()) path.mkdirs();
        
        FileOperations.fileCopy(new File("./test/testFiles/Data.class"), path.getAbsolutePath());
     
        String className = "Data";
        String packageName = "tabulka";
        String classAnnotIniFile = "classAnnots.ini";
        AttributeCreator instance = new AttributeCreator();
        
        boolean result = (instance.getAttributes(className, packageName, classAnnotIniFile, false).size() > 0);
        assertTrue(result);

        FileOperations.deleteDirectory(path);
    }

}