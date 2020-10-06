package com.dicoding.android.fundamental.favouriteapp.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import com.dicoding.android.fundamental.favouriteapp.R;
import com.dicoding.android.fundamental.favouriteapp.adapter.AdapterFavouriteGithubapp;

import static com.dicoding.android.fundamental.favouriteapp.Database.dbContract.UserColumns.CONTENT_URI;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycle_favourite);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (savedInstanceState == null){
            new Fav().execute();
        }
    }

    private class Fav extends AsyncTask<Void,Void, Cursor> {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected Cursor doInBackground(Void... voids) {
            return getApplicationContext().getContentResolver().query(CONTENT_URI,null,null,null,null);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            AdapterFavouriteGithubapp adapterfav = new AdapterFavouriteGithubapp(getApplicationContext());
            adapterfav.setCursor(cursor);
            adapterfav.notifyDataSetChanged();
            recyclerView.setAdapter(adapterfav);
        }
    }

}
