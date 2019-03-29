package com.example.agenda;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.gson.Gson;

public class DetailContactActivity extends AppCompatActivity {

    TextInputEditText etName;

    Contact contact;
    /*
    String name;
    int id;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contact);

        etName = findViewById(R.id.etName);


        Gson gson = new Gson();
        String strContact = getIntent().getStringExtra("contact");
        contact = gson.fromJson(strContact, Contact.class);


        /*
        name = getIntent().getStringExtra("name");
        id = getIntent().getIntExtra("id", -1);
        */

        // Verificando que se haya invocado al seleccionar un item
        if (contact != null) {
            etName.setText(contact.getName());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AppDatabase db = AppDatabase.getInstance(this);

        switch (item.getItemId()) {
            case R.id.btSave:
                // Modificando
                if (contact != null) {
                    contact.setName(etName.getText().toString());
                    db.contactDao().update(contact);

                }
                // Creando un nuevo contacto
                else {
                    contact = new Contact();
                    contact.setName(etName.getText().toString());
                    db.contactDao().insert(contact);
                }

                break;
            case R.id.btDelete:
                db.contactDao().delete(contact);

                break;
        }


        finish();
        return true;
    }
}
