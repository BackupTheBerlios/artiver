/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.controller;

import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.entity.Article;
import eu.gymnaila.chunks.artiver.entity.Stock;
import eu.gymnaila.chunks.artiver.exceptions.StockAlreadyExistsException;
import eu.gymnaila.chunks.artiver.exceptions.StockNotFoundException;
import eu.gymnaila.chunks.artiver.exceptions.StockConnectedWithArticlesException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Handles database-access for Stock entities
 * @author Fat Don  + pmarek
 */
public class StockController implements Serializable
{
    // TODO Anpassungen wie in den anderen Controllern vornehmen!
  
  /**
   * this method returns a List-Object contained with all articles
   * @return List of all Stocks
   * ©Fat Don
   */
  
  public List<Stock> list()
  {
     return update();
  }
 /**
  * Creates a new stock with the parameters and inserts it in the database.
  * @param stockID the unique ID of the stock
  * @param name the name of the stock
  * @param country the country, the stock is placed
  * @param address the address where the stock is placed
  * @throws StockAlreadyExistsException 
  */
  
  
  
  public void edit(Stock stock) throws StockNotFoundException
  {
      List<Stock> stockList = update();
      
      if(!stockExists(stock.getIdStock()))
      {
          throw new StockNotFoundException("Stock with the ID + " + stock.getIdStock() + " does not exist.");
      }
      
      int index = 0;
            
      for (Stock curStock : stockList) 
      {
        if (curStock.getIdStock() == stock.getIdStock()) 
        {
             index = stockList.indexOf(curStock);
             break;
        }
      }

      stockList.set(index, stock);  
      persist(stockList);
      
     
  }
  
  public void createStock(String name, String country, String address) throws StockAlreadyExistsException
  {
   
            
     List<Stock> stockList = update();
     
     int newID = 0;
     
     if(stockExists(name))
     {
         throw new StockAlreadyExistsException("This Stock does already exist."); 
     }
     
     for(Stock stock : stockList)
     {
         if(stock.getIdStock() >= newID)
         {
             newID = stock.getIdStock();
             
         }
     }
     
     newID++;   
     stockList.add(new Stock(newID, name, country, address));   
     persist(stockList);
     
      
  }
  /**
   * removes a stock from the stocklist
   * @param stockID the unique ID of the stock
   * @throws StockNotFoundException 
   */
  public void deleteStock(int stockID) throws StockNotFoundException, StockConnectedWithArticlesException
  {
      List<Stock> stockList = update();
      Articlemanagement am = new Articlemanagement();
      List<Article> articles = am.list();
          if(!stockExists(stockID))
          {
              throw new StockNotFoundException("Stock with ID '" + stockID + "' does not exist.");
          }
          for(Article article : articles)
          {
            if(article.getStock().getIdStock() == stockID)
            {
               throw new StockConnectedWithArticlesException("The stock with the ID '"+stockID+"' still contains Articles.");
            }
          }
          for(Stock stock : stockList)
          {
              if(stock.getIdStock() == stockID)
              {
                  dbDelete(stock);
                  return;
              }
          }
  }
  /**
   * Returns the name of the stock
   * @param stockID the unique ID of the stock
   * @return Returns the stockname
   * @throws StockNotFoundException 
   */
  public String getName(int stockID) throws StockNotFoundException
  {
      
      List<Stock> stockList = update();
      
      String curName = "";
      
          if(!stockExists(stockID))
          {
             throw new StockNotFoundException("Could not find the stock with the ID'"+stockID+"'."); 
          }
            
          for(Stock stock : stockList)
          {
              if(stock.getIdStock() == stockID)
              {
                 curName = stock.getName();
              }
          }
    
      return curName;
  }
  
  /**
   * Returns the address of the stock
   * @param stockID the unique ID of the stock
   * @return returns the stockaddress
   * @throws StockNotFoundException 
   */
  public String getAddress(int stockID) throws StockNotFoundException
  {
      
      List<Stock> stockList = update();
      
      String curAddress = "";
      
          if(!stockExists(stockID))
          {
              throw new StockNotFoundException("Could not find the stock with the ID'"+stockID+"'."); 
          }
            
          for(Stock stock : stockList)
          {
              if(stock.getIdStock() == stockID)
              {
                 curAddress = stock.getAddress();
              }
          }
    
      return curAddress;
  }
  
  /**
   * Returns the country of the stock
   * @param stockID the unique ID of the stock
   * @return returns the country of the stock
   * @throws StockNotFoundException 
   */
  
