/*
 * Code-Notes | SingleEditor.java
 * Created | 8:26:21 PM Oct 10, 2019
 */
package code.notes.gui;

import code.notes.main.CodeNotes;
import code.notes.util.FileChooser;
import code.notes.util.FileHandler;
import java.awt.event.KeyEvent;
import java.nio.file.Path;

/**
 *
 * @author phwts
 */
public class SingleEditor extends org.fife.ui.rsyntaxtextarea.RSyntaxTextArea {

    private final TabHeader HEADER;
    private Path path;
    private boolean save_state = true;

    public SingleEditor() {
        super(20, 60);
        this.setCodeFoldingEnabled(true);

        this.HEADER = new TabHeader(this, "New File");
        this.addChangeListener();
    }

    public SingleEditor(Path path) {
        super(20, 60);
        this.setCodeFoldingEnabled(true);

        this.path = path;
        this.HEADER = new TabHeader(this, this.getFileName());
        this.setText(FileHandler.open(path));
        this.HEADER.setHeader(getFileName());
        this.addChangeListener();
    }

    private void addChangeListener() {
        this.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textAreaKeyTyped(evt);
            }
            private void textAreaKeyTyped(KeyEvent evt) {
                saveFalse();
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
    
    public boolean getSaveState(){
        return save_state;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public String getFileName() {
        return String.valueOf(path.getFileName());
    }

    public TabHeader getHeader() {
        return HEADER;
    }

    public void save() {
        FileHandler.save(this.path, this.getText());
        saveTrue();
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
    }
}
