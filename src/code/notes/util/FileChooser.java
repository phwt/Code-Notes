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

    @Deprecated
    private static final JFileChooser JFILE_CHOOSER = new JFileChooser();

    /**
     * Show file chooser open dialog with multiple files selection
     * @return Paths of all selected files
     */
    public static Path[] openFiles() {
        Action details = JFILE_CHOOSER.getActionMap().get("viewTypeDetails");
        JFILE_CHOOSER.setMultiSelectionEnabled(true);
        details.actionPerformed(null);

        if (JFILE_CHOOSER.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File[] files = JFILE_CHOOSER.getSelectedFiles();
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
        Action details = JFILE_CHOOSER.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);

        if (JFILE_CHOOSER.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return JFILE_CHOOSER.getSelectedFile().toPath();
        }
        return null;
    }

    /**
     * Show file chooser open dialog with directory selection
     * @return Path of selected directory
     */
    public static Path openDirectory() {
        Action details = JFILE_CHOOSER.getActionMap().get("viewTypeDetails");
        JFILE_CHOOSER.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        details.actionPerformed(null);

        if (JFILE_CHOOSER.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return JFILE_CHOOSER.getSelectedFile().toPath();
        }
        return null;
    }

    /**
     * Show file chooser save dialog
     * @return Path of selected file
     */
    public static Path save() {
        Action details = JFILE_CHOOSER.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);
        if (JFILE_CHOOSER.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            return JFILE_CHOOSER.getSelectedFile().toPath();
        }
        return null;
    }
    
    /**
     * Show file chooser save dialog with predefined filename
     * @param filename Filename to be shown
     * @return Path of selected file
     */
    public static Path save(String filename) {
        Action details = JFILE_CHOOSER.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);
        JFILE_CHOOSER.setSelectedFile(new File(filename));
        if (JFILE_CHOOSER.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            return JFILE_CHOOSER.getSelectedFile().toPath();
        }
        return null;
    }
}
