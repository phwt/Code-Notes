/* 
 * Code-Notes | FileHandler.java
 * Created | ‏‎1:29:08 PM ‎Oct ‎10, ‎2019
 */
package code.notes.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.*;

/**
 *
 * @author phwts
 */
public class FileHandler {

    /**
     * Get file contents from specified path
     * 
     * @param path Path to the file
     * @return File contents
     */
    public static String open(Path path) {
        File file = path.toFile();
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

    /**
     * Save contents to specified path
     * 
     * @param path Path to the file
     * @param content File contents
     */
    public static void save(Path path, String content) {

        File file = path.toFile();
        try { file.createNewFile(); } catch (IOException ex) { return; }

        Writer fileWriter;
        try {
            fileWriter = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            return;
        }
    }

}
