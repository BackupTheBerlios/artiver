
package eu.gymnaila.chunks.artiver.controller;


import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.entity.Invoice;
import eu.gymnaila.chunks.artiver.exceptions.*;
import java.util.List;
import eu.gymnaila.chunks.artiver.entity.Customer;
import eu.gymnaila.chunks.artiver.entity.DepictionArticle;
import eu.gymnaila.chunks.artiver.entity.MasterData;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.Date;
/**
 *
 * @author Don
 */
public class InvoiceController 
{
    
  /**
   * see return
   * @return list of all invoices 
   */  
  public List<Invoice> list()
  {
     return update();
  }
  
   /**
    * see return
    * @return generated offer number as String
    */
   public String generateInvoiceNr()
   {

     List<Invoice> invoices = update();
     
     int newID = -1;
     

            for (Invoice invoice : invoices) 
            {
                if (invoice.getIdInvoice() >= newID) 
                {
                    newID = invoice.getIdInvoice();
                }
            }
    
       newID ++;
       MasterData am = AppConfig.getMasterData();
       String nr = am.getInvoicePrefix();
       String newInvoiceNr = nr + newID;
            
      //das hier ist die neueste ID
      //TODO die nummer der id holen  und +1 machen
    
    return newInvoiceNr;
    
   }
  
  
  /**
   * 
   * @param invID PK in DB to search for
   * @return invoice number as String
   * @throws InvoiceNotFoundException thrown, if invalid id as argument 
   */
  public String getInvoiceNumber(int invID) throws InvoiceNotFoundException
  {
      
      List<Invoice> invoiceList = update();
      
          for(Invoice inv : invoiceList)
          {
              if(inv.getIdInvoice() == invID)
              {
                 return inv.getInvoiceNumber();
              }
          }
    
      throw new InvoiceNotFoundException("Invoice with id '" + invID + "' not found"); 
     
  }
  
  
  /**
   * 
   * @param invNum invoice number to set
   * @param invID PK in DB to search for
   * @throws InvoiceNotFoundException thrown, if invalid id as argument
   */  
  public void setInvoiceNumber(String invNum, int invID) throws InvoiceNotFoundException
  {
      
     List<Invoice> invoiceList = update();
   
     
     for(Invoice inv : invoiceList)
     {
         if(inv.getIdInvoice() == invID)
         {
             inv.setInvoiceNumber(invNum);
             persist(invoiceList);
             return;
         }
     }
    
     throw new InvoiceNotFoundException("Invoice could not be found."); 
  }
  
  
  
  /**
   * 
   * @param invID PK in DB to search for
   * @return price as double
   * @throws InvoiceNotFoundException  thrown, if invalid id as argument
   */
  public double getPrice(int invID) throws InvoiceNotFoundException
  {
      
      List<Invoice> invoiceList = update();
      
          for(Invoice inv : invoiceList)
          {
              if(inv.getIdInvoice() == invID)
              {
                 return inv.getPrice();
              }
          }
    
      throw new InvoiceNotFoundException("Invoice with id '" + invID + "' not found"); 
     
  }
  
  
 

  /**
   * 
   * @param invID PK in DB to search for
   * @return invoice's modifier
   * @throws InvoiceNotFoundException thrown, if invalid id as argument
   */
  public String getModifier(int invID) throws InvoiceNotFoundException
  {
      
      List<Invoice> invoiceList = update();
      
          for(Invoice inv : invoiceList)
          {
              if(inv.getIdInvoice() == invID)
              {
                 return inv.getModifier();
              }
          }
    
      throw new InvoiceNotFoundException("Invoice with id '" + invID + "' not found"); 
     
  }
  
 
  
 
  /**
   * 
   * @param invID PK in DB to search for
   * @return customer as customer-object
   * @throws InvoiceNotFoundException thrown, if invalid id as argument
   */
  public Customer getCustomer(int invID) throws InvoiceNotFoundException
  {
      
      List<Invoice> invoiceList = update();
      
          for(Invoice inv : invoiceList)
          {
              if(inv.getIdInvoice() == invID)
              {
                 return inv.getCustomer();
              }
          }
    
      throw new InvoiceNotFoundException("Invoice with id '" + invID + "' not found"); 
     
  }
  
