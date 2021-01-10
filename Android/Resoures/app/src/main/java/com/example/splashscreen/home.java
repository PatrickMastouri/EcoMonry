package com.example.splashscreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import com.example.splashscreen.Fragments.CategorieFragment;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.splashscreen.Fragments.SalaireFragment;
import com.example.splashscreen.Models.user;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton addsalaire;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ImageView categorie,salaire,Sal,Prof,Cat,Reclamation,Logout;
    BottomNavigationView bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        drawerLayout = findViewById(R.id.dropdown);
        //navigationView = findViewById(R.id.nav_view);
        categorie = findViewById(R.id.imageView3);
        salaire = findViewById(R.id.salaire);


        Sal = findViewById(R.id.Sal);
        Prof = findViewById(R.id.Prof);
        Cat = findViewById(R.id.Cat);
        Reclamation = findViewById(R.id.Rec);
        addsalaire = findViewById(R.id.addsalaire);
        Logout=findViewById(R.id.Logout);

        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(home.this);
                builder.setTitle("Confirmation !").
                        setMessage("You sure, that you want to logout?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
/*
                                SharedPreferences preferences = getSharedPreferences("login", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.remove("isUserLogin");
                                editor.commit();

                                
 */
                                Intent i = new Intent(getApplicationContext(),
                                        Login.class);
                                startActivity(i);
                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });

        Sal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getSupportFragmentManager().beginTransaction().replace(R.id.container, new SalaireFragment()).commit();
            }
        });

        Prof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(home.this, Profile.class);
                startActivity(i);
               // getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment1()).commit();
            }
        });

        Cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new CategorieFragment()).commit();
            }
        });

        Reclamation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(home.this, Reclamation.class);
                startActivity(i);
                // getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment1()).commit();
            }
        });

        addsalaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(home.this, ProfileEdit.class);
                startActivity(i);
                // getSupportFragmentManager().beginTransaction().replace(R.id.container, new Fragment1()).commit();
            }
        });



        openFragment(SalaireFragment.newInstance("", ""));


//        navigationView.setNavigationItemSelectedListener(this);
        categorie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(home.this, Categorie.class);
                startActivity(i);

            }
        });


        salaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(home.this, Salaire.class);
                startActivity(i);

            }
        });

    }


    public void openFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /************************** toolbar louta ***********************/
    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.recyclerViewSalaire:
                            getSupportFragmentManager().beginTransaction().replace(R.id.container, new SalaireFragment()).commit();
                            return true;
                        case R.id.recyclerView:
                            System.out.println("ucu");

                            getSupportFragmentManager().beginTransaction().replace(R.id.container, new SalaireFragment()).commit();

                            return true;
                    }
                    return false;
                }
            };
    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Profile) {
            Toast.makeText(home.this, "Action clicked", Toast.LENGTH_LONG).show();

            Intent i = new Intent(home.this, Profile.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}