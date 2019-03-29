package pe.cibertec.movierxjava.models;

import com.google.gson.annotations.SerializedName;

public class Pelicula {

    @SerializedName("title")
    private String titulo;

    @SerializedName("overview")
    private String descripcion;

    @SerializedName("poster_path")
    private String urlImagen;

    public Pelicula(String titulo, String descripcion, String urlImagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
