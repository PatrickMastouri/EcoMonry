package com.example.splashscreen.Models;

import android.widget.Switch;

import com.example.splashscreen.Salaire;

public class sous_categorie {
    private int id_sc;
    private String name;
    private String periode;
    private String reminder;
    private String etat;
    private String montant;
    private String cat_id;
    //private int id_user;




    @Override
    public String toString() {
        return "sous_categorie{" +
                "id_sc=" + id_sc +
                ", name='" + name + '\'' +
                ", periode='" + periode + '\'' +
                ", reminder='" + reminder + '\'' +
                ", etat='" + etat + '\'' +
                ", montant='" + montant + '\'' +
                ", cat_id='" + cat_id + '\''  +
                '}';
    }

    public int getId_sc() {
        return id_sc;
    }
    /*

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

     */

    public sous_categorie(int id_sc, String name, String periode, String reminder, String etat, String montant, String cat_id) {
        this.id_sc = id_sc;
        this.name = name;
        this.periode = periode;
        this.reminder = reminder;
        this.etat = etat;
        this.montant = montant;
        this.cat_id = cat_id;

    }

    public sous_categorie( String name, String periode, String reminder, String etat, String montant, String cat_id) {

        this.name = name;
        this.periode = periode;
        this.reminder = reminder;
        this.etat = etat;
        this.montant = montant;
        this.cat_id = cat_id;

    }

/*
    public sous_categorie(String name, String periode, String reminder, String etat, String montant, String cat_id, int id_user) {

        this.name = name;
        this.periode = periode;
        this.reminder = reminder;
        this.etat = etat;
        this.montant = montant;
        this.cat_id = cat_id;
        this.id_user = id_user;

    }

 */



    public sous_categorie() { }

    public int getId_sc(int id) {
        return id_sc;
    }

    public void setId_sc(int id_sc) {
        this.id_sc = id_sc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }

    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }


}
