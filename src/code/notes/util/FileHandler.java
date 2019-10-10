/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code.notes.util;

import java.io.*;
import java.util.*;
import java.util.logging.*;
import javax.swing.JTextArea;

/**
 *
 * @author phwts
 */
public class FileHandler {

    public static String open(String path) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);

        String file_content = "";
        while (scanner.hasNextLine()) {
            file_content += scanner.nextLine() + ((scanner.hasNextLine()) ? "\n" : "");
        }

        return file_content;
    }

    public static void save(String content, String path) throws IOException {

        File file = new File(path);
        if (file.createNewFile()) {
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }

        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(content);
        fileWriter.close();
    }

}
