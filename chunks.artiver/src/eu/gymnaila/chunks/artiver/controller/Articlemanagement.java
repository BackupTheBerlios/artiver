/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.controller;

import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.entity.Article;
import eu.gymnaila.chunks.artiver.entity.Category;
import eu.gymnaila.chunks.artiver.entity.State;
import eu.gymnaila.chunks.artiver.entity.Stock;
import eu.gymnaila.chunks.artiver.exceptions.ArticleAlreadyExistsException;
import eu.gymnaila.chunks.artiver.exceptions.ArticleDoesNotExistException;
import eu.gymnaila.chunks.artiver.exceptions.NotEnoughAmountException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author pmarek
 */


public class Articlemanagement 
{
 
    /**
     *  this method returns a List-Object contained with all articles
     * @return List of all articles 
     * ©Fat Don
     */
    
 public List<Article> list()
  {
     return update();
  }

 /**
 * this method edits an article and inserts it in the database
 * @param name the name of the article the user wants to create
 * @param nr the unique number og the article the user wants to create
 * @param price the price of the article the user wants to create
 * @param EAN the ean of the article the user wants to create
 * @param amount the amount of the article the user wants to create
 * @param colourCode the colour code of the article the user wants to create
 * @param categoryId the category id of the article the user wants to create
 * @param stockId the stock id
 * @param stateId the state id
 * @throws ArticleDoesNotExistException 
 */

   public void edit (Article article) throws ArticleAlreadyExistsException, ArticleDoesNotExistException            
   {
       
        if (articleExists(article.getNr())) {
            
            List<Article> articles = update();
            
            
            int index = -1;
            
            for (Article newArt : articles) {
                if (newArt.getIdArticle() == article.getIdArticle()) {
                    index = articles.indexOf(newArt);
                    break;
                }
            }

            articles.set(index, article);
            
            persist(articles);
        }
        else
        {
            throw new ArticleDoesNotExistException("The article with the number '"+article.getNr()+"' does not exist.");
        }
    }
 
 
 
/**
 * this method creates a new article and inserts it in the database
 * @param name the name of the article the user wants to create
 * @param nr the unique number og the article the user wants to create
 * @param price the price of the article the user wants to create
 * @param EAN the ean of the article the user wants to create
 * @param amount the amount of the article the user wants to create
 * @param colourCode the colour code of the article the user wants to create
 * @param categoryId the category id of the article the user wants to create
 * @param stockId the stock id
 * @param stateId the state id
 * @throws ArticleAlreadyExistsException 
 */

 public void insert (String name, String nr, double price,String EAN,
          int amount, String colourCode, int categoryId, int stockId, int stateId) throws ArticleAlreadyExistsException, ArticleDoesNotExistException            
   {
       
        if (!articleExists(nr)) 
        {
            List<Article> articles = update();
            

            EntityManager em = AppConfig.createEntityManager();
            em.getTransaction().begin();
            Category tempCategory = em.find(Category.class, categoryId);
            Stock tempStock = em.find(Stock.class, stockId);
            State tempState = em.find(State.class, stateId);

             int newID = -1;

            for (Article newArt : articles) 
            {
                if (newArt.getIdArticle() >= newID) 
                {
                    newID = newArt.getIdArticle();
                }
            }
            newID++;
            
            Article tempArticle = new Article(newID, name, nr, price, EAN, amount, colourCode);
            tempArticle.setStock(tempStock);
            tempArticle.setState(tempState);
            tempArticle.setCategory(tempCategory);

            System.out.println(tempArticle.getIdArticle());
            
            articles.add(tempArticle);

            persist(articles);
        }
        else
        {
            throw new ArticleAlreadyExistsException("The article with the number '"+nr+"' already exists.");
        }
    }
 
 /**
  * this method creates a new article into the database
  * @param article Article including all necessary arguments for method add() 
  * @throws ArticleAlreadyExistsException
  * @throws ArticleDoesNotExistException 
  */
  public void insert (Article article) throws ArticleAlreadyExistsException, ArticleDoesNotExistException            
  {
       
        if (!articleExists(article.getNr())) {
            List<Article> articles = update();
            

            EntityManager em = AppConfig.createEntityManager();
            em.getTransaction().begin();
            Category tempCategory = em.find(Category.class, article.getStock().getIdStock());


            int newID = -1;

            for (Article newArt : articles) {
                if (newArt.getIdArticle() >= newID) {
                    newID = newArt.getIdArticle();
                }
            }
            newID++;
            
            articles.add(new Article(newID, article.getName(), article.getNr(), article.getPrice(), article.getEan(), article.getAmount(), article.getColourCode()));

            persist(articles);
        }
        else
        {
            throw new ArticleAlreadyExistsException("The article with the number '" + article.getNr() + "' already exists.");
        }
    }
 
