/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.controller;

import eu.gymnaila.chunks.artiver.exceptions.ArticleDoesNotExistException;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.*;

/**
 *
 * @author User
 */
public class ArticlemanagementTest {
    
    public ArticlemanagementTest() {
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
     * Test of list method, of class Articlemanagement.
     */
    @Test
    public void testList() {
        System.out.println("list");
        Articlemanagement instance = new Articlemanagement();
        List expResult = null;
        List result = instance.list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class Articlemanagement.
     */
    @Test
    public void testInsert() throws Exception {
        System.out.println("insert");
        Integer id = null;
        String name = "";
        String nr = "";
        double price = 0.0;
        String EAN = "";
        int amount = 0;
        String colourCode = "";
        int categoryId = 0;
        int stockId = 0;
        int stateId = 0;
        Articlemanagement instance = new Articlemanagement();
        instance.insert(id, name, nr, price, EAN, amount, colourCode, categoryId, stockId, stateId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class Articlemanagement.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        String nr = "";
        Articlemanagement instance = new Articlemanagement();
        instance.delete(nr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIn method, of class Articlemanagement.
     */
    @Test
    public void testCheckIn() throws ArticleDoesNotExistException {
        System.out.println("checkIn");
        String nr = "";
        int amount = 0;
        Articlemanagement instance = new Articlemanagement();
        instance.checkIn(nr, amount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkOut method, of class Articlemanagement.
     */
    @Test
    public void testCheckOut() throws Exception {
        System.out.println("checkOut");
        String nr = "";
        int newAmount = 0;
        Articlemanagement instance = new Articlemanagement();
        instance.checkOut(nr, newAmount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
