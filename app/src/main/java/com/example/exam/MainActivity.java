package com.example.exam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String passPattern = "^([a-zA-Z0-9@*#]{8,8})$";

        Pattern patternForEmail = Pattern.compile(emailPattern);
        Pattern patternForPass = Pattern.compile(passPattern);

        Matcher emailMatcher = patternForEmail.matcher(userEditText.getText().toString());
        Matcher passMatcher = patternForPass.matcher(passEditText.getText().toString());

        if(emailMatcher.matches()){
            if(passMatcher.matches()){
                //Validate from DataBase
            }else{
                Toast.makeText(this, "Invalid email", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Invalid email", Toast.LENGTH_LONG).show();
        }
    }
}
























