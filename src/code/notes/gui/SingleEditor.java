/*
 * Code-Notes | SingleEditor.java
 * Created | 8:26:21 PM Oct 10, 2019
 */
package code.notes.gui;

import code.notes.util.ExtensionTranslator;
import code.notes.util.FileChooser;
import code.notes.util.FileHandler;
import code.notes.util.UserPreferences;
import java.io.File;
import java.nio.file.Path;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.iharder.dnd.FileDrop;

/**
 *
 * @author phwts
 */
public class SingleEditor extends org.fife.ui.rsyntaxtextarea.RSyntaxTextArea {

    private final TabHeader HEADER;
    private Path path = null;
    private boolean save_state = true;

    /**
     * Create RSyntaxTextArea empty content
     */
    public SingleEditor() {
        super(20, 60);
        this.refreshStyles();
        this.setCodeFoldingEnabled(true);

        this.HEADER = new TabHeader(this, "New File");
        this.addChangeListener();
    }

    /**
     * Create RSyntaxTextArea with content from path
     *
     * @param path Path assigned to text area
     */
    public SingleEditor(Path path) {
        super(20, 60);
        this.refreshStyles();
        this.setCodeFoldingEnabled(true);

        this.path = path;
        this.HEADER = new TabHeader(this, this.getFileName());
        this.setText(FileHandler.open(path));
        this.HEADER.setHeader(getFileName());
        this.setSyntaxStyle();
        this.addChangeListener();
    }

    private void addChangeListener() {
        this.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) { }

            @Override
            public void insertUpdate(DocumentEvent e) { }

            @Override
            public void changedUpdate(DocumentEvent arg0) {
                saveFalse();
            }
        });
        
        new FileDrop(this, new FileDrop.Listener() {
            public void filesDropped(File[] files) {
                for (File file : files) {
                    CodeNotes.text_editor.addTab(file.toPath());
                }
            }
        });
    }

    private void saveTrue() {
        this.save_state = true;
        HEADER.refresh();
    }

    private void saveFalse() {
        this.save_state = false;
        HEADER.refresh();
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

    public TabHeader getHeader() {
        return HEADER;
    }

    public void save() {
        if (path == null) {
            saveAs();
        } else {
            FileHandler.save(this.path, this.getText());
            saveTrue();
        }
    }

    public void saveAs() {
        Path save_path = FileChooser.save(this.getFileName());
        if (save_path == null) {
            return;
        }
        FileHandler.save(save_path, this.getText());
        this.setPath(save_path);
        this.HEADER.setHeader(getFileName());
        saveTrue();
        this.setSyntaxStyle();
    }
    
    public void refreshStyles() {
        this.setTabSize(UserPreferences.getTabSize());
        this.setTabsEmulated(UserPreferences.isTabEmulated());
        this.setAutoIndentEnabled(UserPreferences.isAutoIndent());
        this.setWhitespaceVisible(UserPreferences.isWtspVisible());
    }
    
    /**
     * Set syntax highlighting to match the file extension
     */
    public void setSyntaxStyle() {
        String syntaxConstant = ExtensionTranslator.getConstant(getFileName());
        this.setSyntaxEditingStyle(syntaxConstant);
    }
}
