package com.neigesoleil;

import com.neigesoleil.controllers.Authentication;
import com.neigesoleil.controllers.ClientHttp;
import org.junit.jupiter.api.Test;

public class TestJava {

    Authentication authTest = new Authentication("admin","786Abasse110");
    String url = ClientHttp.getUrl() + "api/user/4/";
    String userString = "{ \"username\":\"test\"}";

    
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
}
