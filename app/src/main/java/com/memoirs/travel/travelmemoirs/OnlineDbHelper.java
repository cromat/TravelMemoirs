package com.memoirs.travel.travelmemoirs;

import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by mat on 22.10.2015..
 */
public class OnlineDbHelper {

    private final static String LOG_TAG = OnlineDbHelper.class.getSimpleName();

    ArrayList<String> emails = new ArrayList<>();

    public ArrayList<String> getUserEmails(){
        return emails;
    }

    public OnlineDbHelper() {
    }



    /**
     * Dodaje korisnika u bazu
     */

    public void addUserToDatabase(final String email, final String photoUrl) {
        // Tag za prekid zahtjeva
        String tag_string_req = "req_add_user";

        //Spajanje na url skripte
        StringRequest strReq = new StringRequest(Request.Method.POST,
                OnlineDbConfig.URL_ADD_USER, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(LOG_TAG, "addUser Response" + response.toString());
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        Log.d(LOG_TAG, "Korisnik je dodan u bazu!");
                    } else {
                        //Greška
                        Log.d(LOG_TAG, "Greška pri dodavanju korisnika!");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            // Ispisivanje greške u log-u
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(LOG_TAG, "Add Error: " + error.getMessage());
                Log.d(LOG_TAG, "No internet connection!");
            }
        }) {
            // Mapa preko koje se šalju vrijednosti u skriptu
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("image", photoUrl);
                return params;
            }
        };
        //Postavlja zahtjev za spajanje na skriptu
        OnlineDbContoller.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}

