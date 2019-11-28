/*
 * Code-Notes | SingleEditor.java
 * Created | 8:26:21 PM Oct 10, 2019
 */
package code.notes.gui;

import code.notes.util.ExtensionTranslator;
import code.notes.util.FileChooserDialog;
import code.notes.util.FileHandler;
import code.notes.util.UserPreferences;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.fife.ui.rsyntaxtextarea.Theme;

/**
 *
 * @author phwts
 */
public final class SingleEditor extends org.fife.ui.rsyntaxtextarea.RSyntaxTextArea {

    private Path path = null;
    private boolean save_state = true;
    final private EditorTab EDITOR_TAB;

    /**
     * Create RSyntaxTextArea empty content
     * 
     * @param tab Tab that this editor assigned to
     */
    public SingleEditor(EditorTab tab) {
        super(20, 60);
        this.setLAF();
//        this.loadEditorFont();
        this.EDITOR_TAB = tab;
        this.refreshStyles();
        this.setCodeFoldingEnabled(true);

        this.addChangeListener();
    }

    /**
     * Create RSyntaxTextArea with content from path
     *
     * @param path Path assigned to text area
     * @param tab Tab that this editor assigned to
     */
    public SingleEditor(Path path, EditorTab tab) {
        super(20, 60);
        this.setLAF();
//        this.loadEditorFont();
        this.EDITOR_TAB = tab;
        this.refreshStyles();
        this.setCodeFoldingEnabled(true);

        this.path = path;
        this.setText(FileHandler.open(path));
        this.setSyntaxStyle();
        this.addChangeListener();
    }

    private void setLAF() {
        Font font;
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            //Never happens
        }

        try {
            InputStream resourceAsStream = SingleEditor.class.getResourceAsStream("/code/notes/res/DejaVuSansMonoThai.ttf");
            font = Font.createFont(Font.PLAIN, resourceAsStream);
            font = font.deriveFont((float) UserPreferences.getFontSize());
            Theme theme = Theme.load(getClass().getResourceAsStream("/code/notes/res/monokai.xml"), font);
            theme.apply(this);
        } catch (IOException ioe) { // Never happens
            ioe.printStackTrace();
        } catch (FontFormatException ex) {
            Logger.getLogger(SingleEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Load DejaVu Sans Mono Thai font into the editor
     */
    @Deprecated
    public void loadEditorFont() {
//        try {
////            InputStream resourceAsStream = SingleEditor.class.getResourceAsStream("/code/notes/resources/DejaVuSansMonoThai.ttf");
////            font = Font.createFont(Font.PLAIN, resourceAsStream);
////            font.deriveFont(14.0f);
////            this.setFont(font);
////            this.loadEditorFontSize();
//        } catch (FontFormatException | IOException ex) {
//            // Use default font
//        }
    }

    /**
     * Listen for content change inside the text area and for file drop
     */
    private void addChangeListener() {
        this.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                saveFalse();
            }
        });
    }

    private void saveTrue() {
        this.save_state = true;
        EDITOR_TAB.refresh();
    }

    private void saveFalse() {
        this.save_state = false;
        EDITOR_TAB.refresh();
    }

    public boolean isSaved() {
        return save_state;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getFileName() {
        if (path == null) {
            return "New File";
        }
        return String.valueOf(path.getFileName());
    }

    /**
     * Put the contents of this editor into the path assigned to this Redirect
     * to saveAs() if there is no path assigned
     */
    public void save() {
        if (path == null) {
            saveAs();
        } else {
            FileHandler.save(this.path, this.getText());
            saveTrue();
        }
    }

    /**
     * Put the contents of this editor into the path selected by the user
     */
    public void saveAs() {
        Path save_path = FileChooserDialog.save(code.notes.gui.CodeNotes.STAGE);
        if (save_path == null) {
            return;
        }
        FileHandler.save(save_path, this.getText());
        this.setPath(save_path);
        saveTrue();
        this.setSyntaxStyle();
    }

    /**
     * Refresh editor styles (indent, tab size, etc.) after preferences is
     * modified
     */
    public void refreshStyles() {
        this.setTabSize(UserPreferences.getTabSize());
        this.setTabsEmulated(UserPreferences.isTabEmulated());
        this.setAutoIndentEnabled(UserPreferences.isAutoIndent());
        this.setWhitespaceVisible(UserPreferences.isWtspVisible());
//        this.loadEditorFontSize();
        this.setLAF();
    }

    /**
     * Set syntax highlighting to match the file extension
     */
    public void setSyntaxStyle() {
        String syntaxConstant = ExtensionTranslator.getConstant(getFileName());
        this.setSyntaxEditingStyle(syntaxConstant);
    }

    public boolean isSameFile(Path path) {
        if (this.path == null) {
            return false;
        }
        return this.path.equals(path);
    }
}
