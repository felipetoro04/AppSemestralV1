package com.example.appsemestralv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;




import java.util.ArrayList;

public class HistorialUbicaciones extends AppCompatActivity implements View.OnClickListener {




    // Array of strings...
    ListView simpleList;
    String[] countryList = {"1", "2", "3"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_ubicaciones);

        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_historial_ubicaciones,
                R.id.textView, countryList);
        simpleList.setAdapter(arrayAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
        }
    @Override
    public void onBackPressed() {
        Intent i = new Intent (HistorialUbicaciones.this,MainActivity.class);
        startActivity(i);
        finish();

}
}
