package com.rp.rahmawatiputrianasari.research00.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rahmawatiputrianasari on 10/23/17.
 */

public class DbDataUsage extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "DataUsage.db";
    public static final String TABLE_NAME = "Data";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TIME";
    public static final String COL_3 = "MOBILE";
    public static final String COL_4 = "WIFI";
    public static final String COL_5 = "TOTAL";

    public DbDataUsage(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,TIME TEXT,MOBILE TEXT,WIFI TEXT,TOTAL TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String date, String mobile, String wifi, String total) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, date);
        contentValues.put(COL_3, mobile);
        contentValues.put(COL_4, wifi);
        contentValues.put(COL_5, total);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String id, String mobile, String wifi, String total) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, id);
        contentValues.put(COL_3, mobile);
        contentValues.put(COL_4, wifi);
        contentValues.put(COL_5, total);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{id});
        return true;
    }

    public void deleteData() {
//    public Integer deleteData(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
//        return db.delete(TABLE_NAME, "ID = ?", new String[]{id});
//        return db.execSQL("delete from "+ TABLE_NAME);
        db.execSQL("delete from " + TABLE_NAME);
    }
}
