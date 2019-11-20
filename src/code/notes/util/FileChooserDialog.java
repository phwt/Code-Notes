/* 
 * Code-Notes | FileChooserDialog.java
 * Created | 1:29:08 PM ‎Oct ‎10, ‎2019
 */
package code.notes.util;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.Action;
import javax.swing.JFileChooser;

/**
 *
 * @author phwts
 */
public class FileChooserDialog {

    @Deprecated
    private static final JFileChooser JFILE_CHOOSER = new JFileChooser();

    /**
     * Show file chooser open dialog with multiple files selection
     *
     * @param stage
     * @return Paths of all selected files
     */
    public static List<Path> openFiles(Stage stage) {
        FileChooser chooser = new FileChooser();
        List<File> files = chooser.showOpenMultipleDialog(stage);
        if (!files.isEmpty()) {
            ArrayList<Path> paths = new ArrayList<>();

            for (File file : files)
                paths.add(file.toPath());

            return paths;
        }
        return null;
    }

    @Deprecated
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
     *
     * @param stage
     * @return Path of selected file
     */
    public static Path openFile(Stage stage) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showOpenDialog(stage);
        if (file != null)
            return file.toPath();
        return null;
    }
    
    @Deprecated
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
     *
     * @param stage
     * @return Path of selected directory
     */
    public static Path openDirectory(Stage stage) {
        DirectoryChooser chooser = new DirectoryChooser();
        File directory = chooser.showDialog(stage);
        if (directory != null)
            return directory.toPath();
        return null;
    }
    
    @Deprecated
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
     *
     * @return Path of selected file
     */
    public static Path save(Stage stage) {
        FileChooser chooser = new FileChooser();
        File file = chooser.showSaveDialog(stage);
        if (file != null)
            return file.toPath();
        return null;
    }
    
    @Deprecated
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
     *
     * @param path
     * @param stage
     * @return Path of selected file
     */
    public static Path save(Path path, Stage stage) {
        FileChooser chooser = new FileChooser();
        chooser.setInitialDirectory(path.toFile());
        chooser.setInitialFileName(path.toFile().getName());
        File file = chooser.showSaveDialog(stage);
        if (file != null)
            return file.toPath();
        return null;
    }
    
    @Deprecated
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
