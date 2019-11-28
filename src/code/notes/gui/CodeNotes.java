/*
 * Code-Notes | EditorTab.java
 * Created | 12:47:04 PM Nov 28, 2019
 */
package code.notes.gui;

import code.notes.util.Bundle;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Run the application and display the main screen
 * 
 * @author phwts
 */
public class CodeNotes extends Application {

    public static Stage STAGE;
    public static final Image ICON = new Image(CodeNotes.class.getResourceAsStream("/code/notes/res/icon.png"));
    public static final ResourceBundle RESOURCES = ResourceBundle.getBundle("code.notes.res.Bundle", Bundle.getLocale());
    public static FXMLMainController main_controller;
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMain.fxml"), RESOURCES);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        main_controller = (FXMLMainController) loader.getController();

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Code-Notes");
        stage.getIcons().add(ICON);
        
        CodeNotes.STAGE = stage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
