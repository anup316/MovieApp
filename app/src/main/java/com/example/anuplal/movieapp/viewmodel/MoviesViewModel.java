package com.example.anuplal.movieapp.viewmodel;

import android.app.Application;

import com.example.anuplal.movieapp.model.network.ApiServiceProvider;
import com.example.anuplal.movieapp.pojo.Result;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import static com.example.anuplal.movieapp.Constants.API_KEY;

public class MoviesViewModel extends AndroidViewModel {

    private final MutableLiveData<List<Result>> theatreObservable;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        theatreObservable = ApiServiceProvider.getInstance().fetMoviesList(API_KEY);
    }

    public LiveData<List<Result>> getTheatreObservable() {
        return theatreObservable;
    }

    public void sort(final int type) {

        List<Result> value = theatreObservable.getValue();
        Collections.sort(value, new Comparator<Result>() {
            @Override
            public int compare(Result result1, Result result2) {
                if (type == 2) {
                    return (int) (result2.getVoteAverage() - result1.getVoteAverage());
                } else {
                    return (int) (result2.getPopularity() - result1.getPopularity());
                }

            }
        });

        theatreObservable.setValue(value);

    }


}
