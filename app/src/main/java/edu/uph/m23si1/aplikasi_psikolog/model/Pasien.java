package edu.uph.m23si1.aplikasi_psikolog.model;

public class Pasien {
    private String nama;
    private int fotoResId;

    public Pasien(String nama, int fotoResId) {
        this.nama = nama;
        this.fotoResId = fotoResId;
    }

    public String getNama() {
        return nama;
    }

    public int getFotoResId() {
        return fotoResId;
    }
}
