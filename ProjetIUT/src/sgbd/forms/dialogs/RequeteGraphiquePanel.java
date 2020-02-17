package sgbd.forms.dialogs;

import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import sgbd.controllers.Controller;



public class RequeteGraphiquePanel extends javax.swing.JPanel {
    
    private Controller controller;
    private ArrayList<ArrayList<Object>> lesLignes;
    private String nomTableSelectionnee;

    /**
     * Creates new form TableRequeteGraphique
     * @param controller
     */
    public RequeteGraphiquePanel(Controller controller) {
        initComponents();
        this.controller = controller;
        
        String[] tables = controller.getTablesList();
        for (String table : tables) {
            comboBoxTables.addItem(table);
        }
        comboBoxTables.setSelectedIndex(0);
        nomTableSelectionnee = comboBoxTables.getSelectedItem().toString();
        
        TableColumn attributColumn = tableRequete.getColumnModel().getColumn(0);
        attributColumn.setCellEditor(new DefaultCellEditor(comboBoxAttributs));
        
        TableColumn fonctionColumn = tableRequete.getColumnModel().getColumn(2);
        TableColumn fonctionHavingColumn = tableRequete.getColumnModel().getColumn(5);
        fonctionColumn.setCellEditor(new DefaultCellEditor(comboBoxFonctions));
        fonctionHavingColumn.setCellEditor(new DefaultCellEditor(comboBoxFonctions));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboBoxAttributs = new javax.swing.JComboBox<>();
        comboBoxFonctions = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableRequete = new javax.swing.JTable();
        btnExecuterRequete = new javax.swing.JButton();
        btnRemoveLigne = new javax.swing.JButton();
        btnAddLigne = new javax.swing.JButton();
        comboBoxTables = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        comboBoxFonctions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Aucune", "Somme", "Moyenne", "Comptage", "Maximum", "Minimum" }));

        tableRequete.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Attribut", "Afficher données attribut", "Fonction d'ensemble", "Condition attribut", "Regrouper", "Fonction condition groupe", "Condition groupement"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tableRequete);

        btnExecuterRequete.setText("Exécuter la requête");
        btnExecuterRequete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecuterRequeteActionPerformed(evt);
            }
        });

        btnRemoveLigne.setText("Retirer une ligne");
        btnRemoveLigne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveLigneActionPerformed(evt);
            }
        });

        btnAddLigne.setText("Ajouter une ligne");
        btnAddLigne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddLigneActionPerformed(evt);
            }
        });

        comboBoxTables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTablesActionPerformed(evt);
            }
        });

        jLabel1.setText("Table : ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnExecuterRequete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboBoxTables, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnRemoveLigne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnAddLigne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(comboBoxTables, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnAddLigne, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnRemoveLigne, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnExecuterRequete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnExecuterRequeteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecuterRequeteActionPerformed
        try {
            remplirListeLignes();
            final JDialog dialog = new JDialog();
            dialog.setTitle("Résultat");
            dialog.setModal(true);
            dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            ResultatRequetePanel content = new ResultatRequetePanel(controller, controller.traduireRequeteGraphiqueEnSql(lesLignes, comboBoxTables.getSelectedItem().toString()));
            dialog.setContentPane(content);
            dialog.setResizable(true);
            dialog.pack();
            dialog.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erreur " + ex);
        }
    }//GEN-LAST:event_btnExecuterRequeteActionPerformed

    public void remplirListeLignes(){
        lesLignes = new ArrayList<>();
        for (int row = 0; row < tableRequete.getRowCount(); row++) {
            ArrayList<Object> laLigne = new ArrayList<>();
            for (int column = 0; column < tableRequete.getColumnCount(); column++) {
                laLigne.add(tableRequete.getValueAt(row, column));
            }
            lesLignes.add(laLigne);
        }
        lesLignes.forEach(value -> System.out.println(value));
    }

    private void btnAddLigneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddLigneActionPerformed
        DefaultTableModel model = (DefaultTableModel) tableRequete.getModel();
        Object[] row = null;
        model.addRow(row);
        tableRequete.setValueAt(true, model.getRowCount() - 1, 1);
        tableRequete.setValueAt(false, model.getRowCount() - 1, 4);
        tableRequete.setValueAt("", model.getRowCount() - 1, 3);
        tableRequete.setValueAt("Aucune", model.getRowCount() - 1, 2);
        tableRequete.setValueAt("Aucune", model.getRowCount() - 1, 5);
        tableRequete.setValueAt("", model.getRowCount() - 1, 6);
    }//GEN-LAST:event_btnAddLigneActionPerformed

    private void btnRemoveLigneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveLigneActionPerformed
        DefaultTableModel model = (DefaultTableModel) tableRequete.getModel();
        int nbRow = model.getRowCount();
        if (nbRow > 0)
            model.removeRow(nbRow - 1);
    }//GEN-LAST:event_btnRemoveLigneActionPerformed

    private void comboBoxTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTablesActionPerformed
        if (!comboBoxTables.getSelectedItem().toString().equals(nomTableSelectionnee)) {
            if (comboBoxAttributs.getItemCount() > 0) {
                comboBoxAttributs.removeAllItems();
            }
            String[] attributs = controller.getNomsAttributsFromNomTable(comboBoxTables.getSelectedItem().toString());
            for (String attribut : attributs) {
                comboBoxAttributs.addItem(attribut);
            }
            DefaultTableModel model = (DefaultTableModel) tableRequete.getModel();
            int nbRow = model.getRowCount();
            if (nbRow > 0)
                model.setRowCount(0);
            nomTableSelectionnee = comboBoxTables.getSelectedItem().toString();
            
        }
        
        
    }//GEN-LAST:event_comboBoxTablesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddLigne;
    private javax.swing.JButton btnExecuterRequete;
    private javax.swing.JButton btnRemoveLigne;
    private javax.swing.JComboBox<String> comboBoxAttributs;
    private javax.swing.JComboBox<String> comboBoxFonctions;
    private javax.swing.JComboBox<String> comboBoxTables;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableRequete;
    // End of variables declaration//GEN-END:variables
}
