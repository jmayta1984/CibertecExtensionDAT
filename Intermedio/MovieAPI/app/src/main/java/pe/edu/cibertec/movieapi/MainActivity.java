package pe.edu.cibertec.movieapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText etMovie;
    Button btSearch;
    TextView tvTitle, tvYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMovie = findViewById(R.id.etMovie);
        btSearch = findViewById(R.id.btSearch);

        tvTitle = findViewById(R.id.tvTitle);
        tvYear = findViewById(R.id.tvYear);

        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String apikey = "6fc43ba7";
                String movie = etMovie.getText().toString();

                searchMovie(apikey, movie);
            }
        });
    }

    private void searchMovie(String apikey, String movie) {
        // Crear un objeto de la clase Retrofit
        // baseUrl: URL sin condiciones
        // convererFactory: Converson de JSON a un objeto de una clase determinada

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Interface que contiene el/los método(s)
        MovieInterface movieInterface = retrofit.create(MovieInterface.class);

        // Declarar el método de búsqueda
        Call<Movie> searchMethod = movieInterface.searchMovie(apikey, movie);

        // Implementar el método de búsqueda
        // Considerar que se puede tener dos casos
        // onResponse: El llamado al servicio retornó una respuesta
        //  Verificar que la respuesta sea satisfactoria
        // onFailuer: No se obtuvo respuesta alguna
        searchMethod.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
               if (response.isSuccessful()){

                   Movie movie = response.body();

                   tvTitle.setText(movie.getTitle());
                   tvYear.setText(movie.getYear());
               }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                Log.d("MainActivity", t.toString());
            }
        });

    }
}
