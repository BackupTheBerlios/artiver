package eu.gymnaila.chunks.artiver.controller;


import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.entity.Customer;
import eu.gymnaila.chunks.artiver.entity.DepictionArticle;
import eu.gymnaila.chunks.artiver.entity.MasterData;
import eu.gymnaila.chunks.artiver.entity.Offer;
import eu.gymnaila.chunks.artiver.exceptions.OfferDoesNotExistException;
import eu.gymnaila.chunks.artiver.exceptions.OfferNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
/**
 *
 * @author Philipp
 */
public class OfferController 
{
     /**
     *  this method returns a List-Object contained with all offers
     * @return List of all offers
     * ©Fat Don
     */
  public List<Offer> list()
  {
     return update();
  }
  /**
   * This method delets an offer from the offerlist
   * @param offerID the ID of the offer the user wants to delete
   * @throws OfferNotFoundException
   * @throws OfferDoesNotExistException 
   */
    
  //wieso id -> String
  public void deleteOffer(String offerID) throws  OfferNotFoundException, OfferDoesNotExistException
  {
      List<Offer>offers=update();
      if(!offerExists(offerID))
      {
        throw new OfferNotFoundException("The offer with the ID '" + offerID + "' does not exist.");
      }
      for (Offer offer : offers)
      {
         if(offer.getIdOffer().equals(offerID))
         {
           dbDelete(offer);
           break;
         }
      }
  }
 /**
  * This method adds an offer to the offerlist
  * @param price the total price of the offer
  * @param articles the articles that the offer will contain
  * @param customer the customer who created the offer
  */
   public void addOffer(double price, List<DepictionArticle> articles, Customer customer)
   {
     List <Offer> offers = update();
     int newID = -1;
     for (Offer offer : offers) 
      {
       if (offer.getIdOffer() >= newID) 
       {
         newID = offer.getIdOffer();
       }
      }
     newID++;
     List<DepictionArticle> tempArts = this.normalizeList(articles);
     offers.add(new Offer(newID, this.generateOfferNr(), price));
     persist(offers);

    
    
     
   }
   /**
    * this method creates a new offer number
    * @return generated offer number
    */
   public String generateOfferNr()
   {

     List<Offer> offers = update();
     MasterData md = AppConfig.getMasterData();
     
     int newID = -1;
     

            for (Offer offer : offers) 
            {
                if (offer.getIdOffer() >= newID) 
                {
                    newID = offer.getIdOffer();
                }
            }
      newID++;
            
    String prefix = md.getOfferPrefix();
    String newOfferNr = prefix + newID;
    return newOfferNr;
    
   }

    
    

    
    /**
     * this method deletes one offer from the data base
     */
    private void dbDelete(Offer offer)
    {
            EntityManager em = AppConfig.createEntityManager();
            em.getTransaction().begin();
            offer = em.merge(offer);
            
            em.remove(offer);
            em.flush();
            em.getTransaction().commit();
    }
    
    
    
    /**
     * this method sets the offerList equal to the offerList from the data base
     * @return returns all offers
     */
    
    //Doc-Comment überarbeitet; fail copy & paste ;) (Don)
    
    private List<Offer> update()
    {
        EntityManager em = AppConfig.createEntityManager();

        Query articleQuery = em.createNamedQuery("Offer.findAll");
        List<Offer> offers = articleQuery.getResultList();
        em.close();
        return offers;
    }
  
    
    
    // List<Offer> als Attribut übergebbar machen und diese liste in die db schreiben
    private void persist(List<Offer> offers)
    {
        EntityManager em = AppConfig.createEntityManager();
         // TODO Holt euch den EntityManager von der AppConfig Klasse
        for (Offer offer : offers) 
        {
		System.out.println(offer);
 
                em.getTransaction().begin();
                em.merge(offer);
                em.getTransaction().commit();
	}
       // System.out.println("Size: " + offers.size());
        em.close();
        
    }
      private boolean offerExists(String offerNumber) throws OfferDoesNotExistException
  {
     EntityManager em = AppConfig.createEntityManager();

      Query offerQuery = em.createNamedQuery("Offer.findByOfferNumber");
      offerQuery.setParameter("offerNumber", offerNumber);
      
      
     try
        {
            // Try to get exactly one user from the DB, with exactly that card's uid
            Offer offer = (Offer) offerQuery.getSingleResult();
                                  
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

        List<DepictionArticle> tempArts = new ArrayList<DepictionArticle>();
        
        for (DepictionArticle tempArt : articles)
        {
            tempArt.setIdDepictionArticle(newID);
            tempArts.add(tempArt);
            newID++;
        }
        return tempArts;     
    }

}
