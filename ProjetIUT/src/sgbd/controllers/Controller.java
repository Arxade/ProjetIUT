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
    
}
