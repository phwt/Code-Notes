/* 
 * Code-Notes | FileHandler.java
 * Created | ‏‎1:29:08 PM ‎Oct ‎10, ‎2019
 */
package code.notes.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 *
 * @author phwts
 */
public class FileHandler {

    public static String open(String path) {
        File file = new File(path);
        Scanner scanner;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException ex) {
            return "";
        }

        String file_content = "";
        while (scanner.hasNextLine()) {
            file_content += scanner.nextLine() + ((scanner.hasNextLine()) ? "\n" : "");
        }

        return file_content;
    }

    public static void save(String content, String path) {

        File file = new File(path);
        try { file.createNewFile(); } catch (IOException ex) { return; }

        Writer fileWriter;
        try {
//            fileWriter = new FileWriter(file);
            fileWriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            return;
        }
    }

}
