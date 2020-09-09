package com.example.vins2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.WriterException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final TextView Email = (TextView) findViewById(R.id.textViewEmail1);
        final TextView FullName  = (TextView) findViewById(R.id.textViewFullName);
        final TextView Stamps = (TextView) findViewById(R.id.textViewStamps1);
        final TextView FreeProducts = (TextView) findViewById(R.id.textViewFreeProducts1);
        final ImageView QR =(ImageView) findViewById(R.id.imageViewQR);

        Intent intent = getIntent();
        String access_token = intent.getStringExtra("access_token");

        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://vins.pl/api/v1/users/me";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String email  = response.getString("email");
                            String full_name = response.getString("full_name");
                            String points = response.getString("points");
                            String id = response.getString("id");
                            Email.setText(email);
                            FullName.setText(full_name);
                            Stamps.setText(points);
                            //FreeProducts.setText();
                            Bitmap bitmap;
                            ImageView QR;
                            QR = (ImageView)findViewById(R.id.imageViewQR);
                            QRGEncoder qrgEncoder = new QRGEncoder(id, null, QRGContents.Type.TEXT,300);
                            try {
                                // Getting QR-Code as Bitmap
                                bitmap = qrgEncoder.encodeAsBitmap();
                                // Setting Bitmap to ImageView
                                QR.setImageBitmap(bitmap);
                            } catch (WriterException e) {
                                //Log.v(TAG, e.toString());
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(UserAreaActivity.this);
                                builder.setMessage(error.toString())
                                        .setNegativeButton("Ponów", null)
                                        .create()
                                        .show();
                            }
                        }
                )
        {
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            Map<String, String> headerMap = new HashMap<String, String>();
            headerMap.put("Content-Type", "application/json");
            headerMap.put("Authorization", "Bearer " + access_token);
            return headerMap;
        }
        };
        queue.add(jsonObjectRequest);
    }
}