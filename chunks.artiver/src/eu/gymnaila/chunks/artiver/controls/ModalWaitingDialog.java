/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.gymnaila.chunks.artiver.controls;


/**
 *
 * @author User
 */
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;

public class ModalWaitingDialog
{

    ProgressIndicator prgInd;
    final Stage stage;
 
    public ModalWaitingDialog(final Stage stg, String title, String message)
    {
        prgInd = new ProgressIndicator();

        stage = new Stage();
        //Initialize the Stage with type of modal
        stage.initModality(Modality.APPLICATION_MODAL);
        //Set the owner of the Stage 
        stage.initOwner(stg);
        stage.setTitle(title);
        VBox root = new VBox();
       
        Label text = new Label(message);
 
        root.getChildren().add(text);
        root.getChildren().add(prgInd);
        
        root.setPadding(new Insets(7,7,7,7));
        root.setSpacing(7);
        root.setAlignment(Pos.CENTER);
        //root.setPrefWidth(200);
        root.autosize();
        Scene scene = new Scene(root);
        scene.getStylesheets().add(stg.getScene().getStylesheets().get(0));

        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.centerOnScreen();
        stage.sizeToScene();
        stage.show();
        
    }
    
    public void close()
    {
        stage.close();
    }
}
