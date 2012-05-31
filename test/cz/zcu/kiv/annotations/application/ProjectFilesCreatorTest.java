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
public class ProjectFilesCreatorTest {

    public ProjectFilesCreatorTest() {
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
        FileOperations.deleteDirectory(new File("./actProject"));

    }

    /**
     * Test of createProjectFilesTree method, of class ProjectFilesCreator.
     */
    @Test
    public void testCreateProjectFilesTree() throws Exception {
        System.out.println("createProjectFilesTree");
        
        List<File> files = new ArrayList<File>();
        files.add(new File("./test/testFiles/Data.class"));
        files.add(new File("./test/testFiles/Weather.class"));

        String packageName = "firstDir.secondDir";
     
        List result = ProjectFilesCreator.createProjectFilesTree(files, packageName);
        assertNotNull(result);
    }

}