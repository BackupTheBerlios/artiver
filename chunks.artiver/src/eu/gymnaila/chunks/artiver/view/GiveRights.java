/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.controller.GroupsController;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.controls.ModalYesNoDialog;
import eu.gymnaila.chunks.artiver.entity.Article;
import eu.gymnaila.chunks.artiver.entity.Groups;
import eu.gymnaila.chunks.artiver.exceptions.GroupConnectedToUserException;
import eu.gymnaila.chunks.artiver.exceptions.GroupsAlreadyExistException;
import eu.gymnaila.chunks.artiver.exceptions.GroupsNotFoundException;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.javafxdata.datasources.protocol.ObjectDataSource;

/**
 *
 * @author Thunderbolt
 */
public class GiveRights implements Initializable {
    
    
    @FXML
    private TextField txtGiveRightsName;
    @FXML
    private ListView lstGiveRights;
    @FXML
    private VBox vBoxGiveRightsList;
    @FXML
    private Button btnGiveRightsErease;
    @FXML
    private Button btnGiveRightsChange;
    @FXML
    private Button btnGiveRightsRoger;
    
    private DropShadow shadow = new DropShadow();
    
    private Groups editGroup = null;
    
    private TableView<Groups> mainTable;
    
    private GroupsController groups = new GroupsController();
    
    private ObjectDataSource ds;
    
    private ObservableList<Groups> tempList = FXCollections.observableArrayList();
    
    
    @FXML
    private void btnActionGiveRightsRoger(ActionEvent event) 
    {
        try {
            System.out.println("Rechte Bestätigen");
            
            Groups newGroup = new Groups();
            
            if(editGroup != null)
            {
                newGroup = editGroup;
                
                editGroup.setCheckIn(Boolean.FALSE);
                editGroup.setCheckOut(Boolean.FALSE);
                editGroup.setArticle(Boolean.FALSE);
                editGroup.setCategory(Boolean.FALSE);
                editGroup.setStock(Boolean.FALSE);
                editGroup.setState(Boolean.FALSE);
                editGroup.setInvoice(Boolean.FALSE);
                editGroup.setOffer(Boolean.FALSE);
                editGroup.setDeliveryNote(Boolean.FALSE);
                editGroup.setGroups(Boolean.FALSE);
                editGroup.setUser(Boolean.FALSE);
                editGroup.setCustomer(Boolean.FALSE);
                editGroup.setMasterData(Boolean.FALSE);
                editGroup.setAdmin(Boolean.FALSE);
                editGroup.setVoc(Boolean.FALSE);

            }
            
            ObservableList<String> selRights = lstGiveRights.getSelectionModel().getSelectedItems();
            
            if(!txtGiveRightsName.getText().equals(""))
            {
                newGroup.setName(txtGiveRightsName.getText());

                for(String right : selRights)
                {
                    switch(right)
                    {
                        case "Check In":
                            newGroup.setCheckIn(Boolean.TRUE);
                            break;
                        case "Check Out":
                            newGroup.setCheckOut(Boolean.TRUE);
                            break;
                        case "Artikel editieren":
                            newGroup.setArticle(Boolean.TRUE);
                            break;
                        case "Lagerort editieren":
                            newGroup.setStock(Boolean.TRUE);
                            break;
                        case "Kategorie editieren":
                            newGroup.setCategory(Boolean.TRUE);
                            break;
                        case "Status editieren":
                            newGroup.setState(Boolean.TRUE);
                            break;
                        case "Rechnung":
                            newGroup.setInvoice(Boolean.TRUE);
                            break;
                        case "Angebot":
                            newGroup.setOffer(Boolean.TRUE);
                            break;
                        case "Lieferschein":
                            newGroup.setDeliveryNote(Boolean.TRUE);
                            break;
                        case "Kunden":
                            newGroup.setCustomer(Boolean.TRUE);
                            break;
                        case "Gruppen editieren":
                            newGroup.setGroups(Boolean.TRUE);
                            break;
                        case "Nutzer editieren":
                            newGroup.setUser(Boolean.TRUE);
                            break;
                        case "Administrator (alle Rechte)":
                            newGroup.setAdmin(Boolean.TRUE);
                            break;

                    }
                }

                if(editGroup == null)
                {
                    groups.addGroups(newGroup);               
                }
                else
                {
                    groups.edit(newGroup);
                    editGroup = null;
                }
                
                
                ds = new ObjectDataSource(groups.list(), Groups.class, "name", "checkIn", "checkOut", "article", "category", "stock", "state", "invoice", "offer", "deliveryNote", "groups", "user", "customer", "admin");
                mainTable.setItems(tempList);
                mainTable.setItems(ds.getData());
                mainTable.layout();
                
                txtGiveRightsName.setText("");
                lstGiveRights.getSelectionModel().clearSelection();
                
            }
            else
            {
                ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", "Bitte Gruppennamen angeben");
            }
        } catch (GroupsNotFoundException ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        } catch (GroupsAlreadyExistException ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
        
    }
    
    @FXML
    private void btnActionGiveRightsChange(ActionEvent event) 
    {
        System.out.println("Rechte Ändern");
    
        editGroup = mainTable.getSelectionModel().getSelectedItem();
            
        SelectionModel selModel = lstGiveRights.getSelectionModel();
            
        Groups curUsersGroup = editGroup;
                
        selModel.clearSelection();
        
        if(curUsersGroup.getAdmin())
        {
            selModel.select("Administrator (alle Rechte)");
        }
        else
        {
            if(curUsersGroup.getArticle())
            {
                selModel.select("Artikel editieren");
            }
            if(curUsersGroup.getCategory())
            {
                selModel.select("Kategorie editieren");
            }
            if(curUsersGroup.getCheckIn())
            {           
                selModel.select("Check In");
            }
            if(curUsersGroup.getCheckOut())
            {              
                selModel.select("Check Out");
            }
            if(curUsersGroup.getCustomer())
            {
                selModel.select("Kunden");
            }
            if(curUsersGroup.getDeliveryNote())
            {
                selModel.select("Lieferschein");
            }            
            if(curUsersGroup.getGroups())
            {
                selModel.select("Gruppen editieren");
            }
            if(curUsersGroup.getInvoice())
            {
                selModel.select("Rechnung");
            }
            if(curUsersGroup.getMasterData())
            {
                selModel.select("Stammdaten");
            }
            if(curUsersGroup.getOffer())
            {
                selModel.select("Angebot");
            }
            if(curUsersGroup.getState())
            {              
                selModel.select("Status editieren");
            }
            if(curUsersGroup.getStock())
            {
                selModel.select("Lagerort editieren");
            }
            if(curUsersGroup.getUser())
            {
                selModel.select("Nutzer editieren");
            }
        } 
        
        txtGiveRightsName.setText(curUsersGroup.getName());
    }
    
    @FXML
    private void btnActionGiveRightsErease(ActionEvent event) 
    {
        System.out.println("Rechte Löschen");
        
        ModalYesNoDialog yesNo = new ModalYesNoDialog(GuiPrototyp.getInstance().getStage(), "Warnung", "Wollen sie wirklich löschen?");
        EventHandler<ActionEvent> ehaeYes = new EventHandler<ActionEvent>() 
        {

            @Override
            public void handle(ActionEvent arg0) 
            {
        
        
                    try 
                    {
                        groups.deleteGroups(mainTable.getSelectionModel().getSelectedItem());
            
                        // Update TableView
                        ds = new ObjectDataSource(groups.list(), Groups.class, "name", "checkIn", "checkOut", "article", "category", "stock", "state", "invoice", "offer", "deliveryNote", "groups", "user", "customer", "admin");
                        mainTable.setItems(tempList);
                        mainTable.setItems(ds.getData());
                        mainTable.layout();
                        } 
                        catch (GroupsNotFoundException ex)  
                        {
                            Logger.getLogger(GiveRights.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                        catch (GroupConnectedToUserException ex) 
                        {
                        Logger.getLogger(GiveRights.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
            }
        };
        
        yesNo.setOnYes(ehaeYes);
        yesNo.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        btnGiveRightsErease.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnGiveRightsErease.setEffect(shadow);
            }
        
       
        });
        btnGiveRightsErease.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnGiveRightsErease.setEffect(null);
            }
        
       
        });
        
