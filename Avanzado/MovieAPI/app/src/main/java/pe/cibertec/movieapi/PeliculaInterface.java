package pe.cibertec.movieapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PeliculaInterface {

    @GET("movie")
    Call<RespuestaPelicula> buscarPeliculas(@Query("api_key") String llave,
                                          @Query("query") String nombre);


}
