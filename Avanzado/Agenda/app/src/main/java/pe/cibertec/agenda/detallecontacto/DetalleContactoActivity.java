package pe.cibertec.agenda.detallecontacto;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import pe.cibertec.agenda.editarcontacto.EditarContactoActivity;
import pe.cibertec.agenda.model.Contacto;

public class DetalleContactoActivity extends AppCompatActivity
    implements DetalleContactoFragment.OnEditarContactoCallback{

    public static final String EXTRA_CONTACTO = "contacto";

    private Contacto contacto;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contacto = getIntent().getParcelableExtra(EXTRA_CONTACTO);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(android.R.id.content, DetalleContactoFragment.newInstance(contacto));
        ft.commit();
    }

    @Override
    public void onEditarContacto(Contacto contacto) {
        Intent intent = new Intent(this, EditarContactoActivity.class);
        intent.putExtra(EditarContactoActivity.EXTRA_CONTACTO, contacto);
        startActivity(intent);
    }
}
