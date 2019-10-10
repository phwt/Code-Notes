/* 
 * Code-Notes | FileChooser.java
 * Created | 1:29:08 PM ‎Oct ‎10, ‎2019
 */
package code.notes.util;

import javax.swing.Action;
import javax.swing.JFileChooser;

/**
 *
 * @author phwts
 */
public class FileChooser {

    static JFileChooser fileChooser = new JFileChooser();

    public static String open() {
        Action details = fileChooser.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    public static String save() {
        Action details = fileChooser.getActionMap().get("viewTypeDetails");
        details.actionPerformed(null);
        fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile().getAbsolutePath();
        }
        return null;
    }
}
