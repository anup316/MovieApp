package com.example.anuplal.movieapp.model.persistance;



import com.example.anuplal.movieapp.pojo.FavouriteMovie;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import static androidx.room.OnConflictStrategy.REPLACE;


@Dao
public interface FavouriteMovieDAO {

    @Query("select * from fav_movie_table")
    LiveData<List<FavouriteMovie>> getAllFavouiteMovies();

    @Insert(onConflict = REPLACE)
    void addFavouriteMovie(FavouriteMovie movieModel);
}

