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
import code.notes.util.FileHandler;
import javax.swing.*;
import java.awt.BorderLayout;
import java.io.File;
import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

public class SingleEditor extends JPanel {

    private RSyntaxTextArea textArea;
    private String content, path;
    private final @Deprecated int tabID;

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

    public void init() {
        this.setLayout(new BorderLayout());

        textArea = new RSyntaxTextArea(20, 60);
        textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVA);
        textArea.setCodeFoldingEnabled(true);
        RTextScrollPane sp = new RTextScrollPane(textArea);
        this.add(sp);

        setContent(FileHandler.open(path));

        textArea.setText(getContent());
    }

    public void save() {

    }

    public String open(){
        return null;
    }

    @Deprecated
    public int getTabID() {
        return tabID;
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

}
