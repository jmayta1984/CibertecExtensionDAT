package com.example.movieexam;


import com.google.gson.annotations.SerializedName;

import java.util.List;

class MovieResponse {
    @SerializedName("results")
    private List<Movie> movies;

    List<Movie> getMovies() {
        return movies;
    }

}
