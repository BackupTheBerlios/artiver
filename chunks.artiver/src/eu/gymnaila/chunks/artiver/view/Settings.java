/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.view;

import eu.gymnaila.chunks.artiver.controller.MasterDataController;
import eu.gymnaila.chunks.artiver.exceptions.PreexistingEntityException;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import eu.gymnaila.chunks.artiver.entity.MasterData;
import eu.gymnaila.chunks.artiver.main.GuiPrototyp;
import eu.gymnaila.chunks.artiver.tooling.Numbers;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Thunderbolt
 */
public class Settings implements Initializable {
    
    
    @FXML
    private TextField txtSettingsName;
    @FXML
    private TextField txtSettingsForm;
    @FXML
    private TextField txtSettingsContact;
    @FXML
    private TextField txtSettingsStreet;
    @FXML
    private TextField txtSettingsPostalCode;
    @FXML
    private TextField txtSettingsMwSt;
    @FXML
    private TextField txtSettingsTaxNumber;
    @FXML
    private TextField txtSettingsUStIdNr;
    @FXML
    private TextField txtSettingsOrderPre;
    @FXML
    private TextField txtSettingsInvoicePre;
    @FXML
    private TextField txtSettingsDeliveryPre;
    @FXML
    private TextArea txaSettingsTerms;
    @FXML
    private Button btnSettingsRoger;
    
    private DropShadow shadow = new DropShadow();
    
    @FXML 
    private ScrollPane scrSettings;
    
    private MasterData tempMaster = null;
    private MasterDataController mdc = new MasterDataController();
    
    @FXML
    private void btnActionSettingsRoger(ActionEvent event) {
        System.out.println("Bestätigen");
        
        if(tempMaster != null)
        {
            double mwSt = Numbers.parseDouble(txtSettingsMwSt.getText());
            
            tempMaster.setIdMasterData(1);
            
            tempMaster.setCompanyName(txtSettingsName.getText());
            tempMaster.setCorporateForm(txtSettingsForm.getText());
            tempMaster.setContact(txtSettingsContact.getText());
            tempMaster.setStreet(txtSettingsStreet.getText());
            tempMaster.setPostalCode(txtSettingsPostalCode.getText());
            tempMaster.setVat(mwSt);
            tempMaster.setTaxID(txtSettingsTaxNumber.getText());
            tempMaster.setUstID(txtSettingsUStIdNr.getText());
            tempMaster.setOfferPrefix(txtSettingsOrderPre.getText());
            tempMaster.setInvoicePrefix(txtSettingsInvoicePre.getText());
            tempMaster.setDeliveryNotePrefix(txtSettingsDeliveryPre.getText());
            tempMaster.setTerms(txaSettingsTerms.getText());
           
            try 
            {
                mdc.edit(tempMaster);
            } 
            catch (PreexistingEntityException ex) 
            {
                ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
                ex.printStackTrace();
            }
            catch (Exception ex) 
            {
                ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
                ex.printStackTrace();
            }
        }
        else
        {
            double mwSt = Numbers.parseDouble(txtSettingsMwSt.getText());
            
            tempMaster = new MasterData(1);
            
            tempMaster.setCompanyName(txtSettingsName.getText());
            tempMaster.setCorporateForm(txtSettingsForm.getText());
            tempMaster.setContact(txtSettingsContact.getText());
            tempMaster.setStreet(txtSettingsStreet.getText());
            tempMaster.setPostalCode(txtSettingsPostalCode.getText());
            tempMaster.setVat(mwSt);
            tempMaster.setTaxID(txtSettingsTaxNumber.getText());
            tempMaster.setUstID(txtSettingsUStIdNr.getText());
            tempMaster.setOfferPrefix(txtSettingsOrderPre.getText());
            tempMaster.setInvoicePrefix(txtSettingsInvoicePre.getText());
            tempMaster.setDeliveryNotePrefix(txtSettingsDeliveryPre.getText());
            tempMaster.setTerms(txaSettingsTerms.getText());
           
            try 
            {
                mdc.create(tempMaster);
            } 
            catch (PreexistingEntityException ex) 
            {
                ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
                ex.printStackTrace();
            }
            catch (Exception ex) 
            {
                ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
                ex.printStackTrace();
            }
        }

        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        scrSettings.setMinHeight((GuiPrototyp.screenBounds.getHeight() * 0.9) - 105);
        scrSettings.setPrefHeight((GuiPrototyp.screenBounds.getHeight() * 0.9));
        scrSettings.setMaxHeight(GuiPrototyp.screenBounds.getHeight());

        txaSettingsTerms.setDisable(false);
        txaSettingsTerms.setText(" ");
        
        tempMaster = mdc.getMasterData();
        
        if(tempMaster != null)
        {
            txtSettingsName.setText(tempMaster.getCompanyName());
            txtSettingsForm.setText(tempMaster.getCorporateForm());
            txtSettingsContact.setText(tempMaster.getContact());
            txtSettingsStreet.setText(tempMaster.getStreet());
            txtSettingsPostalCode.setText(tempMaster.getPostalCode());
            txtSettingsMwSt.setText(Numbers.toString(tempMaster.getVat()));
            txtSettingsTaxNumber.setText(tempMaster.getTaxID());
            txtSettingsUStIdNr.setText(tempMaster.getUstID());
            txtSettingsOrderPre.setText(tempMaster.getOfferPrefix());
            txtSettingsInvoicePre.setText(tempMaster.getInvoicePrefix());
            txtSettingsDeliveryPre.setText(tempMaster.getDeliveryNotePrefix());
            txaSettingsTerms.setText(tempMaster.getTerms());
        }
        
        btnSettingsRoger.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnSettingsRoger.setEffect(shadow);
            }
        
       
        });
        btnSettingsRoger.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() 
        {
        @Override public void handle(MouseEvent e) 
            {
                btnSettingsRoger.setEffect(null);
            }
        
       
        });

    }    
}
