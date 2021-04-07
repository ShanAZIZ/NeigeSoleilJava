package com.neigesoleil.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserPanel extends JPanel implements ActionListener {

    private JPanel leftPanel = new JPanel();
    private JLabel lbTitle = new JLabel("Neige et Soleil");
    private JButton btnDetail = new JButton("Details");
    private JButton btnAjouter = new JButton("Ajouter");
    private JButton btnModifier = new JButton("Modifier");
    private JButton btnSupprimer = new JButton("Supprimer");

    public UserPanel() {

        this.setLayout(new BorderLayout());

        this.btnDetail.setForeground(Color.DARK_GRAY);
        this.btnAjouter.setForeground(Color.DARK_GRAY);
        this.btnModifier.setForeground(Color.DARK_GRAY);
        this.btnSupprimer.setForeground(Color.DARK_GRAY);

        this.leftPanel.setPreferredSize(new Dimension(200,0));
        this.leftPanel.setLayout(new GridLayout(15,1));
        this.leftPanel.add(this.btnDetail);
        this.leftPanel.add(this.btnAjouter);
        this.leftPanel.add(this.btnModifier);
        this.leftPanel.add(this.btnSupprimer);
        // this.leftPanel.setBackground(new Color(196,114,63));
        // this.leftPanel.setOpaque(true);
        this.leftPanel.setBorder(BorderFactory.createLineBorder (Color.BLACK, 2));
        this.add(leftPanel, BorderLayout.LINE_START);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
