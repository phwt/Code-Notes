/*
 * Code-Notes | UserPreferences.java
 * Created | 4:01:28 PM Nov 12, 2019
 */
package code.notes.util;

import java.util.prefs.Preferences;

/**
 *
 * @author phwts
 */
public class UserPreferences {

    private static final Preferences prefs = Preferences.userRoot().node(UserPreferences.class.getName());;

    /**
     * Reset preference to defaults
     */
    public static void resetPreferences() {
        setAutoIndent(true);
        setTabEmulated(false);
        setWtspVisible(false);
        setTabSize(4);
        setFontSize(14);
        setFontFamily("DejaVu Sans Mono Thai");
        setDirPath("");
        setLocale("en");
    }
    
    /**
     * @return Preference's object
     */
    public static Preferences getPrefs() {
        return prefs;
    }

    public static boolean isAutoIndent() {
        return prefs.getBoolean("auto_indent", true);
    }

    public static boolean isTabEmulated() {
        return prefs.getBoolean("tab_emulated", false);
    }

    public static boolean isWtspVisible() {
        return prefs.getBoolean("whitespace_visible", false);
    }

    public static int getTabSize() {
        return prefs.getInt("tab_size", 4);
    }
    
    public static int getFontSize() {
        return prefs.getInt("font_size", 14);
    }
    
    public static String getFontFamily() {
        return prefs.get("font_family", "DejaVu Sans Mono Thai");
    }

    public static String getDirPath() {
        return prefs.get("dir_path", "");
    }
    
    public static String getLocale() {
        return prefs.get("locale", "en");
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
    
    public static void setFontSize(int size) {
        prefs.putInt("font_size", size);
    }
    
    public static void setFontFamily(String font) {
        prefs.put("font_family", font);
    }

    public static void setDirPath(String path) {
        prefs.put("dir_path", path);
    }
    
    public static void setLocale(String locale) {
        prefs.put("locale", locale);
    }
}
