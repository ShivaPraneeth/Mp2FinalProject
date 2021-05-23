
package com.example.bookapi.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryInfo {

    @SerializedName("_id")
    @Expose
    public Integer id;
    @SerializedName("iso2")
    @Expose
    public String iso2;
    @SerializedName("iso3")
    @Expose
    public String iso3;
    @SerializedName("lat")
    @Expose
    public Integer lat;
    @SerializedName("long")
    @Expose
    public Integer _long;
    @SerializedName("flag")
    @Expose
    public String flag;

}
