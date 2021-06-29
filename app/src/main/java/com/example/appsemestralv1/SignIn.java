package com.example.appsemestralv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignIn extends AppCompatActivity implements View.OnClickListener {


    EditText name, rut, telefono, email,contactEmergency,fonoContact, pass;
    Button saveButton, cancelButton;
    daoUser dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        name = (EditText) findViewById(R.id.SignName);
        rut = (EditText) findViewById(R.id.SignRut);
        telefono = (EditText) findViewById(R.id.SignTelefono);
        email = (EditText) findViewById(R.id.SignEmail);
        contactEmergency = (EditText) findViewById(R.id.SignContactEmergency);
        fonoContact = (EditText) findViewById(R.id.SignFonoContact);
        pass = (EditText) findViewById(R.id.SignPass);
        saveButton = (Button) findViewById(R.id.SaveButton);
        //cancelButton = (Button) findViewById(R.id.CancelSignButton);
        dao = new daoUser(this);
        saveButton.setOnClickListener(this);
        //cancelButton.setOnClickListener(this);

    }

    /*
    saveButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            validarCorreo(email);

        }
    });

     */

    private boolean validarCorreo(EditText email){
        String UserMail = email.getText().toString();

        if(!UserMail.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(UserMail).matches()){
            Toast.makeText(this, "Validacion de correo exitosa", Toast.LENGTH_SHORT).show();
            return true;
        } else{
            Toast.makeText(this, "Correo invalido", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    @Override
    public  void onClick(View v){
        validarCorreo(email);
        switch (v.getId()) {
            case R.id.SaveButton:
                User user = new User();

                String username = name.getText().toString();
                String UserRut = rut.getText().toString();
                String UserTelefono = telefono.getText().toString();
                String UserMail = email.getText().toString();
                String UserContactEmergency = contactEmergency.getText().toString();
                String UserFonoContact = fonoContact.getText().toString();
                String UserPassword = pass.getText().toString();

                user.setName(username);
                user.setRut(UserRut);
                user.setTelefono(UserTelefono);
                user.setEmail(UserMail);
                user.setContactEmergency(UserContactEmergency);
                user.setFonoContact(UserFonoContact);
                user.setPassword(UserPassword);


                if(!user.isNull()) {
                    Toast.makeText(this,"Complete todos los campos", Toast.LENGTH_LONG).show();
                } else if (dao.createUser(user)){
                    Toast.makeText(this,"Registro Exitoso", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignIn.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(this,"El Usuario ya Existe", Toast.LENGTH_LONG).show();
                }


                break;


            /**case R.id.CancelSignButton:
                Intent i2 = new Intent(SignIn.this, MainActivity.class);
                startActivity(i2);
                finish();/**/

        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent (SignIn.this,MainActivity.class);
        startActivity(i);
        finish();

    }

}






