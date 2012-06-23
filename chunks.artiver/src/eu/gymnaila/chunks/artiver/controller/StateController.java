/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.controller;

import eu.gymnaila.chunks.artiver.exceptions.StateConnectedToArticleException;
import eu.gymnaila.chunks.artiver.exceptions.*;
import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.entity.Category;
import eu.gymnaila.chunks.artiver.entity.State;
import eu.gymnaila.chunks.artiver.entity.Article;
import java.io.Serializable;
import java.util.List;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
/**
 *
 * @author Fat Don + ?
 */
public class StateController implements Serializable
{

    /**
   * this method returns a List-Object contained with all States
   * @return List of all States
   */
  public List<State> list()
  {
      return update();  
  }
  
  
  public void addState(String stateName, String description) throws StateAlreadyExistsException
  {
      
     List<State> stateList = update();
     
     if(stateExists(stateName))
     {
         throw new StateAlreadyExistsException("This state does already exist."); 
     }
     
     int newID = -1;
     
     for(State state : stateList)
     {
         if(state.getIdState() >= newID)
         {
             newID = state.getIdState();
         }
     }
     
     newID++;

     stateList.add(new State(newID, stateName, description));
     
        System.out.println(stateList.get(stateList.size()-1));
     
     persist(stateList);
  }
  
  
  public void deleteState(State st) throws StateNotFoundException, StateConnectedToArticleException
  {
          List<State> stateList = update();
          
          Articlemanagement am = new Articlemanagement();
          List<Article> articleList = am.list();
          
          for(Article article : articleList)
          {
              if(article.getState().getIdState() == st.getIdState())
              {
                  throw new StateConnectedToArticleException("Cannot delete State: " + "Article '" + article.getName() + "' connected to this State");
              }
          }
         
          for(State state : stateList)
          {
              if(state.getIdState() == st.getIdState())
              {
                dbDelete(st);
                return;
              }
          }
          
          throw new StateNotFoundException("State with ID '" + st.getIdState() + "' does not exist.");
 
  }
  
  public void setName(int stateID, String name) throws StateNotFoundException
  {
          List<State> stateList = update();
          
          if(!stateExists(stateID))
          {
              throw new StateNotFoundException("State with ID '" + stateID + "' does not exist.");   
          }
            
          for(State state : stateList)
          {
              if(state.getIdState() == stateID)
              {
                  state.setName(name);
              }
          }
  }
  
  
  public void setDescription(int stateID, String description) throws StateNotFoundException
  {
          List<State> stateList = update();
          
          if(!stateExists(stateID))
          {
              throw new StateNotFoundException("State with ID '" + stateID + "' does not exist.");   
          }
            
          for(State state : stateList)
          {
              if(state.getIdState() == stateID)
              {
                  state.setName(description);
              }
          }
          
          persist(stateList);
   
  
  }
  
  
    public void setModificationDate(int stateID, Date modificationDate) throws StateNotFoundException
  {
          List<State> stateList = update();
          
          if(!stateExists(stateID))
          {
              throw new StateNotFoundException("State with ID '" + stateID + "' does not exist.");   
          }
            
          for(State state : stateList)
          {
              if(state.getIdState() == stateID)
              {
                  state.setModificationDate(modificationDate);
              }
          }
          
          persist(stateList);
   
  
  }
  
  public String getName(int stateID) throws StateNotFoundException
  {
      
      List<State> stateList = update();
      
      String curState = "default State";
      
          if(!stateExists(stateID))
          {
              throw new StateNotFoundException("State with ID '" + stateID + "' does not exist."); 
          }
            
          for(State state : stateList)
          {
              if(state.getIdState() == stateID)
              {
                 curState = state.getName().toString();
              }
          }
    
      return curState;
  }
  
  
  public String getDescription(int stateID) throws StateNotFoundException
  {
      
      List<State> stateList = update();
      
      String curDescription = "description missing";
      
          if(!stateExists(stateID))
          {
              throw new StateNotFoundException("State with ID '" + stateID + "' does not exist."); 
          }
            
          for(State state : stateList)
          {
              if(state.getIdState() == stateID)
              {
                 curDescription = state.getDescription().toString();
              }
          }
    
      return curDescription;
  }
  
  
  public Date getModificationDate(int stateID) throws StateNotFoundException
  {
      
      List<State> stateList = update();
      
      Date curModificationDate = null;
      
          if(!stateExists(stateID))
          {
              throw new StateNotFoundException("State with ID '" + stateID + "' does not exist."); 
          }
            
          for(State state : stateList)
          {
              if(state.getIdState() == stateID)
              {
                 curModificationDate = state.getModificationDate();
              }
          }
    
      return curModificationDate;
  }  
  
  
  /**
 * this method edits a state and inserts it in the database
 * @param state the state the user wants to edit
 * @throws StateNotFoundException 
 */
 public void edit (State state) throws StateAlreadyExistsException, StateNotFoundException            
   {
       
        if (stateExists(state.getName())) {
            
            List<State> states = update();
            
            
            int index = -1;
            
            for (State newSta : states) {
                if (newSta.getIdState() == state.getIdState()) {
                    index = states.indexOf(newSta);
                    break;
                }
            }

            states.set(index, state);
            
            persist(states);
        }
        else
        {
            throw new StateNotFoundException("The state with the number '"+state.getName()+"' does not exist.");
        }
    }
 
  
  /**
   * this method is used in order to check whether a state
   * with the named ID exists
   */
  private boolean stateExists(int stateID)
  {
      EntityManager em = AppConfig.createEntityManager();

      Query stateQuery = em.createNamedQuery("State.findByIdState");
      stateQuery.setParameter("idState", stateID);
      
     try
        {
            // Try to get exactly one state from the DB, with exactly that card's uid
            State state = (State) stateQuery.getSingleResult();
                                  
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
   * this method is used to check whether a state exists by its name
   */
  private boolean stateExists(String stateName)
  {
      EntityManager em = AppConfig.createEntityManager();

      Query stateQuery = em.createNamedQuery("State.findByName");
      stateQuery.setParameter("name", stateName);
      
     try
        {
            // Try to get exactly one state from the DB, with exactly that card's uid
            State state = (State) stateQuery.getSingleResult();
                                  
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
   * this method sets the statelist equal to state data from the data base
   */
  private List<State> update()
  {
        EntityManager em = AppConfig.createEntityManager();

        Query stateQuery = em.createNamedQuery("State.findAll");
        List<State> stateList = stateQuery.getResultList();
        em.close();
        return stateList;
  }
  
  /**
   * this method sets data from the data base equal to the statelist
   */
   private void persist(List<State> stateList)
   {       
       EntityManager em = AppConfig.createEntityManager();
        for (State state : stateList) 
        {
		//System.out.println(state);
                em.getTransaction().begin();
                em.merge(state);
                em.getTransaction().commit();
	}
      //  System.out.println("Size: " + stateList.size());
        em.close();
        
    }
   
   private void dbDelete(State state)
    {
            EntityManager em = AppConfig.createEntityManager();
            em.getTransaction().begin();
            state = em.merge(state);
            
            em.remove(state);
            em.flush();
            em.getTransaction().commit();
    }
}
