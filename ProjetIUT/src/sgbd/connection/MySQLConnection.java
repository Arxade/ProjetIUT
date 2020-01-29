/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.connection;

import java.awt.HeadlessException;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        try {
            ArrayList<Attribute> columns = getTableColumns(table);
            String[] lesPK = getPKTab(table.getName());

            ResultSet rs = statement.executeQuery("SELECT * FROM " + table.getName());
            ResultSetMetaData rsmd = rs.getMetaData();

            DatabaseMetaData dm = connection.getMetaData();
            ResultSet rs2 = dm.getImportedKeys(null, null, table.getName());

            //Obtention des PK
            for (String laPK : lesPK) {
                for (Attribute column : columns) {
                    if (column.getName().equals(laPK)) {
                        column.isPrimaryKey(true);
                    }
                }
            }

            int i = 1;
            for (Attribute column : columns) {
                
                //Obtention des not null
                int nullable = rsmd.isNullable(i);
                if (nullable == ResultSetMetaData.columnNoNulls) {
                    column.isNullable(false);
                }
                i++;

                //Obtention des FK
                rs2.beforeFirst();
                while (rs2.next()) {
                    if (rs2.getString("FKCOLUMN_NAME").equals(column.getName())) {
                        column.foreignKey(rs2.getString("PKTABLE_NAME"), rs2.getString("PKCOLUMN_NAME"));
                    }
                }
            }

            table.attributes().addAll(columns);
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(MySQLConnection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

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

    @Override
    public boolean dropForeignKey(String nomTable, String nomFK) {
        try {
            statement = connection.createStatement();
            String query = "ALTER TABLE " + nomTable + " DROP FOREIGN KEY " + nomFK;
            System.out.println(query);
            statement.executeUpdate(query);
            String query2 = "ALTER TABLE " + nomTable + " DROP INDEX " + nomFK;
            System.out.println(query2);
            statement.executeUpdate(query2);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    
}
