package com.example.jobs;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

@Database(entities = {Job.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract JobDao jobDao();

    private static AppDatabase instance = null;

    public static AppDatabase getInstance(Context context) {

        if (instance == null) {
            instance
                    = Room.databaseBuilder(context, AppDatabase.class, "db")
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }
}
