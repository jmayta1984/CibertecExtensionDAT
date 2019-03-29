package com.example.agenda;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM Contact")
    List<Contact> getAll();

    @Query("DELETE FROM contact where name=:condition")
    void deleteByName(String condition);

    @Query("SELECT * FROM contact where name like '%' || :condition || '%'")
    List<Contact> getByName(String condition);

    @Insert
    void insert(Contact... contacts);

    @Delete
    void delete(Contact... contacts);

    @Update
    void update(Contact contact);
}
