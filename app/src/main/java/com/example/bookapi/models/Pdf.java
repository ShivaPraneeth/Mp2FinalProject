
package com.example.bookapi.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Pdf {

    @SerializedName("isAvailable")
    @Expose
    public Boolean isAvailable;
    @SerializedName("acsTokenLink")
    @Expose
    public String acsTokenLink;

}
