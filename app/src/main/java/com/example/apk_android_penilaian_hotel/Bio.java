package com.example.apk_android_penilaian_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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


    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ProgressBar loadingPb;

    public static final String mypreference = "mypref";
    public static final String shNama = "namaKey";
    public static final String shUmur = "umurKey";

    String snama,sumur,skomentar_tambahan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio);
        //
        final EditText etNama = findViewById(R.id.id_nama);
        final EditText etUmur = findViewById(R.id.id_umur);
        final EditText etKomentar = findViewById(R.id.id_komentar);
        final Button btnSimpan = findViewById(R.id.id_btnSimpan);
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Pease Wait.......");
        progressDialog.setCancelable(false);
        final SharedPrefManager prefManager = new SharedPrefManager(getApplicationContext());
        Boolean isiBio = prefManager.pengisianBio();

//
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String sNama = etNama.getText().toString();
                String sUmur = etUmur.getText().toString();
                String sKomen = etKomentar.getText().toString();

                if (sNama == "" && sUmur == "") {
                    Toast.makeText(Bio.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                }else if (sNama != "" && sUmur !=""){
                    ModelBio modelBio = new ModelBio(sNama, sNama);
                    prefManager.setIsiBio(modelBio, true);
                    startActivity(new Intent(getApplicationContext(), pertanyaan.class));
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(Bio.this, "GAGAL MENYIMPAN", Toast.LENGTH_SHORT).show();
                }



            }
        });
    }







}