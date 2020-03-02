package com.example.woolerin_test;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
   TextView tv1,tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
        tv1=findViewById(R.id.tv1);
        tv2=findViewById(R.id.tv2);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.prokerala.com/v1/astrology/kundli-matching?ayanamsa=1&bride_dob=2004-02-12T15:19:21%2B00:00&bride_coordinates=10.214747,78.097626&bridegroom_dob=2004-02-12T15:19:21%2B00:00&bridegroom_coordinates=10.214747,78.097626";

//        final JSONObject hashmap = new JSONObject();
//        try {
//            hashmap.put("ayanamsa", "1");
//            hashmap.put("bride_dob","2004-02-12T15:19:21+00:00");
//            hashmap.put("bride_coordinates", "10.214747,78.097626");
//            hashmap.put("bridegroom_dob","2004-02-12T15:19:21+00:00");
//            hashmap.put("bridegroom_coordinates","10.214747,78.097626");
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

        //tv1.setText(hashmap.toString());
// Request a string response from the provided URL.
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        JSONObject obj1= null;
                        JSONObject obj2= null;
                        try {
                            obj1 = response.getJSONObject("request");
                            obj2 = response.getJSONObject("response");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        tv1.setText("Request"+obj1);
                        tv1.setTextColor(Color.RED);
                        tv2.setText("Response"+obj2);
                        tv2.setTextColor(Color.BLUE);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("delhi",error.getMessage()+"");
            }
        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                HashMap<String, String> hashMap = new HashMap<String, String>();
//                hashMap.put("ayanamsa", "1");
//                hashMap.put("bride_dob","2004-02-12T15:19:21+00:00");
//                hashMap.put("bride_coordinates", "10.214747,78.097626");
//                hashMap.put("bridegroom_dob","2004-02-12T15:19:21+00:00");
//                hashMap.put("bridegroom_coordinates","10.214747,78.097626");
//                return hashMap;
//            };

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError{
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer e56769488793cea2c47c14d37a27b539af3f1710ea5b9efeec9c8c145dd59230");
                headers.put("Content-Type","application/json");
                headers.put("Accept","application/json");

                return headers;
            }
        };

// Add the request to the RequestQueue.
        queue.add(request);
    }
}
