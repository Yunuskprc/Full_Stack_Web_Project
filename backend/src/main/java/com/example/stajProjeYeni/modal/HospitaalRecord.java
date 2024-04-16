package com.example.stajProjeYeni.modal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class HospitaalRecord {
    @Id
    @GeneratedValue
    private long dosyaİd;
    private String hastaAd;
    private String hastaSoyad;
    private long hastaTC;
    private String hastaTaniBaslik;
    @Column(columnDefinition = "TEXT")
    private String hastaTaniDetay;
    private LocalDateTime kayitTarih;
    private String resimURL;

    public long getDosyaİd() {
        return dosyaİd;
    }

    public String getHastaAd() {
        return hastaAd;
    }

    public String getHastaSoyad() {
        return hastaSoyad;
    }

    public long getHastaTC() {
        return hastaTC;
    }

    public String getHastaTaniBaslik() {
        return hastaTaniBaslik;
    }

    public String getHastaTaniDetay() {
        return hastaTaniDetay;
    }

    public LocalDateTime getKayitTarih() {
        return kayitTarih;
    }

    public String getResimURL() {
        return resimURL;
    }

    public void setDosyaİd(long dosyaİd) {
        this.dosyaİd = dosyaİd;
    }

    public void setHastaAd(String hastaAd) {
        this.hastaAd = hastaAd;
    }

    public void setHastaSoyad(String hastaSoyad) {
        this.hastaSoyad = hastaSoyad;
    }

    public void setHastaTC(long hastaTC) {
        this.hastaTC = hastaTC;
    }

    public void setHastaTaniBaslik(String hastaTaniBaslik) {
        this.hastaTaniBaslik = hastaTaniBaslik;
    }

    public void setHastaTaniDetay(String hastaTaniDetay) {
        this.hastaTaniDetay = hastaTaniDetay;
    }

    public void setKayitTarih(LocalDateTime kayitTarih) {
        this.kayitTarih = kayitTarih;
    }

    public void setResimURL(String resimURL) {
        this.resimURL = resimURL;
    }


    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
