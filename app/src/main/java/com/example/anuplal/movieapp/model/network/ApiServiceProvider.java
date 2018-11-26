package com.example.anuplal.movieapp.model.network;

import com.example.anuplal.movieapp.pojo.TheatreResult;

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
                .baseUrl("https://api.themoviedb.org/3/discover")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();


        return retrofit;
    }

    public void fetMoviesList(String apiKey) {

        Call<TheatreResult> allMoviesList = movieService.getAllMoviesList(apiKey);
        allMoviesList.enqueue(new Callback<TheatreResult>() {
            @Override
            public void onResponse(Call<TheatreResult> call, Response<TheatreResult> response) {

            }

            @Override
            public void onFailure(Call<TheatreResult> call, Throwable t) {

            }
        });


    }
}
