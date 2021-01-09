package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.splashscreen.Models.*;
import com.example.splashscreen.Service.CategorieService;
import com.example.splashscreen.Service.UserService;
import com.google.gson.Gson;

public class Categorie_ajout extends AppCompatActivity {
    private EditText CatName,ToTale;
    private Button Ajout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie_ajout);

        CategorieService.getInstance(this);

        CatName = (EditText) findViewById(R.id.CatName);
        ToTale = (EditText) findViewById(R.id.ToTale);

        Ajout = (Button) findViewById(R.id.Ajout);


        Ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                SharedPreferences userPref = getSharedPreferences("user", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = userPref.getString("user", "");
                final user session = gson.fromJson(json, user.class);

                categorie u = new categorie(CatName.getText().toString(), ToTale.getText().toString(),session.getId_user());

                CategorieService.getInstance().Post(u);

/*


                SharedPreferences CatPref = getSharedPreferences("categorie", Context.MODE_PRIVATE);
                SharedPreferences.Editor categorieEditor = CatPref.edit();
                Gson gsoncat = new Gson();
                String jsoncat = gson.toJson(u);
                categorieEditor.putString("user", json);
                categorieEditor.commit();

                System.out.println("***************="+categorieEditor.);


 */

                Toast.makeText(Categorie_ajout.this, "your cat has been added successfully", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Categorie_ajout.this, home.class);
                startActivity(i);

            }
        });






    }
}