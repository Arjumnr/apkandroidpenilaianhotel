package com.example.apk_android_penilaian_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apk_android_penilaian_hotel.Models.ModelBio;
import com.example.apk_android_penilaian_hotel.Models.SharedPrefManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Bio extends AppCompatActivity {


    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    ProgressBar loadingPb;

    public static final String mypreference = "mypref";
    public static final String shNama = "namaKey";
    public static final String shUmur = "umurKey";

//    public static final String sNama


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
        Boolean statusBio = prefManager.pengisianBio();

        if (statusBio){
            startActivity(new Intent(getApplicationContext(), pertanyaan.class));
            finish();
        }else {
            btnSimpan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String sNama = etNama.getText().toString();
                    String sUmur = etUmur.getText().toString();
                    String sKomen = etKomentar.getText().toString();

                    if (sNama.equals("") || sUmur.equals("") || sKomen.equals("")) {
                        Toast.makeText(Bio.this, "Data Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                    } else if (sNama != "" && sUmur !=""){
                        progressDialog.show();
                        ModelBio modelBio = new ModelBio(sNama, sUmur);
                        prefManager.setIsiBio(modelBio, true);
                        //
                        String url= "https://hotels.googlee.win/api/add-responden";
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, response -> {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
//                            boolean status = jsonObject.getBoolean("");
//                            if (!status){
                                startActivity(new Intent(getApplicationContext(), pertanyaan.class));
                                finish();
                                Toast.makeText(getApplicationContext(), "BERHASIL DI SIMPAN",Toast.LENGTH_SHORT).show();

//                            }

                            }catch (JSONException e) {
                                progressDialog.dismiss();
                                e.printStackTrace();
                                Log.e(""+e , "GAGAL CATCH");
                                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }, error -> {
                            Log.e("", "BUNTU");
                            Toast.makeText(Bio.this,error.toString(), Toast.LENGTH_SHORT).show();
                        }){
                            protected Map<String, String> getParams(){
                                Map<String, String> params = new HashMap<>();
                                params.put("nama", sNama);
                                params.put("umur", sUmur);
                                params.put("komentar_tambahan", sKomen);
                                return params;
                            }
                        };
                        Volley.newRequestQueue(getApplicationContext()).add(stringRequest).setShouldCache(false);

                    }else {
                        progressDialog.dismiss();
                        Toast.makeText(Bio.this, "GAGAL MENYIMPAN", Toast.LENGTH_SHORT).show();
                    }



                }
            });
        }



    }

    private void postDataBio(){


    }







}