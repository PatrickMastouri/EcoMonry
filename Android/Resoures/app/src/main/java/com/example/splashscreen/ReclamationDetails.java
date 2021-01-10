package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.splashscreen.Models.ReclamtaionCallback;
import com.example.splashscreen.Models.reclamtion;
import com.example.splashscreen.Service.ReclamationService;

import java.util.List;

public class ReclamationDetails extends AppCompatActivity {
    TextView sujet,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation_details);
        ReclamationService.getInstance(this);
        sujet = findViewById(R.id.sujet);
        description = findViewById(R.id.description);

        Intent i = getIntent();
        Integer id = i.getIntExtra("id",-1);
        System.out.println("CURRENT ID : "+id);

        ReclamationService.getInstance().GetById(id, new ReclamtaionCallback() {

            @Override
            public void onSuccessRec(reclamtion reclamtion) {
                sujet.setText(reclamtion.getSujet());
                description.setText(reclamtion.getDescription());
            }

            @Override
            public void onSuccessrRec(List<reclamtion> reclamtions) {

            }
        });




    }
}