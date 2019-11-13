/*
 * Code-Notes | SingleEditor.java
 * Created | 8:26:21 PM Oct 10, 2019
 */
package code.notes.gui;

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
        this.HEADER = new TabHeader(this.getFilename());
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }
    
    public String getFilename(){
        return String.valueOf(path.getFileName());
    }

    public TabHeader getHeader() {
        return HEADER;
    }
}
