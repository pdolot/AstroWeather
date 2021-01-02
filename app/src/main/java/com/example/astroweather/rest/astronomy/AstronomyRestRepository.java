package com.example.astroweather.rest.astronomy;

import com.example.astroweather.constants.ApiConstants;
import com.example.astroweather.model.astronomy.AstronomyData;

import io.reactivex.Single;

public class AstronomyRestRepository {

    private AstronomyRestService astronomyRestService;

    public AstronomyRestRepository(AstronomyRestService astronomyRestService) {
        this.astronomyRestService = astronomyRestService;
    }

    public Single<AstronomyData> getAstronomyData(String lat, String lng) {
        return astronomyRestService.getAstronomyData(ApiConstants.ASTRONOMY_API_KEY, lat, lng);
    }
}
