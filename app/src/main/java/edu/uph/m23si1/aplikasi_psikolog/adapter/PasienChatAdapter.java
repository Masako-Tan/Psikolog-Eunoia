package edu.uph.m23si1.aplikasi_psikolog.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.uph.m23si1.aplikasi_psikolog.R;
import edu.uph.m23si1.aplikasi_psikolog.model.Pasien;

public class PasienChatAdapter extends RecyclerView.Adapter<PasienChatAdapter.ViewHolder>{
    private List<Pasien> pasienList;
    private Context context;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Pasien pasien);
    }

    public PasienChatAdapter(Context context, List<Pasien> pasienList, OnItemClickListener listener) {
        this.context = context;
        this.pasienList = pasienList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pasien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Pasien pasien = pasienList.get(position);
        holder.txvNama.setText(pasien.getNama());

        // kalau ada foto resource ID
        holder.imgFoto.setImageResource(pasien.getFotoResId());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(pasien));
    }

    @Override
    public int getItemCount() {
        return pasienList.size();
    }

    public void setFilter(List<Pasien> filteredList) {
        pasienList = filteredList;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txvNama;
        ImageView imgFoto;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txvNama = itemView.findViewById(R.id.txvIcha); // ID di item_pasien.xml
            imgFoto = itemView.findViewById(R.id.imgIcha);
        }
    }
}