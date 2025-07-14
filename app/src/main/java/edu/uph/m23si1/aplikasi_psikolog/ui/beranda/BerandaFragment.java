package edu.uph.m23si1.aplikasi_psikolog.ui.beranda;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import edu.uph.m23si1.aplikasi_psikolog.R;
import edu.uph.m23si1.aplikasi_psikolog.adapter.PasienAdapter;
import edu.uph.m23si1.aplikasi_psikolog.model.Pasien;
import edu.uph.m23si1.aplikasi_psikolog.model.Reminder;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class BerandaFragment extends Fragment {
    private View rootView;
    TextView txvBeranda,txvRemind1, txvRemind2, txvGrafik, txvPasien, txvIcha, txvAsep, txvSeny, txvRea, txvRhoma, txvIrama;
    ImageView imgLogo, imgIcha, imgAsep, imgSeny, imgRea, imgRhoma, imgIrama, imgPanah, imgPanah1, imgPanah2, imgPanah3, imgPanah4, imgPanah5;
    CheckBox chbIcha, chbAsep, chbSeny, chbRea, chbKonsulseny, chbIrama, chbRekaprea, chbRhoma;
    EditText edtCari;
    LinearLayout llyLegend, llyGrafik, llyReminder;
    CalendarView calendarView;
    BarChart barChart;
    RecyclerView recyclerViewPasien;

    PasienAdapter pasienAdapter;
    List<Pasien> pasienList;
    Button btnTambahReminder;
    String selectedDate = ""; // Global date

    private final SimpleDateFormat dateFormat= new SimpleDateFormat("dd-MM-yyyy",Locale.getDefault());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_beranda, container, false);

        imgLogo = rootView.findViewById(R.id.imgLogo);
//        imgIcha = rootView.findViewById(R.id.imgIcha);
//        imgAsep = rootView.findViewById(R.id.imgAsep);
//        imgSeny = rootView.findViewById(R.id.imgSeny);
//        imgRea = rootView.findViewById(R.id.imgRea);
//        imgRhoma = rootView.findViewById(R.id.imgRhoma);
//        imgIrama = rootView.findViewById(R.id.imgIrama);

//        imgPanah = rootView.findViewById(R.id.imgPanah);
//        imgPanah1 = rootView.findViewById(R.id.imgPanah1);
//        imgPanah2 = rootView.findViewById(R.id.imgPanah2);
//        imgPanah3 = rootView.findViewById(R.id.imgPanah3);
//        imgPanah4 = rootView.findViewById(R.id.imgPanah4);
//        imgPanah5 = rootView.findViewById(R.id.imgPanah5);

        txvBeranda = rootView.findViewById(R.id.txvBeranda);
//        txvRemind1 = rootView.findViewById(R.id.txvRemind1);
//        txvRemind2 = rootView.findViewById(R.id.txvRemind2);
        txvGrafik = rootView.findViewById(R.id.txvGrafik);
        txvPasien = rootView.findViewById(R.id.txvPasien);
//        txvIcha = rootView.findViewById(R.id.txvIcha);
//        txvAsep = rootView.findViewById(R.id.txvAsep);
//        txvSeny = rootView.findViewById(R.id.txvSeny);
//        txvRea = rootView.findViewById(R.id.txvRea);
//        txvRhoma = rootView.findViewById(R.id.txvRhoma);
//        txvIrama = rootView.findViewById(R.id.txvIrama);

