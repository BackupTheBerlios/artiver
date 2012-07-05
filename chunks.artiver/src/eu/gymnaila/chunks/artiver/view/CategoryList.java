/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.controller.Articlemanagement;
import eu.gymnaila.chunks.artiver.controller.CategoryController;
import eu.gymnaila.chunks.artiver.controls.ArtiVerContextMenu;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.controls.ModalYesNoDialog;
import eu.gymnaila.chunks.artiver.entity.Article;
import eu.gymnaila.chunks.artiver.entity.Category;
import eu.gymnaila.chunks.artiver.exceptions.ArticleDoesNotExistException;
import eu.gymnaila.chunks.artiver.exceptions.CategoryAlreadyExistsException;
import eu.gymnaila.chunks.artiver.exceptions.CategoryConnectedToArticleException;
import eu.gymnaila.chunks.artiver.exceptions.CategoryNotFoundException;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import java.net.URL;
import java.util.HashMap;
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
public class CategoryList implements Initializable {
    
    private TableView<Category> mainTable;
    @FXML
    private VBox vBoxCategoryListList;
    
    private ObjectDataSource ds;
    
    @FXML
    private TextField txtInsertCategoryName;
    @FXML 
    private TextField txtInsertCategoryDesc;
    @FXML 
    private Button btnInsertCategoryRoger;
    @FXML 
    private Button btnCategoryListErease;
    @FXML 
    private Button btnCategoryListChange;        
    
    private DropShadow shadow = new DropShadow();
    
    private ObservableList<Category> tempList = FXCollections.observableArrayList();
    
    private Category tempCat = null;
    
    private CategoryController category = new CategoryController();
    
    @FXML
    private void btnActionCategoryListErease(ActionEvent event) 
    {
        System.out.println("Kategorie löschen");
        
        ModalYesNoDialog yesNo = new ModalYesNoDialog(GuiPrototyp.getInstance().getStage(), "Warnung", "Wollen sie wirklich löschen?");
        EventHandler<ActionEvent> ehaeYes = new EventHandler<ActionEvent>() 
        {

            @Override
            public void handle(ActionEvent arg0) 
            {
        
        
                    try 
                    {
                            category.deleteCategory(mainTable.getSelectionModel().getSelectedItem());
            
                                ds = new ObjectDataSource(category.list(), Category.class, "name", "description");
                                mainTable.setItems(tempList);
                                mainTable.setItems(ds.getData());
                    
                    } 
                    catch (CategoryConnectedToArticleException e) 
                    {
                        ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
                        e.printStackTrace();
                    }
                    catch (CategoryNotFoundException e) {
                        ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
                        e.printStackTrace();
                    }
        
            }
            
        };
        
        yesNo.setOnYes(ehaeYes);
        yesNo.show();
        
    }
    
    @FXML
    private void btnActionCategoryListChange(ActionEvent event) {
        System.out.println("Kategorie ändern");
        
        tempCat = mainTable.getSelectionModel().getSelectedItem();
        
        txtInsertCategoryName.setText(tempCat.getName());
        
        txtInsertCategoryDesc.setText(tempCat.getDescription());
    }
    
    @FXML
    private void btnActionInsertCategoryRoger(ActionEvent event) {
        System.out.println("Kategorie einfügen");
        
    if(tempCat == null)
    {
        
        String tempName = txtInsertCategoryName.getText();
        
        String tempDesc = txtInsertCategoryDesc.getText();
        
        try {
            category.addCategory(tempName, tempDesc);
            
           
            
        } catch (CategoryAlreadyExistsException e) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }  
        
        txtInsertCategoryName.clear();
        
        txtInsertCategoryDesc.clear();
    }
    else
    {
        
        String name = txtInsertCategoryName.getText();
        String desc = txtInsertCategoryDesc.getText();
       
       tempCat.setName(name);
       tempCat.setDescription(desc);
           
           try {
                category.edit(tempCat);
            } catch (CategoryAlreadyExistsException e) {
                ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
                e.printStackTrace();
            } catch (CategoryNotFoundException e) {
                ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
                e.printStackTrace();
            }
       
       tempCat = null;
       
       txtInsertCategoryName.clear();
        
       txtInsertCategoryDesc.clear();

    }
    
     ds = new ObjectDataSource(category.list(), Category.class, "name", "description");
                    mainTable.setItems(tempList);
                    mainTable.setItems(ds.getData());
                    mainTable.getSortOrder().add(mainTable.getColumns().get(0));
        mainTable.getSortOrder().clear();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        btnInsertCategoryRoger.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnInsertCategoryRoger.setEffect(shadow);
            }
        
       
        });
        btnInsertCategoryRoger.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnInsertCategoryRoger.setEffect(null);
            }
        
       
        });
        
        btnCategoryListChange.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnCategoryListChange.setEffect(shadow);
            }
        
       
        });
        btnCategoryListChange.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnCategoryListChange.setEffect(null);
            }
        
       
        });
        
        btnCategoryListErease.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnCategoryListErease.setEffect(shadow);
            }
        
       
        });
        btnCategoryListErease.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnCategoryListErease.setEffect(null);
            }
        
       
        });
        
        
        mainTable = new TableView<Category>();
        try
        {
            ds = new ObjectDataSource(category.list(), Category.class, "name", "description");
            mainTable.setItems(ds.getData());
            mainTable.getColumns().addAll(ds.getColumns());
            
            
            if(!vBoxCategoryListList.getChildren().isEmpty())
            {
                vBoxCategoryListList.getChildren().clear();
            }
            
            vBoxCategoryListList.getChildren().add(mainTable);
        
            mainTable.setContextMenu(new ArtiVerContextMenu("CategoryListRep"));
        }
        catch(Exception e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
    }    
}
