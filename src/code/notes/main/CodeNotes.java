/*
 * Code-Notes | CodeNotes.java
 * Created | 1:27:10 PM Oct 10, 2019
 */
package code.notes.main;

import code.notes.gui.TextEditor;
import code.notes.util.FileChooser;
import javax.swing.UIManager;

/**
 *
 * @author phwts
 */
public class CodeNotes extends javax.swing.JFrame {
    TextEditor text_editor = new TextEditor();
    /**
     * Creates new form CodeNotes
     */
    public CodeNotes() {
        initComponents();
        editor_panel.add(text_editor);
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
        menu_save = new javax.swing.JMenuItem();
        menu_saveas = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        jMenuBar1.add(menu_file);

        jMenu2.setText("Edit");
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
        text_editor.addTab(FileChooser.openFile());
    }//GEN-LAST:event_menu_openActionPerformed

    private void menu_saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_saveActionPerformed
        text_editor.getActiveEditor().save();
    }//GEN-LAST:event_menu_saveActionPerformed

    private void menu_saveasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_saveasActionPerformed
        text_editor.getActiveEditor().saveAs();
    }//GEN-LAST:event_menu_saveasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
    private javax.swing.JMenu menu_file;
    private javax.swing.JMenuItem menu_open;
    private javax.swing.JMenuItem menu_save;
    private javax.swing.JMenuItem menu_saveas;
    // End of variables declaration//GEN-END:variables
}
