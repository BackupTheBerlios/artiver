/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.exceptions;

/**
 *
 * @author S1
 */
public class GroupsAlreadyExistException  extends Exception
{
    
  public GroupsAlreadyExistException(String message)
  {
     super(message);  
  }
  
  public GroupsAlreadyExistException()
  {
      super();
  }
  
}
