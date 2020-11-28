package com.example.astroweather.rest;

import com.example.astroweather.model.api.AstronomyData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestService {

    @GET("astronomy")
    Single<AstronomyData> getSunData(@Query("apiKey") String key, @Query("lat") String lat, @Query("long") String lng);
}
