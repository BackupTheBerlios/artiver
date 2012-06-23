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
public class ArticleTest {
    
    public ArticleTest() {
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
     * Test of getIdArticle method, of class Article.
     */
    @Test
    public void testGetIdArticle() {
        System.out.println("getIdArticle");
        Article instance = new Article();
        Integer expResult = null;
        Integer result = instance.getIdArticle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIdArticle method, of class Article.
     */
    @Test
    public void testSetIdArticle() {
        System.out.println("setIdArticle");
        Integer idArticle = null;
        Article instance = new Article();
        instance.setIdArticle(idArticle);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Article.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Article instance = new Article();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class Article.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        Article instance = new Article();
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNr method, of class Article.
     */
    @Test
    public void testGetNr() {
        System.out.println("getNr");
        Article instance = new Article();
        String expResult = "";
        String result = instance.getNr();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNr method, of class Article.
     */
    @Test
    public void testSetNr() {
        System.out.println("setNr");
        String nr = "";
        Article instance = new Article();
        instance.setNr(nr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrice method, of class Article.
     */
    @Test
    public void testGetPrice() {
        System.out.println("getPrice");
        Article instance = new Article();
        double expResult = 0.0;
        double result = instance.getPrice();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPrice method, of class Article.
     */
    @Test
    public void testSetPrice() {
        System.out.println("setPrice");
        double price = 0.0;
        Article instance = new Article();
        instance.setPrice(price);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEan method, of class Article.
     */
    @Test
    public void testGetEan() {
        System.out.println("getEan");
        Article instance = new Article();
        String expResult = "";
        String result = instance.getEan();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEan method, of class Article.
     */
    @Test
    public void testSetEan() {
        System.out.println("setEan");
        String ean = "";
        Article instance = new Article();
        instance.setEan(ean);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAmount method, of class Article.
     */
    @Test
    public void testGetAmount() {
        System.out.println("getAmount");
        Article instance = new Article();
        int expResult = 0;
        int result = instance.getAmount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAmount method, of class Article.
     */
    @Test
    public void testSetAmount() {
        System.out.println("setAmount");
        int amount = 0;
        Article instance = new Article();
        instance.setAmount(amount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColourCode method, of class Article.
     */
    @Test
    public void testGetColourCode() {
        System.out.println("getColourCode");
        Article instance = new Article();
        String expResult = "";
        String result = instance.getColourCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setColourCode method, of class Article.
     */
    @Test
    public void testSetColourCode() {
        System.out.println("setColourCode");
        String colourCode = "";
        Article instance = new Article();
        instance.setColourCode(colourCode);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImg method, of class Article.
     */
    @Test
    public void testGetImg() {
        System.out.println("getImg");
        Article instance = new Article();
        byte[] expResult = null;
        byte[] result = instance.getImg();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setImg method, of class Article.
     */
    @Test
    public void testSetImg() {
        System.out.println("setImg");
        byte[] img = null;
        Article instance = new Article();
        instance.setImg(img);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDescription method, of class Article.
     */
    @Test
    public void testGetDescription() {
        System.out.println("getDescription");
        Article instance = new Article();
        String expResult = "";
        String result = instance.getDescription();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDescription method, of class Article.
     */
    @Test
    public void testSetDescription() {
        System.out.println("setDescription");
        String description = "";
        Article instance = new Article();
        instance.setDescription(description);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModifier method, of class Article.
     */
    @Test
    public void testGetModifier() {
        System.out.println("getModifier");
        Article instance = new Article();
        String expResult = "";
        String result = instance.getModifier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModifier method, of class Article.
     */
    @Test
    public void testSetModifier() {
        System.out.println("setModifier");
        String modifier = "";
        Article instance = new Article();
        instance.setModifier(modifier);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModificationDate method, of class Article.
     */
    @Test
    public void testGetModificationDate() {
        System.out.println("getModificationDate");
        Article instance = new Article();
        Date expResult = null;
        Date result = instance.getModificationDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModificationDate method, of class Article.
     */
    @Test
    public void testSetModificationDate() {
        System.out.println("setModificationDate");
        Date modificationDate = null;
        Article instance = new Article();
        instance.setModificationDate(modificationDate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStock method, of class Article.
     */
    @Test
    public void testGetStock() {
        System.out.println("getStock");
        Article instance = new Article();
        Stock expResult = null;
        Stock result = instance.getStock();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStock method, of class Article.
     */
    @Test
    public void testSetStock() {
        System.out.println("setStock");
        Stock stock = null;
        Article instance = new Article();
        instance.setStock(stock);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getState method, of class Article.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        Article instance = new Article();
        State expResult = null;
        State result = instance.getState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setState method, of class Article.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        State state = null;
        Article instance = new Article();
        instance.setState(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCategory method, of class Article.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        Article instance = new Article();
        Category expResult = null;
        Category result = instance.getCategory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCategory method, of class Article.
     */
    @Test
    public void testSetCategory() {
        System.out.println("setCategory");
        Category category = null;
        Article instance = new Article();
        instance.setCategory(category);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class Article.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Article instance = new Article();
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class Article.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object object = null;
        Article instance = new Article();
        boolean expResult = false;
        boolean result = instance.equals(object);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Article.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Article instance = new Article();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
