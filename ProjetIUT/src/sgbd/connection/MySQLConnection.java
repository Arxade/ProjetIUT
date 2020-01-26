/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.connection;

import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import sgbd.database.Attribute;
import sgbd.database.Table;

/**
 *
 * @author Paul
 */
public class MySQLConnection extends DatabaseConnection {

    public MySQLConnection(HashMap<String, String> params) {
        url = "jdbc:mysql://" + params.get("Host") + ':' + params.get("Port") + '/' + params.get("Database");
    }

    @Override
    public boolean connect(String user, String password) {
        try {
            // chargement driver sql
            Class.forName("com.mysql.cj.jdbc.Driver");

            // setup connexion avec la BD
            connection = DriverManager
                    .getConnection(url
                            + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                            + "&user=" + user
                            + "&password=" + password);
            javax.swing.JOptionPane.showMessageDialog(null, "Connexion réussie");
            return true;

        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    /*
    VOIR DANS "Connexion.java"
    
    @Override
    public ResultSet getResultSetFromTable(String table) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        statement = connect.createStatement();

        preparedStatement = connect
                .prepareStatement("SELECT * from " + table);
        resultSet = preparedStatement.executeQuery();
        return resultSet;

    }
     */
    @Override
    public boolean prepareStatements() {
        return true;
    }

    @Override
    public ArrayList<HashMap> getForeignKeys(String table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<HashMap> getConstraints(String table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String findColumnByID(String rTable, int columnID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean setTableColumns(Table table) {
        ArrayList<Attribute> columns = getTableColumns(table);
        table.attributes().addAll(columns);
        return true;

    }

    @Override
    public boolean dropTable(String table, boolean cascadeConstraints) {
        try {
            statement = connection.createStatement();
            String dropQuery = "DROP TABLE " + table;
            System.out.println(dropQuery);
            //if(cascadeConstraints) dropQuery += " CASCADE CONSTRAINTS";
            statement.executeUpdate(dropQuery);
            tablesList.remove(table);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de suppression de la table. " + e);
            return false;
        }
    }

    @Override
    public String getDatabaseName() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT DATABASE()");
            resultSet.next();
            return resultSet.getString("DATABASE()");
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération du nom de la base de données.");
            return null;
        }
    }

    @Override
    public boolean renameColonne(String nomTable, String nomColonneActuel, String nomColonneNew, String datatype, int longueur) {
        try {
            statement = connection.createStatement();
            String renameQuery = "ALTER TABLE " + nomTable + " CHANGE " + nomColonneActuel + " " + nomColonneNew + " " + datatype + "(" + longueur + ")";
            System.out.println(renameQuery);
            statement.executeUpdate(renameQuery);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
