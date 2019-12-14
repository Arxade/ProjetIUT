/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetIUT.classesConnexion;


import java.awt.HeadlessException;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Paul
 */
public class ConnexionMySQL extends Connexion {

    public ConnexionMySQL(String host, int port, String db) {
        url = "jdbc:mysql://" + host + ':' + port + '/' + db;
    }

    @Override
    public boolean connexion(String url, String user, String password) {
        try {
            // chargement driver sql
            Class.forName("com.mysql.cj.jdbc.Driver");

            // setup connexion avec la BD
            connect = DriverManager
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
}