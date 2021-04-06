package com.neigesoleil.controllers;

import com.neigesoleil.views.Window;
import java.io.IOException;

/*
 * Class : NeigeSoleil
 * Cette classe contient le main du projet
 * Elle gère l'affichage des différent panel de la class Window
 */
public class NeigeSoleil {

    private static Authentification controlAuthentification;
    private static ClientHttp client;
    private static Window mainWindow;

    public static void main(String[] args) {

        // Instanciation du client HTTP
        NeigeSoleil.client = new ClientHttp();

        NeigeSoleil.mainWindow = new Window();
        NeigeSoleil.mainWindow.showLogin();
        NeigeSoleil.mainWindow.setVisible(true);
    }

    public static void authenticate(String username, String password) throws IOException, InterruptedException {
        controlAuthentification = new Authentification(username, password);
        // TODO: Verifier les credentials et si c'est oui, rendre la vue login non visible et passer a la vue suivante
        if(NeigeSoleil.client.loginRequest(controlAuthentification)){
            // TODO: Rendre invisible le login et passer a la vue suivante
            NeigeSoleil.mainWindow.showMainPanel();
        }
        else {
            // TODO: Renvoyer une erreur d'authentification
        }
    }
}
