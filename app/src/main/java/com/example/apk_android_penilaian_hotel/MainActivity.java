package com.example.apk_android_penilaian_hotel;

import static com.example.apk_android_penilaian_hotel.Bio.mypreference;
import static com.example.apk_android_penilaian_hotel.Bio.shNama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private int waktu_loading=4000;
    SharedPreferences _preferences;


    //4000=4 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        final SharedPrefManager prefManager = new SharedPrefManager(getApplicationContext());
        Boolean isiBio = prefManager.pengisianBio();

        Thread thread = new Thread(){
          @Override
          public void run(){
              try {
                  sleep(3000);
                  //
                  if (isiBio){
                      startActivity(new Intent(getApplicationContext(),pertanyaan.class));
                      finish();
                  }else {
                      startActivity(new Intent(getApplicationContext(), Bio.class));
                  }
              }catch (InterruptedException e){
                  e.printStackTrace();
              }
          }
        };

        thread.start();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//               cekDataCache();
//                finish();
//            }
//        }, waktu_loading);


    }


}