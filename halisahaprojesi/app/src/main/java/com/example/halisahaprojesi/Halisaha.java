package com.example.halisahaprojesi;

public class Halisaha {
    int Halisaha_id;
    String HalisahaAdi;
    String HalisahaAdresi;
    int HalisahaUcreti;

    public Halisaha(int halisaha_id, String halisahaAdi, String halisahaAdresi, int halisahaUcreti) {
        Halisaha_id = halisaha_id;
        HalisahaAdi = halisahaAdi;
        HalisahaAdresi = halisahaAdresi;
        HalisahaUcreti = halisahaUcreti;
    }

    public int getHalisaha_id() {
        return Halisaha_id;
    }

    public void setHalisaha_id(int halisaha_id) {
        Halisaha_id = halisaha_id;
    }

    public String getHalisahaAdi() {
        return HalisahaAdi;
    }

    public void setHalisahaAdi(String halisahaAdi) {
        HalisahaAdi = halisahaAdi;
    }

    public String getHalisahaAdresi() {
        return HalisahaAdresi;
    }

    public void setHalisahaAdresi(String halisahaAdresi) {
        HalisahaAdresi = halisahaAdresi;
    }

    public int getHalisahaUcreti() {
        return HalisahaUcreti;
    }

    public void setHalisahaUcreti(int halisahaUcreti) {
        HalisahaUcreti = halisahaUcreti;
    }
}
