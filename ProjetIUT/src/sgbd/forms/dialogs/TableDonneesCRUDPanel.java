/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.forms.dialogs;

import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
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
        labelTableName2.setText(laTable.getName());
        controllerCRUD = ctr;
        tableCRUD = laTable;
        
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
        labelFiltreSELECT = new java.awt.Label();
        jButtonLancerSELECT = new javax.swing.JButton();
        checkboxSelectAll = new java.awt.Checkbox();
        labelSelectFrom = new java.awt.Label();
        labelTableName2 = new java.awt.Label();
        labelSelectFromWhere = new java.awt.Label();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaSelectNomsDesAttributs = new javax.swing.JTextArea();

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

        labelFiltreSELECT.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelFiltreSELECT.setText("Select");

        jButtonLancerSELECT.setText("Lancer Recherche");
        jButtonLancerSELECT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLancerSELECTActionPerformed(evt);
            }
        });

        checkboxSelectAll.setLabel("*  (tous les attributs) OU");

        labelSelectFrom.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelSelectFrom.setText("From");

        labelTableName2.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelTableName2.setText("TABLE_NAME");

        labelSelectFromWhere.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        labelSelectFromWhere.setText("Where");

        jTextAreaSelectNomsDesAttributs.setColumns(20);
        jTextAreaSelectNomsDesAttributs.setRows(5);
        jScrollPane2.setViewportView(jTextAreaSelectNomsDesAttributs);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelNomDeLaTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(labelTableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(labelFiltreSELECT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(checkboxSelectAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jButtonLancerSELECT, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSelectFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTableName2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(labelSelectFromWhere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelNomDeLaTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelFiltreSELECT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(checkboxSelectAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(labelSelectFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelTableName2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelSelectFromWhere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 308, Short.MAX_VALUE)
                        .addComponent(jButtonLancerSELECT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLancerSELECTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLancerSELECTActionPerformed
        ResultSet rs;
        try {
            rs = controllerCRUD.getResultSetFromTable(tableCRUD);
            DefaultTableModel model = new DefaultTableModel();
            int y = 1;
            ArrayList<String> nomsDeColonnes = new ArrayList<>();
           
            //Obligation de recréer les colonnes dans le nouveau model//
            if(checkboxSelectAll.getState() || (!checkboxSelectAll.getState() && jTextAreaSelectNomsDesAttributs.getText().isEmpty()))
            {
                nomsDeColonnes = controllerCRUD.getAttributesNames(tableCRUD);
                for(String nomCol : nomsDeColonnes)
                {
                    model.addColumn(nomCol);
                }

                //Création des lignes de données//
                while(rs.next())
                {
                    String valeurs[] = new String[rs.getMetaData().getColumnCount()];
                    for(y=1 ; y<rs.getMetaData().getColumnCount()+1 ; y++)
                    {
                        valeurs[y-1] = rs.getString(y);

                    }
                    model.addRow(valeurs);
                }
            }
            else if(!checkboxSelectAll.getState())
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
            
            
            jTableDonneesCRUD.setModel(model);
            
            
            
        } catch (Exception ex) {
            Logger.getLogger(TableDonneesCRUDPanel.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Erreur lors du SELECT " + ex);
        }
    }//GEN-LAST:event_jButtonLancerSELECTActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Checkbox checkboxSelectAll;
    private javax.swing.JButton jButtonLancerSELECT;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTableDonneesCRUD;
    private javax.swing.JTextArea jTextAreaSelectNomsDesAttributs;
    private java.awt.Label labelFiltreSELECT;
    private java.awt.Label labelNomDeLaTable;
    private java.awt.Label labelSelectFrom;
    private java.awt.Label labelSelectFromWhere;
    private java.awt.Label labelTableName;
    private java.awt.Label labelTableName2;
    // End of variables declaration//GEN-END:variables
}
