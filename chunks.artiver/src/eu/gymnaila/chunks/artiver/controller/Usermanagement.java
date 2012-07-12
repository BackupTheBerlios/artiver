
package eu.gymnaila.chunks.artiver.controller;
import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.entity.User;
import eu.gymnaila.chunks.artiver.entity.Groups;
import eu.gymnaila.chunks.artiver.exceptions.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.NoResultException;

/**
 * @author Fat Don
 */

public class Usermanagement 
{
  
  /**
   * this method returns a List-Object contained with all users
   * @return List of all Users
   */
  public List<User> list()
  {
      return update();  
  }
  
  
  /**
   * 
   * @param user user to edit
   * @throws UserNotFoundException thrown if user's id is not found in DB
   */
  public void edit(User user) throws UserNotFoundException
  {
      List<User> userList = update();
      
      if(!userExists(user.getIdUser()))
      {
          throw new UserNotFoundException("User with the ID + " + user.getIdUser() + " does not exist.");
      }
      
      int index = 0;
            
      for (User curUser : userList) 
      {
        if (curUser.getIdUser().equals(user.getIdUser())) 
        {
             index = userList.indexOf(curUser);
             break;
        }
      }

      userList.set(index, user);  
      persist(userList);
      
     
  }
  
  
    /**
     * this method allows you to set the Group of a user by his ID
     * @param userID User's unique ID
     * @param grp Group to set for the User
     * @throws UserNotFoundException
     */
  public void setGroup(int userID, Groups grp) throws UserNotFoundException
  {
          List<User> userList = update();
          
          if(!userExists(userID))
          {
              throw new UserNotFoundException("User with ID '" + userID + "' does not exist.");   
          }
            
          for(User user : userList)
          {
              if(user.getIdUser() == userID)
              {
                  user.setGroups(grp);
              }
          }
          
          persist(userList);
  }
  
  
  /**
   * this method returns the group of a named user from the userlist
   * @return Group of a user as String
   * @throws UserNotFoundException
   */
  public String getGroup(int userID) throws UserNotFoundException
  {
      
      List<User> userList = update();
      
      String curGrp = "default Group";
      
          if(!userExists(userID))
          {
              throw new UserNotFoundException("User with ID '" + userID + "' does not exist."); 
          }
            
          for(User user : userList)
          {
              if(user.getIdUser() == userID)
              {
                 curGrp = user.getGroups().toString();
              }
          }
    
      return curGrp;
  }
  
  

  
  /**
   * this method adds a new user-object to the list with all its data
   * @throws UserAlreadyExistsException
   */
  
  public void addUser(String userName, Groups grp, String pw, String uid, int persoNr) throws UserAlreadyExistsException
  {
      
     List<User> userList = update();
     
     if(userExists(userName))
     {
         throw new UserAlreadyExistsException("This user does already exist."); 
     }
     
     int newID = -1;
     
     for(User user : userList)
     {
         if(user.getIdUser() > newID)
         {
             newID = user.getIdUser();
         }
     }
     
     newID++;
     User newUser = new User(newID, userName, persoNr, pw);
     newUser.setGroups(grp);
     newUser.setUid(uid);
     userList.add(newUser);
     persist(userList);
  }


  /**
   * this method removes an existing user from the userlist
   * @throws UserNotFoundException
   */
  public void deleteUser(int userID) throws UserNotFoundException
  {
          List<User> userList = update();
              
          if(!userExists(userID))
          {
              throw new UserNotFoundException("User with ID '" + userID + "' does not exist.");
          }
          
          for(User user : userList)
          {
              if(user.getIdUser() == userID)
              {
                  dbDelete(user);
                  break;
              }
          }
          
  }
  
  /**
   * this method checks whether a named password equals to the named user's id
   * @param pw User's password
   */
  
  public boolean checkAccPW(String userName, String pw) throws UserNotFoundException
  {   
      EntityManager em = AppConfig.createEntityManager();
      
      Query userQuery = em.createNamedQuery("User.findByName");
      userQuery.setParameter("name", userName);
      
     try
        {
            // Try to get exactly one user from the DB, with exactly that card's uid
            User user = (User) userQuery.getSingleResult();
            // TODO Intelligenz (schaut auch mal die anderen Controller an), Prüfen AccPw             
            return true;
        }
        catch (NoResultException e)
        {
            return false;
        }
     
        finally
        {
            // Remember: Do EVER close the EntityManager you created!!
            em.close();
        }
  }
  
  
  /**
   * this method is used in order to check whether a user
   * with the named ID exists
   */
  private boolean userExists(int userID)
  {
      EntityManager em = AppConfig.createEntityManager();

      Query userQuery = em.createNamedQuery("User.findByIdUser");
      userQuery.setParameter("idUser", userID);
      
     try
        {
            // Try to get exactly one user from the DB, with exactly that card's uid
            User user = (User) userQuery.getSingleResult();
                                  
            return true;
        }
        catch (NoResultException e)
        {
            return false;
        }
        finally
        {
            // Remember: Do EVER close the EntityManager you created!!
            em.close();
        }
  }
  
    /**
   * this method is used to check whether a user exists by his name
   */
  private boolean userExists(String userName)
  {
      EntityManager em = AppConfig.createEntityManager();

      Query userQuery = em.createNamedQuery("User.findByName");
      userQuery.setParameter("name", userName);
      
     try
        {
            // Try to get exactly one user from the DB, with exactly that card's uid
            User user = (User) userQuery.getSingleResult();
                                  
            return true;
        }
     
        catch (NoResultException e)
        {
            return false;
        }
     
        finally
        {
            // Remember: Do EVER close the EntityManager you created!!
            em.close();
        }
  }

  /**
   * this method sets the userlist equal to user data from the data base
   */
  private List<User> update()
  {
        EntityManager em = AppConfig.createEntityManager();

        Query userQuery = em.createNamedQuery("User.findAll");
        List<User> userList = userQuery.getResultList();
        em.close();
        return userList;
  }
  
  /**
   * this method sets data from the data base equal to the userlist
   */
   private void persist(List<User> userList)
   {     
       EntityManager em = AppConfig.createEntityManager();
        for (User user : userList) 
        {
		//System.out.println(user);
                em.getTransaction().begin();
                em.merge(user);
                em.getTransaction().commit();
	}
      //  System.out.println("Size: " + userList.size());
        em.close();
        
    }
   private void dbDelete(User user)
    {
            EntityManager em = AppConfig.createEntityManager();
            em.getTransaction().begin();
            user = em.merge(user);
            
            em.remove(user);
            em.flush();
            em.getTransaction().commit();
    }
   
}
