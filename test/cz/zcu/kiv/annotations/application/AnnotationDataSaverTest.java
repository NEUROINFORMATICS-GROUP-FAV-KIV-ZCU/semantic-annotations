package cz.zcu.kiv.annotations.application;

import cz.zcu.kiv.annotations.data.FileSavedAnnotAttr;
import cz.zcu.kiv.annotations.data.FileSavedAnnotClass;
import java.util.ArrayList;
import cz.zcu.kiv.annotations.data.FileSavedAnnotation;
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
public class AnnotationDataSaverTest {

    public AnnotationDataSaverTest() {
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
     * Test of saveAnnotations method, of class AnnotationDataSaver.
     */
    @Test
    public void testSaveAnnotations() throws Exception {
        System.out.println("saveAnnotations");

        FileSavedAnnotation classAnnot = new FileSavedAnnotClass("pckgName", "ClassTest", "AnnotTest", "ValueTest");
        FileSavedAnnotation attrAnnot = new FileSavedAnnotAttr("pckgName2", "ClassTest2", "AnnotTest2", "ValueTest2", "AttrTest", 2);

        List<FileSavedAnnotation> annotations = new ArrayList<FileSavedAnnotation>();
        annotations.add(classAnnot);
        annotations.add(attrAnnot);
        
        File result = AnnotationDataSaver.saveAnnotations(annotations);
        assertNotNull(result);

        result.delete();
    }

}