//        chbIcha = rootView.findViewById(R.id.chbIcha);
//        chbAsep = rootView.findViewById(R.id.chbAsep);
//        chbSeny = rootView.findViewById(R.id.chbSeny);
//        chbRea = rootView.findViewById(R.id.chbRea);
//        chbKonsulseny = rootView.findViewById(R.id.chbKonsulseny);
//        chbIrama = rootView.findViewById(R.id.chbIrama);
//        chbRekaprea = rootView.findViewById(R.id.chbRekaprea);
//        chbRhoma = rootView.findViewById(R.id.chbRhoma);

        edtCari = rootView.findViewById(R.id.edtCari);
        calendarView = rootView.findViewById(R.id.calendarView);
        barChart = rootView.findViewById(R.id.barChart);
        llyLegend = rootView.findViewById(R.id.llyLegend);
        llyGrafik = rootView.findViewById(R.id.llyGrafik);
        llyReminder = rootView.findViewById(R.id.llyReminder);
        recyclerViewPasien = rootView.findViewById(R.id.recyclerViewPasien);

        // Reminder
        Realm.init(requireContext());
        RealmConfiguration config = new RealmConfiguration.Builder().allowWritesOnUiThread(true).build();
        Realm.setDefaultConfiguration(config);

        btnTambahReminder = rootView.findViewById(R.id.btnTambahReminder);

        btnTambahReminder.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle("Tambah Reminder");

            final EditText input = new EditText(requireContext());
            input.setHint("Isi kegiatan...");
            builder.setView(input);

            builder.setPositiveButton("Simpan", (dialog, which) -> {
                String task = input.getText().toString().trim();
                if (!task.isEmpty() && !selectedDate.isEmpty()) {
                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(r -> {
                        Reminder reminder = r.createObject(Reminder.class, UUID.randomUUID().toString());
                        reminder.setDate(selectedDate);
                        reminder.setTask(task);
                        reminder.setDone(false);
                    });
                    tampilkanReminder(selectedDate);
                }
            });

            builder.setNegativeButton("Batal", (dialog, which) -> dialog.cancel());
            builder.show();
        });




        // Search
        recyclerViewPasien.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 8;
                outRect.bottom = 16; // spasi bawah tiap item
            }
        });

        edtCari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pasienAdapter.filter(s.toString());

                if (s.toString().isEmpty()) {
                    llyGrafik.setVisibility(View.VISIBLE);
                    txvPasien.setVisibility(View.VISIBLE);
                } else {
                    llyGrafik.setVisibility(View.GONE); // hide grafik saat search
                    txvPasien.setVisibility(View.GONE); // sembunyikan judul pasien
                }

            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        setupGrafikStresPasien();


        keteranganWarna();
        setupCalendarListener();
        String today = dateFormat.format(new java.util.Date());
        tampilkanReminder(today);

        setupPasienList();
        setupSearch();



        return rootView;

    }

    private void setupPasienList() {
        recyclerViewPasien.setLayoutManager(new LinearLayoutManager(getContext()));
        pasienList = new ArrayList<>();
        pasienList.add(new Pasien("Icha Septina", R.drawable.icha));
        pasienList.add(new Pasien("Asep Yovara", R.drawable.asep));
        pasienList.add(new Pasien("Seny Caroline", R.drawable.senyy));
        pasienList.add(new Pasien("Rea Parulian", R.drawable.rea));
        pasienList.add(new Pasien("Rhoma", R.drawable.rhoma));
        pasienList.add(new Pasien("Irama", R.drawable.irama));

        pasienAdapter = new PasienAdapter(getContext(), pasienList, this);
        recyclerViewPasien.setAdapter(pasienAdapter);
    }

    private void setupSearch() {
        edtCari.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                pasienAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void setupCalendarListener(){
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            String selectedDate = String.format(Locale.getDefault(), "%02d-%02d-%d", dayOfMonth, month + 1, year);
            tampilkanReminder(selectedDate);
        });

        // Inisialisasi awal
        selectedDate = dateFormat.format(new java.util.Date());
        tampilkanReminder(selectedDate);
    }

    private void tampilkanReminder(String dateKey) {
        llyReminder.removeAllViews();

        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("app.realm")
                .deleteRealmIfMigrationNeeded()
                .allowWritesOnUiThread(true)
                .build();
        Realm realm = Realm.getInstance(config);

        RealmResults<Reminder> reminders = realm.where(Reminder.class)
                .equalTo("date", dateKey)
                .findAll();

        if (reminders.isEmpty()) {
            TextView kosong = new TextView(getContext());
            kosong.setText("Tidak ada reminder untuk tanggal ini.");
            kosong.setTextSize(14);
            kosong.setTextColor(Color.DKGRAY);
            kosong.setPadding(20,10,20,10);
            llyReminder.addView(kosong);
        } else {
            for (Reminder r : reminders) {
                CheckBox checkBox = new CheckBox(getContext());
                checkBox.setText(r.getTask());
                checkBox.setTextSize(14);
                checkBox.setChecked(r.isDone());
                if (r.isDone()) {
                    checkBox.setPaintFlags(checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                }

                // Simpan
                checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                    realm.executeTransaction(bgRealm -> {
                        Reminder update = bgRealm.where(Reminder.class).equalTo("id", r.getId()).findFirst();
                        if (update != null) {
                            update.setDone(isChecked);
                        }
                    });

                    //Coret
                    if (isChecked) {
                        checkBox.setPaintFlags(checkBox.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
                    } else {
                        checkBox.setPaintFlags(checkBox.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
                    }
                });

                // Klik = Edit Reminder
                checkBox.setOnClickListener(v -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                    builder.setTitle("Edit Reminder");

                    final EditText input = new EditText(requireContext());
                    input.setText(r.getTask());
                    builder.setView(input);

                    builder.setPositiveButton("Simpan", (dialog, which) -> {
                        String newTask = input.getText().toString().trim();
                        if (!newTask.isEmpty()) {
                            realm.executeTransaction(bgRealm -> {
                                Reminder update = bgRealm.where(Reminder.class).equalTo("id", r.getId()).findFirst();
                                if (update != null) {
                                    update.setTask(newTask);
                                }
                            });
                            tampilkanReminder(dateKey);
                        }
                    });

                    builder.setNegativeButton("Batal", (dialog, which) -> dialog.cancel());
                    builder.show();
                });

                // Long Press = Hapus Reminder
                checkBox.setOnLongClickListener(v -> {
                    new AlertDialog.Builder(requireContext())
                            .setTitle("Hapus Reminder")
                            .setMessage("Yakin ingin menghapus reminder ini?")
                            .setPositiveButton("Hapus", (dialog, which) -> {
                                realm.executeTransaction(bgRealm -> {
                                    Reminder delete = bgRealm.where(Reminder.class).equalTo("id", r.getId()).findFirst();
                                    if (delete != null) delete.deleteFromRealm();
                                });
                                tampilkanReminder(dateKey);
                            })
                            .setNegativeButton("Batal", null)
                            .show();
                    return true;
                });

                llyReminder.addView(checkBox);
            }
        }
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
        LinearLayout parentLayout = rootView.findViewById(R.id.llyLegend);

        LinearLayout legendLayout = new LinearLayout(getContext());
        legendLayout.setOrientation(LinearLayout.HORIZONTAL);
        legendLayout.setGravity(Gravity.CENTER);
        legendLayout.setPadding(0, 10, 0, 10);

        TextView tinggi = new TextView(getContext());
        tinggi.setText("\uD83D\uDFE5 Tinggi");
        tinggi.setTextColor(Color.RED);
        tinggi.setTextSize(14);
        tinggi.setPadding(20, 0, 20, 0);

        TextView sedang = new TextView(getContext());
        sedang.setText("\uD83D\uDFE8 Sedang");
        sedang.setTextColor(Color.YELLOW);
        sedang.setTextSize(14);
        sedang.setPadding(20, 0, 20, 0);

        TextView rendah = new TextView(getContext());
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