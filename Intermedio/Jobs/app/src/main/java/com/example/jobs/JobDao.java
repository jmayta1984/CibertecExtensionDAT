package com.example.jobs;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface JobDao {

    @Query("select * from job")
    List<Job> getAll();

    @Insert
    void insert(Job... jobs);

    @Delete
    void delete(Job... jobs);
}
