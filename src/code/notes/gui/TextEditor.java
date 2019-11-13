/* 
 * Code-Notes | TextEditor.java
 * Created | 1:29:02 PM ‎Oct ‎10, ‎2019
 */
package code.notes.gui;

import java.nio.file.Path;
import java.util.ArrayList;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 *
 * @author phwts
 */
public final class TextEditor extends javax.swing.JTabbedPane{
    
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
    
    public int getEditorIndex(SingleEditor editor){
        return editor_pool.indexOf(editor);
    }
    
    public void addTab(){
        SingleEditor new_editor = new SingleEditor();
        this.addEditorTab(new_editor);
    }
    
    public void addTab(Path path){
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
    
    public void closeTab(){
        SingleEditor editor = getActiveEditor();
        this.removeTabAt(this.getEditorIndex(editor));
        removeEditor(editor);
    }
    
    public void closeTab(SingleEditor editor){
        this.removeTabAt(this.getEditorIndex(editor));
        removeEditor(editor);
    }
    
    public SingleEditor getActiveEditor() {
        return editor_pool.get(this.getSelectedIndex());
    }
}
