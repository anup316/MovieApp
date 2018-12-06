package com.example.anuplal.movieapp;

import android.app.Application;

import com.example.anuplal.movieapp.model.ModelManager;

public class MovieApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ModelManager.getInstance(this);
    }
}
