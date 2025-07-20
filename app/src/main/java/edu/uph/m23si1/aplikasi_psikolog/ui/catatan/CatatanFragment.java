package edu.uph.m23si1.aplikasi_psikolog.ui.catatan;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.uph.m23si1.aplikasi_psikolog.R;
import edu.uph.m23si1.aplikasi_psikolog.adapter.CatatanAdapter;
import edu.uph.m23si1.aplikasi_psikolog.model.Catatan;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class CatatanFragment extends Fragment {

    RecyclerView rvCatatan;
    ImageView imgAdd;
    CatatanAdapter adapter;
    Realm realm;
    RealmResults<Catatan> catatanList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_catatan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvCatatan = view.findViewById(R.id.rvCatatan);
        imgAdd = view.findViewById(R.id.imgAdd);

        // Init Realm
        Realm.init(requireContext());
        realm = Realm.getDefaultInstance();

        // Ambil data Catatan
        catatanList = realm.where(Catatan.class).findAll();

        // Pasang adapter
        adapter = new CatatanAdapter(catatanList, new CatatanAdapter.OnCatatanClickListener() {
            @Override
            public void onEditClick(String catatanId) {
                // buka fragment edit
                FragmentTransaction ft = requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction();
                ft.replace(R.id.main, CreateCttnFragment.newInstance(catatanId));
                ft.addToBackStack(null);
                ft.commit();
            }

            @Override
            public void onDeleteClick(Catatan catatan) {
                realm.executeTransaction(r -> catatan.deleteFromRealm());
            }
        });

        rvCatatan.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCatatan.setAdapter(adapter);

        // Listen ke perubahan Realm
        catatanList.addChangeListener(results -> adapter.notifyDataSetChanged());

        // Tambah data
        imgAdd.setOnClickListener(v -> {
            FragmentTransaction ft = requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction();
            ft.replace(R.id.main, CreateCttnFragment.newInstance(null));
            ft.addToBackStack(null);
            ft.commit();
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (realm != null) {
            realm.close();
        }
    }
}