  /**
   * enables to set a customer
   * @param cust customer to set
   * @param invID PK in DB to search for
   * @throws InvoiceNotFoundException thrown, if invalid id as argument
   */
  public void setCustomer(Customer cust, int invID) throws InvoiceNotFoundException
  {
      
     List<Invoice> invoiceList = update();
   
     
     for(Invoice inv : invoiceList)
     {
         if(inv.getIdInvoice() == invID)
         {
             inv.setCustomer(cust);
             persist(invoiceList);
             return;
         }
     }
    
     throw new InvoiceNotFoundException("Invoice could not be found."); 
  }
  

  /**
   * 
   * @param invID PK in DB to search for
   * @return date on which invoice is modfied as date-object
   * @throws InvoiceNotFoundException thrown, if invalid id as argument
   */
  public Date getModificationDate(int invID) throws InvoiceNotFoundException
  {
      
      List<Invoice> invoiceList = update();
      
          for(Invoice inv : invoiceList)
          {
              if(inv.getIdInvoice() == invID)
              {
                 return inv.getModificationDate();
              }
          }
    
      throw new InvoiceNotFoundException("Invoice with id '" + invID + "' not found"); 
     
  }
  

  /**
   * enables to add an invoice into the list
   * @param articles articles to add
   * @param price price to set
   * @param customer customer to set
   * @throws InvoiceAlreadyExistsException  thrown, if invalid id as argument
   */
  public void addInvoice(List<DepictionArticle> articles, double price, Customer customer) throws InvoiceAlreadyExistsException
  {
      
     List<Invoice> invoiceList = update();
          
     int newID = 0;
     
     for(Invoice inv : invoiceList)
     {
         if(inv.getIdInvoice() >= newID)
         {
             newID = inv.getIdInvoice();
             newID++;
             List<DepictionArticle> tempArts = this.normalizeList(articles);
             invoiceList.add(new Invoice(newID, this.generateInvoiceNr(), price, tempArts));
             persist(invoiceList); 
             return;
         }
     }

     
     throw new InvoiceAlreadyExistsException("This invoice does already exist."); 
 
  }
  
  
  /**
   * enables to delete an invoice
   * @param invoiceID
   * @throws InvoiceNotFoundException 
   */
  public void deleteInvoice(int invoiceID) throws InvoiceNotFoundException
  {
          List<Invoice> invoiceList = update();
                        
          for(Invoice invoice : invoiceList)
          {
              if(invoice.getIdInvoice() == invoiceID)
              {
                  dbDelete(invoice);
                  return;
              }
          }
          
          throw new InvoiceNotFoundException("Invoice with ID '" + invoiceID + "' does not exist.");
         
          
  }  
  

  


    private void dbDelete(Invoice invoice)
    {
            EntityManager em = AppConfig.createEntityManager();
            em.getTransaction().begin();
            invoice = em.merge(invoice);
            
            em.remove(invoice);
            em.flush();
            em.getTransaction().commit();
    }
    
    
    
    private List<Invoice> update()
    {
        EntityManager em = AppConfig.createEntityManager();

        Query articleQuery = em.createNamedQuery("Invoice.findAll");
        List<Invoice> invoices = articleQuery.getResultList();
        em.close();
        return invoices;
    }

    
    private void persist(List<Invoice> invoices)
    {
        EntityManager em = AppConfig.createEntityManager();
         // TODO Holt euch den EntityManager von der AppConfig Klasse
        for (Invoice invoice : invoices) 
        {
		System.out.println(invoice);
 
                em.getTransaction().begin();
                em.merge(invoice);
                em.getTransaction().commit();
	}
        System.out.println("Size: " + invoices.size());
        em.close();
        
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
