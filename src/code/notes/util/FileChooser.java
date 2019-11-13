/* 
 * Code-Notes | FileChooser.java
 * Created | 1:29:08 PM ‎Oct ‎10, ‎2019
 */
package code.notes.util;

import java.io.File;
import javax.swing.Action;
import javax.swing.JFileChooser;

/**
 *
 * @author phwts
 */
public class FileChooser {

    private static JFileChooser file_chooser = new JFileChooser();

    public static File[] openFiles() {
        Action details = file_chooser.getActionMap().get("viewTypeDetails");
        file_chooser.setMultiSelectionEnabled(true);
        details.actionPerformed(null);

        if (file_chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            File[] files = file_chooser.getSelectedFiles();
            return files;
        }
        return null;
    }

    public static String openDirectory() {
        Action details = file_chooser.getActionMap().get("viewTypeDetails");
        file_chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        details.actionPerformed(null);

        if (file_chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return file_chooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    public static String save() {
        Action details = file_chooser.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);
        if (file_chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            return file_chooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    public static String save(String filename) {
        Action details = file_chooser.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);
        file_chooser.setSelectedFile(new File(filename));
        if (file_chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            return file_chooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
}
