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

    private static final JFileChooser FILE_CHOOSER = new JFileChooser();

    /**
     * Show file chooser open dialog with multiple files selection
     * @return Paths of all selected files
     */
    public static Path[] openFiles() {
        Action details = FILE_CHOOSER.getActionMap().get("viewTypeDetails");
        FILE_CHOOSER.setMultiSelectionEnabled(true);
        details.actionPerformed(null);

        if (FILE_CHOOSER.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File[] files = FILE_CHOOSER.getSelectedFiles();
            Path[] paths = new Path[files.length];
            
            for(int i=0; i<files.length; i++){
                paths[i] = files[i].toPath();
            }
            
            return paths;
        }
        return null;
    }
    
    /**
     * Show file chooser open dialog with single file selection
     * @return Path of selected file
     */
    public static Path openFile() {
        Action details = FILE_CHOOSER.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);

        if (FILE_CHOOSER.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return FILE_CHOOSER.getSelectedFile().toPath();
        }
        return null;
    }

    @Deprecated
    public static String openDirectory() {
        Action details = FILE_CHOOSER.getActionMap().get("viewTypeDetails");
        FILE_CHOOSER.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        details.actionPerformed(null);

        if (FILE_CHOOSER.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return FILE_CHOOSER.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    /**
     * Show file chooser save dialog
     * @return Path of selected file
     */
    public static Path save() {
        Action details = FILE_CHOOSER.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);
        if (FILE_CHOOSER.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            return FILE_CHOOSER.getSelectedFile().toPath();
        }
        return null;
    }
    
    /**
     * Show file chooser save dialog with predefined filename
     * @param filename Filename to be shown
     * @return Path of selected file
     */
    public static Path save(String filename) {
        Action details = FILE_CHOOSER.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);
        FILE_CHOOSER.setSelectedFile(new File(filename));
        if (FILE_CHOOSER.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            return FILE_CHOOSER.getSelectedFile().toPath();
        }
        return null;
    }
}
