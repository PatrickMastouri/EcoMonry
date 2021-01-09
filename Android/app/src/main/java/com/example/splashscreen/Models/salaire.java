package com.example.splashscreen.Models;

public class salaire {

    private int id_sal;
    private  String salaire;

    private  Payment payment;
    private int id_user;

    public salaire(int id_sal, String salaire, Payment payment, int id_user) {
        this.id_sal = id_sal;
        this.salaire = salaire;
        this.payment = payment;
        this.id_user = id_user;
    }


    public salaire(String salaire, Payment payment, int id_user) {

        this.salaire = salaire;
        this.payment = payment;
        this.id_user = id_user;
    }



    public salaire(String salaire, Payment payment) {

        this.salaire = salaire;
        this.payment = payment;
    }





    public salaire() {

    }

    public int getId_sal() {
        return id_sal;
    }

    public void setId_sal(int id_sal) {
        this.id_sal = id_sal;
    }

    public String getSalaire() {
        return salaire;
    }

    public void setSalaire(String salaire) {
        this.salaire = salaire;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "Salaire{" +
                "id_sal=" + id_sal +
                ", salaire='" + salaire + '\'' +
                ", payment=" + payment +
                ", id_user='" + id_user + '\'' +
                '}';
    }
}
