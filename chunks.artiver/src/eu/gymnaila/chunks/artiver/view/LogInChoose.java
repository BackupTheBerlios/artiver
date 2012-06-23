/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Thunderbolt
 */
public class LogInChoose implements Initializable {
    
    @FXML
    private Label lblLogInUser;
    @FXML
    private Button btnLogInUser;
    @FXML
    private Button btnLogInRFID;
            
    
    DropShadow shadow = new DropShadow();
    
    
    @FXML
    private void logInActionRFID(ActionEvent event) {
        System.out.println("RFID LogIn");
        GuiPrototyp.getInstance().gotoRFIDLogIn();
        
    }
    
    @FXML
    private void logInActionUser(ActionEvent event) {
        System.out.println("User LogIn");
        GuiPrototyp.getInstance().gotoUserLogIn();
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        
//Adding the shadow when the mouse cursor is on
        btnLogInUser.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnLogInUser.setEffect(shadow);
            }
        
       
        });
        btnLogInUser.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnLogInUser.setEffect(null);
            }
        
       
        });
        
        btnLogInRFID.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnLogInRFID.setEffect(shadow);
            }
        
       
        });
        btnLogInRFID.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnLogInRFID.setEffect(null);
            }
        
       
        });
        

    
    
    
    }
}
