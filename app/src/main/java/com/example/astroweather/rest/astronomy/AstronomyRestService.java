package com.example.astroweather.rest.astronomy;

import com.example.astroweather.model.astronomy.AstronomyData;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AstronomyRestService {

    @GET("astronomy")
    Single<AstronomyData> getAstronomyData(@Query("apiKey") String key, @Query("lat") String lat, @Query("long") String lng);
}
