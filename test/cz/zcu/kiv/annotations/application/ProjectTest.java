package cz.zcu.kiv.annotations.application;

import java.util.TreeMap;
import java.util.HashMap;
import java.util.Map;
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
public class ProjectTest {

    File projectFile;
    File nonExistProjectFile;
    File brokenFile;

    File jarFileClasses;
    File jarFileClassesNonExist;
    String packageName;
    String packageNameSingle;
    Map<File, String> classes;
    Map<File, String> nonExistClasses;

    public ProjectTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {

        projectFile = new File("./test/testFiles/test.apf");
        nonExistProjectFile = new File("./test/testFiles/Nothing.apf");
        brokenFile = new File("./test/testFiles/Broken.apf");
        jarFileClasses = new File("./test/testFiles/ClassFiles.jar");
        jarFileClassesNonExist = new File("./test/testFiles/NonExistFile.jar");
        packageName = "data.pojo";
        packageNameSingle = "tabulka";

        classes = new TreeMap<File, String>();
        classes.put(new File("./test/testFiles/Data.class"), packageNameSingle);


        nonExistClasses = new HashMap<File, String>();
        nonExistClasses.put(new File("./test/testFiles/NonExist1.class"), "nothing");
        nonExistClasses.put(new File("./test/testFiles/NonExist2.class"), "nothing");
    }

    @After
    public void tearDown() {
    
        File outputFile = new File("outputFile.apf");
        outputFile.delete();

        File classFile = new File("./test/Data.class");
        classFile.delete();

        File jaifFile = new File("Annotations.jaif");
        jaifFile.delete();

        File annotationsFile = new File("annotations.dat");
        annotationsFile.delete();

    }

    /**
     * Test of openExistingProject method, of class Project, when project
     * file really exists.
     */
    @Test
    public void testOpenExistingProjectTrue() {
        System.out.println("openExistingProjectTrue");
        
        ProjectManager instance = new ProjectManager();
        int expResult = 0;
        int result = instance.openExistingProject(projectFile);
        assertEquals(expResult, result);
    }

    /**
     * Test of openExistingProject method, of class Project, when project
     * file does not exists.
     */
    @Test
    public void testOpenExistingProjectFalse() {
        System.out.println("openExistingProjectFalse");

        ProjectManager instance = new ProjectManager();
        int expResult = 1;
        int result = instance.openExistingProject(nonExistProjectFile);
        assertEquals(expResult, result);
    }

    /**
     * Test of openExistingProject method, of class Project, when project
     * file is broken.
     */
    @Test
    public void testOpenExistingProjectBroken() {
        System.out.println("openExistingProjectBroken");

        ProjectManager instance = new ProjectManager();
        int expResult = 2;
        int result = instance.openExistingProject(brokenFile);
        assertEquals(expResult, result);
    }
    /**
     * Test of createProject method, of class Project correct Jar file.
     */
    @Test
    public void testCreateProject_File_StringTrue() {
        System.out.println("createProjectTrue");
        
        ProjectManager instance = new ProjectManager();
        int expResult = 1;
        int result = instance.createProject(jarFileClasses, packageName, true);
        assertEquals(expResult, result);
    }

    /**
     * Test of createProject method, of class Project nonExists Jar file.
     */
    @Test
    public void testCreateProject_File_StringFalse() {
        System.out.println("createProjectFalse");

        ProjectManager instance = new ProjectManager();
        int expResult = 1;
        int result = instance.createProject(jarFileClassesNonExist, packageName, false);
        assertEquals(expResult, result);
    }

    /**
     * Test of createProject method, of class Project from single classes.
     */
    @Test
    public void testCreateProject_List_StringTrue() {
        System.out.println("createProjectTrue");
        
        ProjectManager instance = new ProjectManager();
        int expResult = 0;
        int result = instance.createProject(classes, false);
        assertEquals(expResult, result);
    }

    /**
     * Test of createProject method, of class Project from nonExist classes.
     */
    @Test
    public void testCreateProject_List_StringFalse() {
        System.out.println("createProjectFalse");

        ProjectManager instance = new ProjectManager();
        int expResult = 0;
        int result = instance.createProject(nonExistClasses, false);
        assertEquals(expResult, result);
    }

    /**
     * Test of saveProject method, of class Project.
     */
    @Test
    public void testSaveProjectTrue() {
        System.out.println("saveProjectTrue");
        
        File saveFile = new File("outputFile");
        ProjectManager instance = new ProjectManager();
        boolean expResult = true;
        instance.openExistingProject(projectFile);
        boolean result = instance.saveProject(saveFile);
        assertEquals(expResult, result);
    }

    /**
     * Test of saveProject method, of class Project when is not created.
     */
    @Test
    public void testSaveProjectFalse() {
        System.out.println("saveProjectFalse");

        File saveFile = new File("outputFile");
        ProjectManager instance = new ProjectManager();
        boolean expResult = false;
        boolean result = instance.saveProject(saveFile);
        assertEquals(expResult, result);
    }


    /**
     * Test of exportClasses method, of class Project.
     */
    @Test
    public void testExportClasses() {
        System.out.println("exportClasses");
        File target = new File("./test");
        ProjectManager instance = new ProjectManager();
        instance.createProject(classes, false);
        boolean expResult = true;
        boolean result = instance.exportClasses(target);
        assertEquals(expResult, result);
    }

