package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.splashscreen.Models.*;
import com.example.splashscreen.Service.CategorieService;
import com.example.splashscreen.Service.ReclamationService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Reclamation extends AppCompatActivity {
    private RecyclerView recyclerView;
    AdapterRec myadpter;
    ArrayList<reclamtion> items;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation);
        ReclamationService.getInstance(this);
        recyclerView = findViewById(R.id.Reclamation);

        floatingActionButton = findViewById(R.id.add);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Reclamation.this, ReclamationAjout.class);
                startActivity(i);

            }
        });

        ReclamationService.getInstance().Get(new ReclamtaionCallback() {
            @Override
            public void onSuccessRec(reclamtion reclamtion) {

            }

            @Override
            public void onSuccessrRec(List<reclamtion> c) {

                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                myadpter = new AdapterRec(getApplicationContext(),c);
                recyclerView.setAdapter(myadpter);

            }
        });
    }
}