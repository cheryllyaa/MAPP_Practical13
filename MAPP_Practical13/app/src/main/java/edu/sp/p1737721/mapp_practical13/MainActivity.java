package edu.sp.p1737721.mapp_practical13;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private RecyclerView recyclerView;
    ProductListAdapter mAdapter;
    JSONObject items = new JSONObject();
    // store RequestQueue is static to be shared in this app
    public static RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        queue = Volley.newRequestQueue(this);

        setupRecycler();
        connectToInternet();
    }
    void connectToInternet() {
        // instantiate the request queue
       //  RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://mapp2020-dc18d.firebaseio.com/products.json";

        // request a string response from the provided url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // display characters of the response string
                        Log.d(TAG, "Response is: " + response);

                        parseData(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "That didnt work!");
            }
        });
        // add the request to the RequestQueue
        queue.add(stringRequest);
    }
    void setupRecycler() {
        // create recycler view
        recyclerView = findViewById(R.id.recyclerview);
        // create mAdapter and supply data to be displayed
        mAdapter = new ProductListAdapter(this, items);
        // connect maAdapter with recycler view
        recyclerView.setAdapter(mAdapter);
        // give recycler view default layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

  // Add a floating action click handler for creating new entries.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddProduct.class);
                startActivity(intent);
            }
        });
    }
    void parseData(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            // once data is loaded, update recycler view
            mAdapter.setItems(jsonObject);
            mAdapter.notifyDataSetChanged();

        } catch (JSONException e) {
            Log.e(TAG, e.getMessage());
        }
    }
}
