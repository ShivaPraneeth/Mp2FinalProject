package com.example.bookapi.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModal {


    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    @SerializedName("items")
    List<Items> items;


}
