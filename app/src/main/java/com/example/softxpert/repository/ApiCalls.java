package com.example.softxpert.repository;

import com.example.softxpert.model.NetworkCallResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCalls {
    @GET("/api/v1/cars")
    Call<NetworkCallResponse> getCars(@Query("page") String pageNumber);
}
