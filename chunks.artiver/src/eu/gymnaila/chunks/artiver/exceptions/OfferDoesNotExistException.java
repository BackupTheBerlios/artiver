/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.exceptions;

/**
 *
 * @author privat
 */
public class OfferDoesNotExistException extends Exception
{
    public OfferDoesNotExistException(String message)
    {
       super(message);
    }

    public OfferDoesNotExistException()
    {
       super();
    }
    
}
