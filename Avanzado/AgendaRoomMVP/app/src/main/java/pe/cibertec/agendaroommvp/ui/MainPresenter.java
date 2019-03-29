package pe.cibertec.agendaroommvp.ui;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import pe.cibertec.agendaroommvp.models.AppDatabase;
import pe.cibertec.agendaroommvp.models.Contacto;

class MainPresenter implements MainPresenterInterface {

    MainViewInterface mvi;


    public MainPresenter(MainViewInterface mvi) {
        this.mvi = mvi;

    }

    @Override
    public void obtenerContactos() {
        new TareaListarContactos().execute();
    }

    @Override
    public void agregarContacto(String s) {
        Contacto contacto = new Contacto(s);
        new TareaAgregarContacto().execute(contacto);
    }

    private class TareaListarContactos extends AsyncTask<Void, Void, List<Contacto>> {
        @Override
        protected List<Contacto> doInBackground(Void... voids) {
            return AppDatabase.nuevaInstancia((Context) mvi).getContactoDao().listarTodos();
        }


        @Override
        protected void onPostExecute(List<Contacto> contactos) {
            super.onPostExecute(contactos);
            mvi.mostrarContactos(contactos);

        }
    }


    private class TareaAgregarContacto extends AsyncTask<Contacto, Void, Void> {
        @Override
        protected Void doInBackground(Contacto... contactos) {
            AppDatabase.nuevaInstancia((Context) mvi).getContactoDao().insertarContactos(contactos);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

        }
    }
}
