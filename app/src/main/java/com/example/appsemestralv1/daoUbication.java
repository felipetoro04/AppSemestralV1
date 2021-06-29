package com.example.appsemestralv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class daoUbication {
    Context context; //provee una conection entre ando=rid y nuestro proyecto
    Ubication Ubication;//declaramos un objeto de tipo user
    ArrayList<Ubication> list; // declaramos array list
    SQLiteDatabase conn; //Acceder a los metodos de trabajos sql
    String bd = "ExampleApp";//declaracion de DDBB
    String table = "create table if not exists ubications(id integer primary key autoincrement, coordenadas text, fecha Date)";


    public daoUbication(Context context) {
        this.context = context; //asignacion de conexion
        conn = context.openOrCreateDatabase(bd, context.MODE_PRIVATE, null);//indicar contexto
        conn.execSQL(table);//ejecuta query

    }

    ;

    public void createUbication(Ubication ubication) {
        ContentValues cv = new ContentValues();   //tipo de lista que contendra varios valores
        cv.put("coordenadas", ubication.getCoordenadas());// asigname tag con el valor que nos retorne el objeto
        cv.put("fecha", String.valueOf(Calendar.getInstance().getTime()));

       conn.insert("ubications", null, cv) ;
    }



    public ArrayList<Ubication> getUbications() {//metodo que retornea lista de usuario
        ArrayList<Ubication> list = new ArrayList<Ubication>();
        list.clear();//limpiar lista

        Cursor cursor = conn.rawQuery("SELECT * FROM ubications ORDER BY fecha DESC", null);//este cursor retornara los resultado de la query

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    Ubication ubication = new Ubication();
                    ubication.setId(cursor.getInt(0));// a usuario le asignamos el usuario que trae el cursor
                    ubication.setCoordenadas(cursor.getString(1));

                    list.add(ubication);


                } while (cursor.moveToNext());//la posicion del cursos se mueve a la siguientre
            }
            cursor.close();
        }
        return list;
    }
    public Ubication getUbicationById(int id){
        list = getUbications();
        for (Ubication ubication : list){
            if(ubication.getId() == id){
                return ubication;
            }
        }
        return null;
    }
    public String getLastLocation() {
        String location = getUbications().get(0).Coordenadas;
        return location;
    }

}

