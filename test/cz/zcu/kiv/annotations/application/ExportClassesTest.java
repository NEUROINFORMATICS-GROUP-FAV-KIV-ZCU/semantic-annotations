package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.Project;
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
public class ExportClassesTest {

    public ExportClassesTest() {
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
     * Test of exportAnnotatedClasses method, of class ExportClasses.
     */
    @Test
    public void testExportAnnotatedClasses() throws Exception{
        System.out.println("exportAnnotatedClasses");
        
        ProjectLoader pLoader = new ProjectLoader();
        Project data = pLoader.openExistingProject(new File("./test/testFiles/test.apf"));

        File targetDir = new File("./test/testExport");
        if (!targetDir.exists()) targetDir.mkdir();

        ExportClasses instance = new ExportClasses();
        
        boolean result = instance.exportAnnotatedClasses(data, targetDir);
        assertTrue(result);

        File[] result2 = targetDir.listFiles();
        assertNotNull(result2);

        FileOperations.deleteDirectory(targetDir);
    }

}