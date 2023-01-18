package com.example.apk_android_penilaian_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    private int waktu_loading=4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Thread thread = new Thread(){
          @Override
          public void run(){
              try {
                  sleep(3000);
                  //

                      startActivity(new Intent(getApplicationContext(), Petunjuk.class));

              }catch (InterruptedException e){
                  e.printStackTrace();
              }
          }
        };

        thread.start();



    }


}