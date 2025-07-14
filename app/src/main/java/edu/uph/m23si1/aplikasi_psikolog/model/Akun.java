package edu.uph.m23si1.aplikasi_psikolog.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Akun extends RealmObject {

    @PrimaryKey
    private String email;
    private String password;
    private String namaLengkap;
    public Akun() {
        // constructor kosong wajib untuk Realm
    }

    public Akun(String email, String password, String namaLengkap) {
        this.email = email;
        this.password = password;
        this.namaLengkap = namaLengkap;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }
    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }


}
