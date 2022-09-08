package com.example.apk_android_penilaian_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nex3z.togglebuttongroup.SingleSelectToggleGroup;
import com.nex3z.togglebuttongroup.button.CircularToggle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class pertanyaan extends AppCompatActivity {

    String[] pertanyaanForResponden;
    String url;
    TextView pertanyaan;
    Button btnNext ;

    //radio button library
    CircularToggle pilKepentingan5, pilKepentingan4, pilKepentingan3, pilKepentingan2, pilKepentingan1;
    CircularToggle pilKinerja5, pilKinerja4, pilKinerja3, pilKinerja2, pilKinerja1;





    int pert = 0;
    int noPert = 0;
    ModelPertanyaan restPertanyaan = new ModelPertanyaan();
    int jawabanKepentingan, jawabanKinerja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pertanyaan);

         pertanyaan = findViewById(R.id.id_pertanyaan);
         btnNext = findViewById(R.id.id_btnNext);
        getData();

        SingleSelectToggleGroup sgKepentingan = findViewById(R.id.id_sg_kepentingan);
        SingleSelectToggleGroup sgKinerja = findViewById(R.id.id_sg_kinerja);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                jawKepentingan();
//                jawKinerja();

                startActivity(new Intent(getApplicationContext(), finish.class));
            }
        });



    }

    void jawKinerja(){
        if(pilKinerja5.isChecked()){
            jawabanKinerja = 5;
        }else if(pilKinerja4.isChecked()){
            jawabanKinerja =4;
        }else if (pilKinerja3.isChecked()){
            jawabanKinerja = 3;
        }else if (pilKinerja2.isChecked()){
            jawabanKinerja = 2;
        }else if (pilKinerja1.isChecked()){
            jawabanKinerja = 1;
        }
    }

    void jawKepentingan(){
        if(pilKepentingan5.isChecked()){
            jawabanKepentingan = 5;
        }else if(pilKepentingan4.isChecked()){
            jawabanKepentingan =4;
        }else if (pilKepentingan3.isChecked()){
            jawabanKepentingan = 3;
        }else if (pilKepentingan2.isChecked()){
            jawabanKepentingan = 2;
        }else if (pilKepentingan1.isChecked()){
            jawabanKepentingan = 1;
        }

    }

    void getData(){
        url = "http://hotels.googlee.win/api/get-all-pertanyaan";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response.toString());
//                            boolean status = jsonObject.getBoolean( "error" );

                                JSONArray jsonArray = jsonObject.getJSONArray("data");
                                Log.e("", "isi = " + jsonArray);
                                for (int i=0; i<jsonArray.length(); i++){
                                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                                    ModelPertanyaan modelPertanyaan = new ModelPertanyaan();

                                    pertanyaan.setText(jsonObject1.getString("pertanyaan"));

//                                    String getEmail = " " + jsonObject1.getString("email") + " ";
//                                    restPertanyaan.setData(jsonObject1.getString(""));
                                }


//                            String tes = jsonObject.getString("data");
//                            pertanyaan.setText(tes);
////                            restPertanyaan.getData();
////                            pertanyaan.setText(jsonObject.getString(restPertanyaan.getData());
//
//                            Log.e("data", jsonObject.toString());

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
    }

    void next(){
        Log.d("Current",
                "onClick: " + noPert );
//        pertanyaan.setText();
    }
}