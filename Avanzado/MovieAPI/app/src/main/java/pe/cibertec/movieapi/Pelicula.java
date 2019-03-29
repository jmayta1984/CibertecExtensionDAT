package pe.cibertec.movieapi;

import com.google.gson.annotations.SerializedName;

public class Pelicula {

    @SerializedName("title")
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return titulo;
    }
}
