package edu.uph.m23si1.aplikasi_psikolog.ui.rekap;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import edu.uph.m23si1.aplikasi_psikolog.DetailRekapFragment;
import edu.uph.m23si1.aplikasi_psikolog.model.PasienRekap;
import edu.uph.m23si1.aplikasi_psikolog.ui.catatan.CatatanFragment;
import edu.uph.m23si1.aplikasi_psikolog.ui.konsultasi.KonsultasiFragment;
import edu.uph.m23si1.aplikasi_psikolog.R;
import edu.uph.m23si1.aplikasi_psikolog.ui.beranda.BerandaFragment;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class RekapFragment extends Fragment {
    ImageView imgLogo, imgIcha, imgAsep, imgSeny, imgRea, imgRhoma, imgIrama;
    TextView txvRekap, txvIcha, txvUmuricha, txvHubung, txvPerempuan, txvJurusanicha, txvEmailicha, txvAsep,txvUmurasep, txvHubung1, txvLaki, txvJurusanasep, txvEmailasep, txvSeny, txvUmurseny, txvHubung2, txvPerempuan1, txvJurusanseny, txvEmailseny, txvRea, txvUmurrea, txvHubung3, txvPerempuan2, txvJurusanrea, txvEmailrea, txvRhoma, txvUmurrhoma, txvHubung4, txvLaki1, txvJurusanrhoma, txvEmailrhoma, txvIrama, txvUmurirama, txvHubung5, txvLaki2, txvJurusanirama, txvEmailirama;
    Button btnSelengkapnya, btnSelengkapnya1, btnSelengkapnya2, btnSelengkapnya3, btnSelengkapnya4, btnSelengkapnya5;
    private Realm realm;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rekap, container, false);

        // Inisialisasi Realm (harus di awal)
        Realm.init(requireContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("app.realm") // atau nama lainnya, asal konsisten
                .deleteRealmIfMigrationNeeded()
                .allowWritesOnUiThread(true) // ← WAJIB DITAMBAHKAN
                .build();
        realm = Realm.getInstance(config);



        imgLogo = view.findViewById(R.id.imgLogo);
        imgIcha = view.findViewById(R.id.imgIcha);
        imgAsep = view.findViewById(R.id.imgAsep);
        imgSeny = view.findViewById(R.id.imgSeny);
        imgRea = view.findViewById(R.id.imgRea);
        imgRhoma = view.findViewById(R.id.imgRhoma);
        imgIrama = view.findViewById(R.id.imgIrama);

        txvRekap = view.findViewById(R.id.txvRekap);

        txvIcha = view.findViewById(R.id.txvIcha);
        txvUmuricha = view.findViewById(R.id.txvUmuricha);
        txvHubung = view.findViewById(R.id.txvHubung);
        txvPerempuan = view.findViewById(R.id.txvPerempuan);
        txvJurusanicha = view.findViewById(R.id.txvJurusanicha);
        txvEmailicha = view.findViewById(R.id.txvEmailicha);

        txvAsep = view.findViewById(R.id.txvAsep);
        txvUmurasep = view.findViewById(R.id.txvUmurasep);
        txvHubung1 = view.findViewById(R.id.txvHubung1);
        txvLaki = view.findViewById(R.id.txvLaki);
        txvJurusanasep = view.findViewById(R.id.txvJurusanasep);
        txvEmailasep = view.findViewById(R.id.txvEmailasep);

        txvSeny = view.findViewById(R.id.txvSeny);
        txvUmurseny = view.findViewById(R.id.txvUmurseny);
        txvHubung2 = view.findViewById(R.id.txvHubung2);
        txvPerempuan1 = view.findViewById(R.id.txvPerempuan1);
        txvJurusanseny = view.findViewById(R.id.txvJurusanseny);
        txvEmailseny = view.findViewById(R.id.txvEmailseny);

        txvRea = view.findViewById(R.id.txvRea);
        txvUmurrea = view.findViewById(R.id.txvUmurrea);
        txvHubung3 = view.findViewById(R.id.txvHubung3);
        txvPerempuan2 = view.findViewById(R.id.txvPerempuan2);
        txvJurusanrea = view.findViewById(R.id.txvJurusanrea);
        txvEmailrea = view.findViewById(R.id.txvEmailrea);

        txvRhoma = view.findViewById(R.id.txvRhoma);
        txvUmurrhoma = view.findViewById(R.id.txvUmurrhoma);
        txvHubung4 = view.findViewById(R.id.txvHubung4);
        txvLaki1 = view.findViewById(R.id.txvLaki1);
        txvJurusanrhoma = view.findViewById(R.id.txvJurusanrhoma);
        txvEmailrhoma = view.findViewById(R.id.txvEmailrhoma);

        txvIrama = view.findViewById(R.id.txvIrama);
        txvUmurirama = view.findViewById(R.id.txvUmurirama);
        txvHubung5 = view.findViewById(R.id.txvHubung5);
        txvLaki2 = view.findViewById(R.id.txvLaki2);
        txvJurusanirama = view.findViewById(R.id.txvJurusanirama);
        txvEmailirama = view.findViewById(R.id.txvEmailirama);

        btnSelengkapnya = view.findViewById(R.id.btnSelengkapnya);
        btnSelengkapnya1 = view.findViewById(R.id.btnSelengkapnya1);
        btnSelengkapnya2 = view.findViewById(R.id.btnSelengkapnya2);
        btnSelengkapnya3 = view.findViewById(R.id.btnSelengkapnya3);
        btnSelengkapnya4 = view.findViewById(R.id.btnSelengkapnya4);
        btnSelengkapnya5 = view.findViewById(R.id.btnSelengkapnya5);

        //Tombol Selengkapnya
        btnSelengkapnya.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(RekapFragment.this);
            navController.navigate(R.id.action_navigation_rekap_to_detailRekapFragment);
        });


        // Tambah data dummy kalau belum ada
        tambahDataCoba();

        // Ambil dan tampilkan data ke CardView
        tampilkanData(view);

        return view;

    }

    private void tambahDataCoba() {
        RealmResults<PasienRekap> data = realm.where(PasienRekap.class).findAll();
        if (data.isEmpty()) {
            realm.executeTransaction(r -> {
                r.insert(new PasienRekap("icha.septina@gmail.com", "Icha Septina", "19 Tahun", "Perempuan", "Informatika", R.drawable.icha));
                r.insert(new PasienRekap("yovaraasepp@gmail.com", "Asep Yovara", "20 Tahun", "Laki-laki", "Hukum", R.drawable.asep));
                r.insert(new PasienRekap("sen@gmail.com", "Seny Caroline", "22 Tahun", "Perempuan", "Teknik Kimia", R.drawable.senyy));
                r.insert(new PasienRekap("parulian@gmail.com", "Rea Parulian", "21 Tahun", "Perempuan", "Sistem Informasi", R.drawable.rea));
                r.insert(new PasienRekap("rhomaa@gmail.com", "Rhoma", "20 Tahun", "Laki-laki", "Teknik Industri", R.drawable.rhoma));
                r.insert(new PasienRekap("irama@gmail.com", "Irama", "22 Tahun", "Laki-laki", "Seni Musik", R.drawable.irama));
            });
        }
    }

    private void tampilkanData(View view) {
        RealmResults<PasienRekap> hasil = realm.where(PasienRekap.class).findAll();

        for (PasienRekap pasien : hasil) {
            switch (pasien.getNama()) {
                case "Icha Septina":
                    isiCard(view, R.id.imgIcha, R.id.txvIcha, R.id.txvUmuricha, R.id.txvPerempuan, R.id.txvJurusanicha, R.id.txvEmailicha, pasien);
                    break;
                case "Asep Yovara":
                    isiCard(view, R.id.imgAsep, R.id.txvAsep, R.id.txvUmurasep, R.id.txvLaki, R.id.txvJurusanasep, R.id.txvEmailasep, pasien);
                    break;
                case "Seny Caroline":
                    isiCard(view, R.id.imgSeny, R.id.txvSeny, R.id.txvUmurseny, R.id.txvPerempuan1, R.id.txvJurusanseny, R.id.txvEmailseny, pasien);
                    break;
                case "Rea Parulian":
                    isiCard(view, R.id.imgRea, R.id.txvRea, R.id.txvUmurrea, R.id.txvPerempuan2, R.id.txvJurusanrea, R.id.txvEmailrea, pasien);
                    break;
                case "Rhoma":
                    isiCard(view, R.id.imgRhoma, R.id.txvRhoma, R.id.txvUmurrhoma, R.id.txvLaki1, R.id.txvJurusanrhoma, R.id.txvEmailrhoma, pasien);
                    break;
                case "Irama":
                    isiCard(view, R.id.imgIrama, R.id.txvIrama, R.id.txvUmurirama, R.id.txvLaki2, R.id.txvJurusanirama, R.id.txvEmailirama, pasien);
                    break;
            }
        }
    }

    private void isiCard(View rootView, int idFoto, int idNama, int idUmur, int idGender, int idJurusan, int idEmail, PasienRekap pasien) {
        ImageView foto = rootView.findViewById(idFoto);
        TextView nama = rootView.findViewById(idNama);
        TextView umur = rootView.findViewById(idUmur);
        TextView gender = rootView.findViewById(idGender);
        TextView jurusan = rootView.findViewById(idJurusan);
        TextView email = rootView.findViewById(idEmail);

        foto.setImageResource(pasien.getFotoResId());
        nama.setText(pasien.getNama());
        umur.setText(pasien.getUmur());
        gender.setText(pasien.getJenisKelamin());
        jurusan.setText(pasien.getJurusan());
        email.setText(pasien.getEmail());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }





}