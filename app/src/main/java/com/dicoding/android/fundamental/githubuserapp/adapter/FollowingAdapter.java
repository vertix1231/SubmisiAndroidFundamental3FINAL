package com.dicoding.android.fundamental.githubuserapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.android.fundamental.githubuserapp.R;
import com.dicoding.android.fundamental.githubuserapp.pojo.PojoFollowing;
import com.dicoding.android.fundamental.githubuserapp.pojo.Pojogithub;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.FollowingViewHolder> {
    Context context;
    ArrayList<PojoFollowing> dataModelUser;

    public FollowingAdapter(Context context, ArrayList<PojoFollowing> dataModelUser) {
        this.context = context;
        this.dataModelUser = dataModelUser;
    }

    @NonNull
    @Override
    public FollowingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_following, parent, false);
        return new FollowingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowingViewHolder holder, int position) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataModelUser.get(position).getAvatarUrl()).placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background).into(holder.ivprofil);
        holder.usernamelist.setText(dataModelUser.get(position).getLogin());

    }

    @Override
    public int getItemCount() {
        return dataModelUser.size();
    }

    public class FollowingViewHolder extends RecyclerView.ViewHolder {
        CircleImageView ivprofil;
        TextView usernamelist;
        ConstraintLayout constraintLayout;
        public FollowingViewHolder(@NonNull View itemView) {
            super(itemView);

            ivprofil = itemView.findViewById(R.id.ivImage_following);
            usernamelist = itemView.findViewById(R.id.tvusernamelist_following);
            constraintLayout = itemView.findViewById(R.id.cllistprofilegithub_following);
        }
    }
}
