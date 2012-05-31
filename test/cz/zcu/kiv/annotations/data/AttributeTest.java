package cz.zcu.kiv.annotations.data;

import java.util.ArrayList;
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
public class AttributeTest {

    Annotation paramAnnot;
    Annotation noParamAnnot;

    String parAnnotName = "paramTest";
    String noParAnnotName = "noParamTest";

    String parAnnotValue = "valueTest";
    String attrName = "attributeTest";

    List<Iannotation> annotList;

    Attribute instance;


    public AttributeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        paramAnnot = new Annotation(parAnnotName, true);
        paramAnnot.setValue(parAnnotValue);
        noParamAnnot = new Annotation(noParAnnotName, false);

        annotList = new ArrayList<Iannotation>();
        annotList.add(paramAnnot);
        annotList.add(noParamAnnot);


        instance = new Attribute(attrName, annotList, 2);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class Attribute.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");

        String expResult = attrName;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAttrAnnotations method, of class Attribute.
     */
    @Test
    public void testGetAttrAnnotations() {
        System.out.println("getAttrAnnotations");
        
        List expResult = annotList;
        List result = instance.getAttrAnnotations();
        assertSame(expResult, result);
    }

    /**
     * Test of setAttrAnnotationValue method, of class Attribute.
     */
    @Test
    public void testSetAttrAnnotationValueTrue() {
        System.out.println("setAttrAnnotationValue");
        
        instance.setAttrAnnotationValue(parAnnotName, parAnnotValue);
        
        String value = instance.getAttrAnnotations().get(0).getValue();
        boolean expIsChaged = instance.getAttrAnnotations().get(0).isChangen();

        assertEquals(parAnnotValue, value);
        assertTrue(expIsChaged);
    }

    /**
     * Test of setAttrAnnotationValue method, of class Attribute.
     */
    @Test
    public void testSetAttrAnnotationValueFalse() {
        System.out.println("setAttrAnnotationValue");

        instance.setAttrAnnotationValue(parAnnotName, null);
        
        boolean expIsChaged = instance.getAttrAnnotations().get(0).isChangen();

        assertFalse(expIsChaged);
    }

    /**
     * Test of setNoParAttrAnnotValue method, of class Attribute.
     */
    @Test
    public void testSetNoParAttrAnnotValueTrue() {
        System.out.println("setNoParAttrAnnotValue");
        
        instance.setNoParAttrAnnotValue(noParAnnotName, true);

        boolean expIsChaged = instance.getAttrAnnotations().get(1).isChangen();
        assertTrue(expIsChaged);
    }

    /**
     * Test of setNoParAttrAnnotValue method, of class Attribute.
     */
    @Test
    public void testSetNoParAttrAnnotValueFalse() {
        System.out.println("setNoParAttrAnnotValue");

        instance.setNoParAttrAnnotValue(noParAnnotName, false);

        boolean expIsChaged = instance.getAttrAnnotations().get(1).isChangen();
        assertFalse(expIsChaged);
    }

}