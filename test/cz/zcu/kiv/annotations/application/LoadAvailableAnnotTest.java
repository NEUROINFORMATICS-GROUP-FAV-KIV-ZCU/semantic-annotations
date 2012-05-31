package cz.zcu.kiv.annotations.application;

import java.util.ArrayList;
import cz.zcu.kiv.annotations.data.Iannotation;
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
public class LoadAvailableAnnotTest {

    public LoadAvailableAnnotTest() {
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
     * Test of readAnnotaionList method, of class LoadAvailableAnnot.
     */
    @Test
    public void testReadAnnotaionList() throws Exception {
        System.out.println("readAnnotaionList");
        
        List<Iannotation> annotations = new ArrayList<Iannotation>();
        
        String fileName = "./test/testFiles/attrAnnots.ini";

        List loaded = LoadAvailableAnnot.readAnnotaionList(annotations, fileName);
        boolean result = (loaded.size() > 0);
        
        assertTrue(result);
    }

}