package pe.cibertec.movierxjava.ui;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.cibertec.movierxjava.R;
import pe.cibertec.movierxjava.adapters.PeliculaAdapter;
import pe.cibertec.movierxjava.models.RespuestaPelicula;

public class SearchActivity extends AppCompatActivity implements SearchViewInterface {

    SearchPresenter presenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private SearchView searchView;

    @BindView(R.id.rvPelicula)
    RecyclerView rvPelicula;

    PeliculaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        configurarVistas();
        configurarMVP();

    }

    private void configurarMVP() {
        presenter = new SearchPresenter(this);
    }


    private void configurarVistas() {
        setSupportActionBar(toolbar);
        rvPelicula.setLayoutManager(new LinearLayoutManager(this));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        SearchManager buscador = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(buscador.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Ingrese el nombre de la película");
        presenter.obtenerPeliculas(searchView);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void mostrarPeliculas(RespuestaPelicula respuestaPelicula) {

        adapter = new PeliculaAdapter(respuestaPelicula.getPeliculas());
        rvPelicula.setAdapter(adapter);

    }
}
