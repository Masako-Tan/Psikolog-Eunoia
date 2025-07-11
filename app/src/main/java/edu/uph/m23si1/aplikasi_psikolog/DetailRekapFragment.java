package edu.uph.m23si1.aplikasi_psikolog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import edu.uph.m23si1.aplikasi_psikolog.ui.rekap.RekapFragment;

public class DetailRekapFragment extends Fragment {
    ImageView imgKembali, imgFotoicha;
    TextView txvIcha, txvUmuricha, txvHubung, txvPerempuan, txvStatus, txvPertama, txvTanggal, txvSkor, txvDetail, txvStatusdep, txvKedua, txvTanggal1, txvSkor1, txvDetail1, txvStatusdep1, txvKetiga, txvTanggal2, txvSkor2, txvDetail2, txvStatusdep2, txvKeempat, txvTanggal3, txvSkor3, txvDetail3, txvStatusdep3, txvKelima, txvTanggal4, txvSkor4, txvDetail4, txvStatusdep4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_rekap, container, false);

        imgKembali = view.findViewById(R.id.imgKembali);
        imgFotoicha = view.findViewById(R.id.imgFotoicha);
        txvIcha = view.findViewById(R.id.txvIcha);
        txvUmuricha = view.findViewById(R.id.txvUmuricha);
        txvHubung = view.findViewById(R.id.txvHubung);
        txvPerempuan = view.findViewById(R.id.txvPerempuan);
        txvStatus = view.findViewById(R.id.txvStatus);

        txvPertama = view.findViewById(R.id.txvPertama);
        txvTanggal = view.findViewById(R.id.txvTanggal);
        txvSkor = view.findViewById(R.id.txvSkor);
        txvDetail = view.findViewById(R.id.txvDetail);
        txvStatusdep = view.findViewById(R.id.txvStatusdep);

        txvKedua = view.findViewById(R.id.txvKedua);
        txvTanggal1 = view.findViewById(R.id.txvTanggal1);
        txvSkor1 = view.findViewById(R.id.txvSkor1);
        txvDetail1 = view.findViewById(R.id.txvDetail1);
        txvStatusdep1 = view.findViewById(R.id.txvStatusdep1);

        txvKetiga = view.findViewById(R.id.txvKetiga);
        txvTanggal2 = view.findViewById(R.id.txvTanggal2);
        txvSkor2 = view.findViewById(R.id.txvSkor2);
        txvDetail2 = view.findViewById(R.id.txvDetail2);
        txvStatusdep2 = view.findViewById(R.id.txvStatusdep2);

        txvKeempat = view.findViewById(R.id.txvKeempat);
        txvTanggal3 = view.findViewById(R.id.txvTanggal3);
        txvSkor3 = view.findViewById(R.id.txvSkor3);
        txvDetail3 = view.findViewById(R.id.txvDetail3);
        txvStatusdep3 = view.findViewById(R.id.txvStatusdep3);

        txvKelima = view.findViewById(R.id.txvKelima);
        txvTanggal4 = view.findViewById(R.id.txvTanggal4);
        txvSkor4 = view.findViewById(R.id.txvSkor4);
        txvDetail4 = view.findViewById(R.id.txvDetail4);
        txvStatusdep4 = view.findViewById(R.id.txvStatusdep4);

        //Tombol Balik
        imgKembali.setOnClickListener(v -> {
            NavController navController = NavHostFragment.findNavController(DetailRekapFragment.this);
            navController.navigateUp();
        });
        return view;

    }
}