/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.controller.*;
import eu.gymnaila.chunks.artiver.controls.ArtiVerContextMenu;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.entity.Article;
import eu.gymnaila.chunks.artiver.entity.Customer;
import eu.gymnaila.chunks.artiver.entity.DepictionArticle;
import eu.gymnaila.chunks.artiver.entity.Invoice;
import eu.gymnaila.chunks.artiver.exceptions.DeliveryNoteAlreadyExistsException;
import eu.gymnaila.chunks.artiver.exceptions.InvoiceAlreadyExistsException;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import eu.gymnaila.chunks.artiver.reports.ReportTemplate;
import eu.gymnaila.chunks.artiver.tooling.Numbers;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.javafxdata.datasources.protocol.ObjectDataSource;

/**
 *
 * @author Thunderbolt
 */
public class InvoiceFrame implements Initializable 
{
    
    
    
    private TableView<Article> mainTable;
    
    private ObjectDataSource ds;
    
    @FXML
    private ListView lstInvoice;
    @FXML
    private TextField txtInvoiceAmount;
    @FXML
    private TextField txtInvoicePrice;
    @FXML
    private VBox vBoxArticles;
    @FXML
    private ChoiceBox cbxInvoiceCustomer;
    @FXML
    private TextField txtInvoicePriceNoMwSt;
    @FXML
    private TextField txtInvoiceTotal;
    @FXML
    private ImageView arrowRed;
    @FXML 
    private ImageView arrowGreen;
    @FXML
    private Button btnInvoice;
    @FXML
    private Button btnOffer;
    @FXML
    private Button btnDelivery;
            
    private DropShadow shadow = new DropShadow();
    
    private double priceTotal = 0;

    private double priceNoMwSt = 0;
    
    private double mwst;
    
    List<DepictionArticle> listDepArt = new ArrayList<DepictionArticle>();

    ObservableList<DepictionArticle> observableList = FXCollections.observableList(listDepArt);
    
    DepictionArticle depArt = new DepictionArticle();
            
    InvoiceController invoice = new InvoiceController();
    
    Articlemanagement article = new Articlemanagement();
    
    CustomerController customer = new CustomerController();
    
    OfferController offer = new OfferController();
    
    DeliveryNoteController delivery = new DeliveryNoteController();
    
