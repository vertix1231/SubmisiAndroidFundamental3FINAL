package com.dicoding.android.fundamental.githubuserapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.dicoding.android.fundamental.githubuserapp.Database.Helper;
import com.dicoding.android.fundamental.githubuserapp.Database.dbContract;
import com.dicoding.android.fundamental.githubuserapp.Database.dbHelper;
import com.dicoding.android.fundamental.githubuserapp.R;
import com.dicoding.android.fundamental.githubuserapp.adapter.FragmentPagerAdapterDetailProfil;
import com.dicoding.android.fundamental.githubuserapp.nethelper.ServiceGenerator;
import com.dicoding.android.fundamental.githubuserapp.pojo.Pojogithub;
import com.dicoding.android.fundamental.githubuserapp.service.GithubService;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dicoding.android.fundamental.githubuserapp.Database.dbContract.UserColumns.TABLE_NAME;

public class DetailUser extends AppCompatActivity {

    private CircleImageView ivgambar;
    private TextView tvusername,tvname,tvcompany,tvlocation,tvrepository,tvfollower,tvfollowing;

    public static final String DATA_USER = "userdata";
    public static final String DATA_EXTRA = "extradata";
    private int gambar;
    private String username,name,company,location,repository,follower,following;
    Context context;
    Pojogithub pojogithub;
    private ArrayList<Pojogithub> userList = new ArrayList<>();
    private Helper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_user);

        Bundle bundle = getIntent().getBundleExtra(DATA_EXTRA);
        assert bundle != null;
        pojogithub = bundle.getParcelable(DATA_USER);

        ivgambar = findViewById(R.id.ivImagedetail);
        tvname = findViewById(R.id.name_detail_tv);
        tvusername = findViewById(R.id.username_detail_tv);
        tvlocation = findViewById(R.id.location_detail_tv);
        tvcompany = findViewById(R.id.company_detail_tv);
        tvrepository = findViewById(R.id.repository_detail_tv);


        final ProgressDialog progressbar = new ProgressDialog(DetailUser.this);
        progressbar.setMessage(getString(R.string.progress));
        progressbar.show();


        Picasso.get().load(pojogithub.getIvprofil()).into(ivgambar);

        GithubService service = ServiceGenerator.getRetrofitInstance().create(GithubService.class);
        Call<Pojogithub> calls = service.getDetailUser(pojogithub.getUsername());
        calls.enqueue(new Callback<Pojogithub>() {
            @Override
            public void onResponse(Call<Pojogithub> call, Response<Pojogithub> response) {
                pojogithub = response.body();

                assert pojogithub != null;
                name = pojogithub.getUsername();
                tvname.setText(name);

                username = pojogithub.getName();
                tvusername.setText(username);

                location = pojogithub.getLocation();
                tvlocation.setText(location);

                company = pojogithub.getCompany();
                tvcompany.setText(company);

                repository = pojogithub.getRepository();
                tvrepository.setText(repository);

                progressbar.dismiss();

//                setFragment();


            }

            @Override
            public void onFailure(Call<Pojogithub> call, Throwable t) {

            }
        });

        FragmentPagerAdapterDetailProfil fragmentPagerAdapterDetailProfil = new FragmentPagerAdapterDetailProfil(this, getSupportFragmentManager(), pojogithub.getUsername());
        ViewPager viewPager = findViewById(R.id.vpdetailprofil);
        viewPager.setAdapter(fragmentPagerAdapterDetailProfil);
        TabLayout tabs = findViewById(R.id.tldetailprofil);
        tabs.setupWithViewPager(viewPager);

        getSupportActionBar().setElevation(0);


        OnClickFavourtire(pojogithub);
        helper = Helper.getInstance(getApplicationContext());


    }

    private void OnClickFavourtire(Pojogithub pojogithub) {
        MaterialFavoriteButton favButton = findViewById(R.id.btn_fav);
        if (READY(pojogithub.getUsername())){
            favButton.setFavorite(true);
            favButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite){
                        userList = helper.getDataUser();
                        helper.userInsert(pojogithub);
                        Toast.makeText(DetailUser.this, "Added to favorite", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        userList = helper.getDataUser();
                        helper.userDelete(String.valueOf(pojogithub.getId()));
                        Toast.makeText(DetailUser.this, "Deleted from favorite", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        else {
            favButton.setOnFavoriteChangeListener(new MaterialFavoriteButton.OnFavoriteChangeListener() {
                @Override
                public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                    if (favorite){
                        userList = helper.getDataUser();
                        helper.userInsert(pojogithub);
                        Toast.makeText(DetailUser.this, "Added to favorite", Toast.LENGTH_SHORT).show();
                    }else {
                        userList = helper.getDataUser();
                        helper.userDelete(String.valueOf(pojogithub.getId()));
                        Toast.makeText(DetailUser.this, "Deleted from favorite", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private boolean READY(String username){
        String replace = dbContract.UserColumns.USERNAME + "=?";
        String[] replaceargs = {username};
        String limit = "1";
        helper = new Helper(this);
        helper.open();
        pojogithub = getIntent().getParcelableExtra("DATA_USER");
        dbHelper dbHelper = new dbHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        @SuppressLint("Recycle") Cursor cursor = db.query(TABLE_NAME,null,replace,replaceargs,null,null,null,limit);
        boolean exist = (cursor.getCount() > 0 );
        cursor.close();
        return exist;
    }

}
