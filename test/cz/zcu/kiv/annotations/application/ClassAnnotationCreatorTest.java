package cz.zcu.kiv.annotations.application;

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
public class ClassAnnotationCreatorTest {

    public ClassAnnotationCreatorTest() {
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
     * Test of getAnnotations method, of class ClassAnnotationCreator.
     */
    @Test
    public void testGetAnnotations() throws Exception {
        System.out.println("getAnnotations");
        
        String classAnnotIniFile = "classAnnots.ini";
        ClassAnnotationCreator instance = new ClassAnnotationCreator();
        
        boolean result = (instance.getAnnotations(classAnnotIniFile).size() > 0);
        assertTrue(result);
    }

}