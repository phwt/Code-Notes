/* 
 * Code-Notes | FileHandler.java
 * Created | ‏‎1:29:08 PM ‎Oct ‎10, ‎2019
 */
package code.notes.util;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            if (file.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException ex) {
            return;
        }

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            return;
        }
    }

}
