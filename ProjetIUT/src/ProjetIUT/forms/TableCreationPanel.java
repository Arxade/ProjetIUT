/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetIUT.forms;

import ProjetIUT.classesConnexion.Connexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Kazed
 */
public class TableCreationPanel extends javax.swing.JPanel {
    private Connexion c;
    /**
     * Creates new form TableCreationPanel
     * @param c
     */
    public TableCreationPanel(Connexion c) {
        initComponents();
        this.c = c;
    }
    
    public JTextField getTableName() {
        return txtTableName;
    }
    
    
    public JButton getButton() {
        return btnCreateTable;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTableName = new javax.swing.JLabel();
        txtTableName = new javax.swing.JTextField();
        lblColumnNumber = new javax.swing.JLabel();
        txtColumnNumber = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableCreation = new javax.swing.JTable();
        btnApply = new javax.swing.JButton();
        btnCreateTable = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        lblTableName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblTableName.setText(" Nom");

        txtTableName.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtTableName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTableName.setMaximumSize(new java.awt.Dimension(128, 20));
        txtTableName.setMinimumSize(new java.awt.Dimension(128, 20));
        txtTableName.setPreferredSize(new java.awt.Dimension(128, 20));

        lblColumnNumber.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        lblColumnNumber.setText("Colonnes");

        txtColumnNumber.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        txtColumnNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtColumnNumber.setText("1");
        txtColumnNumber.setMaximumSize(new java.awt.Dimension(32, 20));
        txtColumnNumber.setMinimumSize(new java.awt.Dimension(32, 20));
        txtColumnNumber.setPreferredSize(new java.awt.Dimension(32, 20));

        tableCreation.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        tableCreation.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nom", "Type", "Clef primaire", "NOT NULL", "UNIQUE", "Clef étrangère" , "Default"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tableCreation);

        btnApply.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnApply.setText("Appliquer");
        btnApply.setMaximumSize(new java.awt.Dimension(96, 32));
        btnApply.setMinimumSize(new java.awt.Dimension(96, 32));
        btnApply.setPreferredSize(new java.awt.Dimension(96, 32));
        btnApply.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnApplyActionPerformed(evt);
            }
        });

        btnCreateTable.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        btnCreateTable.setText("Créer Table");
        btnCreateTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateTableActionPerformed(evt);
            }
        });

        jButton1.setText("Retirer une colonne");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Ajouter une colonne");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCreateTable, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblColumnNumber)
                            .addComponent(lblTableName))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTableName, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtColumnNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(16, 16, 16)
                                .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 690, Short.MAX_VALUE)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTableName)
                            .addComponent(txtTableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblColumnNumber)
                            .addComponent(txtColumnNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnApply, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCreateTable, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(52, 52, 52))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnApplyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnApplyActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        //on recup le modele du Jtable
        DefaultTableModel model = (DefaultTableModel) tableCreation.getModel();
        model.setRowCount(0);
        Object[] row = null;
        //model.setValueAt(evt, ERROR, NORMAL);

        int nbCol = Integer.parseInt(txtColumnNumber.getText());
        for(int i = 0 ; i < nbCol ; i++)
        {
            model.addRow(row);
        }
    }//GEN-LAST:event_btnApplyActionPerformed

    private void btnCreateTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateTableActionPerformed
        TableModel comp = tableCreation.getModel();
        boolean primaire = false;
        String pk = "";
        String fk = "";
        String notNull = "" ;
        String unique = "";
        String type = "";
        String req = "CREATE TABLE " + txtTableName.getText() + " (";
        for(int i = 0; i < comp.getRowCount(); i++) {
            String nomAttribut = (String)comp.getValueAt(i, 0);
            if(i > 0) {
                req += ", " + nomAttribut;
            }
            else {
                req += nomAttribut;
            }
            for(int y = 1; y < tableCreation.getColumnCount()+1; y++) {
                switch(comp.getColumnName(y)) {
                    case "Type" :
                    type = (String)comp.getValueAt(i, y);
                    break;
                    case "Clef primaire" :
                    if(comp.getValueAt(i, y)!= null) {
                        primaire = (boolean)comp.getValueAt(i, y);
                    }
                    if(primaire) {
                        if(pk.length() == 0) {
                            pk = nomAttribut;
                        }
                        else {
                            pk += ", " + nomAttribut;
                        }
                    }
                    break;
                    case "NOT NULL" :
                    System.out.println( comp.getValueAt(i, y));
                    if(comp.getValueAt(i, y) != null) {
                        notNull += ", CONSTRAINT nn_" + nomAttribut.toLowerCase() + " CHECK(" + nomAttribut + " IS NOT NULL)";
                    }
                    break;
                    case "Clef étrangère" :
                    String laFk = "";
                    if(comp.getValueAt(i, y) != null) {
                        laFk = (String)comp.getValueAt(i, y);
                    }
                    if(laFk.length()>1) {
                        fk = ", CONSTRAINT fk_" + nomAttribut + " FOREIGN KEY (" + nomAttribut.toLowerCase() + ") REFERENCES " + (String)comp.getValueAt(i, y);
                    }
                    break;
                    case "unique" :
                        if(comp.getValueAt(i, y) != null) {
                        unique = ", CONSTRAINT un_" + nomAttribut + " UNIQUE (" + nomAttribut.toLowerCase() + ") ";
                    }

                    break;
                    case "Default" :
                        if(comp.getValueAt(i, y) != null) {
                            type += " DEFAULT " + (String) comp.getValueAt(i, y);
                        }
                    break;
                }
            }
            req = req + " " + type;
            primaire = false;
        }
        req = req + ", CONSTRAINT pk_" + txtTableName.getText().toLowerCase() + " PRIMARY KEY (" + pk + ") " + fk + " " + notNull + " " + unique;
        req = req + ")";
        System.out.println(req);
        try {
            c.query(req);
        }
        catch (SQLException ex) {
            Logger.getLogger(ConnectionPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur à la création de la table \n"+ ex);
        }
    }//GEN-LAST:event_btnCreateTableActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tableCreation.getModel();
        Object[] row = null;
        model.addRow(row);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        DefaultTableModel model = (DefaultTableModel) tableCreation.getModel();
        int nbRow = model.getRowCount();
        model.removeRow(nbRow - 1);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnApply;
    private javax.swing.JButton btnCreateTable;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblColumnNumber;
    private javax.swing.JLabel lblTableName;
    private javax.swing.JTable tableCreation;
    private javax.swing.JTextField txtColumnNumber;
    private javax.swing.JTextField txtTableName;
    // End of variables declaration//GEN-END:variables
}
