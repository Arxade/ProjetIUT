/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.database;

import java.util.ArrayList;

/**
 *
 * @author Kazed
 */
public class Table {
    private final String name;
    private ArrayList<Attribute> attributes = new ArrayList<>();
    
    public Table(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public ArrayList<Attribute> attributes() {
        return attributes;
    }
    
    @Override
    public String toString() {
        return name;
    }
}