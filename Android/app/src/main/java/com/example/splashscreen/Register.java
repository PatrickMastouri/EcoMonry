package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.splashscreen.Models.user;
import com.example.splashscreen.Service.UserService;

public class Register extends AppCompatActivity {

    private EditText Fname,Lname,inputEmail,inputPassword,phone,date,Adress,username;
    private Button signup;

    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);



        UserService.getInstance(this);

        Fname = (EditText) findViewById(R.id.Fname);
        Lname = (EditText) findViewById(R.id.Lname);
        inputEmail = (EditText) findViewById(R.id.inputEmail);
        inputPassword = (EditText) findViewById(R.id.inputPassword);
        phone = (EditText) findViewById(R.id.phone);
        date = (EditText) findViewById(R.id.date);
        Adress = (EditText) findViewById(R.id.Adress);
        username = (EditText) findViewById(R.id.username);

        signup = (Button) findViewById(R.id.signup);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        // for name
        awesomeValidation.addValidation(this,R.id.Fname, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.Lname, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.Adress, RegexTemplate.NOT_EMPTY,R.string.invalid_adress);
        awesomeValidation.addValidation(this,R.id.username, RegexTemplate.NOT_EMPTY,R.string.invalid_username);
        //for email
        awesomeValidation.addValidation(this,R.id.inputEmail, Patterns.EMAIL_ADDRESS,R.string.invalid_Email);
        // for phone
        awesomeValidation.addValidation(this,R.id.phone,RegexTemplate.NOT_EMPTY,R.string.invalid_mobile);
        // For password
        awesomeValidation.addValidation(this,R.id.inputPassword,".{6,}",R.string.invalid_password);

        // For Date
        awesomeValidation.addValidation(this,R.id.date,RegexTemplate.NOT_EMPTY,R.string.invalid_date);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(awesomeValidation.validate()) {
                    user u = new user(Fname.getText().toString(),
                            Lname.getText().toString(),
                            date.getText().toString(),
                            phone.getText().toString(),
                            inputEmail.getText().toString(),
                            inputPassword.getText().toString(),
                            Adress.getText().toString(),
                            username.getText().toString(),
                            "user",
                            0
                    );

                    UserService.getInstance().Post(u);

                    Intent i = new Intent(Register.this, Login.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(),"not valid",Toast.LENGTH_SHORT).show();
                }
            }});
}}