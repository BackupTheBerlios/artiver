/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.controller;

import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.entity.Category;
import eu.gymnaila.chunks.artiver.entity.Article;
import eu.gymnaila.chunks.artiver.exceptions.CategoryAlreadyExistsException;
import eu.gymnaila.chunks.artiver.exceptions.CategoryNotFoundException;
import eu.gymnaila.chunks.artiver.exceptions.CategoryConnectedToArticleException;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
/**
 *
 * @author privat
 */
public class CategoryController 
{

    /**
   * this method returns a List-Object contained with all users
   * @return List of all Users
   */
  public List<Category> list()
  {
      return update();  
  }

  
    
  public void addCategory(String name, String description) throws CategoryAlreadyExistsException
  {
      
     List<Category> categoryList = update();
     
     if(categoryExists(name))
     {
         throw new CategoryAlreadyExistsException("This Category does already exist."); 
     }
     
     int newID = -1;
     
     for(Category category : categoryList)
     {
         if(category.getIdCategory() >= newID)
         {
             newID = category.getIdCategory();
         }
     }
     
     newID++;
     categoryList.add(new Category(newID, name, description));
     
     persist(categoryList);
  }
  
  
  public void deleteCategory(Category cat) throws CategoryNotFoundException, CategoryConnectedToArticleException
  {
          //  System.out.println(categoryID);
      
          List<Category> categoryList = update();
              
        //    System.out.println(categoryList.size());
          
          Articlemanagement am = new Articlemanagement();
          List<Article> articleList = am.list();
          
          for(Article article : articleList)
          {
              if(article.getCategory().getIdCategory() == cat.getIdCategory())
              {
                  throw new CategoryConnectedToArticleException("Cannot delete Category: Article '" + article.getName() + "' connected to this Category.");
              }
          }
          
          for(Category category : categoryList)
          {
                //System.out.println(category.getIdCategory());
              
              if(category.getIdCategory() == cat.getIdCategory())
              {                
                  dbDelete(cat);
                  return;
              }
          }
          
       throw new CategoryNotFoundException("Category with ID '" + cat.getIdCategory() + "' does not exist.");   
  }
  
  
  public void setName(int categoryID, String name) throws CategoryNotFoundException
  {
          List<Category> categoryList = update();
          
          if(!categoryExists(categoryID))
          {
              throw new CategoryNotFoundException("Category with ID '" + categoryID + "' does not exist.");   
          }
            
          for(Category category : categoryList)
          {
              if(category.getIdCategory() == categoryID)
              {
                  category.setName(name);
              }
          }
          
          persist(categoryList);
  }
  
  
  public void setDescription(int categoryID, String description) throws CategoryNotFoundException
  {
          List<Category> categoryList = update();
          
          if(!categoryExists(categoryID))
          {
              throw new CategoryNotFoundException("Category with ID '" + categoryID + "' does not exist.");   
          }
            
          for(Category category : categoryList)
          {
              if(category.getIdCategory() == categoryID)
              {
                  category.setDescription(description);
              }
          }
          
          persist(categoryList);
   
  
  }
  
  
  public void setModificationDate(int categoryID, Date modificationDate) throws CategoryNotFoundException
  {
          List<Category> categoryList = update();
          
          if(!categoryExists(categoryID))
          {
              throw new CategoryNotFoundException("Category with ID '" + categoryID + "' does not exist.");   
          }
            
          for(Category category : categoryList)
          {
              if(category.getIdCategory() == categoryID)
              {
                  category.setModificationDate(modificationDate);
              }
          }
          
          persist(categoryList);
   
  
  }
  
  public String getName(int categoryID) throws CategoryNotFoundException
  {
      
      List<Category> categoryList = update();
      
      String curCategory = "uncategorized";
      
          if(!categoryExists(categoryID))
          {
              throw new CategoryNotFoundException("Category with ID '" + categoryID + "' does not exist."); 
          }
            
          for(Category category : categoryList)
          {
              if(category.getIdCategory() == categoryID)
              {
                 curCategory = category.getName();
              }
          }
    
      return curCategory;
  }
  
  
  public String getDescription(int categoryID) throws CategoryNotFoundException
  {
      
      List<Category> categoryList = update();
      
      String curDescription = "undescripted";
      
          if(!categoryExists(categoryID))
          {
              throw new CategoryNotFoundException("Category with ID '" + categoryID + "' does not exist."); 
          }
            
          for(Category category : categoryList)
          {
              if(category.getIdCategory() == categoryID)
              {
                 curDescription = category.getDescription();
              }
          }
    
      return curDescription;
  }
  
  
  public Date getModificationDate(int categoryID) throws CategoryNotFoundException
  {
      
      List<Category> categoryList = update();
      
      Date curModificationDate = null;
      
          if(!categoryExists(categoryID))
          {
              throw new CategoryNotFoundException("Category with ID '" + categoryID + "' does not exist."); 
          }
            
          for(Category category : categoryList)
          {
              if(category.getIdCategory() == categoryID)
              {
                 curModificationDate = category.getModificationDate();
              }
          }
    
      return curModificationDate;
  }  
  
  /**
   * this method is used in order to check whether a category
   * with the named ID exists
   */
  private boolean categoryExists(int categoryID)
  {
      EntityManager em = AppConfig.createEntityManager();

      Query categoryQuery = em.createNamedQuery("Category.findByIdCategory");
      categoryQuery.setParameter("idCategory", categoryID);
      
     try
        {
            // Try to get exactly one category from the DB, with exactly that card's uid
            Category category = (Category) categoryQuery.getSingleResult();
                                  
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
 * this method edits an article and inserts it in the database
 * @param category the category the user wants to edit
 * @throws CategoryNotFoundException 
 */
 public void edit (Category category) throws CategoryAlreadyExistsException, CategoryNotFoundException            
   {
       
        if (categoryExists(category.getName())) {
            
            List<Category> categories = update();
            
            
            int index = -1;
            
            for (Category newCat : categories) {
                if (newCat.getIdCategory() == category.getIdCategory()) {
                    index = categories.indexOf(newCat);
                    break;
                }
            }

            categories.set(index, category);
            
            persist(categories);
        }
        else
        {
            throw new CategoryNotFoundException("The category with the number '"+category.getName()+"' does not exist.");
        }
    }
 
  
    /**
   * this method is used to check whether a category exists by its name
   */
  private boolean categoryExists(String categoryName)
  {
      EntityManager em = AppConfig.createEntityManager();

      Query categoryQuery = em.createNamedQuery("Category.findByName");
      categoryQuery.setParameter("name", categoryName);
      
     try
        {
            // Try to get exactly one category from the DB, with exactly that card's uid
            Category category = (Category) categoryQuery.getSingleResult();
                                  
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

  
  private List<Category> update()
    {
        EntityManager em = AppConfig.createEntityManager();

        Query categoryQuery = em.createNamedQuery("Category.findAll");
        List<Category> categoryList = categoryQuery.getResultList();
        em.close();
        return categoryList;
    }
  
    private void persist(List<Category> categoryList)
    {
        EntityManager em = AppConfig.createEntityManager();
        for (Category category : categoryList) 
        {
		System.out.println(category);
                em.getTransaction().begin();
                em.merge(category);
                em.getTransaction().commit();
	}
        System.out.println("Size: " + categoryList.size());
        em.close();
        
    }
    
    private void dbDelete(Category category)
    {
            EntityManager em = AppConfig.createEntityManager();
            em.getTransaction().begin();
            category = em.merge(category);
            
                System.out.println(category.getIdCategory());
            
            em.remove(category);
            em.flush();
            em.getTransaction().commit();
            
            
    }
    
}
