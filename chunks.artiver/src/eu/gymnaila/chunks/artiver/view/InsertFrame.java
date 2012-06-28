/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.controller.Articlemanagement;
import eu.gymnaila.chunks.artiver.controller.CategoryController;
import eu.gymnaila.chunks.artiver.controller.StateController;
import eu.gymnaila.chunks.artiver.controller.StockController;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.entity.Article;
import eu.gymnaila.chunks.artiver.entity.Category;
import eu.gymnaila.chunks.artiver.entity.State;
import eu.gymnaila.chunks.artiver.entity.Stock;
import eu.gymnaila.chunks.artiver.exceptions.ArticleAlreadyExistsException;
import eu.gymnaila.chunks.artiver.exceptions.ArticleDoesNotExistException;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import eu.gymnaila.chunks.artiver.tooling.Numbers;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

/**
 *
 * @author Thunderbolt
 */
public class InsertFrame implements Initializable 
{
    
    @FXML
    private ScrollPane scrInsert;
    @FXML
    private TextField txtGridPaneInsertPropertys;
    @FXML
    private TextField txtGridPaneInsertName;
    @FXML
    private TextField txtGridPaneInsertNumber;
    @FXML
    private ListView lstGridPaneInsertPropertysNonEdit;
    @FXML
    private ChoiceBox cbxGridPaneInsertCategory;
    @FXML
    private ChoiceBox cbxGridPaneInsertPlace;
    @FXML
    private ChoiceBox cbxGridPaneInsertStatus;
    @FXML
    private ChoiceBox cbxGridPaneInsertPropertys;
    @FXML
    private Label lblRogerMessage;
    @FXML
    private Button btnInsertFrameRoger;
    @FXML 
    private ImageView arrowRed;
    @FXML 
    private ImageView arrowGreen;
    
    private DropShadow shadow = new DropShadow();
    
    
    private Articlemanagement articles =  new Articlemanagement();
    
    private StockController stock = new StockController();
    
    private StateController state = new StateController();
    
    private CategoryController category = new CategoryController();
    
    
    
