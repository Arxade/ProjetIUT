/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.forms.dialogs;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sgbd.controllers.Controller;
import sgbd.database.Table;

/**
 *
 * @author Paul
 */
public class TableAlterationPanel extends javax.swing.JPanel {
    private Table table;
    private Controller controller;
    
    /**
     * Creates new form TableModifPanel
     * @param controller
     * @param table
     */
    public TableAlterationPanel(Controller controller, Table table) {
        initComponents();
        this.controller = controller;
        this.table = table;
        getTableInfo();
        labelTableName.setText(table.getName());
        
        
    }

    private void getTableInfo() {

        DefaultTableModel tableModel = (DefaultTableModel) tableModif.getModel();
        
        Object[] tab = {"test", "test", 10, true, true, true, "test"};
        tableModel.addRow(tab);
        
        table.attributes().forEach((unAttribute) -> {
            tableModel.addRow(unAttribute.toObject());
            System.out.println(unAttribute.getName());
        });
    }
     

    
    public void modifTable(){

        
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
        tableModif = new javax.swing.JTable();
        btnSave = new javax.swing.JButton();
        btnDiscard = new javax.swing.JButton();
        btnRenameTable = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labelTableName = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        tableModif.setModel(new javax.swing.table.DefaultTableModel(
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
        tableModif.setPreferredSize(new java.awt.Dimension(1000, 0));
        jScrollPane1.setViewportView(tableModif);

        btnSave.setText("Sauvegarder");

        btnDiscard.setText("Annuler");

        btnRenameTable.setText("Changer le nom de la table");
        btnRenameTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRenameTableActionPerformed(evt);
            }
        });

        jLabel1.setText("Nom de la table : ");

        labelTableName.setText("TABLE_NAME");

        jButton1.setText("getinfos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 17, Short.MAX_VALUE)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(labelTableName)
                                .addGap(20, 20, 20))
                            .addComponent(btnRenameTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDiscard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1025, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelTableName))
                .addGap(18, 18, 18)
                .addComponent(btnRenameTable)
                .addGap(102, 102, 102)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnRenameTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRenameTableActionPerformed
        String inputNom;
        inputNom = JOptionPane.showInputDialog(null, "Entre le nouveau nom de la table : ", "Renommage de la table", JOptionPane.QUESTION_MESSAGE);
        if(controller.renameTable(table.getName(), inputNom.toUpperCase()) == true)
        {

        }

    }//GEN-LAST:event_btnRenameTableActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        getTableInfo();
    }//GEN-LAST:event_jButton1ActionPerformed

    public JButton getButton(String s) {
            switch(s) {
                case "annuler":
                    return btnDiscard;
                case "rename":
                    return btnRenameTable;
                default:
                    return btnSave;
            }
        }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDiscard;
    private javax.swing.JButton btnRenameTable;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTableName;
    private javax.swing.JTable tableModif;
    // End of variables declaration//GEN-END:variables
}
