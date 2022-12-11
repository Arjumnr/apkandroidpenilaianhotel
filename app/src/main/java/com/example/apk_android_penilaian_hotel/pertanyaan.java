package com.example.apk_android_penilaian_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apk_android_penilaian_hotel.Models.ModelPertanyaan;
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup;
import com.nex3z.togglebuttongroup.button.CircularToggle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class pertanyaan extends AppCompatActivity {

    String[] pertanyaanForResponden;
    String url;
    String output = "";
    ModelPertanyaan modelPertanyaan;
    TextView pertanyaan, nomorPertanyaan;



    //radio button library
    CircularToggle pilKepentingan5, pilKepentingan4, pilKepentingan3, pilKepentingan2, pilKepentingan1;
    CircularToggle pilKinerja5, pilKinerja4, pilKinerja3, pilKinerja2, pilKinerja1;

    int indexPertanyaan = 0;
    int noPert = 0;
//    ModelPertanyaan restPertanyaan = new ModelPertanyaan();
    int jawabanKepentingan, jawabanKinerja;
    ArrayList<String> listdata = new ArrayList<>();
    ArrayList<String> listpertanyaan = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertanyaan);

        //
         pilKepentingan5 = findViewById(R.id.choice_5);
         pilKepentingan4 = findViewById(R.id.choice_4);
         pilKepentingan3 = findViewById(R.id.choice_3);
         pilKepentingan2 = findViewById(R.id.choice_2);
         pilKepentingan1 = findViewById(R.id.choice_1);

         pilKinerja5 = findViewById(R.id.choice_5p);
         pilKinerja4 = findViewById(R.id.choice_4p);
         pilKinerja3 = findViewById(R.id.choice_3p);
         pilKinerja2 = findViewById(R.id.choice_2p);
         pilKinerja1 = findViewById(R.id.choice_1p);
         //
        nomorPertanyaan = findViewById(R.id.id_noPertanyaan);
        pertanyaan = findViewById(R.id.id_pertanyaan);

        SingleSelectToggleGroup sgKepentingan = findViewById(R.id.id_sg_kepentingan);
        SingleSelectToggleGroup sgKinerja = findViewById(R.id.id_sg_kinerja);

        //
//         final SharedPrefManager prefManager = new SharedPrefManager(this);
//         if(!prefManager.pengisianBio()){
//             backToBio();
//         }


//        ModelBio modelBio = prefManager.getUserBio();
//
//        String sNama = modelBio.getNama();
//        String sUmur = modelBio.getUmur();

//        Log.e("tag", "namanya" + sNama);

        url = "http://hotels.googlee.win/api/get-all-pertanyaan";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            for (int i=0; i<jsonArray.length(); i++){
//                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                                Log.e("jsobj", "jsobj" + jsonObject1);
//                                pertanyaan.setText(jsonObject1.getString("pertanyaan"));
                                listdata.add(jsonArray.getString(i));
//                                Log.e("donee ", "donee") ;

                            }

                            Log.e("list1", "list1" + listdata.size());
                            String per = listdata.get(indexPertanyaan);
                            JSONObject joData = new JSONObject(per);
                            per = joData.getString("pertanyaan");
                            pertanyaan.setText(per);
                            noPert++;
                            nomorPertanyaan.setText("" + noPert + "/" + jsonArray.length());

                            Log.e("isi per", "Isi Per"+ per);




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




    }

    public void next(View view) throws JSONException {
        //
        SingleSelectToggleGroup sgKepentingan = findViewById(R.id.id_sg_kepentingan);
        SingleSelectToggleGroup sgKinerja = findViewById(R.id.id_sg_kinerja);
        //
        listpertanyaan = listdata;
        Log.e("index", "index" + indexPertanyaan);
        Log.e("nopert", "nopert" + noPert);
        indexPertanyaan++;
        noPert++;

        if ( indexPertanyaan == listpertanyaan.size() ){
            startActivity(new Intent(getApplicationContext(), finish.class));
        } else if (jawKepentingan() !=0 && jawKinerja() != 0){
            output = listpertanyaan.get(indexPertanyaan);
            JSONObject jsonObject = new JSONObject(output);
            output = jsonObject.getString("pertanyaan");
            sgKepentingan.clearCheck();
            sgKinerja.clearCheck();
            pertanyaan.setText(output);
            nomorPertanyaan.setText("" + noPert + "/" + listpertanyaan.size());

        }else if (jawKepentingan() == 0){
            Toast toast = Toast.makeText(pertanyaan.this, "Anda Belum Memilih Kepentingan", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0,0);
            toast.show();
        }else if (jawKinerja() == 0){
            Toast toast = Toast.makeText(pertanyaan.this, "Anda Belum Memilih Kinerja", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0,0);
            toast.show();
        }else{
            Toast toast = Toast.makeText(pertanyaan.this, "Anda Belum Memilih", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0,0);
            toast.show();
        }
//        cek nilai radio button
//        Log.e("jawkepentingan", "jawkepentingan"+jawabanKepentingan);


    }


    int jawKinerja(){
        if(pilKinerja5.isChecked()){
            return jawabanKinerja = 5;
        }else if(pilKinerja4.isChecked()){
            return jawabanKinerja =4;
        }else if (pilKinerja3.isChecked()){
            return jawabanKinerja = 3;
        }else if (pilKinerja2.isChecked()){
            return jawabanKinerja = 2;
        }else if (pilKinerja1.isChecked()){
            return jawabanKinerja = 1;
        }

        return 0;
    }

    public int jawKepentingan(){
        if(pilKepentingan5.isChecked()){
            return jawabanKepentingan = 5;
        }else if(pilKepentingan4.isChecked()){
            return jawabanKepentingan =4;
        }else if (pilKepentingan3.isChecked()){
            return jawabanKepentingan = 3;
        }else if (pilKepentingan2.isChecked()){
           return  jawabanKepentingan = 2;
        }else if (pilKepentingan1.isChecked()){
            return jawabanKepentingan = 1;
        }

        return 0;
    }

    void getDATA(){
        url = "http://hotels.googlee.win/api/get-all-pertanyaan";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("data");

                            for (int i=0; i<jsonArray.length(); i++){
//                                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                                Log.e("jsobj", "jsobj" + jsonObject1);
//                                pertanyaan.setText(jsonObject1.getString("pertanyaan"));
                                listdata.add(jsonArray.getString(i));
//                                Log.e("donee ", "donee") ;

                            }

//                            listpertanyaan = listdata;
//                            Log.e("list1", "list1" + listdata.size());
                            String per = listdata.get(indexPertanyaan);
                            JSONObject joData = new JSONObject(per.toString());
                            per = joData.getString("pertanyaan");
                            pertanyaan.setText(per);
//                            noPert++;
//                            nomorPertanyaan.setText(""+noPert+"/"+jsonArray.length());
//
//                            Log.e("isi per", "Isi Per"+ per);




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
    }

    void showPertanyaan(){



    }




}