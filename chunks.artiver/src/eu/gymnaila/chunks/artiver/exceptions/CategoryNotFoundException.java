/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.exceptions;

/**
 *
 * @author S1
 */
public class CategoryNotFoundException extends Exception
{
    public CategoryNotFoundException(String message)
    {
      super(message);
    }
    
    public CategoryNotFoundException()
    {
        super();
    }
            
}
