package com.example.apk_android_penilaian_hotel.Models;


import com.example.apk_android_penilaian_hotel.Datum;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ModelPertanyaan {
    private int id;
    private String kode;
    private String pertanyaan;
    private ArrayList<String> data = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getKode() {
        return kode;
    }

    public String getPertanyaan() {
        return pertanyaan;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public ModelPertanyaan(int id, String kode, String pertanyaan, ArrayList<String> data) {
        this.id = id;
        this.kode = kode;
        this.pertanyaan = pertanyaan;
        this.data = data;
    }





}


