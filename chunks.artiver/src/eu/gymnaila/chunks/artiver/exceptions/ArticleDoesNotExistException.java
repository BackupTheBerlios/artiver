/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.exceptions;

/**
 *
 * @author Philipp
 */
public class ArticleDoesNotExistException extends Exception 
{
    
    public ArticleDoesNotExistException(String message)
    {
       super(message);
    }

    public ArticleDoesNotExistException()
    {
       super();
    }
    
}

