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
 * Handles database-access for Stock entities
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
        if (curCustomer.getIdCustomer() == curCustomer.getIdCustomer()) 
        {
             index = customerList.indexOf(curCustomer);
             break;
        }
      }

      customerList.set(index, customer);  
      persist(customerList);
      
     
  }
  
  
  
  public void addUser(String number, String customer, String address) throws CustomerAlreadyExistsException
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

