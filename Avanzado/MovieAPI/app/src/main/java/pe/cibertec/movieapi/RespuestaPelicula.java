package pe.cibertec.movieapi;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RespuestaPelicula {

    @SerializedName("results")
    private ArrayList<Pelicula> elementos;


    public ArrayList<Pelicula> getElementos() {
        return elementos;
    }

    public void setElementos(ArrayList<Pelicula> elementos) {
        this.elementos = elementos;
    }
}
