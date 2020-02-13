/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.forms.dialogs;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import sgbd.controllers.Controller;
import sgbd.controllers.DatabaseObjectTextDocument;

/**
 *
 * @author Kazed
 */
public class TableCreationPanel extends javax.swing.JPanel {
    
    private Controller controller;
    private int columns = 0;  
    

    /**
     * Creates new form TableCreationPanel
     *
     * @param c
     */
    
    
    public TableCreationPanel(Controller c, int nbCol) {
        initComponents();
        controller = c;
        columns = nbCol;     
        
        //on rempli la combolist ddes types
        String[] types = controller.getTypesList();
        TableColumn typeColumn = tblAttributes.getColumnModel().getColumn(1);
        for(String type : types) {
            cbxTypes.addItem(type);
        }
        typeColumn.setCellEditor(new DefaultCellEditor(cbxTypes));


        DefaultTableModel model = (DefaultTableModel) tblAttributes.getModel();
        model.setRowCount(0);
        Object[] row = null;

        for (int i = 0; i < columns; i++) {
            model.addRow(row);
        }
        
        //on récupère la liste des table et on rempli la combobox (pour ajout FK)
        String[] lstTables = controller.getTablesList();
        for(String table : lstTables){
            ckbxTableList.addItem(table);
        }
    }

    public JTextField getTableName() {
        return txtTableName;
    }

