package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.splashscreen.Models.*;
import com.example.splashscreen.Service.CategorieService;
import com.example.splashscreen.Service.SousCategorieService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.List;

public class SousCategorie extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<sous_categorie> sous_categories;
    AdapterSouCat myadpter;
    Button edit,delete,view;
    SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton addSoucat;
    //TextView categorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sous_categorie);


        SousCategorieService.getInstance(this);
        recyclerView = findViewById(R.id.recyclerView);
        edit = (Button) findViewById(R.id.edit);
        delete = (Button) findViewById(R.id.delete);
        view = (Button) findViewById(R.id.view);
        addSoucat = (FloatingActionButton) findViewById(R.id.addSoucat);
        swipeRefreshLayout = findViewById(R.id.swiperefresh);
       // categorie = (TextView) findViewById(R.id.categorie);


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*********************************************GET******************************************************************/
                Intent i = getIntent();
                String id = i.getStringExtra("cat_nome");
                SousCategorieService.getInstance().Get(id,new SouscategorieCallback(){


                    @Override
                    public void Souscat(sous_categorie sous_categorie) {

                    }

                    @Override
                    public void onSuccessrsou(List<sous_categorie> sous_categories) {
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        myadpter = new AdapterSouCat(getApplicationContext(),sous_categories);

                        recyclerView.setAdapter(myadpter);

                   }

                });

                swipeRefreshLayout.setRefreshing(false);
            }


        });


        addSoucat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = getIntent();
                String id = i.getStringExtra("cat_nome");
                Integer idcat = i.getIntExtra("Cat_id",-1);


                Intent F = new Intent(SousCategorie.this, AddSouCat.class);
                F.putExtra("cat_nome",id);
                F.putExtra("Cat_id",idcat);
                F.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                startActivity(F);

            }
        });
    }
}