/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.controller;


import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.entity.*;
import java.util.List;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import eu.gymnaila.chunks.artiver.exceptions.*;
import java.util.ArrayList;
import javax.persistence.Query;
/**
 *
 * @author Don
 */
public class DeliveryNoteController 
{
    
  /**
   * 
   * @return the list of all DeliveryNotes 
   */
  public List<DeliveryNote> list()
  {
     return update();
  }
    
  
  
  /**
   * allows you to edit an existing Note
   * @param note note to edit
   * @throws DeliveryNoteNotFoundException thrown if argument could not be found
   */
  
  public void edit(DeliveryNote note) throws DeliveryNoteNotFoundException
  {
      List<DeliveryNote> noteList = update();
      
      if(!noteExists(note.getIdDeliveryNote()))
      {
          throw new DeliveryNoteNotFoundException("Delivery Note with the ID + " + note.getIdDeliveryNote() + " does not exist.");
      }
      
      int index = 0;
            
      for (DeliveryNote curNote : noteList) 
      {
        if (curNote.getIdDeliveryNote() == curNote.getIdDeliveryNote()) 
        {
             index = noteList.indexOf(curNote);
             break;
        }
      }

      noteList.set(index, note);  
      persist(noteList);
      
     
  }  
  
  
  /**
   * 
   * @return a generated Number for each note
   */
   public String generateNoteNr()
   {

     List<DeliveryNote> notes = update();
     MasterData md = AppConfig.getMasterData();
     
     int newID = -1;
     

            for (DeliveryNote note : notes) 
            {
                if (note.getIdDeliveryNote() >= newID) 
                {
                    newID = note.getIdDeliveryNote();
                }
            }
      newID++;
            
    String prefix = md.getDeliveryNotePrefix();
    return prefix + newID;
    
   }

  
  
   /**
    * see return
    * @param noteID PK
    * @return unique number of the delivery note
    * @throws DeliveryNoteNotFoundException thrown, if note's id was not found in DB
    */ 
  public String getDeliveryNoteNumber(int noteID) throws DeliveryNoteNotFoundException
  {
      
      List<DeliveryNote> noteList = update();
      
          for(DeliveryNote note : noteList)
          {
              if(note.getIdDeliveryNote() == noteID)
              {
                 return note.getDeliveryNoteNumber();
              }
          }
    
      throw new DeliveryNoteNotFoundException("Delivery Note with id '" + noteID + "' not found"); 
     
  }
  
  
  /**
   * allows to set the note's number
   * @param deliveryNoteNumber note's unique number
   * @param noteID PK 
   * @throws DeliveryNoteNotFoundException thrown, if id was not found in db 
   */
  public void setDeliveryNoteNumber(String deliveryNoteNumber, int noteID) throws DeliveryNoteNotFoundException
  {
      
     List<DeliveryNote> noteList = update();
     
     if(noteExists(noteID))
     {
         throw new DeliveryNoteNotFoundException("Deliverynote could not be found."); 
     }
     
     for(DeliveryNote note : noteList)
     {
         if(note.getIdDeliveryNote() == noteID)
         {
             note.setDeliveryNoteNumber(deliveryNoteNumber);
         }
     }
    
     persist(noteList);
  }
  
  /**
   * 
   * @param noteID PK 
   * @return returns the note's state
   * @throws DeliveryNoteNotFoundException thrown, if id was not found in DB
   */
  public String getDeliveryState(int noteID) throws DeliveryNoteNotFoundException
  {
      
      List<DeliveryNote> noteList = update();
      
          for(DeliveryNote note : noteList)
          {
              if(note.getIdDeliveryNote() == noteID)
              {
                 return note.getDeliveryState();
              }
          }
    
      throw new DeliveryNoteNotFoundException("Delivery Note with id '" + noteID + "' not found"); 
     
  }
  
  /**
   * 
   * @param state
   * @param noteID
   * @throws DeliveryNoteNotFoundException 
   */
  public void setDeliveryNoteState(String state, int noteID) throws DeliveryNoteNotFoundException
  {
      
     List<DeliveryNote> noteList = update();
     
     if(noteExists(noteID))
     {
         throw new DeliveryNoteNotFoundException("Deliverynote could not be found."); 
     }
     
     
     for(DeliveryNote note : noteList)
     {
         if(note.getIdDeliveryNote() == noteID)
         {
             note.setDeliveryState(state);
         }
     }
    
     persist(noteList);
  }
  
