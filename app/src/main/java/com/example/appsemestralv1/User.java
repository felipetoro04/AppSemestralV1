package com.example.appsemestralv1;

public class User {
    int Id;
    String Name, Rut, Email,ContactEmergency,Password ,Telefono,FonoContact;

    public User(){

    }


    public User(String name, String rut, String telefono, String email, String contactEmergency, String fonoContact , String password){
        Name = name;
        Rut = rut;
        Telefono = telefono;
        Email= email;
        ContactEmergency = contactEmergency;
        FonoContact =  fonoContact;
        Password= password;
    }
    public boolean isNull(){//validacion completado de datos
        if (Name.equals("")|| Rut.equals("")||  Telefono.equals("") || Email.equals("") || ContactEmergency.equals("")|| FonoContact.equals("")|| Password.equals("")     ){
            return false;
        }
        return  true;//else
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRut() {
        return Rut;
    }

    public void setRut(String rut) {
        Rut = rut;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getContactEmergency() {
        return ContactEmergency;
    }

    public void setContactEmergency(String contactEmergency) {
        ContactEmergency = contactEmergency;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getFonoContact() {
        return FonoContact;
    }

    public void setFonoContact(String fonoContact) {
        FonoContact = fonoContact;
    }


}