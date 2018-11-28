package com.example.anuplal.movieapp.model.network;

import com.example.anuplal.movieapp.pojo.TheatreResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {

    @GET("movie")
    Call<TheatreResult> getAllMoviesList(@Query("api_key") String apiKey);
}