  /**
   * see return
   * @param noteID PK 
   * @return note's modifier
   * @throws DeliveryNoteNotFoundException thrown, if id was not found in DB
   */
  public String getModifier(int noteID) throws DeliveryNoteNotFoundException
  {
      
      List<DeliveryNote> noteList = update();
      
          for(DeliveryNote note : noteList)
          {
              if(note.getIdDeliveryNote() == noteID)
              {
                 return note.getModifier();
              }
          }
    
      throw new DeliveryNoteNotFoundException("Delivery Note with id '" + noteID + "' not found"); 
     
  }
  
  /**
   * allows to set note's modifier
   * @param mod modifier to set
   * @param noteID PK 
   * @throws DeliveryNoteNotFoundException thrown, if id was not found in DB
   */
   public void setDeliveryModifier(String mod, int noteID) throws DeliveryNoteNotFoundException
  {
      
     List<DeliveryNote> noteList = update();
     
     if(noteExists(noteID))
     {
         throw new DeliveryNoteNotFoundException("Deliverynote could not be found."); 
     }
     
     for(DeliveryNote note : noteList)
     {
         if(note.getIdDeliveryNote() == noteID)
         {
             note.setModifier(mod);
         }
     }
    
     persist(noteList);
  }
  
  /**
   * see return
   * @param noteID PK 
   * @return returns the delivery
   * @throws DeliveryNoteNotFoundException thrown, if id was not found in DB
   */   
    
  public String getDelivery(int noteID) throws DeliveryNoteNotFoundException
  {
      
      List<DeliveryNote> noteList = update();
      
          for(DeliveryNote note : noteList)
          {
              if(note.getIdDeliveryNote() == noteID)
              {
                 return note.getDelivery();
              }
          }
    
      throw new DeliveryNoteNotFoundException("Delivery Note with id '" + noteID + "' not found"); 
     
  }
  
  /**
   * enables to set the delivery
   * @param delivery delivery to set
   * @param noteID PK 
   * @throws DeliveryNoteNotFoundException  thrown, if was not found in DB
   */
  public void setDelivery(String delivery, int noteID) throws DeliveryNoteNotFoundException
  {
      
     List<DeliveryNote> noteList = update();
     
     if(noteExists(noteID))
     {
         throw new DeliveryNoteNotFoundException("Deliverynote could not be found."); 
     }
     
     for(DeliveryNote note : noteList)
     {
         if(note.getIdDeliveryNote() == noteID)
         {
             note.setDelivery(delivery);
         }
     }
    
     persist(noteList);
  }
  
  
  /**
   * see return
   * @param noteID PK
   * @return returns the date of it's delivery
   * @throws DeliveryNoteNotFoundException thrown, if id was not found in the DB
   */
  public Date getDeliveryDate(int noteID) throws DeliveryNoteNotFoundException
  {
      
      List<DeliveryNote> noteList = update();
      
          for(DeliveryNote note : noteList)
          {
              if(note.getIdDeliveryNote() == noteID)
              {
                 return note.getDeliveryDate();
              }
          }
    
      throw new DeliveryNoteNotFoundException("Delivery Note with id '" + noteID + "' not found"); 
     
  }
  
  /**
   * enables to set the date of delivery
   * @param date date to set
   * @param noteID PK
   * @throws DeliveryNoteNotFoundException  thrown, if id was not found in DB
   */
  public void setDeliveryDate(Date date, int noteID) throws DeliveryNoteNotFoundException
  {
      
     List<DeliveryNote> noteList = update();
     
     if(noteExists(noteID))
     {
         throw new DeliveryNoteNotFoundException("Deliverynote could not be found."); 
     }
     
     for(DeliveryNote note : noteList)
     {
         if(note.getIdDeliveryNote() == noteID)
         {
             note.setDeliveryDate(date);
         }
     }
    
     persist(noteList);
  }  
  
  
 
  /**
   * method adds a Delivery Note into the DB
   * @param customer customer to set on DN
   * @param depArt Depiction Articles to set on DN
   * @throws DeliveryNoteAlreadyExistsException thrown, if id was not found while running the db
   */
  
