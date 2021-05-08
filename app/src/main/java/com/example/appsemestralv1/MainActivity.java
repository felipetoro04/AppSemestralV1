package com.example.appsemestralv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity  implements  View.OnClickListener {
    public Button loginbutton;
    public Button registerButton;
    public ImageButton callFamily;
    public TextView textView_Email;
    daoUser dao;
    int userId = -1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_Email = findViewById(R.id.textView_email);



        loginbutton = (Button) findViewById(R.id.LoginButton);
        loginbutton.setOnClickListener(this);
        registerButton = (Button) findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener(this);
        callFamily=  (ImageButton) findViewById(R.id.CallFamily);
        dao = new daoUser(this);
        Intent i = getIntent();
        if (i.hasExtra("id")){
            Bundle bundle = getIntent().getExtras();

            userId = bundle.getInt("id");

            if (userId>=0){
                callFamily.setVisibility(View.VISIBLE);
            }
        }





    }
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.LoginButton:
                    Intent i3 = new Intent(MainActivity.this, Login.class);
                    startActivity(i3);
                    finish();
                    break;



                    case R.id.RegisterButton:
                        Intent i2 = new Intent(MainActivity.this, SignIn.class);
                        startActivity(i2);
                        finish();
                        break;



                }

            }










    }


    ;
