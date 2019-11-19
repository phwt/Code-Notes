/*
 * Code-Notes | EditorTab.java
 * Created | 4:34:37 PM Nov 19, 2019
 */
package code.notes.fxgui;

import code.notes.gui.SingleEditor;
import java.nio.file.Path;
import javafx.embed.swing.SwingNode;
import javafx.scene.layout.StackPane;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 *
 * @author phwts
 */
public class EditorTab extends javafx.scene.control.Tab {

    private final SingleEditor EDITOR;

    public EditorTab() {
        EDITOR = new SingleEditor(this);
        this.createEditor(EDITOR);
        this.setText(EDITOR.getFileName());
    }

    public EditorTab(Path path) {
        EDITOR = new SingleEditor(path, this);
        this.createEditor(EDITOR);
        this.setText(EDITOR.getFileName());
    }

    private void createEditor(SingleEditor editor) {
        SwingNode swingNode = new SwingNode();
        swingNode.setContent(new RTextScrollPane(editor));
        this.setContent(new StackPane(swingNode));
    }

    public SingleEditor getEDITOR() {
        return EDITOR;
    }
    
    public void refresh() {
        this.setText(EDITOR.getFileName());
    }

}
