package com.neigesoleil.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class User implements Serializable {
    private int id;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private Boolean is_proprietaire;
    private Profile userProfile;

    public User() {
    }

    public User(int id, String username, String first_name, String last_name, String email, Boolean isHote, Profile userProfile) {
        this.id = id;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.is_proprietaire = isHote;
        this.userProfile = userProfile;
    }

    public User(String username, String first_name, String last_name, String email, Boolean is_proprietaire) {
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.is_proprietaire = is_proprietaire;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty(value="is_proprietaire")
    public Boolean getProprietaire() {
        return is_proprietaire;
    }

    @JsonProperty(value="is_proprietaire")
    public void setProprietaire(Boolean proprietaire) {
        is_proprietaire = proprietaire;
    }

    public Profile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(Profile userProfile) {
        this.userProfile = userProfile;
    }
}
