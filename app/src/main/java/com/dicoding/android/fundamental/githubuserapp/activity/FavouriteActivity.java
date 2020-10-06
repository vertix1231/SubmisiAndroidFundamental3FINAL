package com.dicoding.android.fundamental.githubuserapp.activity;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.dicoding.android.fundamental.githubuserapp.R;
import com.dicoding.android.fundamental.githubuserapp.adapter.AdapterFavouriteGithubapp;
import com.dicoding.android.fundamental.githubuserapp.Database.Helper;
import com.dicoding.android.fundamental.githubuserapp.pojo.Pojogithub;
import java.util.ArrayList;

public class FavouriteActivity extends AppCompatActivity {

    private Helper helper;
    private ArrayList<Pojogithub> userList =  new ArrayList<>();
    private AdapterFavouriteGithubapp adapterfav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);

        helper = new Helper(getApplicationContext());
        helper.open();
        userList = helper.getDataUser();
        setRecyclerView();
        getSupportActionBar().setTitle("Favourtie User");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.rv_user);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        adapterfav = new AdapterFavouriteGithubapp(getApplicationContext());
        recyclerView.setAdapter(adapterfav);
    }

    @Override
    protected void onResume() {
        super.onResume();
        userList = helper.getDataUser();
        adapterfav.setUserList(userList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }
}
