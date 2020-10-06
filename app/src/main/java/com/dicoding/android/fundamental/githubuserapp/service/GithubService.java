package com.dicoding.android.fundamental.githubuserapp.service;

import com.dicoding.android.fundamental.githubuserapp.pojo.PojoFollowers;
import com.dicoding.android.fundamental.githubuserapp.pojo.PojoFollowing;
import com.dicoding.android.fundamental.githubuserapp.pojo.Pojogithub;
import com.dicoding.android.fundamental.githubuserapp.pojo.Responses;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubService {

    @GET("/users")
    @Headers("Authorization: token 51d73a3ceeac29f861a0c8b4895dd9b51ebd9799")
    public Call<List<Pojogithub>> getUsers(@Query("per_page") int perPage, @Query("page") int page);

//    @GET("/userss")
//    @Headers("Authorization: token 51d73a3ceeac29f861a0c8b4895dd9b51ebd9799")
//    public Call<List<Pojogithub>> getUserss(@Query("q") String username);

    @GET("/users/{username}")
    @Headers("Authorization: token 51d73a3ceeac29f861a0c8b4895dd9b51ebd9799")
    public Call<Pojogithub> getUser(@Path("username") String username);

    @GET("search/users")
    @Headers("Authorization: token 51d73a3ceeac29f861a0c8b4895dd9b51ebd9799")
    Call<Responses> getSearchUser(
            @Query("q") String username
    );

    @GET("users/{username}")
    @Headers("Authorization: token 51d73a3ceeac29f861a0c8b4895dd9b51ebd9799")
    Call<Pojogithub> getDetailUser(
            @Path("username") String username
    );

    @GET("users/{username}/followers")
    @Headers("Authorization: token 51d73a3ceeac29f861a0c8b4895dd9b51ebd9799")
        //<list> soalnya modelnya dibungkus array karena data ne banyak
    Call<List<PojoFollowers>> getFollowerUser(
            @Path("username") String username
    );

    @GET("users/{username}/following")
    @Headers("Authorization: token 51d73a3ceeac29f861a0c8b4895dd9b51ebd9799")
    Call<List<PojoFollowing>> getFollowingUser(
            @Path("username") String username
    );

}
