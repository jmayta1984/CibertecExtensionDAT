package pe.cibertec.movierxjava.network;


import io.reactivex.Observable;
import pe.cibertec.movierxjava.models.RespuestaPelicula;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface InterfaceNetwork {

    @GET("discover/movie")
    Observable<RespuestaPelicula> getPeliculas(@Query("api_key") String llave);

    @GET("search/movie")
    Observable<RespuestaPelicula> getPeliculasPorNombre(@Query("api_key") String llave,
                                                        @Query("query") String titulo);
}
