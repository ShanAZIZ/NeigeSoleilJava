package com.neigesoleil.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.neigesoleil.models.Contrat;
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
            return !auth.getToken().equals("");
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
            // e.printStackTrace();
        }
        return allUsers;
    }

    public static ArrayList<User> getAllProprietaire() {
        ArrayList<User> allProp = NeigeSoleil.getAllUsers();
        allProp.removeIf(unUser -> !unUser.getProprietaire());
        return allProp;
    }

    public static User getUser (int idUser) {
        try {
            String userString = ClientHttp.getRequest(ClientHttp.getUrl() + ClientHttp.getUserUrl()+ idUser + "/", auth.getToken());
            JsonNode node = JsonHandler.parse(userString);
            return JsonHandler.fromJson(node, User.class);
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    public static Boolean addUser(User unUser) {
        JsonNode valueJson = JsonHandler.toJson(unUser);
        ((ObjectNode) valueJson).remove("id");
        return ClientHttp.postRequest(ClientHttp.getUrl() + ClientHttp.getUserUrl(), auth.getToken(), String.valueOf(valueJson));
    }

    public static Boolean updateUser(User unUser){
        JsonNode valueJson = JsonHandler.toJson(unUser);
        return ClientHttp.putRequest(ClientHttp.getUrl() + ClientHttp.getUserUrl()+unUser.getId()+"/", auth.getToken(), String.valueOf(valueJson));
    }

    public static void deleteUser(int userId){
        ClientHttp.deleteRequest(ClientHttp.getUrl()+ ClientHttp.getUserUrl()+ userId + "/", auth.getToken());
    }

    public static Boolean editPassword(int userId, String new_password){
        new_password = "{ \"new_password\":\""+new_password+"\" }";
        return  ClientHttp.postRequest(ClientHttp.getUrl() + ClientHttp.getUserUrl()+userId+"/"+"set_password/", auth.getToken(), new_password);
    }

    /***** PROFILES *****/
    public static Profile getProfile(int userId){
        try {
            String userString = ClientHttp.getRequest(ClientHttp.getUrl() + ClientHttp.getUserProfileUrl() + userId + "/", auth.getToken());
            JsonNode node = JsonHandler.parse(userString);
            return JsonHandler.fromJson(node, Profile.class);
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    public static Boolean addProfil(User unUser) {
        return ClientHttp.postRequest(ClientHttp.getUrl() + ClientHttp.getProfileUrl(), auth.getToken(), unUser.userProfileToString());
    }

    public static Boolean updateProfile(User unUser){
        int profileId = unUser.getUserProfile().getId();
        JsonNode node = JsonHandler.toJson(unUser.getUserProfile());
        ((ObjectNode) node).put("user", unUser.getId());
        return ClientHttp.putRequest(ClientHttp.getUrl() + ClientHttp.getProfileUrl() + profileId + "/", auth.getToken(), String.valueOf(node));
    }

    /***** CONTRATS *****/
    public static ArrayList<Contrat> getAllContrats (){
        ArrayList<Contrat> allContrats = new ArrayList<>();
        try {
            String allContratString = ClientHttp.getRequest(ClientHttp.getUrl() + ClientHttp.getContratUrl(), auth.getToken());
            JsonNode allContratJson = JsonHandler.parse(allContratString);

            Iterator<JsonNode> it = allContratJson.elements();
            while(it.hasNext()) {
                Contrat unContrat = JsonHandler.fromJson(it.next(), Contrat.class);
                allContrats.add(unContrat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allContrats;
    }

    public static Boolean addContrat(Contrat unContrat){
        JsonNode valueJson = JsonHandler.toJson(unContrat);
        return ClientHttp.postRequest(ClientHttp.getUrl() + ClientHttp.getContratUrl(), auth.getToken(), String.valueOf(valueJson));
    }

    public static Boolean updateContrat(Contrat unContrat){
        int contratId = unContrat.getId();
        JsonNode node = JsonHandler.toJson(unContrat);
        return ClientHttp.putRequest(ClientHttp.getUrl() + ClientHttp.getContratUrl() + contratId + "/", auth.getToken(), String.valueOf(node));
    }

    public static void DeleteContrat(int idContrat){
        ClientHttp.deleteRequest(ClientHttp.getUrl()+ ClientHttp.getContratUrl()+ idContrat + "/", auth.getToken());
    }

    public static Contrat getContrat(int idContrat){
        try {
            String userString = ClientHttp.getRequest(ClientHttp.getUrl() + ClientHttp.getContratUrl()+ idContrat + "/", auth.getToken());
            JsonNode node = JsonHandler.parse(userString);
            return JsonHandler.fromJson(node, Contrat.class);
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }



}
