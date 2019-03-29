package pe.cibertec.agenda.contactos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;

import pe.cibertec.agenda.model.Contacto;
import pe.cibertec.agenda.R;

public class ContactosAdapter extends RecyclerView.Adapter<ContactosAdapter.ViewHolder> {

    private List<Contacto> contactoList;
    private OnContactoClickListener onContactoClickListener;

    public ContactosAdapter(List<Contacto> contactos) {
        this.contactoList = contactos;
    }

    public void setContactoList(List<Contacto> contactoList) {
        this.contactoList = contactoList;
    }

    public void setOnContactoClickListener(OnContactoClickListener listener) {
        this.onContactoClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.contacto_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contacto contacto = contactoList.get(position);
        holder.txtNombre.setText(contacto.getNombre());
        holder.txtTelefono.setText(contacto.getTelefono());
        holder.tgbFavorito.setChecked(contacto.isFavorito());
    }

    @Override
    public int getItemCount() {
        return contactoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtNombre, txtTelefono;
        ToggleButton tgbFavorito;

        public ViewHolder(final View itemView) {
            super(itemView);
            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtTelefono = itemView.findViewById(R.id.txtTelefono);
            tgbFavorito = itemView.findViewById(R.id.tgbFavorito);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onContactoClickListener != null){
                        Contacto contacto = contactoList.get(getAdapterPosition());
                        onContactoClickListener.onContactoClick(contacto);
                    }
                }
            });

            tgbFavorito.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    Contacto contacto = contactoList.get(getAdapterPosition());
                    contacto.setFavorito(b);
                    onContactoClickListener.onContactoFavorito(contacto);
                }
            });
        }
    }

    interface OnContactoClickListener {
        void onContactoClick(Contacto contacto);

        void onContactoFavorito(Contacto contacto);
    }
}
