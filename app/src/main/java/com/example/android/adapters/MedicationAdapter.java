package com.example.android.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.models.Medication;
import com.example.android.pi2.ConfirmaScheduleActivity;
import com.example.android.pi2.MedicationInformationActivity;
import com.example.android.pi2.R;

import java.util.List;

public class MedicationAdapter extends RecyclerView.Adapter<MedicationAdapter.MedicationViewHolder>{

    private Context mCtx;
    private List<Medication> medList;
    private  Intent medIntent;

    public MedicationAdapter(Context mCtx, List<Medication> medList, Intent it) {
        this.mCtx = mCtx;
        this.medList = medList;
        this.medIntent = it;
    }

    @Override
    public MedicationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.med_list_layout, null);
        MedicationViewHolder medHolder  = new MedicationViewHolder(view);
        return medHolder;
    }

    @Override
    public void onBindViewHolder(MedicationViewHolder holder, int position) {
        final Medication med = medList.get(position);
        holder.nomeView.setText(med.getNome());
        holder.pAtivoView.setText(med.getpAtivo());
        holder.descView.setText(med.getDesc());
        //holder.precoView.setText("R$ "+String.valueOf(med.getPreco()));

        holder.imgView.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.medication));

        holder.itemParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent it = new Intent(mCtx, MedicationInformationActivity.class);
                it.putExtra("medName", med.getNome());
                it.putExtra("ativo", med.getpAtivo());
                it.putExtra("lab", med.getLab());
                it.putExtra("desc", med.getDesc());
                it.putExtra("tClass", med.gettClass());
                it.putExtra("restric", med.getResctric());
                it.putExtra("preco", String.valueOf(med.getPreco()));


                it.putExtra("userId", medIntent.getStringExtra("userId"));
                it.putExtra("userName", medIntent.getStringExtra("userName"));
                it.putExtra("userLastName", medIntent.getStringExtra("userLastName"));
                it.putExtra("hrAcorda", medIntent.getStringExtra("hrAcorda"));
                it.putExtra("hrDorme", medIntent.getStringExtra("hrDorme"));


                mCtx.startActivity(it);



            }
        });

    }

    @Override
    public int getItemCount() {
        return medList.size();
    }

    class MedicationViewHolder extends RecyclerView.ViewHolder{

        ImageView imgView;
        TextView nomeView;
        TextView pAtivoView;
        TextView descView;
        TextView tClassView;
        TextView resctrictView;
        //TextView precoView;
        TextView labView;
        LinearLayout itemParent;


        public MedicationViewHolder(View itemView) {
            super(itemView);

            imgView = itemView.findViewById(R.id.medImg);
            nomeView = itemView.findViewById(R.id.medTitle);
            pAtivoView = itemView.findViewById(R.id.medAtivo);
            descView = itemView.findViewById(R.id.medDesc);
            //precoView = itemView.findViewById(R.id.medPreco);
            labView = itemView.findViewById(R.id.medLab);

            itemParent = itemView.findViewById(R.id.parentMedItem);




        }
    }

}
