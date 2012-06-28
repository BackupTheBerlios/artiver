/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.controller.GroupsController;
import eu.gymnaila.chunks.artiver.controller.RFIDLoginController;
import eu.gymnaila.chunks.artiver.controller.Usermanagement;
import eu.gymnaila.chunks.artiver.controls.ArtiVerContextMenu;
import eu.gymnaila.chunks.artiver.controls.ModalWaitingDialog;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.encryption.EncMode;
import eu.gymnaila.chunks.artiver.encryption.ShaEncrypter;
import eu.gymnaila.chunks.artiver.entity.Groups;
import eu.gymnaila.chunks.artiver.entity.User;
import eu.gymnaila.chunks.artiver.exceptions.CardNotPresentException;
import eu.gymnaila.chunks.artiver.exceptions.UserAlreadyExistsException;
import eu.gymnaila.chunks.artiver.exceptions.UserNotFoundException;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javax.smartcardio.CardException;
import org.javafxdata.datasources.protocol.ObjectDataSource;

/**
 *
 * @author Thunderbolt
 */
public class UsersFrame implements Initializable {
    
    private TableView<User> mainTable;
    
    private Usermanagement user = new Usermanagement();
    
    private GroupsController groups = new GroupsController();
    
    private ObjectDataSource ds;
    
    private ObservableList<User> tempList = FXCollections.observableArrayList();
    
    private User tempUser = null;
    
    @FXML
    private VBox vBoxUsersFrameList;
    @FXML
    private TextField txtUsersFrameRFID;
    @FXML 
    private ListView<Groups> lstUsersFrame;
    @FXML
    private TextField txtUsersFrameName;
    @FXML
    private TextField txtUsersFrameNumber;
    @FXML
    private PasswordField pwfUsersFramePW;
    @FXML
    private PasswordField pwfUsersFramePWconf;
    @FXML 
    private Button btnUsersFrameRoger;
    @FXML 
    private Button btnUsersFrameErease;
    @FXML 
    private Button btnUsersFrameChange;
    @FXML
    private Button btnUsersFrameRFID;
    
    private DropShadow shadow = new DropShadow();
    
