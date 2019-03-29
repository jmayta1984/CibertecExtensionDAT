package pe.cibertec.agendaroommvp.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

import pe.cibertec.agendaroommvp.R;
import pe.cibertec.agendaroommvp.adapters.AdaptadorContacto;
import pe.cibertec.agendaroommvp.models.Contacto;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    TextInputEditText etNombre;
    Button btAgregar;
    RecyclerView rvContacto;

    MainPresenter presenter;
    AdaptadorContacto adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        iniciarVistas();
        configurarVistas();
        configurarMvp();
        obtenerContactos();

        btAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Agregar el contacto
                presenter.agregarContacto(etNombre.getText().toString());
                obtenerContactos();
            }
        });
    }

    private void iniciarVistas() {
        etNombre = findViewById(R.id.etNombre);
        btAgregar = findViewById(R.id.btAgregar);
        rvContacto = findViewById(R.id.rvContacto);
    }

    private void obtenerContactos() {
        presenter.obtenerContactos();
    }

    private void configurarVistas() {
        rvContacto.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    private void configurarMvp() {
        this.presenter = new MainPresenter(this);
    }


    @Override
    public void mostrarContactos(List<Contacto> contactos) {
        adaptador = new AdaptadorContacto(contactos);
        rvContacto.setAdapter(adaptador);
    }


}
