package com.example.appsemestralv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {


    EditText email, pass;
    Button logButton, cancelButton;
    daoUser dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.editTextTextPersonName);
        pass = (EditText) findViewById(R.id.editTextTextPassword);
        logButton = (Button) findViewById(R.id.LogButton);
        cancelButton = (Button) findViewById(R.id.CancelLoginButton);



        logButton.setOnClickListener(this);

        /*
        logButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                validarCorreo(email);

            }
        });

         */
        cancelButton.setOnClickListener(this);

        dao = new daoUser(this);

    }

    private boolean validarCorreo(EditText email){
        String mail = email.getText().toString();

        if(!mail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
            Toast.makeText(this, "Validacion de correo exitosa", Toast.LENGTH_SHORT).show();
            return true;
        } else{
            Toast.makeText(this, "Correo invalido", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.LogButton:
                //comentario

                String mail = email.getText().toString();
                String password = pass.getText().toString();


                if (mail.equals("") || password.equals("")){
                    Toast.makeText(this,"Debe completar todos los campos",Toast.LENGTH_LONG).show();
                }else if (dao.login(mail, password)){
                    validarCorreo(email);
                    User loggedUser = dao.getUser(mail,password);
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.putExtra("id",loggedUser.getId());
                    Toast.makeText(this, "Bienvenido "+mail, Toast.LENGTH_SHORT).show();


                    startActivity(intent);
                }else{
                    Toast.makeText(this,"Usuario o contrasenia incorrecta",Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.CancelLoginButton:
                Intent i = new Intent(Login.this,MainActivity.class);
                startActivity(i);
                break;

        }
    }
    @Override
    public  void onBackPressed(){

    }
}