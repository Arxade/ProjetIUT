/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.controllers;

import sgbd.connection.DatabaseConnection;
import sgbd.database.Table;
import sgbd.json.ConnectionDataJSON;

/**
 *
 * @author Kazed
 */
public class Controller {
    private DatabaseConnection connection;
    
    public Controller(DatabaseConnection co) {
        connection = co;
    }
    
    public String getUserName() {
        return connection.getUserName();
    }
    
    public String getDatabaseName() {
        return connection.getDatabaseName();
    }
    
    public boolean tryConnect(ConnectionDataJSON json, String user, String password) {
        if(json.isParametered()) {
            boolean connected = connection.connect(user, password);
            if(connected) {
                connection.prepareConnection();
            }
            return connected;
        }
        else return false;  
    }
    
    public String[] getTablesList() {
         if(connection.setTablesList()) return connection.getTablesList();
         else return null;
    }
    
    public boolean getAttributesList(Table t) {
        return connection.setTableColumns(t);
    }
    
    public String[] getTypesList() {
        if(connection.setTypesList()) return connection.getTypesList();
        else return null;
    }
    
    public void close() {
        connection.close();
    }

    public boolean tryDropTable(String table, boolean cascadeConstraints) {
        return connection.dropTable(table, cascadeConstraints);
    }
}
