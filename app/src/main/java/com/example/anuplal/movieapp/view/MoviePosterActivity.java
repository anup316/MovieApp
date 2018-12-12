package com.example.anuplal.movieapp.view;

import android.os.Bundle;

import com.example.anuplal.movieapp.R;
import com.example.anuplal.movieapp.pojo.Result;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MoviePosterActivity extends AppCompatActivity implements MoviePosterFragment.OnFragmentTransaction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_poster);

        if (savedInstanceState == null)
            replaceFragment(MoviePosterFragment.newInstance());

    }

    @Override
    public void onInteraction(Result result) {
        replaceFragment(PosterDetailFragment.newInstance(result));
    }

    @Override
    public void setToolbarBackEnabled(boolean isBack) {
        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setHomeButtonEnabled(isBack);
            supportActionBar.setDisplayHomeAsUpEnabled(isBack);
        }

    }

    void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        setToolbarBackEnabled(false);
    }
}
