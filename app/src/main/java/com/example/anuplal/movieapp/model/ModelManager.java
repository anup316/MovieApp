package com.example.anuplal.movieapp.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.view.Display;

import com.example.anuplal.movieapp.model.network.ApiServiceProvider;
import com.example.anuplal.movieapp.model.persistance.AppDatabase;
import com.example.anuplal.movieapp.pojo.FavouriteMovie;
import com.example.anuplal.movieapp.pojo.Result;

import java.util.List;

public class ModelManager {

    ApiServiceProvider mProvider;
    AppDatabase mDatabase;

    ModelManager(Application application) {
        mProvider = ApiServiceProvider.getInstance();
        mDatabase = AppDatabase.getDatabase(application);
    }


    LiveData<List<Result>> getMovies(){
        return null;
    }

    void addToFavourite(FavouriteMovie favouriteMovie){
        mDatabase.movieModel().addFavouriteMovie(favouriteMovie);
    }


}
