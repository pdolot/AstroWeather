package com.example.astroweather.db.repository;

import com.example.astroweather.db.dao.DailyWeatherDao;
import com.example.astroweather.model.weather.DailyWeather;
import com.example.astroweather.model.weather.WeatherResponse;

import java.util.Collections;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DailyWeatherRepository {

    private DailyWeatherDao dailyWeatherDao;

    public DailyWeatherRepository(DailyWeatherDao dailyWeatherDao) {
        this.dailyWeatherDao = dailyWeatherDao;
    }

    public Single<List<DailyWeather>> getWeatherForNextSevenDays(Long locationId) {
        return dailyWeatherDao.getDailyWeatherByLocation(locationId)
                .map(list -> {
                    Collections.reverse(list);
                    return list;
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<WeatherResponse> deleteAndInsert(WeatherResponse weather) {
        return Completable.fromAction(dailyWeatherDao::delete)
                .andThen(Completable.fromAction(() -> dailyWeatherDao.insert(weather.getDaily())))
                .toSingle( () -> weather)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
