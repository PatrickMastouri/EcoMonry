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

import com.example.splashscreen.Models.user;
import com.example.splashscreen.Service.UserService;
import com.google.gson.Gson;

public class ProfileEdit extends AppCompatActivity {
    EditText Fname,Lname,inputEmail,inputPassword,phone,date,Adress,username;
    Button EditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);


        UserService.getInstance(this);

        Fname = (EditText) findViewById(R.id.Fname);
        Lname = (EditText) findViewById(R.id.Lname);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        phone = (EditText) findViewById(R.id.phone);
        date = (EditText) findViewById(R.id.date);
        Adress = (EditText) findViewById(R.id.Adress);
        username = (EditText) findViewById(R.id.username);

        EditProfile = (Button) findViewById(R.id.EditProfile);


        EditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user u = new user(Fname.getText().toString(),
                        Lname.getText().toString(),
                        date.getText().toString(),
                        phone.getText().toString(),
                        inputEmail.getText().toString(),
                        inputPassword.getText().toString(),
                        Adress.getText().toString(),
                        username.getText().toString());



                SharedPreferences userPref = getSharedPreferences("user", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = userPref.getString("user", "");
                final user session = gson.fromJson(json, user.class);
                int id = session.getId_user();

                UserService.getInstance().Put(id,u);
                Toast.makeText(ProfileEdit.this, "your account  has been edited successfully", Toast.LENGTH_SHORT).show();

                Intent xyz = new Intent(ProfileEdit.this, Profile.class);
                startActivity(xyz);
            }
        });

    }
}