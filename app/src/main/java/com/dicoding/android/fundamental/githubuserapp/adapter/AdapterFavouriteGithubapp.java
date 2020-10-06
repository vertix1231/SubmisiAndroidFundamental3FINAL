package com.dicoding.android.fundamental.githubuserapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.dicoding.android.fundamental.githubuserapp.activity.DetailUser;
import com.dicoding.android.fundamental.githubuserapp.R;
import com.dicoding.android.fundamental.githubuserapp.pojo.Pojogithub;

import java.util.ArrayList;


public class AdapterFavouriteGithubapp extends RecyclerView.Adapter<AdapterFavouriteGithubapp.MyViewHolder> {
    public static final String DATA_USER = "userdata";
    public static final String DATA_EXTRA = "extradata";
    private Context mcontext;
    private ArrayList<Pojogithub> pojodata;


    public AdapterFavouriteGithubapp(Context mcontext) {
        this.mcontext = mcontext;
    }
    public void setUserList(ArrayList<Pojogithub> userArrayList){
        this.pojodata = userArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_github, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.Usernametv.setText(pojodata.get(position).getUsername());
        Glide.with(mcontext)
                .load(pojodata.get(position).getIvprofil())
                .into(holder.Avatarciv);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent moveDetailActivity = new Intent(mcontext, DetailUser.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DATA_USER,pojodata.get(position));
                moveDetailActivity.putExtra(DATA_EXTRA,bundle);
                moveDetailActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(moveDetailActivity);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pojodata.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView Usernametv, typetv, Repostv;
        ImageView Avatarciv;
        public MyViewHolder(@NonNull View view) {
            super(view);
            Usernametv = itemView.findViewById(R.id.tvusernamelist);
            Avatarciv = itemView.findViewById(R.id.ivImage);
        }
    }
}
