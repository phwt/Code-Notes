/*
 * Code-Notes | SettingsForm.java
 * Created | 11:42:51 PM Nov 10, 2019
 */
package code.notes.gui;

import code.notes.Bundle;
import code.notes.util.FileChooser;
import code.notes.util.UserPreferences;
import java.nio.file.Path;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author phwts
 */
public class SettingsForm extends javax.swing.JFrame {

    final String locale_before = UserPreferences.getLocale();

    /**
     * Creates new form SettingsForm
     */
    public SettingsForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        checkbox_autoindent = new javax.swing.JCheckBox();
        checkbox_tab = new javax.swing.JCheckBox();
        checkbox_wtsp = new javax.swing.JCheckBox();
        spinner_tab = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        btn_save = new javax.swing.JButton();
        brn_reset = new javax.swing.JButton();
        btn_close = new javax.swing.JButton();
        txtf_path = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        combo_locale = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        spinner_font = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Settings");
        setLocationByPlatform(true);
        setResizable(false);

        checkbox_autoindent.setSelected(UserPreferences.isAutoIndent());
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("code/notes/Bundle"); // NOI18N
        checkbox_autoindent.setText(bundle.getString("settings_auto_indent")); // NOI18N

        checkbox_tab.setSelected(UserPreferences.isTabEmulated());
        checkbox_tab.setText(bundle.getString("settings_translate_tab")); // NOI18N

        checkbox_wtsp.setSelected(UserPreferences.isWtspVisible());
        checkbox_wtsp.setText(bundle.getString("settings_whitespace")); // NOI18N

        spinner_tab.setValue((int) UserPreferences.getTabSize());

        jLabel2.setText(bundle.getString("settings_tab_size")); // NOI18N

        btn_save.setText(bundle.getString("save")); // NOI18N
        btn_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_saveActionPerformed(evt);
            }
        });

        brn_reset.setText(bundle.getString("reset_default")); // NOI18N
        brn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brn_resetActionPerformed(evt);
            }
        });

        btn_close.setText(bundle.getString("cancel")); // NOI18N
        btn_close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_closeActionPerformed(evt);
            }
        });

        txtf_path.setText(UserPreferences.getDirPath());
        txtf_path.setName(""); // NOI18N

        jLabel1.setText(bundle.getString("settings_dir_root")); // NOI18N

        jButton4.setText(bundle.getString("settings_browse")); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        combo_locale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English", "ไทย" }));
        combo_locale.setSelectedItem((UserPreferences.getLocale().equals("en")) ? "English" : "ไทย");

        jLabel3.setText(bundle.getString("settings_locale")); // NOI18N

        jLabel4.setText(bundle.getString("settings_font_size")); // NOI18N

        spinner_font.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        spinner_font.setPreferredSize(new java.awt.Dimension(50, 26));
        spinner_font.setValue((int) UserPreferences.getFontSize());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(brn_reset)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_save, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_close, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtf_path, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(checkbox_tab)
                                    .addComponent(checkbox_wtsp)
                                    .addComponent(checkbox_autoindent))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(combo_locale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spinner_font, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spinner_tab, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_autoindent)
                    .addComponent(spinner_tab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(spinner_font, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(checkbox_tab))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkbox_wtsp)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo_locale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtf_path)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_close)
                    .addComponent(brn_reset)
                    .addComponent(btn_save))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_saveActionPerformed
        UserPreferences.setAutoIndent(checkbox_autoindent.isSelected());
        UserPreferences.setTabEmulated(checkbox_tab.isSelected());
        UserPreferences.setWtspVisible(checkbox_wtsp.isSelected());
        UserPreferences.setTabSize((int) spinner_tab.getValue());
        UserPreferences.setFontSize((int) spinner_font.getValue());
        UserPreferences.setDirPath(txtf_path.getText());

        String locale_current;
        switch ((String) combo_locale.getSelectedItem()) {
            case "English":
                locale_current = "en";
                break;
            case "ไทย":
                locale_current = "th";
                break;
            default:
                locale_current = null;
        }
        if (!locale_before.equals(locale_current)) {
            UserPreferences.setLocale(locale_current);
            Bundle.setLocale(Bundle.toLocale(locale_current));
            showLocalePrompt();
        }

        CodeNotes.text_editor.getEditorPool().forEach((editor) -> {
            editor.refreshStyles();
            editor.loadEditorFontSize();
        });
        CodeNotes.directory_listing.init();

        this.dispose();
    }//GEN-LAST:event_btn_saveActionPerformed

    private void brn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brn_resetActionPerformed
        // TODO add your handling code here:
        UserPreferences.resetPreferences();
        CodeNotes.text_editor.getEditorPool().forEach((editor) -> {
            editor.refreshStyles();
        });
        CodeNotes.directory_listing.init();
        this.dispose();
    }//GEN-LAST:event_brn_resetActionPerformed

    private void btn_closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_closeActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_btn_closeActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Path path = FileChooser.openDirectory();
        if(path == null) {
            return;
        }
        txtf_path.setText(String.valueOf(path));
    }//GEN-LAST:event_jButton4ActionPerformed

    private void showLocalePrompt() {
        JOptionPane.showMessageDialog(this, Bundle.get("locale_notice_sub"));
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            //Never happens
        }

        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new SettingsForm().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton brn_reset;
    private javax.swing.JButton btn_close;
    private javax.swing.JButton btn_save;
    private javax.swing.JCheckBox checkbox_autoindent;
    private javax.swing.JCheckBox checkbox_tab;
    private javax.swing.JCheckBox checkbox_wtsp;
    private javax.swing.JComboBox<String> combo_locale;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSpinner spinner_font;
    private javax.swing.JSpinner spinner_tab;
    private javax.swing.JTextField txtf_path;
    // End of variables declaration//GEN-END:variables

}
