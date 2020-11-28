package com.example.astroweather.rest;

import com.example.astroweather.model.api.AstronomyData;

import io.reactivex.Single;

public class RestRepository {

    private RestService restService;

    public RestRepository(RestService restService) {
        this.restService = restService;
    }

    public Single<AstronomyData> getSunData(String key, String lat, String lng) {
        return restService.getSunData(key, lat, lng);
    }
}
