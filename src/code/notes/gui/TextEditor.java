/* 
 * Code-Notes | TextEditor.java
 * Created | 1:29:02 PM ‎Oct ‎10, ‎2019
 */
package code.notes.gui;

import code.notes.util.FileChooser;
import code.notes.util.FileHandler;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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

        editorPane = new javax.swing.JTabbedPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuOpen = new javax.swing.JMenuItem();
        menuSave = new javax.swing.JMenuItem();
        menuSaveAs = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem1 = new javax.swing.JMenuItem();
        menuCloseTab = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        java.util.ResourceBundle bundle = java.util.ResourceBundle.getBundle("code/notes/Bundle"); // NOI18N
        setTitle(bundle.getString("title")); // NOI18N
        setMinimumSize(new java.awt.Dimension(500, 300));

        editorPane.setToolTipText("");
        editorPane.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        editorPane.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                editorPaneComponentAdded(evt);
            }
        });

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
        jMenu1.add(jSeparator2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText(bundle.getString("new_file")); // NOI18N
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        menuCloseTab.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        menuCloseTab.setText(bundle.getString("close_file")); // NOI18N
        menuCloseTab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuCloseTabActionPerformed(evt);
            }
        });
        jMenu1.add(menuCloseTab);
        jMenu1.add(jSeparator1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText(bundle.getString("edit")); // NOI18N
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editorPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(editorPane, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public SingleEditor getCurrentEditor() {
        return (SingleEditor) editorPane.getSelectedComponent();
    }

    private void menuOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOpenActionPerformed
        String path = FileChooser.open();
        if(path == null)return;
        addNewTab(path);
    }//GEN-LAST:event_menuOpenActionPerformed

    private void menuSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveActionPerformed
        SingleEditor singleEditor = getCurrentEditor();
        String currentText = singleEditor.getTextArea().getText();

        if (singleEditor.getPath().isEmpty()) {
            menuSaveAsActionPerformed(evt);
            return;
        }

        FileHandler.save(currentText, singleEditor.getPath());
    }//GEN-LAST:event_menuSaveActionPerformed

    private void menuSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuSaveAsActionPerformed
        SingleEditor singleEditor = getCurrentEditor();
        
        String path = FileChooser.save(singleEditor.getFileName());
        
        if(path == null)return;
        String currentText = singleEditor.getTextArea().getText();

        FileHandler.save(currentText, path);
        singleEditor.setContent(currentText);

        singleEditor.setPath(path);
        editorPane.setTitleAt(singleEditor.getTabID(), singleEditor.getFileName());
    }//GEN-LAST:event_menuSaveAsActionPerformed

    private void addNewTab(String path) {
        SingleEditor singleEditor = new SingleEditor(editorPane.getTabCount(), path);
        
        String filename = singleEditor.getFileName();
        editorPane.addTab(filename, singleEditor);
        editorPane.setTabComponentAt(singleEditor.getTabID(), new TabHeader(this, singleEditor.getTabID(), filename));
    }

    private void addEmptyTab() {
        SingleEditor singleEditor = new SingleEditor(editorPane.getTabCount());
        
        editorPane.addTab(singleEditor.getFileName(), singleEditor);
        editorPane.setTabComponentAt(singleEditor.getTabID(), new TabHeader(this, singleEditor.getTabID()));
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        addEmptyTab();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void menuCloseTabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuCloseTabActionPerformed
        editorPane.removeTabAt(getCurrentEditor().getTabID());
    }//GEN-LAST:event_menuCloseTabActionPerformed

    private void editorPaneComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_editorPaneComponentAdded
        editorPane.setSelectedIndex(editorPane.getTabCount()-1);
    }//GEN-LAST:event_editorPaneComponentAdded

    public void closeTabAt(int tabID){
        editorPane.removeTabAt(tabID);
    }

    public JTabbedPane getEditorPane() {
        return editorPane;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane editorPane;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JMenuItem menuCloseTab;
    private javax.swing.JMenuItem menuOpen;
    private javax.swing.JMenuItem menuSave;
    private javax.swing.JMenuItem menuSaveAs;
    // End of variables declaration//GEN-END:variables

}
