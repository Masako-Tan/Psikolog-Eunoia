package edu.uph.m23si1.aplikasi_psikolog.ui.catatan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.UUID;

import edu.uph.m23si1.aplikasi_psikolog.R;
import edu.uph.m23si1.aplikasi_psikolog.model.Catatan;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class CreateCttnFragment extends Fragment {
    EditText edtNama, edtUmur, edtObs;
    RadioGroup rdgGender;
    RadioButton rdbPr, rdbLk;
    Button btnBatal, btnTambah;
    ImageView imgKembali;
    Realm realm;
    String catatanId = null;

    public static CreateCttnFragment newInstance(String id) {
        CreateCttnFragment fragment = new CreateCttnFragment();
        Bundle args = new Bundle();
        args.putString("id", id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_create_cttn, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 🔁 Init Realm
        Realm.init(requireContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .build();
        realm = Realm.getInstance(config);

        if (getArguments() != null) {
            catatanId = getArguments().getString("id");
        }

        // Inisialisasi view
        edtNama = view.findViewById(R.id.edtNama);
        edtUmur = view.findViewById(R.id.edtUmur);
        edtObs = view.findViewById(R.id.edtObs);
        rdgGender = view.findViewById(R.id.rdgGender);
        rdbPr = view.findViewById(R.id.rdbPr);
        rdbLk = view.findViewById(R.id.rdbLk);
        btnTambah = view.findViewById(R.id.btnTambah);
        btnBatal = view.findViewById(R.id.btnBatal);
        imgKembali = view.findViewById(R.id.imgKembali);

        imgKembali.setOnClickListener(v -> requireActivity().onBackPressed());

        // Mode edit → isi form-nya
            if (catatanId != null) {
                Catatan model = realm.where(Catatan.class).equalTo("id", catatanId).findFirst();
                if (model != null) {
                    edtNama.setText(model.getNama());
                    edtUmur.setText(String.valueOf(model.getUmur()));
                    edtObs.setText(model.getObservasi());

                    if ("Perempuan".equals(model.getGender())) {
                        rdbPr.setChecked(true);
                    } else if ("Laki-Laki".equals(model.getGender())) {
                        rdbLk.setChecked(true);
                    }

                    btnTambah.setText("Simpan");
                } else {
                    // Mode Create → kosongkan pilihan radio
                    rdgGender.clearCheck();
                }
            }

            // Tombol Tambah / Simpan
            btnTambah.setOnClickListener(v -> {
                String nama = edtNama.getText().toString().trim();
                String umurStr = edtUmur.getText().toString().trim();
                String observasi = edtObs.getText().toString().trim();
                String gender = (rdgGender.getCheckedRadioButtonId() == R.id.rdbPr) ? "Perempuan" : "Laki-Laki";

                if (TextUtils.isEmpty(nama) || TextUtils.isEmpty(umurStr)) {
                    Toast.makeText(getContext(), "Nama dan umur wajib diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                int umur = Integer.parseInt(umurStr);

                if (catatanId == null) {
                    // ➕ Tambah data baru
                    String newId = UUID.randomUUID().toString();
                    realm.executeTransaction(r -> {
                        Catatan model = r.createObject(Catatan.class, newId);
                        model.setNama(nama);
                        model.setUmur(umur);
                        model.setGender(gender);
                        model.setObservasi(observasi);
                    });
                    Toast.makeText(getContext(), "Data berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                } else {
                    // ✏️ Edit data lama
                    realm.executeTransaction(r -> {
                        Catatan model = r.where(Catatan.class).equalTo("id", catatanId).findFirst();
                        if (model != null) {
                            model.setNama(nama);
                            model.setUmur(umur);
                            model.setGender(gender);
                            model.setObservasi(observasi);
                        }
                    });
                    Toast.makeText(getContext(), "Data berhasil diperbarui", Toast.LENGTH_SHORT).show();
                }

                requireActivity().onBackPressed(); // kembali ke halaman sebelumnya
            });

            // ❌ Tombol Batal juga kembali
            btnBatal.setOnClickListener(v -> requireActivity().onBackPressed());
        }
    }