package edu.uph.m23si1.aplikasi_psikolog;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    EditText edtUsername, edtEmail, edtPassword;
    Button btnLogin;

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

        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkUser();
            }
        });

    }

    public boolean ValidasiData(){
        if (edtUsername.getText().toString().equals("")){
            edtUsername.setError("Username harus diisi");
            return false;
        } else if (edtEmail.getText().toString().equals("")){
            edtEmail.setError("Email harus diisi");
            return false;
        }else if (edtPassword.getText().toString().equals("")){
            edtPassword.setError("Password harus diisi");
            return false;
        }
        return true;
    }

    public void checkUser(){
        if(ValidasiData()){
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            String email = edtEmail.getText().toString();
            if(username.toLowerCase().equals("masako") && email.equals("03081230053@student.uph.edu") && password.toLowerCase().equals("03081230053")){
                Intent intent = new Intent(this, BerandaActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            } else{
                Toast toast = Toast.makeText(getApplication(), "Akun Tidak terdaftar", Toast.LENGTH_LONG);
                toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
                toast.show();
            }
        }
    }
}