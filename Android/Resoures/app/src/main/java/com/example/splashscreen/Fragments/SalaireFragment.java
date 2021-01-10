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
import com.example.splashscreen.AdapterSal;
import com.example.splashscreen.Models.CategorieCallback;
import com.example.splashscreen.Models.SalaireCallback;
import com.example.splashscreen.Models.categorie;
import com.example.splashscreen.Models.salaire;
import com.example.splashscreen.Models.user;
import com.example.splashscreen.R;
import com.example.splashscreen.Service.CategorieService;
import com.example.splashscreen.Service.SalaireService;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SalaireFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SalaireFragment extends Fragment {
    private RecyclerView recyclerView;
    AdapterSal myadpter;
    ArrayList<salaire> items;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SalaireFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SalaireFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SalaireFragment newInstance(String param1, String param2) {
        SalaireFragment fragment = new SalaireFragment();
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
        View view=inflater.inflate(R.layout.fragment_salaire, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewSalaire);
        SalaireService.getInstance(getContext());



        SharedPreferences userPref = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = userPref.getString("user", "");
        final user session = gson.fromJson(json, user.class);



        SalaireService.getInstance().Get(session.getId_user(),new SalaireCallback(){
            @Override
            public void onSuccessSalaire(salaire salaire) {

            }

            @Override
            public void onSuccessSalaires(List<salaire> salaire) {

                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                myadpter = new AdapterSal(view.getContext(),salaire);
                recyclerView.setAdapter(myadpter);
            }

            });



        return view;
    }
}