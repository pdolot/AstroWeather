package com.example.astroweather.db.repository;

import android.util.Log;

import com.example.astroweather.db.WeatherDatabase;
import com.example.astroweather.db.dao.DailyWeatherDao;
import com.example.astroweather.db.dao.WeatherDao;
import com.example.astroweather.model.weather.WeatherResponse;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SharedDbRepository {
    private final String TAG = this.getClass().getSimpleName();
    private WeatherDatabase db;
    private WeatherDao weatherDao;
    private DailyWeatherDao dailyWeatherDao;

    public SharedDbRepository(WeatherDatabase db) {
        this.db = db;
        weatherDao = db.weatherDao();
        dailyWeatherDao = db.dailyWeatherDao();
    }

    public Completable insertWeather(WeatherResponse weatherResponse) {
        Completable deleteWeather = Completable.fromAction(() -> {
            Log.v(TAG, "Delete old weather");
            weatherDao.delete();
        });
        Completable insertWeather = Completable.fromAction(() -> {
            Log.v(TAG, "Insert new weather");
            weatherDao.insert(weatherResponse.getCurrent());
        });
        Completable deleteDailyWeather = Completable.fromAction(() -> {
            Log.v(TAG, "Delete old dailyWeather");
            dailyWeatherDao.delete();
        });
        Completable insertDailyWeather = Completable.fromAction(() -> {
            Log.v(TAG, "Insert new dailyWeather");
            dailyWeatherDao.insert(weatherResponse.getDaily());
        });
        return Completable.concatArray(deleteWeather, deleteDailyWeather, insertWeather, insertDailyWeather)
                .observeOn(Schedulers.single())
                .doOnSubscribe(s -> {
                    Log.v(TAG, "Begin transaction");
                    db.beginTransaction();
                })
                .doOnComplete(() -> {
                    Log.v(TAG, "Transaction end successful");
                    db.setTransactionSuccessful();
                })
                .doFinally(() -> {
                    Log.v(TAG, "End successful");
                    db.endTransaction();
                })
                .subscribeOn(Schedulers.single())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
