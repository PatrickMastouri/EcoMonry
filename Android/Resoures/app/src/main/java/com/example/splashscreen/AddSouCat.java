package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.splashscreen.Models.sous_categorie;
import com.example.splashscreen.Service.ReclamationService;
import com.example.splashscreen.Service.SousCategorieService;

public class AddSouCat extends AppCompatActivity {

    EditText name1,periode,reminder,montant;
    Switch etat;
    Button Ajout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sou_cat);
        name1 = findViewById(R.id.Name);
        periode = findViewById(R.id.periode);
        reminder = findViewById(R.id.reminder);
        montant = findViewById(R.id.montant);
        etat = findViewById(R.id.switch1);
        Ajout = findViewById(R.id.Ajout);
        SousCategorieService.getInstance(this);





        Intent name = getIntent();
        String id = name.getStringExtra("cat_nome");
        Integer idcat = name.getIntExtra("Cat_id",-1);
        System.out.println("cat ID : "+idcat);
        System.out.println("cat name : "+id);



        Ajout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etat.isChecked()){
                    sous_categorie S = new sous_categorie(name1.getText().toString(),
                            periode.getText().toString(),
                            reminder.getText().toString(),
                            "true",
                            montant.getText().toString(),idcat.toString());
                    SousCategorieService.getInstance().Post(S);

                }else {
                    sous_categorie S = new sous_categorie(name1.getText().toString(),
                            periode.getText().toString(),
                            reminder.getText().toString(),
                            "false",
                            montant.getText().toString(),idcat.toString());
                    SousCategorieService.getInstance().Post(S);
                }




                Toast.makeText(AddSouCat.this, "your cat has been added successfully", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(AddSouCat.this, SousCategorie.class);
                startActivity(i);



            }
        });



    }
}