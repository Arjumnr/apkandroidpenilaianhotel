package com.example.apk_android_penilaian_hotel;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apk_android_penilaian_hotel.Models.ModelPertanyaan;
import com.google.gson.Gson;
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup;
import com.nex3z.togglebuttongroup.button.CircularToggle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Array;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pertanyaan extends AppCompatActivity {

    String url;
    String output = "";
    TextView pertanyaan, nomorPertanyaan, waktu;


    //radio button library
    CircularToggle pilKepentingan5, pilKepentingan4, pilKepentingan3, pilKepentingan2, pilKepentingan1;
    CircularToggle pilKinerja5, pilKinerja4, pilKinerja3, pilKinerja2, pilKinerja1;

    int indexPertanyaan = 0;
    int noPert = 0;
    //    ModelPertanyaan restPertanyaan = new ModelPertanyaan();
    int jawabanKepentingan, jawabanKinerja;
    ArrayList<String> listdata = new ArrayList<>();
    ArrayList<String> listpertanyaan = new ArrayList<String>();

    DbHelper dbHelper = new DbHelper(this);

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertanyaan);

        if (dbHelper.cekDb() == true) {
            dbHelper.deleteAll();
            Log.e("delete", "delete ALL data");
        }

        //
        pilKepentingan5 = findViewById(R.id.choice_5);
        pilKepentingan4 = findViewById(R.id.choice_4);
        pilKepentingan3 = findViewById(R.id.choice_3);
        pilKepentingan2 = findViewById(R.id.choice_2);
        pilKepentingan1 = findViewById(R.id.choice_1);
        //
        pilKinerja5 = findViewById(R.id.choice_5p);
        pilKinerja4 = findViewById(R.id.choice_4p);
        pilKinerja3 = findViewById(R.id.choice_3p);
        pilKinerja2 = findViewById(R.id.choice_2p);
        pilKinerja1 = findViewById(R.id.choice_1p);
        //
        nomorPertanyaan = findViewById(R.id.id_noPertanyaan);
        pertanyaan = findViewById(R.id.id_pertanyaan);

//        waktu = findViewById(R.id.id_time);

        SingleSelectToggleGroup sgKepentingan = findViewById(R.id.id_sg_kepentingan);
        SingleSelectToggleGroup sgKinerja = findViewById(R.id.id_sg_kinerja);


//        url = "http://hotels.googlee.win/api/get-all-pertanyaan";
//        url = "http://http://127.0.0.1:8000/api/get-all-pertanyaan";
        url = "http://hotels.xapps.my.id/api/get-all-pertanyaan";


        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            for (int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                                Log.e("jsobj", "jsobj" + jsonObject1);
//                                pertanyaan.setText(jsonObject1.getString("pertanyaan"));
                                listdata.add(jsonArray.getString(i));
//                                Log.e("donee ", "donee") ;
                            }

                            Log.e("Jumlah List = ", "Jumlah List" + listdata.size());
                            String per = listdata.get(indexPertanyaan);
                            JSONObject joData = new JSONObject(per);
                            per = joData.getString("pertanyaan");
