package com.dicoding.android.fundamental.githubuserapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class PojoFollowing implements Parcelable {

    @SerializedName("login")
    private String login;

    @SerializedName("avatar_url")
    private String avatarUrl;

    @SerializedName("id")
    private int id;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PojoFollowing(String login, String avatarUrl, int id) {
        this.login = login;
        this.avatarUrl = avatarUrl;
        this.id = id;
    }

    public PojoFollowing() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.login);
        dest.writeString(this.avatarUrl);
        dest.writeInt(this.id);
    }

    protected PojoFollowing(Parcel in) {
        this.login = in.readString();
        this.avatarUrl = in.readString();
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<PojoFollowing> CREATOR = new Parcelable.Creator<PojoFollowing>() {
        @Override
        public PojoFollowing createFromParcel(Parcel source) {
            return new PojoFollowing(source);
        }

        @Override
        public PojoFollowing[] newArray(int size) {
            return new PojoFollowing[size];
        }
    };
}
