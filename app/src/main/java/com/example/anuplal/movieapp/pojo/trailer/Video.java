
package com.example.anuplal.movieapp.pojo.trailer;

import com.google.gson.annotations.SerializedName;

public class Video {

    @SerializedName("id")
    private String id;
    @SerializedName("key")
    private String key;
    @SerializedName("type")
    private String type;

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }
}
