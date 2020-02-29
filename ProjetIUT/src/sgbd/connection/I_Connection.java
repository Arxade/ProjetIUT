/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.TableModel;
import sgbd.database.Attribute;
import sgbd.database.Table;

/**
 *
 * @author diazt
 */
public interface I_Connection {
    
    boolean addColonne(String nomTable, String nomColonne, String typeColonne, int longueurColonne);

    boolean addConstraintNotNull(String nomTable, String nomColonne, String dataType, int longueur);

    boolean addConstraintUnique(String nomTable, String nomColonne);

    void addRow(String[][] listeDesValeurs, Table laTable, int nbrow) throws SQLException;

    boolean alterDatatypeColonne(String nomTable, String nomColonne, String datatype, int longueurColonne);

    void close();

    boolean connect(String user, String password);

    boolean createForeignKey(String nomTable, String nomColonne, String nomTableRef, String nomColonneRef);

    boolean createPrimaryKey(String nomTable, ArrayList<String> nomColonnesPK);

    void createTable(String tableName, ArrayList<Attribute> lstAt) throws SQLException;

    void deleteRow(String requete, ArrayList<String> valeurs) throws SQLException;

    boolean dropColonne(String nomTable, String nomColonne);

    boolean dropConstraint(String nomTable, String nomConstraint);

    boolean dropForeignKey(String nomTable, String nomFK);

    boolean dropNotNull(String nomTable, String nomColonne, String dataType, int longueur);

    boolean dropPrimaryKey(String nomTable);

    boolean dropTable(String table, boolean cascadeConstraints);

    ArrayList<String> getAttributesNames(Table laTable);

    ArrayList<HashMap> getConstraints(String table);

    String getDatabaseName();

    String[] getForeignKeyNames(String nomTable);

    String[] getNomsAttributsFromNomTable(String nomTable);

    String[] getPKTab(String table);

    String getPrimaryKeyFromTableName(String tableName) throws SQLException;

    ResultSet getResultSetFromRequete(String requeteSQL);

    ResultSet getResultSetFromTable(Table table) throws Exception;

    ArrayList<Attribute> getTableColumns(Table table);

    String[] getTablesList();

    String[] getTypesList();

    String getUserName();

    void prepareConnection();

    boolean prepareStatements();

    void query(String requete) throws SQLException;

    boolean renameColonne(String nomTable, String nomColonneActuel, String nomColonneNew, String dataType, int longueur);

    boolean renameTable(String nomActuel, String nouveauNom);

    boolean setTableColumns(Table table);

    boolean setTablesList();

    boolean setTypesList();

    String traduireRequeteGraphiqueEnSql(ArrayList<ArrayList<Object>> lesLignes, String nomTable);

    void updateRows(Object[][] valDeBase, TableModel modelNouveau, String laRequete, ArrayList<Attribute> lesAttributs) throws SQLException;
}
