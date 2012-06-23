/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.controller.RFIDLoginController;
import eu.gymnaila.chunks.artiver.controls.ModalWaitingDialog;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.exceptions.CardNotPresentException;
import eu.gymnaila.chunks.artiver.exceptions.UserNotFoundException;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javax.smartcardio.CardException;

/**
 *
 * @author Thunderbolt
 */
public class RFIDLogIn implements Initializable
{

    @FXML
    private Label lblRFIDLogInTitleLabel;
    @FXML
    private ProgressIndicator prgInd;
    @FXML
    private Button btnRFIDLogInRoger;
    private RFIDLoginController loginController = new RFIDLoginController();
    private Thread waitingWorker;
    private Task task;
    private String message = "";
    private String title = "";
    @FXML 
    private Button btnRFIDLogInBack;
    
    private DropShadow shadow = new DropShadow();
    
    
    @FXML
    private void btnActionRFIDLogInBack(ActionEvent event)
    {
        System.out.println("Zurück");

        if (task != null && task.isRunning())
        {
            task.cancel(true);
            try
            {
                waitingWorker.join();
            }
            catch (InterruptedException ex)
            {
                ModalWarningDialog md = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
                Logger.getLogger(RFIDLogIn.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        GuiPrototyp.getInstance().gotoLogInChoose();

    }

    @FXML
    private void btnActionRFIDLogInRoger(ActionEvent event)
    {
        btnRFIDLogInRoger.setDisable(true);

        task = new Task<Void>()
        {

            @Override
            public Void call()
            {

                try
                {
                    showSpinner();

                    if (loginController.validateCard())
                    {
                        gotoMain();
                    }
                    else
                    {
                        updateTitle("Fehler");
                        updateMessage("Problem beim Login.");

                        showMessages();

                    }

                    hideSpinner();
                }
                catch (CardNotPresentException ex)
                {
                    updateTitle("Fehler");
                    updateMessage("RFID-Karte wurde nicht erkannt.");
                    showMessages();
                }
                catch (CardException ex)
                {
                    updateTitle("Fehler");
                    updateMessage(ex.toString());
                    showMessages();
                }
                catch (UserNotFoundException ex)
                {
                    updateTitle("Fehler");
                    updateMessage("User wurde nicht gefunden.");
                    showMessages();
                }
                catch (Exception ex)
                {
                    updateTitle("Fehler");
                    updateMessage(ex.toString());
                    showMessages();
                }
                return null;
            }
        };


        task.titleProperty().addListener(new ChangeListener<String>()
        {

            @Override
            public void changed(ObservableValue<? extends String> observerable, String oldValue, String newValue)
            {
                title = newValue;
            }
        });

        task.messageProperty().addListener(new ChangeListener<String>()
        {

            @Override
            public void changed(ObservableValue<? extends String> observerable, String oldValue, String newValue)
            {
                message = newValue;
            }
        });

        waitingWorker = new Thread(task);
        waitingWorker.start();
    }

    private void gotoMain()
    {
         Platform.runLater(new Runnable()
        {

            @Override
            public void run()
            {
                GuiPrototyp.getInstance().gotoMainFrame();
            }
        });
    }

    private void showSpinner()
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                prgInd.setVisible(true);
            }
        });
    }

    private void hideSpinner()
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                prgInd.setVisible(false);
            }
        });
    }

    private void showMessages()
    {
        Platform.runLater(new Runnable()
        {

            @Override
            public void run()
            {
                ModalWarningDialog md = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), title, message);
                Logger.getLogger(RFIDLogIn.class.getName()).log(Level.SEVERE, message);

                // Zu Testzwecken momentan hier trotzdem zum MainFrame weiterleiten
                // TODO vor Release entfernen!!!
                //GuiPrototyp.getInstance().gotoMainFrame();
            }
        });


    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        btnRFIDLogInRoger.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnRFIDLogInRoger.setEffect(shadow);
            }
        
       
        });
        btnRFIDLogInRoger.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnRFIDLogInRoger.setEffect(null);
            }
        
       
        });
        btnRFIDLogInBack.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnRFIDLogInBack.setEffect(shadow);
            }
        
       
        });
        btnRFIDLogInBack.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnRFIDLogInBack.setEffect(null);
            }
        
       
        });
    }
}
