package edu.uph.m23si1.aplikasi_psikolog;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.uph.m23si1.aplikasi_psikolog.model.Pasien;
import edu.uph.m23si1.aplikasi_psikolog.model.Tes;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.Sort;

public class ChatActivity extends AppCompatActivity {

    LinearLayout chatContainer, llySend, llyBack, llyChatHasilTes, llyBoxHasil;
    EditText edtChat;
    ScrollView chatScroll;
    TextView txvNamaPsi, txvChatPembuka, txvSkorStatus, txvDetail;
    String namaPasien;
    String nama = "Icha Septina";
    SharedPreferences prefs;
    String prefsKey;
    Realm realm;
    ImageView imgFotoPasien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Realm config
        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);

        setContentView(R.layout.activity_chat);
        realm = Realm.getDefaultInstance();

        // Bind view
        txvNamaPsi = findViewById(R.id.txvNamaPsi);
        chatContainer = findViewById(R.id.chatContainer);
        edtChat = findViewById(R.id.edtChat);
        chatScroll = findViewById(R.id.chatScroll);
        llySend = findViewById(R.id.llySend);
        llyBack = findViewById(R.id.llyBack);
        txvChatPembuka = findViewById(R.id.txvChatPembuka);
        llyChatHasilTes = findViewById(R.id.llyChatHasilTes);
        llyBoxHasil = findViewById(R.id.llyBoxHasil);
        txvSkorStatus = findViewById(R.id.txvSkorStatus);
        txvDetail = findViewById(R.id.txvDetail);

        // Dapatkan namaPasien dari intent
        namaPasien = getIntent().getStringExtra("namaPasien");
        Pasien pasien = realm.where(Pasien.class)
                .equalTo("nama", namaPasien)
                .findFirst();

        if (pasien != null) {
            // Set nama di header
            txvNamaPsi.setText(pasien.getNama());

            // Set foto (pastikan `getFotoResId()` atau `getFotoPath()` sesuai model kamu)
            imgFotoPasien = findViewById(R.id.imgFotoPasien);
            imgFotoPasien.setImageResource(pasien.getFotoResId());
        }
        if (namaPasien == null) namaPasien = "Pasien"; // default fallback

        // Ambil hasil tes id = 5
        Tes hasilTes5 = realm.where(Tes.class).equalTo("id", 5).findFirst();
        llyChatHasilTes.setVisibility(View.VISIBLE);
        if (hasilTes5 == null) {
            txvChatPembuka.setText("Hi Doctor, I just saw my result...");
            txvSkorStatus.setText("Belum ada skor");
            llyBoxHasil.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#DDDDDD")));
        } else {
            txvChatPembuka.setText("Halo dok, saya baru saja melihat hasil tes saya...");
            String teks = "Skor Pasien : " + hasilTes5.getSkor() +
                    "\nStatus: " + hasilTes5.getStatus();
            txvSkorStatus.setText(teks);

            // Klik lihat lebih lanjut
            txvDetail.setOnClickListener(v -> {
                Intent intent = new Intent(ChatActivity.this, HasilTesActivity.class);
                intent.putExtra("idTes", 5); // langsung id ke-5
                startActivity(intent);
            });
        }

        prefsKey = "chat_" + namaPasien + "_" + nama;
        prefs = getSharedPreferences("chat_history", MODE_PRIVATE);

        txvNamaPsi.setText(nama);

        // Tombol kembali
        llyBack.setOnClickListener(v -> finish());

        // Kirim pesan
        llySend.setOnClickListener(v -> {
            String pesan = edtChat.getText().toString().trim();
            if (!pesan.isEmpty()) {
                tambahPesanUser(pesan, namaPasien);
                simpanPesan(pesan, namaPasien);
                edtChat.setText("");
                scrollKeBawah();
            }
        });

        muatHistoriChat();
    }

    private void muatHistoriChat() {
        String histori = prefs.getString(prefsKey, null);
        if (histori != null) {
            try {
                JSONArray arr = new JSONArray(histori);
                for (int i = 0; i < arr.length(); i++) {
                    JSONObject obj = arr.getJSONObject(i);
                    String sender = obj.getString("sender");
                    String pesan = obj.getString("message");
                    tambahPesanUser(pesan, sender);
                }
                scrollKeBawah();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void simpanPesan(String pesan, String sender) {
        String histori = prefs.getString(prefsKey, null);
        JSONArray arr;
        try {
            if (histori != null) {
                arr = new JSONArray(histori);
            } else {
                arr = new JSONArray();
            }
            JSONObject obj = new JSONObject();
            obj.put("sender", sender);
            obj.put("message", pesan);
            arr.put(obj);
            prefs.edit().putString(prefsKey, arr.toString()).apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void tambahPesanUser(String teks, String sender) {
        TextView pesanView = new TextView(this);
        pesanView.setText(teks);
        pesanView.setTextColor(getResources().getColor(R.color.white));
        pesanView.setBackgroundResource(R.drawable.bubble_psi);
        pesanView.setPadding(25, 18, 25, 18);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 10);

        if (sender != null && sender.equals(namaPasien)) {
            params.gravity = android.view.Gravity.END;
        } else {
            params.gravity = android.view.Gravity.START;
        }
        pesanView.setLayoutParams(params);

        chatContainer.addView(pesanView);
    }

    private void scrollKeBawah() {
        chatScroll.post(() -> chatScroll.fullScroll(View.FOCUS_DOWN));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null && !realm.isClosed()) {
            realm.close();
        }
    }
}