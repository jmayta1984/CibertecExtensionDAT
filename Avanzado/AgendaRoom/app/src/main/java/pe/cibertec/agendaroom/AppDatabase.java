package pe.cibertec.agendaroom;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Contacto.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private ContactoDao contactoDao;

    private static AppDatabase instancia;

    public static AppDatabase nuevaInstancia(Context contexto) {
        if (instancia == null) {
            instancia = Room.databaseBuilder(contexto, AppDatabase.class, "agenda.db")
                    .build();
        }
        return instancia;
    }

    public abstract ContactoDao getContactoDao();

}
