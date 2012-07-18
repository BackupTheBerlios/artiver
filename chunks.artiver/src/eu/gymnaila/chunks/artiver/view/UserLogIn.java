/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.controller.Usermanagement;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.encryption.EncMode;
import eu.gymnaila.chunks.artiver.encryption.ShaEncrypter;
import eu.gymnaila.chunks.artiver.entity.User;
import eu.gymnaila.chunks.artiver.exceptions.UserNotFoundException;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Thunderbolt
 */
public class UserLogIn implements Initializable 
{
    
    @FXML
    private TextField txtUserLogInUser;
    @FXML
    private PasswordField txtUserLogInPW;
    @FXML 
    private Button btnUserLogInRoger;
    @FXML
    private Button btnUserLogInBack;
    
    private DropShadow shadow = new DropShadow();
    
    
    private void checkKeyPressed(KeyEvent ke, KeyCode kc)
    {
       if(ke.getCode() == kc)
       {
         btnActionUserLogInRoger(new ActionEvent());
       }
    }
    
    @FXML
    private void btnActionUserLogInRoger(ActionEvent event) 
    {
        System.out.println("Bestätige LogIn");
        
        EntityManager em;
        User user;
        
        // Create new EntityManager for the DB operations
        em = AppConfig.createEntityManager();

        // Create query and set parameter uid
        Query userQuery = em.createNamedQuery("User.findByName");
        userQuery.setParameter("name", txtUserLogInUser.getText());

        try
        {
            // Try to get exactly one user from the DB, with exactly that card's uid
            user = (User) userQuery.getSingleResult();
            
            // If no result is found, throw exception
            // Just a safety mechanism, if NoResultException does not fire
            if(user == null)
            {
               throw new UserNotFoundException();
            }
            
             // Encrypter to encrypt the uid string
            ShaEncrypter enc512 = new ShaEncrypter(EncMode.SHA512);
            
            if(user.getPassword().equals(enc512.encryptString(txtUserLogInPW.getText())))
            {                
               // Assign the correct user to the current user field
                AppConfig.setUser(user);
                AppConfig.setCurPassword(txtUserLogInPW.getText());
                GuiPrototyp.getInstance().gotoMainFrame();
            }
            else
            {
                throw new UserNotFoundException();
            }
        }
        catch (NoResultException e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
            System.out.println("MÄÄÄÄÄÄÄP");
           // throw new UserNotFoundException();

        }
        catch(Exception e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
        finally
        {
            // Remember: Do EVER close the EntityManager you created!!
            em.close();
            GuiPrototyp.getInstance().gotoMainFrame();
        }

        
        
        
        
        
    }
    
    @FXML
    private void btnActionUserLogInBack(ActionEvent event) 
    {
        System.out.println("Zurück");
        GuiPrototyp.getInstance().gotoLogInChoose();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        btnUserLogInRoger.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnUserLogInRoger.setEffect(shadow);
            }
        
       
        });
        btnUserLogInRoger.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnUserLogInRoger.setEffect(null);
            }
        
       
        });

        txtUserLogInPW.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() 
        {
            @Override 
            public void handle(KeyEvent e) 
            {
                checkKeyPressed(e, KeyCode.ENTER);
            }
        
       
        });
        
        txtUserLogInUser.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() 
        {
            @Override
            public void handle(KeyEvent e) 
            {
                checkKeyPressed(e, KeyCode.ENTER);
            }
        
       
        });
        
        
        btnUserLogInBack.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnUserLogInBack.setEffect(shadow);
            }
        
       
        });
        btnUserLogInBack.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnUserLogInBack.setEffect(null);
            }
        
       
        });
    }    
}
