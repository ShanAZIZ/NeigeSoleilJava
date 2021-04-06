package com.neigesoleil.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ClientHttp {

    private String url = "http://localhost:8000/";
    private HttpClient client;
    private String token;


    public ClientHttp () {
        this.client = HttpClient.newBuilder().build();
    }


    // Send a request to get the user token
    public Boolean loginRequest (Authentification auth) throws IOException, InterruptedException {
        System.out.println(auth.toString());
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.client + auth.getUrl()))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(auth.toString()))
                .build();

        HttpResponse<String> response =
                this.client.send(request, HttpResponse.BodyHandlers.ofString());

        // TODO: Verifier le retour de la requête si Token -> true, sinon -> false
        auth.setToken(response.body());
        System.out.println(response.body());
        return true;
    }



}
