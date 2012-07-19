/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.controller.DeliveryNoteController;
import eu.gymnaila.chunks.artiver.controller.InvoiceController;
import eu.gymnaila.chunks.artiver.controller.OfferController;
import eu.gymnaila.chunks.artiver.controls.ArtiVerContextMenu;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.entity.DeliveryNote;
import eu.gymnaila.chunks.artiver.entity.Invoice;
import eu.gymnaila.chunks.artiver.entity.Offer;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import eu.gymnaila.chunks.artiver.reports.ReportTemplate;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import org.javafxdata.datasources.protocol.ObjectDataSource;

/**
 *
 * @author Thunderbolt
 */
public class InvoiceList implements Initializable 
{
 
    private TableView<Invoice> mainTableInvoice;
    private TableView<Offer> mainTableOffer;
    private TableView<DeliveryNote> mainTableDelivery;
    
    private ObjectDataSource ds;
    
    private InvoiceController invoice = new InvoiceController();
    private OfferController offer = new OfferController();
    private DeliveryNoteController delivery = new DeliveryNoteController();
    
    @FXML
    private VBox vBoxListInvoice;
    @FXML
    private VBox vBoxListOffer;
    @FXML
    private VBox vBoxListDelivery;
    

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        mainTableInvoice = new TableView<Invoice>();
        try
        {
            ds = new ObjectDataSource(invoice.list(), Invoice.class, "invoiceNumber", "price", "customer", "modificationDate");
            mainTableInvoice.setItems(ds.getData());
            mainTableInvoice.getColumns().addAll(ds.getColumns());
            
            
            
            if(!vBoxListInvoice.getChildren().isEmpty())
            {
                vBoxListInvoice.getChildren().clear();
            }
            
            vBoxListInvoice.getChildren().add(mainTableInvoice);
                                                            
            //mainTableInvoice.setContextMenu(new ArtiVerContextMenu(ReportTemplate.STANDARD.toString(), "Rechnungsliste", ds));
            
        }
        catch(Exception e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
        
        mainTableOffer = new TableView<Offer>();
        try
        {
            ds = new ObjectDataSource(offer.list(), Offer.class, "offerNumber", "price", "customer", "modificationDate");
            mainTableOffer.setItems(ds.getData());
            mainTableOffer.getColumns().addAll(ds.getColumns());
            
            
            
            if(!vBoxListOffer.getChildren().isEmpty())
            {
                vBoxListOffer.getChildren().clear();
            }
            
            vBoxListOffer.getChildren().add(mainTableOffer);
                                                            
            //mainTableOffer.setContextMenu(new ArtiVerContextMenu(ReportTemplate.STANDARD.toString(), "Angebotsliste", ds));
            
        }
        catch(Exception e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
        
        mainTableDelivery = new TableView<DeliveryNote>();
        try
        {
            ds = new ObjectDataSource(delivery.list(), DeliveryNote.class, "deliveryNoteNumber", "delivery", "customer", "modificationDate");
            mainTableDelivery.setItems(ds.getData());
            mainTableDelivery.getColumns().addAll(ds.getColumns());
            
            
            
            if(!vBoxListDelivery.getChildren().isEmpty())
            {
                vBoxListDelivery.getChildren().clear();
            }
            
            vBoxListDelivery.getChildren().add(vBoxListDelivery);
                                                            
            //mainTableOffer.setContextMenu(new ArtiVerContextMenu(ReportTemplate.STANDARD.toString(), "Lieferscheinliste", ds));
            
        }
        catch(Exception e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
    }    
}
