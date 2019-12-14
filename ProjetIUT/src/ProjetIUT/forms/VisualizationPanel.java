/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetIUT.forms;

import ProjetIUT.DatabaseClasses.Table;
import ProjetIUT.classesConnexion.Connexion;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kazed
 */
public class VisualizationPanel extends javax.swing.JPanel {
    private Connexion c;
    private Table[] recentTables = new Table[10];

    /**
     * Creates new form VizualisationPanel
     */
    public VisualizationPanel() {
        initComponents();

        //empecher selection élément du tableau
        tblAttributes.setRowSelectionAllowed(false);
        tblAttributes.setColumnSelectionAllowed(false);
    }

    public void connect(Connexion c) {
        this.c = c;
        try {
            c.setTables();
            tablesList();
        }
        catch(SQLException e) {

        }
    }

    public void tablesList() {
        //Remplissage de la liste des tables
        ArrayList<String> tl = new ArrayList<>();
        c.getTables().keySet().forEach((t) -> {
            tl.add(t);
        });
        Collections.sort(tl);
        lstTables.setListData(tl.toArray(new String[tl.size()]));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        slpTables = new javax.swing.JScrollPane();
        lstTables = new javax.swing.JList<>(new DefaultListModel());
        slpAttributes = new javax.swing.JScrollPane();
        tblAttributes = new javax.swing.JTable();
        btnDisonnect = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnNewTable = new javax.swing.JButton();

        slpTables.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        slpTables.setMaximumSize(new java.awt.Dimension(192, 384));
        slpTables.setMinimumSize(new java.awt.Dimension(192, 384));
        slpTables.setPreferredSize(new java.awt.Dimension(192, 384));

        lstTables.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lstTables.setMaximumSize(new java.awt.Dimension(160, 384));
        lstTables.setMinimumSize(new java.awt.Dimension(160, 384));
        lstTables.setPreferredSize(new java.awt.Dimension(160, 384));
        lstTables.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstTablesMouseClicked(evt);
            }
        });
        slpTables.setViewportView(lstTables);

        slpAttributes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        slpAttributes.setMaximumSize(new java.awt.Dimension(704, 320));
        slpAttributes.setMinimumSize(new java.awt.Dimension(704, 320));
        slpAttributes.setPreferredSize(new java.awt.Dimension(704, 320));

        tblAttributes.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
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
        tblAttributes.setMaximumSize(new java.awt.Dimension(640, 512));
        tblAttributes.setMinimumSize(new java.awt.Dimension(640, 512));
        tblAttributes.setPreferredSize(new java.awt.Dimension(640, 512));
        slpAttributes.setViewportView(tblAttributes);

        btnDisonnect.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnDisonnect.setText("Se déconnecter");
        btnDisonnect.setMaximumSize(new java.awt.Dimension(112, 32));
        btnDisonnect.setMinimumSize(new java.awt.Dimension(112, 32));
        btnDisonnect.setPreferredSize(new java.awt.Dimension(112, 32));
        btnDisonnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisonnectActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Liste des tables :");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Informations de la table : ");

        btnNewTable.setText("Nouvelle table");
        btnNewTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewTableActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(slpTables, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(195, 195, 195)
                                .addComponent(btnDisonnect, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(123, 123, 123)
                                .addComponent(btnNewTable))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(slpAttributes, javax.swing.GroupLayout.PREFERRED_SIZE, 704, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(slpTables, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(slpAttributes, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDisonnect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNewTable))))
                .addGap(32, 32, 32))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnDisonnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisonnectActionPerformed
        c.close();

        //Vide la liste
        DefaultTableModel tableModel = (DefaultTableModel)tblAttributes.getModel();
        tableModel.setRowCount(0);

        //Switche l'affichage sur le JPanel ConnectionPanel
        MainFrame f = (MainFrame)SwingUtilities.getRoot(this);
        JPanel panel = (JPanel)f.getContentPane();
        CardLayout layout = (CardLayout)panel.getLayout();
        layout.next(panel);
        f.setCurrentCard();
    }//GEN-LAST:event_btnDisonnectActionPerformed

    private void lstTablesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstTablesMouseClicked
        String selected = lstTables.getSelectedValue();
        DefaultTableModel tableModel = (DefaultTableModel)tblAttributes.getModel();
        tableModel.setRowCount(0);

        //Remplissage de la JTable avec les variables de la classe Attribut;
        try {
            c.tableColumns(selected);
            c.getTables().get(selected).attributes().forEach((a) -> {
                tableModel.addRow(a.getAttribute());
            });
        }
        catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_lstTablesMouseClicked

    private void btnNewTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewTableActionPerformed
        final JDialog dialog = new JDialog();
        dialog.setTitle("Nouvelle table");
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        TableCreationPanel content = new TableCreationPanel(c);
        content.getButton().addActionListener((ActionEvent e) -> {
            String name = content.getTableName().getText();
            c.getTables().put(name, new Table(name));
            tablesList();
            dialog.dispose();
        });
        dialog.setContentPane(content);
        dialog.setResizable(false);
        dialog.pack();
        dialog.setVisible(true);
    }//GEN-LAST:event_btnNewTableActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDisonnect;
    private javax.swing.JButton btnNewTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> lstTables;
    private javax.swing.JScrollPane slpAttributes;
    private javax.swing.JScrollPane slpTables;
    private javax.swing.JTable tblAttributes;
    // End of variables declaration//GEN-END:variables
}
