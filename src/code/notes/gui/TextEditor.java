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
    
    public int getEditorIndex(SingleEditor editor){
        return editor_pool.indexOf(editor);
    }
    
//    public void init() {
//        this.addTab("New Tab", makeTextPanel("dude"));
//        this.addTab("New Tab 2", makeTextPanel("not cool"));
//    }
    
    public void addTab(){
        SingleEditor new_editor = new SingleEditor();
        
        this.addEditor(new_editor);
        
        this.addTab("", new RTextScrollPane(new_editor));
        this.setTabComponentAt(getEditorIndex(new_editor), new_editor.getHeader());
    }
    
    public void addTab(Path path){
        SingleEditor new_editor = new SingleEditor(path);
        
        this.addEditor(new_editor);

        this.addTab("", new RTextScrollPane(new_editor));
        this.setTabComponentAt(getEditorIndex(new_editor), new_editor.getHeader());
    }
}
