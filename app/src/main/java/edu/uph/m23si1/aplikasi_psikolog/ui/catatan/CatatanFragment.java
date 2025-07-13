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


public class CatatanFragment extends Fragment implements CatatanAdapter.OnCatatanClickListener {
    Button btnEdit, btnDelete;
    ImageView imgAdd;
    Realm realm;
    RecyclerView rvCatatan;
    CatatanAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout first
        View view = inflater.inflate(R.layout.fragment_catatan, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize Realm
        Realm.init(requireContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .build();
        realm = Realm.getInstance(config);

        // Initialize views
        imgAdd = view.findViewById(R.id.imgAdd);
        rvCatatan = view.findViewById(R.id.rvCatatan);

        // MUST SET LAYOUT MANAGER BEFORE SETTING ADAPTER
        rvCatatan.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize adapter with empty list first
        adapter = new CatatanAdapter(new ArrayList<>(), this);
        rvCatatan.setAdapter(adapter);

        imgAdd.setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.nav_host_fragment_activity_main1, new CreateCttnFragment())
                    .addToBackStack(null)
                    .commit();
        });

        loadData();
    }

    private void loadData() {
        RealmResults<Catatan> daftarCatatan = realm.where(Catatan.class).findAll();
        // Update adapter with new data
        adapter = new CatatanAdapter(daftarCatatan, this);
        rvCatatan.setAdapter(adapter);
    }

    @Override
    public void onEditClick(String catatanId) {
        FragmentTransaction transaction = requireActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_activity_main1, CreateCttnFragment.newInstance(catatanId))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onDeleteClick(Catatan catatan) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Konfirmasi")
                .setMessage("Yakin ingin menghapus catatan ini?")
                .setPositiveButton("Ya", (dialog, which) -> {
                    realm.executeTransaction(r -> {
                        Catatan target = r.where(Catatan.class).equalTo("id", catatan.getId()).findFirst();
                        if (target != null) {
                            target.deleteFromRealm();
                            Toast.makeText(getContext(), "Data dihapus", Toast.LENGTH_SHORT).show();
                            loadData();
                        }
                    });
                })
                .setNegativeButton("Batal", null)
                .show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
        }
    }
}