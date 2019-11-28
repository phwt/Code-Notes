/*
 * Code-Notes | EditorTab.java
 * Created | 4:34:37 PM Nov 19, 2019
 */
package code.notes.gui;

import code.notes.util.Bundle;
import java.awt.Color;
import java.awt.Dimension;
import java.nio.file.Path;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
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
            if (!EDITOR.isSaved()) {
                unsavedPrompt(e);
            }
        });
    }

    /**
     * Show unsaved prompt if user attempt to exit without saving
     * 
     * @param e
     */
    public void unsavedPrompt(Event e) {
        ButtonType btn_save = new ButtonType(Bundle.get("save"), ButtonBar.ButtonData.YES);
        ButtonType btn_savent = new ButtonType(Bundle.get("savent"), ButtonBar.ButtonData.NO);
        ButtonType btn_cancel = new ButtonType(Bundle.get("cancel"), ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(
                AlertType.CONFIRMATION,
                getEDITOR().getFileName() + " - " + Bundle.get("save_notice_sub"),
                btn_save, btn_savent, btn_cancel
        );
        alert.showAndWait().ifPresent(response -> {
            if (response.equals(btn_save)) {
                EDITOR.save();
                FXMLMainController.removeTabPool(this);
            } else if (response.equals(btn_savent)) {
                FXMLMainController.removeTabPool(this);
            } else if (response.equals(btn_cancel)) {
                e.consume();
            }
        });
    }

    private void createEditor(SingleEditor editor) {
        SwingNode swingNode = new SwingNode();
        RTextScrollPane scrollPane = new RTextScrollPane(editor);

        Color scroll_bg = new Color(30, 30, 30); // #1E1E1E
        
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setBackground(scroll_bg);
        scrollPane.getVerticalScrollBar().setBackground(scroll_bg);
        scrollPane.getHorizontalScrollBar().setBackground(scroll_bg);
        
        scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(10, 0));
        scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(10, 0));

        scrollPane.getVerticalScrollBar().setUI(new CustomScrollBar());
        scrollPane.getHorizontalScrollBar().setUI(new CustomScrollBar());

        swingNode.setContent(scrollPane);
        this.setContent(new StackPane(swingNode));
    }

    public SingleEditor getEDITOR() {
        return EDITOR;
    }

    /**
     * Refresh editor's header bases on save status
     */
    public void refresh() {
        Platform.runLater(
                () -> {
                    String header_text = EDITOR.getFileName();
                    header_text += ((EDITOR.isSaved()) ? "" : " *");
                    this.setText(header_text);
                }
        );
    }

    class CustomScrollBar extends BasicScrollBarUI {

        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = new Color(66, 66, 66); // #424242
        }

        @Override
        protected JButton createDecreaseButton(int orientation) {
            return createNullButton();
        }

        @Override
        protected JButton createIncreaseButton(int orientation) {
            return createNullButton();
        }

        private JButton createNullButton() {
            JButton jbutton = new JButton();
            Dimension zero = new Dimension(0, 0);
            jbutton.setPreferredSize(zero);
            jbutton.setMinimumSize(zero);
            jbutton.setMaximumSize(zero);
            return jbutton;
        }
    }

}
