package com.example.anuplal.movieapp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.anuplal.movieapp.Constants;
import com.example.anuplal.movieapp.R;
import com.example.anuplal.movieapp.pojo.FavouriteMovie;
import com.example.anuplal.movieapp.pojo.Result;
import com.example.anuplal.movieapp.viewmodel.MoviesViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MoviePosterFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private ProgressBar mProgressBar;
    private PosterAdapter mAdapter;
    private List<Result> results;
    private MoviesViewModel viewModel;


    private OnFragmentTransaction mTransactionListener;
    private boolean isMerged;


    public static MoviePosterFragment newInstance() {
        MoviePosterFragment fragment = new MoviePosterFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentTransaction) {
            mTransactionListener = (OnFragmentTransaction) context;
        } else {

            throw new IllegalStateException("Activity needs to implement OnFragmentTransaction");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_poster, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.posterListView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mProgressBar = view.findViewById(R.id.progress_bar);
        setHasOptionsMenu(true);
        setRetainInstance(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.it_hig_rated) {
            viewModel.sort(Constants.CATEGORY.HIGHTLYRATED);
        } else if (item.getItemId() == R.id.it_most_popular) {
            viewModel.sort(Constants.CATEGORY.MOSTPOPULAR);
        } else if (item.getItemId() == R.id.it_fav_movie) {
            viewModel.sort(Constants.CATEGORY.FAVOURITE);
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (viewModel == null)
            viewModel = ViewModelProviders.of(this).get(MoviesViewModel.class);

        observeViewModel();
    }

    private void observeViewModel() {
        // Update the list when the data changes
        viewModel.getTheatreObservable().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> moviewList) {
                if (moviewList != null) {
                    results = moviewList;
                    mProgressBar.setVisibility(View.GONE);
                    mAdapter = new PosterAdapter(getContext(), results, posterClickEvent);
                    mRecyclerView.setAdapter(mAdapter);
                    if (!isMerged)
                        observeDAO();
                }

            }
        });


    }

    void observeDAO() {
        viewModel.getAllFavouriteMovieObservable().observe(this, new Observer<List<FavouriteMovie>>() {
            @Override
            public void onChanged(List<FavouriteMovie> favouriteMovies) {
                viewModel.mergeList();
                isMerged = true;
            }
        });
    }

    private PosterAdapter.OnPosterItemClick posterClickEvent = new PosterAdapter.OnPosterItemClick() {
        @Override
        public void onClick(int pos) {
            mTransactionListener.onInteraction(results.get(pos));
        }
    };


    public interface OnFragmentTransaction {

        void onInteraction(Result result);

        void setToolbarBackEnabled(boolean isBack);
    }
}
