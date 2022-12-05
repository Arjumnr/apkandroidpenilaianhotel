package com.example.apk_android_penilaian_hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.apk_android_penilaian_hotel.Models.ModelBio;
import com.example.apk_android_penilaian_hotel.Models.SharedPrefManager;

public class finish extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        final Button btnFinish = findViewById(R.id.id_btn_finish);
        final SharedPrefManager prefManager = new SharedPrefManager(this);

        ModelBio modelBio = prefManager.getUserBio();


        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.finishResponden();
                backToBio();


            }
        });

    }

    private void backToBio(){

        finish();
        System.exit(0);
    }

}