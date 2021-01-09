package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.splashscreen.Models.Payment;
import com.example.splashscreen.Models.SalaireCallback;
import com.example.splashscreen.Models.salaire;
import com.example.splashscreen.Service.SalaireService;

import org.angmarch.views.NiceSpinner;

public class EditSalaire extends AppCompatActivity {

    EditText Nsalaire;
    NiceSpinner niceSpinnerXml;
    Button EditSalaire;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_salaire);

        Nsalaire=(EditText)findViewById(R.id.Nsalaire);
        niceSpinnerXml =(NiceSpinner)findViewById(R.id.niceSpinnerXml);
        niceSpinnerXml.setAdapter(new ArrayAdapter<Payment>(this, android.R.layout.simple_spinner_item, Payment.values()));
        EditSalaire=(Button)findViewById(R.id.EditSalaire);
        SalaireService.getInstance(this);

        EditSalaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = getIntent();

                Integer id = i.getIntExtra("id",-1);

                salaire sal = new salaire(Nsalaire.getText().toString(),(Payment) niceSpinnerXml.getSelectedItem());

                SalaireService.getInstance().Put(id,sal);
                Toast.makeText(EditSalaire.this, "your sal  has been edited successfully", Toast.LENGTH_SHORT).show();


                Intent xyz = new Intent(EditSalaire.this, home.class);
                startActivity(xyz);


            }
        });

    }
}