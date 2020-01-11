/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.controllers;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import sgbd.connection.DatabaseConnection;
import sgbd.database.Attribute;
import sgbd.database.Table;
import sgbd.json.ConnectionDataJSON;

/**
 *
 * @author Kazed
 */
public class Controller {
    private DatabaseConnection connection;
    
    public Controller(DatabaseConnection co) {
        connection = co;
    }
    
    public String getUserName() {
        return connection.getUserName();
    }
    
    public String getDatabaseName() {
        return connection.getDatabaseName();
    }
    
    public boolean tryConnect(ConnectionDataJSON json, String user, String password) {
        if(json.isParametered()) {
            boolean connected = connection.connect(user, password);
            if(connected) {
                connection.prepareConnection();
            }
            return connected;
        }
        else return false;  
    }
    
    public String[] getTablesList() {
         if(connection.setTablesList()) return connection.getTablesList();
         else return null;
    }
    
    public boolean getAttributesList(Table t) {
        return connection.setTableColumns(t);
    }
    
    public String[] getTypesList() {
        if(connection.setTypesList()) return connection.getTypesList();
        else return null;
    }
    
    public void close() {
        connection.close();
    }

    
    public void tryCreateTable(String nomTable, TableModel model) throws SQLException{
        ArrayList<Attribute> lstAt = new ArrayList<>();       
        for(int i = 0; i < model.getRowCount(); i++){           
            Attribute at = new Attribute((String) model.getValueAt(i, 0), (String) model.getValueAt(i, 1));   
            //lenght
            if(model.getValueAt(i, 2) != null && (int) model.getValueAt(i, 2) > 0) at.setLength((int) model.getValueAt(i, 2)); 
            //primarykey
            if(model.getValueAt(i,3) != null && (boolean) model.getValueAt(i, 3)) at.isPrimaryKeyJustBool(true);    
            //not null
            if(model.getValueAt(i,4) != null && (boolean) model.getValueAt(i, 4)) at.isNullable(false);
            //unique
            if(model.getValueAt(i, 5) != null && (boolean) model.getValueAt(i,5)) at.isUnique(true);  
            //foreign key
            if(model.getValueAt(i, 6) != null && (boolean) model.getValueAt(i, 6)) {               
                if(model.getValueAt(i, 7) != null && model.getValueAt(i, 8) != null)
                    at.foreignKey((String) model.getValueAt(i, 7), (String) model.getValueAt(i, 8));
            }      
            lstAt.add(at);
        }   

        connection.createTable(nomTable, lstAt);
    }
    
    
    
    public boolean dropTable(String table, boolean cascadeConstraints) {
        return connection.dropTable(table, cascadeConstraints);
    }

    public boolean dropColonne(String nomTable, String nomColonne) {
        return connection.dropColonne(nomTable, nomColonne);
    }

    public boolean addColonne(String nomTable, String nomColonne, String typeColonne, int longueurColonne) {
        return connection.addColonne(nomTable, nomColonne, typeColonne, longueurColonne);
    }

    public boolean renameColonne(String nomTable, String nomColonneActuel, String nomColonneNew) {
        return connection.renameColonne(nomTable, nomColonneActuel, nomColonneNew);
    }

    public boolean alterDatatypeColonne(String nomTable, String nomColonne, String typeColonne, int longueurColonne) {
        return connection.alterDatatypeColonne(nomTable, nomColonne, typeColonne, longueurColonne);
    }

    public boolean dropPrimaryKey(String nomTable) {
        return connection.dropPrimaryKey(nomTable);
    }
    
    public boolean createPrimaryKey(String nomTable, ArrayList<String> nomColonnesPK){
        return connection.createPrimaryKey(nomTable, nomColonnesPK);
    }

    public boolean createForeignyKey(String nomTable, String nomColonne, String nomTableRef, String nomColonneRef) {
        return connection.createForeignKey(nomTable, nomColonne, nomTableRef, nomColonneRef);
    }

    public boolean dropConstraint(String nomTable, String nomConstraint) {
        return connection.dropConstraint(nomTable, nomConstraint);
    }
    
    public boolean addConstraintUnique(String nomTable, String nomColonne) {
        return connection.addConstraintUnique(nomTable, nomColonne);
    }
    
     public boolean addConstraintNotNull(String nomTable, String nomColonne) {
         return connection.addConstraintNotNull(nomTable, nomColonne);
     }
    
    public boolean dropNotNull(String nomTable, String nomColonne){
        return connection.dropNotNull(nomTable, nomColonne);
    }
    
    public String[] getPKList(String nomTable){
        return connection.getPKTab(nomTable);
    }
    
    public String[] getFKNames(String nomTable)
    {
        return connection.getForeignKeyNames(nomTable);
    }

    public ResultSet getResultSetFromTable(Table table) throws Exception
    {
        return connection.getResultSetFromTable(table);
    }
    
    public ArrayList<String> getAttributesNames(Table laTable)
    {
        return connection.getAttributesNames(laTable);
    }
    
    public boolean renameTable(String nomActuel, String nouveauNom)
    {
        if (nouveauNom != null && !nomActuel.equals(nouveauNom)) {
            return connection.renameTable(nomActuel, nouveauNom);
        } else {
            return false;
        }
    }

    //Pour Plus tard//
    /* public ResultSet getResultSetFromTableWithParams(Table laTable , String lesAttributs) throws SQLException
    {
    return connection.getResultSetFromTableWithParams(laTable, lesAttributs);
    }*/
    
    public String[] getAttributesFromJTextArea(String lesAttributs)
    {
        lesAttributs = lesAttributs.toUpperCase();
        lesAttributs = lesAttributs.replace(" ", "");
        
        String[] tabAttributs ;
        
        tabAttributs = lesAttributs.split(",");
        
        return tabAttributs;
    }
    
    public void deleteRow(Table laTable , ArrayList<String> lesAttributs, ArrayList<String> lesValeurs, Component panel) throws SQLException
    {
        String requete = "SELECT ";
        for(int x = 0 ; x < lesAttributs.size() ; x++)
        {
            if(x>0)
            {
                requete = requete + ", " + lesAttributs.get(x) + " ";
            }
            else
            {
                requete = requete + lesAttributs.get(x) + " ";
            }
            
            System.err.println("La requete: " + requete);
        }
        requete = requete + "FROM " + laTable.getName();
        System.err.println("La requete: " + requete);
        
        connection.deleteRow(requete , lesValeurs);
    }
    
    public void updateRows(Object[][] valDeBase , TableModel modelNouveau , ArrayList<String> attributesNames, Table laTable) throws SQLException
    {
        String requete = "SELECT ";
        ArrayList<Attribute> listAttributs = new ArrayList<>();
        for(int i = 0 ; i < attributesNames.size() ; i++)
        {
            if(laTable.attributes().get(i).getName().equals(attributesNames.get(i)) )
            {
               listAttributs.add(laTable.attributes().get(i));
            }
            
            if(i>0)
            {
                requete = requete + ", " + attributesNames.get(i) + " ";
            }
            else
            {
                requete = requete + attributesNames.get(i) + " ";
            }
            System.err.println(requete);
        }
        requete = requete + "FROM " + laTable.getName();
        System.out.println(requete);
        connection.updateRows(valDeBase , modelNouveau ,requete, listAttributs);
    }
}
