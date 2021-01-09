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
import com.example.splashscreen.Service.ReclamationService;
import com.google.gson.Gson;

public class ReclamationAjout extends AppCompatActivity {

    private EditText sujet,description;
    private Button AddRec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation_ajout);
        ReclamationService.getInstance(this);

        sujet = (EditText) findViewById(R.id.sujet);
        description = (EditText) findViewById(R.id.description);
        AddRec = (Button) findViewById(R.id.AddRec);


        AddRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences userPref = getSharedPreferences("user", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = userPref.getString("user", "");
                final user session = gson.fromJson(json, user.class);

                reclamtion rec = new reclamtion(sujet.getText().toString(),description.getText().toString(),session.getId_user());
                ReclamationService.getInstance().Post(rec);
                Toast.makeText(ReclamationAjout.this, "your rec has been added successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(ReclamationAjout.this, home.class);
                startActivity(i);

            }});

    }
}