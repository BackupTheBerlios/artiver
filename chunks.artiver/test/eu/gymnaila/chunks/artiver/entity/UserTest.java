/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.entity;

import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class UserTest {
    
    public UserTest() {
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
     * Test of getIdUser method, of class User.
     */
    @Test
    public void testGetIdUser() {
        System.out.println("getIdUser");
        User instance = new User();
        Integer expResult = null;
        Integer result = instance.getIdUser();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdUser method, of class User.
     */
    @Test
    public void testSetIdUser() {
        System.out.println("setIdUser");
        Integer idUser = null;
        User instance = new User();
        instance.setIdUser(idUser);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class User.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        User instance = new User();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class User.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        User instance = new User();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPersonalNr method, of class User.
     */
    @Test
    public void testGetPersonalNr() {
        System.out.println("getPersonalNr");
        User instance = new User();
        int expResult = 0;
        int result = instance.getPersonalNr();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPersonalNr method, of class User.
     */
    @Test
    public void testSetPersonalNr() {
        System.out.println("setPersonalNr");
        int personalNr = 0;
        User instance = new User();
        instance.setPersonalNr(personalNr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        User instance = new User();
        String expResult = "";
        String result = instance.getPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword method, of class User.
     */
    @Test
    public void testSetPassword() {
        System.out.println("setPassword");
        String password = "";
        User instance = new User();
        instance.setPassword(password);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModifier method, of class User.
     */
    @Test
    public void testGetModifier() {
        System.out.println("getModifier");
        User instance = new User();
        String expResult = "";
        String result = instance.getModifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModifier method, of class User.
     */
    @Test
    public void testSetModifier() {
        System.out.println("setModifier");
        String modifier = "";
        User instance = new User();
        instance.setModifier(modifier);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGroups method, of class User.
     */
    @Test
    public void testGetGroups() {
        System.out.println("getGroups");
        User instance = new User();
        Groups expResult = null;
        Groups result = instance.getGroups();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGroups method, of class User.
     */
    @Test
    public void testSetGroups() {
        System.out.println("setGroups");
        Groups groups = null;
        User instance = new User();
        instance.setGroups(groups);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class User.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        User instance = new User();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class User.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        User instance = new User();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class User.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        User instance = new User();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUid method, of class User.
     */
    @Test
    public void testGetUid() {
        System.out.println("getUid");
        User instance = new User();
        String expResult = "";
        String result = instance.getUid();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUid method, of class User.
     */
    @Test
    public void testSetUid() {
        System.out.println("setUid");
        String uid = "";
        User instance = new User();
        instance.setUid(uid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModificationDate method, of class User.
     */
    @Test
    public void testGetModificationDate() {
        System.out.println("getModificationDate");
        User instance = new User();
        Date expResult = null;
        Date result = instance.getModificationDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModificationDate method, of class User.
     */
    @Test
    public void testSetModificationDate() {
        System.out.println("setModificationDate");
        Date modificationDate = null;
        User instance = new User();
        instance.setModificationDate(modificationDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
