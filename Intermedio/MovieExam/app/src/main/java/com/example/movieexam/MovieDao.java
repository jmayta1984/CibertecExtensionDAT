package com.example.movieexam;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("select id,title,overview,posterPath,releaseDate from movie")
    List<Movie> getAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Movie... jobs);

    @Delete
    void delete(Movie... jobs);
}
