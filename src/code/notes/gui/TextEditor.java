/* 
 * Code-Notes | TextEditor.java
 * Created | 1:29:02 PM ‎Oct ‎10, ‎2019
 */
package code.notes.gui;

import code.notes.util.FileChooser;
import code.notes.util.FileHandler;
import code.notes.Bundle;
import java.awt.*;
import javax.swing.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fife.ui.rtextarea.*;
import org.fife.ui.rsyntaxtextarea.*;

/**
 *
 * @author phwts
 */
public class TextEditor extends javax.swing.JFrame {

    /**
     * Creates new form TextEditor
     */
    public TextEditor() {
        initComponents();
        addEmptyTab();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuOpen = new javax.swing.JMenuItem();
        menuSave = new javax.swing.JMenuItem();
        menuSaveAs = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("code/notes/Bundle"); // NOI18N
        setTitle(bundle.getString("title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(500, 300));

        jMenu1.setText("File");

        menuOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        menuOpen.setText(bundle.getString("open")); // NOI18N
        menuOpen.setActionCommand(bundle.getString("open")); // NOI18N
        menuOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOpenActionPerformed(evt);
            }
        });
        jMenu1.add(menuOpen);

        menuSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        menuSave.setText(bundle.getString("save")); // NOI18N
        menuSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveActionPerformed(evt);
            }
        });
        jMenu1.add(menuSave);

        menuSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        menuSaveAs.setText(bundle.getString("save_as")); // NOI18N
        menuSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuSaveAsActionPerformed(evt);
            }
        });
        jMenu1.add(menuSaveAs);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem1.setText("Add Tab");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public SingleEditor getCurrentEditor(){
        return (SingleEditor) jTabbedPane1.getSelectedComponent();
    }

    private void menuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpenActionPerformed
        String path = FileChooser.open();
        addNewTab(path);
    }//GEN-LAST:event_menuOpenActionPerformed

    private void menuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveActionPerformed
        SingleEditor singleEditor = getCurrentEditor();
        String currentText = singleEditor.getTextArea().getText();
        
        if(singleEditor.getPath().isEmpty()){
            menuSaveAsActionPerformed(evt);
            return;
        }
        
        try {
            FileHandler.save(currentText, singleEditor.getPath());
        } catch (IOException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuSaveActionPerformed

    private void menuSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveAsActionPerformed
        String path = FileChooser.save();
        SingleEditor singleEditor = getCurrentEditor();
        String currentText = singleEditor.getTextArea().getText();
        try {
            FileHandler.save(currentText, path);
            singleEditor.setContent(currentText);
            
            singleEditor.setPath(path);
            jTabbedPane1.setTitleAt(singleEditor.getTabID(), singleEditor.getFileName());
        } catch (IOException ex) {
            Logger.getLogger(TextEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_menuSaveAsActionPerformed

    private void addNewTab(String path){
        System.out.println(jTabbedPane1.getTabCount());
        SingleEditor singleEditor = new SingleEditor(jTabbedPane1.getTabCount(), path);
        jTabbedPane1.addTab(singleEditor.getFileName(), singleEditor);
        jTabbedPane1.setSelectedIndex(singleEditor.getTabID());
        System.out.println(singleEditor.getTabID());
    }
    
    private void addEmptyTab(){
        SingleEditor singleEditor = new SingleEditor(jTabbedPane1.getTabCount());
        jTabbedPane1.addTab(singleEditor.getFileName(), singleEditor);
        jTabbedPane1.setSelectedIndex(singleEditor.getTabID());
    }
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        addEmptyTab();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JMenuItem menuOpen;
    private javax.swing.JMenuItem menuSave;
    private javax.swing.JMenuItem menuSaveAs;
    // End of variables declaration//GEN-END:variables

}
