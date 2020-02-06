/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.forms.dialogs;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import sgbd.controllers.Controller;
import sgbd.database.Table;

/**
 *
 * @author Alexandre
 */
public class TableDonneesCRUDPanel extends javax.swing.JPanel {

    /**
     * Creates new form TableDonneesCRUDPanel
     * @param ctr
     * @param laTable
     */
    public Controller controllerCRUD;
    public Table tableCRUD;
    private Object[][] valeursDeRechercheDeBase;
    
    public TableDonneesCRUDPanel(Controller ctr,Table laTable) {
        initComponents();
        TableColumn col;
        int longueurArrayListDeLaTable = laTable.attributes().size();
        for(int i=0 ; i<longueurArrayListDeLaTable ; i++)
        {
            col = new TableColumn(i);
            col.setHeaderValue(laTable.attributes().get(i).getName());
            jTableDonneesCRUD.addColumn(col);
        }
        labelTableName.setText(laTable.getName());
        controllerCRUD = ctr;
        tableCRUD = laTable;
        lancerRecherche();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelNomDeLaTable = new java.awt.Label();
        labelTableName = new java.awt.Label();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDonneesCRUD = new javax.swing.JTable();
        jButtonEffacerLigneCRUD = new javax.swing.JButton();
        jButtonModifierLigneCRUD = new javax.swing.JButton();
        jButtonInsertCRUD = new javax.swing.JButton();

        labelNomDeLaTable.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelNomDeLaTable.setText("Nom de la table:");

        labelTableName.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelTableName.setText("TABLE_NAME");

        jTableDonneesCRUD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTableDonneesCRUD);

