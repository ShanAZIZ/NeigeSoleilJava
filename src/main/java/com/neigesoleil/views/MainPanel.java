package com.neigesoleil.views;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private JTabbedPane tabbedPane = new JTabbedPane();
    private UserPanel userPanel = new UserPanel();
    private ContratPanel contratPanel = new ContratPanel();

    public MainPanel() {
        this.setBounds(0, 0, 800, 600);
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new BorderLayout());
        this.tabbedPane.setBackground(Color.WHITE);
        this.setOpaque(true);
        this.tabbedPane.addTab("Utilisateurs", null, userPanel,
                "Gérer les utilisateurs");
        this.tabbedPane.addTab("Contrats", null, contratPanel,
                "Gerer les contrats avec les propriétaires");

        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        this.add(tabbedPane);
    }
}
