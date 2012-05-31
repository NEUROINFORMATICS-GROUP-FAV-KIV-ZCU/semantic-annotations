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
public class JarCreatorTest {

    public JarCreatorTest() {
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

        File targetFile = new File("./test/testFiles/CreatedProjectJar.apf");
        targetFile.delete();
    }

    /**
     * Test of createJar method, of class JarCreator.
     */
    @Test
    public void testCreateJar() throws Exception {
        System.out.println("createJar");

        List<File> projectFiles = new ArrayList<File>();
        projectFiles.add(new File("./test/testFiles/Data.class"));
        projectFiles.add(new File("./test/testFiles/annotations.dat"));

        File targetFile = new File("./test/testFiles/CreatedProjectJar.apf");

        JarCreator.createJar(projectFiles, targetFile);

        boolean result = targetFile.exists();

        assertTrue(result);
    }

}