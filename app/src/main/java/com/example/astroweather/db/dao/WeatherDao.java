package com.example.astroweather.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.astroweather.model.weather.Weather;

import io.reactivex.Single;

@Dao
public abstract class WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Long insert(Weather Weather);

    @Query("SELECT * FROM weather WHERE locationId=:locationId ORDER BY date DESC")
    public abstract Single<Weather> getWeatherByLocation(Long locationId);

    @Transaction
    @Query("DELETE FROM weather")
    public abstract int delete();
}
