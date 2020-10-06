package com.dicoding.android.fundamental.githubuserapp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dicoding.android.fundamental.githubuserapp.R;
import com.dicoding.android.fundamental.githubuserapp.activity.DetailUser;
import com.dicoding.android.fundamental.githubuserapp.adapter.FollowersAdapter;
import com.dicoding.android.fundamental.githubuserapp.adapter.FollowingAdapter;
import com.dicoding.android.fundamental.githubuserapp.nethelper.ServiceGenerator;
import com.dicoding.android.fundamental.githubuserapp.pojo.PojoFollowers;
import com.dicoding.android.fundamental.githubuserapp.pojo.PojoFollowing;
import com.dicoding.android.fundamental.githubuserapp.pojo.Pojogithub;
import com.dicoding.android.fundamental.githubuserapp.service.GithubService;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FollowerFragment extends Fragment {
    private static String KEY_LOGIN = "login";


    private RecyclerView rvProfilgithub_followers;
    private ArrayList<PojoFollowers> dataModelUser = new ArrayList<>();
    private ProgressBar progressBar;

    private String userLogin;

    //    semua recyclerview perlu adapter
    private FollowersAdapter recyclerAdapter;



    //    Fungsi untuk membuat kelas fragment FollowingFragment baru
    public static FollowingFragment newInstance(String login){
        FollowingFragment fragment = new FollowingFragment();

        //Bikin bundle dan masukin bundle kedalam fragment yg mau dibuat
        Bundle bundle = new Bundle();
        bundle.putString(KEY_LOGIN,login);
        fragment.setArguments(bundle);

        return fragment;
    }


//    public static FollowerFragment newInstance(int index) {
//        FollowerFragment followerFragment = FollowerFragment.newInstance(login);
//        DetailUser detailUser = (DetailUser) getActivity();
//        assert detailUser != null;
//        Bundle getbundle = detailUser.getIntent().getBundleExtra(DATA_EXTRA);
//        assert getbundle != null;
//        pojogithub =getbundle.getParcelable(DATA_USER);
//
//        return followerFragment;
//    }

    public FollowerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Masukin value login yg dari bundle tadi ke variable di dalem fragment ini supaya bisa dipake di kelas ini
        this.userLogin = getArguments().getString(KEY_LOGIN, "login");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follower, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvProfilgithub_followers = view.findViewById(R.id.follower_rv);
        rvProfilgithub_followers.setLayoutManager(new LinearLayoutManager(view.getContext()));
        progressBar = view.findViewById(R.id.progressBar_followers);

        getFollowingDataModelUsers();

    }

    // FUngsi buat dapetin data dari server
    private void getFollowingDataModelUsers(){

        progressBar.setVisibility(View.VISIBLE);

        GithubService service = ServiceGenerator.getRetrofitInstance().create(GithubService.class);
        Call<List<PojoFollowers>> calls = service.getFollowerUser(userLogin);
        calls.enqueue(new Callback<List<PojoFollowers>>() {
            @Override
            public void onResponse(Call<List<PojoFollowers>> call, Response<List<PojoFollowers>> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){
                        dataModelUser = new ArrayList<>(response.body());
                        Log.d("Result", "Response: " +dataModelUser.size());
                        setAdapter();
                    }
                } else {
                    Toast.makeText(getContext(), "Request Failed", Toast.LENGTH_SHORT).show();
                }

                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<List<PojoFollowers>> call, Throwable t) {
                Toast.makeText(getContext(), "Request Failure"+t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });

    }

    private void setAdapter(){
        recyclerAdapter = new FollowersAdapter(getActivity(), dataModelUser);
        rvProfilgithub_followers.setAdapter(recyclerAdapter);
    }
}
