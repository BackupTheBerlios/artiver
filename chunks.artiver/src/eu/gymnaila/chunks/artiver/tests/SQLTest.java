/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.tests;

import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.entity.Article;
import eu.gymnaila.chunks.artiver.entity.Customer;
import eu.gymnaila.chunks.artiver.entity.Offer;
import eu.gymnaila.chunks.artiver.entity.Stock;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Matze
 */
public class SQLTest {
private static final String PERSISTENCE_UNIT_NAME = "ArtiVerPU";

        // Die Factory sollte in jedem eurer Controller auch zu finden sein (nur nicht mit static-Flag!)
	private static EntityManagerFactory factory;

	public static void main(String[] args) {
       
            try {
            AppConfig.init();
                /*---------------------------------------------------------------------*/
                /*---------------Ab hier für Dennis wichtig!---------------------------*/
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SQLTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SQLTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SQLTest.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(-1);
        }
                
                // Factory für den DB-Zugriff erstellen
		EntityManager em = AppConfig.createEntityManager();
            
                Query articleQuery = em.createNamedQuery("Article.findAll");
                List<Article> articles = articleQuery.getResultList();
   
                List<Offer> offers = new ArrayList<Offer>();
                int index = articles.size()/2;
                
                List<Article> tempArticles = articles.subList(0, index);
                
                List<Article> tempArticles2 = articles.subList(index, articles.size());
                
//                Offer tempOffer1 = new Offer(1, "O1234567", 2.99, tempArticles);
//                Offer tempOffer2 = new Offer(2, "O1234568", 2.98, tempArticles2);
//                Offer tempOffer3 = new Offer(3, "O1234569", 2.97, articles);
//                
//                tempOffer1.setCustomerId(em.find(Customer.class, 1 ));
//                tempOffer2.setCustomerId(em.find(Customer.class, 1 ));
//                tempOffer3.setCustomerId(em.find(Customer.class, 1 ));
//                                
//                offers.add(tempOffer1);
//                offers.add(tempOffer2);
//                offers.add(tempOffer3);
//                
//                
      
         // TODO Holt euch den EntityManager von der AppConfig Klasse
        for (Offer offer : offers) 
        {
		System.out.println(offer);
 
                em.getTransaction().begin();
                em.merge(offer);
                em.getTransaction().commit();
	}
        System.out.println("Size: " + offers.size());
        em.close();
        System.exit(0);
                
                
                //Liste zurückgeben
                //return stockList;
                
                //--------------Bis hierhin geht die update()-Methode.
                
                
//----------------------- Das kommt später in eine Methode, die die Liste wieder in die DB schreibt----                
//                
//		for (Stock stock : stockList) {
//			System.out.println(stock);
//                        stock.setAddress("Selbitz");
//                        em.getTransaction().begin();
//                        em.persist(stock);
//                        em.getTransaction().commit();
//		}
//                Stock stock = new Stock(4,"Lager 4", "SA", "Fort Jackson");
//                em.getTransaction().begin();
//                        em.persist(stock);
//                        em.getTransaction().commit();
//                
//		System.out.println("Size: " + stockList.size());
//                em.close();
	}
}
