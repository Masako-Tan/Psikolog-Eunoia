package edu.uph.m23si1.aplikasi_psikolog.ui.konsultasi;

import android.content.Intent;
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

import java.util.HashMap;
import java.util.Map;

import edu.uph.m23si1.aplikasi_psikolog.ChatActivity;
import edu.uph.m23si1.aplikasi_psikolog.R;

public class KonsultasiFragment extends Fragment {
    LinearLayout Icha, Supran, Herman, Budiman, Kolanh, Teti, Susan, Mass, Tono;
    EditText searchEditText;
    private Map<String, LinearLayout> pasienMap = new HashMap<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_konsultasi, container, false);

        searchEditText = view.findViewById(R.id.searchChat);

        // Ambil referensi pasien layout
        pasienMap.put("icha", view.findViewById(R.id.Icha));
        pasienMap.put("supra", view.findViewById(R.id.Supra));
        pasienMap.put("herman", view.findViewById(R.id.Herman));
        pasienMap.put("budiman", view.findViewById(R.id.Budiman));
        pasienMap.put("kolang", view.findViewById(R.id.Kolang));
        pasienMap.put("teti", view.findViewById(R.id.Teti));
        pasienMap.put("susan", view.findViewById(R.id.Susan));
        pasienMap.put("mass", view.findViewById(R.id.Mass));
        pasienMap.put("tono", view.findViewById(R.id.Tono));

        // Tambahkan listener untuk navigasi ke ChatActivity saat ditekan
        for (Map.Entry<String, LinearLayout> entry : pasienMap.entrySet()) {
            LinearLayout layout = entry.getValue();
            layout.setOnClickListener(v -> {
                Intent intent = new Intent(requireContext(), ChatActivity.class);
                intent.putExtra("nama_pasien", entry.getKey());
                startActivity(intent);
            });
        }

        // Search function
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().toLowerCase();
                for (Map.Entry<String, LinearLayout> entry : pasienMap.entrySet()) {
                    LinearLayout layout = entry.getValue();
                    TextView nameView = (TextView) layout.getChildAt(1);
                    String fullName = nameView.getText().toString().toLowerCase();
                    if (fullName.contains(query)) {
                        layout.setVisibility(View.VISIBLE);
                    } else {
                        layout.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        return view;
    }
}
