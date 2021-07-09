package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.exam.db.DBUserHelper;
import com.example.exam.utils.Faker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    private EditText userEditText;
    private EditText passEditText;
    private Button singInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get EditText's of view
        userEditText = (EditText)findViewById(R.id.user);
        passEditText = (EditText)findViewById(R.id.pass);

        //Get Sign In button
        singInButton = (Button)findViewById(R.id.button_sing_in);


    }

    public void validate(View view) {
        Faker fakeInserts = new Faker();
        fakeInserts.insertFakeUser(this,1000);
        Toast.makeText(this, "Datos insertados", Toast.LENGTH_LONG).show();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String passPattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8}$";

        Pattern patternForEmail = Pattern.compile(emailPattern);
        Pattern patternForPass = Pattern.compile(passPattern);

        Matcher emailMatcher = patternForEmail.matcher(userEditText.getText().toString());
        Matcher passMatcher = patternForPass.matcher(passEditText.getText().toString());

        if(emailMatcher.matches()){
            if(passMatcher.matches()){
                //Validate from DataBase
                DBUserHelper dbHelper = new DBUserHelper(this);
                SQLiteDatabase db = dbHelper.getReadableDatabase();

                // Define a projection that specifies which columns from the database
                // you will actually use after this query.
                String[] projection = {
                        dbHelper.COLUMN_NAME_ID
                };

                // Filter results WHERE "title" = 'My Title'
                String selection = dbHelper.COLUMN_NAME_USER + " = ? AND " + dbHelper.COLUMN_NAME_PASS + " = ?";
                String[] selectionArgs = { userEditText.getText().toString(),passEditText.getText().toString() };

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

                if(cursor.getCount() == 1){
                    //Start new Activity "WelcomeActivity
                    cursor.moveToFirst();
                    int id = cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_NAME_ID));
                    System.out.println("This is an ID: "+ id);
                    Intent intent = new Intent(this, WelcomeActivity.class);
                    intent.putExtra("USER_ID", id);
                    startActivity(intent);

                }else{
                    Toast.makeText(this,"UsuarioNoExiste", Toast.LENGTH_LONG).show();
                }

            }else{
                Toast.makeText(this, "Invalid password", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Invalid email", Toast.LENGTH_LONG).show();
        }
    }
}
























