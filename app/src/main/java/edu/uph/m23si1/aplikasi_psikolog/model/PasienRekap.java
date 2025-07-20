package edu.uph.m23si1.aplikasi_psikolog.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class PasienRekap extends RealmObject {
    @PrimaryKey
    private String email;

    private String nama;
    private int umur;
    private String jenisKelamin;
    private String jurusan;
    private int fotoResId;

    public PasienRekap() {} // Diperlukan Realm

    public PasienRekap(String email, String nama, int umur, String jenisKelamin, String jurusan, int fotoResId) {
        this.email = email;
        this.nama = nama;
        this.umur = umur;
        this.jenisKelamin = jenisKelamin;
        this.jurusan = jurusan;
        this.fotoResId = fotoResId;
    }

    // Getter & Setter
    public String getEmail() { return email; }
    public String getNama() { return nama; }
    public int getUmur() { return umur; }
    public String getJenisKelamin() { return jenisKelamin; }
    public String getJurusan() { return jurusan; }
    public int getFotoResId() { return fotoResId; }

    public void setEmail(String email) { this.email = email; }
    public void setNama(String nama) { this.nama = nama; }
    public void setUmur(int umur) { this.umur = umur; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }
    public void setJurusan(String jurusan) { this.jurusan = jurusan; }
    public void setFotoResId(int fotoResId) { this.fotoResId = fotoResId; }
}