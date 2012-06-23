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
public class StockTest {
    
    public StockTest() {
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
     * Test of getIdStock method, of class Stock.
     */
    @Test
    public void testGetIdStock() {
        System.out.println("getIdStock");
        Stock instance = new Stock();
        Integer expResult = null;
        Integer result = instance.getIdStock();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdStock method, of class Stock.
     */
    @Test
    public void testSetIdStock() {
        System.out.println("setIdStock");
        Integer idStock = null;
        Stock instance = new Stock();
        instance.setIdStock(idStock);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Stock.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Stock instance = new Stock();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Stock.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Stock instance = new Stock();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCountry method, of class Stock.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        Stock instance = new Stock();
        String expResult = "";
        String result = instance.getCountry();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCountry method, of class Stock.
     */
    @Test
    public void testSetCountry() {
        System.out.println("setCountry");
        String country = "";
        Stock instance = new Stock();
        instance.setCountry(country);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddress method, of class Stock.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        Stock instance = new Stock();
        String expResult = "";
        String result = instance.getAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAddress method, of class Stock.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        String address = "";
        Stock instance = new Stock();
        instance.setAddress(address);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModifier method, of class Stock.
     */
    @Test
    public void testGetModifier() {
        System.out.println("getModifier");
        Stock instance = new Stock();
        String expResult = "";
        String result = instance.getModifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModifier method, of class Stock.
     */
    @Test
    public void testSetModifier() {
        System.out.println("setModifier");
        String modifier = "";
        Stock instance = new Stock();
        instance.setModifier(modifier);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArticleCollection method, of class Stock.
     */
    @Test
    public void testGetArticleCollection() {
        System.out.println("getArticleCollection");
        Stock instance = new Stock();
        Collection expResult = null;
        Collection result = instance.getArticleCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArticleCollection method, of class Stock.
     */
    @Test
    public void testSetArticleCollection() {
        System.out.println("setArticleCollection");
        Collection<Article> articleCollection = null;
        Stock instance = new Stock();
        instance.setArticleCollection(articleCollection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Stock.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Stock instance = new Stock();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Stock.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Stock instance = new Stock();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Stock.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Stock instance = new Stock();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModificationDate method, of class Stock.
     */
    @Test
    public void testGetModificationDate() {
        System.out.println("getModificationDate");
        Stock instance = new Stock();
        Date expResult = null;
        Date result = instance.getModificationDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModificationDate method, of class Stock.
     */
    @Test
    public void testSetModificationDate() {
        System.out.println("setModificationDate");
        Date modificationDate = null;
        Stock instance = new Stock();
        instance.setModificationDate(modificationDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
