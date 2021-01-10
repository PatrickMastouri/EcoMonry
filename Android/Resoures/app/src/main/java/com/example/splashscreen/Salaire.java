package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.splashscreen.Models.Payment;
import com.example.splashscreen.Models.salaire;
import com.example.splashscreen.Models.user;
import com.example.splashscreen.Service.SalaireService;
import com.google.gson.Gson;

import org.angmarch.views.NiceSpinner;

public class Salaire extends AppCompatActivity {

EditText salaire,userid;
NiceSpinner Spiner;
Button add;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_salaire);
        SalaireService.getInstance(this);

        salaire = (EditText)findViewById(R.id.salaire);


        NiceSpinner mySpinner = (NiceSpinner) findViewById(R.id.niceSpinnerXml);

        mySpinner.setAdapter(new ArrayAdapter<Payment>(this, android.R.layout.simple_spinner_item, Payment.values()));


        add=(Button)findViewById(R.id.addSalaire);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences userPref = getSharedPreferences("user", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = userPref.getString("user", "");
                final user session = gson.fromJson(json, user.class);
                final Context context;
                context = v.getContext();

               salaire sa = new salaire (salaire.getText().toString(), (Payment) mySpinner.getSelectedItem(),session.getId_user());

                SalaireService.getInstance(context).Post(sa);
                Toast.makeText(Salaire.this, "your Salaire has been added successfully", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Salaire.this, home.class);
                startActivity(i);
            }
        });
    }
}