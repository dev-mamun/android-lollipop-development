package com.sitepoint.marvelmagic;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MainActivity extends Activity {

    Long tsLong = System.currentTimeMillis();
    String ts = tsLong.toString();
    String public_key = "dfa06d77bc9c4f9f0ed01337848247e3";
    String private_key = "28368556487f21005668a0ac6cebbf7886945528";
    String hash = getMD5(ts + private_key + public_key);
    String limit = "25";
    String api_url = "http://gateway.marvel.com/v1/public/characters?ts=" + ts + "&apikey=" + public_key + "&hash=" + hash + "&limit=" + limit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(), R.string.welcome_message, Toast.LENGTH_LONG).show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, api_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            JSONObject jsonData = jsonResponse.getJSONObject("data");
                            JSONArray jsonResults = jsonData.getJSONArray("results");

                            populateCharacters(jsonResults);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response: ", error.toString());
            }
        });

        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    public static String getMD5(String input) {
        try {
            Log.v("string - ", input);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void populateCharacters(JSONArray jsonArray) {
        ArrayList<Character> arrayOfCharacters = new ArrayList<Character>();
        CharacterAdapter adapter = new CharacterAdapter(this, arrayOfCharacters);
        ArrayList<Character> newCharacters = Character.fromJson(jsonArray);
        adapter.addAll(newCharacters);
        ListView listView = (ListView) findViewById(R.id.lvCharacters);
        listView.setAdapter(adapter);
    }
}
