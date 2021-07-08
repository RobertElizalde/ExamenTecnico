package com.example.exam.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBUserHelper extends SQLiteOpenHelper {

    //Data base data
    private static final String DATABASE_NAME = "db_users";
    private static final int DATABASE_VERSION = 1;

    //Table and columns names
    public static final String TABLE_NAME = "table_users";
    public static final String COLUMN_NAME_ID = "id";
    public static final String COLUMN_NAME_USER = "user";
    public static final String COLUMN_NAME_PASS = "pass";
    public static final String COLUMN_NAME_COUNTRY = "country";
    public static final String COLUMN_NAME_STATE = "state";
    public static final String COLUMN_NAME_GENDER = "gender";

    //SQL Query for create table
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME +  "( " +
            COLUMN_NAME_ID  + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_USER + " TEXT," +
            COLUMN_NAME_PASS + " TEXT," +
            COLUMN_NAME_COUNTRY + " TEXT," +
            COLUMN_NAME_STATE + " TEXT," +
            COLUMN_NAME_GENDER + " TEXT)";

    //SQL Query for delate table
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DBUserHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
