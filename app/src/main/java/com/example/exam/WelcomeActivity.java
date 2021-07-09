package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import com.example.exam.db.DBUserHelper;

public class WelcomeActivity extends AppCompatActivity {

    private TextView textViewUser;
    private TextView textViewCountry;
    private TextView textViewState;
    private TextView textViewGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Gets the TextViews
        textViewUser = (TextView)findViewById(R.id.text_view_user);
        textViewCountry = (TextView)findViewById(R.id.text_view_country);
        textViewState = (TextView)findViewById(R.id.text_view_state);
        textViewGender = (TextView)findViewById(R.id.text_view_gender);

        //Get id from intent
        int id = getIntent().getIntExtra("USER_ID",-1);

        if (id != -1){
            DBUserHelper dbHelper = new DBUserHelper(this);
            SQLiteDatabase db = dbHelper.getReadableDatabase();

            // Define a projection that specifies which columns from the database
            // you will actually use after this query.
            String[] projection = {
                    dbHelper.COLUMN_NAME_USER,
                    dbHelper.COLUMN_NAME_COUNTRY,
                    dbHelper.COLUMN_NAME_STATE,
                    dbHelper.COLUMN_NAME_GENDER
            };

            // Filter results WHERE "title" = 'My Title'
            String selection = dbHelper.COLUMN_NAME_ID + " = ?";
            String[] selectionArgs = {String.valueOf(id)};

            // How you want the results sorted in the resulting Cursor

            Cursor cursor = db.query(
                    dbHelper.TABLE_NAME,   // The table to query
                    projection,            // The array of columns to return (pass null to get all)
                    selection,             // The columns for the WHERE clause
                    selectionArgs,         // The values for the WHERE clause
                    null,          // don't group the rows
                    null,           // don't filter by row groups
                    null           // The sort order
            );

            if(cursor.getCount()==1){
                cursor.moveToFirst();
                textViewUser.setText(textViewUser.getText().toString() + cursor.getString(0));
                textViewCountry.setText(textViewCountry.getText().toString() + cursor.getString(1));
                textViewState.setText(textViewState.getText().toString() + cursor.getString(2));
                textViewGender.setText(textViewGender.getText().toString() + cursor.getString(3));
            }

        }
    }
}