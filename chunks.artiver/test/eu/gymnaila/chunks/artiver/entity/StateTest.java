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
public class StateTest {
    
    public StateTest() {
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
     * Test of getIdState method, of class State.
     */
    @Test
    public void testGetIdState() {
        System.out.println("getIdState");
        State instance = new State();
        Integer expResult = null;
        Integer result = instance.getIdState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdState method, of class State.
     */
    @Test
    public void testSetIdState() {
        System.out.println("setIdState");
        Integer idState = null;
        State instance = new State();
        instance.setIdState(idState);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class State.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        State instance = new State();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class State.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        State instance = new State();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class State.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        State instance = new State();
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class State.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "";
        State instance = new State();
        instance.setDescription(description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModifier method, of class State.
     */
    @Test
    public void testGetModifier() {
        System.out.println("getModifier");
        State instance = new State();
        String expResult = "";
        String result = instance.getModifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModifier method, of class State.
     */
    @Test
    public void testSetModifier() {
        System.out.println("setModifier");
        String modifier = "";
        State instance = new State();
        instance.setModifier(modifier);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getArticleCollection method, of class State.
     */
    @Test
    public void testGetArticleCollection() {
        System.out.println("getArticleCollection");
        State instance = new State();
        Collection expResult = null;
        Collection result = instance.getArticleCollection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setArticleCollection method, of class State.
     */
    @Test
    public void testSetArticleCollection() {
        System.out.println("setArticleCollection");
        Collection<Article> articleCollection = null;
        State instance = new State();
        instance.setArticleCollection(articleCollection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class State.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        State instance = new State();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class State.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        State instance = new State();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class State.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        State instance = new State();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModificationDate method, of class State.
     */
    @Test
    public void testGetModificationDate() {
        System.out.println("getModificationDate");
        State instance = new State();
        Date expResult = null;
        Date result = instance.getModificationDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModificationDate method, of class State.
     */
    @Test
    public void testSetModificationDate() {
        System.out.println("setModificationDate");
        Date modificationDate = null;
        State instance = new State();
        instance.setModificationDate(modificationDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
