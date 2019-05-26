package com.example.tester;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseClass extends SQLiteOpenHelper {
    private static final String nama_db = "Cobacoba.db";
    private static final String nama_tabel = "login";

    private static final  String kolom1 = "Username";
    private static final  String kolom2 = "Password";

    public DatabaseClass(Context context) {
        super(context, nama_db, null, 1);

        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+nama_tabel+"(Username, Password)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+nama_tabel);
    }

    public boolean insertData(String Username, String Password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(kolom1, Username);
        contentValues.put(kolom2, Password);

        long result = db.insert(nama_tabel,null,contentValues);
        if (result == -1)
            return false;
        else
            return true;

    }

    public Cursor lihatData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor data = db.rawQuery("SELECT * FROM " +nama_tabel, null);
        return data;
    }

    public boolean editData(String Username, String Password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(kolom1, Username);
        contentValues.put(kolom2, Password);

        db.update(nama_tabel,contentValues,"Username = ?",new String[] {Username});
        return true;
    }

    public Integer hapusData (String Username){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(nama_tabel,"Username = ?",new String[]{Username});
    }
}
