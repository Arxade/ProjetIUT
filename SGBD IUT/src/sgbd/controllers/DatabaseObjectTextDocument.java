/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.controllers;

import java.awt.Toolkit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Kazed
 */
public class DatabaseObjectTextDocument extends PlainDocument {
        private String value;
        
        public DatabaseObjectTextDocument() {
            addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                   if(!value.equals("")) {
                        int firstChar = value.charAt(0);
                        if((value.length() > 1 && value.contains("__")) || (firstChar > 47 && firstChar < 56)) System.out.println("Erreur de nommage de la table");
                   }
                   else {
                        System.out.println("Veuillez nommer la nouvelle table");
                        
                   }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    insertUpdate(e);
                }

                @Override
                public void changedUpdate(DocumentEvent e) {}
            });
        }
        
        @Override
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            if(str != null) {
                String res;
                int length = getLength();
                if (length == 0) {
                    res = str;
                } 
                else {
                    StringBuilder val = new StringBuilder(getText(0, length));
                    val.insert(offs, str);
                    res = val.toString();
                }
                if(isGoodDatabaseObject(res)) {
                    value = res;
                    super.insertString(offs, str, a);
                } 
                else {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        }
        
    @Override
    public void remove(int offset, int length) throws BadLocationException {
        try {
            StringBuilder sb = new StringBuilder(value);
            sb.delete(offset, offset + length);
            value = sb.toString();
            super.remove(offset, length);
        } 
        catch (NumberFormatException | BadLocationException e) {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public boolean isGoodDatabaseObject(String val) {
        if(val.length() < 129){
            Pattern pattern = Pattern.compile("\\W");
            Matcher matcher = pattern.matcher(val);
            return !matcher.find();
        }
        else return false;
    }
}
