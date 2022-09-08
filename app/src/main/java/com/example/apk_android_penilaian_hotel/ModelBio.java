package com.example.apk_android_penilaian_hotel;

public class ModelBio {
    public ModelBio(String string, String preferencesString){}

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUmur() {
        return umur;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getKomentar_tambahan() {
        return komentar_tambahan;
    }

    public void setKomentar_tambahan(String komentar_tambahan) {
        this.komentar_tambahan = komentar_tambahan;
    }

    private String nama, umur, komentar_tambahan;

}
