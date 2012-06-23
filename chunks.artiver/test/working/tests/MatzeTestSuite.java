/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package working.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author User
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({eu.gymnaila.chunks.artiver.controller.RFIDLoginControllerTest.class,eu.gymnaila.chunks.artiver.config.AppConfigTest.class, eu.gymnaila.chunks.artiver.encryption.ShaEncrypterTest.class})
public class MatzeTestSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    
}
