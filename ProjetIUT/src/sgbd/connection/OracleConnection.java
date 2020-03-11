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
    public boolean renameTable(String nomActuel, String nouveauNom) {

        String renameQuery = "RENAME " + nomActuel + " TO " + nouveauNom;
        System.out.println(renameQuery);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(renameQuery);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Impossible de renommer la table : " + e);
            return false;
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
    
    @Override
    public void createTable(String tableName, ArrayList<Attribute> lstAt) throws SQLException {

        String req = "CREATE TABLE " + tableName.toUpperCase() + "(";
        String contraintes = " ";

        for (Attribute at : lstAt) {
            req += at.getName() + " " + at.getType();
            if(at.getLength() == -1){
                if(!at.isNullable()) req += " NOT NULL";
                req += ", ";   
            }
            else {
                req += "(" + at.getLength() + ")";
                if(!at.isNullable()) req += " NOT NULL";   
                req += ", ";
            }
            
            

            if (at.isPrimaryKey()) {
                if (contraintes.equals(" ")) {
                    contraintes += "CONSTRAINT pk_" + at.getName() + " PRIMARY KEY (" + at.getName() + ")";
                } else {
                    contraintes += ", CONSTRAINT pk_" + at.getName() + " PRIMARY KEY (" + at.getName() + ")";
                }
            }
/*
            if (!at.isNullable()) {
                if (contraintes.equals(" ")) {
                    contraintes += "CONSTRAINT nn_" + at.getName() + " CHECK (" + at.getName() + " IS NOT NULL)";
                } else {
                    contraintes += ", CONSTRAINT nn_" + at.getName() + " CHECK (" + at.getName() + " IS NOT NULL)";
                }
            }
*/
            if (at.isUnique()) {
                if (contraintes.equals(" ")) {
                    contraintes += "CONSTRAINT un_" + at.getName() + " UNIQUE (" + at.getName() + ")";
                } else {
                    contraintes += ", CONSTRAINT un_" + at.getName() + " UNIQUE (" + at.getName() + ")";
                }
            }

            if (!at.isForeignKey()[0].equals("NOTFOREIGNKEY")) {
                if (contraintes.equals(" ")) {
                    contraintes += "CONSTRAINT fk_" + at.getName() + " FOREIGN KEY (" + at.getName() + ") REFERENCES " + at.isForeignKey()[0] + "(" + at.isForeignKey()[1] + ")";
                } else {
                    contraintes += ", CONSTRAINT fk_" + at.getName() + " FOREIGN KEY (" + at.getName() + ") REFERENCES " + at.isForeignKey()[0] + "(" + at.isForeignKey()[1] + ")";
                }
            }
        }
        
        
        
        if(contraintes.equals(" ")){
            req = req.substring(0, req.length() -2);
            req += ")";
        }
        else req += contraintes + ")";

        System.out.println(req);

        query(req);
    }
       
       

}
