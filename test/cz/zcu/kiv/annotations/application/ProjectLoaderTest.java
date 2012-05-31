package cz.zcu.kiv.annotations.application;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.io.FileNotFoundException;
import cz.zcu.kiv.annotations.data.Project;
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
public class ProjectLoaderTest {

    public ProjectLoaderTest() {
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
     * Test of openExistingProject method, of class ProjectLoader.
     */
    @Test
    public void testOpenExistingProjectCorrect() throws Exception {
        System.out.println("openExistingProjectCorrect");
        
        File file = new File("./test/testFiles/test.apf");

        ProjectLoader instance = new ProjectLoader();
        
        Project result = instance.openExistingProject(file);
        assertNotNull(result);
    }

    /**
     * Test of openExistingProject method, of class ProjectLoader.
     */
    @Test(expected = FileNotFoundException.class)
    public void testOpenExistingProjectException() throws Exception {
        System.out.println("openExistingProjectException");

        File file = new File("./test/testFiles/NonExist.apf");

        ProjectLoader instance = new ProjectLoader();

        instance.openExistingProject(file);
    }

    /**
     * Test of createProject method, of class ProjectLoader.
     */
    @Test
    public void testCreateProject_File_StringCorrect() throws Exception {
        System.out.println("createProjectCorrect");
        
        File jarFile = new File("./test/testFiles/ClassFiles.jar");
        String packageName = "data.pojo";

        ProjectLoader instance = new ProjectLoader();
        
        Project result = instance.createProject(jarFile, packageName, false);
        assertNotNull(result);
    }

    /**
     * Test of createProject method, of class ProjectLoader.
     */
    @Test(expected = FileNotFoundException.class)
    public void testCreateProject_File_StringException() throws Exception {
        System.out.println("createProjectException");

        File jarFile = new File("./test/testFiles/NonExist.jar");
        String packageName = "data.pojo";

        ProjectLoader instance = new ProjectLoader();

        instance.createProject(jarFile, packageName, false);
    }

    /**
     * Test of createProject method, of class ProjectLoader.
     */
    @Test
    public void testCreateProject_List_StringCorrect() throws Exception {
        System.out.println("createProjectCorrect");
        
        Map<File, String> classes = new HashMap<File, String>();
        classes.put(new File("./test/testFiles/Data.class"), "tabulka");

        ProjectLoader instance = new ProjectLoader();
       
        Project result = instance.createProject(classes, false);
        assertNotNull(result);
    }

    /**
     * Test of createProject method, of class ProjectLoader.
     */
    @Test(expected = NoClassDefFoundError.class)
    public void testCreateProject_List_StringException() throws Exception {
        System.out.println("createProjectException");

        Map<File, String> classes = new HashMap<File, String>();
        classes.put(new File("./test/testFiles/Data.class"), "badName");
        
        ProjectLoader instance = new ProjectLoader();

        instance.createProject(classes, false);
    }

}