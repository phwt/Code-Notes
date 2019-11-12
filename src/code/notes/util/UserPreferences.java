/*
 * Code-Notes | UserPreferences.java
 * Created | 4:01:28 PM Nov 12, 2019
 */
package code.notes.util;

import java.nio.file.Path;
import java.util.prefs.Preferences;

/**
 *
 * @author phwts
 */
public class UserPreferences {

    private static final Preferences prefs = Preferences.userRoot().node(UserPreferences.class.getName());;

    public static void resetPreferences() {
        setAutoIndent(true);
        setTabEmulated(true);
        setWtspVisible(true);
        setTabSize(4);
        setDirPath("/");
    }

    public static boolean isAutoIndent() {
        return prefs.getBoolean("auto_indent", true);
    }

    public static boolean isTabEmulated() {
        return prefs.getBoolean("tab_emulated", true);
    }

    public static boolean isWtspVisible() {
        return prefs.getBoolean("whitespace_visible", true);
    }

    public static int getTabSize() {
        return prefs.getInt("tab_size", 4);
    }

    public static String getDirPath() {
        return prefs.get("dir_path", "/");
    }

    /*--------------------*/
    
    public static void setAutoIndent(boolean bool) {
        prefs.putBoolean("auto_indent", bool);
    }

    public static void setTabEmulated(boolean bool) {
        prefs.putBoolean("tab_emulated", bool);
    }

    public static void setWtspVisible(boolean bool) {
        prefs.putBoolean("whitespace_visible", bool);
    }

    public static void setTabSize(int size) {
        prefs.putInt("tab_size", size);
    }

    public static void setDirPath(String path) {
        prefs.put("dir_path", path);
    }
}
