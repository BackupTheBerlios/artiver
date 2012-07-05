/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * Handles database-access for Stock entities
 * @author privat
 */
public class GroupsController implements Serializable
{
  
  public List<Groups> list()
  {
      return update();
  }
  
  
  
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
  
  public void setArticle(int groupsID, boolean newArticle) throws GroupsNotFoundException
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
                  groups.setArticle(newArticle);
              }
          }
          
          persist(groupsList);
  }
  
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
  
  
  public void setGroups(int groupsID, boolean newGroup) throws GroupsNotFoundException
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
                  groups.setGroups(newGroup);
              }
          }
          
          persist(groupsList);
  }  
  
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
  
  public void setUser(int groupsID, boolean newUser) throws GroupsNotFoundException
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
                  groups.setUser(newUser);
              }
          }
          
          persist(groupsList);
  }
  
  
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

  public void setCategory(int groupsID, boolean newCategory) throws GroupsNotFoundException
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
                  groups.setCategory(newCategory);
              }
          }
          
          persist(groupsList);
  }  
  
  
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

