package pe.cibertec.agenda.editarcontacto;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import pe.cibertec.agenda.model.Contacto;

public class EditarContactoActivity extends AppCompatActivity
    implements EditarContactoFragment.OnContactoDetalleCallback {

    public static final String EXTRA_CONTACTO = "contacto";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Contacto contacto = getIntent().getParcelableExtra(EXTRA_CONTACTO);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(android.R.id.content, EditarContactoFragment.newInstance(contacto, true));
        ft.commit();
    }

    @Override
    public void actualizarContacto(Contacto contacto) {
        finish();
    }

    @Override
    public void eliminarContacto(Contacto contacto) {
        finish();
    }
}
