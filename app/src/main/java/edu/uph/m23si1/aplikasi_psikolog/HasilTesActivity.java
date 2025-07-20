package edu.uph.m23si1.aplikasi_psikolog;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import edu.uph.m23si1.aplikasi_psikolog.model.Tes;
import io.realm.Realm;

public class HasilTesActivity extends AppCompatActivity {

    private Realm realm;
    private TextView txtSkor, txtStatus;
    EditText edtUmur, edtPerasaan, edtJamTidur, edtJamBangun;
    RadioButton rdbLaki, rdbPerempuan, rdbDesa, rdbKota, rdbTinggi, rdbSedang, rdbRendah, rdbUangSignifikan, rdbUangSedang, rdbUangRingan;
    RadioButton rdbYDiriSendiri, rdbNDiriSendiri, rdbYDukungan, rdbNDukungan;
    RadioButton rdbYOlahraga, rdbNOlahraga, rdbYTidur, rdbNTidur, rdbYMakan, rdbNMakan;
    SeekBar skbTidakDihargai, skbDiabaikan, skbTidakPenting, skbNyamanBicara, skbLewatMakan;

    private ImageView imgKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_tes);

        imgKembali = findViewById(R.id.imgKembali);
        imgKembali.setOnClickListener(v -> finish());

        txtSkor = findViewById(R.id.txtSkor);
        txtStatus = findViewById(R.id.txtStatus);

        realm = Realm.getDefaultInstance();

        int idTes = getIntent().getIntExtra("idTes", -1);
        Tes tes = realm.where(Tes.class).equalTo("id", idTes).findFirst();

        initViews();

        if (tes != null) {
            txtSkor.setText("Skor: " + tes.getSkor());
            txtStatus.setText("Status: " + tes.getStatus());

            edtUmur.setText(String.valueOf(tes.getUmur()));
            edtPerasaan.setText(tes.getPerasaan());
            edtJamTidur.setText(tes.getJamTidur());
            edtJamBangun.setText(tes.getJamBangun());

            if ("Laki-laki".equalsIgnoreCase(tes.getGender())) rdbLaki.setChecked(true);
            else rdbPerempuan.setChecked(true);

            if ("Pedesaan".equalsIgnoreCase(tes.getDemografi())) rdbDesa.setChecked(true);
            else rdbKota.setChecked(true);

            if ("3.50 - 4.00".equalsIgnoreCase(tes.getCgpa())) rdbTinggi.setChecked(true);
            else if ("2.50 - 3.49".equalsIgnoreCase(tes.getCgpa())) rdbSedang.setChecked(true);
            else rdbRendah.setChecked(true);

            if ("Signifikan".equalsIgnoreCase(tes.getMasalahUang())) rdbUangSignifikan.setChecked(true);
            else if ("Sedang".equalsIgnoreCase(tes.getMasalahUang())) rdbUangSedang.setChecked(true);
            else rdbUangRingan.setChecked(true);

            if (tes.isJadiDiriSendiri()) rdbYDiriSendiri.setChecked(true);
            else rdbNDiriSendiri.setChecked(true);

            if (tes.isDukunganKeluarga()) rdbYDukungan.setChecked(true);
            else rdbNDukungan.setChecked(true);

            if (tes.isOlahraga()) rdbYOlahraga.setChecked(true);
            else rdbNOlahraga.setChecked(true);

            if (tes.isSusahTidur()) rdbYTidur.setChecked(true);
            else rdbNTidur.setChecked(true);

            if (tes.isNafsuMakan()) rdbYMakan.setChecked(true);
            else rdbNMakan.setChecked(true);

            skbTidakDihargai.setMax(4);
            skbDiabaikan.setMax(4);
            skbTidakPenting.setMax(4);
            skbNyamanBicara.setMax(4);
            skbLewatMakan.setMax(4);

            skbTidakDihargai.setProgress(tes.getSeringTidakDihargai());
            skbDiabaikan.setProgress(tes.getSeringDiabaikan());
            skbTidakPenting.setProgress(tes.getMerasaTidakPenting());
            skbNyamanBicara.setProgress(tes.getNyamanBicara());
            skbLewatMakan.setProgress(tes.getSeringLewatMakan());

            edtUmur.setEnabled(false);
            edtPerasaan.setEnabled(false);
            edtJamTidur.setEnabled(false);
            edtJamBangun.setEnabled(false);
            rdbDesa.setEnabled(false);
            rdbKota.setEnabled(false);
            rdbPerempuan.setEnabled(false);
            rdbLaki.setEnabled(false);
            rdbTinggi.setEnabled(false);
            rdbSedang.setEnabled(false);
            rdbRendah.setEnabled(false);
            rdbUangSignifikan.setEnabled(false);
            rdbUangSedang.setEnabled(false);
            rdbUangRingan.setEnabled(false);
            rdbYDiriSendiri.setEnabled(false);
            rdbNDiriSendiri.setEnabled(false);
            rdbYDukungan.setEnabled(false);
            rdbNDukungan.setEnabled(false);
            rdbYMakan.setEnabled(false);
            rdbNMakan.setEnabled(false);
            rdbYTidur.setEnabled(false);
            rdbNTidur.setEnabled(false);
            rdbYOlahraga.setEnabled(false);
            rdbNOlahraga.setEnabled(false);
        }
    }

    private void initViews() {
        edtUmur = findViewById(R.id.edtUmur);
        edtPerasaan = findViewById(R.id.edtPerasaan);
        edtJamTidur = findViewById(R.id.edtJamTidur);
        edtJamBangun = findViewById(R.id.edtJamBangun);

        rdbLaki = findViewById(R.id.rdbLaki);
        rdbPerempuan = findViewById(R.id.rdbPerempuan);
        rdbDesa = findViewById(R.id.rdbDesa);
        rdbKota = findViewById(R.id.rdbKota);
        rdbTinggi = findViewById(R.id.rdbTinggi);
        rdbSedang = findViewById(R.id.rdbSedang);
        rdbRendah = findViewById(R.id.rdbRendah);
        rdbUangSignifikan = findViewById(R.id.rdbUangSignifikan);
        rdbUangSedang = findViewById(R.id.rdbUangSedang);
        rdbUangRingan = findViewById(R.id.rdbUangRingan);
        rdbYDiriSendiri = findViewById(R.id.rdbYDiriSendiri);
        rdbNDiriSendiri = findViewById(R.id.rdbNDiriSendiri);
        rdbYDukungan = findViewById(R.id.rdbYDukungan);
        rdbNDukungan = findViewById(R.id.rdbNDukungan);
        rdbYOlahraga = findViewById(R.id.rdbYOlahraga);
        rdbNOlahraga = findViewById(R.id.rdbNOlahraga);
        rdbYTidur = findViewById(R.id.rdbYTidur);
        rdbNTidur = findViewById(R.id.rdbNTidur);
        rdbYMakan = findViewById(R.id.rdbYMakan);
        rdbNMakan = findViewById(R.id.rdbNMakan);

        skbTidakDihargai = findViewById(R.id.skbTidakDihargai);
        skbDiabaikan = findViewById(R.id.skbDiabaikan);
        skbTidakPenting = findViewById(R.id.skbTidakPenting);
        skbNyamanBicara = findViewById(R.id.skbNyamanBicara);
        skbLewatMakan = findViewById(R.id.skbLewatMakan);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }
}