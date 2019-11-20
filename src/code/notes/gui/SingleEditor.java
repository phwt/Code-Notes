/*
 * Code-Notes | SingleEditor.java
 * Created | 8:26:21 PM Oct 10, 2019
 */
package code.notes.gui;

import code.notes.fxgui.EditorTab;
import code.notes.util.ExtensionTranslator;
import code.notes.util.FileChooserDialog;
import code.notes.util.FileHandler;
import code.notes.util.UserPreferences;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.iharder.dnd.FileDrop;

/**
 *
 * @author phwts
 */
public final class SingleEditor extends org.fife.ui.rsyntaxtextarea.RSyntaxTextArea {

    private Path path = null;
    private boolean save_state = true;
    private Font font;
    final private EditorTab EDITOR_TAB;

    @Deprecated
    public SingleEditor() {
        EDITOR_TAB = null;
    }
    
    @Deprecated
    public SingleEditor(Path path) {
        this();
    }
    
    /**
     * Create RSyntaxTextArea empty content
     */
    public SingleEditor(EditorTab tab) {
        super(20, 60);
        this.setLAF();
        this.EDITOR_TAB = tab;
        this.loadEditorFont();
        this.refreshStyles();
        this.setCodeFoldingEnabled(true);

        this.addChangeListener();
    }

    /**
     * Create RSyntaxTextArea with content from path
     *
     * @param path Path assigned to text area
     */
    public SingleEditor(Path path, EditorTab tab) {
        super(20, 60);
        this.setLAF();
        this.EDITOR_TAB = tab;
        this.loadEditorFont();
        this.refreshStyles();
        this.setCodeFoldingEnabled(true);

        this.path = path;
        this.setText(FileHandler.open(path));
        this.setSyntaxStyle();
        this.addChangeListener();
    }
    
    private void setLAF() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            //Never happens
        }
    }
    
    /**
     * Load DejaVu Sans Mono Thai font into the editor
     */
    public void loadEditorFont() {
        try {
            Path font_path = Paths.get(System.getProperty("user.dir"), "font", "DejaVuSansMonoThai.ttf");
            font = Font.createFont(Font.PLAIN, font_path.toFile());
            this.loadEditorFontSize();
        } catch (FontFormatException | IOException ex) {
            // Use default font
        }
    }
    
    public void loadEditorFontSize() {
        this.setFont(font.deriveFont((float) UserPreferences.getFontSize()));
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

        new FileDrop(this, new FileDrop.Listener() {
            @Override
            public void filesDropped(File[] files) {
                for (File file : files) {
                    CodeNotes.text_editor.addTab(file.toPath());
                }
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

    public boolean getSaveState() {
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
     * Put the contents of this editor into the path assigned to this
     * Redirect to saveAs() if there is no path assigned
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
//        Path save_path = FileChooserDialog.save(getPath(), code.notes.fxgui.CodeNotes.STAGE);
        Path save_path = FileChooserDialog.save(code.notes.fxgui.CodeNotes.STAGE);
        if (save_path == null)
            return;
        FileHandler.save(save_path, this.getText());
        this.setPath(save_path);
        saveTrue();
        this.setSyntaxStyle();
    }

    /**
     * Refresh editor styles (indent, tab size, etc.) after preferences is modified
     */
    public void refreshStyles() {
        this.setTabSize(UserPreferences.getTabSize());
        this.setTabsEmulated(UserPreferences.isTabEmulated());
        this.setAutoIndentEnabled(UserPreferences.isAutoIndent());
        this.setWhitespaceVisible(UserPreferences.isWtspVisible());
        this.loadEditorFontSize();
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
    
    @Deprecated
    public java.awt.Component getHeader() {
        return null;
    }
}