    public JButton getButton(String s) {
        switch(s) {
            case "confirm":
                return btnCreateTable;
            case "cancel":
                return btnCancel;
            default:
                return null;
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

        cbxTypes = new javax.swing.JComboBox<>();
        ckbxTableList = new javax.swing.JComboBox<>();
        ckbxColumnsList = new javax.swing.JComboBox<>();
        ckbxNewColumns = new javax.swing.JComboBox<>();
        lblTableName = new javax.swing.JLabel();
        txtTableName = new javax.swing.JTextField();
        scroll = new javax.swing.JScrollPane();
        tblAttributes = new javax.swing.JTable();
        btnCreateTable = new javax.swing.JButton();
        btnRemoveRow = new javax.swing.JButton();
        btnAddRow = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnAddFK = new javax.swing.JButton();
        btnRmFK = new javax.swing.JButton();

        cbxTypes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTypesActionPerformed(evt);
            }
        });

        ckbxTableList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));
        ckbxTableList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ckbxTableListActionPerformed(evt);
            }
        });

        ckbxColumnsList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        ckbxNewColumns.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {}));

        lblTableName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblTableName.setText("Nom de la table :");

        txtTableName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTableName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTableName.setMaximumSize(new java.awt.Dimension(128, 20));
        txtTableName.setMinimumSize(new java.awt.Dimension(128, 20));
        txtTableName.setPreferredSize(new java.awt.Dimension(128, 20));
        txtTableName.setDocument(new DatabaseObjectTextDocument());

        tblAttributes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Type", "Longueur", "Clé primaire", "Not null", "Unique", "Clé étrangère", "Table FK", "Référence FK"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAttributes.getModel().addTableModelListener(
            new TableModelListener() 
            {
                public void tableChanged(TableModelEvent e) 
                {
                    //  int row = e.getFirstRow();
                    // int column = e.getColumn();
                    //   TableModel model = (TableModel)e.getSource();
                    //    String columnName = model.getColumnName(column);
                    //   Object data = model.getValueAt(row, column);

                }
            });
            scroll.setViewportView(tblAttributes);

            btnCreateTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
            btnCreateTable.setText("Créer Table");
            btnCreateTable.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnCreateTableActionPerformed(evt);
                }
            });

            btnRemoveRow.setText("Retirer une colonne");
            btnRemoveRow.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnRemoveRowActionPerformed(evt);
                }
            });

            btnAddRow.setText("Ajouter une colonne");
            btnAddRow.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAddRowActionPerformed(evt);
                }
            });

            btnCancel.setText("Annuler");

            btnAddFK.setText("Ajouter une clé étrangère");
            btnAddFK.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnAddFKActionPerformed(evt);
                }
            });

            btnRmFK.setText("Retirer une clé étrangère");
            btnRmFK.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btnRmFKActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
            this.setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnAddRow, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(btnRemoveRow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCreateTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(lblTableName)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTableName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnAddFK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnRmFK, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(18, 18, 18)
                    .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblTableName))
                    .addGap(18, 18, 18)
                    .addComponent(btnAddRow)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnRemoveRow)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddFK)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnRmFK)
                    .addGap(18, 18, 18)
                    .addComponent(btnCreateTable, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(34, 34, 34))
                .addComponent(scroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
            );
        }// </editor-fold>//GEN-END:initComponents

    private void btnCreateTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateTableActionPerformed
        
        String tableName = txtTableName.getText().toUpperCase();
        TableModel tableModel = tblAttributes.getModel();
        
        try {
            controller.tryCreateTable(tableName, tableModel);
            JOptionPane.showMessageDialog(this, "Table Créée");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur lors de la création de la table: " + e.getMessage());
        }
    }//GEN-LAST:event_btnCreateTableActionPerformed

    private void btnRemoveRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveRowActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblAttributes.getModel();
        int nbRow = model.getRowCount();
        if(nbRow > 0)
        model.removeRow(nbRow - 1);
    }//GEN-LAST:event_btnRemoveRowActionPerformed

    private void btnAddRowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddRowActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblAttributes.getModel();
        Object[] row = null;
        model.addRow(row);
    }//GEN-LAST:event_btnAddRowActionPerformed

    private void btnAddFKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddFKActionPerformed
        TableModel model = tblAttributes.getModel();
        
        remplirCkbxNewColumns(false, model);
        
        if(ckbxNewColumns.getItemCount() != 0){
            Object[] message = {
                "Nom de la colonne affectée : ", ckbxNewColumns,
                "Table à référencer : ", ckbxTableList,
                "Clé primaire / contrainte unique à référencer : ", ckbxColumnsList
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Ajouter une clé étrangère", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                boolean trouve = false;
                int k = 0;
                while(!trouve){
                    if(model.getValueAt(k, 0).equals(ckbxNewColumns.getSelectedItem())){
                        model.setValueAt(true, k, 6);
                        model.setValueAt(ckbxTableList.getSelectedItem(), k, 7);
                        model.setValueAt(ckbxColumnsList.getSelectedItem(), k, 8);
                        trouve = true;
                    }
                    k++;
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(this, "Veuillez d'abord nommer vos colonnes.");
        }
    }//GEN-LAST:event_btnAddFKActionPerformed

    private void btnRmFKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRmFKActionPerformed
        TableModel model = tblAttributes.getModel();
        remplirCkbxNewColumns(true, model);
        if(ckbxNewColumns.getItemCount() != 0){
            Object[] message = {"Nom de la colonne affectée: ", ckbxNewColumns};
            int option = JOptionPane.showConfirmDialog(null, message, "Supprimer une clé étrangère", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                boolean trouve = false;
                int k = 0;
                while(!trouve){
                    if(model.getValueAt(k, 0).equals(ckbxNewColumns.getSelectedItem())){
                        model.setValueAt(false, k, 6);
                        model.setValueAt(null, k, 7);
                        model.setValueAt(null, k, 8);
                        trouve = true;
                    }
                }
            }                
        }
        else{
            JOptionPane.showMessageDialog(this, "Aucune clé étrangère existante.");
        }
    }//GEN-LAST:event_btnRmFKActionPerformed

    
    private void remplirCkbxNewColumns(boolean delete, TableModel model){
        ckbxNewColumns.removeAllItems(); 
        int nbRows = model.getRowCount();
        if(delete){
            for(int i = 0; i < nbRows; i++){
                if(model.getValueAt(i, 6) != null && (boolean) model.getValueAt(i, 6))
                    ckbxNewColumns.addItem((String) model.getValueAt(i, 0));
            }        
        }
        else{             
            for(int i = 0; i < nbRows; i++){
                if(model.getValueAt(i, 0) != null)
                    ckbxNewColumns.addItem((String) model.getValueAt(i, 0));
            }
        }
    }

    private void ckbxTableListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ckbxTableListActionPerformed
        // TODO add your handling code here:
        String[] refs = controller.getPKList(ckbxTableList.getSelectedItem().toString());
        ckbxColumnsList.removeAllItems();
        for (String laRef : refs) {
            ckbxColumnsList.addItem(laRef);
        }        
    }//GEN-LAST:event_ckbxTableListActionPerformed

    private void cbxTypesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTypesActionPerformed
        // TODO add your handling code here:
        TableModel model = tblAttributes.getModel();
        if(cbxTypes.getSelectedItem().equals("DATE")) model.setValueAt(38, tblAttributes.getSelectedRow(), 1);           
        else if(cbxTypes.getSelectedItem().equals("NUMBER")) model.setValueAt(7, tblAttributes.getSelectedRow(), 1);
    }//GEN-LAST:event_cbxTypesActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddFK;
    private javax.swing.JButton btnAddRow;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnCreateTable;
    private javax.swing.JButton btnRemoveRow;
    private javax.swing.JButton btnRmFK;
    private javax.swing.JComboBox<String> cbxTypes;
    private javax.swing.JComboBox<String> ckbxColumnsList;
    private javax.swing.JComboBox<String> ckbxNewColumns;
    private javax.swing.JComboBox<String> ckbxTableList;
    private javax.swing.JLabel lblTableName;
    private javax.swing.JScrollPane scroll;
    private javax.swing.JTable tblAttributes;
    private javax.swing.JTextField txtTableName;
    // End of variables declaration//GEN-END:variables
}
