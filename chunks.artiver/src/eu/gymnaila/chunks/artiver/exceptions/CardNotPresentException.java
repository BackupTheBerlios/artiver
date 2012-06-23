/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.exceptions;

/**
 *
 * @author User
 */
public class CardNotPresentException extends Exception
{

    public CardNotPresentException(String message)
    {
        super(message);
    }

    public CardNotPresentException()
    {
        super();
    }
}
