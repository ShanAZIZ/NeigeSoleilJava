package com.neigesoleil.controllers;

public class Authentication {
    String username;
    String password;
    String token;

    public Authentication(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    // Renvoie une chaine avec username et password sous syntaxe Json
    @Override
    public String toString() {
        return "{ \"username\" : \"" + this.username + "\","
                + "\"password\" : \"" + this.password + "\"}"
                ;
    }
}
