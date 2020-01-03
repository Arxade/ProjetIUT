/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.connection;

import sgbd.database.Attribute;
import sgbd.database.Table;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 *
 * @author Arxade
 */
public abstract class DatabaseConnection {
    protected Connection connection = null;
    protected DatabaseMetaData dbMetadata = null;
    protected Statement statement = null;
    protected PreparedStatement foreignKeysList = null;
    protected PreparedStatement constraintsList = null;
    protected PreparedStatement findColumnByID = null;
    protected ResultSet resultSet = null;
    protected PreparedStatement preparedStatement = null;
    protected final ArrayList<String> tablesList = new ArrayList<>();
    protected String[] typesList;
    protected String url;
    
    public abstract boolean connect(String user, String password);
    public abstract boolean prepareStatements();
    
    //methode retournant un tableau de Hashtable contenant pour chacun des éléments : 
    //le nom de la colonne , la clé primaire sur laquelle elle pointe et la table sur laqelle elle pointe.
    public abstract ArrayList<HashMap> getForeignKeys(String table);
    
    //méthode qui retourne un tableau listant toutes les contraintes PRIMARY KEY, UNIQUE et CHECK des colonnes d'une table 
    public abstract ArrayList<HashMap> getConstraints(String table);
    
    public abstract String findColumnByID(String rTable, int columnID);
    public abstract boolean setTableColumns(Table table);
    
    public void prepareConnection() {
        try {
            dbMetadata = connection.getMetaData();
            prepareStatements();
        }
        catch(SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération du nom d'utilisateur.");
        }
    }
    
    public String getUserName() {
        try {
            return dbMetadata.getUserName();
        }
        catch(SQLException e) {
             javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération du nom d'utilisateur.");
             return null;
        }
    }
    
    public abstract String getDatabaseName();
    
    public boolean setTablesList() {
        try {
            resultSet = dbMetadata.getTables(connection.getCatalog(), connection.getSchema(), "%", new String[]{"TABLE"});
            while (resultSet.next()) {
                tablesList.add(resultSet.getString("TABLE_NAME"));
            }
            Collections.sort(tablesList);
            return true;
        }
        catch(SQLException e){
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération de la liste des tables.");
            return false;
        }
        
    }
    
    public abstract boolean dropTable(String table, boolean cascadeConstraints);
    
    public boolean renameTable(String nomActuel, String nouveauNom) {

        String renameQuery = "RENAME " + nomActuel + " TO " + nouveauNom;
        System.out.println(renameQuery);
        try {
            statement = connection.createStatement();
            statement.executeQuery(renameQuery);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Impossible de renommer la table.");
            return false;
        }
    }
    
    public String[] getTablesList() {
        String[] tblLst = new String[tablesList.size()];
        int i = 0;
        for(String table : tablesList) {
            tblLst[i] = table;
            i++;
        }
        return tblLst;
    }
    
    public ArrayList<Attribute> getTableColumns(Table table) {
        ArrayList<Attribute> columns = null;
        try {
            columns = new ArrayList<>();
            resultSet = dbMetadata.getColumns(null, null, table.getName(), null);
            while(resultSet.next()){
                columns.add(new Attribute(resultSet.getString("COLUMN_NAME"), 
                                resultSet.getString("TYPE_NAME"),
                                resultSet.getInt("COLUMN_SIZE")));
            }
        }
        catch(SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération de la liste des colonnes.");
        }
        return columns;
    }
    
    public boolean setTypesList() {
        try {
            ArrayList<String> lst = new ArrayList<>();
            resultSet = dbMetadata.getTypeInfo();
            while (resultSet.next()) {
                lst.add(resultSet.getString("TYPE_NAME"));
            }
            Collections.sort(tablesList);
            typesList = new String[lst.size()];
            int i = 0;
            for(String type : lst) {
                typesList[i] = type;
                i++;
            }
            return true;
        }
        catch(SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération de la liste des types.");
            return false;
        }
    }
    
    public String[] getTypesList() {
        return typesList;
    }
  
    public boolean dropColonne(String nomTable, String nomColonne) {
        try {
            statement = connection.createStatement();
            String dropQuery = "ALTER TABLE " + nomTable + " DROP COLUMN " + nomColonne;
            System.out.println(dropQuery);
            statement.executeQuery(dropQuery);
            javax.swing.JOptionPane.showMessageDialog(null, "Colonne supprimée.");
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de suppression de la colonne.");
            return false;
        }
    }
    
