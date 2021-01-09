package com.example.splashscreen.Models;

import java.util.List;

public interface SalaireCallback {
    void onSuccessSalaire(salaire salaire);
    void onSuccessSalaires(List<salaire> salaires);

}