    private ModalWaitingDialog wait;
   
    
    @FXML
    private void btnActionUsersFrameRoger(ActionEvent event) {
        try {
            System.out.println("Bestätiger User");
            
            // Encrypter to encrypt the uid string
            ShaEncrypter enc512 = new ShaEncrypter(EncMode.SHA512);
            
            String name = txtUsersFrameName.getText();
            Groups grp = lstUsersFrame.getSelectionModel().getSelectedItem();
            String pw = enc512.encryptString(pwfUsersFramePW.getText());
            String pwConf = enc512.encryptString(pwfUsersFramePWconf.getText());
            int persID = Integer.parseInt(txtUsersFrameNumber.getText());
            String uid = enc512.encryptString(txtUsersFrameRFID.getText());
                 
            if (tempUser == null)
            {
                if(pw.equals("") && uid.equals(""))
                {
                    new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", "Bitte Passwort oder RFID UID eingeben");
                }
                else
                {
                    if(pw.equals(pwConf))
                    {
                            try {
                                user.addUser(name, grp, pw, uid, persID);
                                txtUsersFrameRFID.setText("");
                                lstUsersFrame.getSelectionModel().clearSelection();
                                txtUsersFrameName.setText("");
                                txtUsersFrameNumber.setText("");
                                pwfUsersFramePW.setText("");
                                pwfUsersFramePWconf.setText("");
                            } catch (UserAlreadyExistsException ex) {
                                new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", "Benutzer existiert bereits");
                                ex.printStackTrace();
                            }

                    }
                    else
                    {
                        new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", "Bitte Passwort in beiden Feldern korrekt eingeben");
                    }
                }
            }
            else
            {
                if((pwfUsersFramePW.getText().equals("") && pwfUsersFramePWconf.getText().equals("")) || (pw.equals(pwConf)) )
                {
                        tempUser.setName(name);
                        tempUser.setPersonalNr(persID);
                        tempUser.setGroups(grp);
                        if (!pwfUsersFramePW.getText().equals("") && pwConf.equals(pw))
                        {
                            tempUser.setPassword(pw);
                        }
                        
                        if (!txtUsersFrameRFID.getText().equals(""))
                        {
                            tempUser.setUid(uid);
                        }
                        
                        try {
                            user.edit(tempUser);
                            
                            txtUsersFrameRFID.setText("");
                            lstUsersFrame.getSelectionModel().clearSelection();
                            txtUsersFrameName.setText("");
                            txtUsersFrameNumber.setText("");
                            pwfUsersFramePW.setText("");
                            pwfUsersFramePWconf.setText("");
                        } catch (UserNotFoundException ex) {
                            new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", "Benutzer wurde nicht gefunden");
                            ex.printStackTrace();
                        }
                        finally
                        {
                            tempUser = null;
                        }
                }
                else
                {
                    new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", "Bitte Passwort in beiden Feldern korrekt eingeben");
                }
                
            }
            
            ds = new ObjectDataSource(user.list(), User.class, "name", "personalNr", "groups");
            mainTable.setItems(tempList);
            mainTable.setItems(ds.getData());
            mainTable.layout();
            
        } catch (NoSuchAlgorithmException ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
        
                        ds = new ObjectDataSource(user.list(), User.class, "name", "personalNr");
                        mainTable.setItems(tempList);
                        mainTable.setItems(ds.getData());
                        mainTable.layout();
    }
    
    @FXML
    private void btnActionUsersFrameChange(ActionEvent event) {
        System.out.println("Ändere User");
        
        tempUser = mainTable.getSelectionModel().getSelectedItem();
        
        txtUsersFrameName.setText(tempUser.getName());
        
        
        txtUsersFrameNumber.setText(String.valueOf(tempUser.getPersonalNr()));
        
        
        
        lstUsersFrame.getSelectionModel().select(tempUser.getGroups());
        //txtUsersFrameRFID;
    
    }
    
    @FXML
    private void btnActionUsersFrameErease(ActionEvent event) 
    {
        try {
            System.out.println("Lösche User");
            user.deleteUser(mainTable.getSelectionModel().getSelectedItem().getIdUser());

            ds = new ObjectDataSource(user.list(), User.class, "name", "personalNr", "groups");
            mainTable.setItems(tempList);
            mainTable.setItems(ds.getData());
            mainTable.layout();

        } catch (UserNotFoundException e) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
            
            
            
    
    }
    
    @FXML
    private void btnActionUsersFrameRFID(ActionEvent event) {
        try {
            System.out.println("Scanne Karte");
            RFIDLoginController rfLog = new RFIDLoginController();
            showSpinner();
            rfLog.getCardUid();
            hideSpinner();
            
        } catch (CardException ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        } catch (UserNotFoundException ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        } catch (CardNotPresentException ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        } catch (Exception ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        btnUsersFrameErease.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnUsersFrameErease.setEffect(shadow);
            }
        
       
        });
        btnUsersFrameErease.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnUsersFrameErease.setEffect(null);
            }
        
       
        });
        
        btnUsersFrameRoger.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnUsersFrameRoger.setEffect(shadow);
            }
        
       
        });
        btnUsersFrameRoger.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnUsersFrameRoger.setEffect(null);
            }
        
       
        });
        
        btnUsersFrameRoger.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnUsersFrameRoger.setEffect(shadow);
            }
        
       
        });
        btnUsersFrameRFID.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnUsersFrameRFID.setEffect(null);
            }
        
       
        });
        
        btnUsersFrameRFID.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnUsersFrameRFID.setEffect(shadow);
            }
        
       
        });
        btnUsersFrameChange.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnUsersFrameChange.setEffect(null);
            }
        
       
        });
        
        
        
        lstUsersFrame.setItems(FXCollections.observableArrayList(groups.list()));
        
        txtUsersFrameRFID.setEditable(false);
        
        mainTable = new TableView<User>();
        try
        {
            ds = new ObjectDataSource(user.list(), User.class, "name", "personalNr", "groups");
            mainTable.setItems(ds.getData());
            mainTable.getColumns().addAll(ds.getColumns());
            mainTable.prefHeight(200);
            mainTable.minHeight(200);
            
            if(!vBoxUsersFrameList.getChildren().isEmpty())
            {
                vBoxUsersFrameList.getChildren().clear();
            }
            
            vBoxUsersFrameList.getChildren().add(mainTable);
            
            mainTable.setContextMenu(new ArtiVerContextMenu("UserListRep"));
        }
        catch(Exception e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
    }   
    
     private void showSpinner()
    {
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                wait = new ModalWaitingDialog(GuiPrototyp.getInstance().getStage(), "RFID Karte", "Bitte Karte an den Leser halten");
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
                wait.close();
            }
        });
    }
    
}
