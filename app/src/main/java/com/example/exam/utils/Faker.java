package com.example.exam.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.exam.db.DBUserHelper;

import java.util.Random;

public class Faker {


    public void insertFakeUser(Context context, int fakeUsers){
        // Create a new map of values, where column names are the keys
        DBUserHelper dbHelper = new DBUserHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Random random = new Random();

        ContentValues values = null;
        for (int i = 0; i < fakeUsers; i++) {
            values = new ContentValues();
            values.put(DBUserHelper.COLUMN_NAME_USER, "userTest"+i+"@user.com");
            values.put(DBUserHelper.COLUMN_NAME_PASS, "FFff$44$");
            values.put(DBUserHelper.COLUMN_NAME_COUNTRY, "paisNo"+ random.nextInt(5));
            values.put(DBUserHelper.COLUMN_NAME_STATE, "estadoNo"+ random.nextInt(25));
            if (random.nextInt(2) == 1) {
                values.put(DBUserHelper.COLUMN_NAME_GENDER, "M");
            }
            else {
                values.put(DBUserHelper.COLUMN_NAME_GENDER, "F");
            }

            if (values != null ) {
                long newRowId = db.insert(DBUserHelper.TABLE_NAME, null, values);
            }
        }
        // Insert the new row, returning the primary key value of the new row
        System.out.println("OK");
    }
}
