package neigesoleil.controllers;

import neigesoleil.views.InputData;
import neigesoleil.views.Window;

public class NeigeSoleil {

    static Authentification controlAuthentification;

    public static void main(String[] args) {
        Window mainWindow = new Window();
        mainWindow.login();
        mainWindow.setVisible(true);
    }

    public static void createAuthenticate(String username, String password){
        controlAuthentification = new Authentification(username, password);
        // TODO: Verifier les credentials et si c'est oui, rendre la vue login non visible et passer a la vue suivante
        InputData.afficher(controlAuthentification.getUsername() + " " + controlAuthentification.getPassword()) ;
    }
}
