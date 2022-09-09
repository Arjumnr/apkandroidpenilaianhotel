package com.example.apk_android_penilaian_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apk_android_penilaian_hotel.Models.ModelBio;
import com.example.apk_android_penilaian_hotel.Models.SharedPrefManager;
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup;
import com.nex3z.togglebuttongroup.button.CircularToggle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class pertanyaan extends AppCompatActivity {

    String[] pertanyaanForResponden;
    String url;
    String output = "";
    public static int  benar, salah;

    TextView pertanyaan, nomorPertanyaan;
    Button btnNext ;


    //radio button library
    CircularToggle pilKepentingan5, pilKepentingan4, pilKepentingan3, pilKepentingan2, pilKepentingan1;
    CircularToggle pilKinerja5, pilKinerja4, pilKinerja3, pilKinerja2, pilKinerja1;

    int pert = 0;
    int noPert = 0;
//    ModelPertanyaan restPertanyaan = new ModelPertanyaan();
    int jawabanKepentingan, jawabanKinerja;
    ArrayList<String> listdata = new ArrayList<String>();
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
        //
         final SharedPrefManager prefManager = new SharedPrefManager(this);
         if(!prefManager.pengisianBio()){
             backToBio();
         }

         pertanyaan = findViewById(R.id.id_pertanyaan);

        SingleSelectToggleGroup sgKepentingan = findViewById(R.id.id_sg_kepentingan);
        SingleSelectToggleGroup sgKinerja = findViewById(R.id.id_sg_kinerja);

        getData();

        ModelBio modelBio = prefManager.getUserBio();

        String sNama = modelBio.getNama();
        String sUmur = modelBio.getUmur();

        Log.e("tag", "namanya" + sNama);



        //getData
        url = "http://hotels.googlee.win/api/get-all-pertanyaan";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject jsonObject = new JSONObject(response.toString());
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            System.out.println(jsonArray.get(1));
                            Object jsonObject12 = jsonArray.get(noPert);
                            System.out.println("jesonnnnnnn"+jsonObject12);
                            HashMap<String, String> ok = new HashMap<>();
                            System.out.println("ini ok "+ok);



                            for (int i=0; i<jsonArray.length(); i++){
                                     JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                     Log.e("jsobj", "jsobj" + jsonObject1);
//                                pertanyaan.setText(jsonObject1.getString("pertanyaan"));
                                listdata.add(jsonArray.getString(i));
                                Log.e("donee ", "donee") ;

                            }
                            noPert++;
                            nomorPertanyaan.setText(""+noPert+"/"+jsonArray.length());
                            //menghitung total data listdata
//                            Log.e("list", ""+listdata.size());



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
        Log.e("list", ""+listdata);

//        for(int i=0; i <listdata.size(); i++  ){
//            listpertanyaan.add(listdata.get(i));
//        }
//        pertanyaan.setText(""+ listdata);
    }

    public void next(View view) {
        if (jawKepentingan() !=0 && jawKinerja() != 0)benar++;
        else salah++;
//        cek nilai radio button
//        Log.e("jawkepentingan", "jawkepentingan"+jawabanKepentingan);

        noPert++;
        Log.e("list", ""+listdata.size());
        if (noPert < listdata.size()){
            nomorPertanyaan.setText(""+noPert+"/"+listdata.size());
        }
    }



    private void backToBio() {
        startActivity(new Intent(getApplicationContext(), Bio.class));
        finish();
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

    void getData(){




    }

    void next(){
        Log.d("Current",
                "onClick: " + noPert );
//        pertanyaan.setText();
    }


}