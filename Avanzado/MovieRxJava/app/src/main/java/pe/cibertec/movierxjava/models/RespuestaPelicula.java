package pe.cibertec.movierxjava.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RespuestaPelicula {

    @SerializedName("results")
    ArrayList<Pelicula> peliculas;

    public RespuestaPelicula(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    public ArrayList<Pelicula> getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
}
