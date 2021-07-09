package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exam.db.DBUserHelper;

public class SeachActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner, spinnerOrderyBy;
    private EditText editTextValue;
    private TextView textViewForResults;
    private int spinnerPos, spinnerOrderByPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.filters, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        spinnerOrderyBy = (Spinner) findViewById(R.id.spinner_order);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOrderyBy.setAdapter(adapter);
        spinnerOrderyBy.setOnItemSelectedListener(this);

        editTextValue = (EditText) findViewById(R.id.edit_text_value);
        textViewForResults = (TextView) findViewById(R.id.text_view_results);
    }

    public void searchWithParams(View view){
        String searchValue = "", orderByValue = "";
        switch (spinnerPos){
            case 0:
                searchValue = DBUserHelper.COLUMN_NAME_USER;
                break;
            case 1:
                searchValue = DBUserHelper.COLUMN_NAME_COUNTRY;
                break;
            case 2:
                searchValue = DBUserHelper.COLUMN_NAME_STATE;
                break;
            case 3:
                searchValue = DBUserHelper.COLUMN_NAME_GENDER;
                break;
        }
        switch (spinnerOrderByPos){
            case 0:
                orderByValue = DBUserHelper.COLUMN_NAME_USER;
                break;
            case 1:
                orderByValue = DBUserHelper.COLUMN_NAME_COUNTRY;
                break;
            case 2:
                orderByValue = DBUserHelper.COLUMN_NAME_STATE;
                break;
            case 3:
                orderByValue = DBUserHelper.COLUMN_NAME_GENDER;
                break;
        }
        selectFromDataBaseToSeach(searchValue, editTextValue.getText().toString(), orderByValue);
    }
    private void selectFromDataBaseToSeach(String searchValue, String value, String orderBy){
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
        String selection = searchValue + " = ?";
        String[] selectionArgs = {value};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                orderBy + " DESC";

        Cursor cursor = db.query(
                dbHelper.TABLE_NAME,   // The table to query
                projection,            // The array of columns to return (pass null to get all)
                selection,             // The columns for the WHERE clause
                selectionArgs,         // The values for the WHERE clause
                null,          // don't group the rows
                null,           // don't filter by row groups
                sortOrder           // The sort order
        );

        while (cursor.moveToNext()){
            textViewForResults.setText(textViewForResults.getText().toString() + "Usuario:" + cursor.getString(cursor.getColumnIndex(DBUserHelper.COLUMN_NAME_USER)) +
                    ", Pais:" + cursor.getString(cursor.getColumnIndex(DBUserHelper.COLUMN_NAME_COUNTRY)) +
                    ", Estado:" + cursor.getString(cursor.getColumnIndex(DBUserHelper.COLUMN_NAME_STATE)) +
                    ", Genero:" + cursor.getString(cursor.getColumnIndex(DBUserHelper.COLUMN_NAME_GENDER)) + "\n")
            ;
        }
        Toast.makeText(this, "YEAH", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(parent.getId() == R.id.spinner){
            spinnerPos = position;
        }else{
            Toast.makeText(this, parent.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
            spinnerOrderByPos = position;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}