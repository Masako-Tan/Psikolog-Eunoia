package edu.uph.m23si1.aplikasi_psikolog;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class BerandaActivity extends AppCompatActivity {
    TextView txvBeranda,txvRemind1, txvRemind2, txvGrafik, txvPasien, txvIcha, txvAsep, txvSeny, txvRea, txvRhoma, txvIrama;
    ImageView imgLogo, imgIcha, imgAsep, imgSeny, imgRea, imgRhoma, imgIrama, imgPanah, imgPanah1, imgPanah2, imgPanah3, imgPanah4, imgPanah5;
    CheckBox chbIcha, chbAsep, chbSeny, chbRea, chbKonsulseny, chbIrama, chbRekaprea, chbRhoma;
    EditText edtCari;
    LinearLayout llyLegend, llyGrafik;
    CalendarView calendarView;
    BarChart barChart;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_beranda);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgLogo = findViewById(R.id.imgLogo);
        imgIcha = findViewById(R.id.imgIcha);
        imgAsep = findViewById(R.id.imgAsep);
        imgSeny = findViewById(R.id.imgSeny);
        imgRea = findViewById(R.id.imgRea);
        imgRhoma = findViewById(R.id.imgRhoma);
        imgIrama = findViewById(R.id.imgIrama);

        imgPanah = findViewById(R.id.imgPanah);
        imgPanah1 = findViewById(R.id.imgPanah1);
        imgPanah2 = findViewById(R.id.imgPanah2);
        imgPanah3 = findViewById(R.id.imgPanah3);
        imgPanah4 = findViewById(R.id.imgPanah4);
        imgPanah5 = findViewById(R.id.imgPanah5);

        txvBeranda = findViewById(R.id.txvBeranda);
        txvRemind1 = findViewById(R.id.txvRemind1);
        txvRemind2 = findViewById(R.id.txvRemind2);
        txvGrafik = findViewById(R.id.txvGrafik);
        txvPasien = findViewById(R.id.txvPasien);
        txvIcha = findViewById(R.id.txvIcha);
        txvAsep = findViewById(R.id.txvAsep);
        txvSeny = findViewById(R.id.txvSeny);
        txvRea = findViewById(R.id.txvRea);
        txvRhoma = findViewById(R.id.txvRhoma);
        txvIrama = findViewById(R.id.txvIrama);

        chbIcha = findViewById(R.id.chbIcha);
        chbAsep = findViewById(R.id.chbAsep);
        chbSeny = findViewById(R.id.chbSeny);
        chbRea = findViewById(R.id.chbRea);
        chbKonsulseny = findViewById(R.id.chbKonsulseny);
        chbIrama = findViewById(R.id.chbIrama);
        chbRekaprea = findViewById(R.id.chbRekaprea);
        chbRhoma = findViewById(R.id.chbRhoma);

        edtCari = findViewById(R.id.edtCari);
        calendarView = findViewById(R.id.calendarView);
        barChart = findViewById(R.id.barChart);
        bottomNav = findViewById(R.id.bottomNav);

        setupGrafikStresPasien();
        keteranganWarna();

    }

    private void setupGrafikStresPasien(){
        //Data pasien dan tingkat stress (0-10)
        String[] pasien = {"Icha", "Asep", "Seny", "Rea", "Rhoma", "Irama"};
        float[] stres = {3.0f, 9.0f, 4.5f, 7.0f, 6.50f, 8.5f}; //Urutan stresnya

        List<BarEntry> entries = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();
        for (int i = 0; i < stres.length; i++) {
            entries.add(new BarEntry(i, stres[i]));

            //Kategori warnanya
            if (stres[i] >= 7.0f) {
                colors.add(Color.RED); //Tinggi
            } else if (stres[i] >= 4.0f) {
                colors.add(Color.YELLOW); //Sedang
            } else {
                colors.add(Color.GREEN); //Rendah
            }
        }

        BarDataSet dataSet = new BarDataSet(entries, "");
        dataSet.setColors(colors);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setValueTextSize(12f);

        BarData barData = new BarData((dataSet));
        barChart.setData(barData);

        //Sumbu X
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(pasien));
        xAxis.setGranularity(1f);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setTextSize(12f);
        xAxis.setDrawGridLines(false);

        //Sumbu Y
        barChart.getAxisLeft().setAxisMinimum(0f);
        barChart.getAxisLeft().setAxisMaximum(10f);
        barChart.getAxisLeft().setTextColor(Color.BLACK);
        barChart.getAxisRight().setEnabled(false);

        //Tampilannya
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false); // biar legend otomatis mati
        barChart.animateY(1000);
        barChart.invalidate();
    }

    private void keteranganWarna() {
        LinearLayout parentLayout = findViewById(R.id.llyLegend);

        LinearLayout legendLayout = new LinearLayout(this);
        legendLayout.setOrientation(LinearLayout.HORIZONTAL);
        legendLayout.setGravity(Gravity.CENTER);
        legendLayout.setPadding(0, 10, 0, 10);

        TextView tinggi = new TextView(this);
        tinggi.setText("\uD83D\uDFE5 Tinggi");
        tinggi.setTextColor(Color.RED);
        tinggi.setTextSize(14);
        tinggi.setPadding(20, 0, 20, 0);

        TextView sedang = new TextView(this);
        sedang.setText("\uD83D\uDFE8 Sedang");
        sedang.setTextColor(Color.YELLOW);
        sedang.setTextSize(14);
        sedang.setPadding(20, 0, 20, 0);

        TextView rendah = new TextView(this);
        rendah.setText("\uD83D\uDFE9 Rendah");
        rendah.setTextColor(Color.GREEN);
        rendah.setTextSize(14);
        rendah.setPadding(20, 0, 20, 0);

        legendLayout.addView(tinggi);
        legendLayout.addView(sedang);
        legendLayout.addView(rendah);

        parentLayout.addView(legendLayout);

    }
}