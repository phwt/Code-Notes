/*
 * Code-Notes | SingleEditor.java
 * Created | 8:26:21 PM Oct 10, 2019
 */
package code.notes.gui;

/**
 *
 * @author phwts
 */
import code.notes.Bundle;
import code.notes.util.ExtensionTranslator;
import code.notes.util.FileHandler;
import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.io.File;
import org.fife.rsta.ac.LanguageSupportFactory;
import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class SingleEditor extends JPanel {

    private RSyntaxTextArea textArea;
    private TextEditor textEditor;
    private String content, path;
    private boolean saved = true;

    public SingleEditor(TextEditor textEditor) {
        this.path = "";
        this.textEditor = textEditor;
        init();
    }

    public SingleEditor(TextEditor textEditor, String path) {
        this.path = path;
        this.textEditor = textEditor;
        init();
    }

    public void init() {
        this.setLayout(new BorderLayout());

        textArea = new RSyntaxTextArea(20, 60);
        setExtension();
        textArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        this.add(sp);
        LanguageSupportFactory.get().register(textArea);
        setContent(FileHandler.open(path));
        
        textArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textAreaKeyTyped(evt);
            }

            private void textAreaKeyTyped(KeyEvent evt) {
                saveFalse();
            }
        });

        textArea.setText(getContent());
    }
    
    public void setExtension(){
        String syntaxConstant = ExtensionTranslator.getConstant(getFileName());
        textArea.setSyntaxEditingStyle(syntaxConstant);
    }

    public String getFileName() {
        return (!path.isEmpty()) ? new File(path).getName() : Bundle.get("new_file");
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public RSyntaxTextArea getTextArea() {
        return textArea;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    public void saveTrue(){
        saved = true;
        textEditor.updateHeader(getFileName(), true);
    }
    public void saveFalse(){
        saved = false;
        textEditor.updateHeader(getFileName(), isSaved());
    }

    public boolean isSaved() {
        return saved;
    }
}
