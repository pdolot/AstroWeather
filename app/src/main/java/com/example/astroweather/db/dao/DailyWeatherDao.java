package com.example.astroweather.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import com.example.astroweather.model.weather.DailyWeather;

import java.util.List;

import io.reactivex.Single;

@Dao
public abstract class DailyWeatherDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract List<Long> insert(List<DailyWeather> dailyWeather);

    @Query("SELECT DISTINCT * FROM daily_weather WHERE locationId=:locationId ORDER BY date DESC LIMIT 7")
    public abstract Single<List<DailyWeather>> getDailyWeatherByLocation(Long locationId);

    @Transaction
    @Query("DELETE FROM daily_weather")
    public abstract int delete();
}
