package com.example.bookapi.utils;

import com.example.bookapi.models.Data;
import com.example.bookapi.models.ResponseModal;


import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {


    @GET("volumes?q=search+terms")
    Call<ResponseModal> getAllData();
}
