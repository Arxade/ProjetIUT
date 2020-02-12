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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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
    
    //méthode qui retourne un tableau listant toutes les contraintes PRIMARY KEY, UNIQUE et CHECK des colonnes d'une table 
    public abstract ArrayList<HashMap> getConstraints(String table);
    public abstract boolean setTableColumns(Table table);
    
    public void prepareConnection() {
        try {
            dbMetadata = connection.getMetaData();
            prepareStatements();
        }
        catch(SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération du nom d'utilisateur. " + e);
        }
    }
    
    public String getUserName() {
        try {
            return dbMetadata.getUserName();
        }
        catch(SQLException e) {
             javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération du nom d'utilisateur. " + e );
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
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération de la liste des tables. " + e);
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
            javax.swing.JOptionPane.showMessageDialog(null, "Impossible de renommer la table : " + e);
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
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération de la liste des colonnes. " + e);
        }
        return columns;
    }
    
    public boolean setTypesList() {
        try {
            ArrayList<String> lst = new ArrayList<>();
            resultSet = dbMetadata.getTypeInfo();
            while (resultSet.next()) {
                if (!lst.contains(resultSet.getString("TYPE_NAME"))) {
                    lst.add(resultSet.getString("TYPE_NAME"));
                }
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
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération de la liste des types." + e);
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
            String addQuery;
            statement = connection.createStatement();
            if (longueurColonne == -1)
                addQuery = "ALTER TABLE " + nomTable + " ADD " + nomColonne + " " + typeColonne;
            else
                addQuery = "ALTER TABLE " + nomTable + " ADD " + nomColonne + " " + typeColonne + "(" + longueurColonne + ")";
            System.out.println(addQuery);
            statement.executeUpdate(addQuery);
            javax.swing.JOptionPane.showMessageDialog(null, "Colonne ajoutée.");
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de l'ajout de la colonne.");
            return false;
        }
    }

    public abstract boolean renameColonne(String nomTable, String nomColonneActuel, String nomColonneNew, String dataType, int longueur);

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
    
    public abstract boolean dropForeignKey(String nomTable, String nomFK);

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

    public boolean addConstraintNotNull(String nomTable, String nomColonne, String dataType, int longueur) {
        try {
            statement = connection.createStatement();
            String query = "ALTER TABLE " + nomTable + " MODIFY " + nomColonne + " " + dataType + "(" + longueur + ")" + " NOT NULL";
            System.out.println(query);
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
        public boolean dropNotNull(String nomTable, String nomColonne, String dataType, int longueur) {
        try {
            statement = connection.createStatement();
            String query = "ALTER TABLE " + nomTable + " MODIFY " + nomColonne + " " + dataType + "(" + longueur + ")" + " NULL";
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

            //Je recupere le nom de la clef primaire pour trier dans l'ordre croissant les lignes avec getPrimaryKeyFromTableName(leNom)
            preparedStatement = connection.prepareStatement("SELECT * FROM "+ table.getName() + " ORDER BY " + getPrimaryKeyFromTableName(table.getName()));
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
    
    
    public String[] getNomsAttributsFromNomTable(String nomTable)
    {
        ArrayList<String> nomAttributs = new ArrayList<>();
        try {
            resultSet = dbMetadata.getColumns(null, null, nomTable, null);
            while (resultSet.next()) {
                nomAttributs.add(resultSet.getString("COLUMN_NAME"));
            }
        } catch (SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, "Echec de récupération de la liste des colonnes. " + e);
        }
        
        String[] array = nomAttributs.toArray(new String[nomAttributs.size()]);
        return array;
    }
    
//    public String traduireRequeteGraphiqueEnSql(ArrayList<String> lesAttributs, String table, String condition)
//    {
//        String select, from, where, groupBy;
//        select = "SELECT ";
//        from = " FROM ";
//        where = "";
//        groupBy = "";
//        
//        for (String unAttribut  : lesAttributs) {
//            select = select + unAttribut +  ", ";
//        }
//        select = select.substring(0, select.length() - 2);
//        
//        from = from + table;
//                
//        String requete = select + from + where + groupBy;
//        System.out.println(requete);
//        return requete;
//    }
    
    public String traduireRequeteGraphiqueEnSql(ArrayList<ArrayList<Object>> lesLignes) {
        String select, from, where, groupBy;
        select = "SELECT ";
        from = " FROM ";
        where = "";
        groupBy = "";
        
        for (ArrayList<Object> uneLigne : lesLignes) {
            String attribut = uneLigne.get(1).toString();
            String condition = uneLigne.get(4).toString();
            String fonctionEnsemble = "Aucune";
            if (uneLigne.get(3) != null) {
                fonctionEnsemble = uneLigne.get(3).toString();
            }
            Boolean estDansSelect = Boolean.valueOf(uneLigne.get(2).toString());
            Boolean estDansGroupBy = Boolean.valueOf(uneLigne.get(5).toString());
            
            if (estDansSelect == true) {
                switch (fonctionEnsemble) {
                    case "Aucune":
                        select = select + attribut + ", ";
                        break;
                    case "Somme":
                        select = select + "SUM(" + attribut + "), ";
                        break;
                    case "Moyenne":
                        select = select + "AVG(" + attribut + "), ";
                        break;
                    case "Maximum":
                        select = select + "MAX(" + attribut + "), ";
                        break;
                    case "Minimum":
                        select = select + "MIN(" + attribut + "), ";
                        break;
                    case "Comptage":
                        select = select + "COUNT(" + attribut + "), ";
                        break;
                    default:
                        break;
                }
            }

            if (!condition.equals("")) {
                if (!where.contains("WHERE")) {
                    where = " WHERE ";
                } else {
                    where = where + " AND ";
                }
                where = where + attribut + " " + condition;
            }
        }
        
        select = select.substring(0, select.length() - 2);
        from = from + lesLignes.get(0).get(0).toString();

        String requete = select + from + where + groupBy;
        System.out.println(requete);
        return requete;
    }

    public ResultSet getResultSetFromRequete(String requeteSQL)
    {
        ResultSet rs = null;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(requeteSQL);
        } catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Erreur " + ex);
        }
        return rs;
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
                        String type = lesAttributs.get(col).getType();
                        System.err.println("Dans le if row=" + row + " col=" + col);
                        if("VARCHAR2".equals(type)  || "CHAR".equals(type))
                        {
                            resultSet.updateString(col+1, modelNouveau.getValueAt(row, col).toString() );
                            resultSet.updateRow();
                        }
                        else if("NUMBER".equals(type))
                        {
                            resultSet.updateInt(col+1, (int) modelNouveau.getValueAt(row, col));
                            resultSet.updateRow();
                        }
                        else if("FLOAT".equals(type) || "REAL".equals(type))
                        {
                            resultSet.updateFloat(col+1, (float) modelNouveau.getValueAt(row, col));
                            resultSet.updateRow();
                        }
                        else if("LONG".equals(type))
                        {
                            resultSet.updateLong(col+1, (long) modelNouveau.getValueAt(row, col));
                            resultSet.updateRow();
                        }
                        else if("DATE".equals(type))
                        {
                            resultSet.updateDate(col+1, Date.valueOf( (String) modelNouveau.getValueAt(row, col)) );
                            resultSet.updateRow();
                        }
                    }
                }                  
                row++;
            }            
    }
    
    public void addRow(String[][] listeDesValeurs, Table laTable, int nbrow) throws SQLException
    {
        //Je commence d'abord avec le premier attribut pour pouvoir mettre plus aisement les virgules
        String requete = "INSERT INTO " + laTable.getName() + " ( " + laTable.attributes().get(0).getName();
        for(int i = 1 ; i < laTable.attributes().size() ; i++)
        {
            requete = requete + ", "+ laTable.attributes().get(i).getName();
            System.out.println(requete);
        }
        requete = requete + ") VALUES (";
        
        for(int i = 0 ; i < laTable.attributes().size() ; i ++)
        {
            if(i<1)
            {
                requete = requete + "?";
            }
            else
            {
                requete = requete + ", ?";
            }
            
            System.err.println(requete);
        }
        
        requete = requete + ")";
        System.out.println(requete);
        
        String type = "";
        
        System.out.println("Dans addRow de DataBaseConnection.java \n " + requete);
        preparedStatement = connection.prepareStatement(requete);
        for(int row = 0 ; row < nbrow  ; row++)
        {
            for(int col = 0 ; col < laTable.attributes().size() ; col++)
            {
                type = laTable.attributes().get(col).getType();
                if("VARCHAR2".equals(type)  || "CHAR".equals(type))
                {
                    preparedStatement.setString(col+1, listeDesValeurs[row][col].toString() );
                }
                else if("NUMBER".equals(type))
                {
                    preparedStatement.setInt(col+1, Integer.parseInt(listeDesValeurs[row][col]));
                }
                else if("FLOAT".equals((type)) || "REAL".equals(type))
                {
                    preparedStatement.setFloat(col+1, Float.parseFloat(listeDesValeurs[row][col]));
                }
                else if("LONG".equals(type))
                {
                    preparedStatement.setLong(col+1, Long.parseLong(listeDesValeurs[row][col]));
                }
                else if("DATE".equals(type))
                {
                    preparedStatement.setDate(col+1, Date.valueOf(listeDesValeurs[row][col]));
                }
            }
        }
        
        preparedStatement.execute();
    }
    
    public String getPrimaryKeyFromTableName(String tableName) throws SQLException
    {
        resultSet = connection.getMetaData().getPrimaryKeys(null, null, tableName);
        resultSet.next();
        return resultSet.getString(4);
    }
}
