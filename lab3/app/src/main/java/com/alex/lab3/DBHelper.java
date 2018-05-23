package com.alex.lab3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "lab3_db";
    public static final String TABLE_INPUT = "input";

    public static final String KEY_ID = "_id";
    public static final String KEY_INPUT_TEXT = "input_text";
    public static final String KEY_FONT = "font";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_INPUT + "(" + KEY_ID + " integer primary key, "
                + KEY_INPUT_TEXT + " text, " + KEY_FONT + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_INPUT);
        onCreate(db);
    }
}
