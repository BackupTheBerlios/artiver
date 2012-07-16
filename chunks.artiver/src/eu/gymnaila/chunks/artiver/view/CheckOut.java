/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.controller.Articlemanagement;
import eu.gymnaila.chunks.artiver.controls.ArtiVerContextMenu;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.entity.Article;
import eu.gymnaila.chunks.artiver.exceptions.ArticleDoesNotExistException;
import eu.gymnaila.chunks.artiver.exceptions.NotEnoughAmountException;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import eu.gymnaila.chunks.artiver.tooling.Numbers;
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
 * @author Thunderbolt
 */
public class CheckOut implements Initializable {
    
    private TableView<Article> mainTable;
    @FXML
    private VBox vBoxCheckOutArticlelist;
    @FXML
    private TextField txtCheckOutArticle;
    @FXML
    private TextField txtCheckOutNummer;
    @FXML
    private Button btnCheckOutRoger;
    
    private DropShadow shadow = new DropShadow();
    
    private ObjectDataSource ds;
    
    
    private ObservableList<Article> tempList = FXCollections.observableArrayList();
    
    private Articlemanagement articles = new Articlemanagement();
    
    
    
    @FXML
    private void btnActionCheckOutRoger(ActionEvent event) throws NotEnoughAmountException 
    {
        
        System.out.println("Bestätige CheckOut");
        try {
            int anzahl = Numbers.parseInt(txtCheckOutNummer.getText());
            
            articles.checkOut(txtCheckOutArticle.getText(), anzahl);
        }
        catch(NumberFormatException e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Eingabe prüfen", "Bitte Zahleneingabe prüfen!");
            e.printStackTrace();
        }
        catch(Exception e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
        ds = new ObjectDataSource(articles.list(), Article.class, "nr", "name", "ean", "amount", "category", "price");
 
        mainTable.setItems(tempList);
        mainTable.setItems(ds.getData());
        mainTable.layout();
        mainTable.getSortOrder().add(mainTable.getColumns().get(0));
        mainTable.getSortOrder().clear();
        
        txtCheckOutArticle.clear();
        txtCheckOutNummer.clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        btnCheckOutRoger.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnCheckOutRoger.setEffect(shadow);
            }
        
       
        });
        btnCheckOutRoger.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnCheckOutRoger.setEffect(null);
            }
        
       
        });
        
        
        mainTable = new TableView<Article>();
        try
        {
            ds = new ObjectDataSource(articles.list(), Article.class, "nr", "name", "ean", "amount", "category", "price");
            mainTable.setItems(ds.getData());
            mainTable.getColumns().addAll(ds.getColumns());
            
            
            if(!vBoxCheckOutArticlelist.getChildren().isEmpty())
            {
                vBoxCheckOutArticlelist.getChildren().clear();
            }
            
            vBoxCheckOutArticlelist.getChildren().add(mainTable);
            
            mainTable.setContextMenu(new ArtiVerContextMenu("ArticleListRep"));
        }
        catch(Exception e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
        
        mainTable.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
        {
        @Override public void handle(MouseEvent e) 
            {
                txtCheckOutArticle.setText(mainTable.getSelectionModel().getSelectedItem().getNr());
            }   
        });
    }    
}
