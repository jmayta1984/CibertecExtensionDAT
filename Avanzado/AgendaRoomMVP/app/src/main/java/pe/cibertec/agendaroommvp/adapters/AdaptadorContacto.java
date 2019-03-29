package pe.cibertec.agendaroommvp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.cibertec.agendaroommvp.R;
import pe.cibertec.agendaroommvp.models.Contacto;

public class AdaptadorContacto extends RecyclerView.Adapter<AdaptadorContacto.ContactoCelda> {

    List<Contacto> contactos;


    public AdaptadorContacto(List<Contacto> contactos) {
        this.contactos = contactos;
    }

    @NonNull
    @Override
    public ContactoCelda onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View vista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.celda_contacto, parent, false);

        return new ContactoCelda(vista);
    }

    // Mostrar la información
    @Override
    public void onBindViewHolder(@NonNull ContactoCelda celda, int posicion) {
        celda.tvNombre.setText(contactos.get(posicion).getNombre());

    }


    // Cantidad de elementos a mostrar
    @Override
    public int getItemCount() {
        return contactos.size();
    }

    public class ContactoCelda extends RecyclerView.ViewHolder {

        TextView tvNombre;

        public ContactoCelda(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);

        }
    }
}
