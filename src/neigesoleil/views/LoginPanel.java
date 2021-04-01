package neigesoleil.views;

import neigesoleil.controllers.NeigeSoleil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class LoginPanel extends JPanel implements ActionListener{

    private JButton btAnnuler = new JButton("Annuler");
    private JButton btConnexion = new JButton("Connexion");

    private JTextField txtUsername = new JTextField();
    private JTextField txtPassword = new JPasswordField();
    private JLabel lbUsername = new JLabel("Nom d'utilisateur");
    private JLabel lbpassword = new JLabel("Mot de passe");

    public LoginPanel(){

        // Forme du panel
        this.setBounds(50,50,700,450);
        this.setBackground(new Color(231, 231, 227));

        // TODO: Configurer la forme des éléments qui constituent le panel

        // Forme des label
        // Forme des fields
        // Forme des boutons

        this.add(lbUsername);
        this.add(txtUsername);
        this.add(lbpassword);
        this.add(txtPassword);
        this.add(btConnexion);
        this.add(btAnnuler);

        this.btConnexion.addActionListener(this);
        this.btAnnuler.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btAnnuler){
            this.txtUsername.setText("");
            this.txtPassword.setText("");
        }
        else if (e.getSource() == btConnexion) {
            String username;
            String password;
            username = this.txtUsername.getText();
            password = this.txtPassword.getText();
            NeigeSoleil.createAuthenticate(username, password);
        }
    }
}
