package com.example.splashscreen.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.splashscreen.AdapterCat;
import com.example.splashscreen.Models.CategorieCallback;
import com.example.splashscreen.Models.categorie;
import com.example.splashscreen.Models.user;
import com.example.splashscreen.R;
import com.example.splashscreen.Service.CategorieService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;


public class CategorieFragment extends Fragment {
    private RecyclerView recyclerView;
    AdapterCat myadpter;
    ArrayList<categorie> items;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategorieFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CategorieFragment newInstance(String param1, String param2) {
        CategorieFragment fragment = new CategorieFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categorie, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        CategorieService.getInstance(getContext());

        SharedPreferences userPref = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = userPref.getString("user", "");
        final user session = gson.fromJson(json, user.class);


        CategorieService.getInstance().Get(session.getId_user(),new CategorieCallback(){

            @Override
            public void onSuccesscategories(categorie categorie) {

            }

            @Override
            public void onSuccesscategories(List<categorie> c) {
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                myadpter = new AdapterCat(view.getContext(),c);
                recyclerView.setAdapter(myadpter);
                System.out.println("Fragment******="+recyclerView);

            }
        });
        return view;
    }
}