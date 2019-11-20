/*
 * Code-Notes-FX | DirectoryNode.java
 * Created | 6:14:32 PM Nov 20, 2019
 */
package code.notes.util;

import java.io.File;
import java.nio.file.Path;
import javafx.scene.control.TreeItem;

/**
 *
 * @author phwts
 */
public class DirectoryNode extends TreeItem{
    final Path PATH;

    public DirectoryNode(final Path PATH) {
        super(PATH.getFileName().toString());
        this.PATH = PATH;
    }

    public Path getPath() {
        return this.PATH;
    }
    
    public boolean isDirectory() {
        return this.getPath().toFile().isDirectory();
    }
    
    public File toFile() {
        return this.PATH.toFile();
    }
    
    @Override
    public String toString() {
        return this.getPath().getFileName().toString();
    }

}
