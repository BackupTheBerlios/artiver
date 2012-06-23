/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.controller;

import eu.gymnaila.chunks.artiver.config.AppConfig;
import javax.smartcardio.CardException;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class RFIDLoginControllerTest {
    
    private RFIDLoginController instance;
    
    public RFIDLoginControllerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
        AppConfig.init();
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() 
    {
        instance = new RFIDLoginController();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validateCard method, of class RFIDLoginController.
     */
    @Test
    public void testValidateCard() throws Exception {
        System.out.println("validateCard");
        boolean expResult = true;
        boolean result = instance.validateCard();
        assertEquals(expResult, result);
    }
    
}
