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
import com.example.splashscreen.Models.UserCallback;
import com.example.splashscreen.Models.user;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import static com.example.splashscreen.Models.URLs.URL;
public class UserService extends Application {
    private static UserService instance = null;
    String URI = "user";
    public RequestQueue requestQueue;
    private List<user> users = new ArrayList<>();

    /*-------------------------------------------INSTANCIATION-------------------------------------------*/

    private UserService(Context context)
    {
        requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized UserService getInstance(Context context)
    {
        if (null == instance)
            instance = new UserService(context);
        return instance;
    }

    //this is so you don't need to pass context each time
    public static synchronized UserService getInstance()
    {
        if (null == instance)
        {
            throw new IllegalStateException(UserService.class.getSimpleName() +
                    " is not initialized, call getInstance(...) first");
        }
        return instance;
    }

    /*-------------------------------------------LOGIN-------------------------------------------*/


    public void Login(String u, String p,final UserCallback callback)
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL+"login/"+u+"/"+p, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    if(response.has("object"))
                    {
                        callback.onSuccessLogin("something went wrong");
                    }
                    else {
                        user user = new user();
                        int id = Integer.parseInt(response.getString("id_user"));
                        user.setId_user((int) id);
                        int state = Integer.parseInt(response.getString("state"));
                        user.setNom(response.getString("firstName").toString());
                        user.setPrenom(response.getString("lastName").toString());
                        user.setUsername(response.getString("username").toString());
                        user.setEmail(response.getString("email").toString());
                        user.setRole(response.getString("role").toString());
                        user.setDate(response.getString("daten").toString().substring(0,10));
                        user.setPassword(response.getString("password").toString());
                        user.setAdress(response.getString("adress").toString());
                        user.setNum_tel(response.getString("phone").toString());
                        user.setState((int) state);

                        callback.onSuccessUser(user);

                    }


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

    /*-------------------------------------------Get Users-------------------------------------------*/

    public void Get(final UserCallback callback)
    {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL+URI, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                users.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject JObj = response.getJSONObject(i);

                        user user = new user();
                        int id = Integer.parseInt(JObj.getString("id_user"));
                        user.setId_user((int) id);
                        int state = Integer.parseInt(JObj.getString("state"));
                        user.setNom(JObj.getString("firstName").toString());
                        user.setPrenom(JObj.getString("lastName").toString());
                        user.setUsername(JObj.getString("username").toString());
                        user.setEmail(JObj.getString("email").toString());
                        user.setRole(JObj.getString("role").toString());
                        user.setDate(JObj.getString("daten").toString().substring(0,10));
                        user.setPassword(JObj.getString("password").toString());
                        user.setAdress(JObj.getString("adress").toString());
                        user.setNum_tel(JObj.getString("phone").toString());
                        user.setState((int) state);

                        users.add(user);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                callback.onSuccessUsers(users);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

    /*-------------------------------------------Get One User-------------------------------------------*/

    public void GetById(int id,final UserCallback callback)
    {JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, URL+URI+"/"+id  , null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            user user = new user();
            try {

                user.setNom(response.getString("firstName").toString());
                user.setUsername(response.getString("username").toString());
                user.setEmail(response.getString("email").toString());
                user.setDate(response.getString("daten").toString().substring(0, 10));
                user.setAdress(response.getString("adress").toString());
                user.setNum_tel(response.getString("phone").toString());


                callback.onSuccessUser(user);


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


    },new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            error.printStackTrace();
        }
    });
        requestQueue.add(request1);
    }

    /*-------------------------------------------Post One User-------------------------------------------*/

    public void Post(user o)
    {
        JSONObject postData = new JSONObject();
        try {
            postData.put("firstName", o.getNom());
            postData.put("lastName", o.getPrenom());
            postData.put("username", o.getUsername());
            postData.put("email", o.getEmail());
            postData.put("daten", o.getDate());
            postData.put("password", o.getPassword());
            postData.put("phone", o.getNum_tel());
            postData.put("role", o.getRole());
            postData.put("adress", o.getAdress());
            postData.put("state", o.getState());

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

    /*-------------------------------------------Delete One User-------------------------------------------*/

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

    /*-------------------------------------------Update One User-------------------------------------------*/

    public void Put(int id, user o)
    {
        JSONObject postData = new JSONObject();
        try {
            postData.put("firstName", o.getNom());
            postData.put("lastName", o.getPrenom());
            postData.put("username", o.getUsername());
            postData.put("email", o.getEmail());
            postData.put("daten", o.getDate());
            postData.put("password", o.getPassword());
            postData.put("phone", o.getNum_tel());
           // postData.put("role", o.getRole());
            postData.put("adress", o.getAdress());
           // postData.put("state", o.getState());


        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.PUT, URL+URI+"/"+id, postData, new Response.Listener<JSONObject>() {
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

    /*-------------------------------------------Get Users-------------------------------------------*/
