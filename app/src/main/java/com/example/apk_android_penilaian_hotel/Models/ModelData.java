package com.example.apk_android_penilaian_hotel.Models;

import java.io.Serializable;

public class ModelData implements Serializable {
    private int id;
    private int user_id;
    private int pilkep;
    private int pilkin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


    public int getPilkep() {
        return pilkep;
    }

    public void setPilkep(int pilkep) {
        this.pilkep = pilkep;
    }

    public int getPilkin() {
        return pilkin;
    }

    public void setPilkin(int pilkin) {
        this.pilkin = pilkin;
    }
}
