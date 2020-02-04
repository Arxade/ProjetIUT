/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.forms.cardPanels;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.HeadlessException;
import sgbd.forms.dialogs.TableCreationPanel;
import sgbd.database.Table;
import java.awt.event.ActionEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import sgbd.controllers.Controller;
import sgbd.forms.MainFrame;
import sgbd.forms.dialogs.TableAlterationPanel;
import sgbd.forms.dialogs.TableDonneesCRUDPanel;

/**
 *
 * @author Kazed
 */
public class VisualizationPanel extends javax.swing.JPanel {

    private Controller controller;
    private Table[] recentTables = new Table[10];

    /**
     * Creates new form VizualisationPanel
     */
    public VisualizationPanel() {
        initComponents();
        JPopupMenu pppLstTables = new JPopupMenu();
        pppLstTables.add(new JMenuItem("Modifier"));
        pppLstTables.add(new JMenuItem("Supprimer"));
        pppLstTables.addPopupMenuListener(new PopupMenuListener() {
            @Override
            public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
            }

            @Override
            public void popupMenuCanceled(PopupMenuEvent e) {
            }
        });
        lstTables.setComponentPopupMenu(pppLstTables);
        for (Component comp : pppLstTables.getComponents()) {
            JMenuItem item = (JMenuItem) comp;
            item.addActionListener((ActionEvent e) -> {
                switch (((JMenuItem) e.getSource()).getText()) {
                    case "Modifier":
                        showAlterTableDialog(e);
                        break;
                    case "Supprimer":
                        showDropTableDialog(e);
                        break;
                    default:
                        break;
                }
            });
        }
    }

    public void setComponentsParams(Font f) {
        for (Component comp : lpnContainer.getComponents()) {
            boolean setFont = true;
            switch (comp.getClass().getSimpleName()) {
                case "JToolBar":
                    setFont = false;
                    JToolBar tools = (JToolBar) comp;
                    for (Component tool : tools.getComponents()) {
                        tool.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    }
                    break;
                case "JButton":
                    comp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                default:
                    break;
            }
            if (setFont) {
                comp.setFont(f);
            }
        }
    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    public void loadPanel(Controller c) {
        controller = c;
        lblUserName.setText(controller.getUserName());
        lblDbName.setText(controller.getDatabaseName());
        setTablesList();
    }

    public void setTablesList() {
        DefaultListModel listModel = (DefaultListModel) lstTables.getModel();
        String[] tablesList = controller.getTablesList();
        listModel.removeAllElements();
        for (String table : tablesList) {
            listModel.addElement(table);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lpnContainer = new javax.swing.JLayeredPane();
        toolBar = new javax.swing.JToolBar();
        btnDisonnect = new javax.swing.JButton();
        lblUser = new javax.swing.JLabel();
        lblDb = new javax.swing.JLabel();
        lblUserName = new javax.swing.JLabel();
        lblDbName = new javax.swing.JLabel();
        slpTables = new javax.swing.JScrollPane();
        lstTables = new javax.swing.JList<>();
        slpAttributes = new javax.swing.JScrollPane();
        tblAttributes = new javax.swing.JTable();
        btnCreateTable = new javax.swing.JButton();
        lblTableName = new javax.swing.JLabel();
        jButtonDonneesCRUD = new javax.swing.JButton();
        btnAlterTable = new javax.swing.JButton();
        btnDropTable = new javax.swing.JButton();
        btnRenameTable = new javax.swing.JButton();

        setName("visualizationPanel"); // NOI18N

        toolBar.setFloatable(false);
        toolBar.setPreferredSize(new java.awt.Dimension(100, 48));

        btnDisonnect.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sgbd/img/logout.png"))); // NOI18N
        btnDisonnect.setToolTipText("Déconnexion");
        btnDisonnect.setBorder(null);
        btnDisonnect.setContentAreaFilled(false);
        btnDisonnect.setPreferredSize(new java.awt.Dimension(32, 32));
        btnDisonnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisonnectActionPerformed(evt);
            }
        });
        toolBar.add(btnDisonnect);

        lblUser.setText("Utilisateur :");

        lblDb.setText("Base de données :");

        lstTables.setModel(new DefaultListModel()
        );
        lstTables.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                label.setFont(((MainFrame)SwingUtilities.getWindowAncestor(lstTables.getParent())).getMainPanel().getFont());
                return label;
            }
        });
        lstTables.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                setSelectedListItem(evt);
            }
        });
        lstTables.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                setSelectedListItem(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                showSelectedListItem(evt);
            }
        });
        slpTables.setViewportView(lstTables);

        tblAttributes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Type", "Longueur", "Clé primaire", "Not null", "Unique", "Clé étrangère"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        slpAttributes.setViewportView(tblAttributes);
        tblAttributes.setRowSelectionAllowed(false);
        tblAttributes.setColumnSelectionAllowed(false);

        btnCreateTable.setText("Nouvelle table");
        btnCreateTable.setPreferredSize(new java.awt.Dimension(95, 32));
        btnCreateTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateTableActionPerformed(evt);
            }
        });

        jButtonDonneesCRUD.setText("Gestion de données");
        jButtonDonneesCRUD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDonneesCRUDActionPerformed(evt);
            }
        });

        btnAlterTable.setText("Modifier la table");
        btnAlterTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterTableActionPerformed(evt);
            }
        });

        btnDropTable.setText("Supprimer la table");
        btnDropTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDropTableActionPerformed(evt);
            }
        });

        btnRenameTable.setText("Renommer la table");
        btnRenameTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenameTableActionPerformed(evt);
            }
        });

        lpnContainer.setLayer(toolBar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(lblUser, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(lblDb, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(lblUserName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(lblDbName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(slpTables, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(slpAttributes, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(btnCreateTable, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(lblTableName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(jButtonDonneesCRUD, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(btnAlterTable, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(btnDropTable, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(btnRenameTable, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpnContainerLayout = new javax.swing.GroupLayout(lpnContainer);
        lpnContainer.setLayout(lpnContainerLayout);
        lpnContainerLayout.setHorizontalGroup(
            lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(lpnContainerLayout.createSequentialGroup()
                .addComponent(lblDb)
                .addGap(8, 8, 8)
                .addComponent(lblDbName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTableName)
                .addGap(365, 365, 365))
            .addGroup(lpnContainerLayout.createSequentialGroup()
                .addComponent(lblUser)
                .addGap(8, 8, 8)
                .addComponent(lblUserName)
                .addGap(95, 95, 95))
            .addGroup(lpnContainerLayout.createSequentialGroup()
                .addComponent(slpTables, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slpAttributes)
                    .addGroup(lpnContainerLayout.createSequentialGroup()
                        .addComponent(btnCreateTable, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
                        .addComponent(btnAlterTable, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnRenameTable, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnDropTable, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonDonneesCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        lpnContainerLayout.setVerticalGroup(
            lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpnContainerLayout.createSequentialGroup()
                .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lpnContainerLayout.createSequentialGroup()
                        .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblUser)
                            .addComponent(lblUserName))
                        .addGap(0, 0, 0)
                        .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDb)
                            .addComponent(lblDbName)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnContainerLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTableName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(lpnContainerLayout.createSequentialGroup()
                        .addComponent(slpAttributes, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(32, 32, 32)
                        .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonDonneesCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAlterTable, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRenameTable, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnDropTable, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCreateTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59))
                    .addComponent(slpTables, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lpnContainer)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lpnContainer)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDisonnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisonnectActionPerformed
        //Ferme la connexion et réinitialise le contrôleur
        controller.close();
        controller = null;

        //Vide la liste des tables et la table des attributs
        ((DefaultListModel) lstTables.getModel()).clear();
        ((DefaultTableModel) tblAttributes.getModel()).setRowCount(0);
        lblTableName.setText("");
        lblUserName.setText("");
        lblDbName.setText("");

        //Switche sur le panel de connexion
        ((MainFrame) SwingUtilities.getWindowAncestor(this)).getCardPanel("first");
    }//GEN-LAST:event_btnDisonnectActionPerformed

    private void showDropTableDialog(java.awt.event.ActionEvent evt) {
        String tableToDrop = lstTables.getSelectedValue();
        String message = "Supprimer la table " + tableToDrop + " ?";
        JCheckBox ckbCascadeConstraints = new JCheckBox("Supprimer les contraintes d'intégrité référentielle");
        Object[] params = {message, ckbCascadeConstraints};
        int dialog = JOptionPane.showConfirmDialog(null, params, "Suppression d'une table", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
        if (dialog == JOptionPane.YES_OPTION) {
            if (controller.dropTable(lstTables.getSelectedValue(), ckbCascadeConstraints.isSelected()) == true) {
                ((DefaultListModel) lstTables.getModel()).removeElement(tableToDrop);
                DefaultTableModel tableModel = (DefaultTableModel) tblAttributes.getModel();
                tableModel.setRowCount(0);
                JOptionPane.showMessageDialog(null, "Table " + tableToDrop + " supprimée.");
            }
        }
    }

    private void showAlterTableDialog(java.awt.event.ActionEvent evt) {
        final JDialog dialog = new JDialog();
        dialog.setTitle("Modification");
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        String selected = lstTables.getSelectedValue();
        TableAlterationPanel content = new TableAlterationPanel(controller, recentTables[0]);
        content.getButton("confirmer").addActionListener((ActionEvent e) -> {
        });
        content.getButton("annuler").addActionListener((ActionEvent e) -> {
            dialog.dispose();
        });
        dialog.setContentPane(content);
        dialog.setResizable(true);
        dialog.pack();
        dialog.setVisible(true);
    }

    private void btnCreateTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateTableActionPerformed
        int columns = 0;
        //set du nbr de lignes de la table 
        try {
            String strNbCol = JOptionPane.showInputDialog("Entrer le nombre de colonnes : ");
            if (strNbCol != null) {
                columns = Integer.parseInt(strNbCol);
                final JDialog dialog = new JDialog();
                dialog.setTitle("Nouvelle table");
                dialog.setModal(true);
                dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                TableCreationPanel content = new TableCreationPanel(controller, columns);
                content.getButton("confirm").addActionListener((ActionEvent e) -> {
                    String name = content.getTableName().getText();
                    dialog.dispose();
                });
                dialog.setContentPane(content);
                dialog.setResizable(true);
                dialog.pack();
                dialog.setVisible(true);
                setTablesList();
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "erreur lors de la saisie du nombre de colonnes.");
        }
    }//GEN-LAST:event_btnCreateTableActionPerformed

    private void showSelectedListItem(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_showSelectedListItem
        String selected = lstTables.getSelectedValue();
        DefaultTableModel tableModel = (DefaultTableModel) tblAttributes.getModel();
        tableModel.setRowCount(0);
        int i = 0;
        boolean tblFound = false;
        resizeColumnWidth(tblAttributes);
        while (recentTables[i] != null && i < recentTables.length && !tblFound) {
            if (recentTables[i].getName().equals(selected)) {
                tblFound = true;
            }
            i++;
        }
        if (tblFound) {
            Table t = recentTables[i - 1];
            for (int j = i - 1; j > 1; j--) {
                recentTables[j] = recentTables[j - 1];
            }
            recentTables[0] = t;
        } else if (i == recentTables.length) {
            for (int j = recentTables.length - 1; j > 1; j--) {
                recentTables[j] = recentTables[j - 1];
            }
            recentTables[0] = new Table(selected);
            controller.getAttributesList(recentTables[0]);
        } else {
            for (int j = i - 1; j > 1; j--) {
                recentTables[j] = recentTables[j - 1];
            }
            recentTables[0] = new Table(selected);
            controller.getAttributesList(recentTables[0]);
        }
        if (!recentTables[0].attributes().isEmpty()) {
            recentTables[0].attributes().forEach((a) -> {
                tableModel.addRow(a.toObject());
            });
            resizeColumnWidth(tblAttributes);
            lblTableName.setText(selected);
        } else
            lblTableName.setText("");
    }//GEN-LAST:event_showSelectedListItem

    private void setSelectedListItem(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setSelectedListItem
//        int index = lstTables.locationToIndex(evt.getPoint());
//        if(index > -1 && index < lstTables.getVisibleRowCount()) lstTables.setSelectedIndex(index);
//        else lstTables.clearSelection();
    }//GEN-LAST:event_setSelectedListItem

    private void jButtonDonneesCRUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDonneesCRUDActionPerformed
        if (lstTables.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une table");
        } else {
            final JDialog dialog = new JDialog();
            dialog.setTitle("Gestion des données");
            dialog.setModal(true);
            dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

            TableDonneesCRUDPanel content = new TableDonneesCRUDPanel(controller, recentTables[0]);
            //content.getButton("confirm").addActionListener((ActionEvent e) -> {
            //    String name = content.getTableName().getText();
            //    setTablesList();
            //    dialog.dispose();
            //});
            dialog.setContentPane(content);
            dialog.setResizable(true);
            dialog.pack();
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_jButtonDonneesCRUDActionPerformed

    private void btnDropTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDropTableActionPerformed
        if (lstTables.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une table");
        } else {
            String tableToDrop = lstTables.getSelectedValue();
            String message = "Supprimer la table " + tableToDrop + " ?";
            JCheckBox ckbCascadeConstraints = new JCheckBox("Supprimer les contraintes d'intégrité référentielle");
            Object[] params = {message, ckbCascadeConstraints};
            int dialog = JOptionPane.showConfirmDialog(null, params, "Suppression d'une table", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (dialog == JOptionPane.YES_OPTION) {
                if (controller.dropTable(lstTables.getSelectedValue(), ckbCascadeConstraints.isSelected()) == true) {
                    ((DefaultListModel) lstTables.getModel()).removeElement(tableToDrop);
                    DefaultTableModel tableModel = (DefaultTableModel) tblAttributes.getModel();
                    tableModel.setRowCount(0);
                    JOptionPane.showMessageDialog(null, "Table " + tableToDrop + " supprimée.");
                }
            }
        }

    }//GEN-LAST:event_btnDropTableActionPerformed


    private void btnAlterTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterTableActionPerformed
        if (lstTables.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une table");
        } else {
            final JDialog dialog = new JDialog();
            dialog.setTitle("Modification");
            dialog.setModal(true);
            dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            TableAlterationPanel content = new TableAlterationPanel(controller, recentTables[0]);
            content.getButton("confirmer").addActionListener((ActionEvent e) -> {
            });
            content.getButton("annuler").addActionListener((ActionEvent e) -> {
                if (content.getButton("annuler").getText() == "Fermer") {
                    DefaultTableModel tableModel = (DefaultTableModel) tblAttributes.getModel();
                    tableModel.setRowCount(0);
                    dialog.dispose();
                    recentTables[0].attributes().forEach((a) -> {
                        tableModel.addRow(a.toObject());
                    });
                    resizeColumnWidth(tblAttributes);
                }
            });
            content.getButton("rename").addActionListener((ActionEvent e) -> {
            });
            dialog.setContentPane(content);
            dialog.setResizable(true);
            dialog.pack();
            dialog.setVisible(true);
        }
    }//GEN-LAST:event_btnAlterTableActionPerformed

    private void btnRenameTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenameTableActionPerformed
        if (lstTables.getSelectedValue() == null) {
            JOptionPane.showMessageDialog(null, "Veuillez sélectionner une table");
        } else {
            String inputNom, nomActuel;
            nomActuel = lstTables.getSelectedValue();
            inputNom = JOptionPane.showInputDialog(null, "Entrer le nouveau nom de la table " + nomActuel + " : ", "Renommage de la table", JOptionPane.QUESTION_MESSAGE);
            if (inputNom != null)
            {
                if (controller.renameTable(nomActuel, inputNom.toUpperCase()) == true) {
                    setTablesList();
                }
            }

        }

    }//GEN-LAST:event_btnRenameTableActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterTable;
    private javax.swing.JButton btnCreateTable;
    private javax.swing.JButton btnDisonnect;
    private javax.swing.JButton btnDropTable;
    private javax.swing.JButton btnRenameTable;
    private javax.swing.JButton jButtonDonneesCRUD;
    private javax.swing.JLabel lblDb;
    private javax.swing.JLabel lblDbName;
    private javax.swing.JLabel lblTableName;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLabel lblUserName;
    private javax.swing.JLayeredPane lpnContainer;
    private javax.swing.JList<String> lstTables;
    private javax.swing.JScrollPane slpAttributes;
    private javax.swing.JScrollPane slpTables;
    private javax.swing.JTable tblAttributes;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables
}
