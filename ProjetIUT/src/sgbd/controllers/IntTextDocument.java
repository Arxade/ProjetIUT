/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.controllers;

import java.awt.Toolkit;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Kazed
 */
public class IntTextDocument extends PlainDocument {
        private int minimum, maximum, value;
        
        public IntTextDocument(int min, int max) {
            minimum = min;
            maximum = max;
            addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if (value > 65535) {
                        System.out.println("erreur de port");
                    }
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
 
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
                if(isGoodNumber(res)) {
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
            value = Integer.parseInt(getText(0, length));
            super.remove(offset, length);
        } 
        catch (NumberFormatException | BadLocationException e) {
            Toolkit.getDefaultToolkit().beep();
        }
    }

    public boolean isGoodNumber(String val) {
        boolean b = false;
        try {
            int intVal = Integer.parseInt(val);
            if(val.length() < 6 && Integer.signum(intVal) != -1 && !val.contains("+")) {
                b = !b;
            }
        }   
        catch(NumberFormatException e) {}
        return b;
    }
}
