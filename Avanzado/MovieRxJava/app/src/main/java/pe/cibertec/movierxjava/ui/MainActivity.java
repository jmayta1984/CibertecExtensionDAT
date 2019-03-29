package pe.cibertec.movierxjava.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.cibertec.movierxjava.R;
import pe.cibertec.movierxjava.adapters.PeliculaAdapter;
import pe.cibertec.movierxjava.models.RespuestaPelicula;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    @BindView(R.id.rvPelicula)
    RecyclerView rvPelicula;

    PeliculaAdapter adapter;
    MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        configurarMVP();
        configurarVistas();
        obtenerPeliculas();
    }

    private void obtenerPeliculas() {
        presenter.obtenerPeliculas();
    }

    private void configurarVistas() {
        rvPelicula.setLayoutManager(new LinearLayoutManager(this));
    }

    private void configurarMVP() {
        presenter = new MainPresenter(this);
    }

    @Override
    public void mostrarPeliculas(RespuestaPelicula respuesta) {
        if (respuesta != null){
            adapter = new PeliculaAdapter(respuesta.getPeliculas());
            rvPelicula.setAdapter(adapter);
        }
    }
}
