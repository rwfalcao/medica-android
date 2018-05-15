package com.example.android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.models.Schedule;
import com.example.android.pi2.R;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleviewHolder> {

    private Context mCtx;
    private List<Schedule> schedList;

    public ScheduleAdapter(Context mCtx, List<Schedule> schedList) {
        this.mCtx = mCtx;
        this.schedList = schedList;
    }

    @Override
    public ScheduleviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        View view = inflater.inflate(R.layout.rotina_item, null);

        return new ScheduleviewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleviewHolder holder, int position) {
        Schedule sched = schedList.get(position);

        holder.username.setText(sched.getUser().getNome());
        holder.medname.setText(sched.getMed().getNome());
        holder.freq.setText(String.valueOf(sched.getFreq()+" vezes por dia"));

        holder.rotinaImg.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.clock_icon));
    }

    @Override
    public int getItemCount() {
        return schedList.size();
    }

    class ScheduleviewHolder extends RecyclerView.ViewHolder{

        ImageView rotinaImg;
        TextView username, freq, medname;


        public ScheduleviewHolder(View itemView) {
            super(itemView);

            rotinaImg = itemView.findViewById(R.id.rotinaImage);
            username = itemView.findViewById(R.id.itemUsername);
            freq = itemView.findViewById(R.id.itemFreq);
            medname = itemView.findViewById(R.id.itemMedname);
        }
    }
}
