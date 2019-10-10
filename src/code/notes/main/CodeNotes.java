/*
 * Code-Notes | CodeNotes.java
 * Created | 1:27:10 PM Oct 10, 2019
 */
package code.notes.main;

import code.notes.gui.TextEditor;
import javax.swing.UIManager;

/**
 *
 * @author phwts
 */
public class CodeNotes {
    public static void main(String[] args) {
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            //Never happens
        }
        
        java.awt.EventQueue.invokeLater(() -> {
            TextEditor textEditor = new TextEditor();
            textEditor.setVisible(true);
        });
    }
}
