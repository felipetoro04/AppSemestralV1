package com.example.appsemestralv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity  implements DialogLogin.DialogLoginListener{
    private Button loginbutton;
    private TextView textView_Email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_Email = findViewById(R.id.textView_email);


        loginbutton = (Button) findViewById(R.id.LoginButton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();



            }
        });

    }
    public void openDialog(){
        DialogLogin dialogLogin = new DialogLogin();
        dialogLogin.show(getSupportFragmentManager(),"dialog Login");


    }
    @Override
    public  void  applyTexts(String email){
        textView_Email.setText(email);

    }


    }
