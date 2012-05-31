package cz.zcu.kiv.annotations.application;

import java.io.File;
import java.io.FileNotFoundException;
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
public class SavedDataLoaderTest {

    public SavedDataLoaderTest() {
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
     * Test of getSavedAnnotations method, of class SavedDataLoader.
     */
    @Test
    public void testGetSavedAnnotationsCorrect() throws Exception {
        System.out.println("getSavedAnnotations");
        
        File file = new File("test/testFiles/Annotations.dat");
        SavedDataLoader instance = new SavedDataLoader();
        
        List result = instance.getSavedAnnotations(file);
        assertNotNull(result);
    }

    /**
     * Test of getSavedAnnotations method, of class SavedDataLoader.
     */
    @Test(expected = FileNotFoundException.class)
    public void testGetSavedAnnotationsException() throws Exception{
        System.out.println("getSavedAnnotations");

        File file = new File("test/testFiles/NonExist.dat");
        SavedDataLoader instance = new SavedDataLoader();

        instance.getSavedAnnotations(file);
    }

}