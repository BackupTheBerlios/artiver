/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.controller.Articlemanagement;
import eu.gymnaila.chunks.artiver.controller.CustomerController;
import eu.gymnaila.chunks.artiver.controller.InvoiceController;
import eu.gymnaila.chunks.artiver.controls.ArtiVerContextMenu;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.entity.Article;
import eu.gymnaila.chunks.artiver.entity.DepictionArticle;
import eu.gymnaila.chunks.artiver.entity.Invoice;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import eu.gymnaila.chunks.artiver.tooling.Numbers;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
    private Button btnInvoiceRoger;
            
    private DropShadow shadow = new DropShadow();
    
    private double priceTotal = 0;

    private double priceNoMwSt = 0;
    
    
            
    InvoiceController invoice = new InvoiceController();
    
    Articlemanagement article = new Articlemanagement();
    
    CustomerController customer = new CustomerController();
    
    
    
    @FXML
    private void btnActionInvoiceRoger(ActionEvent event) 
    {
        System.out.println("Rechnung bestätigen");
    
        
        
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
        
        priceTempNoMwSt = priceTempNoMwSt - (priceTempNoMwSt * 0.19);
        
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
        
        priceTempNoMwSt = priceTempNoMwSt - (priceTempNoMwSt * 0.19);
        
        txtInvoiceTotal.setText(Numbers.toString(priceTempTotal));
        txtInvoicePriceNoMwSt.setText(Numbers.toString(priceTempNoMwSt));
        
        
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
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
        
        btnInvoiceRoger.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnInvoiceRoger.setEffect(shadow);
            }
        
       
        });
        btnInvoiceRoger.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnInvoiceRoger.setEffect(null);
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
                                                            
            mainTable.setContextMenu(new ArtiVerContextMenu("ArticleListRep"));
            
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
