package com.example.anuplal.movieapp.model.network;


import com.example.anuplal.movieapp.pojo.Result;
import com.example.anuplal.movieapp.pojo.TheatreResult;
import com.example.anuplal.movieapp.pojo.review.Review;
import com.example.anuplal.movieapp.pojo.review.ReviewResult;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceProvider {

    private static ApiServiceProvider INSTANCE;
    private final MovieService movieService;


    private ApiServiceProvider() {
        movieService = getClient().create(MovieService.class);

    }

    public static synchronized ApiServiceProvider getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ApiServiceProvider();
        }
        return INSTANCE;
    }


    private Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        return retrofit;
    }

    public MutableLiveData<List<Result>> fetMoviesList(String apiKey) {
        final MutableLiveData<List<Result>> data = new MutableLiveData<>();

        movieService.getAllMoviesList(apiKey).enqueue(new Callback<TheatreResult>() {
            @Override
            public void onResponse(Call<TheatreResult> call, Response<TheatreResult> response) {
                data.setValue(response.body().getMovieList());
            }

            @Override
            public void onFailure(Call<TheatreResult> call, Throwable t) {

            }
        });
        return data;

    }


    public MutableLiveData<List<Review>> fetchReviewList(int movieId, String apiKey) {
        final MutableLiveData<List<Review>> data = new MutableLiveData<>();

        movieService.getReviews(movieId, apiKey).enqueue(new Callback<ReviewResult>() {
            @Override
            public void onResponse(Call<ReviewResult> call, Response<ReviewResult> response) {
                data.setValue(response.body().getReviews());
            }

            @Override
            public void onFailure(Call<ReviewResult> call, Throwable t) {

            }
        });
        return data;


    }
}
