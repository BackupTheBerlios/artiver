/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.encryption;

import eu.gymnaila.chunks.artiver.config.AppConfig;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class ShaEncrypterTest {
    
    public ShaEncrypterTest() {
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
     * Test of encryptString method, of class ShaEncrypter.
     */
    @Test
    public void testEncryptString() throws Exception {
        System.out.println("encryptString");
        String string = "Hallo an alle, die das hier lesen!";
        String string2 = "hallo an alle, die das hier lesen!";
        String string3 = "Halloan alle, die das hier lesen!";
        
        ShaEncrypter instance256 = new ShaEncrypter(EncMode.SHA256);
        ShaEncrypter instance512 = new ShaEncrypter(EncMode.SHA512);
        
        String exp256Result = "1587670460018e5576844c3987d36a318a918bc1bcdee74307c45b9048da7d9e";
        String exp256Result2 = "f981cfcc1e37db685ad9f160ca76d87a4854f730b539f834d2aac1a2fd46171f";
        String exp256Result3 = "756ec790cfcb6c4de6cdcc758e58ee0affb326194d66cac45a4618042d3014ec";
        
        String exp512Result = "4b5b3c4a315b8fb02e7608071cd280c6898998004fedb1cfdcbca1afd4b21dd1cc17c3d9ea12d078cda3b05a182a0353ec8731800da5518a528c6aa4c1c1301a";
        String exp512Result2 = "9d51a7f2b019be8e031e53527e279fbeb5602f57b37c6a5d4beb329c09f25898108af763f976ebbb09b51de88d9b97370207799d40892e1de28039268b4a9754";
        String exp512Result3 = "f739c967ca634983fa4344ad74190fa2c5c0d60b94b89716b8c13e8f5b72e2796ab9fae32041e8bc35474c87211dd23236dc5b5311bd18b9edacfe0706f9b9f9";
        
        
        string = instance256.encryptString(string);
        string2 = instance256.encryptString(string2);
        string3 = instance256.encryptString(string3);
        
        assertEquals(string, exp256Result);
        assertEquals(string2, exp256Result2);
        assertEquals(string3, exp256Result3);
        
        assertFalse(string.equals(exp256Result2));
        assertFalse(string2.equals(exp256Result3));
        assertFalse(string3.equals(exp256Result));
        
        assertFalse(string.equals(exp256Result3));
        assertFalse(string2.equals(exp256Result));
        assertFalse(string3.equals(exp256Result2));
        
        string = "Hallo an alle, die das hier lesen!";
        string2 = "hallo an alle, die das hier lesen!";
        string3 = "Halloan alle, die das hier lesen!";
        
        string = instance512.encryptString(string);
        string2 = instance512.encryptString(string2);
        string3 = instance512.encryptString(string3);
        
             
        assertEquals(string, exp512Result);
        assertEquals(string2, exp512Result2);
        assertEquals(string3, exp512Result3);
        
        assertFalse(string.equals(exp512Result2));
        assertFalse(string2.equals(exp512Result3));
        assertFalse(string3.equals(exp512Result));
        
        assertFalse(string.equals(exp512Result3));
        assertFalse(string2.equals(exp512Result));
        assertFalse(string3.equals(exp512Result2));
        
    }

    /**
     * Test of encryptFile method, of class ShaEncrypter.
     */
    @Test
    public void testEncryptFile() throws Exception {
        System.out.println("encryptFile");
        String path = AppConfig.class.getResource("AppConfig.class").getPath();
        ShaEncrypter instance256 = new ShaEncrypter(EncMode.SHA256);
        ShaEncrypter instance512 = new ShaEncrypter(EncMode.SHA512);
        
        String expResult256 = "10a8fc9d00bb86ea384cd123ec7adb1a45d07522a6f7b8713bb226d2565328e0";
        String expResult512 = "5d5fc235f449f2d6aa523ce33ef01939ee15e0874b6d1bce21dcf512d287f8be856a888bdb85a93c7319f9cad790c17f1bc00440cc080bd2e5217ea68a2d152c";
        
        String result256 = instance256.encryptFile(path);
        String result512 = instance512.encryptFile(path);
  
        assertEquals(expResult256, result256);
        assertEquals(expResult512, result512);

    }

    /**
     * Test of checkStringMatch method, of class ShaEncrypter.
     */
    @Test
    public void testCheckStringMatch() throws Exception {
        System.out.println("checkStringMatch");

        String string = "Hallo an alle, die das hier lesen!";

        ShaEncrypter instance256 = new ShaEncrypter(EncMode.SHA256);
        ShaEncrypter instance512 = new ShaEncrypter(EncMode.SHA512);
        
        String exp256Result = "1587670460018e5576844c3987d36a318a918bc1bcdee74307c45b9048da7d9e";

        String exp512Result = "4b5b3c4a315b8fb02e7608071cd280c6898998004fedb1cfdcbca1afd4b21dd1cc17c3d9ea12d078cda3b05a182a0353ec8731800da5518a528c6aa4c1c1301a";

        boolean expResult = true;
        boolean result256 = instance256.checkStringMatch(string, exp256Result);
        boolean result512 = instance512.checkStringMatch(string, exp512Result);   
       
        assertEquals(expResult, result256);
        assertEquals(expResult, result512);
        
    }

    /**
     * Test of checkFileMatch method, of class ShaEncrypter.
     */
    @Test
    public void testCheckFileMatch() throws Exception {
        System.out.println("checkFileMatch");
        
        String path = AppConfig.class.getResource("AppConfig.class").getPath();
        ShaEncrypter instance256 = new ShaEncrypter(EncMode.SHA256);
        ShaEncrypter instance512 = new ShaEncrypter(EncMode.SHA512);
        
        String expResult256 = "10a8fc9d00bb86ea384cd123ec7adb1a45d07522a6f7b8713bb226d2565328e0";
        String expResult512 = "5d5fc235f449f2d6aa523ce33ef01939ee15e0874b6d1bce21dcf512d287f8be856a888bdb85a93c7319f9cad790c17f1bc00440cc080bd2e5217ea68a2d152c";
        
        boolean result256 = instance256.checkFileMatch(path, expResult256);
        boolean result512 = instance512.checkFileMatch(path, expResult512);

        boolean expResult = true;
        
        assertEquals(expResult, result256);
        
        assertEquals(expResult, result512);

    }
}
