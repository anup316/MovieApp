package com.example.anuplal.movieapp.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.anuplal.movieapp.R;
import com.example.anuplal.movieapp.pojo.Result;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.ViewHolder> {


    private final List<Result> mMoviePsoterList;
    private final LayoutInflater mInflater;
    private final OnPosterItemClick mListener;

    PosterAdapter(Context context, List<Result> posterList, OnPosterItemClick listener) {
        this.mInflater = LayoutInflater.from(context);
        this.mMoviePsoterList = posterList;
        this.mListener = listener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Result poster = mMoviePsoterList.get(position);
        String builder = "https://image.tmdb.org/t/p/w500/"
                + poster.getPosterPath();

        Picasso.get().load(builder).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                holder.posterView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                Log.e("Picasso", "Error");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

        holder.ratingBar.setText(String.valueOf(poster.getVoteAverage()));
        holder.popularity.setText(String.valueOf(poster.getPopularity()));
        holder.posterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClick(position);
            }
        });


    }


    @Override
    public int getItemCount() {
        return mMoviePsoterList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterView;
        TextView ratingBar;
        TextView popularity;

        ViewHolder(View itemView) {
            super(itemView);
            posterView = itemView.findViewById(R.id.posterView);
            ratingBar = itemView.findViewById(R.id.movie_rating);
            popularity = itemView.findViewById(R.id.txv_popularity);

        }
    }

    public interface OnPosterItemClick {

        void onClick(int pos);
    }

}
