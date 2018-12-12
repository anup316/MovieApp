
package com.example.anuplal.movieapp.pojo.trailer;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class VideoResult {

    @SerializedName("id")
    private int id;
    @SerializedName("results")
    private List<Video> results = null;

    public int getId() {
        return id;
    }

    public List<Video> getVideos() {
        return results;
    }
}
