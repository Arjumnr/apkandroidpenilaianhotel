package com.example.apk_android_penilaian_hotel;

import static com.example.apk_android_penilaian_hotel.Bio.mypreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class finish extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        final Button btnFinish = findViewById(R.id.id_btn_finish);
        final SharedPrefManager prefManager = new SharedPrefManager(this);

        ModelBio modelBio = prefManager.getIsiBio();


        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prefManager.finishResponden();
                backToBio();


            }
        });

    }

    private void backToBio(){
        startActivity(new Intent(getApplicationContext(), Bio.class));
        finish();
    }

}