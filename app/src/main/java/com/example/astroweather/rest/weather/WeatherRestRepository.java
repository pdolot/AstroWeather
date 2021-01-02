package com.example.astroweather.rest.weather;

import com.example.astroweather.constants.ApiConstants;
import com.example.astroweather.data.LocalStorage;
import com.example.astroweather.di.Injector;
import com.example.astroweather.model.weather.DailyWeather;
import com.example.astroweather.model.weather.Location;
import com.example.astroweather.model.weather.WeatherResponse;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherRestRepository {
    @Inject
    LocalStorage localStorage;

    private WeatherRestService weatherRestService;

    public WeatherRestRepository(WeatherRestService weatherRestService) {
        this.weatherRestService = weatherRestService;
        Injector.appComponent.inject(this);
    }

    public Single<Location> checkIfLocationExist(String city) {
        return weatherRestService.checkIfLocationExist(city, ApiConstants.WEATHER_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(location -> {
                    location.setCity(city);
                    location.setLatitude(location.getCoordinates().getLatitude());
                    location.setLongitude(location.getCoordinates().getLongitude());
                    return location;
                });
    }

    public Single<WeatherResponse> getCurrentWeather(String city) {
        Long locationId = localStorage.getCurrentLocation();
        String lat = String.valueOf(localStorage.getCurrentLatitude());
        String lon = String.valueOf(localStorage.getCurrentLongitude());
        String units = localStorage.getWeatherUnits().name();
        return weatherRestService.getCurrentWeather(lat, lon, ApiConstants.EXCLUDE, units, ApiConstants.LANG, ApiConstants.WEATHER_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(response -> {
                    response.getCurrent().setLocationId(locationId);
                    response.getCurrent().setIcon(response.getCurrent().getWeather().get(0).getIcon());
                    response.getCurrent().setDescription(response.getCurrent().getWeather().get(0).getDescription());
                    response.getCurrent().setCity(city);
                    response.getCurrent().setDate(response.getCurrent().getDate() * 1000);
                    response.getCurrent().setSunrise(response.getCurrent().getSunrise() * 1000);
                    response.getCurrent().setSunset(response.getCurrent().getSunset() * 1000);

                    for (DailyWeather dailyWeather : response.getDaily()) {

                        dailyWeather.setLocationId(locationId);
                        dailyWeather.setCity(city);
                        dailyWeather.setIcon(dailyWeather.getWeather().get(0).getIcon());
                        dailyWeather.setDescription(dailyWeather.getWeather().get(0).getDescription());

                        dailyWeather.setTemp_day(dailyWeather.getTemp().getDay());
                        dailyWeather.setTemp_max(dailyWeather.getTemp().getMax());
                        dailyWeather.setTemp_min(dailyWeather.getTemp().getMin());
                        dailyWeather.setTemp_night(dailyWeather.getTemp().getNight());
                        dailyWeather.setTemp_eve(dailyWeather.getTemp().getEve());
                        dailyWeather.setTemp_morn(dailyWeather.getTemp().getMorn());

                        dailyWeather.setFeelsTemp_day(dailyWeather.getFeelsTemp().getDay());
                        dailyWeather.setFeelsTemp_night(dailyWeather.getFeelsTemp().getNight());
                        dailyWeather.setFeelsTemp_eve(dailyWeather.getFeelsTemp().getEve());
                        dailyWeather.setFeelsTemp_morn(dailyWeather.getFeelsTemp().getMorn());

                        dailyWeather.setDate(dailyWeather.getDate() * 1000);
                        dailyWeather.setSunrise(dailyWeather.getSunrise() * 1000);
                        dailyWeather.setSunset(dailyWeather.getSunset() * 1000);
                    }
                    return response;
                });
    }
}
