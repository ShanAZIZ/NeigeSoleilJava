package com.neigesoleil;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.neigesoleil.controllers.Authentication;
import com.neigesoleil.controllers.ClientHttp;
import com.neigesoleil.controllers.JsonHandler;
import com.neigesoleil.controllers.NeigeSoleil;
import com.neigesoleil.models.Profile;
import com.neigesoleil.models.User;
import org.junit.jupiter.api.Test;

public class TestJava {

    Authentication authTest = new Authentication("admin","786Abasse110");
    String url = ClientHttp.getUrl() + "api/user/7/";
    String userString = "{ \"username\":\"test\"}";

    User unUser = new User("Test", "Shan", "AZIZ","shan.azizh@gmail.com ",false);
    Profile unProfile = new Profile("Test", "93200" ,"tEST", "0794816194", "fRFSDF" );

    @Test
    void parse() {
        String new_password = "Test123";
        JsonNode jsonPassword = null;
        try {
            jsonPassword = JsonHandler.parse(new_password);
            System.out.println(jsonPassword);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
    
    @Test
    void token () {
        System.out.println(ClientHttp.getToken(authTest));
    }

    @Test
    void getData () {
        try {
            System.out.println(ClientHttp.getRequest(url, ClientHttp.getToken(authTest)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void postData() {
        try {
            System.out.println(ClientHttp.postRequest(url, ClientHttp.getToken(authTest), userString));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void putData() {
        try {
            System.out.println(ClientHttp.putRequest(url, ClientHttp.getToken(authTest), userString));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteData() {
        try {
            System.out.println(ClientHttp.deleteRequest(url, ClientHttp.getToken(authTest)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void setPassword() {
        NeigeSoleil.authenticate("admin","786Abasse110");
        User unUser = NeigeSoleil.getUser(3);
        NeigeSoleil.editPassword(unUser.getId(), "Test123456");
    }

    @Test
    void addProfile(){
        NeigeSoleil.authenticate("admin","786Abasse110");
        User unUser = NeigeSoleil.getUser(8);
        unUser.setUserProfile(unProfile);
        System.out.println(unUser.userProfileToString());
        System.out.println(ClientHttp.postRequest(ClientHttp.getUrl() + ClientHttp.getProfileUrl(), NeigeSoleil.getAuth().getToken(), unUser.userProfileToString()));
    }

    @Test
    void getAllContrat() {
        NeigeSoleil.authenticate("admin","786Abasse110");
        NeigeSoleil.getAllContrats();
    }
}
