/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.connection;

import java.util.HashMap;

public class ConnectionFactory {
    
    protected ConnectionFactory(){}

    public static I_Connection createConnection(HashMap<String, String> params, String nomConnection) {
        switch (nomConnection) {
            case "Oracle":
                return new OracleConnection(params);
            case "MySQL":
                return new MySQLConnection(params);
            default:
                return null;
        }
    }
}
