package com.neigesoleil.views;

import com.neigesoleil.controllers.NeigeSoleil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginPanel extends JPanel implements ActionListener{

    // Les deux panel qui composent le panel principal
    private JPanel credentialPanel = new JPanel();
    private JPanel buttonPanel = new JPanel();

    // Boutons inclus dans les panel
    private JButton btAnnuler = new JButton("Annuler");
    private JButton btConnexion = new JButton("Connexion");

    // Champs d'insertions du panel
    private JTextField txtUsername = new JTextField();
    private JTextField txtPassword = new JPasswordField();

    // Label du panel
    private JLabel lbTitre = new JLabel("Neige et Soleil - Adminitration");
    private JLabel lbUsername = new JLabel("Nom d'utilisateur");
    private JLabel lbPassword = new JLabel("Mot de passe");

    // Font du projet
    private Font font;

    public LoginPanel(Font font){

        // Les font du panel
        this.font = font; // Font principal
        Font titleFont = new Font(Window.fontName, Font.BOLD, 50); // Font du titre

        // Forme du panel
        this.setBounds(0,0,800,600);
        this.setBackground(new Color(231, 231, 227));

        this.credentialPanel.setBounds(400,600, 400,400);
        this.credentialPanel.setLayout(new GridLayout(4,0));

        // TODO: Configurer la forme des éléments qui constituent le panel

        // Forme des label
        lbTitre.setBounds(150, 300, 520, 30);
        lbTitre.setFont(titleFont);
        // Forme des fields
        // Forme des boutons



        this.credentialPanel.add(lbUsername);
        this.credentialPanel.add(txtUsername);
        this.credentialPanel.add(lbPassword);
        this.credentialPanel.add(txtPassword);

        this.add(lbTitre);
        this.add(credentialPanel);

        this.add(btConnexion);
        this.add(btAnnuler);


        this.btConnexion.addActionListener(this);
        this.btAnnuler.addActionListener(this);

        this.credentialPanel.setVisible(true);
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
            try {
                NeigeSoleil.authenticate(username, password);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }
}
