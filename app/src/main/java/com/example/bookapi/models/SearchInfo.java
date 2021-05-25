
package com.example.bookapi.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SearchInfo {

    @SerializedName("textSnippet")
    @Expose
    public String textSnippet;

}
