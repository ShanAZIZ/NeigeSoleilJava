package com.neigesoleil.views;

import com.neigesoleil.controllers.ClientHttp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame implements ActionListener {

    // TODO: Logout Logic
    // TODO: Changement des URLS si besoin (En cas d'évolution de l'api)
    private LoginPanel loginPanel;
    private MainPanel mainPanel;
    private JMenuBar menuBar = new JMenuBar();
    private JMenu mnOption = new JMenu("Options");

    private JMenuItem itemChangeLien = new JMenuItem("Changer l'url");

    public MyWindow() {
        // Fenêtre principale
        this.setTitle("Administration de Neige et Soleil");
        this.setBounds(500,500,800,600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(800, 600));
        this.setLocationRelativeTo(null);
        this.menuBar.add(this.mnOption);
        this.mnOption.add(this.itemChangeLien);
        this.itemChangeLien.addActionListener(this);

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

        this.setJMenuBar(menuBar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.itemChangeLien) {
            ClientHttp.setUrl(JOptionPane.showInputDialog("(URL ACTUELLE : "+ ClientHttp.getUrl() +"). ENTRER UNE URL :"));
        }
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
