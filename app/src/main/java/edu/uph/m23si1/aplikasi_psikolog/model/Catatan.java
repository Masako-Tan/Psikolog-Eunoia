package edu.uph.m23si1.aplikasi_psikolog.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Catatan extends RealmObject {
    @PrimaryKey
    private String id;
    private String nama;
    private int umur;
    private String gender;
    private String observasi;

    // Getter dan Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getUmur() {
        return umur;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getObservasi() {
        return observasi;
    }

    public void setObservasi(String observasi) {
        this.observasi = observasi;
    }
}

