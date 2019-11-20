/*
 * Code-Notes-FX | FXMLPreferencesController.java
 * Created | 10:03:52 PM Nov 19, 2019
 */
package code.notes.fxgui;

import code.notes.util.UserPreferences;
import java.awt.GraphicsEnvironment;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author phwts
 */
public class FXMLPreferencesController implements Initializable {

    @FXML
    private CheckBox box_autoindent;
    @FXML
    private CheckBox box_translatetab;
    @FXML
    private CheckBox box_wtsp;
    @FXML
    private ComboBox combo_indent;
    @FXML
    private ComboBox combo_font_fam;
    @FXML
    private ComboBox combo_font_size;
    @FXML
    private TextField field_dir_path;
    @FXML
    private ComboBox combo_locale;

    @FXML
    private void handleBrowseAction(ActionEvent e) {
        System.out.println("browse");
    }

    @FXML
    private void handleRestoreAction(ActionEvent e) {
        UserPreferences.resetPreferences();
        handleCancelAction(e);
    }

    @FXML
    private void handleSaveAction(ActionEvent e) {
        UserPreferences.setAutoIndent(box_autoindent.isSelected());
        UserPreferences.setTabEmulated(box_translatetab.isSelected());
        UserPreferences.setWtspVisible(box_wtsp.isSelected());

        int indent = 0;
        int font_size = 0;
        try {
            indent = Integer.valueOf((String) combo_indent.getSelectionModel().getSelectedItem());
            font_size = Integer.valueOf((String) combo_font_size.getSelectionModel().getSelectedItem());
        } catch (ClassCastException ex) {
            indent = ((int) combo_indent.getSelectionModel().getSelectedItem());
            font_size = ((int) combo_font_size.getSelectionModel().getSelectedItem());
        }

        UserPreferences.setTabSize(indent);
        UserPreferences.setFontSize(font_size);

        UserPreferences.setFontFamily((String) combo_font_fam.getSelectionModel().getSelectedItem());

        UserPreferences.setDirPath(field_dir_path.getText());
        
        switch (((String) combo_locale.getSelectionModel().getSelectedItem()).toLowerCase()) {
            case "english":
                UserPreferences.setLocale("en");
                break;
            case "ภาษาไทย":
                UserPreferences.setLocale("th");
                break;
        }
        
        handleCancelAction(e);
    }

    @FXML
    private void handleCancelAction(ActionEvent e) {
        Stage stage = (Stage) box_wtsp.getScene().getWindow();
        stage.close();
    }

    private void loadSettings() {
        box_autoindent.setSelected(UserPreferences.isAutoIndent());
        box_translatetab.setSelected(UserPreferences.isTabEmulated());
        box_wtsp.setSelected(UserPreferences.isWtspVisible());

        combo_indent.getSelectionModel().select(String.valueOf(UserPreferences.getTabSize()));
        combo_font_fam.getSelectionModel().select(UserPreferences.getFontFamily());
        combo_font_size.getSelectionModel().select(String.valueOf(UserPreferences.getFontSize()));

        field_dir_path.setText(UserPreferences.getDirPath());

        switch (UserPreferences.getLocale().toLowerCase()) {
            case "en":
                combo_locale.getSelectionModel().select("English");
                break;
            case "th":
                combo_locale.getSelectionModel().select("ภาษาไทย");
                break;
        }
    }

    private void loadForms() {
        for (int i = 2; i <= 16; i *= 2) {
            combo_indent.getItems().add(i);
        }
        combo_font_size.getItems().addAll(8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72);
        combo_locale.getItems().addAll("English", "ภาษาไทย");
        this.loadFonts();
    }

    private void loadFonts() {
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        combo_font_fam.getItems().add("DejaVu Sans Mono Thai");
        for (int i = 0; i < fonts.length; i++) {
            combo_font_fam.getItems().add(fonts[i]);
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.loadForms();
        this.loadSettings();
    }

}
