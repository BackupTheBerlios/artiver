/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.controller.CategoryController;
import eu.gymnaila.chunks.artiver.controller.StateController;
import eu.gymnaila.chunks.artiver.controls.ArtiVerContextMenu;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.entity.Article;
import eu.gymnaila.chunks.artiver.entity.Category;
import eu.gymnaila.chunks.artiver.entity.State;
import eu.gymnaila.chunks.artiver.exceptions.CategoryNotFoundException;
import eu.gymnaila.chunks.artiver.exceptions.StateAlreadyExistsException;
import eu.gymnaila.chunks.artiver.exceptions.StateConnectedToArticleException;
import eu.gymnaila.chunks.artiver.exceptions.StateNotFoundException;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
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
public class StateList implements Initializable {
    
    private TableView<State> mainTable;
    @FXML
    private VBox vBoxStateListList;
    @FXML
    private Button btnStateListErease;
    @FXML
    private Button btnStateListChange;
    @FXML
    private Button btnInsertStateRoger;
    
    private ObjectDataSource ds;
    
    @FXML
    private TextField txtInsertStateName;
    @FXML
    private TextField txtInsertStateDescription;
    
    private ObservableList<State> tempList = FXCollections.observableArrayList();
    
    private State tempState = null;
    
    private DropShadow shadow = new DropShadow();
    
    private StateController state = new StateController();
    
    
    @FXML
    private void btnActionStateListErease(ActionEvent event) 
    {
        System.out.println("Status löschen");
        
        try 
        {
            state.deleteState(mainTable.getSelectionModel().getSelectedItem());
            
                    ds = new ObjectDataSource(state.list(), State.class, "name", "description");
                    mainTable.setItems(tempList);
                    mainTable.setItems(ds.getData());
                    
        } catch (StateConnectedToArticleException e) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
            catch (StateNotFoundException e) {
                ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
                e.printStackTrace();
        }
        
    }
    
    @FXML
    private void btnActionStateListChange(ActionEvent event) 
    {
        System.out.println("Status ändern");
        tempState = mainTable.getSelectionModel().getSelectedItem();
        
        txtInsertStateName.setText(tempState.getName());
        txtInsertStateDescription.setText(tempState.getDescription());
    }
    
    @FXML
    private void btnActionInsertStateRoger(ActionEvent event) {
        System.out.println("Status erstellen");
    
        String tempName = txtInsertStateName.getText();
       
        String tempDesc = txtInsertStateDescription.getText();
        
                    
        try {
            if(tempState == null)
            {
                 state.addState(tempName, tempDesc);
            }
            else
            {
                tempState.setName(tempName);
                tempState.setDescription(tempDesc);
                try {
                    state.edit(tempState);
                } catch (StateNotFoundException e) {
                    ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
                    e.printStackTrace();
                }
                
                tempState = null;
            }
           
            
            ds = new ObjectDataSource(state.list(), State.class, "name", "description");
                    mainTable.setItems(tempList);
                    mainTable.setItems(ds.getData());
                    
        } catch (StateAlreadyExistsException e) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
        
        txtInsertStateName.clear();
       
        txtInsertStateDescription.clear();
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
        btnStateListErease.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnStateListErease.setEffect(shadow);
            }
        
       
        });
        btnStateListErease.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnStateListErease.setEffect(null);
            }
        
       
        });
        
        btnStateListChange.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnStateListChange.setEffect(shadow);
            }
        
       
        });
        btnStateListChange.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnStateListChange.setEffect(null);
            }
        
       
        });
        
        btnInsertStateRoger.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnInsertStateRoger.setEffect(shadow);
            }
        
       
        });
        btnInsertStateRoger.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnInsertStateRoger.setEffect(null);
            }
        
       
        });
        
        mainTable = new TableView<State>();
        try
        {
            ds = new ObjectDataSource(state.list(), State.class, "name", "description");
            mainTable.setItems(ds.getData());
            mainTable.getColumns().addAll(ds.getColumns());
            
            
            if(!vBoxStateListList.getChildren().isEmpty())
            {
                vBoxStateListList.getChildren().clear();
            }
            
            vBoxStateListList.getChildren().add(mainTable);
            
            mainTable.setContextMenu(new ArtiVerContextMenu("StateListRep"));
        }
        catch(Exception e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
    }    
}
