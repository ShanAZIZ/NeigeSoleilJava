package neigesoleil.controllers;

public class Authentification {
    String username;
    String password;
    String token;

    public Authentification(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Authentification(String username, String password, String token) {
        this.username = username;
        this.password = password;
        this.token = token;
    }

    public void Authenticate(String username, String password){
        // TODO: Récupérer le token avec les paramètres par un POST
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
}
