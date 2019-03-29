package pe.cibertec.movieapi;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity {

    TextInputEditText etNombre;
    ListView lvPelicula;
    Button btBuscar;

    ArrayAdapter<Pelicula> adapter;

    ArrayList<Pelicula> peliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        lvPelicula = findViewById(R.id.lvPelicula);
        btBuscar = findViewById(R.id.btBuscar);

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarElementos();

            }
        });





    }

    private void cargarElementos() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PeliculaInterface peliculaInterface  = retrofit.create(PeliculaInterface.class);

        Call<RespuestaPelicula> metodoBuscar = peliculaInterface.buscarPeliculas("3cae426b920b29ed2fb1c0749f258325",etNombre.getText().toString());

        metodoBuscar.enqueue(new Callback<RespuestaPelicula>() {
            @Override
            public void onResponse(Call<RespuestaPelicula> call, Response<RespuestaPelicula> response) {
                if (response.isSuccessful()){
                    RespuestaPelicula respuesta = response.body();
                    peliculas = respuesta.getElementos();

                    adapter = new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,peliculas);
                    lvPelicula.setAdapter(adapter);


                }
            }

            @Override
            public void onFailure(Call<RespuestaPelicula> call, Throwable t) {
                Toast.makeText(MainActivity.this,t.toString(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}
