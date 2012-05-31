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
public class FileSavedAnnotClassTest {

    String className = "classNameTest";
    String annotationName = "annotationNameTest";
    String annotationValue = "annotationValueTest";

    FileSavedAnnotClass instance;

    public FileSavedAnnotClassTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {

        instance = new FileSavedAnnotClass("pacakageName", className, annotationName, annotationValue);
    }

    @After
    public void tearDown() {
    }

     /**
     * Test of getClassName method, of class FileSavedAnnotation.
     */
    @Test
    public void testGetClassName() {
        System.out.println("getClassName");

        String expResult = className;
        String result = instance.getClassName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAnnotName method, of class FileSavedAnnotation.
     */
    @Test
    public void testGetAnnotName() {
        System.out.println("getAnnotName");

        String expResult = annotationName;
        String result = instance.getAnnotName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAnnotValue method, of class FileSavedAnnotation.
     */
    @Test
    public void testGetAnnotValue() {
        System.out.println("getAnnotValue");

        String expResult = annotationValue;
        String result = instance.getAnnotValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAnnotValue method, of class FileSavedAnnotation.
     */
    @Test
    public void testGetAnnotValueNoParam() {
        System.out.println("getAnnotValue");

        instance = new FileSavedAnnotClass("packageName", className, annotationName, null);

        String result = instance.getAnnotValue();
        assertNull(result);
    }

}