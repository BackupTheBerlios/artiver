/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.main;

import com.sun.javafx.tk.Toolkit;
import eu.gymnaila.chunks.artiver.config.AppConfig;
import eu.gymnaila.chunks.artiver.controls.ModalWarningDialog;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author Thunderbolt
 */
public class GuiPrototyp extends Application 
{
    private Stage stage;
    
    private Image logo;
    
    private static GuiPrototyp instance;
    
    public static Rectangle2D screenBounds;
    
    @FXML
    private Label lblMainFrameTitleLabel;
    
    
    @FXML private VBox vBoxMain;
   // private VBox VBox;

    public GuiPrototyp() {
        instance = this;
    }
    
    public static GuiPrototyp getInstance() {
        return instance;
    }
    
    public static void main(String[] args) {
        Application.launch(GuiPrototyp.class, args);
    }
    
    

    private void doExit(Event e) {
        AppConfig.close();
        e.consume();
        System.exit(0);
    }

    /**
	 * Configures the Logger with log4j.properties found at LOG4J_PATH in Analyzer.properties
	 */
	private void configureLogger(){
		
		BasicConfigurator.configure();
		
		File f = null;
		
		URL url = GuiPrototyp.class.getClassLoader().getResource("/eu/gymnaila/chunks/artiver/libs/log4j.properties");
		
		if(url == null){
			f = new File("/eu/gymnaila/chunks/artiver/libs/log4j.properties");
		}
		else{
			f = new File(url.getPath());
		}
		
		
			System.out.println("loggerpath: "+"/eu/gymnaila/chunks/artiver/libs/log4j.properties");
			PropertyConfigurator.configure(f.getPath());
			
		

	}
    
