package com.example.anuplal.movieapp.model.persistance;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.example.anuplal.movieapp.pojo.FavouriteMovie;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface FavouriteMovieDAO {

    @Query("select * from fav_movie_table")
    LiveData<List<FavouriteMovie>> getAllFavouiteMovies();

    @Insert(onConflict = REPLACE)
    void addFavouriteMovie(FavouriteMovie movieModel);
}

