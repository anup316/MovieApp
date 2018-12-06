
package com.example.anuplal.movieapp.pojo.review;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReviewResult {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("results")
    @Expose
    public List<Review> reviews = null;
    @SerializedName("total_pages")
    @Expose
    public Integer totalPages;
    @SerializedName("total_results")
    @Expose
    public Integer totalResults;

    public List<Review> getReviews() {
        return reviews;
    }

}
