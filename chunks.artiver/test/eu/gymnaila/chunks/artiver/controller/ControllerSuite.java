/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.controller;

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
@Suite.SuiteClasses({eu.gymnaila.chunks.artiver.controller.UsermanagementTest.class, eu.gymnaila.chunks.artiver.controller.RFIDLoginControllerTest.class, eu.gymnaila.chunks.artiver.controller.GroupsControllerTest.class, eu.gymnaila.chunks.artiver.controller.CustomerControllerTest.class, eu.gymnaila.chunks.artiver.controller.ArticlemanagementTest.class, eu.gymnaila.chunks.artiver.controller.StockControllerTest.class})
public class ControllerSuite {

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
