/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.controller;

import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.entity.Customer;
import eu.gymnaila.chunks.artiver.exceptions.CustomerAlreadyExistsException;
import eu.gymnaila.chunks.artiver.exceptions.CustomerNotFoundException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * @author Fat Don + ?
 */
public class CustomerController implements Serializable
{
  
    /**
   * this method returns a List-Object contained with all users
   * @return List of all Customers
   */
  public List<Customer> list()
  {
      return update();  
  }
  
  
  /**
   * used to edit a given customer
   * @param customer customer wanted to edit
   * @throws CustomerNotFoundException thrown, if invalid customer 
   */
  public void edit(Customer customer) throws CustomerNotFoundException
  {
      List<Customer> customerList = update();
      
      if(!customerExists(customer.getIdCustomer()))
      {
          throw new CustomerNotFoundException("Customer with the ID + " + customer.getIdCustomer() + " does not exist.");
      }
      
      int index = 0;
            
      for (Customer curCustomer : customerList) 
      {
        if (curCustomer.getIdCustomer().equals(customer.getIdCustomer())) 
        {
             index = customerList.indexOf(curCustomer);
             break;
        }
      }

      customerList.set(index, customer);  
      persist(customerList);
      
     
  }
  
  //TODO sehr aussagekräftig, ändern
  /**
   * 
   * @param number
   * @param customer
   * @param address 
   * @throws CustomerAlreadyExistsException thrown if customer already exists in DB
   */
  public void addCustomer(String number, String customer, String address) throws CustomerAlreadyExistsException
  {
      
     List<Customer> customerList = update();
     
     if(customerExists(number))
     {
         throw new CustomerAlreadyExistsException("This customer does already exist."); 
     }
     
     int newID = -1;
     
     for(Customer cust : customerList)
     {
         if(cust.getIdCustomer() >= newID)
         {
             newID = cust.getIdCustomer();
         }
     }
     
     newID++;
     
     customerList.add(new Customer(newID, number, customer, address));
     
     persist(customerList);
  }
  
  /**
   * this method deletes a customer by it's id
   * @param customerID PK in the DB
   * @throws CustomerNotFoundException thrown if customer does not exist in DB 
   */
  public void deleteCustomer(int customerID) throws CustomerNotFoundException
  {
          List<Customer> customerList = update();
              
          if(!customerExists(customerID))
          {
              throw new CustomerNotFoundException("Customer with id '" + customerID + "' not found"); 
          }
          
          for(Customer customer : customerList)
          {
              if(customer.getIdCustomer() == customerID) //getIdCustomer() ?
              {
                  dbDelete(customer);
                  break;
              }
          }
          
  }
  
  /**
   * this method returns a customer's name
   * @param customerID PK in DB
   * @return customer's name as String
   * @throws CustomerNotFoundException thrown, if ID does not exist in DB
   */
  public String getName(int customerID) throws CustomerNotFoundException
  {
      
      List<Customer> customerList = update();
      
      String curName = "";
      
          if(!customerExists(customerID))
          {
              throw new CustomerNotFoundException("Customer with id '" + customerID + "' not found"); 
          }
            
          for(Customer customer : customerList)
          {
              if(customer.getIdCustomer() == customerID)
              {
                 curName = customer.getCustomer();
              }
          }
    
      return curName;
  }
  
  /**
   * returns customer's address
   * @param customerID PK in DB
   * @return customer's address as String
   * @throws CustomerNotFoundException thrown, if id does not exist in db
   */
  public String getAddress(int customerID) throws CustomerNotFoundException
  {
      
      List<Customer> customerList = update();
      
      String curAddress = "";
      
          if(!customerExists(customerID))
          {
              throw new CustomerNotFoundException("Customer with id '" + customerID + "' not found"); 
          }
            
          for(Customer customer : customerList)
          {
              if(customer.getIdCustomer() == customerID)
              {
                 curAddress = customer.getAddress();
              }
          }
    
      return curAddress;
  }
  
  /**
   * this method is used to set a customer's name
   * @param customerID PK in DB
   * @param name customer's name
   * @throws CustomerNotFoundException thrown, if cannot be found in db 
   */
  public void setName(int customerID, String name) throws CustomerNotFoundException
  {
          List<Customer> customerList = update();
          
          if(!customerExists(customerID))
          {
              throw new CustomerNotFoundException("Customer with id '" + customerID + "' not found");   
          }
            
          for(Customer customer : customerList)
          {
              if(customer.getIdCustomer() == customerID)
              {
                  customer.setCustomer(name);
              }
          }
          
          persist(customerList);
  }
  
  /**
   * sets a customer's address
   * @param customerID PK in DB 
   * @param address customer's address
   * @throws CustomerNotFoundException thrown, if id does not exist in db
   */
  public void setAddress(int customerID, String address) throws CustomerNotFoundException
  {
          List<Customer> customerList = update();
          
          if(!customerExists(customerID))
          {
              throw new CustomerNotFoundException("Customer with id '" + customerID + "' not found");   
          }
            
          for(Customer customer : customerList)
          {
              if(customer.getIdCustomer() == customerID)
              {
                  customer.setAddress(address);
              }
          }
          
          persist(customerList);
  }
  
  
  
  /**
   * searches for a customer in db by it's number
   * @param number
   * @return returns true, if customer exists
   */
   private boolean customerExists(String number)
   {
      EntityManager em = AppConfig.createEntityManager();

      Query customerQuery = em.createNamedQuery("Customer.findByCustomerNr");
      customerQuery.setParameter("customerNr", number);
      
     try
        {
            // Try to get exactly one customer from the DB, with exactly that card's uid
            Customer customer = (Customer) customerQuery.getSingleResult();
                                  
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
    * searches for a customer in db by it's id
    * @param customerID PK in DB
    * @return true, if customer exists
    */
   private boolean customerExists(int customerID)
   {
      EntityManager em = AppConfig.createEntityManager();

      Query customerQuery = em.createNamedQuery("Customer.findByIdCustomer");
      customerQuery.setParameter("idCustomer", customerID);
      
     try
        {
            // Try to get exactly one customer from the DB, with exactly that card's uid
            Customer customer = (Customer) customerQuery.getSingleResult();
                                  
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
  

    private List<Customer> update()
    {
        EntityManager em = AppConfig.createEntityManager();

        Query customerQuery = em.createNamedQuery("Customer.findAll");
        List<Customer> customerList = customerQuery.getResultList();
        em.close();
        return customerList;
    }
    private void persist(List<Customer> customerList)
    {
        EntityManager em = AppConfig.createEntityManager();
        for (Customer customer : customerList) 
        {
		System.out.println(customer);
                em.getTransaction().begin();
                em.merge(customer);
                em.getTransaction().commit();
	}
        System.out.println("Size: " + customerList.size());
        em.close();
        
    }
    private void dbDelete(Customer customer)
    {
            EntityManager em = AppConfig.createEntityManager();
            em.getTransaction().begin();
            customer = em.merge(customer);
            
            em.remove(customer);
            em.flush();
            em.getTransaction().commit();
    }
    
}

