package com.example.splashscreen.Service;

import android.app.Application;
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.splashscreen.Models.Payment;
import com.example.splashscreen.Models.SalaireCallback;
import com.example.splashscreen.Models.categorie;
import com.example.splashscreen.Models.salaire;
import com.example.splashscreen.Models.URLs;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.splashscreen.Models.URLs.URL;

public class SalaireService  extends Application {

    private static SalaireService instance = null;
    String URI = "salaire";
    public RequestQueue requestQueue;
    private List<salaire> salaires = new ArrayList<>();

    private SalaireService(Context context)
    {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized SalaireService getInstance(Context context)
    {
        if (null == instance)
            instance = new SalaireService(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized SalaireService getInstance()
    {
        if (null == instance)
        {
            throw new IllegalStateException(SalaireService.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    /*-------------------------------------------Post Salary-------------------------------------------*/

    public void Post(salaire o)
    {
        JSONObject postData = new JSONObject();
        try {
            postData.put("salaire", o.getSalaire());
            postData.put("payment", o.getPayment());
            postData.put("id_user", o.getId_user());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URLs.URL+URI, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }

    /*-------------------------------------------Get Salaire------------------------------------------*/

    public void Get(int iduser,final SalaireCallback callback)
    {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL+URI+"/User/"+iduser, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                salaires.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject JObj = response.getJSONObject(i);
                        salaire sal = new salaire();
                        int id = Integer.parseInt(JObj.getString("id_sal"));
                        sal.setId_sal(id);
                        int idUser = Integer.parseInt(JObj.getString("id_user"));
                        sal.setId_user(idUser);
                        sal.setSalaire(JObj.getString("salaire"));
                        System.out.println("TESTING TESTING 1233"+(JObj.getString("payment")));
                           //ne marche pas
                       // Gson g = new Gson();

                        //jsonArray, listType
                        //Payment p = g.fromJson((JObj.getJSONArray("payment")).toString(), Payment.class);

                       // sal.setPayment(p);
                        System.out.println(Payment.valueOf(JObj.getString("payment")));

                        sal.setPayment(Payment.valueOf(JObj.getString("payment")));
                        salaires.add(sal);
                        System.out.println(sal.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                callback.onSuccessSalaires(salaires);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }


    public void Put(int sal_id, salaire s)
    {
        JSONObject postData = new JSONObject();
        try {
            postData.put("salaire", s.getSalaire());
            postData.put("payment", s.getPayment());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, URL+URI+"/"+sal_id, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }

    public void Delete(int id)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, URL+URI+"/"+id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                salaires.clear();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }



}
