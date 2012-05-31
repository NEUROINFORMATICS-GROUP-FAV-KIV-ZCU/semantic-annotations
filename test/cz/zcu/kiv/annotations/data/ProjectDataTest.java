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
public class ProjectDataTest {

    File file1;
    File file2;

    String className1;
    String className2;

    Annotation annotationParam;
    Annotation annotationNoPar;

    String annotationValue;

    List<Iannotation> annotationList1;
    List<Iannotation> annotationList2;

    Iattribute attribute1;
    Iattribute attribute2;

    IclassItem classItem1;
    IclassItem classItem2;

    List<Iattribute> attributeList1;
    List<Iattribute> attributeList2;

    List<IclassItem> classItemList;

    String packageName;

    Project instance;


    public ProjectDataTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {

    file1 = new File("testFile1Test");
    file2 = new File("testFile2Test");
    
    className1 = "className1Test";
    className2 = "className2Test";

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

    attributeList1 = new ArrayList<Iattribute>();
    attributeList1.add(attribute1);
    attributeList1.add(attribute2);

    attributeList2 = new ArrayList<Iattribute>();
    attributeList2.add(attribute2);
    attributeList2.add(attribute1);

    classItem1 = new ClassItem(className1, "packageName", file1, annotationList1, attributeList2);
    classItem2 = new ClassItem(className2, "packageName2", file2, annotationList2, attributeList1);

    annotationValue = "annotationValueTest";

    packageName = "packageNameTest";

    classItemList = new ArrayList<IclassItem>();

    classItemList.add(classItem1);
    classItemList.add(classItem2);

    instance = new Project(classItemList, false);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getClasses method, of class ProjectData.
     */
    @Test
    public void testGetClasses() {
        System.out.println("getClasses");
        
        List expResult = classItemList;
        List result = instance.getClasses();
        assertEquals(expResult, result);
    }

    

    /**
     * Test of getClassesAnnotations method, of class ProjectData.
     */
    @Test
    public void testGetClassesAnnotations() {
        System.out.println("getClassesAnnotations");
        
        String name = className1;
        List expResult = annotationList1;
        List result = instance.getClassesAnnotations(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getClassesAttribute method, of class ProjectData.
     */
    @Test
    public void testGetClassesAttribute() {
        System.out.println("getClassesAttribute");
        
        String name = className2;
        List expResult = attributeList1;
        List result = instance.getClassesAttribute(name);
        assertEquals(expResult, result);
    }

    /**
     * Test of getClassesAttrAnnotations method, of class ProjectData.
     */
    @Test
    public void testGetClassesAttrAnnotations() {
        System.out.println("getClassesAttrAnnotations");
        
        String className = className1;
        String attrName = attribute2.getName();
        
        List expResult = annotationList2;
        List result = instance.getClassesAttrAnnotations(className, attrName);
        assertEquals(expResult, result);
    }

    /**
     * Test of changeClassAnnotation method, of class ProjectData.
     */
    @Test
    public void testChangeClassAnnotation() {
        System.out.println("changeClassAnnotation");
        
        String className = className1;
        String annotationName = annotationParam.getName();
        String value = annotationValue;

        instance.changeClassAnnotation(className, annotationName, value);

        String expResult = annotationValue;

        String result = instance.getClassAnnotation(className1, annotationName).getValue();

        assertEquals(expResult, result);
    }

    /**
     * Test of changeClassNoParAnnotation method, of class ProjectData.
     */
    @Test
    public void testChangeClassNoParAnnotationTrue() {
        System.out.println("changeClassNoParAnnotation");
        
        String className = className2;
        String annotationName = annotationNoPar.getName();
        boolean set = true;
        
        instance.changeClassNoParAnnotation(className, annotationName, set);

        boolean result = instance.getClassAnnotation(className2, annotationName).isChangen();

        assertTrue(result);
    }

    /**
     * Test of changeClassNoParAnnotation method, of class ProjectData.
     */
    @Test
    public void testChangeClassNoParAnnotationFalse() {
        System.out.println("changeClassNoParAnnotation");

        String className = className1;
        String annotationName = annotationNoPar.getName();
        boolean set = false;

        instance.changeClassNoParAnnotation(className, annotationName, set);

        boolean result = instance.getClassAnnotation(className1, annotationName).isChangen();

        assertFalse(result);
    }

    /**
     * Test of changeAttributeAnnotation method, of class ProjectData.
     */
    @Test
    public void testChangeAttributeAnnotation() {
        System.out.println("changeAttributeAnnotation");
        
        String className = className1;
        String attrName = attribute2.getName();
        String AnnotationName = annotationParam.getName();
        
        String value = annotationValue;

        String expResult = annotationValue;
        
        instance.changeAttributeAnnotation(className, attrName, AnnotationName, value);

        String result = instance.getAttrAnnotation(className1, attribute2.getName(), annotationParam.getName()).getValue();

        assertEquals(expResult, result);
    }

    /**
     * Test of changeAttributeNoParAnnotation method, of class ProjectData.
     */
    @Test
    public void testChangeAttributeNoParAnnotation() {
        System.out.println("changeAttributeNoParAnnotation");

        String className = className1;
        String attrName = attribute2.getName();
        String AnnotationName = annotationNoPar.getName();

        String value = annotationValue;

        String expResult = annotationValue;

        instance.changeAttributeAnnotation(className, attrName, AnnotationName, value);

        String result = instance.getAttrAnnotation(className1, attribute2.getName(), annotationNoPar.getName()).getValue();

       assertEquals(expResult, result);
    }

    

   

}