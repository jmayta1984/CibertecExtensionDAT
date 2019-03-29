package pe.cibertec.agenda.detallecontacto;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pe.cibertec.agenda.ContactosApplication;
import pe.cibertec.agenda.R;
import pe.cibertec.agenda.model.Contacto;

public class DetalleContactoFragment extends Fragment {

    private static final String ARG_CONTACTO = "contacto";

    private Contacto contacto;

    private TextView txtNombre, txtTelefono;

    private OnEditarContactoCallback onEditarContactoCallback;

    public static DetalleContactoFragment newInstance(Contacto contacto) {
        DetalleContactoFragment f = new DetalleContactoFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CONTACTO, contacto);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onEditarContactoCallback = (OnEditarContactoCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    context.toString() + " debe implementar OnEditarContactoCallback");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contacto = getArguments().getParcelable(ARG_CONTACTO);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_contacto, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        txtNombre = view.findViewById(R.id.txtNombre);
        txtTelefono = view.findViewById(R.id.txtTelefono);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (contacto != null) {
            contacto = ((ContactosApplication) getActivity().getApplication())
                    .getContacto(contacto.getId());
            actualizarVistaContacto(contacto);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.detalle_contacto, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onEditarContactoCallback.onEditarContacto(contacto);
        return super.onOptionsItemSelected(item);
    }

    public void actualizarVistaContacto(Contacto contacto) {
        this.contacto = contacto;
        txtNombre.setText(contacto.getNombre());
        txtTelefono.setText(contacto.getTelefono());
    }

    public interface OnEditarContactoCallback {
        void onEditarContacto(Contacto contacto);
    }
}