        public boolean addColonne(String nomTable, String nomColonne, String typeColonne, int longueurColonne) {
        try {
            statement = connection.createStatement();
            String dropQuery = "ALTER TABLE " + nomTable + " ADD " + nomColonne + " " + typeColonne + "(" + longueurColonne + ")";
            System.out.println(dropQuery);
            statement.executeQuery(dropQuery);
            javax.swing.JOptionPane.showMessageDialog(null, "Colonne ajoutée.");
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de l'ajout de la colonne.");
            return false;
        }
    }

    public boolean renameColonne(String nomTable, String nomColonneActuel, String nomColonneNew) {
        try {
            statement = connection.createStatement();
            String renameQuery = "ALTER TABLE " + nomTable + " RENAME COLUMN " + nomColonneActuel + " TO " + nomColonneNew;
            System.out.println(renameQuery);
            statement.executeQuery(renameQuery);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }


    public void query (String requete) throws SQLException {
        statement = connection.createStatement();
        statement.execute(requete);
    }
    
    public void close() {
        try {
            if(statement != null) statement.close();
            if(preparedStatement != null) preparedStatement.close();
            if(resultSet != null) resultSet.close();
            findColumnByID.close();
            foreignKeysList.close();
            constraintsList.close();
            connection.close();
        } 
        catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de fermeture de la connexion");
        }
    }
    
    
   public ResultSet getResultSetFromTable(Table table) throws Exception{
                statement = connection.createStatement();

            preparedStatement = connection.prepareStatement("SELECT * FROM "+ table.getName());
            resultSet = preparedStatement.executeQuery();
            return resultSet;
   
    }
    
     /*
    METHODE SELECT PAR NOM DE TABLE JE LA LAISSE ON VA SUREMENT SEN SERVIR PLUS TARD
    
    public String writeSelectToString(String nomTable) throws SQLException, Exception {
        String text = "";
        resultSet = getResultSetFromTable(nomTable);
        ResultSet rsTable = getResultSetFromTable(nomTable);
        while (resultSet.next()) {
            for (int y = 1; y < (rsTable.getMetaData().getColumnCount()) + 1; y++) {
                if (y == rsTable.getMetaData().getColumnCount()) {
                    text = text + resultSet.getString(y) + "\r\n";
                } else {
                    text = text + resultSet.getString(y) + " ";
                }

            }
        }
        return text;
    }
    */
    
    public ArrayList<String> getAttributesNames(Table laTable)
    {
        ArrayList<String> lesAttributs = new ArrayList<>();
        for(Attribute attribute : laTable.attributes())
        {
            lesAttributs.add(attribute.getName());
        }
        return lesAttributs;
    }
    
    //Pour plus tard//
    /*public ResultSet getResultSetFromTableWithParams(Table laTable, String lesAttributs) throws SQLException
    {
    System.out.println("Nom de table: " + laTable.getName() + " Nom des attributs: " + lesAttributs);
    String query = "SELECT * FROM " + laTable.getName();
    resultSet = statement.executeQuery(query);
    return resultSet;
    }*/
    
    public void deleteRow(String requete , ArrayList<String> valeurs) throws SQLException
    {
        statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
        resultSet = statement.executeQuery(requete);
        boolean valeursDeLaLigneCherche = true;
        while(resultSet.next())
        {
            valeursDeLaLigneCherche = true;
            for(int i = 1 ; i < resultSet.getMetaData().getColumnCount()+1 ; i++)
            {
                String valDuResultSet = resultSet.getString(i);
                if(resultSet.wasNull())
                {
                    valDuResultSet = "null";
                }
                System.out.println("Dans le for de deleterow de connection i = " + i);
                if(!valDuResultSet.equals(valeurs.get(i-1)))
                {
                    System.out.println("Dans le if du for resultSet = " + resultSet.getString(i) + " ET valeurs.get = " + valeurs.get(i-1));
                    valeursDeLaLigneCherche = false;
                    System.out.println("Valeur de valeursDeLaLigneCherche: " + valeursDeLaLigneCherche);
                }
                
            }
            if(valeursDeLaLigneCherche)
            {
                System.err.println("Dans le deleteRow de DataBaseConnection: Dans le if(valeursDeLaLigneCherche)");
                resultSet.deleteRow();
            }
            
        }
        
        
    }
}