        btnGiveRightsChange.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnGiveRightsChange.setEffect(shadow);
            }
        
       
        });
        btnGiveRightsChange.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnGiveRightsChange.setEffect(null);
            }
        
       
        });
        
        btnGiveRightsRoger.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnGiveRightsRoger.setEffect(shadow);
            }
        
       
        });
        btnGiveRightsRoger.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnGiveRightsRoger.setEffect(null);
            }
        
       
        });
        
        
        
        mainTable = new TableView<Groups>();
        try
        {
            ds = new ObjectDataSource(groups.list(), Groups.class, "name", "checkIn", "checkOut", "article", "category", "stock", "state", "invoice", "offer", "deliveryNote", "groups", "user", "customer", "admin");
            mainTable.setItems(ds.getData());
            mainTable.getColumns().addAll(ds.getColumns());
            mainTable.prefHeight(200);
            mainTable.minHeight(200);
            
            if(!vBoxGiveRightsList.getChildren().isEmpty())
            {
                vBoxGiveRightsList.getChildren().clear();
            }
            
            vBoxGiveRightsList.getChildren().add(mainTable);
        }
        catch(Exception e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
        
        ObservableList<String> groupList = FXCollections.observableArrayList("Administrator (alle Rechte)", "Check In", "Check Out", "Artikel editieren", "Lagerort editieren", "Kategorie editieren", "Status editieren", "Rechnung", "Angebot", "Lieferschein", "Kunden", "Gruppen editieren", "Nutzer editieren", "Stammdaten");
        lstGiveRights.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lstGiveRights.setItems(groupList);
        
    }    
    
}
