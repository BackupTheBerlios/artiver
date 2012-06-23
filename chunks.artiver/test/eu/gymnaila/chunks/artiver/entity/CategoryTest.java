/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.entity;

import java.util.Collection;
import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class CategoryTest {
    
    public CategoryTest() {
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
     * Test of getIdCategory method, of class Category.
     */
    @Test
    public void testGetIdCategory() {
        System.out.println("getIdCategory");
        Category instance = new Category();
        Integer expResult = null;
        Integer result = instance.getIdCategory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdCategory method, of class Category.
     */
    @Test
    public void testSetIdCategory() {
        System.out.println("setIdCategory");
        Integer idCategory = null;
        Category instance = new Category();
        instance.setIdCategory(idCategory);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Category.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Category instance = new Category();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Category.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Category instance = new Category();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSort method, of class Category.
     */
    @Test
    public void testGetSort() {
        System.out.println("getSort");
        Category instance = new Category();
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSort method, of class Category.
     */
    @Test
    public void testSetSort() {
        System.out.println("setDescription");
        String description = "";
        Category instance = new Category();
        instance.setDescription(description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModifier method, of class Category.
     */
    @Test
    public void testGetModifier() {
        System.out.println("getModifier");
        Category instance = new Category();
        String expResult = "";
        String result = instance.getModifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModifier method, of class Category.
     */
    @Test
    public void testSetModifier() {
        System.out.println("setModifier");
        String modifier = "";
        Category instance = new Category();
        instance.setModifier(modifier);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArticleCollection method, of class Category.
     */
    @Test
    public void testGetArticleCollection() {
        System.out.println("getArticleCollection");
        Category instance = new Category();
        Collection expResult = null;
        Collection result = instance.getArticleCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArticleCollection method, of class Category.
     */
    @Test
    public void testSetArticleCollection() {
        System.out.println("setArticleCollection");
        Collection<Article> articleCollection = null;
        Category instance = new Category();
        instance.setArticleCollection(articleCollection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Category.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Category instance = new Category();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Category.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Category instance = new Category();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Category.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Category instance = new Category();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModificationDate method, of class Category.
     */
    @Test
    public void testGetModificationDate() {
        System.out.println("getModificationDate");
        Category instance = new Category();
        Date expResult = null;
        Date result = instance.getModificationDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModificationDate method, of class Category.
     */
    @Test
    public void testSetModificationDate() {
        System.out.println("setModificationDate");
        Date modificationDate = null;
        Category instance = new Category();
        instance.setModificationDate(modificationDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
