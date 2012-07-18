
package eu.gymnaila.chunks.artiver.controller;

import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.entity.Groups;
import eu.gymnaila.chunks.artiver.exceptions.GroupsAlreadyExistException;
import eu.gymnaila.chunks.artiver.exceptions.GroupsNotFoundException;
import eu.gymnaila.chunks.artiver.exceptions.GroupConnectedToUserException;
import java.io.Serializable;
import java.util.List;
import eu.gymnaila.chunks.artiver.entity.User;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;


/**
 * 
 * @author Don
 */
public class GroupsController implements Serializable
{
  
    /**
     * see return
     * @return returns all Groups as a List<Groups> Object
     */
  public List<Groups> list()
  {
      return update();
  }
  
  
  /**
   * enables to edit a Group
   * @param groups group to edit
   * @throws GroupsNotFoundException thrown, if group's id was not found in DB 
   * 
   */
  public void edit(Groups groups) throws GroupsNotFoundException
  {
      List<Groups> groupsList = update();
      
      if(!groupsExist(groups.getIdGroups()))
      {
          throw new GroupsNotFoundException("Groups with the ID + " + groups.getIdGroups() + " does not exist.");
      }
      
      int index = 0;
            
      for (Groups curGroups : groupsList) 
      {
        if (curGroups.getIdGroups().equals(groups.getIdGroups())) 
        {
             index = groupsList.indexOf(curGroups);
             break;
        }
      }

      groupsList.set(index, groups);  
      persist(groupsList);
      
     
  }
  
  
  /**
   * enables to add a group into the DB
   * @param group group to add
   * @throws GroupsAlreadyExistException  thrown, if id was not found in db
   */
  public void addGroups(Groups group) throws GroupsAlreadyExistException
  {
      
     List<Groups> groupsList = update();
     
     
     int newID = -1;
     
     for(Groups tempGroup : groupsList)
     {
         if(tempGroup.getIdGroups() > newID)
         {
             newID = tempGroup.getIdGroups();
         }
     }
     
     newID++;
     
     group.setIdGroups(newID);
     
     if(groupsExist(group.getIdGroups()))
     {
         throw new GroupsAlreadyExistException("This Group does already exist."); 
     }
     
     groupsList.add(group);
     
     persist(groupsList);
  }
  
