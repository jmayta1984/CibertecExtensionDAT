package pe.cibertec.agendaroom;

import android.os.AsyncTask;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextInputEditText etNombre;
    Button btAgregar;
    RecyclerView rvContacto;

    List<Contacto> elementos;
    AdaptadorContacto adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = findViewById(R.id.etNombre);
        btAgregar = findViewById(R.id.btAgregar);
        rvContacto = findViewById(R.id.rvContacto);


        btAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Agregar el contacto
                Contacto contacto = new Contacto(etNombre.getText().toString());

                new TareaAgregarContacto().execute(contacto);
                new TareaListarContactos().execute();

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        new TareaListarContactos().execute();




    }

    private class TareaAgregarContacto extends AsyncTask<Contacto,Void,Void>{
        @Override
        protected Void doInBackground(Contacto... contactos) {
            AppDatabase.nuevaInstancia(MainActivity.this).getContactoDao().insertarContactos(contactos);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adaptador.notifyDataSetChanged();
        }
    }

    private class TareaListarContactos extends AsyncTask<Void, Void,List<Contacto>>{
        @Override
        protected List<Contacto> doInBackground(Void... voids) {
           return AppDatabase.nuevaInstancia(MainActivity.this).getContactoDao().listarTodos();
        }


        @Override
        protected void onPostExecute(List<Contacto> contactos) {
            super.onPostExecute(contactos);
            elementos = contactos;
            adaptador = new AdaptadorContacto(elementos);
            rvContacto.setAdapter(adaptador);

            rvContacto.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
    }
}
