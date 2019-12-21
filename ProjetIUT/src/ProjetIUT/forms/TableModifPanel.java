/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetIUT.forms;

import ProjetIUT.DatabaseClasses.Table;
import ProjetIUT.classesConnexion.Connexion;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Paul
 */
public class TableModifPanel extends javax.swing.JPanel {
    
    private Connexion connexion;
    private String tableN;
    private Table tSave;
    /**
     * Creates new form TableModifPanel
     */
    public TableModifPanel(Connexion co, String tableNa) {
        initComponents();
        tableN = tableNa;
        connexion = co;   
        getTableInfo();
        
    }
    
    private void getTableInfo(){
        
        DefaultTableModel tableModel = (DefaultTableModel) tableModif.getModel();
        tableModel.setRowCount(0);
        
        try {
            connexion.tableColumns(tableN);
            connexion.getTables().get(tableN).attributes().forEach((unAttribute) -> {
                tableModel.addRow(unAttribute.getAttribute());
            });
            tSave = connexion.getTables().get(tableN);
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
        }
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
        btnSave1 = new javax.swing.JButton();
        btnSave2 = new javax.swing.JButton();
        txtBoxNomTable = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

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
                true, true, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableModif);

        btnSave.setText("Retirer une colonne");

        btnDiscard.setText("Annuler");

        btnSave1.setText("Ajouter une colonne");

        btnSave2.setText("Sauvegarder");

        jLabel1.setText("Nom de la table :");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDiscard, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSave2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBoxNomTable, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 833, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBoxNomTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(60, 60, 60)
                .addComponent(btnSave1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSave2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDiscard, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    public JButton getButton(String s) {
            switch(s) {
                case "annuler":
                    return btnDiscard;
                default:
                    return btnSave;
            }
        }



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDiscard;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSave2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableModif;
    private javax.swing.JTextField txtBoxNomTable;
    // End of variables declaration//GEN-END:variables
}
