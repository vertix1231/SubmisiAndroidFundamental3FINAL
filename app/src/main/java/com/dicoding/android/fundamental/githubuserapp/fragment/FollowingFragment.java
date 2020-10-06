package com.dicoding.android.fundamental.githubuserapp.fragment;

import android.app.ProgressDialog;
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
import com.dicoding.android.fundamental.githubuserapp.adapter.FollowingAdapter;
import com.dicoding.android.fundamental.githubuserapp.nethelper.ServiceGenerator;
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
public class FollowingFragment extends Fragment {
    private static String KEY_LOGIN = "login";


    private RecyclerView rvProfilgithub_following;
    private ArrayList<PojoFollowing> dataModelUser = new ArrayList<>();
    private ProgressBar progressBar;

    private String userLogin;

//    semua recyclerview perlu adapter
    private FollowingAdapter recyclerAdapter;

//    Fungsi untuk membuat kelas fragment FollowingFragment baru
    public static FollowingFragment newInstance(String login){
        FollowingFragment fragment = new FollowingFragment();

        //Bikin bundle dan masukin bundle kedalam fragment yg mau dibuat
        Bundle bundle = new Bundle();
        bundle.putString(KEY_LOGIN,login);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Masukin value login yg dari bundle tadi ke variable di dalem fragment ini supaya bisa dipake di kelas ini
        this.userLogin = getArguments().getString(KEY_LOGIN, "login");
    }


    public FollowingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false);
    }


//    set data data / inisialisasi data di dalem sini
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvProfilgithub_following = view.findViewById(R.id.following_rv);
        rvProfilgithub_following.setLayoutManager(new LinearLayoutManager(view.getContext()));
        progressBar = view.findViewById(R.id.progressBar_following);

        getFollowingDataModelUsers();
    }

    // FUngsi buat dapetin data dari server
    private void getFollowingDataModelUsers(){

        progressBar.setVisibility(View.VISIBLE);

        GithubService service = ServiceGenerator.getRetrofitInstance().create(GithubService.class);
        Call<List<PojoFollowing>> calls = service.getFollowingUser(userLogin);
        calls.enqueue(new Callback<List<PojoFollowing>>() {
            @Override
            public void onResponse(Call<List<PojoFollowing>> call, Response<List<PojoFollowing>> response) {
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
            public void onFailure(Call<List<PojoFollowing>> call, Throwable t) {

                Toast.makeText(getContext(), "Request Failure"+t.getMessage(), Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);

            }
        });

    }

    private void setAdapter(){
        recyclerAdapter = new FollowingAdapter(getActivity(), dataModelUser);
        rvProfilgithub_following.setAdapter(recyclerAdapter);
    }
}
