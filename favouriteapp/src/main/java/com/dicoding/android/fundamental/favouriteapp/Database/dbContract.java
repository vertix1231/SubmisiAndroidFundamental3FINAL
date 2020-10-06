package com.dicoding.android.fundamental.favouriteapp.Database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class dbContract {

    public static final String AUTHORITY = "com.dicoding.android.fundamental.githubuserapp";
    public static final String SCHEME = "content";

    public static final class UserColumns implements BaseColumns{
        public static final String TABLE_NAME = "fav_user";
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String AVATAR = "avatar";
        public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_NAME)
                .build();
    }

    public static String getFavoriteItem(Cursor c, String column){
        return c.getString(c.getColumnIndex(column));
    }
}