  /**
   * enables to delete a group
   * @param group group to delete
   * @throws GroupsNotFoundException thrown, if group's id was not found in db
   * @throws GroupConnectedToUserException thrown, if a user is assigned to the group to delete
   */
  public void deleteGroups(Groups group) throws GroupsNotFoundException, GroupConnectedToUserException
  {
     List<Groups> groupsList = update();
     
     Usermanagement usm = new Usermanagement();
     List<User> users = usm.list(); 
     int groupsID = group.getIdGroups();
     
     for(User user : users)
     {
         if(user.getGroups().getIdGroups() == groupsID)
         {
             throw new GroupConnectedToUserException("Cannot delete Group: " + user.getName() + "connected to this Group");
         }
     }
     
              
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                  dbDelete(groups);
                  return;
              }
          }
          
          
      throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found."); 
  }
 
  /**
   * see return
   * @param groupsID PK
   * @return returns group's name
   * @throws GroupsNotFoundException  thrown, if id was not found in db
   */
  public String getName(int groupsID) throws GroupsNotFoundException
  {
      
     List<Groups> groupsList = update();
      
      String curName = "default name";
      
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found."); 
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                 curName = groups.getName();
              }
          }
    
      return curName;
  }
  
  /**
   * enables to set a group's name
   * @param groupsID PK
   * @param name name to set
   * @throws GroupsNotFoundException  thrown, if id was not found in db
   */
  public void setName(int groupsID, String name) throws GroupsNotFoundException
  {
     List<Groups> groupsList = update();
          
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found.");   
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                  groups.setName(name);
              }
          }
          
          persist(groupsList);
  }  
  
  
  /**
   * check whether the right to check in is given
   * @param groupsID PK in DB to search for
   * @return true, if right is given
   * @throws GroupsNotFoundException thrown, if argument (id) does not exist
   */
  public boolean getCheckIn(int groupsID) throws GroupsNotFoundException
  {
      
     List<Groups> groupsList = update();
      
      boolean curCheckIn = false;
      
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found."); 
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                 curCheckIn = groups.getCheckIn();
              }
          }
    
      return curCheckIn;
  }
  
  /**
   * enables the right to checkin
   * @param groupsID PK 
   * @param checkIn true, if allowed to check in
   * @throws GroupsNotFoundException thrown, if id was not found in db 
   */
  public void setCheckIn(int groupsID, boolean checkIn) throws GroupsNotFoundException
  {
     List<Groups> groupsList = update();
          
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found.");   
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                  groups.setCheckIn(checkIn);
              }
          }
          
          persist(groupsList);
  }
  
  /**
   * enables the right to check out
   * @param groupsID PK in DB 
   * @param checkOut true, if allowed to check out
   * @throws GroupsNotFoundException thrown, if group's id was not found in db
   */
  public void setCheckOut(int groupsID, boolean checkOut) throws GroupsNotFoundException
  {
     List<Groups> groupsList = update();
          
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found.");   
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                  groups.setCheckOut(checkOut);
              }
          }
          
          persist(groupsList);
  }  
  
  
  /**
   * check whether the right to check out is given
   * @param groupsID PK in DB
   * @return true, if the right is given 
   * @throws GroupsNotFoundException thrown, if id does not exist
   */
  public boolean getCheckOut(int groupsID) throws GroupsNotFoundException
  {
      
     List<Groups> groupsList = update();
      
      boolean curCheckOut = false;
      
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found."); 
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                 curCheckOut = groups.getCheckOut();
              }
          }
    
      return curCheckOut;
  }  
  
  /**
   * checks, whether the right to edit an article is given
   * @param groupsID ok in db to search for
   * @return true, if the right to edit an article is given
   * @throws GroupsNotFoundException thrown, if id does not exist
   */
  
  public boolean getArticle(int groupsID) throws GroupsNotFoundException
  {
      
     List<Groups> groupsList = update();
      
      boolean curArticle = false;
      
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found."); 
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                 curArticle = groups.getArticle();
              }
          }
    
      return curArticle;
  } 
  
  
  /**
   * enables to set the right to edit an article
   * @param groupsID PK in DB
   * @param article true, if allowed to edit
   * @throws GroupsNotFoundException 
   */
  public void setArticle(int groupsID, boolean article) throws GroupsNotFoundException
  {
     List<Groups> groupsList = update();
          
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found.");   
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                  groups.setArticle(article);
              }
          }
          
          persist(groupsList);
  }
  
  /**
   * checks, whether the right to edit groups is given
   * @param groupsID PK in DB to search for
   * @return true, if the right to edit is given
   * @throws GroupsNotFoundException  thrown, if id in db was not found
   */
  public boolean getGroups(int groupsID) throws GroupsNotFoundException
  {
      
     List<Groups> groupsList = update();
      
      boolean curNewGroup = false;
      
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found."); 
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                 curNewGroup = groups.getGroups();
              }
          }
    
      return curNewGroup;
  } 
  
  /**
   * enables to set the right to edit groups
   * @param groupsID PK in DB to search for
   * @param group true, if allowed to edit
   * @throws GroupsNotFoundException 
   */
  public void setGroups(int groupsID, boolean group) throws GroupsNotFoundException
  {
     List<Groups> groupsList = update();
          
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found.");   
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                  groups.setGroups(group);
              }
          }
          
          persist(groupsList);
  }  
  
  /**
   * checks, whether the right to edit an user is given
   * @param groupsID PK in DB to search for
   * @return true, if the right is given
   * @throws GroupsNotFoundException thrown, if id was not found
   */
  public boolean getUser(int groupsID) throws GroupsNotFoundException
  {
      
     List<Groups> groupsList = update();
      
      boolean curNewUser = false;
      
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found."); 
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                 curNewUser = groups.getUser();
              }
          }
    
      return curNewUser;
  }   
  
  /**
   * enables to set the right to edit users
   * @param groupsID PK in DB to search for
   * @param user true, if allowed to edit
   * @throws GroupsNotFoundException thrown, if id does not exist
   */
  public void setUser(int groupsID, boolean user) throws GroupsNotFoundException
  {
     List<Groups> groupsList = update();
          
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found.");   
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                  groups.setUser(user);
              }
          }
          
          persist(groupsList);
  }
  
  /**
   * checks, whether the right to edit categories is given
   * @param groupsID PK in DB to search for
   * @return true, if the right is given
   * @throws GroupsNotFoundException thrown, if id does not exist
   */
  public boolean getCategory(int groupsID) throws GroupsNotFoundException
  {
      
     List<Groups> groupsList = update();
      
      boolean curNewCategory = false;
      
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found."); 
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                 curNewCategory = groups.getCategory();
              }
          }
    
      return curNewCategory;
  }

  /**
   * enables to set the right to edit categories
   * @param groupsID PK in DB to search for
   * @param category true, if allowed to edit
   * @throws GroupsNotFoundException thrown, if id was not found
   */
  public void setCategory(int groupsID, boolean category) throws GroupsNotFoundException
  {
     List<Groups> groupsList = update();
          
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found.");   
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                  groups.setCategory(category);
              }
          }
          
          persist(groupsList);
  }  
  
  /**
   * checks, whether the right to edit admin is given
   * @param groupsID PK in DB to search for
   * @return true, if allowed to edit
   * @throws GroupsNotFoundException thrown, if id was not found
   */
  public boolean getAdmin(int groupsID) throws GroupsNotFoundException
  {
      
     List<Groups> groupsList = update();
      
      boolean curAdmin = false;
      
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found."); 
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                 curAdmin = groups.getAdmin();
              }
          }
    
      return curAdmin;
  }
  
  /**
   * enables to set the right to edit admin
   * @param groupsID PK in DB to search for
   * @param admin true, if allowed to edit
   * @throws GroupsNotFoundException thrown, if id was not found
   */
  public void setAdmin(int groupsID, boolean admin) throws GroupsNotFoundException
  {
     List<Groups> groupsList = update();
          
          if(!groupsExist(groupsID))
          {
              throw new GroupsNotFoundException("Group with ID '" + groupsID + "' not found.");   
          }
            
          for(Groups groups : groupsList)
          {
              if(groups.getIdGroups() == groupsID)
              {
                  groups.setAdmin(admin);
              }
          }
          
          persist(groupsList);
  }  
    
  
  private boolean groupsExist(int groupsID)
  {
      EntityManager em = AppConfig.createEntityManager();

      Query groupQuery = em.createNamedQuery("Groups.findByIdGroups");
      groupQuery.setParameter("idGroups", groupsID);
      
     try
        {
            // Try to get exactly one user from the DB, with exactly that card's uid
            Groups group = (Groups) groupQuery.getSingleResult();
                                  
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
  
  
  private boolean groupsExist(String name)
  {
      EntityManager em = AppConfig.createEntityManager();

      Query groupQuery = em.createNamedQuery("Groups.findByName");
      groupQuery.setParameter("name", name);
      
     try
        {
            // Try to get exactly one user from the DB, with exactly that card's uid
            Groups group = (Groups) groupQuery.getSingleResult();
                                  
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
    private List<Groups> update()
    {
        EntityManager em = AppConfig.createEntityManager();

        Query groupQuery = em.createNamedQuery("Groups.findAll");
        List<Groups> groupsList = groupQuery.getResultList();
        em.close();
        return groupsList;
    }
    private void persist(List<Groups> groupsList)
    {
        EntityManager em = AppConfig.createEntityManager();
        for (Groups group : groupsList) 
        {
		System.out.println(group);
                em.getTransaction().begin();
                em.merge(group);
                em.getTransaction().commit();
	}
        System.out.println("Size: " + groupsList.size());
        em.close();
        
    }
    private void dbDelete(Groups groups)
    {
            EntityManager em = AppConfig.createEntityManager();
            em.getTransaction().begin();
            groups = em.merge(groups);
            
            em.remove(groups);
            em.flush();
            em.getTransaction().commit();
    }
    
}

