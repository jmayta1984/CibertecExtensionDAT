package pe.edu.cibertec.pokeapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonInterface {

    @GET("pokemon/{id}")
    Call<Pokemon> getPokemon(@Path("id") String id);

}
