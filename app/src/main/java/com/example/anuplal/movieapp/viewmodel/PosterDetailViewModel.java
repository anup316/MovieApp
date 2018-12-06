package com.example.anuplal.movieapp.viewmodel;

import android.app.Application;

import com.example.anuplal.movieapp.model.network.ApiServiceProvider;
import com.example.anuplal.movieapp.pojo.Result;
import com.example.anuplal.movieapp.pojo.review.Review;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import static com.example.anuplal.movieapp.Constants.API_KEY;

public class PosterDetailViewModel extends AndroidViewModel {

    private MutableLiveData<List<Review>> reviewList;

    public PosterDetailViewModel(@NonNull Application application) {
        super(application);
    }


    public MutableLiveData<List<Review>> getReviewList(int movieId) {
        this.reviewList = ApiServiceProvider.getInstance().fetchReviewList(movieId, API_KEY);
        return reviewList;
    }


}

