/* 
 * Code-Notes | TextEditor.java
 * Created | 1:29:02 PM ‎Oct ‎10, ‎2019
 */
package code.notes.gui;

import code.notes.Bundle;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 *
 * @author phwts
 */
public final class TextEditor extends javax.swing.JTabbedPane {

    private static ArrayList<SingleEditor> editor_pool = new ArrayList<SingleEditor>();

    public TextEditor() {
        this.addTab();
    }

    /**
     * Add SingleEditor to editor_pool
     * @param editor
     */
    public void addEditor(SingleEditor editor) {
        editor_pool.add(editor);
    }

    /**
     * Remove SingleEditor from editor_pool
     * @param editor
     */
    public void removeEditor(SingleEditor editor) {
        editor_pool.remove(editor);
    }

    /**
     * Get tab index of specified editor
     * @param editor Specified editor
     * @return Position of editor on tab
     */
    public int getEditorIndex(SingleEditor editor) {
        return editor_pool.indexOf(editor);
    }
    
    public ArrayList<SingleEditor> getEditorPool() {
        return editor_pool;
    }

    /**
     * Add new tab
     */
    public void addTab() {
        SingleEditor new_editor = new SingleEditor();
        this.addEditorTab(new_editor);
    }

    /**
     * Add new tab with a content
     * @param path Path assigned to this tab
     */
    public void addTab(Path path) {
        SingleEditor new_editor = new SingleEditor(path);
        this.addEditorTab(new_editor);
    }

    /**
     * Add editor to tab
     * @param editor
     */
    public void addEditorTab(SingleEditor editor) {
        this.addEditor(editor);
        int index = getEditorIndex(editor);
        this.addTab("", new RTextScrollPane(editor));
        this.setTabComponentAt(index, editor.getHeader());
        this.setSelectedIndex(index);
    }

    /**
     * Remove specified tab from the pane and from editor_pool
     * @param editor Editor to remove
     */
    public void removeTab(SingleEditor editor) {
        this.removeTabAt(this.getEditorIndex(editor));
        removeEditor(editor);
    }

    /**
     * Close active tab
     */
    public void closeTab() {
        SingleEditor editor = getActiveEditor();
        if(unsavedPrompt(editor))
            removeTab(editor);
    }

    /**
     * Close specified tab
     * @param editor Editor to close
     */
    public void closeTab(SingleEditor editor) {
        if(unsavedPrompt(editor))
            removeTab(editor);
    }

    /**
     * Show a prompt to notify user about their unsaved changes
     * @param editor Editor to check for unsaved changes
     * @return true - File is already saved or user save the file from prompt
     *          false - User cancel the operation
     */
    public boolean unsavedPrompt(SingleEditor editor) {
        if (!editor.getSaveState()) {
            Object[] options = {
                Bundle.get("save"), Bundle.get("savent"), Bundle.get("cancel")
            };
            int n = JOptionPane.showOptionDialog(editor,
                    Bundle.get("save_notice"),
                    Bundle.get("warning"),
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[2]
            );
            
            switch(n) {
                case 0:
                    editor.save();
                    break;
                case 1:
                    break;
                case 2:
                    return false;
            }
            return true;
        }
        return true;
    }
    
    /**
     * Check in editor_pool if there's any SingleEditor with unsaved changes
     * @return true if there's one or more unsaved SingleEditor / false if there is none
     */
    public boolean hasUnsaved() {
        return editor_pool.stream().anyMatch((editor) -> (!editor.getSaveState()));
    }

    /**
     * Get active editor
     * @return Active editor
     */
    public SingleEditor getActiveEditor() {
        return editor_pool.get(this.getSelectedIndex());
    }
}
