/*
 * Code-Notes | MockGUI.java
 * Created | 11:52:26 AM Nov 11, 2019
 */
package code.notes.gui;

import code.notes.util.ExceptionLookup;
import java.util.Scanner;
/**
 *
 * @author phwts
 */
public class MockGUI {
    public static void displayText(String text){
        System.out.println(text);
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Language: ");
        String search_lang = scanner.nextLine();
        System.out.print("Search for: ");
        String search_key = scanner.nextLine();
        
        ExceptionLookup.search(search_lang, search_key);
    }
}
