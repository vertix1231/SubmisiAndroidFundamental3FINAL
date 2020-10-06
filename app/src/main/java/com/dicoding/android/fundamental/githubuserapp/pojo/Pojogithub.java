package com.dicoding.android.fundamental.githubuserapp.pojo;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pojogithub implements Parcelable {
//    private int ivprofil;
//    private String username,name,company,location,repository,follower,following;

    @SerializedName("id")
    private long id;

    private  String location;

    @SerializedName("login")
    private String username;

    @SerializedName("avatar_url")
    private String ivprofil;

    @SerializedName("name")
    private String name;

    @SerializedName("url")
    private String url;

    @SerializedName("html_url")
    private String htmlUrl;

    @SerializedName("followers_url")
    private String follower;

    @SerializedName("following_url")
    private String following;

    @SerializedName("gists_url")
    private String gistsUrl;

    @SerializedName("starred_url")
    private String starredUrl;

    @SerializedName("subscriptions_url")
    private String subscriptionsUrl;

    @SerializedName("organizations_url")
    private String company;

    @SerializedName("repos_url")
    private String repository;

    @SerializedName("public_repos")
    private String publicRepos;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIvprofil() {
        return ivprofil;
    }

    public void setIvprofil(String ivprofil) {
        this.ivprofil = ivprofil;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getFollower() {
        return follower;
    }

    public void setFollower(String follower) {
        this.follower = follower;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public void setGistsUrl(String gistsUrl) {
        this.gistsUrl = gistsUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public void setStarredUrl(String starredUrl) {
        this.starredUrl = starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public void setSubscriptionsUrl(String subscriptionsUrl) {
        this.subscriptionsUrl = subscriptionsUrl;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getPublicRepos() {
        return publicRepos;
    }

    public void setPublicRepos(String publicRepos) {
        this.publicRepos = publicRepos;
    }

    public Pojogithub(long id, String location, String username, String ivprofil, String name, String url, String htmlUrl, String follower, String following, String gistsUrl, String starredUrl, String subscriptionsUrl, String company, String repository, String publicRepos) {
        this.id = id;
        this.location = location;
        this.username = username;
        this.ivprofil = ivprofil;
        this.name = name;
        this.url = url;
        this.htmlUrl = htmlUrl;
        this.follower = follower;
        this.following = following;
        this.gistsUrl = gistsUrl;
        this.starredUrl = starredUrl;
        this.subscriptionsUrl = subscriptionsUrl;
        this.company = company;
        this.repository = repository;
        this.publicRepos = publicRepos;
    }

    public Pojogithub() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeString(this.location);
        dest.writeString(this.username);
        dest.writeString(this.ivprofil);
        dest.writeString(this.name);
        dest.writeString(this.url);
        dest.writeString(this.htmlUrl);
        dest.writeString(this.follower);
        dest.writeString(this.following);
        dest.writeString(this.gistsUrl);
        dest.writeString(this.starredUrl);
        dest.writeString(this.subscriptionsUrl);
        dest.writeString(this.company);
        dest.writeString(this.repository);
        dest.writeString(this.publicRepos);
    }

    protected Pojogithub(android.os.Parcel in) {
        this.id = in.readLong();
        this.location = in.readString();
        this.username = in.readString();
        this.ivprofil = in.readString();
        this.name = in.readString();
        this.url = in.readString();
        this.htmlUrl = in.readString();
        this.follower = in.readString();
        this.following = in.readString();
        this.gistsUrl = in.readString();
        this.starredUrl = in.readString();
        this.subscriptionsUrl = in.readString();
        this.company = in.readString();
        this.repository = in.readString();
        this.publicRepos = in.readString();
    }

    public static final Creator<Pojogithub> CREATOR = new Creator<Pojogithub>() {
        @Override
        public Pojogithub createFromParcel(android.os.Parcel source) {
            return new Pojogithub(source);
        }

        @Override
        public Pojogithub[] newArray(int size) {
            return new Pojogithub[size];
        }
    };
}
