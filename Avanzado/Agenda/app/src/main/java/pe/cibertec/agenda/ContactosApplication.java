package pe.cibertec.agenda;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pe.cibertec.agenda.model.Contacto;

public class ContactosApplication extends Application {

    private Map<Integer, Contacto> contactoMap;
    private int id = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        contactoMap = new HashMap<>();
    }

    public Contacto getContacto(int id) {
        return contactoMap.get(id);
    }

    public List<Contacto> getContactoList() {
        return new ArrayList<>(contactoMap.values());
    }

    public List<Contacto> getContactosFavoritos() {
        List<Contacto> contactos = new ArrayList<>(contactoMap.values());
        List<Contacto> favoritos = new ArrayList<>();

        for(Contacto contacto : contactos) {
            if (contacto.isFavorito()){
                favoritos.add(contacto);
            }
        }
        return favoritos;
    }

    public void agregarContacto(Contacto contacto) {
        contacto.setId(++id);
        contactoMap.put(contacto.getId(), contacto);
    }

    public void actualizarContacto(Contacto contacto) {
        contactoMap.put(contacto.getId(), contacto);
    }

    public void eliminarContacto(Contacto contacto) {
        contactoMap.remove(contacto.getId());
    }
}