  public String getCountry(int stockID) throws StockNotFoundException
  {
      
      List<Stock> stockList = update();
      
      String curCountry = "";
      
          if(!stockExists(stockID))
          {
              throw new StockNotFoundException("Could not find the stock with the ID'"+stockID+"'."); 
          }
            
          for(Stock stock : stockList)
          {
              if(stock.getIdStock() == stockID)
              {
                 curCountry = stock.getCountry();
              }
          }
    
      return curCountry;
  }
  /**
   * returns the modifier of the stock
   * @param stockID the unique ID of the stock
   * @return returns the modifier of the stock
   * @throws StockNotFoundException 
   */
  //TODO weiterarbeiten an den java-doc-kommentaren
  public String getModifier(int stockID) throws StockNotFoundException
  {
      
      List<Stock> stockList = update();
      
      String curModifier = "";
      
          if(!stockExists(stockID))
          {
              throw new StockNotFoundException("Could not find the stock with the ID'"+stockID+"'."); 
          }
            
          for(Stock stock : stockList)
          {
              if(stock.getIdStock() == stockID)
              {
                 curModifier = stock.getModifier();
              }
          }
    
      return curModifier;
  }
  
  
  public void setName(int stockID, String name) throws StockNotFoundException
  {
          List<Stock> stockList = update();
          
          if(!stockExists(stockID))
          {
              throw new StockNotFoundException("Could not find the stock with the ID'"+stockID+"'.");   
          }
            
          for(Stock stock : stockList)
          {
              if(stock.getIdStock() == stockID)
              {
                  stock.setName(name);
              }
          }
          
          persist(stockList);
  }
  
  public void setAddress(int stockID, String address) throws StockNotFoundException
  {
          List<Stock> stockList = update();
          
          if(!stockExists(stockID))
          {
              throw new StockNotFoundException("Could not find the stock with the ID'"+stockID+"'.");   
          }
            
          for(Stock stock : stockList)
          {
              if(stock.getIdStock() == stockID)
              {
                  stock.setAddress(address);
              }
          }
          
          persist(stockList);
  }
  
  
  
  public void setCountry(int stockID, String country) throws StockNotFoundException
  {
          List<Stock> stockList = update();
          
          if(!stockExists(stockID))
          {
              throw new StockNotFoundException("Could not find the stock with the ID'"+stockID+"'.");   
          }
            
          for(Stock stock : stockList)
          {
              if(stock.getIdStock() == stockID)
              {
                  stock.setCountry(country);
              }
          }
          
          persist(stockList);
  }
  
  public void setModifier(int stockID, String modifier) throws StockNotFoundException
  {
          List<Stock> stockList = update();
          
          if(!stockExists(stockID))
          {
              throw new StockNotFoundException("Could not find the stock with the ID'"+stockID+"'.");   
          }
            
          for(Stock stock : stockList)
          {
              if(stock.getIdStock() == stockID)
              {
                  stock.setModifier(modifier);
              }
          }
          
          persist(stockList);
  } 
  
  
    
  private boolean stockExists(int stockID)
  {
      EntityManager em = AppConfig.createEntityManager();

      Query stockQuery = em.createNamedQuery("Stock.findByIdStock");
      stockQuery.setParameter("idStock", stockID);
      
     try
        {
            // Try to get exactly one user from the DB, with exactly that card's uid
            Stock stock = (Stock) stockQuery.getSingleResult();
                                  
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
  

  private boolean stockExists(String stockName)
  {
      EntityManager em = AppConfig.createEntityManager();

      Query stockQuery = em.createNamedQuery("Stock.findByName");
      stockQuery.setParameter("name", stockName);
      
     try
        {
            // Try to get exactly one user from the DB, with exactly that card's uid
            Stock stock = (Stock) stockQuery.getSingleResult();
                                  
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
    
    
    
    private List<Stock> update()
    {
        EntityManager em = AppConfig.createEntityManager();

        Query stockQuery = em.createNamedQuery("Stock.findAll");
        List<Stock> stockList = stockQuery.getResultList();
        em.close();
        return stockList;
    }
    
    private void persist(List<Stock> stockList)
    {
        EntityManager em = AppConfig.createEntityManager();
        for (Stock stock : stockList) 
        {
		System.out.println(stock);
                em.getTransaction().begin();
                em.merge(stock);
                em.getTransaction().commit();
	}
        System.out.println("Size: " + stockList.size());
        em.close();
        
    }
    private void dbDelete(Stock stock)
    {
            EntityManager em = AppConfig.createEntityManager();
            em.getTransaction().begin();
            stock = em.merge(stock);
            
            em.remove(stock);
            em.flush();
            em.getTransaction().commit();
    }
    
}
