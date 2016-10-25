package com.eniserkaya.firebaseci;


/**
 * Created by eniserkaya on 23.10.2016.
 */

public class Kullanici {

    private String ad,soyad;
    private int yas;

    public Kullanici(){}

    public Kullanici(String ad, String soyad, int yas) {
        this.ad = ad;
        this.soyad = soyad;
        this.yas = yas;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }
}
