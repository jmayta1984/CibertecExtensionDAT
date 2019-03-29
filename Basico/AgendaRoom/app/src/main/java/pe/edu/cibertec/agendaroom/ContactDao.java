package pe.edu.cibertec.agendaroom;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("select * from contact")
    List<Contact> getAll();

    @Query("select * from contact where id=:id")
    Contact getById(int id);

    @Query("delete from contact where name=:name")
    void deleteName(String name);

    @Insert
    void insert(Contact... contacts);

    @Update
    void update(Contact... contacts);

    @Delete
    void delete(Contact contact);
}
