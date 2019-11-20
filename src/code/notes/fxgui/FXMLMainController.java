/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.notes.fxgui;

import code.notes.util.FileChooserDialog;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

/**
 *
 * @author phwts
 */
public class FXMLMainController implements Initializable {

    private static final ArrayList<EditorTab> tab_pool = new ArrayList<>();

    @FXML
    private TabPane tab_pane;

    @FXML
    private void handleNewFileAction(ActionEvent event) {
        addTab();
    }
    
    @FXML
    private void handleOpenAction(ActionEvent event) {
        // TODO: Make addTab handle multiple files by itself
        List<Path> paths = FileChooserDialog.openFiles(CodeNotes.STAGE);
        if(paths != null)
            for (Path path : paths)
                this.addTab(path);
    }
    
    @FXML
    private void handleSaveAction(ActionEvent event) {
        EditorTab selected_tab = (EditorTab) tab_pane.getSelectionModel().getSelectedItem();
        selected_tab.getEDITOR().save();
    }
    
    @FXML
    private void handleSaveAsAction(ActionEvent event) {
        EditorTab selected_tab = (EditorTab) tab_pane.getSelectionModel().getSelectedItem();
        selected_tab.getEDITOR().saveAs();
    }
    
    @FXML
    private void handleExceptionLookupMenu(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLExceptionLookup.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
    @FXML
    private void handlePreferencesMenu(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPreferences.fxml"));
        
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Add SingleEditor to editor_pool
     * @param tab
     */
    public static void addTabPool(EditorTab tab) {
        tab_pool.add(tab);
    }

    /**
     * Remove SingleEditor from editor_pool
     * @param tab
     */
    public static void removeTabPool(EditorTab tab) {
        tab_pool.remove(tab);
    }
    
    public ArrayList<EditorTab> getTabPool() {
        return tab_pool;
    }

    private void addTab() {
        EditorTab editor_tab = new EditorTab();
        tab_pane.getTabs().add(editor_tab);
        tab_pane.getSelectionModel().select(editor_tab);

        addTabPool(editor_tab);
    }

    private void addTab(Path path) {
        for (EditorTab editor : tab_pool) {
            if (editor.getEDITOR().isSameFile(path)) {
                tab_pane.getSelectionModel().select(editor);
                return;
            }
        }
        
        EditorTab editor_tab = new EditorTab(path);
        tab_pane.getTabs().add(editor_tab);
        tab_pane.getSelectionModel().select(editor_tab);

        addTabPool(editor_tab);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addTab();
    }

}
