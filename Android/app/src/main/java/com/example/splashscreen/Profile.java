package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.splashscreen.Models.UserCallback;
import com.example.splashscreen.Models.user;
import com.example.splashscreen.Service.UserService;
import com.google.gson.Gson;

import java.util.List;

public class Profile extends AppCompatActivity {

    ImageView edit;
    TextView tv_name,tv_address,username,date,email,phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        tv_name = findViewById(R.id.tv_name);
        tv_address = findViewById(R.id.tv_address);
        username = findViewById(R.id.username);
        date = findViewById(R.id.date);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        edit = findViewById(R.id.editProf);


        SharedPreferences userPref = getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = userPref.getString("user", "");
        final user session = gson.fromJson(json, user.class);
        int id=session.getId_user();

        UserService.getInstance().GetById(id, new UserCallback() {

            @Override
            public void onSuccessUser(user user) {

                tv_name.setText(user.getNom());
                tv_address.setText(user.getAdress());
                username.setText(user.getUsername());
                date.setText(user.getDate());
                email.setText(user.getEmail());
                phone.setText(user.getNum_tel());
            }

            @Override
            public void onSuccessUsers(List<user> users) {

            }

            @Override
            public void onSuccessLogin(String msg) {

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, ProfileEdit.class);
                startActivity(i);
            }
        });


    }
}