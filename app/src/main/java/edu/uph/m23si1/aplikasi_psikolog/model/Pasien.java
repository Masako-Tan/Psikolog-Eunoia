package edu.uph.m23si1.aplikasi_psikolog.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

public class Pasien extends RealmObject{
    @PrimaryKey
    private String email;
    private String nama;
    private String jurusan;
    private int umur;
    private String jenisKelamin;
    private int fotoResId;

    public Pasien() {
    }

    public Pasien(String email, String nama, String jurusan, int umur, String jenisKelamin, int fotoResId) {
        this.email = email;
        this.nama = nama;
        this.jurusan = jurusan;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;
        this.fotoResId = fotoResId;
    }

    public String getEmail() {
        return email;
    }

    public String getNama() {
        return nama;
    }

    public String getJurusan() {
        return jurusan;
    }

    public int getUmur() {
        return umur;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public int getFotoResId() {
        return fotoResId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    public void setUmur(int umur) {
        this.umur = umur;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public void setFotoResId(int fotoResId) {
        this.fotoResId = fotoResId;
    }
}