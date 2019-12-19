/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetIUT.classesConnexion;

import ProjetIUT.DatabaseClasses.Attribute;
import ProjetIUT.DatabaseClasses.Table;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arxade
 */
public abstract class Connexion {
    protected String url;
    protected Connection connect = null;
    protected DatabaseMetaData dbMetadata = null;
    protected Statement statement = null;
    protected PreparedStatement foreignKeysList = null;
    protected PreparedStatement constraintsList = null;
    protected PreparedStatement findColumnByID = null;
    protected ResultSet resultSet = null;
    protected PreparedStatement preparedStatement = null;
    private HashMap<String, Table> tables = new HashMap<>();
     
    public String getURL() {
        return url;
    }
    
    public abstract boolean connexion(String url, String user, String password);

    public void prepareStatements() throws SQLException {
        findColumnByID = connect.prepareStatement("SELECT column_name " +
                                                        "FROM all_tab_columns " +
                                                        "WHERE table_name = ? " +
                                                        "AND column_id = ?");
            foreignKeysList = connect.prepareStatement("SELECT t1.column_id COLUMN_ID, c2.table_name R_TABLE_NAME, t2.column_id R_COLUMN_ID " +
                                                "FROM all_constraints c " +
                                                "JOIN all_cons_columns c1 ON(c1.constraint_name = c.constraint_name) " +
                                                "JOIN all_cons_columns c2 ON(c.r_constraint_name = c2.constraint_name) " +
                                                "LEFT OUTER JOIN all_tab_columns t1 ON(t1.table_name = c1.table_name AND t1.column_name = c1.column_name) " +
                                                "LEFT OUTER JOIN all_tab_columns t2 ON(t2.table_name = c2.table_name AND t2.column_name = c2.column_name) " +
                                                "WHERE c.table_name = ? " +
                                                "ORDER BY t1.column_id");
            constraintsList = connect.prepareStatement("SELECT column_id, constraint_type, search_condition " +
                                                    "FROM all_cons_columns " +
                                                    "NATURAL JOIN all_tab_columns " +
                                                    "NATURAL JOIN all_constraints " +
                                                    "WHERE constraint_type <> 'R' " +
                                                    "AND table_name = ? " +
                                                    "ORDER BY column_id");
    }
    
    public void setTables() throws SQLException {
        dbMetadata = connect.getMetaData();
        resultSet = dbMetadata.getTables(connect.getCatalog(), connect.getSchema(), "%", new String[]{"TABLE"});
        while (resultSet.next()) {
            String table = resultSet.getString("TABLE_NAME");
            tables.put(table, new Table(table));
        }
    }
    
    public HashMap<String, Table> getTables() {
        return tables;
    }
    
    public void tableColumns(String table)throws SQLException{
        tables.get(table).attributes().clear();
        ArrayList<HashMap> fk = getForeignKeys(table);
        ArrayList<HashMap> cons = getConstraints(table);
        resultSet = dbMetadata.getColumns(null, null, table, null);
        ArrayList<Attribute> colonnes = new ArrayList<>();
        
        //Récupération des colonnes de la table
        while(resultSet.next()){
            colonnes.add(new Attribute(resultSet.getString("COLUMN_NAME"), 
                            resultSet.getString("TYPE_NAME"),
                            resultSet.getInt("COLUMN_SIZE")));
        }
        
        //Ajout des clefs étrangères et des autres contraintes
        fk.forEach((c) -> {
            try {
                findColumnByID.setString(1, c.get("R_TABLE_NAME").toString());
                findColumnByID.setString(2, c.get("R_COLUMN_ID").toString());
                resultSet = findColumnByID.executeQuery();
                while(resultSet.next()){
                    colonnes.get((int)c.get("COLUMN_ID") - 1).foreignKey(c.get("R_TABLE_NAME").toString(), resultSet.getString("COLUMN_NAME"));
                }
            }
            catch (SQLException ex) {
                Logger.getLogger(Connexion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }); 
        cons.forEach((c) -> {
            int a = (int)c.get("COLUMN_ID") - 1;
            switch((char)c.get("CONSTRAINT_TYPE")) {
                case 'C' :
                    if(c.get("SEARCH_CONDITION").toString().contains("IS NOT NULL")) colonnes.get(a).isNullable(false);
                    break;
                case 'P' :
                    colonnes.get(a).isPrimaryKey(true);
                    break;
                case 'U' :
                    colonnes.get(a).isUnique(true);
                    break;
                default :
                    break;
            }
        });
        tables.get(table).attributes().addAll(colonnes);
    }
    
    //methode retournant un tableau de Hashtable contenant pour chacun des éléments : 
    //le nom de la colonne , la clé primaire sur laquelle elle pointe et la table sur laqelle elle pointe.
    private ArrayList<HashMap> getForeignKeys(String table) throws SQLException {
        foreignKeysList.setString(1, table);
        resultSet = foreignKeysList.executeQuery();
        ArrayList<HashMap> tab = new ArrayList<>();
  
        //on rempli l'arraylist des hashtable des foreign keys
        while (resultSet.next()) {
            HashMap<String, Object> h = new HashMap<>();
            h.put("COLUMN_ID", resultSet.getInt("COLUMN_ID")) ;
            h.put("R_TABLE_NAME", resultSet.getString("R_TABLE_NAME"));
            h.put("R_COLUMN_ID", resultSet.getInt("R_COLUMN_ID"));
            tab.add(h);
        }
        return tab;
    }
    
    //méthode qui retourne un tableau listant toutes les contraintes PRIMARY KEY, UNIQUE et CHECK des colonnes d'une table 
    public ArrayList<HashMap> getConstraints(String table) throws SQLException {
        constraintsList.setString(1, table);
        resultSet = constraintsList.executeQuery();
        ArrayList<HashMap> tab = new ArrayList<>();
        while (resultSet.next()) {
            HashMap<String, Object> h = new HashMap<>();
            h.put("COLUMN_ID", resultSet.getInt("COLUMN_ID")) ;
            h.put("CONSTRAINT_TYPE", resultSet.getString("CONSTRAINT_TYPE").charAt(0));
            h.put("SEARCH_CONDITION", resultSet.getString("SEARCH_CONDITION"));
            tab.add(h);
        }
        return tab;
    }
    
    //supprime la table passée en paramètre
    public void dropTable(String table) throws SQLException{
        Statement dropTable = connect.createStatement();
        dropTable.executeUpdate("DROP TABLE " + table + " CASCADE CONSTRAINTS");  
    }
    
    
    public void modifTable(String nomTable, Table oldT, Table newT){
        
        
        
    }
    
    
    
    
    
    
    
    public void query (String requete) throws SQLException {
        statement = connect.createStatement();
        statement.execute(requete);
    }
    
    public void close() {
        try {
            if (statement != null) {
                statement.close();
            }
            resultSet.close();
            findColumnByID.close();
            foreignKeysList.close();
            constraintsList.close();
            connect.close();
        } 
        catch (SQLException e) {
            
        }
    }
    
    
}