/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DJQuery;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.controller.*;
import eu.gymnaila.chunks.artiver.controls.ArtiVerContextMenu;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.entity.*;
import eu.gymnaila.chunks.artiver.exceptions.DeliveryNoteAlreadyExistsException;
import eu.gymnaila.chunks.artiver.exceptions.InvoiceAlreadyExistsException;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import eu.gymnaila.chunks.artiver.reports.InvoiceReportTemplate;
import eu.gymnaila.chunks.artiver.reports.MainTest;
import eu.gymnaila.chunks.artiver.reports.ReportTemplate;
import eu.gymnaila.chunks.artiver.tooling.Numbers;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.stage.FileChooser;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
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
        try 
        {
            System.out.println("Rechnung bestätigen");
            
            String stringTemp;
            
            if(lstInvoice.getItems().size()>0)
            {
                listDepArt.clear();
           
                for(int i = 0; i < lstInvoice.getItems().size(); i++)
                {
                    stringTemp = lstInvoice.getItems().get(i).toString();
                    System.out.println(stringTemp);
                    String stringList[] = stringTemp.split("; ");

                    depArt = new DepictionArticle();
                    depArt.setName(stringList[1]);
                    System.out.println(depArt);
                    depArt.setNr(stringList[0]);
                    depArt.setPrice(Numbers.parseDouble(stringList[3]));
                    depArt.setAmount(Numbers.parseInt(stringList[2]));

                    System.out.println(depArt);

                    listDepArt.add(depArt);

                }

              int idInv = invoice.addInvoice(listDepArt, priceTotal, (Customer)cbxInvoiceCustomer.getSelectionModel().getSelectedItem());
          
            
                  
        


          
            List<AbstractColumn> cols = new ArrayList<>();
                 
                
                AbstractColumn column1 = ColumnBuilder.getNew()
                    .setColumnProperty("Artikelnummer", "java.lang.String")
                    .setTitle("Artikelnummer")
                    .setWidth(85)
                    .build();
                
                cols.add(column1);
                
                AbstractColumn column2 = ColumnBuilder.getNew()
                    .setColumnProperty("Anzahl", "java.lang.Integer")
                    .setTitle("Anzahl")
                    .setWidth(85)
                    .build();
                
                cols.add(column2);
                
                AbstractColumn column3 = ColumnBuilder.getNew()
                    .setColumnProperty("Artikel", "java.lang.String")
                    .setTitle("Artikel")
                    .setWidth(85)
                    .build();
                
                cols.add(column3);
                
                AbstractColumn column4 = ColumnBuilder.getNew()
                    .setColumnProperty("Preis", "java.lang.Double")
                    .setTitle("Preis")
                    .setWidth(85)
                    .build();
                
                cols.add(column4);
                
            HashMap<String,String> props = new HashMap<>();
            
            props.put("Total_sum", txtInvoiceTotal.getText());
            System.out.println(txtInvoiceTotal.getText());
            InvoiceReportTemplate repTemp = new InvoiceReportTemplate(ReportTemplate.INVOICE.toString(), cols, "Rechnung", AppConfig.getMasterData(), (Customer)cbxInvoiceCustomer.getSelectionModel().getSelectedItem());
            DynamicReport dr = repTemp.buildReport();
     
        
           dr.setQuery(new DJQuery("Select art.nr as Artikelnummer, art.amount as Anzahl, art.name as Artikel, art.price as Preis " +
                                    " From IntersectionInvoiceArticle inter "+
                                    " inner join DepictionArticle art on art.idDepictionArticle = inter.idArticle "+
                                    " inner join Invoice inv on inv.idInvoice = inter.idInvoice " +
                                    " where inter.idInvoice = " + idInv, DJConstants.QUERY_LANGUAGE_SQL));
           
            
            EntityManager entityManager = AppConfig.createEntityManager();
                
            entityManager.getTransaction().begin();
                
            java.sql.Connection connection = entityManager.unwrap(java.sql.Connection.class);
                
            System.out.println(connection.isValid(0));
                
            entityManager.getTransaction().commit();
            
    
            
            final JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), connection, props);
         


           
                     System.out.println("PDF-Export");
                        FileChooser selFile = new FileChooser();
                        selFile.setTitle("PDF-Export");
                        
                        //Set extension filter
                        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF Dateien (*.pdf)", "*.pdf");
                        selFile.getExtensionFilters().add(extFilter);

                        File tempFile = selFile.showSaveDialog(GuiPrototyp.getInstance().getStage());
                        
                        if(tempFile != null)
                        {
                            String path = tempFile.getAbsolutePath().endsWith(".pdf") ? tempFile.getAbsolutePath() : tempFile.getAbsolutePath() + ".pdf";
                            
                            JasperExportManager.exportReportToPdfFile(jasperPrint, path); 
                        }
                        else
                        {
                           // throw new Exception();
                        }
                        
                        JasperViewer.viewReport(jasperPrint, false);
            
            
            
            
            
            
            }
            
        } 
        catch (Exception e) 
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
    
        
        
    }
    
    @FXML
    private void btnActionOfferRoger(ActionEvent event) 
    {
        try
        {
            System.out.println("Angebot bestätigen");
            
            String stringTemp;
            
            if(lstInvoice.getItems().size()>0)
            {
                listDepArt.clear();

                for(int i = 0; i < lstInvoice.getItems().size(); i++)
                {
                    stringTemp = lstInvoice.getItems().get(i).toString();

                    String stringList[] = stringTemp.split("; ");

                    depArt = null;
                    depArt.setName(stringList[1]);
                    depArt.setNr(stringList[0]);
                    depArt.setPrice(Numbers.parseDouble(stringList[3]));
                    depArt.setAmount(Numbers.parseInt(stringList[2]));

                    listDepArt.add(depArt);

                }

                int idOff = offer.addOffer(priceTotal, listDepArt, (Customer)cbxInvoiceCustomer.getSelectionModel().getSelectedItem());
           
           
          
            List<AbstractColumn> cols = new ArrayList<>();
                 
                
                AbstractColumn column1 = ColumnBuilder.getNew()
                    .setColumnProperty("Artikelnummer", "java.lang.String")
                    .setTitle("Artikelnummer")
                    .setWidth(85)
                    .build();
                
                cols.add(column1);
                
                AbstractColumn column2 = ColumnBuilder.getNew()
                    .setColumnProperty("Anzahl", "java.lang.Integer")
                    .setTitle("Anzahl")
                    .setWidth(85)
                    .build();
                
                cols.add(column2);
                
                AbstractColumn column3 = ColumnBuilder.getNew()
                    .setColumnProperty("Artikel", "java.lang.String")
                    .setTitle("Artikel")
                    .setWidth(85)
                    .build();
                
                cols.add(column3);
                
                AbstractColumn column4 = ColumnBuilder.getNew()
                    .setColumnProperty("Preis", "java.lang.Double")
                    .setTitle("Preis")
                    .setWidth(85)
                    .build();
                
                cols.add(column4);
                
            HashMap<String,String> props = new HashMap<>();
            
            props.put("Total_sum", txtInvoicePrice.getText());
            
            InvoiceReportTemplate repTemp = new InvoiceReportTemplate(ReportTemplate.OFFER.toString(), cols, "Angebot", AppConfig.getMasterData(), (Customer)cbxInvoiceCustomer.getSelectionModel().getSelectedItem());
            DynamicReport dr = repTemp.buildReport();
     
        
           dr.setQuery(new DJQuery("Select art.nr as Artikelnummer, art.amount as Anzahl, art.name as Artikel, art.price as Preis " +
                                    " From IntersectionOfferArticle inter "+
                                    " inner join DepictionArticle art on art.idDepictionArticle = inter.idArticle " +
                                    " where inter.idOffer = " + idOff, DJConstants.QUERY_LANGUAGE_SQL));
           
            
            EntityManager entityManager = AppConfig.createEntityManager();
                
            entityManager.getTransaction().begin();
                
            java.sql.Connection connection = entityManager.unwrap(java.sql.Connection.class);
                
            System.out.println(connection.isValid(0));
                
            entityManager.getTransaction().commit();
            
    
            
            final JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), connection, props);
         


           
                     System.out.println("PDF-Export");
                        FileChooser selFile = new FileChooser();
                        selFile.setTitle("PDF-Export");
                        
                        //Set extension filter
                        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF Dateien (*.pdf)", "*.pdf");
                        selFile.getExtensionFilters().add(extFilter);

                        File tempFile = selFile.showSaveDialog(GuiPrototyp.getInstance().getStage());
                        
                        if(tempFile != null)
                        {
                            String path = tempFile.getAbsolutePath().endsWith(".pdf") ? tempFile.getAbsolutePath() : tempFile.getAbsolutePath() + ".pdf";
                            
                            JasperExportManager.exportReportToPdfFile(jasperPrint, path); 
                        }
                        else
                        {
                           // throw new Exception();
                        }
                        
                        JasperViewer.viewReport(jasperPrint, false);
            
            
            
            
            
            }
        } 
        catch (Exception e) 
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
    }
    
    @FXML
    private void btnActionDeliveryRoger(ActionEvent event) 
    {
        try 
        {
            System.out.println("Lieferschein bestätigen");
            
            String stringTemp;
            
            if(lstInvoice.getItems().size()>0)
            {
                listDepArt.clear();

                for(int i = 0; i < lstInvoice.getItems().size(); i++)
                {
                    stringTemp = lstInvoice.getItems().get(i).toString();

                    String stringList[] = stringTemp.split("; ");

                    depArt = null;
                    depArt.setName(stringList[1]);
                    depArt.setNr(stringList[0]);
                    depArt.setPrice(Numbers.parseDouble(stringList[3]));
                    depArt.setAmount(Numbers.parseInt(stringList[2]));

                    listDepArt.add(depArt);

                }

                int idNote = delivery.addDeliveryNote((Customer)cbxInvoiceCustomer.getSelectionModel().getSelectedItem(), listDepArt);
           
                      
        


          
            List<AbstractColumn> cols = new ArrayList<>();
                 
                
                AbstractColumn column1 = ColumnBuilder.getNew()
                    .setColumnProperty("Artikelnummer", "java.lang.String")
                    .setTitle("Artikelnummer")
                    .setWidth(85)
                    .build();
                
                cols.add(column1);
                
                AbstractColumn column2 = ColumnBuilder.getNew()
                    .setColumnProperty("Anzahl", "java.lang.Integer")
                    .setTitle("Anzahl")
                    .setWidth(85)
                    .build();
                
                cols.add(column2);
                
                AbstractColumn column3 = ColumnBuilder.getNew()
                    .setColumnProperty("Artikel", "java.lang.String")
                    .setTitle("Artikel")
                    .setWidth(85)
                    .build();
                
                cols.add(column3);
                
      
            
            InvoiceReportTemplate repTemp = new InvoiceReportTemplate(ReportTemplate.DELIVERY.toString(), cols, "Lieferschein", AppConfig.getMasterData(), (Customer)cbxInvoiceCustomer.getSelectionModel().getSelectedItem());
            DynamicReport dr = repTemp.buildReport();
     
        
           dr.setQuery(new DJQuery("Select art.nr as Artikelnummer, art.amount as Anzahl, art.name as Artikel " +
                                    " From IntersectionInvoiceArticle inter "+
                                    " inner join DepictionArticle art on art.idDepictionArticle = inter.idArticle " +
                                    " where inv.idInvoice = " + idNote, DJConstants.QUERY_LANGUAGE_SQL));
           
            
            EntityManager entityManager = AppConfig.createEntityManager();
                
            entityManager.getTransaction().begin();
                
            java.sql.Connection connection = entityManager.unwrap(java.sql.Connection.class);
                
            System.out.println(connection.isValid(0));
                
            entityManager.getTransaction().commit();
            
    
            
            final JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), connection, null);
         


           
                     System.out.println("PDF-Export");
                        FileChooser selFile = new FileChooser();
                        selFile.setTitle("PDF-Export");
                        
                        //Set extension filter
                        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PDF Dateien (*.pdf)", "*.pdf");
                        selFile.getExtensionFilters().add(extFilter);

                        File tempFile = selFile.showSaveDialog(GuiPrototyp.getInstance().getStage());
                        
                        if(tempFile != null)
                        {
                            String path = tempFile.getAbsolutePath().endsWith(".pdf") ? tempFile.getAbsolutePath() : tempFile.getAbsolutePath() + ".pdf";
                            
                            JasperExportManager.exportReportToPdfFile(jasperPrint, path); 
                        }
                        else
                        {
                           // throw new Exception();
                        }
                        
                        JasperViewer.viewReport(jasperPrint, false);
            
          
            
            }
        }
        catch (Exception e) 
        {
             ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
             e.printStackTrace();
        }
        
    }
    
    
    
    @FXML
    private void imgActionArrowGreen(MouseEvent event) 
    {
        System.out.println("Grüner Pfeil");
        
        if(!mainTable.getSelectionModel().isEmpty() && !txtInvoicePrice.getText().equals("") && !txtInvoiceAmount.getText().equals(""))
        {
            String nameArt = mainTable.getSelectionModel().getSelectedItem().getName();
                System.out.println(nameArt);
            String nrArt = mainTable.getSelectionModel().getSelectedItem().getNr().toString();
                System.out.println(nrArt);
            String price = txtInvoicePrice.getText().toString();
                System.out.println(price);

            String amount = "";

            if(Numbers.parseInt(txtInvoiceAmount.getText()) <= mainTable.getSelectionModel().getSelectedItem().getAmount())
            {
                amount = txtInvoiceAmount.getText();
                    System.out.println(amount);
            }
            else
            {
                amount = Numbers.toString(mainTable.getSelectionModel().getSelectedItem().getAmount());
            }

            if(mainTable.getSelectionModel().getSelectedItem() != null && !txtInvoiceAmount.getText().equals("") && !txtInvoicePrice.getText().equals(""))
            {
                lstInvoice.getItems().add(nrArt + "; " + nameArt + "; " + amount + "; " + price);
            }

            double priceTemp = Numbers.parseDouble(price) * Numbers.parseInt(amount);

            priceTotal = priceTotal + (priceTemp);
            priceNoMwSt = priceNoMwSt + (priceTemp - (priceTemp * mwst));

            txtInvoiceTotal.setText(Numbers.toString(Math.round(priceTotal*100)/100.0));
            txtInvoicePriceNoMwSt.setText(Numbers.toString(Math.round(priceNoMwSt*100)/100.0));

            txtInvoicePrice.clear();
            txtInvoiceAmount.clear();

        }
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
        
        
        String stringList[] = stringTemp.split("; ");
        
        txtInvoicePrice.setText(stringList[3]);
        txtInvoiceAmount.setText(stringList[2]);

        double priceTemp = Numbers.parseDouble(txtInvoicePrice.getText()) * Numbers.parseInt(txtInvoiceAmount.getText());
        
        priceTotal = priceTotal - priceTemp;
        priceNoMwSt = priceNoMwSt - (priceTemp - (priceTemp * mwst));
                
        //priceTempNoMwSt = Math.round(priceTempNoMwSt*100)/100;
        
        txtInvoiceTotal.setText(Numbers.toString(Math.round(priceTotal*100)/100.0));
        txtInvoicePriceNoMwSt.setText(Numbers.toString(Math.round(priceNoMwSt*100)/100.0));
        
        
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        txtInvoiceTotal.setEditable(false);
        txtInvoicePriceNoMwSt.setEditable(false);
        
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
            mainTable.setContextMenu(new ArtiVerContextMenu(ReportTemplate.INVOICE.toString(), "Rechnung", ds, null));
            
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
