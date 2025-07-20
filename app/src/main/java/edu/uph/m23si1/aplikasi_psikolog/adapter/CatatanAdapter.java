package edu.uph.m23si1.aplikasi_psikolog.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.uph.m23si1.aplikasi_psikolog.R;
import edu.uph.m23si1.aplikasi_psikolog.model.Catatan;

public class CatatanAdapter extends RecyclerView.Adapter<CatatanAdapter.CatatanViewHolder> {
    List<Catatan> catatanList;
    OnCatatanClickListener listener;



    // [+] NEW INTERFACE FOR CLICK HANDLING
    public interface OnCatatanClickListener {
        void onEditClick(String catatanId);
        void onDeleteClick(Catatan catatan);
    }

    public CatatanAdapter(List<Catatan> catatanList, OnCatatanClickListener listener) {
        this.catatanList = catatanList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CatatanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // [+] INFLATE ITEM LAYOUT
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_catatan_card, parent, false);
        return new CatatanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatatanViewHolder holder, int position) {
        // [+] BIND DATA TO VIEWS
        Catatan catatan = catatanList.get(position);
        holder.bind(catatan);
    }

    @Override
    public int getItemCount() {
        return catatanList.size();
    }

    // [+] VIEW HOLDER CLASS
    class CatatanViewHolder extends RecyclerView.ViewHolder {
        TextView txvNama, txvUmur, txvTanggal, txvSkor, txvStatus, txvIsiObs;
        Button btnEdit, btnDelete;

        public CatatanViewHolder(@NonNull View itemView) {
            super(itemView);
            // [+] INITIALIZE VIEWS
            txvNama = itemView.findViewById(R.id.txvNama);
            txvUmur = itemView.findViewById(R.id.txvUmur);
            txvTanggal = itemView.findViewById(R.id.txvTanggal);
            txvSkor = itemView.findViewById(R.id.txvSkor);
            txvStatus = itemView.findViewById(R.id.txvStatus);
            txvIsiObs = itemView.findViewById(R.id.txvIsiObservasi);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

        public void bind(Catatan catatan) {
            // [+] SET DATA TO VIEWS
            txvNama.setText(catatan.getNama());
            txvUmur.setText(catatan.getUmur() + " Tahun | " + catatan.getGender());
            txvIsiObs.setText(catatan.getObservasi());

            // Default values
            txvTanggal.setText("Tes Terakhir: 11 Juli 2025");
            txvSkor.setText("Skor Terakhir: 15");
            txvStatus.setText("Status: Tidak Depresi");

            // [+] SET CLICK LISTENERS
            btnEdit.setOnClickListener(v -> listener.onEditClick(catatan.getId()));
            btnDelete.setOnClickListener(v -> listener.onDeleteClick(catatan));
        }
    }
}