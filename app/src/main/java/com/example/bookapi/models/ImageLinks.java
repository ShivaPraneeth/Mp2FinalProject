
package com.example.bookapi.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ImageLinks {


    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public void setSmallThumbnail(String smallThumbnail) {
        this.smallThumbnail = smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @SerializedName("smallThumbnail")
    @Expose
    public String smallThumbnail;

    @SerializedName("thumbnail")
    @Expose
    public String thumbnail;

}
