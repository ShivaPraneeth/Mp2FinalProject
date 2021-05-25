
package com.example.bookapi.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class PanelizationSummary {

    @SerializedName("containsEpubBubbles")
    @Expose
    public Boolean containsEpubBubbles;
    @SerializedName("containsImageBubbles")
    @Expose
    public Boolean containsImageBubbles;

}
