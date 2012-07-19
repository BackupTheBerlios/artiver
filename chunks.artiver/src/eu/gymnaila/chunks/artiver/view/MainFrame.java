/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.entity.Groups;
import eu.gymnaila.chunks.artiver.entity.User;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 *
 * @author Thunderbolt
 */
public class MainFrame implements Initializable {
    
    @FXML private VBox vBoxMain;
    
    @FXML
    private Label lblMainFrameTitleLabel;
    
    @FXML
    private Accordion accMainFrame;
    
    @FXML
    private Button btnAccMainFrameCheckIn;
    @FXML
    private Button btnAccMainFrameCheckOut;
    @FXML
    private Button btnAccMainFrameInsert;
    @FXML
    private Button btnAccMainFrameList;
    @FXML
    private Button btnAccMainFrameStock;
    @FXML
    private Button btnAccMainFrameState;
    @FXML
    private Button btnAccMainFrameCategory;
    @FXML
    private Button btnAccMainFrameGiveRights;
    @FXML
    private Button btnAccMainFrameUsers;
    @FXML
    private Button btnAccMainFrameSettings;
    @FXML
    private Button btnAccMainFrameInvoice;
    @FXML
    private Button btnAccMainFrameInvoiceList;
    @FXML
    private Button btnAccMainFrameOffer;
    @FXML
    private Button btnAccMainFrameDelivery;
    @FXML
    private Button btnAccMainFrameCustomer;
    
    private DropShadow shadow = new DropShadow();
            
    
 
    @FXML
    private void btnActionAccMainFrameCheckIn(ActionEvent event) {
        System.out.println("CheckIn");
        lblMainFrameTitleLabel.setText("Artikel einchecken");
        GuiPrototyp.getInstance().buildCheckIn(vBoxMain);
        
    }
    
    @FXML
    private void btnActionAccMainFrameCheckOut(ActionEvent event) {
        System.out.println("CheckOut");
        lblMainFrameTitleLabel.setText("Artikel auschecken");
        GuiPrototyp.getInstance().buildCheckOut(vBoxMain);
    }
 
    @FXML
    private void btnActionAccMainFrameList(ActionEvent event) {
        System.out.println("Liste");
        lblMainFrameTitleLabel.setText("Artikelliste");
        GuiPrototyp.getInstance().buildListFrame(vBoxMain);
    }
       
    @FXML
    private void btnActionAccMainFrameInsert(ActionEvent event) {
        System.out.println("Einfügen");
        lblMainFrameTitleLabel.setText("Artikel erstellen");
        GuiPrototyp.getInstance().buildInsertFrame(vBoxMain);
    }
    
    
    
    
    @FXML
    private void btnActionAccMainFrameGiveRights(ActionEvent event) {
        System.out.println("Rechte editieren");
        lblMainFrameTitleLabel.setText("Gruppen editieren");
        GuiPrototyp.getInstance().buildGiveRights(vBoxMain);
    }
    
    @FXML
    private void btnActionAccMainFrameMakeUsers(ActionEvent event) {
        System.out.println("Nutzer erstellen");
        lblMainFrameTitleLabel.setText("Nutzer editieren");
        GuiPrototyp.getInstance().buildUsersFrame(vBoxMain);
    }
    
    
    
    
    @FXML
    private void btnActionAccMainFrameCategory(ActionEvent event) {
        System.out.println("Kategorie editieren");
        lblMainFrameTitleLabel.setText("Kategorie editieren");
        GuiPrototyp.getInstance().buildCategoryList(vBoxMain);
    }
   
        
    @FXML
    private void btnActionAccMainFrameStock(ActionEvent event) {
        System.out.println("Lagerort editieren");
        lblMainFrameTitleLabel.setText("Lagerort editieren");
        GuiPrototyp.getInstance().buildStockList(vBoxMain);
        
    
    }
    
    @FXML
    private void btnActionAccMainFrameState(ActionEvent event) {
        System.out.println("Status editieren");
        lblMainFrameTitleLabel.setText("Status editieren");
        GuiPrototyp.getInstance().buildStateList(vBoxMain);
        
    
    }
    
