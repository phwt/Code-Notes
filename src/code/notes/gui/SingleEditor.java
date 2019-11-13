/*
 * Code-Notes | SingleEditor.java
 * Created | 8:26:21 PM Oct 10, 2019
 */
package code.notes.gui;

import code.notes.util.FileChooser;
import code.notes.util.FileHandler;
import java.nio.file.Path;

/**
 *
 * @author phwts
 */

public class SingleEditor extends org.fife.ui.rsyntaxtextarea.RSyntaxTextArea {
    private final TabHeader HEADER;
    private Path path;
    
    public SingleEditor() {
        super(20, 60);
        this.setCodeFoldingEnabled(true);
        
        this.HEADER = new TabHeader("New File");
    }
    
    public SingleEditor(Path path) {
        super(20, 60);
        this.setCodeFoldingEnabled(true);
        
        this.path = path;
        this.HEADER = new TabHeader(this.getFileName());
        this.setText(FileHandler.open(path));
        this.HEADER.setHeader(getFileName());
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
    
    public String getFileName(){
        return String.valueOf(path.getFileName());
    }

    public TabHeader getHeader() {
        return HEADER;
    }
    
    public void save() {
        FileHandler.save(this.path, this.getText());
    }
    
    public void saveAs() {
        Path save_path = FileChooser.save(this.getFileName());
        if(save_path == null) { return; }
        FileHandler.save(save_path, this.getText());
        this.setPath(save_path);
        this.HEADER.setHeader(getFileName());
    }
}
