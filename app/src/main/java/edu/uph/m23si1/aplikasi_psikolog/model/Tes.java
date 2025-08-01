package edu.uph.m23si1.aplikasi_psikolog.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Tes extends RealmObject {
    @PrimaryKey
    private int id;
    private int umur;
    private String gender;
    private String demografi;
    private String cgpa;
    private String masalahUang;
    private int seringTidakDihargai;
    private int seringDiabaikan;
    private int merasaTidakPenting;
    private String perasaan;
    private int nyamanBicara;
    private boolean jadiDiriSendiri;
    private boolean dukunganKeluarga;
    private boolean olahraga;
    private boolean susahTidur;
    private String jamTidur;
    private String jamBangun;
    private boolean nafsuMakan;
    private int seringLewatMakan;
    private int skor;
    private String status;
    private String tanggal;

    public Tes(){}

    public Tes(int id, int umur, String gender, String demografi, String cgpa,
               String masalahUang, int seringTidakDihargai, int seringDiabaikan,
               int merasaTidakPenting, String perasaan, int nyamanBicara,
               boolean jadiDiriSendiri, boolean dukunganKeluarga, boolean olahraga,
               boolean susahTidur, String jamTidur, String jamBangun,
               boolean nafsuMakan, int seringLewatMakan, int skor, String status, String tanggal) {
        this.id = id;
        this.umur = umur;
        this.gender = gender;
        this.demografi = demografi;
        this.cgpa = cgpa;
        this.masalahUang = masalahUang;
        this.seringTidakDihargai = seringTidakDihargai;
        this.seringDiabaikan = seringDiabaikan;
        this.merasaTidakPenting = merasaTidakPenting;
        this.perasaan = perasaan;
        this.nyamanBicara = nyamanBicara;
        this.jadiDiriSendiri = jadiDiriSendiri;
        this.dukunganKeluarga = dukunganKeluarga;
        this.olahraga = olahraga;
        this.susahTidur = susahTidur;
        this.jamTidur = jamTidur;
        this.jamBangun = jamBangun;
        this.nafsuMakan = nafsuMakan;
        this.seringLewatMakan = seringLewatMakan;
        this.skor = skor;
        this.status = status;
        this.tanggal = tanggal;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public String getDemografi() {
        return demografi;
    }
    public void setDemografi(String demografi) {
        this.demografi = demografi;
    }

    public String getCgpa() {
        return cgpa;
    }
    public void setCgpa(String cgpa) {
        this.cgpa = cgpa;
    }

    public String getMasalahUang() {
        return masalahUang;
    }
    public void setMasalahUang(String masalahUang) {
        this.masalahUang = masalahUang;
    }

    public int getSeringTidakDihargai() {
        return seringTidakDihargai;
    }
    public void setSeringTidakDihargai(int seringTidakDihargai) {
        this.seringTidakDihargai = seringTidakDihargai;
    }

    public int getSeringDiabaikan() {
        return seringDiabaikan;
    }
    public void setSeringDiabaikan(int seringDiabaikan) {
        this.seringDiabaikan = seringDiabaikan;
    }

    public int getMerasaTidakPenting() {
        return merasaTidakPenting;
    }
    public void setMerasaTidakPenting(int merasaTidakPenting) {
        this.merasaTidakPenting = merasaTidakPenting;
    }

    public String getPerasaan() {
        return perasaan;
    }
    public void setPerasaan(String perasaan) {
        this.perasaan = perasaan;
    }

    public int getNyamanBicara() {
        return nyamanBicara;
    }
    public void setNyamanBicara(int nyamanBicara) {
        this.nyamanBicara = nyamanBicara;
    }

    public boolean isJadiDiriSendiri() {
        return jadiDiriSendiri;
    }
    public void setJadiDiriSendiri(boolean jadiDiriSendiri) {
        this.jadiDiriSendiri = jadiDiriSendiri;
    }

    public boolean isDukunganKeluarga() {
        return dukunganKeluarga;
    }
    public void setDukunganKeluarga(boolean dukunganKeluarga) {
        this.dukunganKeluarga = dukunganKeluarga;
    }

    public boolean isOlahraga() {
        return olahraga;
    }
    public void setOlahraga(boolean olahraga) {
        this.olahraga = olahraga;
    }

    public boolean isSusahTidur() {
        return susahTidur;
    }
    public void setSusahTidur(boolean susahTidur) {
        this.susahTidur = susahTidur;
    }

    public String getJamTidur() {
        return jamTidur;
    }
    public void setJamTidur(String jamTidur) {
        this.jamTidur = jamTidur;
    }

    public String getJamBangun() {
        return jamBangun;
    }
    public void setJamBangun(String jamBangun) {
        this.jamBangun = jamBangun;
    }

    public boolean isNafsuMakan() {
        return nafsuMakan;
    }
    public void setNafsuMakan(boolean nafsuMakan) {
        this.nafsuMakan = nafsuMakan;
    }

    public int getSeringLewatMakan() {
        return seringLewatMakan;
    }
    public void setSeringLewatMakan(int seringLewatMakan) {
        this.seringLewatMakan = seringLewatMakan;
    }

    public int getSkor() {
        return skor;
    }
    public void setSkor(int skor) {
        this.skor = skor;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getTanggal() {
        return tanggal;
    }
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}