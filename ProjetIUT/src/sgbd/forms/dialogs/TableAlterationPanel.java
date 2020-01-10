/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.forms.dialogs;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import sgbd.controllers.Controller;
import sgbd.database.Table;
import sgbd.database.Attribute;

/**
 *
 * @author Paul
 */
public class TableAlterationPanel extends javax.swing.JPanel {

    private Table table;
    private Controller controller;
    private ArrayList<String> nomsColonnes;
    private ArrayList<ArrayList<Object>> listeAttributs;

    /**
     * Creates new form TableModifPanel
     *
     * @param controller
     * @param table
     */
    public TableAlterationPanel(Controller controller, Table table) {
        initComponents();
        this.controller = controller;
        this.table = table;
        getTableInfo();
        tableModif.getTableHeader().setReorderingAllowed(false);
        labelTableName.setText(table.getName());

        String[] types = controller.getTypesList();
        TableColumn typeColumn = tableModif.getColumnModel().getColumn(1);
        for (String type : types) {
            comboboxTypes.addItem(type);
        }
        typeColumn.setCellEditor(new DefaultCellEditor(comboboxTypes));

        String[] tables = controller.getTablesList();
        for (String laTable : tables) {
            comboboxTables.addItem(laTable);
        }

        String[] colonnes = nomsColonnes.toArray(new String[nomsColonnes.size()]);
        for (String laColonne : colonnes) {
            comboboxColonnes.addItem(laColonne);
        }

    }

    private void getTableInfo() {
        DefaultTableModel tableModel = (DefaultTableModel) tableModif.getModel();
        tableModel.setRowCount(0);
        ArrayList<Attribute> vide = new ArrayList<>();
        table.setAttributes(vide);
        controller.getAttributesList(table);
        nomsColonnes = new ArrayList<>();

        table.attributes().forEach((unAttribute) -> {
            tableModel.addRow(unAttribute.toObject());
            nomsColonnes.add(unAttribute.getName());
        });

        remplirListesAttributs();
        resizeColumnWidth(tableModif);

    }

