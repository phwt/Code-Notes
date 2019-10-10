/*
 * Code-Notes | Bundle.java
 * Created | 3:15:32 PM Oct 10, 2019
 */
package code.notes;

/**
 *
 * @author phwts
 */
public class Bundle {
    
    public static String get(String key){
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("code/notes/Bundle");
        return bundle.getString(key);
    }
    
}
