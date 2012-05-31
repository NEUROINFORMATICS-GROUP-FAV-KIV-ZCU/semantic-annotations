package cz.zcu.kiv.annotations.data;

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
public class ClassItemTest {

    ClassItem instance;
    File file;
    String className;
    Annotation annotationParam;
    Annotation annotationNoPar;

    String annotationValue;

    List<Iannotation> annotationList1;
    List<Iannotation> annotationList2;

    Iattribute attribute1;
    Iattribute attribute2;

    List<Iattribute> attributeList;

    public ClassItemTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        
    file = new File("testFileTest");
    className = "classNameTest";
    
    annotationParam = new Annotation("annotationParamTest", true);
    annotationNoPar = new Annotation("annotationNoParTest", false);
    
    annotationList1 = new ArrayList<Iannotation>();
    annotationList1.add(annotationParam);
    annotationList1.add(annotationNoPar);

    annotationList2 = new ArrayList<Iannotation>();
    annotationList2.add(annotationNoPar);
    annotationList2.add(annotationParam);
   
    attribute1 = new Attribute("attribute1Test", annotationList1, 2);
    attribute2 = new Attribute("attribute2Test", annotationList2, 2);

    attributeList = new ArrayList<Iattribute>();
    attributeList.add(attribute1);
    attributeList.add(attribute2);

    instance = new ClassItem(className,"package", file, annotationList1, attributeList);

    annotationValue = "annotationValueTest";

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getName method, of class ClassItem.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");


        String expResult = className;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFile method, of class ClassItem.
     */
    @Test
    public void testGetFile() {
        System.out.println("getFile");
        
        File expResult = file;
        File result = instance.getFile();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClassAnnotations method, of class ClassItem.
     */
    @Test
    public void testGetClassAnnotations() {
        System.out.println("getClassAnnotations");
        
        List expResult = annotationList1;
        List result = instance.getClassAnnotations();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeClassAnnoatation method, of class ClassItem.
     */
    @Test
    public void testChangeClassAnnoatation() {
        System.out.println("changeClassAnnoatation");
        
        instance.changeClassAnnoatation(annotationParam.getName(), annotationValue);
        
        String expResult = annotationValue;
        String result = instance.getClassAnnotations().get(0).getValue();
        
       assertEquals(expResult, result);
    }

    /**
     * Test of getClassAttributes method, of class ClassItem.
     */
    @Test
    public void testGetClassAttributes() {
        System.out.println("getClassAttributes");
        
        List expResult = attributeList;
        List result = instance.getClassAttributes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getClassesAttriuteAnnotations method, of class ClassItem.
     */
    @Test
    public void testGetClassesAttriuteAnnotations() {

        System.out.println("getClassesAttriuteAnnotations");
        String name = attribute1.getName();
        List expResult = attribute1.getAttrAnnotations();
        List result = instance.getClassesAttriuteAnnotations(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of changeAttributeAnnotation method, of class ClassItem.
     */
    @Test
    public void testChangeAttributeAnnotation() {
        System.out.println("changeAttributeAnnotation");
        
        String attrName = attribute2.getName();
        String attrAnnotation = annotationParam.getName();
        String value = annotationValue;
        
        instance.changeAttributeAnnotation(attrName, attrAnnotation, value);

        String expResult = annotationValue;
        String result = instance.getClassesAttriuteAnnotations(attrName).get(1).getValue();

        assertEquals(expResult, result);
    }

    /**
     * Test of changeClassNoParAnnotation method, of class ClassItem.
     */
    @Test
    public void testChangeClassNoParAnnotation() {
        System.out.println("changeClassNoParAnnotation");

        String attrName = attribute1.getName();
        String attrAnnotation = annotationParam.getName();
        String value = annotationValue;

        instance.changeAttributeAnnotation(attrName, attrAnnotation, value);

        String expResult = annotationValue;
        String result = instance.getClassesAttriuteAnnotations(attrName).get(0).getValue();

        assertEquals(expResult, result);
    }

    /**
     * Test of chageAttrNoParAnnotation method, of class ClassItem.
     */
    @Test
    public void testChageAttrNoParAnnotation() {
        System.out.println("chageAttrNoParAnnotation");

        String attrName = attribute2.getName();
        String attrAnnotation = annotationNoPar.getName();
        String value = annotationValue;

        instance.changeAttributeAnnotation(attrName, attrAnnotation, value);

        String expResult = annotationValue;
        String result = instance.getClassesAttriuteAnnotations(attrName).get(0).getValue();

        assertEquals(expResult, result);
    }

}