package com.example.anuplal.movieapp.model.persistance;

;
import android.content.Context;

import com.example.anuplal.movieapp.pojo.FavouriteMovie;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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
