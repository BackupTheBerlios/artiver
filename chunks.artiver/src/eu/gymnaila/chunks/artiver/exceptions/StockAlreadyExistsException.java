/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.exceptions;

/**
 *
 * @author Philipp
 */
public class StockAlreadyExistsException extends Exception
{
     public StockAlreadyExistsException(String message)
    {
        super(message);
    }

    public StockAlreadyExistsException()
    {
        super();
    }   
}
