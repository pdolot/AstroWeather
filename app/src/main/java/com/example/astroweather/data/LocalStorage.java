package com.example.astroweather.data;

import androidx.annotation.Nullable;

import com.example.astroweather.model.WeatherUnits;

public interface LocalStorage {
    @Nullable
    Long getCurrentLocation();
    void setCurrentLocation(Long id);

    @Nullable
    Float getCurrentLatitude();
    void setCurrentLatitude(Float latitude);

    @Nullable
    Float getCurrentLongitude();
    void setCurrentLongitude(Float longitude);

    @Nullable
    Integer getSyncInterval();
    void setSyncInterval(Integer syncInterval);

    @Nullable
    WeatherUnits getWeatherUnits();
    void setWeatherUnits(String units);

    LivePreference<Boolean> configurationChanged();
}
