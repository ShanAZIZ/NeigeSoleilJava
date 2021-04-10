package com.neigesoleil.controllers;

import com.fasterxml.jackson.databind.JsonNode;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {

    private static String url = "http://localhost:8000/";
    private static String tokenUrl = "api-token-auth/";
    private static String userUrl = "api/user/";
    private static String userProfileUrl = "api/user-profile/";
    private static String profileUrl = "api/profile/";
    private static String contratUrl = "api/contrat/";
    private static String reservationUrl = "api/reservation/";
    private static String locationUrl = "api/location/";

    private static final HttpClient client = HttpClient.newBuilder().build();

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        ClientHttp.url = url;
    }

    public static String getTokenUrl() {
        return tokenUrl;
    }

    public static void setTokenUrl(String tokenUrl) {
        ClientHttp.tokenUrl = tokenUrl;
    }

    public static String getUserUrl() { return userUrl; }

    public static void setUserUrl(String userUrl) { ClientHttp.userUrl = userUrl; }

    public static String getUserProfileUrl() { return userProfileUrl; }

    public static void setUserProfileUrl(String getProfileUrl) { ClientHttp.userProfileUrl = getProfileUrl; }

    public static String getProfileUrl() { return profileUrl; }

    public static void setProfileUrl(String profileUrl) { ClientHttp.profileUrl = profileUrl; }

    public static String getContratUrl() {
        return contratUrl;
    }

    public static void setContratUrl(String contratUrl) {
        ClientHttp.contratUrl = contratUrl;
    }

    public static String getReservationUrl() {
        return reservationUrl;
    }

    public static void setReservationUrl(String reservationUrl) {
        ClientHttp.reservationUrl = reservationUrl;
    }

    public static String getLocationUrl() {
        return locationUrl;
    }

    public static void setLocationUrl(String locationUrl) {
        ClientHttp.locationUrl = locationUrl;
    }

    public static String getToken (Authentication auth) {
        String url = ClientHttp.url + ClientHttp.tokenUrl;

        // Requête
        HttpRequest tokenRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(auth.toString()))
                .build();

        HttpResponse<String> response = null;
        try {
            response = ClientHttp.client.send(tokenRequest, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            // e.printStackTrace();
        }
        try {
            JsonNode tokenJson = JsonHandler.parse(response.body());
            if(tokenJson.get("is_superuser").asBoolean()){
                return tokenJson.get("token").asText();
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return "";
    }

    public static String getRequest (String url, String token) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Token " + token)
                .GET()
                .build()
                ;
        HttpResponse<String> response = ClientHttp.client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    public static Boolean postRequest (String url, String token, String values) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Token " + token)
                .POST(HttpRequest.BodyPublishers.ofString(values))
                .build();

        try{
            HttpResponse<String> response = ClientHttp.client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 201 || response.statusCode() == 200;
        } catch (Exception e){
            // e.printStackTrace();
            return false;
        }
    }

    public static boolean putRequest (String url, String token, String values) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Token " + token)
                .PUT(HttpRequest.BodyPublishers.ofString(values))
                .build();

        HttpResponse<String> response;
        try {
            response = ClientHttp.client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200;
        } catch (Exception e) {
            // e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteRequest (String url, String token) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Token " + token)
                .DELETE()
                .build();

        HttpResponse<String> response;
        try {
            response = ClientHttp.client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 204;
        } catch (Exception e) {
            // e.printStackTrace();
            return false;
        }
    }

}
