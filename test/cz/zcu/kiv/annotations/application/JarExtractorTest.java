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
public class JarExtractorTest {

    public JarExtractorTest() {
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
     * Test of extractJar method, of class JarExtractor.
     */
    @Test
    public void testExtractJar() throws Exception {
        System.out.println("extractJar");

        File jarFile = new File("./test/testFiles/ClassFiles.jar");

        List result = JarExtractor.extractJar(jarFile, false);
        assertNotNull(result);
    }

}