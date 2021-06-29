package com.example.appsemestralv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;



import java.util.ArrayList;
import java.util.List;

public class HistorialUbicaciones extends AppCompatActivity implements View.OnClickListener {

    daoUbication dao2;


    // Array of strings...
    ListView simpleList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_ubicaciones);
        dao2 = new daoUbication(this);
        List<Ubication> countryList = dao2.getUbications();
        List<String> coordenadasParseadas  = new ArrayList();
        for (int i = 0;i < countryList.size(); i++){
            coordenadasParseadas.add(i+"-. "+ "http://maps.google.co.in/maps?q=" + countryList.get(i).Coordenadas);
        }
        simpleList = (ListView)findViewById(R.id.simpleListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.activity_historial_ubicaciones,
                R.id.textView, coordenadasParseadas);
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
