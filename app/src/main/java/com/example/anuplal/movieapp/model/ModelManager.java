package com.example.anuplal.movieapp.model;

import android.app.Application;

import com.example.anuplal.movieapp.model.network.ApiServiceProvider;
import com.example.anuplal.movieapp.model.persistance.AppDatabase;
import com.example.anuplal.movieapp.pojo.FavouriteMovie;
import com.example.anuplal.movieapp.pojo.Result;

import java.net.HttpCookie;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import static com.example.anuplal.movieapp.Constants.API_KEY;

public class ModelManager {

    ApiServiceProvider mProvider;
    AppDatabase mDatabase;

    private static ModelManager INSTANCE;
    private MutableLiveData<List<Result>> theatreObservable;


    private ModelManager(Application application) {
        mProvider = ApiServiceProvider.getInstance();
        mDatabase = AppDatabase.getDatabase(application);
    }

    public static synchronized ModelManager getInstance(Application application) {

        if (INSTANCE == null) {
            INSTANCE = new ModelManager(application);
        }

        return INSTANCE;
    }


    LiveData<List<Result>> getMovies() {


        return null;
    }

    public void addToFavourite(FavouriteMovie favouriteMovie) {
        mDatabase.movieModel().addFavouriteMovie(favouriteMovie);
    }

    public FavouriteMovie getFavouriteMovie(int id) {
        return mDatabase.movieModel().getFavouriteMovie(id);
    }

    public LiveData<List<FavouriteMovie>> getAllMovies() {
       return mDatabase.movieModel().getAllFavouiteMovies();

    }





}
