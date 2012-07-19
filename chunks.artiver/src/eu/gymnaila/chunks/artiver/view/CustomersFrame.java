/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.controller.CustomerController;
import eu.gymnaila.chunks.artiver.controller.GroupsController;
import eu.gymnaila.chunks.artiver.controller.RFIDLoginController;
import eu.gymnaila.chunks.artiver.controller.Usermanagement;
import eu.gymnaila.chunks.artiver.controls.ArtiVerContextMenu;
import eu.gymnaila.chunks.artiver.controls.ModalWaitingDialog;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.controls.ModalYesNoDialog;
import eu.gymnaila.chunks.artiver.encryption.EncMode;
import eu.gymnaila.chunks.artiver.encryption.ShaEncrypter;
import eu.gymnaila.chunks.artiver.entity.Customer;
import eu.gymnaila.chunks.artiver.entity.Groups;
import eu.gymnaila.chunks.artiver.entity.User;
import eu.gymnaila.chunks.artiver.exceptions.*;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import eu.gymnaila.chunks.artiver.reports.ReportTemplate;
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
public class CustomersFrame implements Initializable {
    
    private TableView<Customer> mainTable;
    
    private CustomerController customer = new CustomerController();
    
    //private GroupsController groups = new GroupsController();
    
    private ObjectDataSource ds;
    
    private ObservableList<Customer> tempList = FXCollections.observableArrayList();
    
    
    
    @FXML
    private VBox vBoxCustomersFrameList;
    @FXML
    private TextField txtCustomersFrameName;
    @FXML
    private TextField txtCustomersFrameCustomer;
    @FXML
    private TextField txtCustomersFrameAddress;
    @FXML 
    private TextField txtCustomersFrameCustomerID;
    @FXML 
    private Button btnCustomersFrameRoger;
    @FXML 
    private Button btnCustomersFrameErease;
    @FXML 
    private Button btnCustomersFrameChange;
    @FXML
    private ScrollPane scrCustomers;
    
    private Customer tempCust = null;
    
    
    private DropShadow shadow = new DropShadow();
    
    private ModalWaitingDialog wait;
   
    
    @FXML
    private void btnActionCustomersFrameRoger(ActionEvent event) 
    {
            String cust = txtCustomersFrameCustomer.getText();
            String address = txtCustomersFrameAddress.getText();
            String customerId = txtCustomersFrameCustomerID.getText();
            
            if(tempCust == null)
            {
            
            
            
            
                    try {
                            customer.addCustomer(customerId, cust, address);
        
                            ds = new ObjectDataSource(customer.list(), Customer.class, "customerNr", "customer", "address");
                                mainTable.setItems(tempList);
                                mainTable.setItems(ds.getData());
                                mainTable.layout();
        
                        } 
                        catch (CustomerAlreadyExistsException ex) 
                        {
                            Logger.getLogger(CustomersFrame.class.getName()).log(Level.SEVERE, null, ex);
                        }
        
                            txtCustomersFrameCustomer.clear();
                            txtCustomersFrameAddress.clear();
                            txtCustomersFrameCustomerID.clear();
      
            }
            else
            {
          
                tempCust.setCustomer(cust);
                tempCust.setCustomerNr(customerId);
                tempCust.setAddress(address);
          
                try
                {
                    customer.edit(tempCust);
                    
                                ds = new ObjectDataSource(customer.list(), Customer.class, "customerNr", "customer", "address");
                                mainTable.setItems(tempList);
                                mainTable.setItems(ds.getData());
                                mainTable.layout();
                                
                                txtCustomersFrameCustomer.clear();
                                txtCustomersFrameAddress.clear();
                                txtCustomersFrameCustomerID.clear();
      
                    
                } catch (CustomerNotFoundException ex) 
                {
                Logger.getLogger(CustomersFrame.class.getName()).log(Level.SEVERE, null, ex);
            
                }
                finally
                {
                    tempCust = null;
                }
                
                
            }
            
            
    }
    
    @FXML
    private void btnActionCustomersFrameChange(ActionEvent event) 
    {
        
        tempCust = mainTable.getSelectionModel().getSelectedItem();
        
        txtCustomersFrameCustomer.setText(tempCust.getCustomer());
        
        txtCustomersFrameCustomerID.setText(tempCust.getCustomerNr());
        
        txtCustomersFrameAddress.setText(tempCust.getAddress());
        
    }
    
    
    @FXML
    private void btnActionCustomersFrameErease(ActionEvent event) 
    {
       
      ModalYesNoDialog yesNo = new ModalYesNoDialog(GuiPrototyp.getInstance().getStage(), "Warnung", "Wollen sie wirklich löschen?");
        EventHandler<ActionEvent> ehaeYes = new EventHandler<ActionEvent>() 
        {

            @Override
            public void handle(ActionEvent arg0) 
            {
          
        
                    try {
                            customer.deleteCustomer(mainTable.getSelectionModel().getSelectedItem().getIdCustomer());
            
                                ds = new ObjectDataSource(customer.list(), Customer.class, "customerNr", "customer", "address");
                                mainTable.setItems(tempList);
                                mainTable.setItems(ds.getData());
                                mainTable.layout();
                        
                        } 
                        catch (CustomerNotFoundException e) 
                        {
                        ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
                        e.printStackTrace();
                        }
      
            }
      };
        
      yesNo.setOnYes(ehaeYes);
      yesNo.show();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        
        mainTable = new TableView<Customer>();
        try
        {
            ds = new ObjectDataSource(customer.list(), Customer.class, "customerNr", "customer", "address");
            mainTable.setItems(ds.getData());
            mainTable.getColumns().addAll(ds.getColumns());
            mainTable.prefHeight(200);
            mainTable.minHeight(200);
            
            if(!vBoxCustomersFrameList.getChildren().isEmpty())
            {
                vBoxCustomersFrameList.getChildren().clear();
            }
            
            vBoxCustomersFrameList.getChildren().add(mainTable);
            
            mainTable.setContextMenu(new ArtiVerContextMenu(ReportTemplate.STANDARD.toString(), "Kundenliste", ds, null));
        }
        catch(Exception e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
        
        btnCustomersFrameRoger.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnCustomersFrameRoger.setEffect(shadow);
            }
        
       
        });
        btnCustomersFrameRoger.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnCustomersFrameRoger.setEffect(null);
            }
        
       
        });
        
        btnCustomersFrameErease.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnCustomersFrameErease.setEffect(shadow);
            }
        
       
        });
        btnCustomersFrameErease.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnCustomersFrameErease.setEffect(null);
            }
        
       
        });
        
        btnCustomersFrameChange.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnCustomersFrameChange.setEffect(shadow);
            }
        
       
        });
        btnCustomersFrameChange.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnCustomersFrameChange.setEffect(null);
            }
        
       
        });
                
    }   
    
    
    
}




   
            
            
    
    