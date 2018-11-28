package com.example.anuplal.movieapp.pojo;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fav_movie_table")
public class FavouriteMovie {

    @PrimaryKey
    public int movieId;
    private String movieName;

    public FavouriteMovie(int movieId, String movieName) {
        this.movieId = movieId;
        this.movieName = movieName;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getMovieName() {
        return movieName;
    }
}
