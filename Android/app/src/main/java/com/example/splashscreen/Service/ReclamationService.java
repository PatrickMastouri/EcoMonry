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
import com.example.splashscreen.Models.ReclamtaionCallback;
import com.example.splashscreen.Models.reclamtion;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.example.splashscreen.Models.URLs.URL;

public class ReclamationService extends Application {

    private static ReclamationService instance = null;
    String URI = "reclamation";
    public RequestQueue requestQueue;
    private List<reclamtion> reclamtions = new ArrayList<>();


    private ReclamationService(Context context)
    {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized ReclamationService getInstance(Context context)
    {
        if (null == instance)
            instance = new ReclamationService(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized ReclamationService getInstance()
    {
        if (null == instance)
        {
            throw new IllegalStateException(ReclamationService.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }




    /*-------------------------------------------Get Categorie-------------------------------------------*/

    public void Get(final ReclamtaionCallback callback)
    {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL+URI, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                reclamtions.clear();
                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject JObj = response.getJSONObject(i);
                        reclamtion rec = new reclamtion();
                        int id = Integer.parseInt(JObj.getString("id_rec"));
                        rec.setId_rec(id);
                        int iduser = Integer.parseInt(JObj.getString("id_user"));
                        rec.setId_user(iduser);
                        rec.setSujet(JObj.getString("sujet").toString());
                        rec.setDescription(JObj.getString("description").toString());
                        reclamtions.add(rec);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                callback.onSuccessrRec(reclamtions);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }







    public void Get(int userid,final ReclamtaionCallback callback)
    {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL+URI+"/User/"+userid, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                reclamtions.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject JObj = response.getJSONObject(i);
                        reclamtion rec = new reclamtion();
                        int id = Integer.parseInt(JObj.getString("id_rec"));
                        rec.setId_rec(id);
                        int iduser = Integer.parseInt(JObj.getString("id_user"));
                        rec.setId_user(iduser);
                        rec.setSujet(JObj.getString("sujet").toString());
                        rec.setDescription(JObj.getString("description").toString());
                        reclamtions.add(rec);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                callback.onSuccessrRec(reclamtions);
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

    public void Put(int id_rec, reclamtion o)
    {
        JSONObject postData = new JSONObject();
        try {
            postData.put("sujet", o.getSujet());
            postData.put("description", o.getDescription());



        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, URL+URI+"/"+id_rec, postData, new Response.Listener<JSONObject>() {
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


    /*-------------------------------------------Get Rec------------------------------------------*/

    public void GetById(int id,final ReclamtaionCallback callback)
    {

         JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, URL+URI+"/"+id  , null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                reclamtion rec = new reclamtion();

                try {

                    int id = Integer.parseInt(response.getString("id_rec"));
                    rec.setId_rec(id);
                    rec.setSujet(response.getString("sujet"));
                    rec.setDescription(response.getString("description"));
                    int id_user = Integer.parseInt(response.getString("id_user"));
                    rec.setId_user(id_user);
                    callback.onSuccessRec(rec);

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




    /*-------------------------------------------Delete Reclamation-------------------------------------------*/

    public void Delete(int id)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.DELETE, URL+URI+"/"+id, null, new Response.Listener<JSONObject>() {
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

    public void Post(reclamtion o)
    {
        JSONObject postData = new JSONObject();
        try {
            postData.put("sujet", o.getSujet());
            postData.put("description", o.getDescription());
            postData.put("id_user", o.getId_user());

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

}


