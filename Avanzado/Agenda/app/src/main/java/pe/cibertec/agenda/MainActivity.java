package pe.cibertec.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import pe.cibertec.agenda.contactos.ContactosFragment;
import pe.cibertec.agenda.contactos.ListaContactosFragment;
import pe.cibertec.agenda.detallecontacto.DetalleContactoActivity;
import pe.cibertec.agenda.detallecontacto.DetalleContactoFragment;
import pe.cibertec.agenda.editarcontacto.EditarContactoActivity;
import pe.cibertec.agenda.editarcontacto.EditarContactoFragment;
import pe.cibertec.agenda.model.Contacto;

public class MainActivity extends AppCompatActivity
        implements ContactosFragment.ContactosCallback,
        EditarContactoFragment.OnContactoDetalleCallback,
        DetalleContactoFragment.OnEditarContactoCallback,
        ListaContactosFragment.OnContactoFavoritoListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: modificar pantalla de creacion de contacto
        Contacto contacto = new Contacto();
        contacto.setNombre("Cesar");
        contacto.setTelefono("999 999 999");
        ((ContactosApplication) getApplication()).agregarContacto(contacto);

        DetalleContactoFragment detalleContactoFragment = (DetalleContactoFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragDetalleContacto);
        if (detalleContactoFragment != null) {
            detalleContactoFragment.actualizarVistaContacto(contacto);
        }
    }

    @Override
    public void onCrearNuevoContacto() {
        DetalleContactoFragment detalleContactoFragment = (DetalleContactoFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragDetalleContacto);

        if (detalleContactoFragment == null) { // En telefono
            Intent intent = new Intent(this, EditarContactoActivity.class);
            startActivity(intent);
        } else {
            EditarContactoFragment dialogFragment = new EditarContactoFragment();
            dialogFragment.show(getSupportFragmentManager(), "editar_dialog");
        }
    }

    @Override
    public void onSeleccionarContacto(Contacto contacto) {
        DetalleContactoFragment detalleContactoFragment = (DetalleContactoFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragDetalleContacto);

        if (detalleContactoFragment == null) { // En telefono
            Intent intent = new Intent(this, DetalleContactoActivity.class);
            intent.putExtra(DetalleContactoActivity.EXTRA_CONTACTO, contacto);
            startActivity(intent);
        } else { // En tablet
            detalleContactoFragment.actualizarVistaContacto(contacto);
        }
    }

    @Override
    public void actualizarContacto(Contacto contacto) {
        ContactosFragment contactosFragment = (ContactosFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragContactos);
        if (contactosFragment != null) {
//            contactosFragment.actualizarListaContactos();
        }

        DetalleContactoFragment detalleContactoFragment = (DetalleContactoFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragDetalleContacto);
        if (detalleContactoFragment != null) {
            detalleContactoFragment.actualizarVistaContacto(contacto);
        }
    }

    @Override
    public void eliminarContacto(Contacto contacto) {
        ContactosFragment contactosFragment = (ContactosFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragContactos);
        if (contactosFragment != null) {
//            contactosFragment.actualizarListaContactos();
        }
    }

    @Override
    public void onEditarContacto(Contacto contacto) {
        EditarContactoFragment dialogFragment = EditarContactoFragment
                .newInstance(contacto, false);
//        EditarContactoDialogFragment dialogFragment =  new EditarContactoDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "editar_dialog");
    }

    @Override
    public void onContactoFavorito(Contacto contacto) {
        ContactosFragment contactosFragment = (ContactosFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragContactos);
        if (contactosFragment !=null) {
            contactosFragment.actualizarListaFavoritos();
            if (!contacto.isFavorito()) {
                contactosFragment.actualizarListaContactos();
            }
        }
    }
}
