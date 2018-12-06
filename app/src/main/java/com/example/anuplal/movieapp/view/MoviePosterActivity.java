package com.example.anuplal.movieapp.view;

import android.os.Bundle;
import android.util.Log;

import com.example.anuplal.movieapp.R;
import com.example.anuplal.movieapp.model.ModelManager;
import com.example.anuplal.movieapp.pojo.FavouriteMovie;
import com.example.anuplal.movieapp.pojo.Result;

import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;

public class MoviePosterActivity extends AppCompatActivity implements MoviePosterFragment.OnFragmentTransaction {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_poster);

        replaceFragment(MoviePosterFragment.newInstance());

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<FavouriteMovie> data = ModelManager.getInstance(getApplication()).getAllMovies().getValue();
                if (data != null) {
                    Log.v("Database", String.valueOf(data.size()));
                }


            }
        }).start();

    }

    @Override
    public void onInteraction(Result result) {
        replaceFragment(PosterDetailFragment.newInstance(result));
    }

    @Override
    public void setToolbarBackEnabled(boolean isBack) {
        final ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null){
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
