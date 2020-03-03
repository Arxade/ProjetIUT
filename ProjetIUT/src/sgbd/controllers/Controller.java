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
import java.util.HashMap;
import javax.swing.table.TableModel;
import sgbd.connection.*;
import sgbd.database.Attribute;
import sgbd.database.Table;
import sgbd.json.ConnectionDataJSON;

/**
 *
 * @author Kazed
 */
public class Controller {
    private I_Connection DBConnection;
    private String SGBD;
    private HashMap<String, String> params;
    
    public Controller(String SGBD, HashMap<String, String> params) {
        this.SGBD = SGBD;
        this.params = params;
        this.DBConnection = ConnectionFactory.createConnection(params, SGBD);
    }
    
    public String getUserName() {
        return DBConnection.getUserName();
    }
    
    public String getDatabaseName() {
        return DBConnection.getDatabaseName();
    }
    
    public boolean connect(ConnectionDataJSON json, String user, String password) {
        if(json.isParametered()) {
            boolean connected = DBConnection.connect(user, password);
            if(connected) {
                DBConnection.prepareConnection();
            }
            return connected;
        }
        else return false;  
    }
    
    public String[] getTablesList() {
         if(DBConnection.setTablesList()) return DBConnection.getTablesList();
         else return null;
    }
    
    public boolean getAttributesList(Table t) {
        return DBConnection.setTableColumns(t);
    }
    
    public String[] getTypesList() {
        if(DBConnection.setTypesList()) return DBConnection.getTypesList();
        else return null;
    }
    
    public void close() {
        DBConnection.close();
    }

    
    public void tryCreateTable(String nomTable, TableModel model) throws SQLException{
        ArrayList<Attribute> lstAt = new ArrayList<>();       
        for(int i = 0; i < model.getRowCount(); i++){           
            Attribute at = new Attribute((String) model.getValueAt(i, 0), (String) model.getValueAt(i, 1));   
            //lenght
            if(model.getValueAt(i, 2) != null && (int) model.getValueAt(i, 2) > 0) at.setLength((int) model.getValueAt(i, 2)); 
            else at.setLength(-1);
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

        DBConnection.createTable(nomTable, lstAt);
    }
    
    
    
    public boolean dropTable(String table, boolean cascadeConstraints) {
        return DBConnection.dropTable(table, cascadeConstraints);
    }

    public boolean dropColonne(String nomTable, String nomColonne) {
        return DBConnection.dropColonne(nomTable, nomColonne);
    }

    public boolean addColonne(String nomTable, String nomColonne, String typeColonne, int longueurColonne) {
        return DBConnection.addColonne(nomTable, nomColonne, typeColonne, longueurColonne);
    }

    public boolean renameColonne(String nomTable, String nomColonneActuel, String nomColonneNew, String dataType, int longueur) {
        return DBConnection.renameColonne(nomTable, nomColonneActuel, nomColonneNew, dataType, longueur);
    }

    public boolean alterDatatypeColonne(String nomTable, String nomColonne, String typeColonne, int longueurColonne) {
        return DBConnection.alterDatatypeColonne(nomTable, nomColonne, typeColonne, longueurColonne);
    }

    public boolean dropPrimaryKey(String nomTable) {
        return DBConnection.dropPrimaryKey(nomTable);
    }
    
    public boolean createPrimaryKey(String nomTable, ArrayList<String> nomColonnesPK){
        return DBConnection.createPrimaryKey(nomTable, nomColonnesPK);
    }

    public boolean createForeignyKey(String nomTable, String nomColonne, String nomTableRef, String nomColonneRef) {
        return DBConnection.createForeignKey(nomTable, nomColonne, nomTableRef, nomColonneRef);
    }

    public boolean dropConstraint(String nomTable, String nomConstraint) {
        return DBConnection.dropConstraint(nomTable, nomConstraint);
    }
    
    public boolean addConstraintUnique(String nomTable, String nomColonne) {
        return DBConnection.addConstraintUnique(nomTable, nomColonne);
    }
    
     public boolean addConstraintNotNull(String nomTable, String nomColonne, String dataType, int longueur) {
         return DBConnection.addConstraintNotNull(nomTable, nomColonne, dataType, longueur);
     }
    
    public boolean dropNotNull(String nomTable, String nomColonne, String dataType, int longueur){
        return DBConnection.dropNotNull(nomTable, nomColonne, dataType, longueur);
    }
    
    public boolean dropFK(String nomTable, String nomFK)
    {
        return DBConnection.dropForeignKey(nomTable, nomFK);
    }
    
    public String[] getPKList(String nomTable){
        return DBConnection.getPKTab(nomTable);
    }
    
    public String[] getFKNames(String nomTable)
    {
        return DBConnection.getForeignKeyNames(nomTable);
    }

    public ResultSet getResultSetFromTable(Table table) throws Exception
    {
        return DBConnection.getResultSetFromTable(table);
    }
    
    public ArrayList<String> getAttributesNames(Table laTable)
    {
        return DBConnection.getAttributesNames(laTable);
    }
    
    public boolean renameTable(String nomActuel, String nouveauNom)
    {
        if (nouveauNom != null && !nomActuel.equals(nouveauNom)) {
            return DBConnection.renameTable(nomActuel, nouveauNom);
        } else {
            return false;
        }
    }
    
    public String[] getNomsAttributsFromNomTable(String nomTable) {
        return DBConnection.getNomsAttributsFromNomTable(nomTable);
    }

//    public String traduireRequeteGraphiqueEnSql(ArrayList<String> lesAttributs, String table, String condition) {
//        return connection.traduireRequeteGraphiqueEnSql(lesAttributs, table, condition);
//    }
    
        public String traduireRequeteGraphiqueEnSql(ArrayList<ArrayList<Object>> lesLignes, String nomTable, boolean estDistinct) {
        return DBConnection.traduireRequeteGraphiqueEnSql(lesLignes, nomTable, estDistinct);
    }

    public ResultSet getResultSetFromRequete(String requeteSQL) {
        return DBConnection.getResultSetFromRequete(requeteSQL);
    }


    //Pour Plus tard//
    /* public ResultSet getResultSetFromTableWithParams(Table laTable , String lesAttributs) throws SQLException
    {
    return connection.getResultSetFromTableWithParams(laTable, lesAttributs);
    }*/
    
    /*public String[] getAttributesFromJTextArea(String lesAttributs)
    {
    lesAttributs = lesAttributs.toUpperCase();
    lesAttributs = lesAttributs.replace(" ", "");
    
    String[] tabAttributs ;
    
    tabAttributs = lesAttributs.split(",");
    
    return tabAttributs;
    }*/
    
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
        
        DBConnection.deleteRow(requete , lesValeurs);
    }
    
    public void updateRows(Object[][] valDeBase , TableModel modelNouveau , Table laTable) throws SQLException
    {
        String requete = "SELECT ";
        for(int i = 0 ; i < laTable.attributes().size() ; i++)
        {
            if(i>0)
            {
                requete = requete + ", " + laTable.attributes().get(i).getName() + " ";
            }
            else
            {
                requete = requete + laTable.attributes().get(i).getName() + " ";
            }
            System.err.println(requete);
        }
        requete = requete + "FROM " + laTable.getName() + " ORDER BY " + DBConnection.getPrimaryKeyFromTableName(laTable.getName());
        System.out.println(requete);
        DBConnection.updateRows(valDeBase , modelNouveau ,requete, laTable.attributes());
    }
    
    public void addRows(String[][] listeDesValeurs , Table laTable, int nbRows) throws SQLException
    {
        DBConnection.addRow(listeDesValeurs, laTable, nbRows);
    }
}
