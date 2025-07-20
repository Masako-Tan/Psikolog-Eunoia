package edu.uph.m23si1.aplikasi_psikolog.ui.catatan;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import edu.uph.m23si1.aplikasi_psikolog.R;
import edu.uph.m23si1.aplikasi_psikolog.model.Catatan;
import edu.uph.m23si1.aplikasi_psikolog.model.Pasien;
import io.realm.Realm;
import io.realm.RealmConfiguration;


public class CreateCttnFragment extends Fragment {
    Spinner spinnerNama;
    EditText edtUmur, edtObs;
    RadioGroup rdgGender;
    RadioButton rdbPr, rdbLk;
    Button btnBatal, btnTambah;
    ImageView imgKembali;

    Realm realm;
    String catatanId = null;

    List<Pasien> pasienList = new ArrayList<>();

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
        realm = Realm.getDefaultInstance();

        if (getArguments() != null) {
            catatanId = getArguments().getString("id");
        }

        // 📌 Inisialisasi View
        spinnerNama = view.findViewById(R.id.spinnerNama);
        edtUmur = view.findViewById(R.id.edtUmur);
        edtObs = view.findViewById(R.id.edtObs);
        rdgGender = view.findViewById(R.id.rdgGender);
        rdbPr = view.findViewById(R.id.rdbPr);
        rdbLk = view.findViewById(R.id.rdbLk);
        btnTambah = view.findViewById(R.id.btnTambah);
        btnBatal = view.findViewById(R.id.btnBatal);
        imgKembali = view.findViewById(R.id.imgKembali);

        imgKembali.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main, new CatatanFragment())
                .commit());
        btnBatal.setOnClickListener(v -> requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main, new CatatanFragment())
                .commit());

        // 📌 Ambil data Pasien dari Realm
        pasienList = realm.where(Pasien.class).findAll();

        List<String> namaPasien = new ArrayList<>();
        for (Pasien p : pasienList) {
            namaPasien.add(p.getNama());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_item,
                namaPasien
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNama.setAdapter(adapter);

        // 📌 Saat spinner dipilih → update form
        spinnerNama.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(android.widget.AdapterView<?> parent, View view, int position, long id) {
                Pasien selected = pasienList.get(position);
                edtUmur.setText(String.valueOf(selected.getUmur()));

                if ("Perempuan".equalsIgnoreCase(selected.getJenisKelamin())) {
                    rdbPr.setChecked(true);
                } else if ("Laki-Laki".equalsIgnoreCase(selected.getJenisKelamin())) {
                    rdbLk.setChecked(true);
                } else {
                    rdgGender.clearCheck();
                }
            }

            @Override
            public void onNothingSelected(android.widget.AdapterView<?> parent) {
                edtUmur.setText("");
                rdgGender.clearCheck();
            }
        });

        // 📌 Mode Edit
        if (catatanId != null) {
            Catatan model = realm.where(Catatan.class).equalTo("id", catatanId).findFirst();
            if (model != null) {
                // pilih spinner sesuai nama
                int pos = namaPasien.indexOf(model.getNama());
                if (pos >= 0) spinnerNama.setSelection(pos);

                edtObs.setText(model.getObservasi());
                btnTambah.setText("Simpan");
            }
        }

        // 📌 Tombol Simpan / Tambah
        btnTambah.setOnClickListener(v -> {
            String nama = spinnerNama.getSelectedItem().toString();
            String umurStr = edtUmur.getText().toString().trim();
            String observasi = edtObs.getText().toString().trim();
            String gender = (rdgGender.getCheckedRadioButtonId() == R.id.rdbPr) ? "Perempuan" : "Laki-Laki";

            if (TextUtils.isEmpty(nama) || TextUtils.isEmpty(umurStr)) {
                Toast.makeText(getContext(), "Nama dan umur wajib diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            int umur = Integer.parseInt(umurStr);

            if (catatanId == null) {
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

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main, new CatatanFragment())
                    .commit();
        });
    }
}