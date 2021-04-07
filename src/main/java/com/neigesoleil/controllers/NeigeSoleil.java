package com.neigesoleil.controllers;

import com.neigesoleil.views.MyWindow;

/*
 * Class : NeigeSoleil
 * Cette classe contient le main du projet
 * Elle gère l'affichage des différent panel de la class Window
 */
public class NeigeSoleil {

    private static Authentication controlAuthentication;
    private static MyWindow mainWindow;

    public static void main(String[] args) {

        NeigeSoleil.mainWindow = new MyWindow();
        NeigeSoleil.mainWindow.showLogin();
        NeigeSoleil.mainWindow.setVisible(true);

    }

    public static Boolean authenticate(String username, String password) {
        try {
            NeigeSoleil.controlAuthentication = new Authentication(username, password);
            NeigeSoleil.controlAuthentication.setToken(ClientHttp.getToken(NeigeSoleil.controlAuthentication));
            NeigeSoleil.mainWindow.showMainPanel();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
