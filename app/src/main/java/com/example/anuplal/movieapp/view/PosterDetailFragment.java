package com.example.anuplal.movieapp.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anuplal.movieapp.R;
import com.example.anuplal.movieapp.model.ModelManager;
import com.example.anuplal.movieapp.pojo.FavouriteMovie;
import com.example.anuplal.movieapp.pojo.Result;
import com.example.anuplal.movieapp.pojo.review.Review;
import com.example.anuplal.movieapp.pojo.trailer.Video;
import com.example.anuplal.movieapp.viewmodel.PosterDetailViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PosterDetailFragment extends Fragment implements View.OnClickListener {


    private TextView mMovieName;
    private TextView mOverView;
    private ImageView mPosterThumbNail;
    private TextView mRating;
    private TextView mReleaseDate;
    private TextView mLanguage;
    private TextView mReviewHeader;
    private TextView mTrailerHeader;
    private Button mAddToFavourite;
    private RecyclerView mReviewList;
    private Result result;

    private ReviewAdapter mAdapter;

    private MoviePosterFragment.OnFragmentTransaction mTransactionListener;
    private String key;

    public static PosterDetailFragment newInstance(Result result) {

        Bundle args = new Bundle();
        args.putParcelable("data", result);
        PosterDetailFragment fragment = new PosterDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof MoviePosterFragment.OnFragmentTransaction) {
            mTransactionListener = (MoviePosterFragment.OnFragmentTransaction) context;
        } else {

            throw new IllegalStateException("Activity needs to implement OnFragmentTransaction");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMovieName = view.findViewById(R.id.txv_movie_name);
        mOverView = view.findViewById(R.id.txv_overview);
        mPosterThumbNail = view.findViewById(R.id.img_poster);
        mRating = view.findViewById(R.id.txv_rating);
        mReleaseDate = view.findViewById(R.id.release_date);
        mLanguage = view.findViewById(R.id.language);
        mAddToFavourite = view.findViewById(R.id.btn_add_fav);
        mReviewList = view.findViewById(R.id.review_list);
        mReviewHeader = view.findViewById(R.id.txv_reviews);
        mTrailerHeader = view.findViewById(R.id.txv_trailer);
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(getContext());
        mReviewList.setLayoutManager(gridLayoutManager);
        mAddToFavourite.setOnClickListener(this);
        mTransactionListener.setToolbarBackEnabled(true);
        mTrailerHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + key)));
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Bundle arguments = getArguments();
        if (arguments != null) {
            result = arguments.getParcelable("data");
            String builder = "https://image.tmdb.org/t/p/w500/"
                    + result.getPosterPath();
            Picasso.get().load(builder).into(mPosterThumbNail);
            mMovieName.setText(result.getOriginalTitle());
            mOverView.setText(result.getOverview());
            mRating.setText(result.getVoteAverage() + " / 10");
            mReleaseDate.setText(result.getReleaseDate());
            mLanguage.setText(" |   " + result.getOriginalLanguage());
            PosterDetailViewModel viewModel = ViewModelProviders.of(this).get(PosterDetailViewModel.class);
            observeViewModel(viewModel, result.getId());
            checkDBIfAddedToFavourite();
        }


    }

    private void observeViewModel(PosterDetailViewModel viewModel, int id) {
        // Update the list when the data changes
        viewModel.getReviewList(id).observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                if (reviews != null) {
                    mAdapter = new ReviewAdapter(getContext(), reviews);
                    mReviewList.setAdapter(mAdapter);
                    mReviewHeader.setText("(" + reviews.size() + ") Reviews ");

                }
            }
        });

        viewModel.getVideoList(id).observe(this, new Observer<List<Video>>() {
            @Override
            public void onChanged(List<Video> videos) {

                if (videos != null && videos.size() > 0) {
                    //Taking first object to play video. Not showing list.
                    key = videos.get(0).getKey();
                    if (key != null) {
                        mTrailerHeader.setText(getString(R.string.watch_now));
                        mTrailerHeader.setEnabled(true);
                    } else {
                        mTrailerHeader.setText(getString(R.string.no_trailer));
                    }

                } else {
                    mTrailerHeader.setText(getString(R.string.no_trailer));
                }

            }
        });

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "Added to favourite", Toast.LENGTH_LONG).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                ModelManager.getInstance(getActivity().getApplication()).addToFavourite(new FavouriteMovie(result.getId(), result.getOriginalTitle()));
            }
        }).start();
        setButtonColor(true);

    }

    void checkDBIfAddedToFavourite() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final FavouriteMovie favouriteMovie =
                        ModelManager.getInstance(getActivity().getApplication()).getFavouriteMovie(result.getId());
                setButtonColor(favouriteMovie != null);
            }
        }).start();

    }

    private void setButtonColor(boolean isAddedToFavourite) {
        if (isAddedToFavourite) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAddToFavourite.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
                    mAddToFavourite.setText(getString(R.string.added_fav));
                    mAddToFavourite.setEnabled(false);
                }
            });
        }
    }

}
