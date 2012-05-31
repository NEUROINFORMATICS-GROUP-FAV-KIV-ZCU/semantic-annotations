package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.IclassItem;
import cz.zcu.kiv.annotations.data.Attribute;
import cz.zcu.kiv.annotations.data.Iattribute;
import cz.zcu.kiv.annotations.data.Iannotation;
import cz.zcu.kiv.annotations.data.Annotation;
import java.io.File;
import cz.zcu.kiv.annotations.data.ClassItem;
import java.util.ArrayList;
import java.util.List;
import cz.zcu.kiv.annotations.data.Project;
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
public class ProjectSaverTest {

    public ProjectSaverTest() {
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
    
        File saveFile = new File("test/testFiles/saveProjectFile.apf");
        saveFile.delete();
    }

    /**
     * Test of saveProject method, of class ProjectSaver.
     */
    @Test
    public void testSaveProject() {
        System.out.println("saveProject");

        Iannotation annotation = new Annotation("Symmetric", false);
        List<Iannotation> annotations = new ArrayList<Iannotation>();
        annotations.add(annotation);

        Iattribute attribute = new Attribute("obsah", annotations, 2);
        List<Iattribute> attributes = new ArrayList<Iattribute>();
        attributes.add(attribute);

        ClassItem itemClass = new ClassItem("Data", "pckgName", new File("test/testFiles/Data.class"), annotations, attributes);
        List<IclassItem> classItems = new ArrayList<IclassItem>();
        classItems.add(itemClass);

        Project data = new Project(classItems, false);

        ProjectSaver instance = new ProjectSaver();

        boolean result = instance.saveProject(data, new File("test/testFiles/saveProjectFile"));

        assertTrue(result);

        File saveFile = new File("test/testFiles/saveProjectFile.apf");
        
        assertTrue(saveFile.exists());
    }

}