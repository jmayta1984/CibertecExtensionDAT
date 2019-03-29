package pe.edu.cibertec.moviedbapi;



import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

class MovieResponse {

    @SerializedName("results")
    private ArrayList<Movie> movies;

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
