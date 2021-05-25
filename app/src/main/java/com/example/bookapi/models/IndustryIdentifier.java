
package com.example.bookapi.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class IndustryIdentifier {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("identifier")
    @Expose
    public String identifier;

}
