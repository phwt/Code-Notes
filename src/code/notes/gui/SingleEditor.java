/*
 * Code-Notes | SingleEditor.java
 * Created | 8:26:21 PM Oct 10, 2019
 */
package code.notes.gui;

/**
 *
 * @author phwts
 */
import code.notes.util.FileHandler;
import javax.swing.*;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class SingleEditor extends JPanel {
    private RSyntaxTextArea textArea;
    private String content, path;
    private final int tabID;

    public SingleEditor(int tabID) {
        this.tabID = tabID;
        this.path = "";
        init();
    }
    
    public SingleEditor(int tabID, String path) {
        this.tabID = tabID;
        this.path = path;
        init();
    }
    
    public void init(){
        this.setLayout(new BorderLayout());

        textArea = new RSyntaxTextArea(20, 60);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        this.add(sp);
        
        try {
            setContent(FileHandler.open(path));
        } catch (FileNotFoundException ex) {
            setContent("");
        }
        
        textArea.setText(getContent());
    }

    public int getTabID() {
        return tabID;
    }
    
    public String getFileName(){
        return (!path.isEmpty()) ? new File(path).getName() : "New File";
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
    
    
}
