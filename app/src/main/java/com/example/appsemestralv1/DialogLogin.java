package com.example.appsemestralv1;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class DialogLogin extends AppCompatDialogFragment {
    private EditText emailLogin;
    private EditText passwordLogin;
    private  DialogLoginListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog,null);


        builder.setView(view)
                .setTitle("LOGIN")
                .setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {

                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        String email = emailLogin.getText().toString();

                        listener.applyTexts("BIENVENIDO: " + email);



                    }
                });
        emailLogin = view.findViewById(R.id.EmailLogin);
        passwordLogin = view.findViewById(R.id.PasswordLogin);
        return  builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (DialogLoginListener)context;
        } catch (ClassCastException e) {
            throw  new ClassCastException(context.toString() + "Bla Bla Bla");
        }
    }

    public  interface DialogLoginListener{
        void applyTexts(String emailLogin);

    }
}
