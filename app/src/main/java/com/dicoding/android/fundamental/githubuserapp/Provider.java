package com.dicoding.android.fundamental.githubuserapp;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

import com.dicoding.android.fundamental.githubuserapp.Database.Helper;

import java.util.Objects;
import static com.dicoding.android.fundamental.githubuserapp.Database.dbContract.AUTHORITY;
import static com.dicoding.android.fundamental.githubuserapp.Database.dbContract.CONTENT_URI;
import static com.dicoding.android.fundamental.githubuserapp.Database.dbContract.UserColumns.TABLE_NAME;

public class Provider extends ContentProvider {
    private static final int USER = 0;
    private static final int USER_ID = 1;
    Helper helper;

    private static final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        matcher.addURI(AUTHORITY,TABLE_NAME,USER);
        matcher.addURI(AUTHORITY,TABLE_NAME + "/#",USER_ID);
    }

    @Override
    public boolean onCreate() {
        helper = Helper.getInstance(getContext());
        helper.open();
        return true;
    }

    @Override
    public Cursor query(Uri u, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Cursor cursor;
        switch (matcher.match(u)){
            case USER:
                cursor = helper.queryAll();
                break;
            case USER_ID:
                cursor = helper.queryById(u.getLastPathSegment());
                break;
            default:
                cursor = null;
                break;
        }
        if (cursor != null){
            cursor.setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(),u);
        }
        return cursor;
    }

    @Override
    public String getType(Uri u) {
        return null;
    }

    @Override
    public Uri insert(Uri u, ContentValues values) {
        long added;
        Uri contentUri = null;
        switch (matcher.match(u)){
            case USER:
                added = helper.InsertProvider(values);
                if (added > 0) {
                    contentUri = ContentUris.withAppendedId(CONTENT_URI, added);
                }
                break;
            default:
                added = 0;
                break;
        }
        if (added > 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(u, null);
        }
        return contentUri;
    }


    @Override
    public int update(Uri u, ContentValues values, String selection, String[] selectionArgs) {
        int update;
        switch (matcher.match(u)){
            case USER_ID:
                update = helper.UpdateProvider(u.getLastPathSegment(),values);
                break;
            default:
                update = 0;
                break;
        }
        if (update > 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(u, null);
        }
        return update;
    }

    @Override
    public int delete(Uri u, String selection, String[] selectionArgs) {
        int delete;
        switch (matcher.match(u)){
            case USER_ID:
                delete = helper.DeleteProvider(u.getLastPathSegment());
                break;
            default:
                delete = 0;
                break;
        }
        if (delete > 0) {
            Objects.requireNonNull(getContext()).getContentResolver().notifyChange(u, null);
        }
        return delete;
    }

}
