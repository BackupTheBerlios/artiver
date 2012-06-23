/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.entity;

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
@Suite.SuiteClasses({eu.gymnaila.chunks.artiver.entity.UserTest.class, eu.gymnaila.chunks.artiver.entity.GroupsTest.class, eu.gymnaila.chunks.artiver.entity.CategoryTest.class, eu.gymnaila.chunks.artiver.entity.CustomerTest.class, eu.gymnaila.chunks.artiver.entity.ArticleTest.class, eu.gymnaila.chunks.artiver.entity.StateTest.class, eu.gymnaila.chunks.artiver.entity.StockTest.class})
public class EntitySuite {

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
