/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.exceptions;

/**
 *
 * @author Philipp
 */
public class StockConnectedWithArticlesException extends Exception
{
    public StockConnectedWithArticlesException(String message)
    {
      super(message);
    }
    public StockConnectedWithArticlesException()
    {
      super();  
    }
}
