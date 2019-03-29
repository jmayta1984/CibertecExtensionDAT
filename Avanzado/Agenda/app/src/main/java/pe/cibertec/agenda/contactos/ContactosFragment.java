package pe.cibertec.agenda.contactos;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import pe.cibertec.agenda.model.Contacto;
import pe.cibertec.agenda.ContactosApplication;
import pe.cibertec.agenda.R;

public class ContactosFragment extends Fragment
        implements View.OnClickListener {

    private ContactosCallback contactosCallback;
    private ContactosPagerAdapter pagerAdapter;
    private ViewPager viewPager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            contactosCallback = (ContactosCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(
                    context.toString() + " debe implementar ContactosCallback");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_contactos, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.pager);
        pagerAdapter = new ContactosPagerAdapter(getFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        FloatingActionButton fabNuevoContacto = view.findViewById(R.id.fabNuevoContacto);
        fabNuevoContacto.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        contactosCallback.onCrearNuevoContacto();
    }

    public void actualizarListaFavoritos() {
        ListaContactosFragment fragment = (ListaContactosFragment)
                pagerAdapter.getItem(1);
        fragment.actualizarListaContactos();
    }

    public void actualizarListaContactos() {
        ListaContactosFragment fragment = (ListaContactosFragment)
                pagerAdapter.getItem(0);
        fragment.actualizarListaContactos();
    }

    public interface ContactosCallback {
        void onCrearNuevoContacto();
        void onSeleccionarContacto(Contacto contacto);
    }
}
