/*
 * Code-Notes | CodeNotes.java
 * Created | 1:27:10 PM Oct 10, 2019
 */
package code.notes.main;

import code.notes.Bundle;
import code.notes.gui.ExceptionForm;
import code.notes.gui.SettingsForm;
import code.notes.gui.TextEditor;
import code.notes.util.FileChooser;
import java.nio.file.Path;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author phwts
 */
public class CodeNotes extends javax.swing.JFrame {

    public static TextEditor text_editor;

    /**
     * Creates new form CodeNotes
     */
    public CodeNotes() {
        initComponents();
        text_editor = new TextEditor();
        editor_panel.add(text_editor);
        checkUnsavedEvent();
    }

    private void checkUnsavedEvent() {
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (text_editor.hasUnsaved()) {
                    int n = JOptionPane.showConfirmDialog(
                            null,
                            Bundle.get("save_notice_sub"), Bundle.get("save_notice_frame"),
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );
                    if (n == JOptionPane.YES_OPTION)
                        System.exit(0);
                } else {
                    System.exit(0);
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        directory_tree = new javax.swing.JTree();
        editor_panel = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        menu_file = new javax.swing.JMenu();
        menu_open = new javax.swing.JMenuItem();
        menu_newfile = new javax.swing.JMenuItem();
        menu_save = new javax.swing.JMenuItem();
        menu_saveas = new javax.swing.JMenuItem();
        menu_closetab = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menu_lookup = new javax.swing.JMenuItem();
        menu_perferences = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationByPlatform(true);

        jScrollPane1.setViewportView(directory_tree);

        editor_panel.setLayout(new java.awt.BorderLayout());

        menu_file.setText("File");

        menu_open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("code/notes/Bundle"); // NOI18N
        menu_open.setText(bundle.getString("open")); // NOI18N
        menu_open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_openActionPerformed(evt);
            }
        });
        menu_file.add(menu_open);

        menu_newfile.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        menu_newfile.setText(bundle.getString("new_file")); // NOI18N
        menu_newfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_newfileActionPerformed(evt);
            }
        });
        menu_file.add(menu_newfile);

        menu_save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menu_save.setText(bundle.getString("save")); // NOI18N
        menu_save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_saveActionPerformed(evt);
            }
        });
        menu_file.add(menu_save);

        menu_saveas.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menu_saveas.setText(bundle.getString("save_as")); // NOI18N
        menu_saveas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_saveasActionPerformed(evt);
            }
        });
        menu_file.add(menu_saveas);

        menu_closetab.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        menu_closetab.setText("Close Tab");
        menu_closetab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_closetabActionPerformed(evt);
            }
        });
        menu_file.add(menu_closetab);

        jMenuBar1.add(menu_file);

        jMenu2.setText(bundle.getString("tools")); // NOI18N

        menu_lookup.setText(bundle.getString("exception_lookup")); // NOI18N
        menu_lookup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_lookupActionPerformed(evt);
            }
        });
        jMenu2.add(menu_lookup);

        menu_perferences.setText(bundle.getString("preferences")); // NOI18N
        menu_perferences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_perferencesActionPerformed(evt);
            }
        });
        jMenu2.add(menu_perferences);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(editor_panel, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editor_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_openActionPerformed
        for (Path path : FileChooser.openFiles()) {
            text_editor.addTab(path);
        }
    }//GEN-LAST:event_menu_openActionPerformed

    private void menu_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_saveActionPerformed
        text_editor.getActiveEditor().save();
    }//GEN-LAST:event_menu_saveActionPerformed

    private void menu_saveasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_saveasActionPerformed
        text_editor.getActiveEditor().saveAs();
    }//GEN-LAST:event_menu_saveasActionPerformed

    private void menu_closetabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_closetabActionPerformed
        text_editor.closeTab();
    }//GEN-LAST:event_menu_closetabActionPerformed

    private void menu_newfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_newfileActionPerformed
        text_editor.addTab();
    }//GEN-LAST:event_menu_newfileActionPerformed

    private void menu_lookupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_lookupActionPerformed
        new ExceptionForm().setVisible(true);
    }//GEN-LAST:event_menu_lookupActionPerformed

    private void menu_perferencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_perferencesActionPerformed
        new SettingsForm().setVisible(true);
    }//GEN-LAST:event_menu_perferencesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            //Never happens
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CodeNotes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree directory_tree;
    private javax.swing.JPanel editor_panel;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JMenuItem menu_closetab;
    private javax.swing.JMenu menu_file;
    private javax.swing.JMenuItem menu_lookup;
    private javax.swing.JMenuItem menu_newfile;
    private javax.swing.JMenuItem menu_open;
    private javax.swing.JMenuItem menu_perferences;
    private javax.swing.JMenuItem menu_save;
    private javax.swing.JMenuItem menu_saveas;
    // End of variables declaration//GEN-END:variables
}
