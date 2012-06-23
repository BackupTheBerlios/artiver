/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.exceptions;

/**
 *
 * @author S1
 */
public class ArticleAlreadyExistsException extends Exception 
{

    public ArticleAlreadyExistsException(String message)
    {
        super(message);
    }

    public ArticleAlreadyExistsException()
    {
        super();
    }

}
