/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.forms.dialogs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sgbd.controllers.Controller;
import sgbd.database.Attribute;
import sgbd.database.Table;

/**
 *
 * @author Alexandre
 */
public class TableDonneesInsertDialog extends javax.swing.JPanel 
{

    /**
     * Creates new form TableDonneesInsertPanel
     * @param laTable
     */
    private Controller controllerInsert;
    private int nbRows = 0;
    private Table laTableInsert;
    private String[][] listeDesValeurs;
    
    public TableDonneesInsertDialog(Table laTable, Controller ctr) 
    {
        initComponents();
        ArrayList<Attribute> lesAttributs = laTable.attributes();
        DefaultTableModel model = (DefaultTableModel) jTableDonneesInsert.getModel();
        for(Attribute attribut : lesAttributs)
        {
            model.addColumn(attribut.getName());
        }
        Object[] rowData = null;
        
        model.addRow(rowData);
        nbRows ++ ;
        
        jTableDonneesInsert.setModel(model);
        
        controllerInsert = ctr;
        
        laTableInsert = laTable;
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
        jTableDonneesInsert = new javax.swing.JTable();
        jButtonConfirmerInsert = new javax.swing.JButton();
        jButtonAjouterLigneInsert = new javax.swing.JButton();
        jButtonEnleverNbLignesSelectionneeInsert = new javax.swing.JButton();
        jButtonEnleverUneLigneInsert = new javax.swing.JButton();

        jTableDonneesInsert.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableDonneesInsert);

        jButtonConfirmerInsert.setText("Confirmer Insert");
        jButtonConfirmerInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConfirmerInsertActionPerformed(evt);
            }
        });

        jButtonAjouterLigneInsert.setText("Ajouter une ligne");
        jButtonAjouterLigneInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAjouterLigneInsertActionPerformed(evt);
            }
        });

        jButtonEnleverNbLignesSelectionneeInsert.setText("Effacer ligne selectionnée");
        jButtonEnleverNbLignesSelectionneeInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnleverNbLignesSelectionneeInsertActionPerformed(evt);
            }
        });

        jButtonEnleverUneLigneInsert.setText("Retirer une ligne");
        jButtonEnleverUneLigneInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEnleverUneLigneInsertActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonAjouterLigneInsert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonEnleverNbLignesSelectionneeInsert, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonConfirmerInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButtonEnleverUneLigneInsert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 782, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jButtonAjouterLigneInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEnleverUneLigneInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68)
                        .addComponent(jButtonEnleverNbLignesSelectionneeInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonConfirmerInsert, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public JButton fermerTableDonneesInsertPanel()
    { 
        return jButtonConfirmerInsert;
    }
    
    public void insert()
    {
        listeDesValeurs = new String[jTableDonneesInsert.getRowCount()][jTableDonneesInsert.getColumnCount()];
        for(int col = 0 ; col < jTableDonneesInsert.getColumnCount() ; col++)
        {
            for(int row = 0 ; row < jTableDonneesInsert.getRowCount() ; row++)
            {
                if(jTableDonneesInsert.getValueAt(row, col) != null)
                {
                    listeDesValeurs[row][col] = jTableDonneesInsert.getValueAt(row, col).toString();
                }
            }
        }
        
        try {
            controllerInsert.addRows(listeDesValeurs, laTableInsert, nbRows);
        } catch (SQLException ex) {
            Logger.getLogger(TableDonneesInsertDialog.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(TableDonneesInsertDialog.this, "Erreur lors de l'insertion de donnéées: " + ex);
        }
    }
    
    private void jButtonConfirmerInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConfirmerInsertActionPerformed
    
        
        
    }//GEN-LAST:event_jButtonConfirmerInsertActionPerformed

    private void jButtonAjouterLigneInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAjouterLigneInsertActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableDonneesInsert.getModel();
            Object[] rowData = null;
            model.addRow(rowData);
            nbRows++;
        
    }//GEN-LAST:event_jButtonAjouterLigneInsertActionPerformed

    private void jButtonEnleverNbLignesSelectionneeInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnleverNbLignesSelectionneeInsertActionPerformed
                                                             
        DefaultTableModel model = (DefaultTableModel) jTableDonneesInsert.getModel();
        int indiceLigne = jTableDonneesInsert.getSelectedRow();
        try
        {
            model.removeRow(indiceLigne);
            nbRows--; 
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(TableDonneesInsertDialog.this, "Erreur lors de la supression de la ligne :" + e);
        }
        
    }//GEN-LAST:event_jButtonEnleverNbLignesSelectionneeInsertActionPerformed

    private void jButtonEnleverUneLigneInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEnleverUneLigneInsertActionPerformed

        DefaultTableModel model = (DefaultTableModel) jTableDonneesInsert.getModel();
        if (nbRows > 0) {
            model.removeRow(nbRows - 1);
            nbRows--;
        }

    }//GEN-LAST:event_jButtonEnleverUneLigneInsertActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAjouterLigneInsert;
    private javax.swing.JButton jButtonConfirmerInsert;
    private javax.swing.JButton jButtonEnleverNbLignesSelectionneeInsert;
    private javax.swing.JButton jButtonEnleverUneLigneInsert;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDonneesInsert;
    // End of variables declaration//GEN-END:variables

    Object getButton(String confirm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
