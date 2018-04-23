package com.example.android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.models.Medication;
import com.example.android.pi2.R;

import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder>{

    private Context mCtx;
    private List<Medication> medList;

    public MedicationAdapter(Context mCtx, List<Medication> medList) {
        this.mCtx = mCtx;
        this.medList = medList;
    }

    @Override
    public MedicationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.med_item_layout, null);
        MedicationViewHolder medHolder  = new MedicationViewHolder(view);
        return medHolder;
    }

    @Override
    public void onBindViewHolder(MedicationViewHolder holder, int position) {
        Medication med = medList.get(position);
        holder.nomeView.setText(med.getNome());
        holder.pAtivoView.setText(med.getpAtivo());
        holder.descView.setText(med.getDesc());
        holder.precoView.setText("R$ "+String.valueOf(med.getPreco()));

        holder.imgView.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.medication));

    }

    @Override
    public int getItemCount() {
        return medList.size();
    }

    class MedicationViewHolder extends RecyclerView.ViewHolder{

        ImageView imgView;
        TextView nomeView;
        TextView pAtivoView;
        TextView descView;/*
        TextView labView;
        TextView tClassView;
        TextView resctrictView;*/
        TextView precoView;
        TextView labView;


        public MedicationViewHolder(View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.medImg);
            nomeView = itemView.findViewById(R.id.medTitle);
            pAtivoView = itemView.findViewById(R.id.medAtivo);
            descView = itemView.findViewById(R.id.medDesc);
            precoView = itemView.findViewById(R.id.medPreco);
            labView = itemView.findViewById(R.id.medLab);


        }
    }

}