    @FXML
    private void btnActionAccMainFrameSettings(ActionEvent event) {
        System.out.println("Einstellungen");
        lblMainFrameTitleLabel.setText("Einstellungen");
        GuiPrototyp.getInstance().buildSettings(vBoxMain);
        
    
    }
    
    
    @FXML
    private void btnActionAccMainFrameInvoice(ActionEvent event) {
        System.out.println("Erstellen von Aufträgen");
        lblMainFrameTitleLabel.setText("Erstellen");
        GuiPrototyp.getInstance().buildInvoiceFrame(vBoxMain);
    }
    
    @FXML
    private void btnActionAccMainFrameInvoiceList(ActionEvent event) {
        System.out.println("Einsehen von Auftragen");
        lblMainFrameTitleLabel.setText("Einsehen");
        GuiPrototyp.getInstance().buildInvoiceList(vBoxMain);
    }
    
    @FXML
    private void btnActionAccMainFrameOffer(ActionEvent event) {
        System.out.println("Angebot");
        lblMainFrameTitleLabel.setText("Angebot");
        
    }
    @FXML
    private void btnActionAccMainFrameDelivery(ActionEvent event) {
        System.out.println("Lieferung");
        lblMainFrameTitleLabel.setText("Lieferung");
        
    }
    @FXML
    private void btnActionAccMainFrameCustomer(ActionEvent event) {
        System.out.println("Kunden");
        lblMainFrameTitleLabel.setText("Kunden");
        GuiPrototyp.getInstance().buildCustomersFrame(vBoxMain);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        lblMainFrameTitleLabel.setText("Hauptmenü");
        
        btnAccMainFrameCheckIn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameCheckIn.setEffect(shadow);
            }
        
       
        });
        btnAccMainFrameCheckIn.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameCheckIn.setEffect(null);
            }
        
       
        });
        btnAccMainFrameCheckOut.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameCheckOut.setEffect(shadow);
            }
        
       
        });
        btnAccMainFrameCheckOut.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameCheckOut.setEffect(null);
            }
        
       
        });
        
        btnAccMainFrameInsert.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameInsert.setEffect(shadow);
            }
        
       
        });
        btnAccMainFrameInsert.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameInsert.setEffect(null);
            }
        
       
        });
        btnAccMainFrameList.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameList.setEffect(shadow);
            }
        
       
        });
        btnAccMainFrameList.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameList.setEffect(null);
            }
        
       
        });
        
        btnAccMainFrameStock.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameStock.setEffect(shadow);
            }
        
       
        });
        btnAccMainFrameStock.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameStock.setEffect(null);
            }
        
       
        });
        btnAccMainFrameState.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameState.setEffect(shadow);
            }
        
       
        });
        btnAccMainFrameState.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameState.setEffect(null);
            }
        
       
        });
        
        btnAccMainFrameCategory.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameCategory.setEffect(shadow);
            }
        
       
        });
        btnAccMainFrameCategory.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameCategory.setEffect(null);
            }
        
       
        });
        
        btnAccMainFrameGiveRights.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameGiveRights.setEffect(shadow);
            }
        
       
        });
        btnAccMainFrameGiveRights.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameGiveRights.setEffect(null);
            }
        
       
        });
        
        btnAccMainFrameUsers.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameUsers.setEffect(shadow);
            }
        
       
        });
        btnAccMainFrameUsers.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameUsers.setEffect(null);
            }
        
       
        });
        
        btnAccMainFrameInvoice.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameInvoice.setEffect(shadow);
            }
        
       
        });
        btnAccMainFrameInvoice.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameInvoice.setEffect(null);
            }
        
       
        });
        
         btnAccMainFrameInvoiceList.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameInvoiceList.setEffect(shadow);
            }
        
       
        });
        btnAccMainFrameInvoiceList.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameInvoiceList.setEffect(null);
            }
        
       
        });
        
