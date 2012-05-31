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
public class AttrAnnotationCreatorTest {

    public AttrAnnotationCreatorTest() {
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
     * Test of getAnnotations method, of class AttrAnnotationCreator.
     */
    @Test
    public void testGetAnnotations() throws Exception {
        System.out.println("getAnnotations");
        
        String classAnnotIniFile = "classAnnots.ini";
        AttrAnnotationCreator instance = new AttrAnnotationCreator();
        
        boolean result = (instance.getAnnotations(classAnnotIniFile).size() > 0);
        assertTrue(result);
    }

}