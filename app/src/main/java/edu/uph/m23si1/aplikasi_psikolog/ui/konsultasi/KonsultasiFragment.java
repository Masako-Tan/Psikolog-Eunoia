package edu.uph.m23si1.aplikasi_psikolog.ui.konsultasi;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.uph.m23si1.aplikasi_psikolog.ChatActivity;
import edu.uph.m23si1.aplikasi_psikolog.R;
import edu.uph.m23si1.aplikasi_psikolog.adapter.PasienChatAdapter;
import edu.uph.m23si1.aplikasi_psikolog.model.Pasien;
import io.realm.Realm;

public class KonsultasiFragment extends Fragment {
    RecyclerView rvPasien;
    EditText searchEditText;
    PasienChatAdapter adapter;
    List<Pasien> pasienList;
    Realm realm;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_konsultasi, container, false);

        searchEditText = view.findViewById(R.id.searchChat);
        rvPasien = view.findViewById(R.id.rvPasien);

        realm = Realm.getDefaultInstance();

        pasienList = realm.where(Pasien.class).findAll();

        adapter = new PasienChatAdapter(requireContext(), pasienList, pasien -> {
            Intent intent = new Intent(requireContext(), ChatActivity.class);
            intent.putExtra("namaPasien", pasien.getNama());
            startActivity(intent);
        });

        rvPasien.setLayoutManager(new LinearLayoutManager(requireContext()));
        rvPasien.setAdapter(adapter);
        rvPasien.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
                                       @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.bottom = 5;
            }
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase();
                List<Pasien> filtered = new ArrayList<>();
                for (Pasien p : pasienList) {
                    if (p.getNama().toLowerCase().contains(query)) {
                        filtered.add(p);
                    }
                }
                adapter.setFilter(filtered);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return view;
    }
}