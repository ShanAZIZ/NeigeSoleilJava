package com.neigesoleil.views;

import com.neigesoleil.controllers.NeigeSoleil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class LoginPanel extends JPanel implements ActionListener, MouseListener {

    private JLabel lbNeigeSoleil = new JLabel("NEIGE SOLEIL");
    private JLabel lbTitre = new JLabel("ADMINISTRATION");
    private JLabel lbUsername = new JLabel("Nom d'utilisateur");
    private JTextField txtUsername = new JTextField();
    private JLabel lbPassword = new JLabel("Mot de passe");
    private JPasswordField txtPassword = new JPasswordField();
    private JButton btnConnexion = new JButton("Connexion");
    private JButton btnAnnuler = new JButton("Annuler");

    public LoginPanel(){
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);

        this.lbNeigeSoleil.setBounds(0,0,200,100);
        lbNeigeSoleil.setForeground(Color.WHITE);
        lbNeigeSoleil.setHorizontalAlignment( SwingConstants.CENTER );
        lbNeigeSoleil.setVerticalAlignment(SwingConstants.CENTER);
        lbNeigeSoleil.setBackground(Color.DARK_GRAY);
        lbNeigeSoleil.setOpaque(true);
        lbNeigeSoleil.setFont(lbNeigeSoleil.getFont().deriveFont(25,25));
        lbNeigeSoleil.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        this.add(lbNeigeSoleil);

        this.lbTitre.setBounds(200,0,600,100);
        lbTitre.setForeground(Color.WHITE);
        lbTitre.setHorizontalAlignment( SwingConstants.CENTER );
        lbTitre.setVerticalAlignment(SwingConstants.CENTER);
        lbTitre.setBackground(new Color(196,114,63));
        lbTitre.setOpaque(true);
        lbTitre.setFont(lbTitre.getFont().deriveFont(Font.BOLD,25));
        this.add(lbTitre);

        this.lbUsername.setBounds(250,180,300,30);
        lbUsername.setForeground(Color.WHITE);
        lbUsername.setHorizontalAlignment( SwingConstants.CENTER );
        lbUsername.setVerticalAlignment(SwingConstants.CENTER);
        lbUsername.setFont(lbUsername.getFont().deriveFont(Font.BOLD,20));
        this.add(lbUsername);

        this.txtUsername.setBounds(250,230,300,30);
        this.add(txtUsername);

        this.lbPassword.setBounds(250,280,300,30);
        lbPassword.setForeground(Color.WHITE);
        lbPassword.setHorizontalAlignment( SwingConstants.CENTER );
        lbPassword.setVerticalAlignment(SwingConstants.CENTER);
        lbPassword.setFont(lbPassword.getFont().deriveFont(Font.BOLD,20));
        this.add(lbPassword);

        this.txtPassword.setBounds(250,330,300,30);
        this.add(txtPassword);

        this.btnConnexion.setBounds(200,390,190,40);
        this.btnConnexion.setForeground(Color.WHITE);
        this.btnConnexion.setBackground(Color.DARK_GRAY);
        this.btnConnexion.setBorder(BorderFactory.createLineBorder (Color.WHITE, 2));
        this.btnConnexion.setOpaque(true);
        this.btnConnexion.addActionListener(this);
        this.btnConnexion.addMouseListener(this);
        this.add(btnConnexion);

        this.btnAnnuler.setBounds(410,390,190,40);
        this.btnAnnuler.setForeground(Color.WHITE);
        this.btnAnnuler.setBackground(new Color(196,114,63));
        this.btnAnnuler.setBorder(BorderFactory.createLineBorder (Color.WHITE, 2));
        this.btnAnnuler.setOpaque(true);
        this.btnAnnuler.addActionListener(this);
        this.btnAnnuler.addMouseListener(this);
        this.add(btnAnnuler);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnAnnuler) {
            this.txtUsername.setText("");
            this.txtPassword.setText("");
        }
        else if(e.getSource()==btnConnexion){
            String username = txtUsername.getText();
            String password = txtPassword.getText();
            if(!NeigeSoleil.authenticate(username, password)) {
                JOptionPane.showMessageDialog(this,
                        "Erreur de connexion, Verifiez vos identifiants, ou changez les URL");
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnAnnuler) {
            this.btnAnnuler.setBackground(new Color(180,100,55));
        }
        else if (e.getSource() == btnConnexion) {
            this.btnConnexion.setBackground(Color.LIGHT_GRAY);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == btnAnnuler) {
            this.btnAnnuler.setBackground(new Color(196,114,63));
        }
        else if (e.getSource() == btnConnexion) {
            this.btnConnexion.setBackground(Color.DARK_GRAY);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
