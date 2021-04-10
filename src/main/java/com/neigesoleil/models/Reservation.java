package com.neigesoleil.models;

public class Reservation {

    private int id;
    private int user;
    private int propriete;
    private String status_reservation;
    private String date_reservation;
    private String date_debut_sejour;
    private String date_fin_sejour;

    public Reservation() {
    }

    public Reservation(int user, int propriete, String status, String date_debut_sejour, String date_fin_sejour) {
        this.user = user;
        this.propriete = propriete;
        this.status_reservation = status;
        this.date_debut_sejour = date_debut_sejour;
        this.date_fin_sejour = date_fin_sejour;
    }

    public Reservation(int id, int user, int propriete, String status, String date_reservation, String date_debut_sejour, String date_fin_sejour) {
        this.id = id;
        this.user = user;
        this.propriete = propriete;
        this.status_reservation = status;
        this.date_reservation = date_reservation;
        this.date_debut_sejour = date_debut_sejour;
        this.date_fin_sejour = date_fin_sejour;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getStatus_reservation() {
        return status_reservation;
    }

    public void setStatus_reservation(String status_reservation) {
        this.status_reservation = status_reservation;
    }

    public int getPropriete() {
        return propriete;
    }

    public void setPropriete(int propriete) {
        this.propriete = propriete;
    }

    public String getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(String date_reservation) {
        this.date_reservation = date_reservation;
    }

    public String getDate_debut_sejour() {
        return date_debut_sejour;
    }

    public void setDate_debut_sejour(String date_debut_sejour) {
        this.date_debut_sejour = date_debut_sejour;
    }

    public String getDate_fin_sejour() {
        return date_fin_sejour;
    }

    public void setDate_fin_sejour(String date_fin_sejour) {
        this.date_fin_sejour = date_fin_sejour;
    }
}
