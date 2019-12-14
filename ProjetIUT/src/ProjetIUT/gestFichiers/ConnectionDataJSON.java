/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetIUT.gestFichiers;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Paul
 */
public class ConnectionDataJSON {
    private String sgbd;
    private HashMap<String, String> params = new HashMap();
    
    public ConnectionDataJSON() {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader("connectionData.json"));
            JSONObject data = (JSONObject)obj;
            sgbd = (String)data.get("SGBD");
            params.put("Host", (String)data.get("Host"));
            params.put("Port", (String)data.get("Port"));
            params.put("Database", (String)data.get("Database"));
        } catch (IOException | ParseException e) {
            System.err.println(e);
        }
    }
    
    public HashMap<String, String> getParams() {
        return params;
    }
    
    public String getSGBD() {
        return sgbd;
    }
    
    public void setParam(String param, String value) {
        if(param.equals("SGBD")) {
            sgbd = value;
        }
        else {
            params.put(param, value);
        }
    }
    
    public void save() {
        try {
            JSONObject data = new JSONObject();
            data.put("SGBD", sgbd);
            data.put("Host", params.get("Host"));
            data.put("Port", params.get("Port"));
            data.put("Database", params.get("Database"));
            FileWriter file = new FileWriter("connectionData.json");
            file.write(data.toJSONString());
            file.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
