/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.tooling;

/**
 *
 * @author User
 */
public class Numbers 
{
    
    /**********     Double Section     **********/
    
    public static boolean isDouble(String toCheck)
    {
        int commaCtr = 0;
        for (int i = 0; i < toCheck.length(); i++)
        {
            //If we find a non-digit character we return false.
            if (!Character.isDigit(toCheck.charAt(i))) 
            {
                if(toCheck.charAt(i) != ',' && toCheck.charAt(i) != '.')
                {
                    return false;
                }
                else
                {
                    commaCtr ++;
                }
            }
        }
        
        if(commaCtr > 1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public static double parseDouble(String toParse) throws NumberFormatException
    {
        if(isDouble(toParse))
        {
            toParse = toParse.replace(',', '.');
            
            return Double.parseDouble(toParse);            
        }
        else
        {
            throw new NumberFormatException();
        }
    }
    
    
    /**********     Integer Section     **********/
    
    public static boolean isInt(String toCheck)
    {
        for (int i = 0; i < toCheck.length(); i++)
        {
            //If we find a non-digit character we return false.
            if (!Character.isDigit(toCheck.charAt(i))) 
            {
                return false;
            }
        }
        return true;
    }
    
    public static int parseInt(String toParse) throws NumberFormatException
    {
        if(isInt(toParse))
        {            
            return Integer.parseInt(toParse);            
        }
        else
        {
            throw new NumberFormatException();
        }
    }

    public static String toString(double dbl) 
    {
        return Double.toString(dbl).replace('.', ',');
    }
    
    public static String toString(int integer) 
    {
        return Integer.toString(integer);
    }
}
