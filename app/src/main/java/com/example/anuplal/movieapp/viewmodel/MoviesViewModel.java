package com.example.anuplal.movieapp.viewmodel;

import android.app.Application;

import com.example.anuplal.movieapp.model.network.ApiServiceProvider;
import com.example.anuplal.movieapp.pojo.Result;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MoviesViewModel extends AndroidViewModel {

    private final LiveData<List<Result>> theatreObservable;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        theatreObservable= ApiServiceProvider.getInstance().fetMoviesList("d9ddc031606a5496292daf4bd9f3f5b0");
    }

    public LiveData<List<Result>> getTheatreObservable() {
        return theatreObservable;
    }



}
