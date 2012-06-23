/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.controller;


import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.entity.DeliveryNote;
import java.util.List;
import java.util.Date;
import eu.gymnaila.chunks.artiver.entity.State;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import eu.gymnaila.chunks.artiver.exceptions.*;
import javax.persistence.Query;
import eu.gymnaila.chunks.artiver.entity.MasterData;
/**
 *
 * @author Fat
 */
public class DeliveryNoteController 
{
    
  public List<DeliveryNote> list()
  {
     return update();
  }
    
  
  
  
  
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

  
  
    
  public int getDeliveryNoteNumber(int noteID) throws DeliveryNoteNotFoundException
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
  
  
  
  public void setDeliveryNoteNumber(int deliveryNoteNumber, int noteID) throws DeliveryNoteNotFoundException
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
  
  
 
  
  public void addDeliveryNote(int deliveryNoteNumber, String delivery, String deliveryState, String deliveryName) throws DeliveryNoteAlreadyExistsException
  {
      
     List<DeliveryNote> noteList = update();
     
     if(noteExists(deliveryName))
     {
         throw new DeliveryNoteAlreadyExistsException("This note does already exist."); 
     }
     
     int newID = 0;
     
     for(DeliveryNote note : noteList)
     {
         if(note.getIdDeliveryNote() > newID)
         {
             newID = note.getIdDeliveryNote();
         }
     }
     
     newID++;
     noteList.add(new DeliveryNote(newID, deliveryNoteNumber, delivery, deliveryState));
     persist(noteList);
  }

  
  
  
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
        System.out.println("Size: " + deliveryNotes.size());
        em.close();
        
    }
}
