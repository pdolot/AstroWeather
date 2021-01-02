package com.example.astroweather.di.module;

import androidx.room.Room;

import com.example.astroweather.App;
import com.example.astroweather.db.WeatherDatabase;
import com.example.astroweather.db.dao.DailyWeatherDao;
import com.example.astroweather.db.dao.LocationDao;
import com.example.astroweather.db.dao.WeatherDao;
import com.example.astroweather.db.repository.DailyWeatherRepository;
import com.example.astroweather.db.repository.LocationRepository;
import com.example.astroweather.db.repository.SharedDbRepository;
import com.example.astroweather.db.repository.WeatherRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DbModule {

    private App app;

    public DbModule(App app) {
        this.app = app;
    }

    @Singleton
    @Provides
    public WeatherDatabase provideDatabase() {
        return Room.databaseBuilder(app.getApplicationContext(), WeatherDatabase.class, "weather_database")
                .fallbackToDestructiveMigration()
                .build();
    }

    @Singleton
    @Provides
    public LocationDao provideLocationDao(WeatherDatabase db) {
        return db.locationDao();
    }

    @Singleton
    @Provides
    public WeatherDao provideWeatherDao(WeatherDatabase db) {
        return db.weatherDao();
    }

    @Singleton
    @Provides
    public DailyWeatherDao provideDailyWeatherDao(WeatherDatabase db) {
        return db.dailyWeatherDao();
    }


    @Singleton
    @Provides
    public LocationRepository provideLocationRepository(LocationDao dao) {
        return new LocationRepository(dao);
    }

    @Singleton
    @Provides
    public WeatherRepository provideWeatherRepository(WeatherDao dao) {
        return new WeatherRepository(dao);
    }

    @Singleton
    @Provides
    public DailyWeatherRepository provideDailyWeatherRepository(DailyWeatherDao dao) {
        return new DailyWeatherRepository(dao);
    }

    @Singleton
    @Provides
    public SharedDbRepository provideSharedDbRepository(WeatherDatabase db) {
        return new SharedDbRepository(db);
    }
}
