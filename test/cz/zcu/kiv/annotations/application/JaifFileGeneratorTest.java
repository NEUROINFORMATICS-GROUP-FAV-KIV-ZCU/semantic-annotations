package cz.zcu.kiv.annotations.application;

import java.util.ArrayList;
import cz.zcu.kiv.annotations.data.FileSavedAnnotAttr;
import cz.zcu.kiv.annotations.data.FileSavedAnnotClass;
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
public class JaifFileGeneratorTest {

    public JaifFileGeneratorTest() {
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

        File output = new File(Constants.jaifFileName);
        output.delete();
    }

    /**
     * Test of generateJaif method, of class JaifFileGenerator.
     */
    @Test
    public void testGenerateJaif() throws Exception {
        System.out.println("generateJaif");
        
        FileSavedAnnotation classAnnot = new FileSavedAnnotClass("pckgName", "ClassTest", "AnnotTest", "ValueTest");
        FileSavedAnnotation attrAnnot = new FileSavedAnnotAttr("pckgName2", "ClassTest2", "AnnotTest2", "ValueTest2", "AttrTest", 2);
        
        List<FileSavedAnnotation> annotations = new ArrayList<FileSavedAnnotation>();
        annotations.add(classAnnot);
        annotations.add(attrAnnot);

        String packageName = "packageTest";

        JaifFileGenerator.generateJaif(annotations);

        boolean result = new File(Constants.jaifFileName).exists();

        assertTrue(result);
    }

}