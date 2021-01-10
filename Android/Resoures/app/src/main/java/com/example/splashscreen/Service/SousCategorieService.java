    package com.example.splashscreen.Service;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.splashscreen.Models.SouscategorieCallback;
import com.example.splashscreen.Models.*;
import com.example.splashscreen.SousCategorie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.splashscreen.Models.URLs.URL;

public class SousCategorieService extends Application {

    private static SousCategorieService instance = null;
    String URI = "souscat";
    public RequestQueue requestQueue;
    private List<sous_categorie> sousCategories = new ArrayList<>();


    private SousCategorieService(Context context)
    {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized SousCategorieService getInstance(Context context)
    {
        if (null == instance)
            instance = new SousCategorieService(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized SousCategorieService getInstance()
    {
        if (null == instance)
        {
            throw new IllegalStateException(SousCategorieService.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }




    /*-------------------------------------------Get Categorie-------------------------------------------*/

    public void Get(String catid ,final SouscategorieCallback callback)
    {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL+URI+"/User/"+catid,null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                sousCategories.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject JObj = response.getJSONObject(i);
                        sous_categorie rec = new sous_categorie();
                        int id = Integer.parseInt(JObj.getString("id_sc"));
                        rec.setId_sc(id);

                        rec.setCat_id(JObj.getString("Cat_id").toString());
                        rec.setName(JObj.getString("name").toString());
                        rec.setPeriode(JObj.getString("periode").toString());
                        rec.setReminder(JObj.getString("reminder").toString());
                        rec.setEtat(JObj.getString("etat").toString());
                        rec.setMontant(JObj.getString("montant").toString());



                        sousCategories.add(rec);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                callback.onSuccessrsou(sousCategories);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }


    /*-------------------------------------------Update One User-------------------------------------------*/

    public void Put(int id_sc, sous_categorie o)
    {
        JSONObject postData = new JSONObject();
        try {
            postData.put("name", o.getName());
            postData.put("periode", o.getPeriode());
            postData.put("reminder", o.getReminder());
            postData.put("etat", o.getEtat());
            postData.put("montant", o.getMontant());
            //postData.put("cat_id ", o.getCat_id());



        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, URL+URI+"/"+id_sc, postData, new Response.Listener<JSONObject>() {
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


    /*-------------------------------------------Get One sous category-------------------------------------------*/

    public void GetById(int id,final SouscategorieCallback callback)
    {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL+URI+id, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                sous_categorie rec = new sous_categorie();
                JSONObject JObj = null;
                try {

                    int id = Integer.parseInt(JObj.getString("id_sc"));
                    rec.getId_sc(id);
                    rec.setName(JObj.getString("name").toString());
                    rec.setPeriode(JObj.getString("periode").toString());
                    rec.setReminder(JObj.getString("reminder").toString());
                    rec.setEtat(JObj.getString("etat").toString());
                    rec.setMontant(JObj.getString("montant").toString());
                    rec.setCat_id(JObj.getString("Cat_id").toString());


                    callback.Souscat(rec);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }




    /*-------------------------------------------Delete sous cat-------------------------------------------*/

    public void Delete(int id_sc)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, URL+URI+"/"+id_sc, null, new Response.Listener<JSONObject>() {
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
    /*-------------------------------------------Post-------------------------------------------*/

    public void Post(sous_categorie o)
    {
        JSONObject postData = new JSONObject();
        try {

            postData.put("name", o.getName());
            postData.put("periode", o.getPeriode());
            postData.put("reminder", o.getReminder());
            postData.put("etat", o.getEtat());
            postData.put("montant", o.getMontant());
            postData.put("cat_id ", o.getCat_id());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL+URI, postData, new Response.Listener<JSONObject>() {
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

    /*********************************get date and content******************************/

    public void Getdata(int id,final SouscategorieCallback callback)
    {

        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, URL+URI+"/pick/"+id  , null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                sous_categorie sc = new sous_categorie();

                Log.d("array json", String.valueOf(response));
                try {


                    sc.setReminder(response.getString("reminder"));
                    sc.setPeriode(response.getString("periode"));
                    callback.Souscat(sc);

                    //callback.onSuccesscategories(categorie);

                } catch (JSONException e) {
                    Log.d("array json", "on resp");
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        error.printStackTrace();
                    }
                });
        requestQueue.add(request1);




    }




}


