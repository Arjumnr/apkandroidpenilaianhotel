package com.example.apk_android_penilaian_hotel;


public class ModelPertanyaan {

    private int page;
    private String per_page;
    private String total;
    private String total_pages;
    private  Datum[] data;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getPer_page() {
        return per_page;
    }

    public void setPer_page(String per_page) {
        this.per_page = per_page;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(String total_pages) {
        this.total_pages = total_pages;
    }

    public Datum[] getData() {
        return data;
    }

    public void setData(Datum[] data) {
        this.data = data;
    }





}


