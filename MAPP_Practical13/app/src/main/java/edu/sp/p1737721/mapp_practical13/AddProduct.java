package edu.sp.p1737721.mapp_practical13;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class AddProduct extends AppCompatActivity {

    private String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.additem);
    }

    void sendData(final String name, double price) {
        String url="https://mapp2020-dc18d.firebaseio.com/products.json";
        final String n = name;
        final double p = price;

        // request string response from provided url
      /*  StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // display the characters of response string
                        Log.d(TAG, "Response is: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "That didnt work!");
            }
        }) {
            // inner class for StringRequest
            // format body content to sent to network
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    // set up json obj
                    JSONObject obj = new JSONObject();
                     obj.put("name", n);
                     obj.put("price", p);

                    Log.d(TAG, obj.toString());

                    //send data in bytes
                    return obj.toString().getBytes();
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
                return super.getBody();
            }
            // set content type to json
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        }; */

        //add request to requestqueue
        // reuse queue by setting it as static variable
        // MainActivity.queue.add(stringRequest);
    }

    public void addproduct(View view) {
        String url="https://mapp2020-dc18d.firebaseio.com/products.json";
        final String n = ((EditText) findViewById(R.id.pName)).getText().toString();
        final String p = ((EditText) findViewById(R.id.pPrice)).getText().toString();

        // add product to database
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // display the characters of response string
                        Log.d(TAG, "Response is: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "That didnt work!");
            }
        }) {
            // inner class for StringRequest
            // format body content to sent to network
            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    // set up json obj
                    JSONObject obj = new JSONObject();
                    obj.put("name", n);
                    obj.put("price", p);

                    Log.d(TAG, obj.toString());

                    //send data in bytes
                    return obj.toString().getBytes();
                } catch (Exception e) {
                    Log.e(TAG, e.getMessage());
                }
                return super.getBody();
            }
            // set content type to json
            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        //add request to requestqueue
        // reuse queue by setting it as static variable
        MainActivity.queue.add(stringRequest);
    }
}
