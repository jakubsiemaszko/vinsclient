package com.example.vins2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
        final EditText editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);
        final Button button = (Button) findViewById(R.id.button);
        final EditText editTextTextPersonName4 = (EditText) findViewById(R.id.editTextTextPersonName4);

        RequestQueue queue = Volley.newRequestQueue(this);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = editTextTextEmailAddress.getText().toString();
                final String password = editTextTextPassword.getText().toString();
                final String full_name = editTextTextPersonName4.getText().toString();
                String url = "http://vins.pl/api/v1/users/open";
                Map<String, String> params = new HashMap<String, String>();
                params.put("password", password);
                params.put("email", email);
                params.put("full_name", full_name);
                JSONObject parameters = new JSONObject(params);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                        (Request.Method.POST, url, parameters, new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);
                            }
                        },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                        builder.setMessage(error.toString())
                                                .setNegativeButton("Ponów", null)
                                                .create()
                                                .show();
                                    }
                                }
                        )
                {
                };
                queue.add(jsonObjectRequest);
            }
        });
    }
}