    @FXML
    private void btnActionInsertFrameRoger(ActionEvent event) 
    {   
        System.out.println("Bestätigen");
    
        ObservableList<String> tempList = lstGridPaneInsertPropertysNonEdit.getItems();
        HashMap<String,String> tempMap = new HashMap();
        
        System.out.println(articles.list().size());
        
        double price = 0;
        String ean = "";
        int amount = 0;
        String colourCode = "";
        int categoryId;
        int stockId;
        int stateId;
        
        // TODO: Keine doppelten Properties zulassen!!!
        
        
        for(int i=0; i< tempList.size(); i++)
        {
            String tempString[] = tempList.get(i).split(": ");
            tempMap.put(tempString[0],tempString[1]);
        }
        
        if(tempMap.containsKey("Preis"))
        {
            price = Numbers.parseDouble(tempMap.get("Preis"));
        }
        
        if(tempMap.containsKey("EAN"))
        {
            ean = tempMap.get("EAN");
        }
        
        if(tempMap.containsKey("Anzahl"))
        {
            amount = Numbers.parseInt(tempMap.get("Anzahl"));
        }
        
        if(tempMap.containsKey("Farbe"))
        {
            colourCode = tempMap.get("Farbe");
        }
        Category tempCat = (Category) cbxGridPaneInsertCategory.getSelectionModel().getSelectedItem();
        categoryId = tempCat.getIdCategory().intValue();
        
        State tempSta = (State) cbxGridPaneInsertStatus.getSelectionModel().getSelectedItem();
        stateId = tempSta.getIdState().intValue();
        
        Stock tempSto = (Stock) cbxGridPaneInsertPlace.getSelectionModel().getSelectedItem();
        stockId = tempSto.getIdStock().intValue();
        try {
            if(AppConfig.getEditArticle() != null)
            {
                Article tArt = AppConfig.getEditArticle();
                
                AppConfig.setEditArticle(null);
                
                tArt.setAmount(amount);
                tArt.setCategory(tempCat);
                tArt.setColourCode(colourCode);
                tArt.setState(tempSta);
                tArt.setEan(ean);
                tArt.setName(txtGridPaneInsertName.getText());
                tArt.setNr(txtGridPaneInsertNumber.getText());
                tArt.setPrice(price);
                tArt.setStock(tempSto);
               
                articles.edit(tArt);
                
                
                
            }
            else
            {
                articles.insert(txtGridPaneInsertName.getText(), txtGridPaneInsertNumber.getText(), price, ean, amount, colourCode, categoryId, stockId, stateId);
            }
            
            txtGridPaneInsertName.clear();
            txtGridPaneInsertNumber.clear();
            lstGridPaneInsertPropertysNonEdit.getItems().clear();
            txtGridPaneInsertPropertys.clear();
            cbxGridPaneInsertPlace.getSelectionModel().clearSelection();
            cbxGridPaneInsertStatus.getSelectionModel().clearSelection();
            cbxGridPaneInsertCategory.getSelectionModel().clearSelection();
            
            animateMessage();
            
        } catch (ArticleAlreadyExistsException e) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        } catch (ArticleDoesNotExistException e) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
        catch (Exception e)
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", e.toString());
            e.printStackTrace();
        }
        
        
    }
    
    @FXML
    private void imgActionArrowRed(MouseEvent event) 
    {   
        System.out.println("Pfeil-Klick Rot");
        
        if(!lstGridPaneInsertPropertysNonEdit.getItems().isEmpty())
        {   
            String stringTemp;
            
            if(lstGridPaneInsertPropertysNonEdit.getSelectionModel().isEmpty())
            {        
                stringTemp = lstGridPaneInsertPropertysNonEdit.getItems().get(lstGridPaneInsertPropertysNonEdit.getItems().size()-1).toString();
                lstGridPaneInsertPropertysNonEdit.getItems().remove(stringTemp);
            }
            else
            {    
                stringTemp = (String)lstGridPaneInsertPropertysNonEdit.getSelectionModel().getSelectedItem();
                lstGridPaneInsertPropertysNonEdit.getItems().remove(stringTemp);
            }
            
            String stringProps[] = stringTemp.split(": ");
            
            txtGridPaneInsertPropertys.setText(stringProps[1]);
            
            cbxGridPaneInsertPropertys.getItems().add(stringProps[0]);
            cbxGridPaneInsertPropertys.getSelectionModel().select(stringProps[0]);
        }
        
    }
    
    @FXML
    private void imgActionArrowGreen(MouseEvent event) 
    {   
        System.out.println("Pfeil-Klick Grün");
        
        if(!txtGridPaneInsertPropertys.getText().equals("") && !cbxGridPaneInsertPropertys.getItems().isEmpty())
        {
            try
            {
                String temp = txtGridPaneInsertPropertys.getText();
                //TODO Remove this... for debugging
                System.out.println(temp);

                String prop = (String)cbxGridPaneInsertPropertys.getSelectionModel().getSelectedItem();

                if(prop.equals("Preis"))
                {
                    if(!Numbers.isDouble(temp))
                    {
                        throw new NumberFormatException();
                    }
                }
                if(prop.equals("Anzahl"))
                {
                    if(!Numbers.isInt(temp))
                    {
                        throw new NumberFormatException();
                    }
                }

                lstGridPaneInsertPropertysNonEdit.getItems().add(prop + ": " + temp);

                System.out.println(temp);

                cbxGridPaneInsertPropertys.getItems().remove(prop);

                txtGridPaneInsertPropertys.clear();
            }
            catch(NumberFormatException e)
            {
               ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Eingabe prüfen", "Bitte Zahleneingabe prüfen!");
               e.printStackTrace();
            }
        } 
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
//        scrInsert.setMinHeight((GuiPrototyp.screenBounds.getHeight() * 0.9) - 105);
//        scrInsert.setPrefHeight((GuiPrototyp.screenBounds.getHeight() * 0.9));
//        scrInsert.setMaxHeight(GuiPrototyp.screenBounds.getHeight());
        
        btnInsertFrameRoger.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnInsertFrameRoger.setEffect(shadow);
            }
        
       
        });
        btnInsertFrameRoger.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnInsertFrameRoger.setEffect(null);
            }
        
       
        });
        
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
        
        
        
        // TODO
        lstGridPaneInsertPropertysNonEdit.setEditable(false);
 
        cbxGridPaneInsertCategory.setItems(FXCollections.observableArrayList(category.list()));

        cbxGridPaneInsertPlace.setItems(FXCollections.observableArrayList(stock.list()));

        cbxGridPaneInsertStatus.setItems(FXCollections.observableArrayList(state.list()));

        cbxGridPaneInsertPropertys.setItems(FXCollections.observableArrayList("Preis", "EAN", "Anzahl", "Farbe"));

        
        if(AppConfig.getEditArticle() != null)
        {
            Article tempArt = AppConfig.getEditArticle();
        
            txtGridPaneInsertPropertys.setText("");
            txtGridPaneInsertName.setText(tempArt.getName());
            txtGridPaneInsertNumber.setText(tempArt.getNr());
            txtGridPaneInsertNumber.setDisable(true);
            
            if(tempArt.getAmount() != 0)
            {
                lstGridPaneInsertPropertysNonEdit.getItems().add("Anzahl: " + tempArt.getAmount());
            }
            
            if(!tempArt.getColourCode().equals(""))
            {
                lstGridPaneInsertPropertysNonEdit.getItems().add("Farbe: " + tempArt.getColourCode());
            }
             
            if(!tempArt.getEan().equals(""))
            {
                lstGridPaneInsertPropertysNonEdit.getItems().add("EAN: " + tempArt.getEan());
            }
              
            if(tempArt.getPrice() != 0)
            {
                lstGridPaneInsertPropertysNonEdit.getItems().add("Preis: " + tempArt.getPrice());
            }
                      
            cbxGridPaneInsertCategory.getSelectionModel().select(tempArt.getCategory());
            cbxGridPaneInsertPlace.getSelectionModel().select(tempArt.getStock());
            cbxGridPaneInsertStatus.getSelectionModel().select(tempArt.getState());
        }    
        
                
    }
    
    
    
    private void animateMessage() throws InterruptedException {
        FadeTransition ft = new FadeTransition(new Duration(600), lblRogerMessage);
        ft.setFromValue(0.0);
        ft.setToValue(1);        
        ft.play();
        
    }
    
    

}
