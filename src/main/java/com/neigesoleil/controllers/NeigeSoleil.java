package com.neigesoleil.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.neigesoleil.models.Profile;
import com.neigesoleil.models.User;
import com.neigesoleil.views.MyWindow;

import java.util.ArrayList;
import java.util.Iterator;

/*
 * Class : NeigeSoleil
 * Cette classe contient le main du projet
 * Elle gère l'affichage des différent panel de la class Window
 */
public class NeigeSoleil {

    private static Authentication auth;
    private static MyWindow mainWindow;

    public static void main(String[] args) {

        NeigeSoleil.mainWindow = new MyWindow();
        NeigeSoleil.mainWindow.showLogin();
        NeigeSoleil.mainWindow.setVisible(true);
    }

    public static Authentication getAuth() {
        return auth;
    }

    public static void setAuth(Authentication auth) {
        NeigeSoleil.auth = auth;
    }

    public static MyWindow getMainWindow() {
        return mainWindow;
    }

    public static void setMainWindow(MyWindow mainWindow) {
        NeigeSoleil.mainWindow = mainWindow;
    }

    public static Boolean authenticate(String username, String password) {
        try {
            NeigeSoleil.auth = new Authentication(username, password);
            NeigeSoleil.auth.setToken(ClientHttp.getToken(NeigeSoleil.auth));
            if(auth.getToken() != ""){
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    /***** USER *****/
    // TODO: Refactor des methodes
    public static ArrayList<User> getAllUsers () {
        ArrayList<User> allUsers = new ArrayList<>();
        try {
            String allUserString = ClientHttp.getRequest(ClientHttp.getUrl() + ClientHttp.getUserUrl(), auth.getToken());
            JsonNode allUserJson = JsonHandler.parse(allUserString);

            Iterator<JsonNode> it = allUserJson.elements();
            while(it.hasNext()) {
                User oneUser = JsonHandler.fromJson(it.next(), User.class);
                allUsers.add(oneUser);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    public static User getUser (int idUser) {
        try {
            String userString = ClientHttp.getRequest(ClientHttp.getUrl() + ClientHttp.getUserUrl()+ idUser + "/", auth.getToken());
            JsonNode node = JsonHandler.parse(userString);
            User unUser = JsonHandler.fromJson(node, User.class);
            return unUser;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Boolean addUser(User unUser) {
        JsonNode valueJson = JsonHandler.toJson(unUser);
        ((ObjectNode) valueJson).remove("id");
        try {
            ClientHttp.postRequest(ClientHttp.getUrl() + ClientHttp.getUserUrl(), auth.getToken(), String.valueOf(valueJson));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean updateUser(User unUser){
        JsonNode valueJson = JsonHandler.toJson(unUser);
        System.out.println(valueJson);
        try {
            ClientHttp.putRequest(ClientHttp.getUrl() + ClientHttp.getUserUrl()+unUser.getId()+"/", auth.getToken(), String.valueOf(valueJson));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean deleteUser(int userId){
        try {
            ClientHttp.deleteRequest(ClientHttp.getUrl()+ ClientHttp.getUserUrl()+ userId + "/", auth.getToken());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /***** PROFILES *****/
    public static ArrayList<Profile> getAllProfiles() {
        ArrayList<Profile> allProfiles = new ArrayList<>();
        try {
            String allProfileString = ClientHttp.getRequest(ClientHttp.getUrl() + ClientHttp.getGetProfileUrl(), auth.getToken());
            JsonNode allProfileJson = JsonHandler.parse(allProfileString);
            Iterator<JsonNode> it = allProfileJson.elements();
            while(it.hasNext()) {
                Profile oneProfile = JsonHandler.fromJson(it.next(), Profile.class);
                allProfiles.add(oneProfile);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allProfiles;
    }



}
