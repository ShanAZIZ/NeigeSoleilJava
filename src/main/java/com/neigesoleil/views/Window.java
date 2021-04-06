package com.neigesoleil.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window extends JFrame implements ActionListener {

    LoginPanel loginPanel;
    MainPanel mainPanel;
    static String fontName = "Helvetica";
    Font font = new Font("fontName", Font.PLAIN, 13);

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

    public void showLogin(){
        this.loginPanel = new LoginPanel(this.font);
        this.add(this.loginPanel);
        this.loginPanel.setVisible(true);
    }

    public void hidePanel(JPanel panel){
        panel.setVisible(false);
    }

    public void showMainPanel() {
        this.hidePanel(this.loginPanel);
        this.mainPanel = new MainPanel();
        this.add(this.mainPanel);
        this.mainPanel.setVisible(true);
    }

}
