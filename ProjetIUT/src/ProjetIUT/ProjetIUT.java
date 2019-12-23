/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProjetIUT;

import ProjetIUT.forms.ConnectionPanel;
import ProjetIUT.forms.MainFrame;
import ProjetIUT.forms.VisualizationPanel;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author Arxade
 */
public class ProjetIUT {
    /*
     * @param args the command line arguments
     */
    public static void main(String[] args) {   
        //Initialisation des différents JPanel
        ConnectionPanel panel1 = new ConnectionPanel();
        panel1.setName("Connection");
        VisualizationPanel panel2 = new VisualizationPanel();
        panel2.setName("Visualization");
        
        //Initialisation du CardLayout avec les JPanel
        JPanel cards = new JPanel(new CardLayout());
        cards.add(panel1);
        cards.add(panel2);
        
        //Initialisation de la JFrame
        MainFrame frame = new MainFrame();
        frame.setContentPane(cards);
        frame.setCurrentCard();
        frame.pack();
        frame.setVisible(true);
        
        //Si les informations relatives à la connexion à la bdd ne sont pas 
        //trouvables dans le JSON, affiche le JDialog pour entrer les paramètres de l'URL
        if(!panel1.isURLSet()) {
            panel1.createParamsDialog().setVisible(true);
        }
    }

}