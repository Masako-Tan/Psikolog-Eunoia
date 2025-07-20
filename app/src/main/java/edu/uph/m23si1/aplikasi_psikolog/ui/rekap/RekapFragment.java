package edu.uph.m23si1.aplikasi_psikolog.ui.rekap;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import edu.uph.m23si1.aplikasi_psikolog.R;
import edu.uph.m23si1.aplikasi_psikolog.model.Pasien;
import edu.uph.m23si1.aplikasi_psikolog.model.PasienRekap;
import io.realm.Realm;
import io.realm.RealmResults;

public class RekapFragment extends Fragment {

    private LinearLayout llyContainer; // untuk menampung semua card pasien
    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rekap, container, false);

        // init Realm
        Realm.init(requireContext());
        realm = Realm.getDefaultInstance();

        llyContainer = view.findViewById(R.id.llyContainer);

        tampilkanDataPasien();

        return view;
    }

    private void tampilkanDataPasien() {
        RealmResults<Pasien> pasienList = realm.where(Pasien.class).findAll();
        // Bersihkan dulu container
        llyContainer.removeAllViews();

        for (Pasien pasien : pasienList) {
            View card = buatCardPasien(pasien);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // atau WRAP_CONTENT kalau mau lebar sesuai konten
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(32, 16, 32, 16); // jarak kiri, atas, kanan, bawah
            params.gravity = Gravity.CENTER_HORIZONTAL; // biar di tengah kalau lebar WRAP_CONTENT

            card.setLayoutParams(params);

            llyContainer.addView(card);
        }

    }

    private View buatCardPasien(Pasien pasien) {
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View cardView = inflater.inflate(R.layout.item_rekap, llyContainer, false);

        ImageView img = cardView.findViewById(R.id.imgIcha);
        TextView nama = cardView.findViewById(R.id.txvIcha);
        TextView umur = cardView.findViewById(R.id.txvUmuricha);
        TextView jenisKelamin = cardView.findViewById(R.id.txvPerempuan);
        TextView jurusan = cardView.findViewById(R.id.txvJurusanicha);
        TextView email = cardView.findViewById(R.id.txvEmailicha);
        Button btnDetail = cardView.findViewById(R.id.btnSelengkapnya);

        img.setImageResource(pasien.getFotoResId());
        nama.setText(pasien.getNama());
        umur.setText(pasien.getUmur() + " Tahun");
        jenisKelamin.setText(pasien.getJenisKelamin());
        jurusan.setText(pasien.getJurusan());
        email.setText(pasien.getEmail());

        btnDetail.setOnClickListener(v -> {
            // kirim data ke DetailRekapFragment
            Bundle bundle = new Bundle();
            bundle.putString("email", pasien.getEmail());
            NavController navController = NavHostFragment.findNavController(RekapFragment.this);
            navController.navigate(R.id.action_navigation_rekap_to_detailRekapFragment, bundle);
        });

        return cardView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
