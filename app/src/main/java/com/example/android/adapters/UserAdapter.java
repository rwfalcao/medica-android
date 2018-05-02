package com.example.android.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.models.User;
import com.example.android.pi2.R;

import java.util.List;

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context mCtx;
    private List<User> listUsers;

    public UserAdapter(Context mCtx, List<User> listUsers) {
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
    public void onBindViewHolder(UserViewHolder holder, int position) {
        User user = listUsers.get(position);

        holder.usrName.setText(user.getNome());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(R.drawable.com_facebook_profile_picture_blank_square));
    }

    @Override
    public int getItemCount() {
        return listUsers.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView usrName;

        public UserViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.userImg);
            usrName = itemView.findViewById(R.id.txtUserName);
        }
    }
}
