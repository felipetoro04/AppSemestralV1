package com.example.appsemestralv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HistorialUbicaciones extends AppCompatActivity implements View.OnClickListener {

    public Button historialBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_ubicaciones);

        historialBack = (Button) findViewById(R.id.HistorialBack);
        historialBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.HistorialBack:
                Intent i = new Intent (HistorialUbicaciones.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
    }
}

