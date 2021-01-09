package com.example.splashscreen.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.splashscreen.AdapterRec;
import com.example.splashscreen.AdapterUsers;
import com.example.splashscreen.R;
import com.example.splashscreen.Models.*;
import com.example.splashscreen.Service.ReclamationService;
import com.example.splashscreen.Service.UserService;

import java.util.ArrayList;
import java.util.List;


public class ReclamationFragment extends Fragment {


    private RecyclerView recyclerView;
    AdapterRec myadpter;
    ArrayList<reclamtion> items;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReclamationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReclamationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReclamationFragment newInstance(String param1, String param2) {
        ReclamationFragment fragment = new ReclamationFragment();
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reclamation, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewReclamation);
        ReclamationService.getInstance(getContext());

        ReclamationService.getInstance().Get(new ReclamtaionCallback() {
            @Override
            public void onSuccessRec(reclamtion reclamtion) {

            }

            @Override
            public void onSuccessrRec(List<reclamtion> c) {

                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                myadpter = new AdapterRec(view.getContext(), c);
                recyclerView.setAdapter(myadpter);
                System.out.println("**********************************************");
            }

            });
        return view;
    }
}