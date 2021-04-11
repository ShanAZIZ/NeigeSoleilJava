package com.neigesoleil.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame implements ActionListener {

    // TODO: Logout Logic
    // TODO: Changement des URLS si besoin (En cas d'évolution de l'api)
    private LoginPanel loginPanel;
    private MainPanel mainPanel;

    public MyWindow() {
        // Fenêtre principale
        this.setTitle("Administration de Neige et Soleil");
        this.setBounds(500,500,800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        //this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void showLogin(){
        this.loginPanel = new LoginPanel();
        this.add(this.loginPanel);
    }

    public void showMainPanel() {
        this.loginPanel.setVisible(false);
        this.mainPanel = new MainPanel();
        this.add(this.mainPanel);
        this.mainPanel.setVisible(true);
    }

}
