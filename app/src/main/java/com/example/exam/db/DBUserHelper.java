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
    private static final String TABLE_NAME = "table_users";
    private static final String COLUMN_NAME_ID = "id";
    private static final String COLUMN_NAME_USER = "user";
    private static final String COLUMN_NAME_PASS = "pass";
    private static final String COLUMN_NAME_COUNTRY = "country";
    private static final String COLUMN_NAME_STATE = "state";
    private static final String COLUMN_NAME_GENDER = "gender";

    //SQL Query for create table
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME +  "( " +
            COLUMN_NAME_ID  + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_NAME_USER + "TEXT," +
            COLUMN_NAME_PASS + "TEXT," +
            COLUMN_NAME_COUNTRY + "TEXT," +
            COLUMN_NAME_STATE + "TEXT," +
            COLUMN_NAME_GENDER + "TEXT)";

    //SQL Query for delate table
    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DBUserHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
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
