package edu.uph.m23si1.aplikasi_psikolog;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import edu.uph.m23si1.aplikasi_psikolog.databinding.ActivityMain1Binding;

public class MainActivity1 extends AppCompatActivity {

    private ActivityMain1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMain1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_beranda, R.id.navigation_rekap, R.id.navigation_konsultasi, R.id.navigation_catatan)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main1);
        NavigationUI.setupWithNavController(binding.navView, navController);
    }

}