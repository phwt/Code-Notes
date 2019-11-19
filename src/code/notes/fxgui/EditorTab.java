/*
 * Code-Notes | EditorTab.java
 * Created | 4:34:37 PM Nov 19, 2019
 */
package code.notes.fxgui;

import code.notes.gui.SingleEditor;
import java.nio.file.Path;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
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
        this.createTab();
    }

    public EditorTab(Path path) {
        EDITOR = new SingleEditor(path, this);
        this.createTab();
    }

    private void createTab() {
        this.createEditor(EDITOR);
        this.setText(EDITOR.getFileName());

        this.setOnCloseRequest(e -> {
            if (!EDITOR.getSaveState())
                unsavedPrompt(e);
        });
    }

    private void unsavedPrompt(Event e) {
        ButtonType btn_save = new ButtonType("Save", ButtonBar.ButtonData.YES);
        ButtonType btn_savent = new ButtonType("Don't Save", ButtonBar.ButtonData.NO);
        ButtonType btn_cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(
                AlertType.CONFIRMATION,
                "Your changes will be lost if you don't save them. Do you want to proceed?",
                btn_save, btn_savent, btn_cancel
        );
        alert.showAndWait().ifPresent(response -> {
            if (response.equals(btn_save)) {
                EDITOR.save();
            } else if (response.equals(btn_savent)) {
                // Do nothing
            } else if (response.equals(btn_cancel)) {
                e.consume();
            }
        });
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
        Platform.runLater(
                () -> {
                    String header_text = EDITOR.getFileName();
                    header_text += ((EDITOR.getSaveState()) ? "" : " *");
                    this.setText(header_text);
                }
        );
    }

}
