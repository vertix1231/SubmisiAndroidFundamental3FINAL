package com.dicoding.android.fundamental.githubuserapp.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.dicoding.android.fundamental.githubuserapp.R;
import com.dicoding.android.fundamental.githubuserapp.adapter.AdapterGithubapp;
import com.dicoding.android.fundamental.githubuserapp.nethelper.ServiceGenerator;
import com.dicoding.android.fundamental.githubuserapp.pojo.Pojogithub;
import com.dicoding.android.fundamental.githubuserapp.pojo.Responses;
import com.dicoding.android.fundamental.githubuserapp.service.GithubService;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvProfilgithub;
    private ArrayList<Pojogithub> dataModelUser = new ArrayList<>();
    private  TextView username;
    private CircleImageView profillist;
    ProgressDialog progressDoalog;
    private ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvProfilgithub =findViewById(R.id.rvprofilgithub);
        username = findViewById(R.id.tvusernamelist);
        profillist = findViewById(R.id.ivImage);
        progress = findViewById(R.id.progressBar);

        rvProfilgithub.setHasFixedSize(true);
//        dataModelUser.addAll(LocalData.getListData());
        showRecyclerList();

        progressDoalog = new ProgressDialog(MainActivity.this);
        progressDoalog.setMessage("Loading....");
        progressDoalog.show();


        /*Create handle for the RetrofitInstance interface*/
        GithubService service = ServiceGenerator.getRetrofitInstance().create(GithubService.class);
        Call<List<Pojogithub>> calls = service.getUsers(10,1);
        calls.enqueue(new Callback<List<Pojogithub>>() {
            @Override
            public void onResponse(Call<List<Pojogithub>> call, Response<List<Pojogithub>> response) {
                progressDoalog.dismiss();
                List<Pojogithub> results = response.body();
                AdapterGithubapp adapterGithubapp = new AdapterGithubapp(MainActivity.this, (ArrayList<Pojogithub>) results);
                rvProfilgithub.setAdapter(adapterGithubapp);
//                showRecyclerListApi(response.body());

            }

            @Override
            public void onFailure(Call<List<Pojogithub>> call, Throwable t) {
                progressDoalog.dismiss();
                Toast.makeText(MainActivity.this, "Gagal Memuat", Toast.LENGTH_SHORT).show();


            }
        });




    }



    private void getDataOnline(final String username) {
        Call<Responses> service = ServiceGenerator.getRetrofitInstance().create(GithubService.class).getSearchUser(username);
        service.enqueue(new Callback<Responses>() {
            @Override
            public void onResponse(Call<Responses> call, Response<Responses> response) {
                if (response.isSuccessful()) {
                    //Mengambil data dari internet masuk ke Data Github
                    assert response.body() != null;
                    if(dataModelUser == null){
                        dataModelUser = new ArrayList<>();
                    }
                    dataModelUser = (ArrayList<Pojogithub>) response.body().getItems();
                    //Set Adapter ke Recycler View
                    AdapterGithubapp adapterGithubapp = new AdapterGithubapp(MainActivity.this,  dataModelUser);
                    rvProfilgithub.setAdapter(adapterGithubapp);
                    showProgress(false);
                }
                else {
                    Toast.makeText(MainActivity.this, "Request Failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Responses> call, Throwable t) {

            }
        });

    }


    private void showRecyclerList(){
        rvProfilgithub.setLayoutManager(new LinearLayoutManager(this));
        AdapterGithubapp listgithubAdapter = new AdapterGithubapp(this, (ArrayList<Pojogithub>) dataModelUser);   //LIAT LAGI SAMA LIAT LAGI DI CONTRUCTOR BLANK DI ADAPTER
        rvProfilgithub.setAdapter(listgithubAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);



        
        SearchManager sm = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView sv = (SearchView) menu.findItem(R.id.search).getActionView();

        if (sm !=null){
            sv.setSearchableInfo(sm.getSearchableInfo(getComponentName()));
            sv.setIconifiedByDefault(false);
            sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    showProgress(true);
                    if (s != null) {
                        getDataOnline(s);
                    } else {
                        Toast.makeText(MainActivity.this, "Insert Username First", Toast.LENGTH_SHORT).show();
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    getDataOnline(s);
                    return true;
                }
            });

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu2) {
            Intent Intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(Intent);
        }
        else if (item.getItemId()== R.id.fav){
            Intent intent = new Intent(this, FavouriteActivity.class);
            startActivity(intent);
        }
        else if (item.getItemId()== R.id.remainder){
            Intent intent = new Intent(this, RemainderActivity.class);
            startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    private void showProgress(Boolean state) {
        if (state) {
            progress.setVisibility(View.VISIBLE);
        } else {
            progress.setVisibility(View.GONE);
        }
    }



}
