package com.example.anuplal.movieapp.view;

import android.os.Bundle;

import com.example.anuplal.movieapp.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;

public class MoviePosterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_poster);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        MoviePosterFragment fragment = new MoviePosterFragment();
        ft.replace(R.id.container, fragment);
        ft.commit();
    }
}
