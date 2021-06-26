package com.example.appsemestralv1;
import java.security.PublicKey;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



import java.util.ArrayList;

public class  daoUser {
    Context context; //provee una conection entre ando=rid y nuestro proyecto
    User user;//declaramos un objeto de tipo user
    ArrayList<User> list; // declaramos array list
    SQLiteDatabase conn; //Acceder a los metodos de trabajos sql
    String bd = "ExampleApp";//declaracion de DDBB
    String table = "create table if not exists users(id integer primary key autoincrement, name text,rut text,telefono text,email text,contactEmergency text,fonoContact text, pass text)";//creacion tabla


    public daoUser(Context context) {
        this.context = context; //asignacion de conexion
        conn = context.openOrCreateDatabase(bd, context.MODE_PRIVATE, null);//indicar contexto
        conn.execSQL(table);//ejecuta query

    }

    public boolean createUser(User user) {
        //if (search(user.getEmail()) == 0) {
            ContentValues cv = new ContentValues();   //tipo de lista que contendra varios valores
            cv.put("name", user.getName());// asigname tag con el valor que nos retorne el objeto
            cv.put("rut", user.getRut());
            cv.put("telefono", user.getTelefono());
            cv.put("email", user.getEmail());
            cv.put("contactEmergency", user.getContactEmergency());
            cv.put("fonoContact", user.getFonoContact());
            cv.put("pass", user.getPassword());

            //return (conn.insert("users",null,cv) > 0);
            if (conn.update("users", cv, "id = 1", null) > 0) {
                return true;
            } else if(conn.insert("users", null ,cv) > 0) {
                return true;
            } else{
                return false;
            }
        /*}else{
            return  false;
        }*/

    }



    public ArrayList<User> getUsers() {//metodo que retornea lista de usuario
        ArrayList<User> list = new ArrayList<User>();
        list.clear();//limpiar lista

        Cursor cursor = conn.rawQuery("SELECT * FROM users", null);//este cursor retornara los resultado de la query

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    User user = new User();
                    user.setId(cursor.getInt(0));// a usuario le asignamos el usuario que trae el cursor
                    user.setName(cursor.getString(1));
                    user.setRut(cursor.getString(2));
                    user.setTelefono(cursor.getString(3));
                    user.setEmail(cursor.getString(4));
                    user.setContactEmergency(cursor.getString(5));
                    user.setFonoContact(cursor.getString(6));
                    user.setPassword(cursor.getString(7));
                    list.add(user);


                } while (cursor.moveToNext());//la posicion del cursos se mueve a la siguientre
            }
            cursor.close();
        }
        return list;
    }

    public boolean login(String email, String password) {
        boolean success = false;
        Cursor cursor = conn.rawQuery("SELECT * FROM users", null);//este cursor retornara los resultado de la quer
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    if (cursor.getString(4).equals(email) && cursor.getString(7).equals(password)) {
                        success = true;
                        break;
                    }
                }
                while (cursor.moveToNext());

                if (success) {
                    cursor.close();
                    return true;
                }
            }
            cursor.close();
        }
        return false;
    }
    public int search(String email){
        int count=0;
        list = getUsers();
        for (User selectedUser :list){
            if(selectedUser.getEmail().equals(email)){
                count++;
            }
        }
        return count;
    }
    public User getUser  (String email,String password){
        list = getUsers();
        for (User user : list){
            if (user.getEmail().equals(email) && user.getPassword().equals(password)){
                return user;
            }
        }
        return null;

    }
    public User getUserById(int id){
        list = getUsers();
        for (User user : list){
            if(user.getId() == id){
                return user;
            }
        }
        return null;
    }
    public String getPhone() {
        User user = getUsers().get(0);
        return  user.FonoContact;
    }
    public void cleanDataBase(){

    }
}