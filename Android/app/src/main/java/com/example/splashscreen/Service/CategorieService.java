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
import com.example.splashscreen.Models.CategorieCallback;
import com.example.splashscreen.Models.UserCallback;
import com.example.splashscreen.Models.categorie;
import com.example.splashscreen.Models.salaire;
import com.example.splashscreen.Models.user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.splashscreen.Models.URLs.URL;

public class CategorieService extends Application {

    private static CategorieService instance = null;
    String URI = "category";
    public RequestQueue requestQueue;
    private List<categorie> categories = new ArrayList<>();


    private CategorieService(Context context)
    {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized CategorieService getInstance(Context context)
    {
        if (null == instance)
            instance = new CategorieService(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized CategorieService getInstance()
    {
        if (null == instance)
        {
            throw new IllegalStateException(CategorieService.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }




    /*-------------------------------------------Get Categorie-------------------------------------------*/

    public void Get(int userid ,final CategorieCallback callback)
    {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL+URI+"/User/"+userid, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                categories.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        categorie categorie = new categorie();

                        JSONObject JObj = response.getJSONObject(i);
                        //int*********
                        int id = Integer.parseInt(JObj.getString("Cat_id"));
                        categorie.setCat_id(id);
                        //int******************
                        int User_id = Integer.parseInt(JObj.getString("User_id"));
                        categorie.setUser_user(User_id);
                        //Strings
                        categorie.setTolate(JObj.getString("Totale").toString());
                        categorie.setCat_Nome(JObj.getString("Cat_Nome").toString());
                        categories.add(categorie);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                callback.onSuccesscategories(categories);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }


    /*-------------------------------------------Update One Categorie------------------------------------------*/

    public void Put(int cat_id, categorie o)
    {
        JSONObject postData = new JSONObject();
        try {
            postData.put("Totale", o.getTolate());
            postData.put("Cat_Nome", o.getCat_Nome());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, URL+URI+"/"+cat_id, postData, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                categories.clear();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }


    /*-------------------------------------------Get One category-------------------------------------------*/

    public void GetById(int id,final CategorieCallback callback)
    {

        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, URL+URI+"/"+id  , null, new Response.Listener<JSONObject>() {


            @Override
            public void onResponse(JSONObject response) {
                categorie categorie = new categorie();

                Log.d("array json", String.valueOf(response));
                try {
                    int id = Integer.parseInt(response.getString("Cat_id"));
                    categorie.setCat_id(id);
                    int User_id = Integer.parseInt(response.getString("User_id"));
                    categorie.setUser_user(User_id);
                    categorie.setTolate(response.getString("Totale"));
                    categorie.setCat_Nome(response.getString("Cat_Nome"));

                    callback.onSuccesscategories(categorie);

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

    /*-------------------------------------------Delete Categorie-------------------------------------------*/

    public void Delete(int id)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, URL+URI+"/"+id, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                categories.clear();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }




    /*-------------------------------- post cat --------------------------------------*/
    public void Post(categorie o)
    {
        JSONObject postData = new JSONObject();
        try {

            postData.put("Cat_Nome", o.getCat_Nome());
            postData.put("Totale", o.getTolate());
            postData.put("User_id", o.getUser_user());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL+URI+"/create", postData, new Response.Listener<JSONObject>() {
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


}


