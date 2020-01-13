/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sgbd.forms.cardPanels;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import sgbd.connection.DatabaseConnection;
import sgbd.forms.dialogs.ConnectionParamsPanel;
import sgbd.json.ConnectionDataJSON;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import sgbd.connection.MySQLConnection;
import sgbd.connection.OracleConnection;
import sgbd.controllers.Controller;
import sgbd.forms.MainFrame;

/**
 *
 * @author Kazed
 */
public class ConnectionPanel extends javax.swing.JPanel {
    private final ConnectionDataJSON json  = new ConnectionDataJSON();
    private Controller controller;

    /**
     * Creates new form FormConnect
     */
    public ConnectionPanel() {
        initComponents(); 
    }
    
    public boolean isURLSet() {
      return json.isParametered();
    }

    public void setComponentsParams(Font f) {
        for(Component comp : lpnContainer.getComponents()) {
            boolean setFont = true;
            switch(comp.getClass().getSimpleName()) {
                case "JButton":
                    if(comp.getName().equals("btnConnectionParams")) setFont = false;
                    comp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    break;
                case "JComboBox":
                    comp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    break;
                default:
                    break;
            }
            if(setFont)comp.setFont(f);
        }
    }

    public void showParamsDialog() {
        final JDialog dialog = new JDialog();
        dialog.setTitle("Paramètres de l'URL");
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ConnectionParamsPanel panel = new ConnectionParamsPanel(json.getParams());
        panel.setComponentsParams(getFont());
        ActionListener btnActionListener = (ActionEvent e) -> {
            if(e.getSource() == panel.getButton("confirm")) {
                json.setParam("Host", panel.getInput("Host").getText());
                json.setParam("Port", panel.getInput("Port").getText());
                json.setParam("Database", panel.getInput("Database").getText());
                json.save();
            }
            dialog.dispose();
        };
        panel.getButton("confirm").addActionListener(btnActionListener);
        panel.getButton("cancel").addActionListener(btnActionListener);
        dialog.setContentPane(panel);
        dialog.setResizable(false);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lpnContainer = new javax.swing.JLayeredPane();
        lblUser = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        btnConnectionParams = new javax.swing.JButton();
        btnConnect = new javax.swing.JButton();
        lblSGBD = new javax.swing.JLabel();
        cbxSGBD = new javax.swing.JComboBox<>();
        txtPassword = new javax.swing.JPasswordField();

        setName("connectionPanel"); // NOI18N

        lblUser.setLabelFor(txtUser);
        lblUser.setText("Utilisateur");

        lblPassword.setLabelFor(txtPassword);
        lblPassword.setText("Mot de passe");

        txtUser.setPreferredSize(new java.awt.Dimension(96, 20));

        btnConnectionParams.setIcon(new javax.swing.ImageIcon(getClass().getResource("/sgbd/img/settings.png"))); // NOI18N
        btnConnectionParams.setToolTipText("Paramètres de l'URL");
        btnConnectionParams.setBorder(null);
        btnConnectionParams.setContentAreaFilled(false);
        btnConnectionParams.setName("btnConnectionParams"); // NOI18N
        btnConnectionParams.setPreferredSize(new java.awt.Dimension(32, 32));
        btnConnectionParams.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectionParamsActionPerformed(evt);
            }
        });

        btnConnect.setText("Se connecter");
        btnConnect.setName("btnConnect"); // NOI18N
        btnConnect.setPreferredSize(new java.awt.Dimension(105, 32));
        btnConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConnectActionPerformed(evt);
            }
        });

        lblSGBD.setLabelFor(cbxSGBD);
        lblSGBD.setText("SGBD");

        cbxSGBD.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MySQL", "Oracle" }));

        txtPassword.setPreferredSize(new java.awt.Dimension(96, 20));

        lpnContainer.setLayer(lblUser, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(lblPassword, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(txtUser, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(btnConnectionParams, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(btnConnect, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(lblSGBD, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(cbxSGBD, javax.swing.JLayeredPane.DEFAULT_LAYER);
        lpnContainer.setLayer(txtPassword, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpnContainerLayout = new javax.swing.GroupLayout(lpnContainer);
        lpnContainer.setLayout(lpnContainerLayout);
        lpnContainerLayout.setHorizontalGroup(
            lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpnContainerLayout.createSequentialGroup()
                .addComponent(btnConnectionParams, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(lpnContainerLayout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblUser)
                    .addComponent(lblPassword)
                    .addComponent(lblSGBD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxSGBD, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lpnContainerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        lpnContainerLayout.setVerticalGroup(
            lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lpnContainerLayout.createSequentialGroup()
                .addComponent(btnConnectionParams, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSGBD)
                    .addComponent(cbxSGBD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUser)
                    .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(lpnContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(btnConnect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lpnContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(lpnContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        if(json.getSGBD() != null) cbxSGBD.setSelectedItem(json.getSGBD());
    }// </editor-fold>//GEN-END:initComponents

    private void btnConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectActionPerformed
        String sgbd = cbxSGBD.getSelectedItem().toString();
        DatabaseConnection co;
        //Connexion à la BDD en fonction du SGBD choisi
        switch(sgbd) {
            case "MySQL" :
                co = new MySQLConnection(json.getParams());
                break;
            default :
                co = new OracleConnection(json.getParams());
        }
        controller = new Controller(co);

        //Si la connexion est effectuée, sauvegarde le SGBD dans le JSON
        //et switche sur le JPanel VisualizationPanel
        if(controller.tryConnect(json, txtUser.getText(), String.valueOf(txtPassword.getPassword()))) {
            txtPassword.setText("");
            json.setParam("SGBD", sgbd);
            json.save();
            VisualizationPanel panel = (VisualizationPanel)((MainFrame)SwingUtilities.getWindowAncestor(this)).getCardPanel("next");
            panel.loadPanel(controller);
        }
    }//GEN-LAST:event_btnConnectActionPerformed

    private void btnConnectionParamsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConnectionParamsActionPerformed
        showParamsDialog();
    }//GEN-LAST:event_btnConnectionParamsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConnect;
    private javax.swing.JButton btnConnectionParams;
    private javax.swing.JComboBox<String> cbxSGBD;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblSGBD;
    private javax.swing.JLabel lblUser;
    private javax.swing.JLayeredPane lpnContainer;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
