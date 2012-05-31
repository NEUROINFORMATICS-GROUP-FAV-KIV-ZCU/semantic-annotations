package cz.zcu.kiv.annotations.data;

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
public class AnnotationTest {

    Annotation annotationNoPar;
    Annotation annotationPar;
    String annotationName = "annotationTest";
    String parAnnotValue = "valueTest";


    public AnnotationTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        annotationNoPar = new Annotation(annotationName, false);
        annotationPar = new Annotation(annotationName, true);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Annotation.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        
        String expResult = annotationName;
        String result = annotationNoPar.getName();
        assertEquals(expResult, result);

        result = annotationPar.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of isChangen method, of class Annotation.
     */
    @Test
    public void testIsChangen() {
        System.out.println("isChangen");
        
        assertFalse("Not changed", annotationNoPar.isChangen());
        assertFalse("Not changed", annotationPar.isChangen());

        annotationNoPar.setNoParam(true);
        annotationPar.setValue(parAnnotValue);

        assertTrue("Changed", annotationNoPar.isChangen());
        assertTrue("Changed", annotationPar.isChangen());
    }

    /**
     * Test of setValue method, of class Annotation.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");

        String result = annotationNoPar.getValue();
        assertEquals("", result);

        annotationPar.setValue(parAnnotValue);
        result = annotationPar.getValue();
        assertEquals(parAnnotValue, result);
    }

    /**
     * Test of isParam method, of class Annotation.
     */
    @Test
    public void testIsParam() {
        
        assertFalse(annotationNoPar.isParam());
        assertTrue(annotationPar.isParam());
    }
}