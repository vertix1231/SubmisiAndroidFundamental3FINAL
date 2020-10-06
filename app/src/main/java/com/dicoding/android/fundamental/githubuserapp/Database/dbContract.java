package com.dicoding.android.fundamental.githubuserapp.Database;

import android.net.Uri;
import android.provider.BaseColumns;
import static com.dicoding.android.fundamental.githubuserapp.Database.dbContract.UserColumns.TABLE_NAME;

public class dbContract {

    public static final String AUTHORITY = "com.dicoding.android.fundamental.githubuserapp";
    public static final String SCHEME = "content";

    public static final class UserColumns implements BaseColumns{
        public static final String TABLE_NAME = "fav_user";
        public static final String ID = "id";
        public static final String USERNAME = "username";
        public static final String AVATAR = "avatar";
    }
    public static final Uri CONTENT_URI = new Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build();

}
