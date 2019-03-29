package pe.cibertec.agendaroommvp.models;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ContactoDao {

    @Insert
    void insertarContactos(Contacto... contactos);

    @Update
    void actualizarContacto(Contacto contacto);

    @Delete
    void eliminarContacto(Contacto contacto);

    @Query("select * from contacto")
    List<Contacto> listarTodos();

    @Query("select * from contacto where:nombre")
    List<Contacto> listarPorNombre(String nombre);

}
