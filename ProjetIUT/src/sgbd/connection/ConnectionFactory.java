/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.connection;

import java.util.HashMap;

/**
 *
 * @author diazt
 */
public class ConnectionFactory {
    
    protected ConnectionFactory(){}

    public static I_Connection createConnection(HashMap<String, String> params, String nomConnection) {
        if (nomConnection.equals("Oracle")) {
            return new OracleConnection(params);
        } else if (nomConnection.equals("MySQL")) {
            return new MySQLConnection(params);
        }
        return null;
    }
}
