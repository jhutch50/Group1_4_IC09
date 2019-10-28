package com.example.group14_ic09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {

    private static final int SIGN_UP_REQ = 123;
    EditText editTextemail;
    EditText editTextpassword;
    Button buttonlogin;
    Button buttonsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        buttonsignup = findViewById(R.id.buttonsignup);
        buttonlogin = findViewById(R.id.buttonlogin);
        buttonsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                startActivityForResult(intent,SIGN_UP_REQ);
            }
        });
    }
}
