package com.example.apk_android_penilaian_hotel;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    final SharedPreferences preferences;
    private static final String SHARED_PREF_NAME = "SP_CACHE_HOTEL";
    private static final String KEY_NAMA = "namaKEY";
    private static final String KEY_UMUR = "umurKEY";
    private static final String ISI_KEY = "isinya_bro";

    SharedPrefManager(Context context){
        preferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public ModelBio getIsiBio(){
        return new ModelBio(
                preferences.getString(KEY_NAMA,null),
                preferences.getString(KEY_UMUR,null)
        );
    }

    public void setIsiBio( ModelBio bio, Boolean isBioCompleate){

        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_NAMA, bio.getNama());
        editor.putString(KEY_UMUR, bio.getUmur());
        editor.putBoolean(ISI_KEY, isBioCompleate);
        editor.apply();
    }

    public Boolean pengisianBio(){
        return preferences.getBoolean(ISI_KEY, false);
    }

    public void finishResponden(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
}
