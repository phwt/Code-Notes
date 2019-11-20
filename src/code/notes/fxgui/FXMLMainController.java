/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.notes.fxgui;

import code.notes.util.FileChooserDialog;
import code.notes.util.UserPreferences;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.DosFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

/**
 *
 * @author phwts
 */
public class FXMLMainController implements Initializable {

    private static final ArrayList<EditorTab> TAB_POOL = new ArrayList<>();

    @FXML
    private TabPane tab_pane;
    @FXML
    private TreeView dir_tree;

    @FXML
    private void handleNewFileAction(ActionEvent event) {
        addTab();
    }

    @FXML
    private void handleOpenAction(ActionEvent event) {
        // TODO: Make addTab handle multiple files by itself
        List<Path> paths = FileChooserDialog.openFiles(CodeNotes.STAGE);
        if (paths != null) {
            for (Path path : paths) {
                this.addTab(path);
            }
        }
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
    private void handleExceptionLookupMenu(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLExceptionLookup.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void handlePreferencesMenu(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLPreferences.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void loadTree() {
        if (!UserPreferences.getDirPath().isEmpty()) {
            File root_path = new File(UserPreferences.getDirPath());
            TreeItem dir_node = new TreeItem(root_path.getName());
            dir_tree.setRoot(dir_node);
            dir_tree.setShowRoot(false);

            for (File file : root_path.listFiles()) {
                createTree(file, dir_node);
            }
        }
    }

    private void createTree(File root_file, TreeItem parent) {
        try {
            DosFileAttributes attr = Files.readAttributes(root_file.toPath(), DosFileAttributes.class);
            if (attr.isSystem() || attr.isHidden() || attr.isReadOnly()) {
                // Do nothing
            } else if (root_file.isDirectory()) {
                TreeItem node = new TreeItem(root_file.getName());
                parent.getChildren().add(node);
                for (File f : root_file.listFiles()) {
                    TreeItem placeholder = new TreeItem();
                    node.getChildren().add(placeholder);

                    node.addEventHandler(TreeItem.branchExpandedEvent(), new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            createTree(f, node);
                            node.getChildren().remove(placeholder);
                            node.removeEventHandler(TreeItem.branchExpandedEvent(), this);
                        }
                    });
                }
            } else {
                parent.getChildren().add(new TreeItem(root_file.getName()));
            }
        } catch (IOException ex) {
            Logger.getLogger(FXMLMainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Add SingleEditor to editor_pool
     *
     * @param tab
     */
    public static void addTabPool(EditorTab tab) {
        TAB_POOL.add(tab);
    }

    /**
     * Remove SingleEditor from editor_pool
     *
     * @param tab
     */
    public static void removeTabPool(EditorTab tab) {
        TAB_POOL.remove(tab);
    }

    public static ArrayList<EditorTab> getTabPool() {
        return TAB_POOL;
    }

    private void addTab() {
        EditorTab editor_tab = new EditorTab();
        tab_pane.getTabs().add(editor_tab);
        tab_pane.getSelectionModel().select(editor_tab);

        addTabPool(editor_tab);
    }

    private void addTab(Path path) {
        for (EditorTab editor : TAB_POOL) {
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
    
    private void addFileListener() {
         UserPreferences.getPrefs().addPreferenceChangeListener((e) -> {
            if (e.getKey().equals("dir_path")) {
                loadTree();
            }
        });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addTab();
        loadTree();
        addFileListener();
    }

}
