package edu.uph.m23si1.aplikasi_psikolog;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import edu.uph.m23si1.aplikasi_psikolog.model.Akun;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class LoginActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    Button btnLogin;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Realm.init(getApplicationContext());
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("akun.realm")
                .schemaVersion(1)
                .allowWritesOnUiThread(true)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        initData();
        realm = Realm.getDefaultInstance();

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(v -> {
            String email = edtEmail.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            Akun akun = realm.where(Akun.class)
                    .equalTo("email", email)
                    .equalTo("password", password)
                    .findFirst();

            if (akun != null) {
                Toast.makeText(this, "Login berhasil!", Toast.LENGTH_SHORT).show();

                String namaLengkap = akun.getNamaLengkap();
                Intent intent = new Intent(LoginActivity.this, MainActivity1.class);
                intent.putExtra("namaLengkap", namaLengkap);
                startActivity(intent);
                finish();
            } else {
                Toast toast = Toast.makeText(getApplication(), "Email atau password tidak terdaftar", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                toast.show();
            }
        });
    }

    public void initData() {
        Realm realm = Realm.getDefaultInstance();
        long count = realm.where(Akun.class).count();
        if (count > 0) return;

        List<Akun> daftarAkun = new ArrayList<>();
        daftarAkun.add(new Akun("yosafa.zea@gmail.com", "12345", "Zea Yosafa"));
        daftarAkun.add(new Akun("khalea.yuna@gmail.com", "12345", "Yuna Khalea"));
        daftarAkun.add(new Akun("doe.john@gmail.com", "12345", "John Doe"));
        daftarAkun.add(new Akun("aurel.syah@gmail.com", "12345", "Aurel Syah"));
        daftarAkun.add(new Akun("renata@gmail.com", "12345", "Renata"));
        daftarAkun.add(new Akun("arvindo97@gmail.com", "12345", "Arvindo"));
        daftarAkun.add(new Akun("lasman27@gmail.com", "12345", "Lasman"));
        daftarAkun.add(new Akun("meli.dinda@gmail.com", "12345", "Dinda Meli"));
        daftarAkun.add(new Akun("setya.ayra@gmail.com", "12345", "Ayra Setya"));
        daftarAkun.add(new Akun("adita.ezra@gmail.com", "12345", "Ezra Adita"));

        realm.executeTransaction(r -> {
            r.insert(daftarAkun);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            realm.close();
        }
    }
}
