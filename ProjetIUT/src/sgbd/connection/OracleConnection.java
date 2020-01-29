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
public class OracleConnection extends DatabaseConnection {

    public OracleConnection(HashMap<String, String> params) {
        url = "jdbc:oracle:thin:@" + params.get("Host") + ':' + params.get("Port") + ':' + params.get("Database");
    }

    @Override
    public boolean connect(String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            javax.swing.JOptionPane.showMessageDialog(null, "Connexion à la base de donnée réussie !");
            return true;
        } catch (HeadlessException | SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    @Override
    public String getDatabaseName() {
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM GLOBAL_NAME");
            resultSet.next();
            return resultSet.getString("GLOBAL_NAME");
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération du nom de la base de données.");
            return null;
        }
    }

    @Override
    public boolean prepareStatements() {
        try {
//            findColumnByID = connection.prepareStatement("SELECT column_name "
//                    + "FROM all_tab_columns "
//                    + "WHERE table_name = ? "
//                    + "AND column_id = ?");
//            foreignKeysList = connection.prepareStatement("SELECT t1.column_id COLUMN_ID, c2.table_name R_TABLE_NAME, t2.column_id R_COLUMN_ID "
//                    + "FROM all_constraints c "
//                    + "JOIN all_cons_columns c1 ON(c1.constraint_name = c.constraint_name) "
//                    + "JOIN all_cons_columns c2 ON(c.r_constraint_name = c2.constraint_name) "
//                    + "LEFT OUTER JOIN all_tab_columns t1 ON(t1.table_name = c1.table_name AND t1.column_name = c1.column_name) "
//                    + "LEFT OUTER JOIN all_tab_columns t2 ON(t2.table_name = c2.table_name AND t2.column_name = c2.column_name) "
//                    + "WHERE c.table_name = ? "
//                    + "ORDER BY t1.column_id");
            constraintsList = connection.prepareStatement("SELECT column_id, constraint_type, search_condition "
                    + "FROM all_cons_columns "
                    + "NATURAL JOIN all_tab_columns "
                    + "NATURAL JOIN all_constraints "
                    + "WHERE constraint_type <> 'R' "
                    + "AND table_name = ? "
                    + "ORDER BY column_id");
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de préparation des requêtes.");
            return false;
        }
    }

    @Override
    public ArrayList<HashMap> getForeignKeys(String table) {
        ArrayList<HashMap> fkList = null;
        try {
            fkList = new ArrayList<>();
            foreignKeysList.setString(1, table);
            resultSet = foreignKeysList.executeQuery();

            //on rempli l'arraylist des hashtable des foreign keys
            while (resultSet.next()) {
                HashMap<String, Object> h = new HashMap<>();
                h.put("COLUMN_ID", resultSet.getInt("COLUMN_ID"));
                h.put("R_TABLE_NAME", resultSet.getString("R_TABLE_NAME"));
                h.put("R_COLUMN_ID", resultSet.getInt("R_COLUMN_ID"));
                fkList.add(h);
            }
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération de la liste des clés étrangères.");
        }
        return fkList;
    }

    @Override
    public ArrayList<HashMap> getConstraints(String table) {
        ArrayList<HashMap> consList = null;
        try {
            consList = new ArrayList<>();
            constraintsList.setString(1, table);
            resultSet = constraintsList.executeQuery();
            while (resultSet.next()) {
                HashMap<String, Object> h = new HashMap<>();
                h.put("COLUMN_ID", resultSet.getInt("COLUMN_ID"));
                h.put("CONSTRAINT_TYPE", resultSet.getString("CONSTRAINT_TYPE").charAt(0));
                h.put("SEARCH_CONDITION", resultSet.getString("SEARCH_CONDITION"));
                consList.add(h);
            }
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération de la liste des contraintes.");
        }
        return consList;
    }

