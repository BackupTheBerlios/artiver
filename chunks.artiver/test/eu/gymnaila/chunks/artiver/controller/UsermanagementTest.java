/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.controller;

import eu.gymnaila.chunks.artiver.entity.Groups;
import java.util.List;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class UsermanagementTest {
    
    public UsermanagementTest() {
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
     * Test of list method, of class Usermanagement.
     */
    @Test
    public void testList() {
        System.out.println("list");
        Usermanagement instance = new Usermanagement();
        List expResult = null;
        List result = instance.list();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGroup method, of class Usermanagement.
     */
    @Test
    public void testSetGroup() throws Exception {
        System.out.println("setGroup");
        int userID = 0;
        Groups grp = null;
        Usermanagement instance = new Usermanagement();
        instance.setGroup(userID, grp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroup method, of class Usermanagement.
     */
    @Test
    public void testGetGroup() throws Exception {
        System.out.println("getGroup");
        int userID = 0;
        Usermanagement instance = new Usermanagement();
        String expResult = "";
        String result = instance.getGroup(userID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addUser method, of class Usermanagement.
     */
    @Test
    public void testAddUser() throws Exception {
        System.out.println("addUser");
        String userName = "";
        Groups grp = null;
        String pw = "";
        int userID = 0;
        int persoNr = 0;
        Usermanagement instance = new Usermanagement();
        instance.addUser(userName, grp, pw, userID, persoNr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteUser method, of class Usermanagement.
     */
    @Test
    public void testDeleteUser() throws Exception {
        System.out.println("deleteUser");
        int userID = 0;
        Usermanagement instance = new Usermanagement();
        instance.deleteUser(userID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkAccPW method, of class Usermanagement.
     */
    @Test
    public void testCheckAccPW() throws Exception {
        System.out.println("checkAccPW");
        String userID = "";
        String pw = "";
        Usermanagement instance = new Usermanagement();
        boolean expResult = false;
        boolean result = instance.checkAccPW(userID, pw);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
