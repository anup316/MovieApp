package com.example.anuplal.movieapp.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anuplal.movieapp.R;
import com.example.anuplal.movieapp.pojo.review.Review;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {


    private final List<Review> mReviewList;
    private final LayoutInflater mInflater;


    ReviewAdapter(Context context, List<Review> reviews) {
        this.mInflater = LayoutInflater.from(context);
        this.mReviewList = reviews;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.view_review, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Review review = mReviewList.get(position);
        holder.authorName.setText("Written By " + review.getAuthor());
        holder.reviewText.setText(review.getContent());
    }


    @Override
    public int getItemCount() {
        return mReviewList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView authorName;
        TextView reviewText;
        TextView date;

        ViewHolder(View itemView) {
            super(itemView);
            authorName = itemView.findViewById(R.id.txv_username);
            date = itemView.findViewById(R.id.txv_date);
            reviewText = itemView.findViewById(R.id.txv_review);

        }
    }
}
