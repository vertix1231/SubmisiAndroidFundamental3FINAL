package com.dicoding.android.fundamental.githubuserapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.dicoding.android.fundamental.githubuserapp.pojo.Pojogithub;
import java.util.ArrayList;
import static com.dicoding.android.fundamental.githubuserapp.Database.dbContract.UserColumns.ID;
import static com.dicoding.android.fundamental.githubuserapp.Database.dbContract.UserColumns.TABLE_NAME;
import static com.dicoding.android.fundamental.githubuserapp.Database.dbContract.UserColumns.AVATAR;
import static com.dicoding.android.fundamental.githubuserapp.Database.dbContract.UserColumns.USERNAME;

public class Helper {
    private static final String DB_TABLE = TABLE_NAME;
    private static dbHelper dbUserHelper;
    private static Helper userHelper;
    private static SQLiteDatabase db;

    public Helper(Context context) {
        dbUserHelper = new dbHelper(context);
    }

    public static Helper getInstance(Context context) {
        if (userHelper == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (userHelper == null) {
                    userHelper = new Helper(context);
                }
            }
        }
        return userHelper;
    }

    public void open() throws SQLException {
        db = dbUserHelper.getWritableDatabase();
    }

    public void close() {
        dbUserHelper.close();
        if (db.isOpen())
            db.close();
    }

    public Cursor queryAll() {
        return db.query(DB_TABLE,
                null,
                null,
                null,
                null,
                null,
                ID+ " ASC");
    }

    public Cursor queryById(String string) {
        return db.query(DB_TABLE, null
                , ID + " = ?"
                , new String[]{string}
                , null
                , null
                , null
                , null);
    }
    public ArrayList<Pojogithub> getDataUser(){
        ArrayList<Pojogithub> userlist = new ArrayList<>();
        Cursor cursor = db.query(DB_TABLE,null,
                null,
                null,
                null,
                null,
                USERNAME + " ASC",
                null);
        cursor.moveToFirst();
        Pojogithub pojogithub;
        if (cursor.getCount() > 0){
            do {
                pojogithub = new Pojogithub();
                pojogithub.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                pojogithub.setUsername(cursor.getString(cursor.getColumnIndexOrThrow(USERNAME)));
                pojogithub.setIvprofil(cursor.getString(cursor.getColumnIndexOrThrow(AVATAR)));
                userlist.add(pojogithub);
                cursor.moveToNext();
            }while (!cursor.isAfterLast());
        }cursor.close();
        return userlist;
    }

    public long userInsert(Pojogithub pojogithub){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID,pojogithub.getId());
        contentValues.put(USERNAME,pojogithub.getUsername());
        contentValues.put(AVATAR,pojogithub.getIvprofil());

        return db.insert(DB_TABLE,null, contentValues);

    }

    public int userDelete(String string){
        return db.delete(TABLE_NAME,ID + " = '" + string + "'", null);
    }


    public int DeleteProvider(String string) {
        return db.delete(TABLE_NAME, ID+ "=?",new String[]{string});
    }
    public int UpdateProvider(String string, ContentValues values) {
        return db.update(DB_TABLE, values, ID + " =?", new String[]{string});
    }
    public long InsertProvider(ContentValues values) {
        return db.insert(DB_TABLE, null, values);
    }
}