    @Override
    public void start(Stage primStage) throws Exception {
       
        configureLogger();
        
        stage=primStage;  
  

        EventHandler<WindowEvent> ehwe;
        ehwe = new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent we) {
                doExit(we);
            }
        };
        stage.setOnCloseRequest(ehwe);

        // IMPORTANT: This path (and every other path, pointing to a .fxml-file) has to be absolute WITH leading '/' !
        Parent root = FXMLLoader.load(GuiPrototyp.class.getResource("/eu/gymnaila/chunks/artiver/view/fxml/LogInChoose.fxml"));
        Scene scene = new Scene(root,500,400);
        
        // IMPORTANT: This path (and every other path, pointing to a .css-file) has to be absolute WITHOUT leading '/' !
        scene.getStylesheets().add("eu/gymnaila/chunks/artiver/view/css/logincss.css");
        stage.setScene(scene);
        stage.setTitle("ArtiVer Anmeldung");
        
        logo = new Image(GuiPrototyp.class.getResource("/eu/gymnaila/chunks/artiver/main/logokiste_transparent.PNG").getPath());
        
        stage.getIcons().add(logo);
        
        
        
        stage.show();
        
        
        
        // TODO Exception handling for that method
        AppConfig.init();
    }
    
    //This method shows the User Log In
    public void gotoUserLogIn() 
    {
        try 
        {
            replaceSceneContent("/eu/gymnaila/chunks/artiver/view/fxml/UserLogIn.fxml");
        } 
        catch (Exception ex) 
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    //This method shows the Frame in which you can choose the way you want to acces the MainFrame
    public void gotoLogInChoose() 
    {
        try 
        {
            replaceSceneContent("/eu/gymnaila/chunks/artiver/view/fxml/LogInChoose.fxml");
        } 
        catch (Exception ex) 
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    //This method shows the Frame for an LogIn with a RFID-Card
     public void gotoRFIDLogIn() 
    {
        try 
        {
            replaceSceneContent("/eu/gymnaila/chunks/artiver/view/fxml/RFIDLogIn.fxml");
        } 
        catch (Exception ex) 
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    //This method show the main part of the programm, the mainFrame 
    public void gotoMainFrame() 
    {
        try 
        {
            createMainContent("/eu/gymnaila/chunks/artiver/view/fxml/MainFrame.fxml");
        } 
        catch (Exception ex) 
        {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
      
    
    //This method is used to switch between the logIn-Frames. It importes the different fxml-files and styles them with the "logincss.css" Stylesheet
    private Parent replaceSceneContent(String fxml) throws Exception 
    {
        Parent page = (Parent) FXMLLoader.load(GuiPrototyp.class.getResource(fxml), null, new JavaFXBuilderFactory());
        Scene scene = stage.getScene();
        if (scene == null) 
        {
            scene = new Scene(page, 700, 450);
            scene.getStylesheets().add("eu/gymnaila/chunks/artiver/view/css/logincss.css");
            stage.setScene(scene);                                      
        } 
        else 
        {
            stage.getScene().setRoot(page);
        }
        
        stage.sizeToScene();
        return page;
    }
    
    
    //This method is used to switch from the logIn-Frames to the MainFrame
    private Parent createMainContent(String fxml) throws Exception
    {
        Scene scene;
        
        Parent page = (Parent) FXMLLoader.load(GuiPrototyp.class.getResource(fxml), null, new JavaFXBuilderFactory());
        
        // Creating a rectangle that exactly fits the screen
        screenBounds = Screen.getPrimary().getVisualBounds();
        
        // Creating a new scene with 90% of the screenbounds
        scene = new Scene(page, screenBounds.getWidth() * (0.9), screenBounds.getHeight() * (0.9));
        
        // Clearing stylesheets of the new scene (not that important)
        scene.getStylesheets().clear();
        
        // Adding new stylesheet
        scene.getStylesheets().add("eu/gymnaila/chunks/artiver/view/css/mainFrame.css");
       
        // As already known from above...
        stage.setScene(scene);
        
        // Center screen looked better
        stage.centerOnScreen();

        // Stage set to fit the scene's size
        stage.sizeToScene();
        
        stage.setTitle("ArtiVer");
        
        return page;
    }
    
    
    //This method builds the CheckIn
    public void buildCheckIn(VBox aVBox) 
     {
        try {
            vBoxMain = aVBox;
            replacePanelContent("/eu/gymnaila/chunks/artiver/view/fxml/CheckIn.fxml");
        } catch (Exception ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    //This method builds the CheckOut
    public void buildCheckOut(VBox aVBox) 
     {
        try {
            vBoxMain = aVBox;
            replacePanelContent("/eu/gymnaila/chunks/artiver/view/fxml/CheckOut.fxml");
        } catch (Exception ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    //This methods builds the Articlelist
    public void buildListFrame(VBox aVBox) 
     {
        try {
            vBoxMain = aVBox;
            replacePanelContent("/eu/gymnaila/chunks/artiver/view/fxml/ListFrame.fxml");
        } catch (Exception ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    //This method builds the InsertFrame
    public void buildInsertFrame(VBox aVBox) 
     {
        try {
            vBoxMain = aVBox;
            replacePanelContent("/eu/gymnaila/chunks/artiver/view/fxml/InsertFrame.fxml");
        } catch (Exception ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    //This method builds the ChangeFrame
    public void buildChangeFrame() 
     {
         //TODO: die Sache mit dem Label und den exceptions...
        try {
            replacePanelContent("/eu/gymnaila/chunks/artiver/view/fxml/InsertFrame.fxml");
            
            Label lblTemp = (Label) stage.getScene().lookup(".titleLabel");
    
            if (lblTemp != null)
            {
                lblTemp.setText("Artikel ändern");
            }
            
        } catch (Exception ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    
    
    //This method builds the Frame with which the admin can create new Rightgroups
    public void buildGiveRights(VBox aVBox) 
     {
        try {
            vBoxMain = aVBox;
            replacePanelContent("/eu/gymnaila/chunks/artiver/view/fxml/GiveRights.fxml");
        } catch (Exception ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    //This method builds the Frame with which you can create new Users and Give them Rights
    public void buildUsersFrame(VBox aVBox) 
     {
        try {
            vBoxMain = aVBox;
            replacePanelContent("/eu/gymnaila/chunks/artiver/view/fxml/UsersFrame.fxml");
        } catch (Exception ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    
    //This method builds the Frame with which you can see the List of the Categories and create new ones
    public void buildCategoryList(VBox aVBox) 
     {
        try {
            vBoxMain = aVBox;
            replacePanelContent("/eu/gymnaila/chunks/artiver/view/fxml/CategoryList.fxml");
        } catch (Exception ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    //This method builds the Frame with which you can see the List of the States and create new ones
    public void buildStateList(VBox aVBox) 
     {
        try {
            vBoxMain = aVBox;
            replacePanelContent("/eu/gymnaila/chunks/artiver/view/fxml/StateList.fxml");
        } catch (Exception ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    //This method builds the Frame with which you can see the List of the Stocks and create new ones
    public void buildStockList(VBox aVBox) 
     {
        try {
            vBoxMain = aVBox;
            replacePanelContent("/eu/gymnaila/chunks/artiver/view/fxml/StockList.fxml");
        } catch (Exception ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    //This method builds the frame which creates the invoices
    public void buildInvoiceFrame(VBox aVBox) 
     {
        try {
            vBoxMain = aVBox;
            replacePanelContent("/eu/gymnaila/chunks/artiver/view/fxml/InvoiceFrame.fxml");
        } catch (Exception ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    //This method builds the SettingsFrame
    public void buildSettings(VBox aVBox) 
     {
        try {
            vBoxMain = aVBox;
            replacePanelContent("/eu/gymnaila/chunks/artiver/view/fxml/Settings.fxml");
        } catch (Exception ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    //This method builds the CustomersFrame
    public void buildCustomersFrame(VBox aVBox) 
     {
        try {
            vBoxMain = aVBox;
            replacePanelContent("/eu/gymnaila/chunks/artiver/view/fxml/CustomersFrame.fxml");
        } catch (Exception ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    public void buildInvoiceList(VBox aVBox) 
     {
        try {
            vBoxMain = aVBox;
            replacePanelContent("/eu/gymnaila/chunks/artiver/view/fxml/InvoiceList.fxml");
        } catch (Exception ex) {
            ModalWarningDialog m = new ModalWarningDialog(GuiPrototyp.getInstance().getStage(), "Fehler", ex.toString());
            ex.printStackTrace();
        }
    }
    
    
    //This method is used to show the different Frames in the middle of the MainFrame. It needs the new Frame given as an fxml-file.
    private void replacePanelContent(String fxml) throws Exception 
    {
        if(!vBoxMain.getChildren().isEmpty())
        {
            vBoxMain.getChildren().clear();
        }
        
        vBoxMain.getChildren().addAll( (Parent) FXMLLoader.load(GuiPrototyp.class.getResource(fxml), null, new JavaFXBuilderFactory()));
    }
     
     
    //This method returns the current stage.
    public Stage getStage()
    {
       return stage;
    }
     
}