        jButtonEffacerLigneCRUD.setText("Effacer ligne");
        jButtonEffacerLigneCRUD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEffacerLigneCRUDActionPerformed(evt);
            }
        });

        jButtonModifierLigneCRUD.setText("Modifier ligne");
        jButtonModifierLigneCRUD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModifierLigneCRUDActionPerformed(evt);
            }
        });

        jButtonInsertCRUD.setText("Ajouter ligne");
        jButtonInsertCRUD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInsertCRUDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelNomDeLaTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelTableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 750, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonEffacerLigneCRUD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonModifierLigneCRUD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonInsertCRUD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButtonEffacerLigneCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonModifierLigneCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonInsertCRUD, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelTableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNomDeLaTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lancerRecherche()
    {
        ResultSet rs;
        try {
            rs = controllerCRUD.getResultSetFromTable(tableCRUD);
            DefaultTableModel model = new DefaultTableModel();
            int y = 1;
            ArrayList<String> nomsDeColonnes = new ArrayList<>();
           
            //Obligation de recréer les colonnes dans le nouveau model//
            /*if(checkboxSelectAll.getState() || (!checkboxSelectAll.getState() && jTextAreaSelectNomsDesAttributs.getText().isEmpty()))
            {*/
                nomsDeColonnes = controllerCRUD.getAttributesNames(tableCRUD);
                for(String nomCol : nomsDeColonnes)
                {
                    model.addColumn(nomCol);
                }

                DateFormat df = new SimpleDateFormat("YYYY-MM-dd");
                //Création des lignes de données//
                while(rs.next())
                {
                    String valeurs[] = new String[rs.getMetaData().getColumnCount()];
                    for(y=1 ; y<rs.getMetaData().getColumnCount()+1 ; y++)
                    {
                        valeurs[y-1] = rs.getString(y);
                        if(tableCRUD.attributes().get(y-1).getType().equals("DATE") )
                        {
                            valeurs[y-1] = df.format(rs.getDate(y));
                        }
                        
                    }
                    model.addRow(valeurs);
                }
            //}
            /*else if(!checkboxSelectAll.getState())
            {
            String[] lesAttributs = controllerCRUD.getAttributesFromJTextArea(jTextAreaSelectNomsDesAttributs.getText());
            for(int i = 0 ; i < lesAttributs.length ; i++)
            {
            nomsDeColonnes.add(lesAttributs[i]);
            }
            for(String nomCol : nomsDeColonnes)
            {
            model.addColumn(nomCol);
            }
            while(rs.next())
            {
            String valeurs[] = new String[nomsDeColonnes.size()];
            for(y=1 ; y<rs.getMetaData().getColumnCount()+1 ; y++)
            {
            for(int x = 0 ; x < nomsDeColonnes.size() ; x++)
            {
            if(nomsDeColonnes.get(x).equals(rs.getMetaData().getColumnName(y)))
            {
            valeurs[x] = rs.getString(y);
            }
            }
            }
            model.addRow(valeurs);
            }
            System.out.println(nomsDeColonnes.toString());
            }
            */
            
            jTableDonneesCRUD.setModel(model);
            Object[][] nouvelleValeurs = new Object[jTableDonneesCRUD.getColumnCount()][jTableDonneesCRUD.getRowCount()];
            for(int i = 0 ; i < jTableDonneesCRUD.getColumnCount() ; i++)
            {
                for(int z = 0 ; z < jTableDonneesCRUD.getRowCount() ; z++)
                {
                    nouvelleValeurs[i][z] = jTableDonneesCRUD.getValueAt(z, i);
                }
            }
            valeursDeRechercheDeBase = nouvelleValeurs;
            
        } catch (Exception ex) {
            Logger.getLogger(TableDonneesCRUDPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur lors du SELECT " + ex);
        }
    }
    private void jButtonEffacerLigneCRUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEffacerLigneCRUDActionPerformed
        int id = jTableDonneesCRUD.getSelectedRow();
        if(id > -1)
        {
            try 
            {
                ArrayList<String> lesValeurs = new ArrayList<>();
                ArrayList<String> lesAttributs = new ArrayList<>();
                for(int col = 0 ; col < jTableDonneesCRUD.getModel().getColumnCount() ; col ++)
                {
                    lesAttributs.add(jTableDonneesCRUD.getColumnName(col));
                    if(jTableDonneesCRUD.getModel().getValueAt(id, col) != null)
                    {
                        lesValeurs.add(jTableDonneesCRUD.getModel().getValueAt(id, col).toString());
                    }
                    else
                    {
                        lesValeurs.add("null"); System.out.println("LesValeur.get(" + col + ") = " + lesValeurs.get(col));
                    }
                }
                controllerCRUD.deleteRow(tableCRUD, lesAttributs, lesValeurs, this);
                lancerRecherche();
                
                
            }
            catch (SQLException e) 
            {
                JOptionPane.showMessageDialog(TableDonneesCRUDPanel.this, "Erreur lors de la suppression " + e);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(TableDonneesCRUDPanel.this, "Veuillez selectionner une ligne.");
        }
        
    }//GEN-LAST:event_jButtonEffacerLigneCRUDActionPerformed

    private void jButtonModifierLigneCRUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModifierLigneCRUDActionPerformed
        
        try 
        {
            controllerCRUD.updateRows(valeursDeRechercheDeBase, jTableDonneesCRUD.getModel(), tableCRUD);
            lancerRecherche();
        } catch (SQLException ex) {
            Logger.getLogger(TableDonneesCRUDPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(TableDonneesCRUDPanel.this, "Erreur lors de la modification: " + ex);
        }
        
        
    }//GEN-LAST:event_jButtonModifierLigneCRUDActionPerformed

    private void jButtonInsertCRUDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInsertCRUDActionPerformed
        
        final JDialog dialog = new JDialog();
        dialog.setTitle("Insertion de données dans la table " + tableCRUD.getName());
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        TableDonneesInsertPanel content = new TableDonneesInsertPanel(tableCRUD , controllerCRUD);
        content.fermerTableDonneesInsertPanel().addActionListener((ActionEvent e) -> {
            content.insert();
            dialog.dispose();
            lancerRecherche();
        });
        dialog.setContentPane(content);
        dialog.setResizable(true);
        dialog.pack();
        dialog.setVisible(true);
    }//GEN-LAST:event_jButtonInsertCRUDActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEffacerLigneCRUD;
    private javax.swing.JButton jButtonInsertCRUD;
    private javax.swing.JButton jButtonModifierLigneCRUD;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDonneesCRUD;
    private java.awt.Label labelNomDeLaTable;
    private java.awt.Label labelTableName;
    // End of variables declaration//GEN-END:variables
}
