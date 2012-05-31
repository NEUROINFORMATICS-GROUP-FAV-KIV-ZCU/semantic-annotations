package cz.zcu.kiv.annotations.application;

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
public class CommonAnnotsTest {

    public CommonAnnotsTest() {
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
     * Test of getCommonAnnots method, of class CommonAnnots.
     */
    @Test
    public void testGetCommonAnnots() {
        System.out.println("getCommonAnnots");
        
        ProjectManager project = new ProjectManager();
        project.openExistingProject(new File("test/testFiles/test.apf"));
        
        
        List<String[]> compareList = new ArrayList<String[]>();

        String[] item1 = {"Data", "measuration"};
        String[] item2 = {"Data", "mimetype"};
        compareList.add(item1);
        compareList.add(item2);

        boolean result = (CommonAnnots.getCommonAnnots(project, compareList).size() > 0);
        assertTrue(result);
    }

}