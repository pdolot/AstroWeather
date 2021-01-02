package com.example.astroweather.rest.weather;

import com.example.astroweather.model.weather.Location;
import com.example.astroweather.model.weather.WeatherResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherRestService {

    @GET("weather")
    Single<Location> checkIfLocationExist(@Query("q") String city, @Query("appid") String apiKey);

    @GET("onecall")
    Single<WeatherResponse> getCurrentWeather(@Query("lat") String lat, @Query("lon") String lon,
                                              @Query("exclude") String exclude, @Query("units") String units,
                                              @Query("lang") String lang, @Query("appid") String apiKey);

}
