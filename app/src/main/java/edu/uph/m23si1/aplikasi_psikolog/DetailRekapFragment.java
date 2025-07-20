package edu.uph.m23si1.aplikasi_psikolog;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import edu.uph.m23si1.aplikasi_psikolog.model.Pasien;
import edu.uph.m23si1.aplikasi_psikolog.model.Tes;
import io.realm.Realm;
import io.realm.RealmResults;

public class DetailRekapFragment extends Fragment {

    ImageView imgKembali, imgFotoicha;
    TextView txvIcha, txvUmuricha, txvPerempuan;

    TextView txvTanggal, txvSkor, txvStatusdep, txvDetail;
    TextView txvTanggal1, txvSkor1, txvStatusdep1, txvDetail1;
    TextView txvTanggal2, txvSkor2, txvStatusdep2, txvDetail2;
    TextView txvTanggal3, txvSkor3, txvStatusdep3, txvDetail3;
    TextView txvTanggal4, txvSkor4, txvStatusdep4, txvDetail4;

    Realm realm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_rekap, container, false);

        Realm.init(requireContext());
        realm = Realm.getDefaultInstance();

        String email = getArguments() != null ? getArguments().getString("email") : null;

        imgKembali = view.findViewById(R.id.imgKembali);
        imgFotoicha = view.findViewById(R.id.imgFotoicha);
        txvIcha = view.findViewById(R.id.txvIcha);
        txvUmuricha = view.findViewById(R.id.txvUmuricha);
        txvPerempuan = view.findViewById(R.id.txvPerempuan);

        txvTanggal = view.findViewById(R.id.txvTanggal);
        txvSkor = view.findViewById(R.id.txvSkor);
        txvStatusdep = view.findViewById(R.id.txvStatusdep);
        txvDetail = view.findViewById(R.id.txvDetail);

        txvTanggal1 = view.findViewById(R.id.txvTanggal1);
        txvSkor1 = view.findViewById(R.id.txvSkor1);
        txvStatusdep1 = view.findViewById(R.id.txvStatusdep1);
        txvDetail1 = view.findViewById(R.id.txvDetail1);

        txvTanggal2 = view.findViewById(R.id.txvTanggal2);
        txvSkor2 = view.findViewById(R.id.txvSkor2);
        txvStatusdep2 = view.findViewById(R.id.txvStatusdep2);
        txvDetail2 = view.findViewById(R.id.txvDetail2);

        txvTanggal3 = view.findViewById(R.id.txvTanggal3);
        txvSkor3 = view.findViewById(R.id.txvSkor3);
        txvStatusdep3 = view.findViewById(R.id.txvStatusdep3);
        txvDetail3 = view.findViewById(R.id.txvDetail3);

        txvTanggal4 = view.findViewById(R.id.txvTanggal4);
        txvSkor4 = view.findViewById(R.id.txvSkor4);
        txvStatusdep4 = view.findViewById(R.id.txvStatusdep4);
        txvDetail4 = view.findViewById(R.id.txvDetail4);

        if (email != null) {
            Pasien pasien = realm.where(Pasien.class).equalTo("email", email).findFirst();
            if (pasien != null) {
                // tampilkan profil pasien
                txvIcha.setText(pasien.getNama());
                txvUmuricha.setText(pasien.getUmur() + " Tahun");
                txvPerempuan.setText(pasien.getJenisKelamin());
                imgFotoicha.setImageResource(pasien.getFotoResId());

                // update semua tes dummy sesuai pasien
                realm.executeTransaction(r -> {
                    RealmResults<Tes> listTes = r.where(Tes.class).findAll();
                    if (listTes.isEmpty()) {
                        newTes(r);
                    }
                    for (Tes tes : listTes) {
                        tes.setUmur(pasien.getUmur());
                        tes.setGender(pasien.getJenisKelamin());
                    }
                });
            }
        }

        // tampilkan data tes ke UI
        RealmResults<Tes> listTes = realm.where(Tes.class).findAll();

        if (listTes.size() >= 5) {
            Tes tes1 = listTes.get(0);
            Tes tes2 = listTes.get(1);
            Tes tes3 = listTes.get(2);
            Tes tes4 = listTes.get(3);
            Tes tes5 = listTes.get(4);

            txvTanggal.setText(tes1.getTanggal());
            txvSkor.setText("Skor: " + tes1.getSkor());
            txvStatusdep.setText("Status: " + tes1.getStatus());

            txvTanggal1.setText(tes2.getTanggal());
            txvSkor1.setText("Skor: " + tes2.getSkor());
            txvStatusdep1.setText("Status: " + tes2.getStatus());

            txvTanggal2.setText(tes3.getTanggal());
            txvSkor2.setText("Skor: " + tes3.getSkor());
            txvStatusdep2.setText("Status: " + tes3.getStatus());

            txvTanggal3.setText(tes4.getTanggal());
            txvSkor3.setText("Skor: " + tes4.getSkor());
            txvStatusdep3.setText("Status: " + tes4.getStatus());

            txvTanggal4.setText(tes5.getTanggal());
            txvSkor4.setText("Skor: " + tes5.getSkor());
            txvStatusdep4.setText("Status: " + tes5.getStatus());
        }

        imgKembali.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(DetailRekapFragment.this);
            navController.navigateUp();
        });

        setTesDetailClick(txvDetail, 1);
        setTesDetailClick(txvDetail1, 2);
        setTesDetailClick(txvDetail2, 3);
        setTesDetailClick(txvDetail3, 4);
        setTesDetailClick(txvDetail4, 5);

        return view;
    }

    private void setTesDetailClick(TextView view, int idTes) {
        view.setOnClickListener(v -> {
            Tes tes = realm.where(Tes.class).equalTo("id", idTes).findFirst();
            if (tes != null) {
                Intent intent = new Intent(requireContext(), HasilTesActivity.class);
                intent.putExtra("idTes", tes.getId());
                startActivity(intent);
            }
        });
    }

    private void newTes(Realm r) {
        r.insertOrUpdate(new Tes(
                1, 19, "Perempuan", "Pedesaan", "0.00 - 2.49", "Signifikan",
                4, 4, 0, "Stress", 0, false, false, false,
                true, "22:00", "06:00", true, 3, 78,
                "Depresi", "28 Februari 2025"
        ));

        r.insertOrUpdate(new Tes(
                2, 19, "Perempuan", "Perkotaan", "2.50 - 3.49", "Sedang",
                3, 2, 2, "Cemas", 2,
                false, true, true, true,
                "23:00", "07:00", true, 2,
                65, "Waspada", "15 Maret 2025"
        ));

        r.insertOrUpdate(new Tes(
                3, 19, "Perempuan", "Perkotaan", "2.50 - 3.49", "Ringan",
                1, 1, 3, "Tenang", 3,
                true, true, true, false,
                "00:00", "08:00", false, 1,
                40, "Waspada", "28 Mei 2025"
        ));

        r.insertOrUpdate(new Tes(
                4, 20, "Perempuan", "Perkotaan", "3.50 - 4.00", "Ringan",
                0, 0, 4, "Netral", 4,
                true, true, true, false,
                "21:00", "05:00", false, 0,
                20, "Tidak Depresi", "17 Juni 2025"
        ));

        r.insertOrUpdate(new Tes(
                5, 20, "Perempuan", "Perkotaan", "3.50 - 4.00", "Ringan",
                1, 0, 4, "Bahagia", 4,
                true, true, true, false,
                "22:30", "06:30", false, 0,
                15, "Tidak Depresi", "11 Juli 2025"
        ));

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
