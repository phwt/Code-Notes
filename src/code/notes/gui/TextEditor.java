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

    public void addEditor(SingleEditor editor) {
        editor_pool.add(editor);
    }

    public void removeEditor(SingleEditor editor) {
        editor_pool.remove(editor);
    }

    public int getEditorIndex(SingleEditor editor) {
        return editor_pool.indexOf(editor);
    }

    public void addTab() {
        SingleEditor new_editor = new SingleEditor();
        this.addEditorTab(new_editor);
    }

    public void addTab(Path path) {
        SingleEditor new_editor = new SingleEditor(path);
        this.addEditorTab(new_editor);
    }

    public void addEditorTab(SingleEditor editor) {
        this.addEditor(editor);
        int index = getEditorIndex(editor);
        this.addTab("", new RTextScrollPane(editor));
        this.setTabComponentAt(index, editor.getHeader());
        this.setSelectedIndex(index);
    }

    public void removeTab(SingleEditor editor) {
        this.removeTabAt(this.getEditorIndex(editor));
        removeEditor(editor);
    }

    public void closeTab() {
        SingleEditor editor = getActiveEditor();
        if(unsavedPrompt(editor))
            removeTab(editor);
    }

    public void closeTab(SingleEditor editor) {
        if(unsavedPrompt(editor))
            removeTab(editor);
    }

    public boolean unsavedPrompt(SingleEditor editor) {
        if (!editor.getSaveState()) {
            Object[] options = {
                Bundle.get("save"),
                Bundle.get("savent"),
                Bundle.get("cancel")
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
                    editor.saveAs();
                    break;
                case 2:
                    return false;
            }
            return true;
        }
        return true;
    }

    public SingleEditor getActiveEditor() {
        return editor_pool.get(this.getSelectedIndex());
    }
}
