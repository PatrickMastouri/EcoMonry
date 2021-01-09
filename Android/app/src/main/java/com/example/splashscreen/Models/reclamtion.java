package com.example.splashscreen.Models;

public class reclamtion {
    private int id_rec;
    private String sujet;
    private String description;
    private int id_user;

    public reclamtion(int id_rec, String sujet, String description, int id_user) {
        this.id_rec = id_rec;
        this.sujet = sujet;
        this.description = description;
        this.id_user = id_user;
    }

    public reclamtion(String sujet, String description, int id_user) {

        this.sujet = sujet;
        this.description = description;
        this.id_user = id_user;
    }

    public reclamtion() {

    }

    public int getId_rec() {
        return id_rec;
    }

    public void setId_rec(int id_rec) {
        this.id_rec = id_rec;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "reclamtion{" +
                "id_rec=" + id_rec +
                ", sujet='" + sujet + '\'' +
                ", description='" + description + '\'' +
                ", id_user='" + id_user + '\'' +
                '}';
    }
}
