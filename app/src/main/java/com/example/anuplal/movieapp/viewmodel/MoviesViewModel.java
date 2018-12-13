package com.example.anuplal.movieapp.viewmodel;

import android.app.Application;
import android.icu.text.Normalizer2;

import com.example.anuplal.movieapp.Constants;
import com.example.anuplal.movieapp.model.ModelManager;
import com.example.anuplal.movieapp.model.network.ApiServiceProvider;
import com.example.anuplal.movieapp.pojo.FavouriteMovie;
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
    private final LiveData<List<FavouriteMovie>> allFavouriteMovieObservable;

    public MoviesViewModel(@NonNull Application application) {
        super(application);
        theatreObservable = ApiServiceProvider.getInstance().fetMoviesList(API_KEY);
        allFavouriteMovieObservable = ModelManager.getInstance(application).getAllMovies();
    }

    public LiveData<List<Result>> getTheatreObservable() {
        return theatreObservable;
    }

    public LiveData<List<FavouriteMovie>> getAllFavouriteMovieObservable() {
        return allFavouriteMovieObservable;
    }

    public void sort(final Constants.CATEGORY type) {

        List<Result> value = theatreObservable.getValue();

        Collections.sort(value, new Comparator<Result>() {
            @Override
            public int compare(Result result1, Result result2) {

                switch (type) {
                    case HIGHTLYRATED:
                        return Float.compare(result2.getVoteAverage(), result1.getVoteAverage());

                    case FAVOURITE:
                        return Boolean.compare(result2.isFavourite(), result1.isFavourite());

                    case MOSTPOPULAR:
                    default:
                        return Float.compare(result2.getPopularity(), result1.getPopularity());

                }

            }
        });

        theatreObservable.setValue(value);

    }

    public void mergeList() {
        List<Result> movieList = theatreObservable.getValue();
        if (movieList != null) {
            for (Result result : movieList) {
                if (isFavourite(result.getId())) {
                    result.setFavourite(true);
                }
            }
            theatreObservable.setValue(movieList);
        }
    }

    private boolean isFavourite(int id) {
        List<FavouriteMovie> favouriteMovies = allFavouriteMovieObservable.getValue();

        if (favouriteMovies != null && favouriteMovies.size() > 0) {
            for (FavouriteMovie favouriteMovie : favouriteMovies) {
                if(favouriteMovie.getMovieId() == id){
                    return true;
                }
            }
        }

        return false;
    }

}
