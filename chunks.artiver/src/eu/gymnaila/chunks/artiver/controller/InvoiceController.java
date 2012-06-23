/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author privat
 */
public class InvoiceController 
{
  public List<Invoice> list()
  {
     return update();
  }
  
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
  
  
  //1
  
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
  
  
  
  //2
  
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
  
  
 
  //3
  
    
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
  
 
  
  //4
  
    
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
  
  //5
  
    
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
  
  //6
  
  
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
         }
     }
     List<DepictionArticle> tempArts = this.normalizeList(articles);
     
     invoiceList.add(new Invoice(newID, this.generateInvoiceNr(), price, tempArts));
     persist(invoiceList);
     
     throw new InvoiceAlreadyExistsException("This invoice does already exist."); 
 
  }
  
  
  
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
  

  

    /**
     * this method delets one invoice from the data base
     */
    private void dbDelete(Invoice invoice)
    {
            EntityManager em = AppConfig.createEntityManager();
            em.getTransaction().begin();
            invoice = em.merge(invoice);
            
            em.remove(invoice);
            em.flush();
            em.getTransaction().commit();
    }
    
    
    
    /**
     * this method sets the invoicelist equal to the invoicelist from the data base
     * @return this returns the invoices in the invoicelist
     * ©Fat Don
     */
    
    private List<Invoice> update()
    {
        EntityManager em = AppConfig.createEntityManager();

        Query articleQuery = em.createNamedQuery("Invoice.findAll");
        List<Invoice> invoices = articleQuery.getResultList();
        em.close();
        return invoices;
    }
    /**
     * this method changes the old invoicelist from the data base and replaces it with the list,
     * that has been changed by the method
     * @param invoices this returns the invoices in the invoicelist
     */
    // List<Invoice> als Attribut übergebbar machen und diese liste in die db schreiben
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
