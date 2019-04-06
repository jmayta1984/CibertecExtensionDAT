package pe.edu.cibertec.agendaroom;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.List;

class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactPrototype> {

    private List<Contact> items;

    public ContactAdapter(List<Contact> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ContactPrototype onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;

        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.prototype_contact, viewGroup, false);
        ContactPrototype contactPrototype = new ContactPrototype(view);

        return contactPrototype;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactPrototype contactPrototype, final int position) {
        contactPrototype.tvName.setText(items.get(position).getName() +"\n"+ items.get(position).getId() ) ;

        contactPrototype.cvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Obteniendo el contacto seleccionado
                Contact contact = items.get(position);

                Gson gson = new Gson();
                Intent intent = new Intent(v.getContext(), ContactActivity.class);
                intent.putExtra("contact", gson.toJson(contact));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ContactPrototype extends RecyclerView.ViewHolder {
        CardView cvContact;
        TextView tvName;

        public ContactPrototype(@NonNull final View itemView) {
            super(itemView);

            cvContact = itemView.findViewById(R.id.cvContact);
            tvName = itemView.findViewById(R.id.tvName);


        }
    }
}
