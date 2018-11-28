package com.example.anuplal.movieapp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anuplal.movieapp.R;
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

    RecyclerView mRecyclerView;
    private PosterAdapter mAdapter;
    private List<Result> results;

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
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final MoviesViewModel viewModel =
                ViewModelProviders.of(this).get(MoviesViewModel.class);

        observeViewModel(viewModel);
    }

    private void observeViewModel(MoviesViewModel viewModel) {
        // Update the list when the data changes
        viewModel.getTheatreObservable().observe(this, new Observer<List<Result>>() {
            @Override
            public void onChanged(@Nullable List<Result> moviewList) {
                if (moviewList != null) {
                    results = moviewList;
                    if (mAdapter == null) {
                        mAdapter = new PosterAdapter(getContext(), results);
                        mRecyclerView.setAdapter(mAdapter);
                    } else {
                        results.addAll(moviewList);
                        mAdapter.notifyDataSetChanged();
                    }

                }
            }
        });
    }
}