    /**
     * this method allows the user to completely delete an article out of the list
     * @param nr the unique number of the article the user want to delete
     * @throws ArticleDoesNotExistException 
     */
    public void delete(String nr) throws ArticleDoesNotExistException
    {
        if (articleExists(nr)) {
            List<Article> articles = update();
            for (Article article : articles) 
            {
                if (article.getNr().equals(nr)) 
                {
                    dbDelete(article);
                    return ;
                } 
            }
            
        }
        else
        {
            throw new ArticleDoesNotExistException("The article with the number '"+nr+"' does not exist.");
        }
      
    }
    /**
     * this method is used to increase the stock of an article by a certain amount, the user chooses
     * @param nr the unique nr of the article, the user wants to check in/increase
     * @param amount the amount the article should be increased by
     * @throws ArticleDoesNotExistException 
     */

    public void checkIn (String nr, int amount) throws ArticleDoesNotExistException
    {
        List<Article> articles = update();
        if(articleExists(nr))
        {
            for(Article article : articles)
            {
                if(article.getNr().equals(nr))
                {
                    int stock = article.getAmount() + amount;
                    article.setAmount(stock) ;
                }
            }
            persist(articles);
        }
        else
        {
            throw new ArticleDoesNotExistException("The article with the number '"+nr+"' does not exist.");
        }
    }
     /**
      * this method is used to decrease the stock of an article by a certain amount, the user chooses
      * @param nr the unique nr of the article, the user wants to check out/decrease 
      * @param newAmount the amount the user wants the article to be decreased by
      * @throws NotEnoughAmountException
      * @throws ArticleDoesNotExistException 
      */
    
    public void checkOut(String nr, int newAmount) throws NotEnoughAmountException, ArticleDoesNotExistException
    {

        List<Article> articles = update();
        if(articleExists(nr))
        {
            for(Article article : articles)
            {
                if(article.getNr().equals(nr))
                {
                    int amount = article.getAmount()- newAmount;
                    if(amount < 0)
                    {
                        throw new NotEnoughAmountException("There is not enough amount of the article with the number '"+nr+"'");
                    }
                    else
                    {
                        article.setAmount(amount);
                    }
                }
            }
            persist(articles);
        }
        else
        {
            throw new ArticleDoesNotExistException("The article '"+ nr +"' does not exist.");
        }
    }
    
     /**
     * this method delets one article from the data base
     */
    private void dbDelete(Article article)
    {
            EntityManager em = AppConfig.createEntityManager();
            em.getTransaction().begin();
            article = em.merge(article);
            
            em.remove(article);
            em.flush();
            em.getTransaction().commit();
    }
    
    
    
    /**
     * this method sets the articlelist equal to the articlelist from the data base
     * @return this returns the articles in the articlelist
     * ©Fat Don
     */
    
    private List<Article> update()
    {
        EntityManager em = AppConfig.createEntityManager();

        Query articleQuery = em.createNamedQuery("Article.findAll");
        List<Article> articles = articleQuery.getResultList();
        em.close();
        return articles;
    }
    /**
     * this method changes the old articlelist from the data base and replaces it with the list,
     * that has been changed by the method
     * @param articles this returns the articles in the articlelist
     */
    // List<Article> als Attribut übergebbar machen und diese liste in die db schreiben
    private void persist(List<Article> articles)
    {
        EntityManager em = AppConfig.createEntityManager();
         // TODO Holt euch den EntityManager von der AppConfig Klasse
        for (Article article : articles) 
        {
		System.out.println(article);
 
                em.getTransaction().begin();
                em.merge(article);
                em.getTransaction().commit();
	}
        System.out.println("Size: " + articles.size());
        em.close();
        
    }
    
   /**
   * this method is used in order to check whether an article
   * with the nr exists
   */
  private boolean articleExists(String nr) throws ArticleDoesNotExistException
  {
     EntityManager em = AppConfig.createEntityManager();

      Query articleQuery = em.createNamedQuery("Article.findByNr");
      articleQuery.setParameter("nr", nr);
      
      
     try
        {
            // Try to get exactly one user from the DB, with exactly that card's uid
            Article article = (Article) articleQuery.getSingleResult();
                                  
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

 
}
