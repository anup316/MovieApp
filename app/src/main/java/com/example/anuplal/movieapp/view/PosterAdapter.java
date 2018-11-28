package com.example.anuplal.movieapp.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.anuplal.movieapp.R;
import com.example.anuplal.movieapp.pojo.Result;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.ViewHolder> {


    private final List<Result> mMoviePsoterList;
    private final LayoutInflater mInflater;

    PosterAdapter(Context context, List<Result> posterList) {
        this.mInflater = LayoutInflater.from(context);
        this.mMoviePsoterList = posterList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_photo, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        //https://image.tmdb.org/t/p/w500//uyJgTzAsp3Za2TaPiZt2yaKYRIR.jpg
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

    }


    @Override
    public int getItemCount() {
        return mMoviePsoterList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterView;

        ViewHolder(View itemView) {
            super(itemView);
            posterView = itemView.findViewById(R.id.posterView);
        }
    }

}
