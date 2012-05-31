package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.Annotation;
import cz.zcu.kiv.annotations.data.Attribute;
import cz.zcu.kiv.annotations.data.Iannotation;
import cz.zcu.kiv.annotations.data.Iattribute;
import cz.zcu.kiv.annotations.data.ClassItem;
import java.util.ArrayList;
import cz.zcu.kiv.annotations.data.IclassItem;
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
public class LoadedAnnotSetterTest {

    public LoadedAnnotSetterTest() {
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
     * Test of loadAnnotations method, of class LoadedAnnotSetter.
     */
    @Test
    public void testLoadAnnotations() throws Exception {
        System.out.println("loadAnnotations");
        
        ClassItemsGenerator itemGenerator = new ClassItemsGenerator();
        
        Iannotation annotation = new Annotation("VersionInfo", true);
        List<Iannotation> annotations = new ArrayList<Iannotation>();
        annotations.add(annotation);

        Iattribute attribute = new Attribute("obsah", annotations, 2);
        List<Iattribute> attributes = new ArrayList<Iattribute>();
        attributes.add(attribute);

        ClassItem itemClass = new ClassItem("Data", "pckgName",  new File("test/testFiles/Data.class"), annotations, attributes);
        List<IclassItem> classItems = new ArrayList<IclassItem>();
        classItems.add(itemClass);

        File annotsFile = new File("test/testFiles/annotations2.dat");
        LoadedAnnotSetter instance = new LoadedAnnotSetter();
        instance.loadAnnotations(classItems, annotsFile);

        String annotValue = classItems.get(0).getClassesAttriuteAnnotations("obsah").get(0).getValue();
        String expAnnotValue = "VersionData";
        assertEquals(annotValue, expAnnotValue);
    }

}