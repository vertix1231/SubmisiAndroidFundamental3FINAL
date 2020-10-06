package com.dicoding.android.fundamental.githubuserapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.dicoding.android.fundamental.githubuserapp.R;
import com.dicoding.android.fundamental.githubuserapp.activity.DetailUser;
import com.dicoding.android.fundamental.githubuserapp.pojo.Pojogithub;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdapterGithubapp extends RecyclerView.Adapter<AdapterGithubapp.ViewHolder> {

    Context context;
    ArrayList<Pojogithub> dataModelUser;
    public static final String DATA_USER = "userdata";
    public static final String DATA_EXTRA = "extradata";


    public AdapterGithubapp(Context context, ArrayList<Pojogithub> dataModelUser) {
        this.context = context;
        this.dataModelUser = dataModelUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_github, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataModelUser.get(position).getIvprofil()).placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background).into(holder.ivprofil);
        holder.usernamelist.setText(dataModelUser.get(position).getUsername());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, DetailUser.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(DATA_USER,dataModelUser.get(position));
                intent.putExtra(DATA_EXTRA,bundle);
                Toast.makeText(holder.constraintLayout.getContext(),"user: "+dataModelUser.get(holder.getAdapterPosition()).getUsername(),Toast.LENGTH_LONG).show();
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModelUser.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView ivprofil;
        TextView usernamelist;
        ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivprofil = itemView.findViewById(R.id.ivImage);
            usernamelist = itemView.findViewById(R.id.tvusernamelist);
            constraintLayout = itemView.findViewById(R.id.cllistprofilegithub);
        }

        public void setData(Pojogithub data){
//            ivprofil.setImageResource(data.getIvprofill());
//            usernamelist.setText(data.getUsername());

        }
    }
}
