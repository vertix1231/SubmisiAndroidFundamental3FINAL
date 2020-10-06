package com.dicoding.android.fundamental.githubuserapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.dicoding.android.fundamental.githubuserapp.Database.dbContract.UserColumns.TABLE_NAME;

public class dbHelper extends SQLiteOpenHelper {
    private static final String USER_DB_NAME = "dbuserfav";
    private static final int USER_DB_VERSION = 2;
    private static final String SQL_CREATE_TABLE_NOTE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY ," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            TABLE_NAME,
            dbContract.UserColumns.ID,
            dbContract.UserColumns.USERNAME,
            dbContract.UserColumns.AVATAR
    );

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_NOTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    public dbHelper(Context context){
        super(context,USER_DB_NAME,null,USER_DB_VERSION);
    }
}