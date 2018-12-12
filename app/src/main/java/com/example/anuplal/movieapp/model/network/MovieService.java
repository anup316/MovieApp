package com.example.anuplal.movieapp.model.network;

import com.example.anuplal.movieapp.pojo.TheatreResult;
import com.example.anuplal.movieapp.pojo.review.ReviewResult;
import com.example.anuplal.movieapp.pojo.trailer.VideoResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {

    @GET("discover/movie")
    Call<TheatreResult> getAllMoviesList(@Query("api_key") String apiKey);

    @GET("movie/{movie_id}/reviews")
    Call<ReviewResult> getReviews(@Path("movie_id") int id, @Query("api_key") String apiKey);

    @GET("movie/{movie_id}/videos")
    Call<VideoResult> getVideos(@Path("movie_id") int id, @Query("api_key") String apiKey);
}
