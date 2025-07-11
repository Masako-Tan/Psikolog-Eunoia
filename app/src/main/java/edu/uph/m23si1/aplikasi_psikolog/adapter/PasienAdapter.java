package edu.uph.m23si1.aplikasi_psikolog.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.content.Context;
import java.util.ArrayList;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import edu.uph.m23si1.aplikasi_psikolog.R;
import edu.uph.m23si1.aplikasi_psikolog.model.Pasien;

public class PasienAdapter extends RecyclerView.Adapter<PasienAdapter.ViewHolder> {

    private List<Pasien> pasienList;
    private List<Pasien> fullList;
    private Context context;

    private Fragment fragment;

    public PasienAdapter(Context context, List<Pasien> pasienList, Fragment fragment) {
        this.context = context;
        this.pasienList = new ArrayList<>(pasienList);
        this.fullList = new ArrayList<>(pasienList);
        this.fragment = fragment; // Simpan referensi fragment
    }

    public void filter(String text) {
        pasienList.clear();
        if (text.isEmpty()) {
            pasienList.addAll(fullList);
        } else {
            text = text.toLowerCase();
            for (Pasien pasien : fullList) {
                if (pasien.getNama().toLowerCase().contains(text)) {
                    pasienList.add(pasien);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PasienAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pasien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasienAdapter.ViewHolder holder, int position) {
        Pasien model = pasienList.get(position);
        holder.txvNama.setText(model.getNama());
        holder.imgFoto.setImageResource(model.getFotoResId());
        holder.imgPanah.setOnClickListener(v -> {
            NavHostFragment.findNavController(fragment)
                    .navigate(R.id.action_navigation_beranda_to_detailRekapFragment);
        });
    }

    @Override
    public int getItemCount() {
        return pasienList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvNama;
        ImageView imgFoto, imgPanah;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvNama = itemView.findViewById(R.id.txvIcha);
            imgFoto = itemView.findViewById(R.id.imgIcha);
            imgPanah = itemView.findViewById(R.id.imgPanah);
        }
    }
}

