package com.example.agenda;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactPrototype> {
    List<Contact> items;

    public ContactAdapter(List<Contact> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ContactPrototype onCreateViewHolder(@NonNull final ViewGroup viewGroup, int position) {
        View view;

        view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.prototype_contact, viewGroup, false);

        ContactPrototype prototype = new ContactPrototype(view);


        return prototype;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactPrototype contactPrototype, final int position) {
        contactPrototype.tvName.setText(items.get(position).getName());
        contactPrototype.cvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Gson gson = new Gson();
                Intent intent = new Intent(v.getContext(),DetailContactActivity.class);

                intent.putExtra("contact", gson.toJson(items.get(position)));
                 /*
                intent.putExtra("name",items.get(position).getName());
                intent.putExtra("id",items.get(position).getId());
                */
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

        public ContactPrototype(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            cvContact = itemView.findViewById(R.id.cvContact);

        }
    }
}
