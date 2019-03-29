package pe.cibertec.agenda.editarcontacto;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import pe.cibertec.agenda.model.Contacto;
import pe.cibertec.agenda.ContactosApplication;
import pe.cibertec.agenda.R;

public class EditarContactoFragment extends DialogFragment {

    private static final int MENU_ITEM_ELIMINAR = 10;
    public static final String ARG_CONTACTO = "contacto";
    public static final String ARG_MOSTRAR_OPCIONES = "mostrar_opciones";

    private EditText edtNombre;
    private EditText edtTelefono;

    private Contacto contacto;
    private boolean mostrarOpciones;

    private OnContactoDetalleCallback OnContactoDetalleCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            OnContactoDetalleCallback = (OnContactoDetalleCallback) context;
        }catch (ClassCastException e) {
            throw new ClassCastException(
                    context.toString() + " debe implementar OnContactoDetalleCallback");
        }
    }

    public static EditarContactoFragment newInstance(Contacto contacto, boolean mostarOpciones) {
        EditarContactoFragment f = new EditarContactoFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_CONTACTO, contacto);
        args.putBoolean(ARG_MOSTRAR_OPCIONES, mostarOpciones);
        f.setArguments(args);
        return f;
    }

    public EditarContactoFragment() {
        // Constructor publico sin parametros requerido
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            contacto = getArguments().getParcelable(ARG_CONTACTO);
            mostrarOpciones = getArguments().getBoolean(ARG_MOSTRAR_OPCIONES);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mostrarOpciones) {
            return inflater.inflate(R.layout.fragment_editar_contacto, container, false);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.fragment_editar_contacto,
                null, false);

        edtNombre = view.findViewById(R.id.edtNombre);
        edtTelefono = view.findViewById(R.id.edtTelefono);

        String titulo;
        if (contacto != null) {
            actualizarVistaContacto(contacto);
            titulo = "Editar Contacto";
        } else {
            titulo = "Nuevo Contacto";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                .setTitle(titulo)
                .setView(view)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        guardar();
                    }
                })
                .setNegativeButton("Cancelar", null);
        return builder.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edtNombre = view.findViewById(R.id.edtNombre);
        edtTelefono = view.findViewById(R.id.edtTelefono);

        if (contacto != null) {
            actualizarVistaContacto(contacto);
        }
        setHasOptionsMenu(mostrarOpciones);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.contacto, menu);
        if (contacto != null) {
            menu.add(0, MENU_ITEM_ELIMINAR, Menu.NONE, "Eliminar")
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_guardar:
                guardar();
                break;
            case MENU_ITEM_ELIMINAR:
                eliminar();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void guardar() {
        String nombre = edtNombre.getText().toString();
        String telefono = edtTelefono.getText().toString();

        if (contacto == null) { // Si se crea un contacto nuevo
            contacto = new Contacto();
            contacto.setNombre(nombre);
            contacto.setTelefono(telefono);
            ((ContactosApplication) getActivity().getApplication())
                    .agregarContacto(contacto);
        } else { // Actualiza el contacto existente
            contacto.setNombre(nombre);
            contacto.setTelefono(telefono);
            ((ContactosApplication) getActivity().getApplication())
                    .actualizarContacto(contacto);
        }

        OnContactoDetalleCallback.actualizarContacto(contacto);
    }

    private void eliminar() {
        ((ContactosApplication) getActivity().getApplication())
                .eliminarContacto(contacto);

        OnContactoDetalleCallback.eliminarContacto(contacto);
    }

    public void actualizarVistaContacto(Contacto contacto) {
        this.contacto = contacto;
        edtNombre.setText(contacto.getNombre());
        edtTelefono.setText(contacto.getTelefono());
    }

    public interface OnContactoDetalleCallback {
        void actualizarContacto(Contacto contacto);
        void eliminarContacto(Contacto contacto);
    }
}
