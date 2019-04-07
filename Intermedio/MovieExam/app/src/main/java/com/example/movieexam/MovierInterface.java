package com.example.movieexam;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface MovierInterface {

    @GET("movie")
    Call<MovieResponse> searchMovies(@Query("api_key") String apikey,
                                     @Query("query") String movie,
                                     @Query("page") String page);
}
