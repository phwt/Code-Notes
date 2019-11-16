/*
 * Code-Notes | DirectoryListing.java
 * Created | 1:02:08 AM Nov 17, 2019
 */
package code.notes.gui;

import code.notes.util.UserPreferences;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.nio.file.Path;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * @author Gilbert Le Blanc https://stackoverflow.com/a/23808238/
 */
public class DirectoryListing extends javax.swing.JTree {

    static File fileRoot;
    static DefaultMutableTreeNode root;
    static DefaultTreeModel treeModel;

    public DirectoryListing() {
        this.init();
        this.addListener();
    }

    public void init() {
        fileRoot = new File(UserPreferences.getDirPath());
        root = new DefaultMutableTreeNode(new FileNode(fileRoot));
        treeModel = new DefaultTreeModel(root);

        this.setModel(treeModel);
        this.setShowsRootHandles(true);

        CreateChildNodes ccn = new CreateChildNodes(fileRoot, root);
        new Thread(ccn).start();
    }

    private Path toPath(TreePath path) {
        FileNode user_path = (FileNode) ((DefaultMutableTreeNode) path.getLastPathComponent()).getUserObject();
        File file = new File(user_path.toStringPath());
        if (file.isDirectory())
            return null;
        return file.toPath();
    }

    public void addListener() {
        JTree tree = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = tree.getRowForLocation(e.getX(), e.getY());
                TreePath selected_path = tree.getPathForLocation(e.getX(), e.getY());
                if (selected_row != -1) {
                    if (e.getClickCount() == 2) {
                        Path file_path = toPath(selected_path);
                        if (file_path != null) {
                            CodeNotes.text_editor.addTab(file_path);
                        }
                    }
                }
            }
        });
    }

    public static class CreateChildNodes implements Runnable {

        private DefaultMutableTreeNode root;

        private File fileRoot;

        public CreateChildNodes(File fileRoot,
                DefaultMutableTreeNode root) {
            this.fileRoot = fileRoot;
            this.root = root;
        }

        @Override
        public void run() {
            createChildren(fileRoot, root);
        }

        private void createChildren(File fileRoot,
                DefaultMutableTreeNode node) {
            File[] files = fileRoot.listFiles();
            if (files == null) {
                return;
            }

            for (File file : files) {
                DefaultMutableTreeNode childNode
                        = new DefaultMutableTreeNode(new FileNode(file));
                node.add(childNode);
                if (file.isDirectory()) {
                    createChildren(file, childNode);
                }
            }
        }

    }

    public static class FileNode {

        private File file;

        public FileNode(File file) {
            this.file = file;
        }

        @Override
        public String toString() {
            String name = file.getName();
            if (name.equals("")) {
                return file.getAbsolutePath();
            } else {
                return name;
            }
        }

        public String toStringPath() {
            return file.getAbsolutePath();
        }
    }
}
