package com.neigesoleil.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JPanel implements ActionListener {

    private JLabel lbTitre = new JLabel("Neige et Soleil - Adminitration");

    public MainPanel () {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Forme du panel
        this.setBounds(0,0,800,600);
        this.setBackground(new Color(231, 231, 227));
        this.add(lbTitre);
    }
}
