/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.controller.StockController;
import eu.gymnaila.chunks.artiver.controls.ArtiVerContextMenu;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.controls.ModalYesNoDialog;
import eu.gymnaila.chunks.artiver.entity.State;
import eu.gymnaila.chunks.artiver.entity.Stock;
import eu.gymnaila.chunks.artiver.exceptions.*;
import eu.gymnaila.chunks.artiver.exceptions.StockConnectedWithArticlesException;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import eu.gymnaila.chunks.artiver.reports.ReportTemplate;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import org.javafxdata.datasources.protocol.ObjectDataSource;

/**
 *
 * @author Thundebolt
 */
public class StockList implements Initializable {
    
    private TableView<Stock> mainTable;
    @FXML
    private VBox vBoxStockListList;
    @FXML
    private Button btnStockListErease;
    @FXML
    private Button btnStockListChange;
    @FXML
    private Button btnInsertStockRoger;
    
    private ObjectDataSource ds;
    
    private ObservableList<Stock> tempList = FXCollections.observableArrayList();
    
    private DropShadow shadow = new DropShadow();
    
    @FXML
    private TextField txtInsertStockName;
    @FXML
    private TextField txtInsertStockCountry;
    @FXML
    private TextField txtInsertStockAddress;
    
    private Stock tempStock = null;
    
    private StockController stock = new StockController();
    
    
    @FXML
    private void btnActionStockListErease(ActionEvent event) 
    {
        System.out.println("Lager entfernen");
        
        
        ModalYesNoDialog yesNo = new ModalYesNoDialog(GuiPrototyp.getInstance().getStage(), "Warnung", "Wollen sie wirklich löschen?");
        EventHandler<ActionEvent> ehaeYes = new EventHandler<ActionEvent>() 
        {

            @Override
            public void handle(ActionEvent arg0) 
            {
        
                    try 
                    {
                            stock.deleteStock(mainTable.getSelectionModel().getSelectedItem().getIdStock());
            
                                ds = new ObjectDataSource(stock.list(), Stock.class, "name", "country", "address");
                                mainTable.setItems(tempList);
                                mainTable.setItems(ds.getData());
                    
                    } 
                    catch (StockConnectedWithArticlesException e) 
                    {
                        ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
                        e.printStackTrace();
                    } 
                    catch (StockNotFoundException e) 
                    {
                        ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
                        e.printStackTrace();
                    }
            
            }
        };
        
        yesNo.setOnYes(ehaeYes);
        yesNo.show();
        
    }
    
    @FXML
    private void btnActionStockListChange(ActionEvent event) 
    {
        System.out.println("You clicked me!");
        tempStock = mainTable.getSelectionModel().getSelectedItem();
        
         txtInsertStockName.setText(tempStock.getName());
         txtInsertStockCountry.setText(tempStock.getCountry());
         txtInsertStockAddress.setText(tempStock.getAddress());
    }
    
    @FXML
    private void btnActionInsertStockRoger(ActionEvent event) {
        System.out.println("Lagerort erstellen");
        
        String tempName = txtInsertStockName.getText();
        
        String tempCountry = txtInsertStockCountry.getText();
        
        String tempAddress = txtInsertStockAddress.getText();
        
        try {
            if(tempStock == null)
            {
                stock.createStock(tempName, tempCountry, tempAddress);
            }
            else
            {
                tempStock.setName(tempName);
                tempStock.setCountry(tempCountry);
                tempStock.setAddress(tempAddress);
                
                try {
                    stock.edit(tempStock);
                } catch (StockNotFoundException e) {
                    ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
                    e.printStackTrace();
                }
                
                tempStock = null;
            
            }
            
            ds = new ObjectDataSource(stock.list(), Stock.class, "name", "country", "address");
                    mainTable.setItems(tempList);
                    mainTable.setItems(ds.getData());
                    mainTable.getSortOrder().add(mainTable.getColumns().get(0));
        mainTable.getSortOrder().clear();
            
        } catch (StockAlreadyExistsException e) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }  
        
        txtInsertStockName.clear();
        
        txtInsertStockCountry.clear();
        
        txtInsertStockAddress.clear();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        btnStockListErease.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnStockListErease.setEffect(shadow);
            }
        
       
        });
        btnStockListErease.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnStockListErease.setEffect(null);
            }
        
       
        });
        
        btnStockListChange.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnStockListChange.setEffect(shadow);
            }
        
       
        });
        btnStockListChange.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnStockListChange.setEffect(null);
            }
        
       
        });
        
        btnInsertStockRoger.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnInsertStockRoger.setEffect(shadow);
            }
        
       
        });
        btnInsertStockRoger.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnInsertStockRoger.setEffect(null);
            }
        
       
        });
        
        
        mainTable = new TableView<Stock>();
        try
        {
            ds = new ObjectDataSource(stock.list(), Stock.class, "name", "country", "address");
            mainTable.setItems(ds.getData());
            mainTable.getColumns().addAll(ds.getColumns());
            
            
            if(!vBoxStockListList.getChildren().isEmpty())
            {
                vBoxStockListList.getChildren().clear();
            }
            
            vBoxStockListList.getChildren().add(mainTable);
            
            mainTable.setContextMenu(new ArtiVerContextMenu(ReportTemplate.STANDARD.toString(), "Lagerliste", ds));
        }
        catch(Exception e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
    }    
}