    @Override
    public boolean setTableColumns(Table table) {
//        if (prepareStatements()) {
//            ArrayList<HashMap> fk = getForeignKeys(table.getName());
//            ArrayList<HashMap> cons = getConstraints(table.getName());
//            ArrayList<Attribute> columns = getTableColumns(table);
//            if (fk != null && cons != null && columns != null) {
//
//                //Ajout des clefs étrangères et des autres contraintes
//                fk.forEach((c) -> {
//                    String columnName = findColumnByID(c.get("R_TABLE_NAME").toString(), (int) c.get("R_COLUMN_ID"));
//                    columns.get((int) c.get("COLUMN_ID") - 1).foreignKey(c.get("R_TABLE_NAME").toString(), columnName);
//                });
//                cons.forEach((c) -> {
//                    int attr = (int) c.get("COLUMN_ID") - 1;
//                    switch ((char) c.get("CONSTRAINT_TYPE")) {
//                        case 'C':
//                            if (c.get("SEARCH_CONDITION").toString().contains("IS NOT NULL")) {
//                                columns.get(attr).isNullable(false);
//                            }
//                            break;
//                        case 'P':
//                            columns.get(attr).isPrimaryKey(true);
//                            break;
//                        case 'U':
//                            columns.get(attr).isUnique(true);
//                            break;
//                        default:
//                            break;
//                    }
//                });
//                table.attributes().addAll(columns);
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }

        try {
            ArrayList<Attribute> columns = getTableColumns(table);
            String[] lesPK = getPKTab(table.getName());

            ResultSet rs = statement.executeQuery("SELECT * FROM " + table.getName());
            ResultSetMetaData rsmd = rs.getMetaData();;
            DatabaseMetaData dm = connection.getMetaData();
            
            ArrayList<HashMap> constraints = getConstraints(table.getName());
            
            List<List> lesFK = new ArrayList<>();
            ResultSet rsFK = dm.getImportedKeys(null, null, table.getName());
            while (rsFK.next()) {
                ArrayList<String> donneesFK = new ArrayList<>();
                donneesFK.add(rsFK.getString("FKCOLUMN_NAME"));
                donneesFK.add(rsFK.getString("PKTABLE_NAME"));
                donneesFK.add(rsFK.getString("PKCOLUMN_NAME"));
                lesFK.add(donneesFK);
            }
            
            //Obtention des unique
            constraints.forEach((c) -> {
                    int attr = (int) c.get("COLUMN_ID") - 1;
                    switch ((char) c.get("CONSTRAINT_TYPE")) {
                        case 'U':
                            columns.get(attr).isUnique(true);
                            break;
                        default:
                            break;
                    }
                                    });

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
                
                //Obtentation des FK
                for (List<String> laFK : lesFK) {
                    if (column.getName().equals(laFK.get(0))) {
                        column.foreignKey(laFK.get(1), laFK.get(2));
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
    public String findColumnByID(String rTable, int columnID) {
        try {
            findColumnByID.setString(1, rTable);
            findColumnByID.setInt(2, columnID);
            resultSet = findColumnByID.executeQuery();
            resultSet.next();
            return resultSet.getString("COLUMN_NAME");
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération d'un attribut par son ID.");
            return null;
        }
    }

    @Override
    public boolean dropTable(String table, boolean cascadeConstraints) {
        try {
            statement = connection.createStatement();
            String dropQuery = "DROP TABLE " + table;
            if (cascadeConstraints) {
                dropQuery += " CASCADE CONSTRAINTS";
            }
            statement.executeUpdate(dropQuery);
            tablesList.remove(table);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de suppression de la table.");
            return false;
        }
    }

    @Override
       public boolean renameColonne(String nomTable, String nomColonneActuel, String nomColonneNew, String datatype, int longueur) {
        try {
            statement = connection.createStatement();
            String renameQuery = "ALTER TABLE " + nomTable + " RENAME COLUMN " + nomColonneActuel + " TO " + nomColonneNew;
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
        return dropConstraint(nomTable, nomFK);
    }
       
       

}