    MasterDataController masterData = new MasterDataController();
    
    
    @FXML
    private void btnActionInvoiceRoger(ActionEvent event) 
    {
        try {
            System.out.println("Rechnung bestätigen");
            
            String stringTemp;
            
            listDepArt.clear();
           
            for(int i = 0; i < lstInvoice.getItems().size()-1; i++)
            {
                stringTemp = lstInvoice.getItems().get(i).toString();
               
                String stringList[] = stringTemp.replaceAll("\t", "").split(";");
                
                depArt = null;
                depArt.setName(stringList[1]);
                depArt.setNr(stringList[0]);
                depArt.setPrice(Numbers.parseDouble(stringList[3]));
                depArt.setAmount(Numbers.parseInt(stringList[2]));
                
                listDepArt.add(depArt);
                
            }
            
            invoice.addInvoice(listDepArt, priceTotal, (Customer)cbxInvoiceCustomer.getSelectionModel().getSelectedItem());
        } catch (InvoiceAlreadyExistsException ex) {
            Logger.getLogger(InvoiceFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        
        
    }
    
    @FXML
    private void btnActionOfferRoger(ActionEvent event) 
    {
        
            System.out.println("Angebot bestätigen");
            
            String stringTemp;
            
            listDepArt.clear();
           
            for(int i = 0; i < lstInvoice.getItems().size()-1; i++)
            {
                stringTemp = lstInvoice.getItems().get(i).toString();
               
                String stringList[] = stringTemp.replaceAll("\t", "").split(";");
                
                depArt = null;
                depArt.setName(stringList[1]);
                depArt.setNr(stringList[0]);
                depArt.setPrice(Numbers.parseDouble(stringList[3]));
                depArt.setAmount(Numbers.parseInt(stringList[2]));
                
                listDepArt.add(depArt);
                
            }
            
            offer.addOffer(priceTotal, listDepArt, (Customer)cbxInvoiceCustomer.getSelectionModel().getSelectedItem());
        
    }
    
    @FXML
    private void btnActionDeliveryRoger(ActionEvent event) 
    {
        try {
            System.out.println("Lieferschein bestätigen");
            
            String stringTemp;
            
            listDepArt.clear();
           
            for(int i = 0; i < lstInvoice.getItems().size()-1; i++)
            {
                stringTemp = lstInvoice.getItems().get(i).toString();
               
                String stringList[] = stringTemp.replaceAll("\t", "").split(";");
                
                depArt = null;
                depArt.setName(stringList[1]);
                depArt.setNr(stringList[0]);
                depArt.setPrice(Numbers.parseDouble(stringList[3]));
                depArt.setAmount(Numbers.parseInt(stringList[2]));
                
                listDepArt.add(depArt);
                
            }
            
            delivery.addDeliveryNote((Customer)cbxInvoiceCustomer.getSelectionModel().getSelectedItem(), listDepArt);
        } catch (DeliveryNoteAlreadyExistsException ex) {
            Logger.getLogger(InvoiceFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    @FXML
    private void imgActionArrowGreen(MouseEvent event) 
    {
        System.out.println("Grüner Pfeil");
        
        
        
        
        String nameArt = mainTable.getSelectionModel().getSelectedItem().getName();
            System.out.println(nameArt);
        String nrArt = mainTable.getSelectionModel().getSelectedItem().getNr().toString();
            System.out.println(nrArt);
        String amount = txtInvoiceAmount.getText().toString();
            System.out.println(amount);
        String price = txtInvoicePrice.getText();
            System.out.println(price);
        
        if(mainTable.getSelectionModel().getSelectedItem() != null && !txtInvoiceAmount.getText().equals("") && !txtInvoicePrice.getText().equals(""))
        {
            lstInvoice.getItems().add(nrArt + ";\t" + nameArt + ";\t" + amount + ";\t" + price);
        }
        
        double priceTemp = Numbers.parseDouble(txtInvoicePrice.getText());
        
        priceTotal = priceTotal + (priceTemp * (Numbers.parseInt(txtInvoiceAmount.getText())));
        priceNoMwSt = priceNoMwSt + (priceTemp * (Numbers.parseInt(txtInvoiceAmount.getText())));
        
        double priceTempTotal = priceTotal;
        double priceTempNoMwSt = priceNoMwSt;
        
        priceTempNoMwSt = priceTempNoMwSt - (priceTempNoMwSt * mwst);
        priceTempNoMwSt = Math.round(priceTempNoMwSt*100)/100;
                
        txtInvoiceTotal.setText(Numbers.toString(priceTempTotal));
        txtInvoicePriceNoMwSt.setText(Numbers.toString(priceTempNoMwSt));
        
        txtInvoicePrice.clear();
        txtInvoiceAmount.clear();
         
             
    }
    
    @FXML
    private void imgActionArrowRed(MouseEvent event) 
    {
        System.out.println("Roter Pfeil");
        
        String stringTemp;
        
        if(lstInvoice.getItems().size() != 0)
        {
            if(lstInvoice.getSelectionModel().isEmpty())
            {
                stringTemp = lstInvoice.getItems().get(lstInvoice.getItems().size()-1).toString();
                lstInvoice.getItems().remove(stringTemp);
            }
            else
            {
                stringTemp = (String)lstInvoice.getSelectionModel().getSelectedItem();
                lstInvoice.getItems().remove(stringTemp);
            }
        
        
        String stringList[] = stringTemp.replaceAll("\t", "").split(";");
        
        txtInvoicePrice.setText(stringList[3]);
        txtInvoiceAmount.setText(stringList[2]);

        double priceTemp = Numbers.parseDouble(txtInvoicePrice.getText());
        
        priceTotal = priceTotal - (priceTemp * (Numbers.parseInt(txtInvoiceAmount.getText())));
        priceNoMwSt = priceNoMwSt - (priceTemp * (Numbers.parseInt(txtInvoiceAmount.getText())));
        
        double priceTempTotal = priceTotal;
        double priceTempNoMwSt = priceNoMwSt;
        
        priceTempNoMwSt = priceTempNoMwSt - (priceTempNoMwSt * mwst);
        
        priceTempNoMwSt = Math.round(priceTempNoMwSt*100)/100;
        
        txtInvoiceTotal.setText(Numbers.toString(priceTempTotal));
        txtInvoicePriceNoMwSt.setText(Numbers.toString(priceTempNoMwSt));
        
        
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        if(masterData.getMasterData().getVat() != 0)
        {
            mwst = ((masterData.getMasterData().getVat())/100);
            System.out.println(mwst);
        }
        else
        {
            mwst = 0;
        }
        
        
        arrowGreen.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                arrowGreen.setEffect(shadow);
            }
        
       
        });
        arrowGreen.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                arrowGreen.setEffect(null);
            }
        
       
        });
        
        arrowRed.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                arrowRed.setEffect(shadow);
            }
        
       
        });
        arrowRed.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                arrowRed.setEffect(null);
            }
        
       
        });
        
        btnInvoice.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnInvoice.setEffect(shadow);
            }
        
       
        });
        btnInvoice.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnInvoice.setEffect(null);
            }
        
       
        });
        
        btnOffer.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnOffer.setEffect(shadow);
            }
        
       
        });
        btnOffer.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnOffer.setEffect(null);
            }
        
       
        });
        
        btnDelivery.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnDelivery.setEffect(shadow);
            }
        
       
        });
        btnDelivery.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnDelivery.setEffect(null);
            }
        
       
        });
                
        mainTable = new TableView<Article>();
        try
        {
            ds = new ObjectDataSource(article.list(), Article.class, "nr", "name", "ean", "amount", "category", "price", "stock", "state");
            mainTable.setItems(ds.getData());
            mainTable.getColumns().addAll(ds.getColumns());
            
            
            if(!vBoxArticles.getChildren().isEmpty())
            {
                vBoxArticles.getChildren().clear();
            }
            
            vBoxArticles.getChildren().add(mainTable);
              
            //TODO Invoice!!!
            mainTable.setContextMenu(new ArtiVerContextMenu(ReportTemplate.STANDARD.toString(), "Artikelliste", ds));
            
        }
        catch(Exception e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
        
    
    lstInvoice.setEditable(false);
    
    mainTable.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
    {
     @Override public void handle(MouseEvent e) 
            {
                txtInvoicePrice.setText(Numbers.toString(mainTable.getSelectionModel().getSelectedItem().getPrice()));
            }   
    });
    
    cbxInvoiceCustomer.setItems(FXCollections.observableArrayList(customer.list()));
    
    }
}
