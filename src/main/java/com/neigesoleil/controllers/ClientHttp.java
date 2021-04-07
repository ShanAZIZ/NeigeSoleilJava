package com.neigesoleil.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {

    private static String url = "http://localhost:8000/";
    private static String tokenUrl = "api-token-auth/";
    private static HttpClient client = HttpClient.newBuilder().build();

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

    public static String getToken (Authentication auth) {
        System.out.println(auth.toString());
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
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            JsonNode tokenJson = JsonHandler.parse(response.body());
            return tokenJson.get("token").asText();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
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

    public static String postRequest (String url, String token, String values) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Token " + token)
                .POST(HttpRequest.BodyPublishers.ofString(values))
                .build();

        HttpResponse<String> response = ClientHttp.client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public static String putRequest (String url, String token, String values) throws Exception{
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Token " + token)
                .PUT(HttpRequest.BodyPublishers.ofString(values))
                .build();

        HttpResponse<String> response = ClientHttp.client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    public static String deleteRequest (String url, String token) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .header("Authorization", "Token " + token)
                .DELETE()
                .build();

        HttpResponse<String> response = ClientHttp.client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

}