    /**
     * Test of getClasses method, of class Project.
     */
    @Test
    public void testGetClasses() {
        System.out.println("getClasses");

        ProjectManager instance = new ProjectManager();
        instance.createProject(classes, false);
        List result = instance.getClasses();
        assertNotNull(result);
    }

    /**
     * Test of getClassesAnnotations method, of class Project.
     */
    @Test
    public void testGetClassesAnnotations() {
        System.out.println("getClassesAnnotations");
        String name = "Data";
        ProjectManager instance = new ProjectManager();
        instance.createProject(classes, false);

        List result = instance.getClassesAnnotations(name);
        assertNotNull(result);
    }

    /**
     * Test of getClassesAttribute method, of class Project.
     */
    @Test
    public void testGetClassesAttribute() {
        System.out.println("getClassesAttribute");
        String name = "Data";
        ProjectManager instance = new ProjectManager();
        instance.createProject(classes, false);
        List result = instance.getClassesAttribute(name);
        assertNotNull(result);
    }

    /**
     * Test of getClassesAttrAnnotations method, of class Project.
     */
    @Test
    public void testGetClassesAttrAnnotations() {
        System.out.println("getClassesAttrAnnotations");
        
        String className = "Data";
        String attrName = "obsah";
        ProjectManager instance = new ProjectManager();
        instance.createProject(classes, false);
        List result = instance.getClassesAttrAnnotations(className, attrName);
        assertNotNull(result);
    }

    /**
     * Test of changeClassAnnotation method, of class Project.
     */
    @Test
    public void testChangeClassAnnotation() {
        System.out.println("changeClassAnnotation");
        
        String className = "Data";
        String annotationName = "Namespace";
        String value = "NewValue";
        
        ProjectManager instance = new ProjectManager();
        instance.createProject(classes, false);

        instance.changeClassAnnotation(className, annotationName, value);

        String expResult = value;
        String result = instance.getClassesAnnotations(className).get(0).getValue();
      
        assertEquals(expResult, result);
    }

    /**
     * Test of changeAttributeAnnotation method, of class Project.
     */
    @Test
    public void testChangeAttributeAnnotation() {
        System.out.println("changeAttributeAnnotation");
        
        String className = "Data";
        String attrName = "obsah";
        String AnnotationName = "RdfProperty";
        String value = "newValue";
        ProjectManager instance = new ProjectManager();
        instance.createProject(classes, false);

        instance.changeAttributeAnnotation(className, attrName, AnnotationName, value);

        String expResult = value;
        String result = instance.getClassesAttrAnnotations(className, attrName).get(0).getValue();

        assertEquals(expResult, result);
    }

    /**
     * Test of changeNoParClassAnnotation method, of class Project.
     */
    @Test
    public void testChangeNoParClassAnnotation() {
        System.out.println("changeNoParClassAnnotation");
        
        String className = "Data";
        String annotationName = "Transitive";
        
        boolean set = true;
        ProjectManager instance = new ProjectManager();
        instance.createProject(classes, false);

        instance.changeNoParClassAnnotation(className, annotationName, set);

        boolean result = instance.getClassesAnnotations(className).get(2).isChangen();
        assertTrue(result);
    }

    /**
     * Test of changeNoParAttributeAnnotation method, of class Project.
     */
    @Test
    public void testChangeNoParAttributeAnnotation() {
        System.out.println("changeNoParAttributeAnnotation");
        
        String className = "Data";
        String attrName = "obsah";
        String AnnotationName = "Id";
        boolean set = true;

        ProjectManager instance = new ProjectManager();
        instance.createProject(classes, false);

        instance.changeNoParAttributeAnnotation(className, attrName, AnnotationName, set);

        boolean result = instance.getClassesAttrAnnotations(className, attrName).get(1).isChangen();

        assertTrue(result);
    }

    /**
     * Test of isClassAnnotParam method, of class Project.
     */
    @Test
    public void testIsClassAnnotParam() {
        System.out.println("isClassAnnotParam");
        String className = "Data";
        String annotName = "InverseOf";
        String annotName2 = "Symmetric";
        ProjectManager instance = new ProjectManager();
        instance.createProject(classes, false);
        boolean expResult = true;
        boolean expResult2 = false;
        boolean result = instance.isClassAnnotParam(className, annotName);
        assertEquals(expResult, result);
        result = instance.isClassAnnotParam(className, annotName2);
        assertEquals(expResult2, result);
    }

    /**
     * Test of isAttrAnnotParam method, of class Project.
     */
    @Test
    public void testIsAttrAnnotParam() {
        System.out.println("isAttrAnnotParam");

        String className = "Data";
        String attrName = "obsah";
        String annotName = "InverseOf";
        String annotName2 = "Symmetric";
        ProjectManager instance = new ProjectManager();
        instance.createProject(classes, false);
        boolean expResult = true;
        boolean expResult2 = false;
        boolean result = instance.isAttrAnnotParam(className, attrName, annotName);
        assertEquals(expResult, result);
        result = instance.isAttrAnnotParam(className, attrName, annotName2);
        assertEquals(expResult2, result);
    }

}