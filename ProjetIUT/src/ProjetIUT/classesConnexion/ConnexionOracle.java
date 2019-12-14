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
public class ConnexionOracle extends Connexion {
    
    public ConnexionOracle(String host, int port, String db){
        url = "jdbc:oracle:thin:@" + host + ':' + port + ':' + db;
    }
    
    @Override
    public boolean connexion(String url, String user, String password){
        try {
            connect = DriverManager.getConnection(url, user , password);
            javax.swing.JOptionPane.showMessageDialog(null, "Connexion à la base de donnée réussie !");
            return true;
        }
        catch (HeadlessException | SQLException e) {
            javax.swing.JOptionPane.showMessageDialog(null, e);
            return false;
        } 
    }
    
    /* 
    VOIR DANS "Connexion.java"
    
    @Override
    public ResultSet getResultSetFromTable(String table) throws Exception{
                statement = connect.createStatement();

            preparedStatement = connect.prepareStatement("SELECT * FROM "+ table);
            resultSet = preparedStatement.executeQuery();
            return resultSet;
   
    }
    */
}