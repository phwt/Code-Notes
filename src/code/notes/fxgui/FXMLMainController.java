/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.notes.fxgui;

import code.notes.gui.SingleEditor;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.StackPane;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 *
 * @author phwts
 */
public class FXMLMainController implements Initializable {

    private static ArrayList<EditorTab> tab_pool = new ArrayList<>();

    @FXML
    private TabPane tab_pane;

    @FXML
    private void handleNewFileAction(ActionEvent event) {
        addTab();
    }

    /**
     * Add SingleEditor to editor_pool
     * @param tab
     */
    public void addTabPool(EditorTab tab) {
        tab_pool.add(tab);
    }

    /**
     * Remove SingleEditor from editor_pool
     * @param tab
     */
    public void removeTabPool(EditorTab tab) {
        tab_pool.remove(tab);
    }

//    /**
//     * Get tab index of specified editor
//     * @param editor Specified editor
//     * @return Position of editor on tab
//     */
//    @Deprecated
//    public int getEditorIndex(SingleEditor editor) {
//        return editor_pool.indexOf(editor);
//    }
    
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
            // TODO: Check for duplicate tab
//            if (editor.isSameFile(path)) {
//                tab_pane.getSelectionModel().select(getEditorIndex(editor));
//                return;
//                tab_pane.setSelectedIndex(getEditorIndex(editor));
//            }
        }
        
        EditorTab editor_tab = new EditorTab(path);
        tab_pane.getTabs().add(editor_tab);
        tab_pane.getSelectionModel().select(editor_tab);

        addTabPool(editor_tab);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
