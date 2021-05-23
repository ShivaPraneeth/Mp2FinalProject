
package com.example.bookapi.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Book {

    @SerializedName("updated")
    @Expose
    public Long updated;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("countryInfo")
    @Expose
    public CountryInfo countryInfo;
    @SerializedName("cases")
    @Expose
    public Integer cases;
    @SerializedName("todayCases")
    @Expose
    public Integer todayCases;
    @SerializedName("deaths")
    @Expose
    public Integer deaths;
    @SerializedName("todayDeaths")
    @Expose
    public Integer todayDeaths;
    @SerializedName("recovered")
    @Expose
    public Integer recovered;
    @SerializedName("todayRecovered")
    @Expose
    public Integer todayRecovered;
    @SerializedName("active")
    @Expose
    public Integer active;
    @SerializedName("critical")
    @Expose
    public Integer critical;
    @SerializedName("casesPerOneMillion")
    @Expose
    public Integer casesPerOneMillion;
    @SerializedName("deathsPerOneMillion")
    @Expose
    public Integer deathsPerOneMillion;
    @SerializedName("tests")
    @Expose
    public Integer tests;
    @SerializedName("testsPerOneMillion")
    @Expose
    public Integer testsPerOneMillion;
    @SerializedName("population")
    @Expose
    public Integer population;
    @SerializedName("continent")
    @Expose
    public String continent;
    @SerializedName("oneCasePerPeople")
    @Expose
    public Integer oneCasePerPeople;
    @SerializedName("oneDeathPerPeople")
    @Expose
    public Integer oneDeathPerPeople;
    @SerializedName("oneTestPerPeople")
    @Expose
    public Integer oneTestPerPeople;
    @SerializedName("undefined")
    @Expose
    public Integer undefined;
    @SerializedName("activePerOneMillion")
    @Expose
    public Double activePerOneMillion;
    @SerializedName("recoveredPerOneMillion")
    @Expose
    public Double recoveredPerOneMillion;
    @SerializedName("criticalPerOneMillion")
    @Expose
    public Double criticalPerOneMillion;

}
