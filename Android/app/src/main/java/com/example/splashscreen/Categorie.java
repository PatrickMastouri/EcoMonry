package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.SearchView;

import com.example.splashscreen.Models.CategorieCallback;

import com.example.splashscreen.Models.user;
import com.example.splashscreen.Service.CategorieService;
import com.example.splashscreen.Models.categorie;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.List;

public class Categorie extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<categorie> categotries;
    AdapterCat myadpter;
    Button edit,delete;
    SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton addcat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorie);



        CategorieService.getInstance(this);
        recyclerView = findViewById(R.id.recyclerView);
        edit = (Button) findViewById(R.id.edit);
        delete = (Button) findViewById(R.id.delete);
        addcat = (FloatingActionButton) findViewById(R.id.addcat);
        swipeRefreshLayout = findViewById(R.id.swiperefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*********************************************GET******************************************************************/


                SharedPreferences userPref = getSharedPreferences("user", Context.MODE_PRIVATE);
                Gson gson = new Gson();
                String json = userPref.getString("user", "");
                final user session = gson.fromJson(json, user.class);


                CategorieService.getInstance().Get(session.getId_user(),new CategorieCallback(){

                    @Override
                    public void onSuccesscategories(categorie categorie) {

                    }

                    @Override
                    public void onSuccesscategories(List<categorie> c) {

                        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
                        myadpter = new AdapterCat(getApplicationContext(),c);
                        recyclerView.setAdapter(myadpter);
                    }
                });

                swipeRefreshLayout.setRefreshing(false);
            }


        });

        addcat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Categorie.this, Categorie_ajout.class);
                startActivity(i);

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.categorie, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println("adppppppppterrrrrrr********"+myadpter);
                myadpter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}