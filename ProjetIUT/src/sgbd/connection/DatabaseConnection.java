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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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

    
    public void createTable(String tableName, ArrayList<Attribute> lstAt) throws SQLException{
        
      
        String req = "CREATE TABLE " + tableName.toUpperCase() + "(";
        String contraintes = "";

        for (Attribute at : lstAt) {
            req += at.getName() + " " + at.getType();
            if(at.getLength()!= -1){
                req+= "(" + at.getLength() + "), ";
            }
            else req += ", ";

            if(at.isPrimaryKey()){
                if(contraintes.equals("")) contraintes += "CONSTRAINT pk_" + at.getName() + " PRIMARY KEY (" + at.getName() + ") ";
                else contraintes += ", CONSTRAINT pk_" + at.getName() + " PRIMARY KEY (" + at.getName() + ") ";
            }
            
            if(!at.isNullable()){
                if(contraintes.equals("")) contraintes += "CONSTRAINT nn_" + at.getName() + " CHECK (" + at.getName() + " IS NOT NULL) ";
                else contraintes += ", CONSTRAINT nn_" + at.getName() + " CHECK (" + at.getName() + " IS NOT NULL) ";
            }

            if(at.isUnique()){
                if(contraintes.equals("")) contraintes += "CONSTRAINT un_" + at.getName() + " UNIQUE (" + at.getName() + ") ";
                else contraintes += ", CONSTRAINT un_" + at.getName() + " UNIQUE (" + at.getName() + ") ";
            }

            if(!at.isForeignKey()[0].equals("NOTFOREIGNKEY")){
                if(contraintes.equals("")) contraintes += "CONSTRAINT fk_" + at.getName() + " FOREIGN KEY (" + at.getName() + ") REFERENCES " + at.isForeignKey()[0] + "(" + at.isForeignKey()[1] + ") ";
                else contraintes += ", CONSTRAINT fk_" + at.getName() + " FOREIGN KEY (" + at.getName() + ") REFERENCES " + at.isForeignKey()[0] + "(" + at.isForeignKey()[1] + ") ";
            }
        }

        req += contraintes + ")";

        System.out.println(req);
        
        
        query(req);
    }
    
    
    public String[] getTablesList() {
        try {
            ArrayList<String> lesTables = new ArrayList<>();
            dbMetadata = connection.getMetaData();
            String[] types = {"TABLE"};
            resultSet = dbMetadata.getTables(connection.getCatalog(), connection.getSchema(), "%", types);
            while (resultSet.next()) {
                String laPK = resultSet.getString("TABLE_NAME");
                lesTables.add(laPK);
            }
            String[] tblLst = lesTables.toArray(new String[lesTables.size()]);
            return tblLst;
        } catch (SQLException e) {
            String[] array;
            return array = new String[1];
        }

    }

    public String[] getPKTab(String table) {
        try {
            ArrayList<String> listePK = new ArrayList<>(2);
            dbMetadata = connection.getMetaData();
            resultSet = dbMetadata.getPrimaryKeys(connection.getCatalog(), connection.getSchema(), table);
            while (resultSet.next()) {
                String laPK = resultSet.getString("COLUMN_NAME");
                listePK.add(laPK);
            }
            String[] array = listePK.toArray(new String[listePK.size()]);
            return array;
        } catch (SQLException ex) {
            String[] array;
            return array = new String[1];
        }
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
            statement.executeUpdate(dropQuery);
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
            String addQuery = "ALTER TABLE " + nomTable + " ADD " + nomColonne + " " + typeColonne + "(" + longueurColonne + ")";
            System.out.println(addQuery);
            statement.executeUpdate(addQuery);
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
            statement.executeUpdate(renameQuery);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean alterDatatypeColonne(String nomTable, String nomColonne, String datatype, int longueurColonne) {
        try {
            statement = connection.createStatement();
            String query = "";
            if (longueurColonne != -1) {
                query = "ALTER TABLE " + nomTable + " MODIFY " + nomColonne + " " + datatype + "(" + longueurColonne + ")";
            } else {
                query = "ALTER TABLE " + nomTable + " MODIFY " + nomColonne + " " + datatype;
            }
            System.out.println(query);
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    public boolean dropPrimaryKey(String nomTable) {
        try {
            statement = connection.createStatement();
            String query = "ALTER TABLE " + nomTable + " DROP PRIMARY KEY";
            System.out.println(query);
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    public boolean createPrimaryKey(String nomTable, ArrayList<String> nomColonnesPK) {
        try {
            String colonnesPK = "";
            for (String uneColonne : nomColonnesPK) {
                colonnesPK = colonnesPK + uneColonne + ",";
            }
            colonnesPK = colonnesPK.substring(0, colonnesPK.length() - 1);
            statement = connection.createStatement();
            String query = "ALTER TABLE " + nomTable + " ADD CONSTRAINT pk_" + nomTable + " PRIMARY KEY " + "(" + colonnesPK + ")";
            System.out.println(query);
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
        
    public boolean createForeignKey(String nomTable, String nomColonne, String nomTableRef, String nomColonneRef) {
        try {
            statement = connection.createStatement();
            String query = "ALTER TABLE " + nomTable + " ADD CONSTRAINT fk_" + nomColonne + " FOREIGN KEY " + "(" + nomColonne + ")" 
                    + " REFERENCES " + nomTableRef + "(" + nomColonneRef + ")";
            System.out.println(query);
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean dropConstraint(String nomTable, String nomConstraint) {
        try {
            statement = connection.createStatement();
            String query = "ALTER TABLE " + nomTable + " DROP CONSTRAINT " + nomConstraint;
            System.out.println(query);
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean addConstraintUnique(String nomTable, String nomColonne) {
        try {
            statement = connection.createStatement();
            String query = "ALTER TABLE " + nomTable + " ADD CONSTRAINT un_" + nomColonne + " UNIQUE("+nomColonne+")";
            System.out.println(query);
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public boolean addConstraintNotNull(String nomTable, String nomColonne) {
        try {
            statement = connection.createStatement();
            String query = "ALTER TABLE " + nomTable + " MODIFY " + nomColonne + " NOT NULL";
            System.out.println(query);
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
        public boolean dropNotNull(String nomTable, String nomColonne) {
        try {
            statement = connection.createStatement();
            String query = "ALTER TABLE " + nomTable + " MODIFY " + nomColonne + " NULL";
            System.out.println(query);
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    public String[] getForeignKeyNames(String nomTable){
       ArrayList<String> lesFK = new ArrayList<>();    
        try {
            dbMetadata = connection.getMetaData();
            resultSet = dbMetadata.getImportedKeys(connection.getCatalog(), connection.getSchema(), nomTable);
            while (resultSet.next())
            {
                lesFK.add(resultSet.getString("FK_NAME"));
            }
            String[] array = lesFK.toArray(new String[lesFK.size()]);
            return array;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            String[] array;
            return array = new String[1];
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
            if (findColumnByID != null) findColumnByID.close();
            if (foreignKeysList != null) foreignKeysList.close();
            if (constraintsList != null) constraintsList.close();
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
    
    public void updateRows(Object[][] valDeBase , TableModel modelNouveau, String laRequete, ArrayList<Attribute> lesAttributs) throws SQLException
    {
            statement = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(laRequete);
            int row = 0;
            while(resultSet.next())
            {
                System.out.println("Dans le while de updateRows de DataBaseConnection: row = " + row);
                for(int col = 0 ; col < valDeBase.length ; col++)
                {
                    System.err.println("les vals " + modelNouveau.getValueAt(row, col) + "   " + valDeBase[col][row]);
                    if(!modelNouveau.getValueAt(row, col).equals(valDeBase[col][row]))
                    {
                        System.err.println("Dans le if row=" + row + " col=" + col);
                        if("VARCHAR2".equals(lesAttributs.get(col).getType())  || "CHAR".equals(lesAttributs.get(col).getType()))
                        {
                            resultSet.updateString(col+1, modelNouveau.getValueAt(row, col).toString() );
                            resultSet.updateRow();
                        }
                        else if("NUMBER".equals(lesAttributs.get(col).getType()))
                        {
                            resultSet.updateInt(col+1, (int) modelNouveau.getValueAt(row, col));
                            resultSet.updateRow();
                        }
                        else if("FLOAT".equals(lesAttributs.get(col).getType()) || "REAL".equals(lesAttributs.get(col).getType()))
                        {
                            resultSet.updateFloat(col+1, (float) modelNouveau.getValueAt(row, col));
                            resultSet.updateRow();
                        }
                        else if("LONG".equals(lesAttributs.get(col).getType()))
                        {
                            resultSet.updateLong(col+1, (long) modelNouveau.getValueAt(row, col));
                            resultSet.updateRow();
                        }
                        else if("DATE".equals(lesAttributs.get(col).getType()))
                        {
                            resultSet.updateDate(col+1, (Date) modelNouveau.getValueAt(row, col));
                            resultSet.updateRow();
                        }
                    }
                }
                    
                row++;
            }
            
    }
    
    public void addRow(Object[][] listeDesValeurs, Table laTable, int nbrow) throws SQLException
    {
        resultSet = connection.getMetaData().getPrimaryKeys(null, null, laTable.getName());
        resultSet.next();
        String pk = resultSet.getString(4);
        
        String requete = "INSERT INTO " + laTable.getName() + " ("+ pk +") VALUES(" +listeDesValeurs[0][0] + ")";
        statement = connection.createStatement();
        statement.executeUpdate(requete);
        // String type = "";
        
        /*for(Attribute attribut: laTable.attributes())
        {
        if(attribut.getName().equals(pk))
        {
        type = attribut.getType();
        System.out.println(type);
        }
        }
        
        System.out.println("Dans addRow de DataBaseConnection.java \n " + requete);
        
        for(int row = 0 ; row < nbrow  ; row++)
        {
        for(int col = 0 ; col < laTable.attributes().size() ; col++)
        {
        if("VARCHAR2".equals(type)  || "CHAR".equals(type))
        {
        preparedStatement.setString(1, listeDesValeurs[row][1].toString() );
        }
        else if("NUMBER".equals(type))
        {
        preparedStatement.setInt(1, (int) listeDesValeurs[row][1]);
        }
        else if("FLOAT".equals((type)) || "REAL".equals(type))
        {
        preparedStatement.setFloat(1, (float) listeDesValeurs[row][1]);
        }
        else if("LONG".equals(type))
        {
        preparedStatement.setLong(1, (long) listeDesValeurs[row][1]);
        }
        else if("DATE".equals(type))
        {
        preparedStatement.setDate(1, (Date) listeDesValeurs[row][1]);
        }
        }
        }
        
        preparedStatement.execute();*/
    }
}
