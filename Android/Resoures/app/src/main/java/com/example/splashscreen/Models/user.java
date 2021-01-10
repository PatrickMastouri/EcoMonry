package com.example.splashscreen.Models;

import java.util.Date;

import retrofit2.converter.scalars.ScalarsConverterFactory;

public class user {

    private int id_user;
    private String nom;
    private String prenom;
    private String date;
    private String num_tel;
    private String Email;
    private String password;
    private String adress;
    private String username;
    private String role;
    private int state;

    public user() {
    }

    public user(int id_user, String nom, String prenom, String date, String num_tel, String email, String password, String adress, String username,String role,int state) {
        this.id_user = id_user;
        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.num_tel = num_tel;
        this.Email = email;
        this.password = password;
        this.adress = adress;
        this.username = username;
        this.role = role;
        this.state=state;
    }



    public user(String nom, String prenom, String date, String num_tel, String email, String password, String adress, String username,String role,int state) {

        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.num_tel = num_tel;
        this.Email = email;
        this.password = password;
        this.adress = adress;
        this.username = username;
        this.role = role;
        this.state=state;


    }

    public user(String nom, String prenom, String date, String num_tel, String email, String password, String adress, String username) {

        this.nom = nom;
        this.prenom = prenom;
        this.date = date;
        this.num_tel = num_tel;
        this.Email = email;
        this.password = password;
        this.adress = adress;
        this.username = username;

    }






    public user(String username, String password) {
        this.username = username;
        this.password = password;
    }



    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String  getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
