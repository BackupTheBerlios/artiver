/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.config;

import eu.gymnaila.chunks.artiver.entity.User;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class AppConfigTest {
    
    public AppConfigTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        AppConfig.init();
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
     * Test of init method, of class AppConfig.
     */
    @Test
    public void testInit() throws Exception {
        System.out.println("init");
        AppConfig.init();
        // TODO review the generated test code and remove the default call to fail.
       assertNotNull(AppConfig.PERSISTENCE_UNIT_NAME);
       assertNotNull(AppConfig.isDebug());
       System.out.println(AppConfig.isDebug());
    }

    /**
     * Test of createEntityManager method, of class AppConfig.
     */
    @Test
    public void testCreateEntityManager() {
        System.out.println("createEntityManager");

        EntityManagerFactory factory;

        // Map (Key, Value) for EntityManager configuration 
        Map persistProps = new HashMap();

        persistProps.put("javax.persistence.jdbc.driver","com.mysql.jdbc.Driver");
        persistProps.put("javax.persistence.jdbc.url", "jdbc:mysql://localhost:3306/test");
        persistProps.put("javax.persistence.jdbc.user", "dennis");
        persistProps.put("javax.persistence.jdbc.password", "0wn3d_y0u");


        // With persistProps readily configured factory
        factory = Persistence.createEntityManagerFactory(AppConfig.PERSISTENCE_UNIT_NAME, persistProps);

        EntityManager expResult = factory.createEntityManager();

        EntityManager result = AppConfig.createEntityManager();
        assertEquals(expResult.getEntityManagerFactory().getProperties(), result.getEntityManagerFactory().getProperties());
    }

    /**
     * Test of getUser method, of class AppConfig.
     */
    @Test
    public void testGetUser() {
        System.out.println("getUser");
        User expResult = new User(1, "hofmmat", 1093, "owned_you");
        User result = AppConfig.getUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of isDebug method, of class AppConfig.
     */
    @Test
    public void testIsDebug() {
        System.out.println("isDebug");
        boolean expResult = false;
        boolean result = AppConfig.isDebug();
        assertEquals(expResult, result);
    }

    /**
     * Test of setCurPassword method, of class AppConfig.
     */
    @Test
    public void testSetCurPassword() {
        System.out.println("setCurPassword");
        String pass = "testPass";
        AppConfig.setCurPassword(pass);
    }

    /**
     * Test of setUser method, of class AppConfig.
     */
    @Test
    public void testSetUser() throws Exception {
        System.out.println("setUser");
        User user = new User(1, "Matze H", 1, "123");
        if(AppConfig.getUser() == null)
        {
            AppConfig.setUser(user);   
        }
        assertEquals(user, AppConfig.getUser());
    }
}
