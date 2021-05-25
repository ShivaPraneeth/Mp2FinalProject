
package com.example.bookapi.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class SaleInfo {

    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("saleability")
    @Expose
    public String saleability;
    @SerializedName("isEbook")
    @Expose
    public Boolean isEbook;

}
