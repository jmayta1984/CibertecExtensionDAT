package pe.cibertec.agenda.contactos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pe.cibertec.agenda.ContactosApplication;
import pe.cibertec.agenda.R;
import pe.cibertec.agenda.model.Contacto;

public class ListaContactosFragment extends Fragment
        implements ContactosAdapter.OnContactoClickListener {

    private RecyclerView recyclerView;
    private List<Contacto> contactoList;
    private ContactosAdapter adapter;

    private ContactosFragment.ContactosCallback contactosCallback;
    private OnContactoFavoritoListener onContactoFavoritoListener;

    private boolean esListaDeFavoritos;

    public static ListaContactosFragment newInstance(boolean esListaDeFavoritos) {
        ListaContactosFragment f = new ListaContactosFragment();
        Bundle args = new Bundle();
        args.putBoolean("favoritos", esListaDeFavoritos);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            esListaDeFavoritos = getArguments().getBoolean("favoritos");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            contactosCallback = (ContactosFragment.ContactosCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    context.toString() + " debe implementar ContactosCallback");
        }

        try {
            onContactoFavoritoListener = (OnContactoFavoritoListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    context.toString() + " debe implementar OnContactoFavoritoListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.lista_contactos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (esListaDeFavoritos) {
            contactoList = ((ContactosApplication) getActivity().getApplication())
                    .getContactosFavoritos();
        } else {
            contactoList = ((ContactosApplication) getActivity().getApplication())
                    .getContactoList();
        }

        adapter = new ContactosAdapter(contactoList);
        adapter.setOnContactoClickListener(this);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        actualizarListaContactos();
    }

    @Override
    public void onContactoClick(Contacto contacto) {
        contactosCallback.onSeleccionarContacto(contacto);
    }

    @Override
    public void onContactoFavorito(Contacto contacto) {
        ((ContactosApplication) getActivity().getApplication())
                .actualizarContacto(contacto);
        onContactoFavoritoListener.onContactoFavorito(contacto);
    }

    public void actualizarListaContactos() {
        if (esListaDeFavoritos) {
            contactoList = ((ContactosApplication) getActivity().getApplication())
                    .getContactosFavoritos();
        } else {
            contactoList = ((ContactosApplication) getActivity().getApplication())
                    .getContactoList();
        }

        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                adapter.setContactoList(contactoList);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public interface OnContactoFavoritoListener {
        void onContactoFavorito(Contacto contacto);
    }
}
