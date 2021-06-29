package com.example.appsemestralv1;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;

import android.media.MediaPlayer;

import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity  implements  View.OnClickListener {
    public Button loginbutton;
    public Button closeSesion;
    public Button registerButton;
    public ImageButton btn_Sound;
    public ImageButton callFamily;
    public TextView textView_Email;
    public Button historyTrack;
    public Button btn_Track;
    public User user;
    public Button callPolice;
    private static final int PERMISSION_SEND_SMS = 123;
    daoUser dao;
    int userId = -1;
    MediaPlayer mp;
    daoUbication dao2;
    private static final String TAG = "MainActivity";
    int LOCATION_REQUEST_CODE = 10001;
    public TextView Coordenadas;
    FusedLocationProviderClient fusedLocationProviderClient;
String coordenadaText="";
//String phone=



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Coordenadas = findViewById(R.id.TVcoordenadas);
        textView_Email = findViewById(R.id.textView_email);



        callPolice = (Button) findViewById(R.id.CallPolice);
        callPolice.setOnClickListener(this);

        historyTrack = (Button) findViewById(R.id.HistoryTrack);
        historyTrack.setOnClickListener(this);
        //loginbutton = (Button) findViewById(R.id.LoginButton);
        //loginbutton.setOnClickListener(this);

        registerButton = (Button) findViewById(R.id.RegisterButton);
        registerButton.setOnClickListener(this);
        closeSesion = (Button) findViewById(R.id.CloseSesion);
        closeSesion.setOnClickListener(this);


        btn_Sound = (ImageButton) findViewById(R.id.Btn_Sound);
        btn_Sound.setOnClickListener(this);
        callFamily = (ImageButton) findViewById(R.id.CallFamily);
        callFamily.setOnClickListener(this);
        dao2 = new daoUbication(this);
        btn_Track = (Button) findViewById(R.id.Btn_Track);
        btn_Track.setOnClickListener(this);

        mp = MediaPlayer.create(this, R.raw.soundd);


        dao = new daoUser(this);
        Intent i = getIntent();
        if (i.hasExtra("id")) {
            Bundle bundle = getIntent().getExtras();

            userId = bundle.getInt("id");

            if (userId >= 0) {
                closeSesion.setVisibility(View.VISIBLE);
                //callFamily.setVisibility(View.VISIBLE);
                registerButton.setVisibility(View.GONE);
                loginbutton.setVisibility(View.GONE);
            } else if (userId == -1) {
                closeSesion.setVisibility(View.GONE);
                //callFamily.setVisibility(View.GONE);
                registerButton.setVisibility(View.VISIBLE);
                loginbutton.setVisibility(View.VISIBLE);

            }

        }

    }

    private void sendSms(String address, String message) {

        try {
                String SENT = "SMS_SENT";

                PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);

                registerReceiver(new BroadcastReceiver() {
                    @Override
                    public void onReceive(Context arg0, Intent arg1) {
                        int resultCode = getResultCode();
                        switch (resultCode) {
                            case Activity.RESULT_OK:
                                Toast.makeText(getBaseContext(), "SMS sent", Toast.LENGTH_LONG).show();
                                break;
                            case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                                Toast.makeText(getBaseContext(), "Generic failure", Toast.LENGTH_LONG).show();
                                break;
                            case SmsManager.RESULT_ERROR_NO_SERVICE:
                                Toast.makeText(getBaseContext(), "No service", Toast.LENGTH_LONG).show();
                                break;
                            case SmsManager.RESULT_ERROR_NULL_PDU:
                                Toast.makeText(getBaseContext(), "Null PDU", Toast.LENGTH_LONG).show();
                                break;
                            case SmsManager.RESULT_ERROR_RADIO_OFF:
                                Toast.makeText(getBaseContext(), "Radio off", Toast.LENGTH_LONG).show();
                                break;
                        }
                    }
                }, new IntentFilter(SENT));

                SmsManager smsMgr = SmsManager.getDefault();
                smsMgr.sendTextMessage(address, null, message, sentPI, null);
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage() + "!\n" + "Failed to send SMS", Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }


    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //getLastLocation();
        } else {
            askLocationPermission();
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},
                    PERMISSION_SEND_SMS);

        }
    }

    private void getLastLocation() {
        Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();

        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    //We have a location
                    Log.d(TAG, "onSuccess: " + location.toString());
                    Log.d(TAG, "onSuccess: " + location.getLatitude());
                    Log.d(TAG, "onSuccess: " + location.getLongitude());

                    String text = location.getLatitude() +","+ location.getLongitude();
                    coordenadaText = text;
                    Coordenadas.setText(coordenadaText);
                    Ubication ubication = new Ubication();
                    String coordenada = Coordenadas.getText().toString();
                    ubication.setCoordenadas(coordenada);
                    if(!ubication.isNull()) {

                    } else {
                        dao2.createUbication(ubication);
                        Toast.makeText(getApplicationContext(),"Coordenadas ingresadas", Toast.LENGTH_LONG).show();
                        };
                }
            }


        });

        locationTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "onFailure: " + e.getLocalizedMessage() );
            }
        });

    }


    private void askLocationPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                Log.d(TAG, "askLocationPermission: you should show an alert dialog...");
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                getLastLocation();
            } else {
                //Permission not granted
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.LoginButton:
                Intent i3 = new Intent(MainActivity.this, Login.class);
                mp.pause();
                startActivity(i3);
                finish();

                break;
*/

            case R.id.RegisterButton:
                Intent i2 = new Intent(MainActivity.this, SignIn.class);
                mp.pause();
                startActivity(i2);
                finish();
                break;

            case R.id.CloseSesion:
                Intent i = new Intent(MainActivity.this, MainActivity.class);
                userId = -1;
                mp.pause();
                startActivity(i);
                finish();
                break;

            case R.id.HistoryTrack:
                Intent i4 = new Intent(MainActivity.this, HistorialUbicaciones.class);
                startActivity(i4);
                mp.pause();
                finish();

                break;

            case R.id.Btn_Sound:
                if(mp.isPlaying()){
                    btn_Sound.setImageResource(R.drawable.play);
                    mp.pause();
                } else {
                    mp.start();
                    btn_Sound.setImageResource(R.drawable.pause);
                }
                break;

            case R.id.Btn_Track:
                getLastLocation();
                break;

            case R.id.CallFamily:
                //startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + dao.getPhone())));
                getLastLocation();
                String location = "NECESITO AYUDA! \"http://maps.google.co.in/maps?q=\"" + dao2.getLastLocation();
                String number = "56" + dao.getPhone();
                sendSms(number, location);
                Toast.makeText(this, "Mensaje Enviado", Toast.LENGTH_SHORT).show();

                break;

            case R.id.CallPolice:
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "133")));
                break;


        }
    };



};
