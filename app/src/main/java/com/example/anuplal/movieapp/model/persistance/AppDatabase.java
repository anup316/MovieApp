package com.example.anuplal.movieapp.model.persistance;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.anuplal.movieapp.pojo.FavouriteMovie;

@Database(entities = {FavouriteMovie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public abstract FavouriteMovieDAO movieModel();

    public static synchronized AppDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "movie_db")
                            .build();
        }
        return INSTANCE;
    }
}