//        btnAccMainFrameOffer.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
//        {
//        @Override public void handle(MouseEvent e) 
//            {
//                btnAccMainFrameOffer.setEffect(shadow);
//            }
//        
//       
//        });
//        btnAccMainFrameOffer.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
//        {
//        @Override public void handle(MouseEvent e) 
//            {
//                btnAccMainFrameOffer.setEffect(null);
//            }
//        
//       
//        });
//        
//        btnAccMainFrameDelivery.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
//        {
//        @Override public void handle(MouseEvent e) 
//            {
//                btnAccMainFrameDelivery.setEffect(shadow);
//            }
//        
//       
//        });
//        btnAccMainFrameDelivery.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
//        {
//        @Override public void handle(MouseEvent e) 
//            {
//                btnAccMainFrameDelivery.setEffect(null);
//            }
//        
//       
//        });
        
        btnAccMainFrameCustomer.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameCustomer.setEffect(shadow);
            }
        
       
        });
        btnAccMainFrameCustomer.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameCustomer.setEffect(null);
            }
        
       
        });
        
        btnAccMainFrameSettings.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameSettings.setEffect(shadow);
            }
        
       
        });
        btnAccMainFrameSettings.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnAccMainFrameSettings.setEffect(null);
            }
        
       
        });
                
        
        giveRights();
        
        
    }
    
    @FXML protected void CheckIn(ActionEvent event) {
        GuiPrototyp.getInstance().buildCheckIn(vBoxMain);
    }
    
    private void giveRights()
    {
        User curUser = AppConfig.getUser();
        Groups curUsersGroup = curUser.getGroups();
                
        if(!curUsersGroup.getAdmin())
        {
            if(!curUsersGroup.getArticle())
            {
                btnAccMainFrameInsert.setVisible(false);
                btnAccMainFrameList.setVisible(false);
            }
            if(!curUsersGroup.getCategory())
            {
                btnAccMainFrameCategory.setVisible(false);
            }
            if(!curUsersGroup.getCheckIn())
            {           
                btnAccMainFrameCheckIn.setVisible(false);
            }
            if(!curUsersGroup.getCheckOut())
            {              
                btnAccMainFrameCheckOut.setVisible(false);
            }
            if(!curUsersGroup.getCustomer())
            {
                btnAccMainFrameCustomer.setVisible(false);
            }
            if(!curUsersGroup.getGroups())
            {
                btnAccMainFrameGiveRights.setVisible(false);
            }
            if(!curUsersGroup.getInvoice() || !curUsersGroup.getOffer() || !curUsersGroup.getDeliveryNote())
            {
                btnAccMainFrameInvoice.setVisible(false);
                btnAccMainFrameInvoiceList.setVisible(false);
            }
            if(!curUsersGroup.getMasterData())
            {
                btnAccMainFrameSettings.setVisible(false);
            }
            if(!curUsersGroup.getState())
            {              
                btnAccMainFrameState.setVisible(false);
            }
            if(!curUsersGroup.getStock())
            {
                 btnAccMainFrameStock.setVisible(false);
            }
            if(!curUsersGroup.getUser())
            {
                btnAccMainFrameUsers.setVisible(false);
            }
           
            
            List<TitledPane> lstPanes = accMainFrame.getPanes();
            
            for(TitledPane tltPane : lstPanes)
            {
                String title = tltPane.getText();
                
                if(title.equals("Artikelverwaltung"))
                {
                     if(!curUsersGroup.getArticle() && !curUsersGroup.getCheckIn() && !curUsersGroup.getCheckOut())
                     {
                         tltPane.setVisible(false);
                     }
                }
                if(title.equals("Stammdatenverwaltung"))
                {
                     if(!curUsersGroup.getCategory() && !curUsersGroup.getState() && !curUsersGroup.getStock())
                     {
                         tltPane.setVisible(false);
                     }
                }
                if(title.equals("Rechteverwaltung"))
                {
                     if(!curUsersGroup.getUser() && !curUsersGroup.getGroups())
                     {
                         tltPane.setVisible(false);
                     }
                }          
                if(title.equals("Auftragsverwaltung"))
                {
                     if(!curUsersGroup.getInvoice() && !curUsersGroup.getCustomer() && !curUsersGroup.getDeliveryNote() && !curUsersGroup.getOffer())
                     {
                         tltPane.setVisible(false);
                     }
                }
            }
        }      
    }
}
