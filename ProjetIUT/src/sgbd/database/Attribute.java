/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.database;
/**
 *
 * @author Kazed
 */
public class Attribute {
    private String name;
    private String type;
    private int length;
    private boolean isNullable = true, isPrimaryKey = false, isUnique = false;
    private FK foreignKey = null;
    
    class FK {
        String rTable, rAttribute;
        
        private FK(String rT, String rA) {
            rTable = rT;
            rAttribute = rA;
        }
    }
    
    public Attribute(String n, String t, int le) {
        name = n;
        type = t;
        length = le;
    }
    
    public Attribute(String n, String t){
        name = n;
        type = t;
    }
    
    
    public Object[] toObject() {
        String fk = "";
        if(foreignKey != null) {
            fk = foreignKey.rTable + '(' + foreignKey.rAttribute + ')';
        }
        return new Object[]{name, type, length, isPrimaryKey, !isNullable, isUnique, fk};
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
    }
    
    public int getLength() {
        return length;
    }

    public void setLength(int le){
        length = le;
    }
    

    
    public boolean isNullable() {
        return isNullable;
    }
    
    public void isNullable(boolean b) {
        isNullable = b;
    }
    
    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }
     
    public void isPrimaryKey(boolean b) {
        isPrimaryKey = b;
        if(b) {
            isNullable = !b;
            isUnique = b;
        }
    }
    
    public void isPrimaryKeyJustBool(boolean b){
        isPrimaryKey = b;
    }
    
    
     public boolean isUnique() {
        return isUnique;
    }
     
    public void isUnique(boolean b) {
        isUnique = b;
    }
    
    public void foreignKey(String rT, String rA) {
        foreignKey = new FK(rT, rA);
    }
    
    public String[] isForeignKey(){
        
        if(foreignKey != null) return new String[]{foreignKey.rTable, foreignKey.rAttribute};
        else return new String[] {"NOTFOREIGNKEY"};
        
    }
}