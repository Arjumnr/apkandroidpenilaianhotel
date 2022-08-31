package com.example.apk_android_penilaian_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class Bio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);


        EditText etNama = findViewById(R.id.id_nama);
        EditText etUmur = findViewById(R.id.id_umur);
        EditText etKomentar = findViewById(R.id.id_komentar);
        Button btnSimpan = findViewById(R.id.id_btnSimpan);
        ProgressBar loadingPb;

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if(etNama.getText().toString().isEmpty() || etUmur.getText().toString().isEmpty() ){
//                    Toast.makeText(Bio.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
//                    return;
//                }

                startActivity(new Intent(getApplicationContext(),pertanyaan.class));

//                postData
            }
        });

//        private void postDataResponden(String nama, String umur){
//            String url = "";
//            loadingPb.setVisibility(View.VISIBLE);
//
//            RequestQueue queue = Volley.newRequestQueue(Bio.this);
//
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    loadingPb.setVisibility(View.GONE);
//                    try {
////                        JSONObject respObj = new JSONObject(response);
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//
//                    }
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Toast.makeText(Bio.this, "Fail to get response = " + error, Toast.LENGTH_SHORT).show();
//                }
//            }){
//                protected  Map<String,String> getParams(){
//                    Map<String, String> params = new HashMap<String, String>();
//
//                    params.put("nama", etNama);
//                    params.put("umur", etUmur);
//                    params.put("komentar_tambahan", etKomentar);
//                    return params;
//                }
//            };
//
//        }
    }



}