    public void remplirListesAttributs() {
        listeAttributs = new ArrayList<ArrayList<Object>>();
        for (int row = 0; row < tableModif.getRowCount(); row++) {
            ArrayList<Object> proprietesAttribut = new ArrayList<>();
            for (int column = 0; column < tableModif.getColumnCount(); column++) {
                proprietesAttribut.add(tableModif.getValueAt(row, column));
            }
            listeAttributs.add(proprietesAttribut);
        }
        listeAttributs.forEach(value -> System.out.println(value));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboboxTypes = new javax.swing.JComboBox<>();
        comboboxTables = new javax.swing.JComboBox<>();
        comboboxRef = new javax.swing.JComboBox<>();
        comboboxColonnes = new javax.swing.JComboBox<>();
        comboboxFK = new javax.swing.JComboBox<>();
        btnModif = new javax.swing.JButton();
        btnAnnuler = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labelTableName = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableModif = new javax.swing.JTable();
        buttonAddColonne = new javax.swing.JButton();
        buttonDropColonne = new javax.swing.JButton();
        buttonAddFK = new javax.swing.JButton();
        buttonDropFK = new javax.swing.JButton();

        comboboxTables.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxTablesActionPerformed(evt);
            }
        });

        btnModif.setText("Commencer les modifications");
        btnModif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModifActionPerformed(evt);
            }
        });

        btnAnnuler.setText("Fermer");
        btnAnnuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnnulerActionPerformed(evt);
            }
        });

        jLabel1.setText("Nom de la table : ");

        labelTableName.setText("TABLE_NAME");

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
                true, true, true, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableModif.setEnabled(false);
        jScrollPane2.setViewportView(tableModif);
        if (tableModif.getColumnModel().getColumnCount() > 0) {
            tableModif.getColumnModel().getColumn(2).setMinWidth(80);
            tableModif.getColumnModel().getColumn(2).setPreferredWidth(80);
            tableModif.getColumnModel().getColumn(2).setMaxWidth(80);
            tableModif.getColumnModel().getColumn(3).setMinWidth(80);
            tableModif.getColumnModel().getColumn(3).setPreferredWidth(80);
            tableModif.getColumnModel().getColumn(3).setMaxWidth(80);
            tableModif.getColumnModel().getColumn(4).setMinWidth(80);
            tableModif.getColumnModel().getColumn(4).setPreferredWidth(80);
            tableModif.getColumnModel().getColumn(4).setMaxWidth(80);
            tableModif.getColumnModel().getColumn(5).setMinWidth(80);
            tableModif.getColumnModel().getColumn(5).setPreferredWidth(80);
            tableModif.getColumnModel().getColumn(5).setMaxWidth(80);
        }

        buttonAddColonne.setText("Ajouter une colonne");
        buttonAddColonne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddColonneActionPerformed(evt);
            }
        });

        buttonDropColonne.setText("Supprimer une colonne");
        buttonDropColonne.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDropColonneActionPerformed(evt);
            }
        });

        buttonAddFK.setText("Ajouter une clé étrangère");
        buttonAddFK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddFKActionPerformed(evt);
            }
        });

        buttonDropFK.setText("Supprimer une clé étrangère");
        buttonDropFK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDropFKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelTableName))
                    .addComponent(btnModif, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addComponent(btnAnnuler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDropColonne, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addComponent(buttonAddFK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAddColonne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDropFK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE)
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
                            .addComponent(labelTableName))
                        .addGap(70, 70, 70)
                        .addComponent(buttonAddColonne, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDropColonne, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(buttonAddFK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDropFK, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModif, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAnnuler, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnModifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModifActionPerformed
        //modifTable();
        if (btnModif.getText().equals("Commencer les modifications")) {
            tableModif.setEnabled(true);
            btnModif.setText("Valider les modifications");
            btnAnnuler.setText("Annuler les modifications");

        } else if (btnModif.getText().equals("Valider les modifications")) {

            ArrayList<Boolean> anciennesPK = new ArrayList<>();
            ArrayList<Boolean> nouvellesPK = new ArrayList<>();
            ArrayList<String> colonnesPK = new ArrayList<>();
            Boolean PKexistante = false;

            for (int row = 0; row < tableModif.getRowCount(); row++) {

                //Enregistrement des anciennes données et nouvelles données
                String ancienNomColonne = listeAttributs.get(row).get(0).toString();
                String nouveauNomColonne = tableModif.getValueAt(row, 0).toString().toUpperCase();

                String ancienDatatype = listeAttributs.get(row).get(1).toString();
                String nouveauDatatype = tableModif.getValueAt(row, 1).toString();

                int ancienneLongueur = Integer.parseInt(listeAttributs.get(row).get(2).toString());
                int nouvelleLongueur = Integer.parseInt(tableModif.getValueAt(row, 2).toString());

                anciennesPK.add(Boolean.parseBoolean(listeAttributs.get(row).get(3).toString()));
                nouvellesPK.add(Boolean.parseBoolean(tableModif.getValueAt(row, 3).toString()));

                Boolean ancienNotNull = Boolean.parseBoolean(listeAttributs.get(row).get(4).toString());
                Boolean nouveauNotNull = Boolean.parseBoolean(tableModif.getValueAt(row, 4).toString());

                Boolean ancienUnique = Boolean.parseBoolean(listeAttributs.get(row).get(5).toString());
                Boolean nouveauUnique = Boolean.parseBoolean(tableModif.getValueAt(row, 5).toString());

                //Détermine si la table possedait déjà une clé primaire
                if (Boolean.parseBoolean(listeAttributs.get(row).get(3).toString()) == true) {
                    PKexistante = true;
                }

                //remplissage de la liste des colonnes de la nouvelle clé primaire
                if (Boolean.parseBoolean(tableModif.getValueAt(row, 3).toString()) == true) {
                    colonnesPK.add(nouveauNomColonne);
                }

                //Renommage des colonnes
                if (!nouveauNomColonne.equals(ancienNomColonne)) {
                    controller.renameColonne(table.getName(), ancienNomColonne, nouveauNomColonne);
                }

                //changement du type de données des colonnes
                if (!ancienDatatype.equals(nouveauDatatype) || ancienneLongueur != nouvelleLongueur) {
                    controller.alterDatatypeColonne(table.getName(), nouveauNomColonne, nouveauDatatype, nouvelleLongueur);
                }

                //gestion de la contrainte not null
                if (ancienNotNull != nouveauNotNull) {
                    if (nouveauNotNull == true) {
                        controller.addConstraintNotNull(table.getName(), nouveauNomColonne);
                    } else {
                        controller.dropNotNull(table.getName(), nouveauNomColonne);
                    }
                }

                //gestion de la contrainte unique
                if (ancienUnique != nouveauUnique) {
                    if (nouveauUnique == true) {
                        controller.addConstraintUnique(table.getName(), nouveauNomColonne);
                    } else {
                        controller.dropConstraint(table.getName(), "un_" + nouveauNomColonne);
                    }
                }
            }

            //gestion de la clé primaire
            //cas suppresion de la clé primaire
            if (!(anciennesPK.equals(nouvellesPK)) && colonnesPK.isEmpty() && PKexistante == true) {
                controller.dropPrimaryKey(table.getName());

                //cas changement ou création de clé primaire
            } else {
                if (!(anciennesPK.equals(nouvellesPK)) && !colonnesPK.isEmpty()) {
                    if (PKexistante == true) {
                        controller.dropPrimaryKey(table.getName());
                    }
                    controller.createPrimaryKey(table.getName(), colonnesPK);
                }
            }

            getTableInfo();
            btnModif.setText("Commencer les modifications");
            btnAnnuler.setText("Fermer");
            tableModif.setEnabled(false);
        }

    }//GEN-LAST:event_btnModifActionPerformed

    private void buttonDropColonneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDropColonneActionPerformed
        String[] colonnesCB = nomsColonnes.toArray(new String[nomsColonnes.size()]);
        String droppedColonne;

        droppedColonne = (String) JOptionPane.showInputDialog(null, "Choisissez la colonne à supprimer : ", "Suppression d'une colonne", JOptionPane.QUESTION_MESSAGE, null, colonnesCB, colonnesCB[0]);
        if (droppedColonne != null) {
            int dialogConfirmation = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir supprimer la colonne " + droppedColonne + " ?", "Confirmation", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (dialogConfirmation == JOptionPane.YES_OPTION) {
                controller.dropColonne(table.getName(), droppedColonne);
                getTableInfo();
            }
        }
    }//GEN-LAST:event_buttonDropColonneActionPerformed

    private void buttonAddColonneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddColonneActionPerformed
        JTextField txtboxNomColonne = new JTextField();
        JTextField txtboxLongueur = new JTextField();

        Object[] message = {
            "Nom de la colonne : ", txtboxNomColonne,
            "Type de données : ", comboboxTypes,
            "Longueur : ", txtboxLongueur
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Ajouter une colonne", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            controller.addColonne(table.getName(), txtboxNomColonne.getText(), comboboxTypes.getSelectedItem().toString(), Integer.parseInt(txtboxLongueur.getText()));
            getTableInfo();
        }
    }//GEN-LAST:event_buttonAddColonneActionPerformed

    private void btnAnnulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnnulerActionPerformed
        if (btnAnnuler.getText().equals("Annuler les modifications")) {
            btnModif.setText("Commencer les modifications");
            btnAnnuler.setText("Fermer");
            tableModif.setEnabled(false);
            getTableInfo();
        }

    }//GEN-LAST:event_btnAnnulerActionPerformed

    private void buttonAddFKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddFKActionPerformed
        Object[] message = {
            "Nom de la colonne affectée : ", comboboxColonnes,
            "Table à référencer : ", comboboxTables,
            "Clé primaire / contrainte unique à référencer : ", comboboxRef
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Ajouter une clé étrangère", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            controller.createForeignyKey(table.getName(), comboboxColonnes.getSelectedItem().toString(), comboboxTables.getSelectedItem().toString(), comboboxRef.getSelectedItem().toString());
            getTableInfo();
        }
    }//GEN-LAST:event_buttonAddFKActionPerformed

    private void buttonDropFKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDropFKActionPerformed
        comboboxFK.removeAllItems();
        String[] lesFK = controller.getFKNames(table.getName());
        for (String laFK : lesFK) {
            comboboxFK.addItem(laFK);
        }

        if (lesFK.length == 0) {
            JOptionPane.showMessageDialog(null, "Aucune clé étrangère sur cettre table", "Erreur", JOptionPane.WARNING_MESSAGE);
        } else {
            Object[] message = {
                "Nom de la clé étrangère à supprimer : ", comboboxFK
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Supprimer une clé étrangère", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                controller.dropConstraint(table.getName(), comboboxFK.getSelectedItem().toString());
                getTableInfo();
            }
        }

    }//GEN-LAST:event_buttonDropFKActionPerformed

    private void comboboxTablesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxTablesActionPerformed
        String[] refs = controller.getPKList(comboboxTables.getSelectedItem().toString());
        comboboxRef.removeAllItems();
        for (String laRef : refs) {
            comboboxRef.addItem(laRef);
        }
    }//GEN-LAST:event_comboboxTablesActionPerformed

    public JButton getButton(String s) {
        switch (s) {
            case "annuler":
                return btnAnnuler;
            default:
                return btnModif;
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnnuler;
    private javax.swing.JButton btnModif;
    private javax.swing.JButton buttonAddColonne;
    private javax.swing.JButton buttonAddFK;
    private javax.swing.JButton buttonDropColonne;
    private javax.swing.JButton buttonDropFK;
    private javax.swing.JComboBox<String> comboboxColonnes;
    private javax.swing.JComboBox<String> comboboxFK;
    private javax.swing.JComboBox<String> comboboxRef;
    private javax.swing.JComboBox<String> comboboxTables;
    private javax.swing.JComboBox<String> comboboxTypes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelTableName;
    private javax.swing.JTable tableModif;
    // End of variables declaration//GEN-END:variables
}
