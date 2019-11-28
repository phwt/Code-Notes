/*
 * Code-Notes-FX | FXMLPreferencesController.java
 * Created | 10:03:52 PM Nov 19, 2019
 */
package code.notes.gui;

import code.notes.util.Bundle;
import code.notes.util.FileChooserDialog;
import code.notes.util.UserPreferences;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

    private String current_locale;
    private String current_path;

    @FXML
    private void handleBrowseAction(ActionEvent e) {
        Path path = FileChooserDialog.openDirectory((Stage) box_autoindent.getScene().getWindow());
        if (path != null) {
            field_dir_path.setText(path.toString());
        }
    }

    @FXML
    private void handleRestoreAction(ActionEvent e) {
        UserPreferences.resetPreferences();
        refreshEditors();
        handleCancelAction(e);
    }

    @FXML
    private void handleSaveAction(ActionEvent e) {
        UserPreferences.setAutoIndent(box_autoindent.isSelected());
        UserPreferences.setTabEmulated(box_translatetab.isSelected());
        UserPreferences.setWtspVisible(box_wtsp.isSelected());

        int indent = Integer.valueOf((String) combo_indent.getSelectionModel().getSelectedItem());
        int font_size = Integer.valueOf((String) combo_font_size.getSelectionModel().getSelectedItem());

        UserPreferences.setTabSize(indent);
        UserPreferences.setFontSize(font_size);

        UserPreferences.setFontFamily((String) combo_font_fam.getSelectionModel().getSelectedItem());
        
        String new_path = field_dir_path.getText();
        if (!new_path.equals(current_path)){
            UserPreferences.setDirPath(new_path);
            CodeNotes.main_controller.loadTree();
        }

        String new_locale = ((String) combo_locale.getSelectionModel().getSelectedItem());
        if (!new_locale.equals(current_locale)) {
            Alert alert = new Alert(
                    AlertType.INFORMATION,
                    Bundle.get("locale_notice_sub")
            );
            alert.showAndWait();

            switch (new_locale.toLowerCase()) {
                case "english":
                    UserPreferences.setLocale("en");
                    break;
                case "ภาษาไทย":
                    UserPreferences.setLocale("th");
                    break;
            }
        }
        refreshEditors();
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
        current_path = UserPreferences.getDirPath();

        switch (UserPreferences.getLocale().toLowerCase()) {
            case "en":
                combo_locale.getSelectionModel().select("English");
                break;
            case "th":
                combo_locale.getSelectionModel().select("ภาษาไทย");
                break;
        }
        current_locale = (String) combo_locale.getSelectionModel().getSelectedItem();
    }

    private void loadForms() {
        for (int i = 2; i <= 16; i *= 2) {
            combo_indent.getItems().add(i);
        }
        combo_font_size.getItems().addAll("8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72");
        combo_locale.getItems().addAll("English", "ภาษาไทย");
        this.loadFonts();
    }

    private void loadFonts() {
//        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        combo_font_fam.getItems().add("DejaVu Sans Mono Thai");
//        for (int i = 0; i < fonts.length; i++) {
//            combo_font_fam.getItems().add(fonts[i]);
//        }
    }

    private void refreshEditors() {
        // TODO: Fix thread problem
        FXMLMainController.getTabPool().forEach((tab) -> {
            tab.getEDITOR().refreshStyles();
        });
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
