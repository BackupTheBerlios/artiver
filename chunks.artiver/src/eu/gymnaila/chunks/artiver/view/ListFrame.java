/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.controller.Articlemanagement;
import eu.gymnaila.chunks.artiver.controls.ArtiVerContextMenu;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.controls.ModalYesNoDialog;
import eu.gymnaila.chunks.artiver.entity.Article;
import eu.gymnaila.chunks.artiver.exceptions.ArticleDoesNotExistException;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
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
import javafx.stage.FileChooser;
import javax.persistence.EntityManager;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.type.WhenNoDataTypeEnum;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.javafxdata.datasources.protocol.ObjectDataSource;

/**
 *
 * @author Thunderbolt
 */
public class ListFrame implements Initializable 
{
    
    @FXML
    private Label lblMainFrameTitleLabel;
    
    private TableView<Article> mainTable;
    @FXML
    private VBox vBoxListArticlelist;
    
    private ObjectDataSource ds;
    
    @FXML
    private Button btnListFrameErease;
    @FXML
    private Button btnListFrameChange;
    
    private DropShadow shadow = new DropShadow();
    
    
    private ObservableList<Article> tempList = FXCollections.observableArrayList();
    
    private Articlemanagement articles = new Articlemanagement();
        
    @FXML
    private void btnActionListFrameChange(ActionEvent event) 
    {
        System.out.println("Ändern");
        AppConfig.setEditArticle(mainTable.getSelectionModel().getSelectedItem());
    //TODO: siehe Main
        GuiPrototyp.getInstance().buildChangeFrame();
    }
    
    @FXML
    private void btnActionListFrameErease(ActionEvent event) 
    {
        System.out.println("Entfernen");
        
        
        ModalYesNoDialog yesNo = new ModalYesNoDialog(GuiPrototyp.getInstance().getStage(), "Warnung", "Wollen sie wirklich löschen?");
        EventHandler<ActionEvent> ehaeYes = new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) 
            {
                
            
        
        
        
                    // Todo try catch
                    try {
                        articles.delete(mainTable.getSelectionModel().getSelectedItem().getNr());
            
            
                
                                ds = new ObjectDataSource(articles.list(), Article.class, "nr", "name", "ean", "amount", "category", "price", "stock", "state", "colourCode");
                                mainTable.setItems(tempList);
                                mainTable.setItems(ds.getData());
                                mainTable.layout();
            
            
                        } 
                        catch (ArticleDoesNotExistException e) 
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
        btnListFrameErease.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnListFrameErease.setEffect(shadow);
            }
        
       
        });
        btnListFrameErease.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnListFrameErease.setEffect(null);
            }
        
       
        });
        btnListFrameChange.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnListFrameChange.setEffect(shadow);
            }
        
       
        });
        btnListFrameChange.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnListFrameChange.setEffect(null);
            }
        
       
        });
        
        
        mainTable = new TableView<Article>();
        try
        {
            ds = new ObjectDataSource(articles.list(), Article.class, "nr", "name", "ean", "amount", "category", "price", "stock", "state", "colourCode");
            mainTable.setItems(ds.getData());
            mainTable.getColumns().addAll(ds.getColumns());
            
            
            
            if(!vBoxListArticlelist.getChildren().isEmpty())
            {
                vBoxListArticlelist.getChildren().clear();
            }
            
            vBoxListArticlelist.getChildren().add(mainTable);
                                                            
            mainTable.setContextMenu(new ArtiVerContextMenu("ArticleListRep"));
            
        }
        catch(Exception e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
    }    








}
