package com.dicoding.android.fundamental.favouriteapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dicoding.android.fundamental.favouriteapp.activity.DetailUser;
import com.dicoding.android.fundamental.favouriteapp.pojo.Pojogithub;
import com.dicoding.android.fundamental.favouriteapp.R;


public class AdapterFavouriteGithubapp extends RecyclerView.Adapter<AdapterFavouriteGithubapp.UserViewHolder> {
    private Context context;
    private Cursor c;

    public AdapterFavouriteGithubapp(Context c) {
        this.context = c;
    }

    public void setCursor(Cursor cursor) {
        this.c = cursor;
    }

    private Pojogithub getItemData(int position){
        if (!c.moveToPosition(position)){
            throw new IllegalStateException("INVALID");
        }
        return new Pojogithub(c);
    }
    @NonNull
    @Override
    public AdapterFavouriteGithubapp.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_github,parent,false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterFavouriteGithubapp.UserViewHolder holder, int position) {
        Pojogithub pojogithub = getItemData(position);
        holder.tv_username.setText(pojogithub.getUsername());
        Glide.with(holder.itemView.getContext())
                .load(pojogithub.getIvprofil())
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        if (c == null) return 0;
        return c.getCount();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView avatar;
        TextView tv_username;
        UserViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.ivImage);
            tv_username = itemView.findViewById(R.id.tvusernamelist);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Pojogithub pojoUserGithub = getItemData(getAdapterPosition());
            Intent i = new Intent(context, DetailUser.class);
            i.putExtra("DATA_USER",pojoUserGithub);
            v.getContext().startActivity(i);
        }
    }
}
