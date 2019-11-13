/* 
 * Code-Notes | FileChooser.java
 * Created | 1:29:08 PM ‎Oct ‎10, ‎2019
 */
package code.notes.util;

import java.io.File;
import java.nio.file.Path;
import javax.swing.Action;
import javax.swing.JFileChooser;

/**
 *
 * @author phwts
 */
public class FileChooser {

    private static JFileChooser file_chooser = new JFileChooser();

    public static Path[] openFiles() {
        Action details = file_chooser.getActionMap().get("viewTypeDetails");
        file_chooser.setMultiSelectionEnabled(true);
        details.actionPerformed(null);

        if (file_chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File[] files = file_chooser.getSelectedFiles();
            Path[] paths = new Path[files.length];
            
            for(int i=0; i<files.length; i++){
                paths[i] = files[i].toPath();
            }
            
            return paths;
        }
        return null;
    }
    
    public static Path openFile() {
        Action details = file_chooser.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);

        if (file_chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return file_chooser.getSelectedFile().toPath();
        }
        return null;
    }

    @Deprecated
    public static String openDirectory() {
        Action details = file_chooser.getActionMap().get("viewTypeDetails");
        file_chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        details.actionPerformed(null);

        if (file_chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return file_chooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    public static Path save() {
        Action details = file_chooser.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);
        if (file_chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            return file_chooser.getSelectedFile().toPath();
        }
        return null;
    }
    
    public static Path save(String filename) {
        Action details = file_chooser.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);
        file_chooser.setSelectedFile(new File(filename));
        if (file_chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            return file_chooser.getSelectedFile().toPath();
        }
        return null;
    }
}
