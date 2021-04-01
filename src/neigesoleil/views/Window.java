package neigesoleil.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {

    LoginPanel loginPanel = new LoginPanel();

    public Window() {

        // Fenêtre principale
        this.setTitle("Administration de Neige et Soleil");
        this.setBounds(200,200,800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(255, 255, 255));
        this.setLayout(null);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void login(){
        LoginPanel loginPanel = new LoginPanel();
        this.add(loginPanel);
        loginPanel.setVisible(true);
    }
}
