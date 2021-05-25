package com.example.bookapi.models;

import com.google.gson.annotations.SerializedName;

public class Items {

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }

    @SerializedName("volumeInfo")
    VolumeInfo volumeInfo;




}
