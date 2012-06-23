/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.encryption;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Matze
 */
public class ShaEncrypter 
{
    private String mode;
        
    public ShaEncrypter (EncMode encMode)
    {
        switch (encMode)
        {
            case SHA256:
                mode = "SHA-256";
                break;
            case SHA512:
                mode = "SHA-512";
                break;
            default:
                mode = "SHA-256";
                break;
        }
    }
    
    public String encryptString(String string) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance(mode);
        md.update(string.getBytes());
 
        byte byteData[] = md.digest();
 
        //convert the byte to hex format method 1
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) 
        {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    
    public String encryptFile(String path) throws NoSuchAlgorithmException, FileNotFoundException, IOException
    {
        MessageDigest md = MessageDigest.getInstance(mode);
        
        FileInputStream fis = new FileInputStream(path);
 
        byte[] dataBytes = new byte[1024];
 
        int nread = 0; 
        while ((nread = fis.read(dataBytes)) != -1) 
        {
            md.update(dataBytes, 0, nread);
        }
        
        byte[] mdbytes = md.digest();
 
        //convert the byte to hex format method 1
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mdbytes.length; i++)
        {
            sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    
    public boolean checkStringMatch(String string, String hash) throws NoSuchAlgorithmException
    {
        String compare = encryptString(string);
        
        return hash.equals(compare);
    }
    
    public boolean checkFileMatch(String path, String hash) throws NoSuchAlgorithmException, FileNotFoundException, IOException
    {
        String compare = encryptFile(path);
        
        return hash.equals(compare);
    }
}
