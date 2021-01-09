package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.splashscreen.Models.categorie;
import com.example.splashscreen.Service.CategorieService;

public class CategorieEdit extends AppCompatActivity {
    EditText CatName,Totale;
    Button Edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie_edit);

        CatName=(EditText)findViewById(R.id.CatName);
        Totale =(EditText)findViewById(R.id.ToTale);
        Edit = (Button)findViewById(R.id.Edit);
        CategorieService.getInstance(this);

        Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = getIntent();

                Integer id = i.getIntExtra("id",-1);
                System.out.println("CURRENT ID : "+id);

                categorie newCat = new categorie(CatName.getText().toString(),Totale.getText().toString());

                CategorieService.getInstance().Put(id,newCat);
                Toast.makeText(CategorieEdit.this, "your cat  has been edited successfully", Toast.LENGTH_SHORT).show();


                Intent xyz = new Intent(CategorieEdit.this, Categorie.class);
                startActivity(xyz);

            }
        });
    }
}