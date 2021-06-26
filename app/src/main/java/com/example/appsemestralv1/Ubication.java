package com.example.appsemestralv1;

public class Ubication {
    int Id;
    String Coordenadas;

    public Ubication() {

    }
    public boolean isNull(){//validacion completado de datos
        if (Coordenadas.equals("")){
            return false;
        }
        return  true;//else
    }


    public Ubication(String coordenadas) {
        Coordenadas = coordenadas;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCoordenadas() {
        return Coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        Coordenadas = coordenadas;
    }

}
