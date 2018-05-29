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

import com.example.android.models.User;
import com.example.android.pi2.R;
import com.example.android.pi2.ScheduleMedicationActivity;

import java.util.List;

public class ChooseUserAdapter extends  RecyclerView.Adapter<ChooseUserAdapter.UserViewHolder> {

    private Context mCtx;
    private List<User> listUsers;

    public ChooseUserAdapter(Context mCtx, List<User> listUsers) {
        this.mCtx = mCtx;
        this.listUsers = listUsers;
    }


    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.user_list_layout, null);
        UserViewHolder holder = new UserViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {
        User user = listUsers.get(position);

        holder.usrName.setText(user.getNome());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.com_facebook_profile_picture_blank_square));
        holder.id = user.getUserId();

        holder.userParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mCtx, listUsers.get(position).getUserId(), Toast.LENGTH_SHORT).show();

                Intent it = new Intent(mCtx, ScheduleMedicationActivity.class);
                it.putExtra("userId", listUsers.get(position).getUserId());
                it.putExtra("userName", listUsers.get(position).getNome());
                it.putExtra("userLastName", listUsers.get(position).getSobrenome());
                it.putExtra("hrAcorda", listUsers.get(position).getHoraAcorda());
                it.putExtra("hrDorme", listUsers.get(position).getHoraDorme());
                mCtx.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView usrName;
        String id;
        LinearLayout userParent;

        public UserViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.userImg);
            usrName = itemView.findViewById(R.id.txtUserName);
            userParent = itemView.findViewById(R.id.userItemParent);
        }
    }
}
