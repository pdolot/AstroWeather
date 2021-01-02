package com.example.astroweather.db.repository;

import com.example.astroweather.db.WeatherDatabase;
import com.example.astroweather.db.dao.WeatherDao;
import com.example.astroweather.di.Injector;
import com.example.astroweather.model.weather.Weather;
import com.example.astroweather.model.weather.WeatherResponse;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WeatherRepository {

    private WeatherDao weatherDao;

    public WeatherRepository(WeatherDao weatherDao) {
        this.weatherDao = weatherDao;
    }


    public Single<Weather> getCurrentWeather(Long locationId) {
        return weatherDao.getWeatherByLocation(locationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<WeatherResponse> deleteAndInsert(WeatherResponse weather) {
        return Completable.fromAction(weatherDao::delete)
                .andThen(Completable.fromAction(() -> weatherDao.insert(weather.getCurrent())))
                .toSingle( () -> weather)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
