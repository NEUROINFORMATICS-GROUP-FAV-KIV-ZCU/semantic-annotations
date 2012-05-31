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
public class FileSavedAnnotAttrTest {

    String className = "classNameTest";
    String annotationName = "annotationNameTest";
    String annotationValue = "annotationValueTest";
    String attrName = "attrNameTest";

    FileSavedAnnotAttr instance;

    public FileSavedAnnotAttrTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {

        instance = new FileSavedAnnotAttr("package", className, annotationName, annotationValue, attrName, 2);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAttrName method, of class FileSavedAnnotAttr.
     */
    @Test
    public void testGetAttrName() {
        System.out.println("getAttrName");
       
        String expResult = attrName;
        String result = instance.getAttrName();

        assertEquals(expResult, result);
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

        instance = new FileSavedAnnotAttr("packageName", className, annotationName, null, attrName, 2);

        String result = instance.getAnnotValue();
        assertNull(result);
    }
}