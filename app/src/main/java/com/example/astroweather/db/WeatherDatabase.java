package com.example.astroweather.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.astroweather.db.dao.DailyWeatherDao;
import com.example.astroweather.db.dao.LocationDao;
import com.example.astroweather.db.dao.WeatherDao;
import com.example.astroweather.model.weather.DailyWeather;
import com.example.astroweather.model.weather.Location;
import com.example.astroweather.model.weather.Weather;

@Database(entities = {Location.class, DailyWeather.class, Weather.class}, version = 3, exportSchema = false)
public abstract class WeatherDatabase extends RoomDatabase {

    public abstract DailyWeatherDao dailyWeatherDao();

    public abstract LocationDao locationDao();

    public abstract WeatherDao weatherDao();
}
