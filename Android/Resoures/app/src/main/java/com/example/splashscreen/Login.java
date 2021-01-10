package com.example.splashscreen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.bumptech.glide.request.RequestOptions;
import com.example.splashscreen.Models.UserCallback;
import com.example.splashscreen.Models.user;
import com.example.splashscreen.Service.UserService;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;


public class Login extends AppCompatActivity {

    private TextView gotoRegister;
    private LoginButton loginbutton;
    private CallbackManager callbackManager;
    private static final String EMAIL = "email";
    Button SignIn;
    EditText userName, Password;
    public static user session = new user();
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        UserService.getInstance(this);
        userName = (EditText) findViewById(R.id.inputEmail);
        Password = (EditText) findViewById(R.id.inputPassword);
        SignIn = (Button) findViewById(R.id.btnLogin);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        // for name
        awesomeValidation.addValidation(this,R.id.inputEmail, RegexTemplate.NOT_EMPTY,R.string.invalid_Email);
        //awesomeValidation.addValidation(this,R.id.inputPassword,".{6,}",R.string.invalid_password);

        loginbutton=(LoginButton) findViewById(R.id.login_button);
        callbackManager = CallbackManager.Factory.create();

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserService.getInstance().Login(userName.getText().toString(), Password.getText().toString(), new UserCallback() {
                    @Override
                    public void onSuccessUser(user user) {


                        SharedPreferences preferences = getSharedPreferences("Login", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("isUserLogin", true);
                        editor.commit();


                        SharedPreferences userPref = getSharedPreferences("user", Context.MODE_PRIVATE);
                        SharedPreferences.Editor userEditor = userPref.edit();
                        Gson gson = new Gson();
                        String json = gson.toJson(user);
                        userEditor.putString("user", json);
                        userEditor.commit();




                        if(awesomeValidation.validate()){


                            if (user.getState() == 1) {
                                session = user;
                                if (user.getRole().equals("user")) {


                                    Intent u = new Intent(Login.this, home.class);
                                    startActivity(u);
                                } else if (user.getRole().equals("admin")) {
                                    Intent m = new Intent(Login.this, Admin.class);
                                    startActivity(m);
                                } else {
                                    System.out.println("*********jdjjdjdjd*******");
                                }
                            } else {

                                Toast.makeText(getApplicationContext(), "Activate ur account", Toast.LENGTH_SHORT).show();
                                System.out.println("**********************************HNNNNNE");
                            }
                        }


                        else {
                            Toast.makeText(getApplicationContext(),"not valid",Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onSuccessUsers(List<user> users) {
                    }
                    @Override
                    public void onSuccessLogin(String msg) {

                        Toast.makeText(getApplicationContext(), "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
                        System.out.println("Incorrect Username or Password");
                    }
                });
            }
        });


        loginbutton.setReadPermissions(Arrays.asList("email","username"));

        loginbutton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });


        gotoRegister = (TextView) findViewById(R.id.gotoRegister);
        gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        callbackManager.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    AccessTokenTracker tokenTracker = new AccessTokenTracker() {
        @Override
        protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                 if(currentAccessToken==null){

                     Toast.makeText(Login.this, "user logged out", Toast.LENGTH_SHORT).show();
                 }
                 else {
                     //loaduserPofile(currentAccessToken);



                 }
        }
    };
    private void loaduserPofile(AccessToken newAccessToken){

        GraphRequest request = GraphRequest.newMeRequest(newAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {



                    
                    RequestOptions requestOptions = new RequestOptions();
                    requestOptions.dontAnimate();

                    Intent intent = new Intent(Login.this, home.class);
                    startActivity(intent);





            }
        });

        Bundle parametrs = new Bundle();
        parametrs.putString("fields","first_name,last_name,email,id");
        request.setParameters(parametrs);
        request.executeAsync();

        Intent intent = new Intent(Login.this, home.class);
        startActivity(intent);

    }
}