  //re-fixed :)
 public int addDeliveryNote(Customer customer, List<DepictionArticle> depArt) throws DeliveryNoteAlreadyExistsException
  {
      
     List<DeliveryNote> noteList = update();
     
     
     int newID = 0;
     
     for(DeliveryNote note : noteList)
     {
         if(note.getIdDeliveryNote() > newID)
         {
             newID = note.getIdDeliveryNote();
         }
     }
     
     newID++;
     DeliveryNote delNote = new DeliveryNote(newID, generateNoteNr(), "", "");
     delNote.setArticles(this.normalizeList(depArt));
     delNote.setCustomer(customer);
     noteList.add(delNote); //don @ matze: customer und depArt nicht übergeben, wozu dann im constructor? 
     
     persist(noteList);
     
     return newID;
  }

  private List<DepictionArticle> normalizeList(List<DepictionArticle> articles)
    {
        int newID = 0;

        EntityManager em = AppConfig.createEntityManager();

        Query articleQuery = em.createNamedQuery("DepictionArticle.findAll");
        List<DepictionArticle> depArts = articleQuery.getResultList();
        em.close();


        for (DepictionArticle depArt : depArts) 
        {
            if (depArt.getIdDepictionArticle() >= newID) 
            {
                newID = depArt.getIdDepictionArticle();
                newID++;
            }
        }

        List<DepictionArticle> tempArts = new ArrayList<>();
        
        for (DepictionArticle tempArt : articles)
        {
            tempArt.setIdDepictionArticle(newID);
            tempArts.add(tempArt);
            newID++;
        }
        return tempArts;     
    }
  
  /**
   * enables to delete a note from DB
   * @param noteID PK 
   * @throws DeliveryNoteNotFoundException thrown, if id was not found in DB
   */
  public void deleteNote(int noteID) throws DeliveryNoteNotFoundException
  {
          List<DeliveryNote> noteList = update();
                        
          for(DeliveryNote note : noteList)
          {
              if(note.getIdDeliveryNote() == noteID)
              {
                  dbDelete(note);
                  return;
              }
          }
          
          throw new DeliveryNoteNotFoundException("Note with ID '" + noteID + "' does not exist.");
         
          
  }  
  
  
  
  

  private boolean noteExists(int noteID)
  {
      EntityManager em = AppConfig.createEntityManager();

      Query noteQuery = em.createNamedQuery("DeliveryNote.findByIdDeliveryNote");
      noteQuery.setParameter("idDeliveryNote", noteID);
      
        try
        {
            // Try to get exactly one user from the DB, with exactly that card's uid
            DeliveryNote note = (DeliveryNote) noteQuery.getSingleResult();
                                  
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

  
  
  
  private boolean noteExists(String noteName)
  {
      EntityManager em = AppConfig.createEntityManager();

      Query noteQuery = em.createNamedQuery("DeliveryNote.findByName");
      noteQuery.setParameter("name", noteName);
      
     try
        {
            // Try to get exactly one user from the DB, with exactly that card's uid
            DeliveryNote note = (DeliveryNote) noteQuery.getSingleResult();
                                  
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
     * this method delets one invoice from the data base
     */
    private void dbDelete(DeliveryNote deliveryNote)
    {
            EntityManager em = AppConfig.createEntityManager();
            em.getTransaction().begin();
            deliveryNote = em.merge(deliveryNote);
            
            em.remove(deliveryNote);
            em.flush();
            em.getTransaction().commit();
    }  
    
    /**
     * this method sets the deliveryNoteList equal to the deliveryNoteList from the data base
     * @return this returns the deliveryNotes in the deliveryNoteList
     * ©Fat Don
     */
    
    private List<DeliveryNote> update()
    {
        EntityManager em = AppConfig.createEntityManager();

        Query articleQuery = em.createNamedQuery("DeliveryNote.findAll");
        List<DeliveryNote> deliveryNotes = articleQuery.getResultList();
        em.close();
        return deliveryNotes;
    }
    /**
     * this method changes the old deliveryNoteList from the data base and replaces it with the list,
     * that has been changed by the method
     * @param deliveryNotes this returns the deliveryNotes in the deliveryNoteList
     */
    // List<DeliveryNote> als Attribut übergebbar machen und diese liste in die db schreiben
    private void persist(List<DeliveryNote> deliveryNotes)
    {
        EntityManager em = AppConfig.createEntityManager();
         // TODO Holt euch den EntityManager von der AppConfig Klasse
        for (DeliveryNote deliveryNote : deliveryNotes) 
        {
		System.out.println(deliveryNote);
 
                em.getTransaction().begin();
                em.merge(deliveryNote);
                em.getTransaction().commit();
	}
      //  System.out.println("Size: " + deliveryNotes.size());
        em.close();
        
    }
}
