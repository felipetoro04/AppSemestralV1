package com.example.appsemestralv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity  implements DialogLogin.DialogLoginListener, View.OnClickListener {
    private Button loginbutton;
    private Button registerButton;
    private TextView textView_Email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_Email = findViewById(R.id.textView_email);


        loginbutton = (Button) findViewById(R.id.LoginButton);
        loginbutton.setOnClickListener(this);
        registerButton = (Button) findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.LoginButton:
                        openDialog();
                        finish();
                        break;


                    case R.id.RegisterButton:
                        Intent i2 = new Intent(MainActivity.this, SignIn.class);
                        startActivity(i2);
                        finish();
                        break;



                }








            }
            public void openDialog(){
        DialogLogin dialogLogin = new DialogLogin();
        dialogLogin.show(getSupportFragmentManager(),"dialog Login");


    }
    @Override
    public  void  applyTexts(String email){
        textView_Email.setText(email);
        };




    }


    ;
