package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.Annotation;
import cz.zcu.kiv.annotations.data.Attribute;
import cz.zcu.kiv.annotations.data.ClassItem;
import cz.zcu.kiv.annotations.data.Iannotation;
import cz.zcu.kiv.annotations.data.Iattribute;
import java.io.File;
import java.util.ArrayList;
import cz.zcu.kiv.annotations.data.IclassItem;
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
public class ChangeAnnotationsGatherTest {

    public ChangeAnnotationsGatherTest() {
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
     * Test of getChangedAnnotations method, of class ChangeAnnotationsGather.
     */
    @Test
    public void testGetChangedAnnotations() {
        System.out.println("getChangedAnnotations");

        Iannotation annotation = new Annotation("Symmetric", false);
        annotation.setNoParam(true);
        List<Iannotation> annotations = new ArrayList<Iannotation>();
        annotations.add(annotation);

        Iattribute attribute = new Attribute("obsah", annotations, 2);
        List<Iattribute> attributes = new ArrayList<Iattribute>();
        attributes.add(attribute);

        ClassItem itemClass = new ClassItem("Data", "pckgName", new File("test/testFiles/Data.class"), annotations, attributes);
        List<IclassItem> classItems = new ArrayList<IclassItem>();
        classItems.add(itemClass);

        ChangeAnnotationsGather instance = new ChangeAnnotationsGather();
        
        boolean result = (instance.getChangedAnnotations(classItems).size() > 0);
        assertTrue(result);
        
    }

}