package com.example.appsemestralv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity  implements  View.OnClickListener {
    public Button loginbutton;
    public Button closeSesion;
    public Button registerButton;
    public Button btn_Sound;
    public ImageButton callFamily;
    public TextView textView_Email;
    public Button historyTrack;
    daoUser dao;
    int userId = -1;

    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_Email = findViewById(R.id.textView_email);


        historyTrack = (Button) findViewById(R.id.HistoryTrack);
        historyTrack.setOnClickListener(this);
        loginbutton = (Button) findViewById(R.id.LoginButton);
        loginbutton.setOnClickListener(this);

        registerButton = (Button) findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener(this);
        closeSesion = (Button) findViewById(R.id.CloseSesion);
        closeSesion.setOnClickListener(this);


        btn_Sound = (Button) findViewById(R.id.Btn_Sound);
        btn_Sound.setOnClickListener(this);
        callFamily = (ImageButton) findViewById(R.id.CallFamily);


        mp = MediaPlayer.create(this, R.raw.soundd);


        dao = new daoUser(this);
        Intent i = getIntent();
        if (i.hasExtra("id")) {
            Bundle bundle = getIntent().getExtras();

            userId = bundle.getInt("id");

            if (userId >= 0) {
                closeSesion.setVisibility(View.VISIBLE);
                callFamily.setVisibility(View.VISIBLE);
                registerButton.setVisibility(View.GONE);
                loginbutton.setVisibility(View.GONE);
            } else if (userId == -1) {
                closeSesion.setVisibility(View.GONE);
                callFamily.setVisibility(View.GONE);
                registerButton.setVisibility(View.VISIBLE);
                loginbutton.setVisibility(View.VISIBLE);

            }

        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
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
            case R.id.CloseSesion:
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                userId = -1;
                startActivity(i);
                finish();
                break;

            case R.id.HistoryTrack:
                Intent i4 = new Intent(MainActivity.this, HistorialUbicaciones.class);
                startActivity(i4);
                finish();
                break;
            case R.id.Btn_Sound:
                if (mp.isPlaying()) {
                    mp.pause();
                } else {
                    mp.start();
                }
        }
    }
};