//                            String _id = joData.getString("id");
//                            Log.e("id = ", _id);

                            pertanyaan.setText(per);
                            noPert++;
                            nomorPertanyaan.setText("" + noPert + "/" + jsonArray.length());

                            Log.e("isi per", "Isi Per" + per);


                        } catch (JSONException e) {
                            Log.e("ERROR", "erork");
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(
                                pertanyaan.this,
                                error.getMessage(),
                                Toast.LENGTH_SHORT)
                        .show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
//        Log.e("isi List Data2", "isi List Data2" + listdata);

//        new CountDownTimer(15000, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                waktu.setText("waktu pengisian : " + millisUntilFinished / 1000);
//                Log.e("waktu", String.valueOf(dbHelper.cekDb()));
//
//            }
//
//            public void onFinish() {
//                Toast toast = Toast.makeText(getApplicationContext(), "Waktu Habis", Toast.LENGTH_SHORT);
//                toast.show();
//
//                //
//                SingleSelectToggleGroup sgKepentingan = findViewById(R.id.id_sg_kepentingan);
//                SingleSelectToggleGroup sgKinerja = findViewById(R.id.id_sg_kinerja);
//                //
//                listpertanyaan = listdata;
//                Log.e("index", "index" + indexPertanyaan);
//                Log.e("nopert", "nopert" + noPert);
//
//                String idPertanyaan = listpertanyaan.get(indexPertanyaan);
//                JSONObject jsonObject1 = null;
//                try {
//                    jsonObject1 = new JSONObject(idPertanyaan);
//                    int id_pertanyaan = jsonObject1.getInt("id");;
//
//                    Log.e("id_pertanyaan", String.valueOf(id_pertanyaan));
//                    Log.e("jawabanKepentingan", String.valueOf(jawKepentingan()));
//                    Log.e("jawabanKinerja", String.valueOf(jawKinerja()));
//
//
//                    int pertanyaan_id = id_pertanyaan;
//                    int kepentingan = 1;
//                    int kinerja = 1;
//
//                    try {
//                        dbHelper.insert( pertanyaan_id, kepentingan, kinerja);
//                        Log.e("data", dbHelper.getAllData().toString());
//                    }catch (Exception e){
//                        Log.e("errorrrrr", e.toString());
//                    }
//
//
//
//                Log.e("index", "index" + indexPertanyaan);
//                indexPertanyaan++;
//                noPert++;
//                output = listpertanyaan.get(indexPertanyaan);
//                JSONObject jsonObject2 = null;
//                try {
//                    jsonObject2 = new JSONObject(output);
//                    output = jsonObject2.getString("pertanyaan");
//
//                    sgKepentingan.clearCheck();
//                    sgKinerja.clearCheck();
//                    pertanyaan.setText(output);
//                    nomorPertanyaan.setText("" + noPert + "/" + listpertanyaan.size());
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
////            Log.e("id Pertanyaaan : ", jsonObject.getString("id"));
//
//
////
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//
//            }
//        }.start();
    }

    public void next(View view) throws JSONException {
        //
        RequestQueue queue = Volley.newRequestQueue(pertanyaan.this);
        SingleSelectToggleGroup sgKepentingan = findViewById(R.id.id_sg_kepentingan);
        SingleSelectToggleGroup sgKinerja = findViewById(R.id.id_sg_kinerja);
        //
        listpertanyaan = listdata;
        Log.e("inde = x", "index" + indexPertanyaan);
        Log.e("indexPertanyaan = ", "" + listpertanyaan.size());
        Log.e("nopert = ", "nopert" + noPert);


        if (indexPertanyaan + 1 == listpertanyaan.size()) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);

            final StringRequest request = new StringRequest(Request.Method.POST, "http://hotels.xapps.my.id/api/add-responden",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                            return response()->json(['success' => true, 'data' => $data], 200);
                           Log.e("response", response);
                           Toast.makeText(pertanyaan.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(pertanyaan.this, finish.class);
                            startActivity(intent);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("error", error.toString());
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    ArrayList<HashMap<String, String>> data = dbHelper.getAllData();
                    Gson gson = new Gson();
                    String json = gson.toJson(data);
                    Log.e("json", json);
                    Map<String, String> params = new HashMap<>();
                    params.put("data", json);
                    return params;
                }
            };
            queue.add(request);

        } else if (jawKepentingan() != 0 && jawKinerja() != 0) {
            String idPertanyaan = listpertanyaan.get(indexPertanyaan);
            JSONObject jsonObject1 = new JSONObject(idPertanyaan);
            int id_pertanyaan = jsonObject1.getInt("id");

            Log.e("id_pertanyaan", String.valueOf(id_pertanyaan));
            Log.e("jawabanKepentingan", String.valueOf(jawKepentingan()));
            Log.e("jawabanKinerja", String.valueOf(jawKinerja()));

            //get current time second
            long time = System.currentTimeMillis();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String dateString = sdf.format(time);
//            Log.e("time", dateString);
//            waktu.setText(dateString);


            int pertanyaan_id = id_pertanyaan;
            int kepentingan = jawKepentingan();
            Log.e("kepentingan", String.valueOf(kepentingan));
            int kinerja = jawKinerja();
            Time waktu = Time.valueOf(dateString);

            try {
                dbHelper.insert(pertanyaan_id, kepentingan, kinerja, waktu);
            } catch (Exception e) {
                Log.e("errorrrrr", e.toString());
            }
            Log.e("Jumlah Data = ", "" + dbHelper.getAllData().size());
            Log.e("isi = ", "" + dbHelper.getAllData());

            indexPertanyaan++;
            noPert++;
            output = listpertanyaan.get(indexPertanyaan);
            JSONObject jsonObject = new JSONObject(output);
            output = jsonObject.getString("pertanyaan");

//            Log.e("id Pertanyaaan : ", jsonObject.getString("id"));

            sgKepentingan.clearCheck();
            sgKinerja.clearCheck();
            pertanyaan.setText(output);
            nomorPertanyaan.setText("" + noPert + "/" + listpertanyaan.size());








        } else if (jawKepentingan() == 0) {
            Toast toast = Toast.makeText(pertanyaan.this, "Anda Belum Memilih Kepentingan", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else if (jawKinerja() == 0) {
            Toast toast = Toast.makeText(pertanyaan.this, "Anda Belum Memilih Kinerja", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            Toast toast = Toast.makeText(pertanyaan.this, "Anda Belum Memilih", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
//        cek nilai radio button
//        Log.e("jawkepentingan", "jawkepentingan"+jawabanKepentingan);


    }


    int jawKinerja() {
        if (pilKinerja5.isChecked()) {
            return jawabanKinerja = 5;
        } else if (pilKinerja4.isChecked()) {
            return jawabanKinerja = 4;
        } else if (pilKinerja3.isChecked()) {
            return jawabanKinerja = 3;
        } else if (pilKinerja2.isChecked()) {
            return jawabanKinerja = 2;
        } else if (pilKinerja1.isChecked()) {
            return jawabanKinerja = 1;
        }

        return 0;
    }

    public int jawKepentingan() {
        if (pilKepentingan5.isChecked()) {
            return jawabanKepentingan = 5;
        } else if (pilKepentingan4.isChecked()) {
            return jawabanKepentingan = 4;
        } else if (pilKepentingan3.isChecked()) {
            return jawabanKepentingan = 3;
        } else if (pilKepentingan2.isChecked()) {
            return jawabanKepentingan = 2;
        } else if (pilKepentingan1.isChecked()) {
            return jawabanKepentingan = 1;
        }

        return 0;
    }
}





