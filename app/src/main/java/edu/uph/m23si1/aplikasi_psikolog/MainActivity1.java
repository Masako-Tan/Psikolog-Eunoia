package edu.uph.m23si1.aplikasi_psikolog;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
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

        BottomNavigationView navView = binding.navView;


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment_activity_main1);

        if (navHostFragment == null) {
            throw new IllegalStateException("NavHostFragment not found. Cek ID-nya!");
        }

        NavController navController = navHostFragment.getNavController();

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_beranda,
                R.id.navigation_rekap,
                R.id.navigation_konsultasi,
                R.id.navigation_catatan
        ).build();

        NavigationUI.setupWithNavController(navView, navController);
    }
}