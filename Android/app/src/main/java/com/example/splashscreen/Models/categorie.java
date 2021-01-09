package com.example.splashscreen.Models;

import javax.net.ssl.SSLSession;

public class categorie {
    private  int cat_id;
    private String Cat_Nome;
    private String Tolate;
    private int User_user;

    public categorie(int cat_id, String cat_Nome, String tolate, int user_user) {
        this.cat_id = cat_id;
        this.Cat_Nome = cat_Nome;
        this.Tolate = tolate;
        this.User_user = user_user;
    }



    public categorie(String cat_Nome, String tolate, int user_user) {
        Cat_Nome = cat_Nome;
        Tolate = tolate;
        User_user = user_user;
    }





    public categorie( String cat_Nome, String tolate) {

        Cat_Nome = cat_Nome;
        Tolate = tolate;

    }




    public categorie(String cat_Nome) {

        Cat_Nome = cat_Nome;

    }

    public categorie() {

    }


    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_Nome() {
        return Cat_Nome;
    }

    public void setCat_Nome(String cat_Nome) {
        Cat_Nome = cat_Nome;
    }

    public String getTolate()
    {
        return Tolate;
    }

    public void setTolate(String tolate) {
        Tolate = tolate;
    }

    public int getUser_user()
    {
        return User_user;
    }

    public void setUser_user(int user_user)
    {
        User_user = user_user;
    }

    @Override
    public String toString() {
        return "categorie{" +
                "cat_id=" + cat_id +
                ", Cat_Nome='" + Cat_Nome + '\'' +
                ", Tolate='" + Tolate + '\'' +
                ", User_user='" + User_user + '\'' +
                '}';
    }
}
