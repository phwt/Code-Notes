/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.notes.gui;

import code.notes.Bundle;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author phwts
 */
public class CodeNotes extends Application {

    public static Stage STAGE;
    public static Image ICON = new Image(CodeNotes.class.getResourceAsStream("/code/notes/resources/icon.png"));

    @Override
    public void start(Stage stage) throws Exception {
        ResourceBundle resources = ResourceBundle.getBundle("code.notes.Bundle", Bundle.getLocale());
        Parent root = new FXMLLoader().load(getClass().getResource("FXMLMain.fxml"), resources);
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Code-Notes");
        CodeNotes.STAGE = stage;

        stage.getIcons().add(ICON);
        
        stage.setOnCloseRequest(e -> {
            for (EditorTab tab : FXMLMainController.getTabPool()) {
                if (!tab.getEDITOR().getSaveState()) {
                    tab.unsavedPrompt(e);
                }
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        code.notes.util.UserPreferences.resetPreferences();
        launch(args);
    }

}
