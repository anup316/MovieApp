package com.example.anuplal.movieapp.viewmodel;

import android.app.Application;

import com.example.anuplal.movieapp.model.network.ApiServiceProvider;
import com.example.anuplal.movieapp.pojo.review.Review;
import com.example.anuplal.movieapp.pojo.trailer.Video;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import static com.example.anuplal.movieapp.Constants.API_KEY;

public class PosterDetailViewModel extends AndroidViewModel {

    private MutableLiveData<List<Review>> reviewList;
    private MutableLiveData<List<Video>> videoList;

    public PosterDetailViewModel(@NonNull Application application) {
        super(application);

    }


    public MutableLiveData<List<Review>> getReviewList(int movieId) {
        this.reviewList = ApiServiceProvider.getInstance().fetchReviewList(movieId, API_KEY);
        return reviewList;
    }

    public MutableLiveData<List<Video>> getVideoList(int movieId) {
        this.videoList = ApiServiceProvider.getInstance().fetchVideos(movieId, API_KEY);
        return videoList;
    }

}

