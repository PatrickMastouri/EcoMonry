package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.splashscreen.Models.CategorieCallback;
import com.example.splashscreen.Models.categorie;
import com.example.splashscreen.Service.CategorieService;

import java.util.List;

public class CategoritDelails extends AppCompatActivity {
   TextView id_cat,catname,totale,userid;
   Button delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorit_delails);
        CategorieService.getInstance(this);
        id_cat = findViewById(R.id.id_cat);
        catname = findViewById(R.id.catname);
        totale = findViewById(R.id.totale);
        userid = findViewById(R.id.userid);
        delete = findViewById(R.id.button2);



        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent i = getIntent();

        Integer id = i.getIntExtra("id",-1);
        System.out.println("CURRENT ID : "+id);


        CategorieService.getInstance().GetById(id, new CategorieCallback() {


            @Override
            public void onSuccesscategories(categorie categorie) {
                Log.d("iddd", ""+categorie);
                id_cat.setText(String.valueOf(categorie.getCat_id()));
                catname.setText(categorie.getCat_Nome());
                totale.setText(categorie.getTolate());
                userid.setText(String.valueOf(categorie.getUser_user()));


            }

            @Override
            public void onSuccesscategories(List<categorie> categorie) {

            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




    }
}