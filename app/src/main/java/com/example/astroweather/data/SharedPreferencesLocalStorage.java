package com.example.astroweather.data;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

import com.example.astroweather.model.WeatherUnits;

public class SharedPreferencesLocalStorage implements LocalStorage {

    public static final String PREFS_NAME = "astronomyAppSharedPreferences";
    public static final String CURRENT_LOCATION = "currentLocation";
    public static final String CURRENT_LATITUDE = "currentLatitude";
    public static final String CURRENT_LONGITUDE = "currentLongitude";
    public static final String CURRENT_SYNC_INTERVAL = "currentSyncInterval";
    public static final String CURRENT_UNITS = "currentUnits";
    public static final String CONFIGURATION_CHANGED = "configurationChanged";

    private Context context;
    private SharedPreferences sharedPreferences;
    public LiveSharedPreferences liveSharedPreferences;

    public SharedPreferencesLocalStorage(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        liveSharedPreferences = new LiveSharedPreferences(sharedPreferences);
    }

    @Nullable
    @Override
    public Long getCurrentLocation() {
        return sharedPreferences.getLong(CURRENT_LOCATION, -1L);
    }

    @Override
    public void setCurrentLocation(Long id) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(CURRENT_LOCATION, id);
        editor.putBoolean(CONFIGURATION_CHANGED, true);
        editor.apply();
    }

    @Nullable
    @Override
    public Float getCurrentLatitude() {
        return sharedPreferences.getFloat(CURRENT_LATITUDE, 51.77407962293879f);
    }

    @Override
    public void setCurrentLatitude(Float latitude) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(CURRENT_LATITUDE, latitude);
        editor.putBoolean(CONFIGURATION_CHANGED, true);
        editor.apply();
    }

    @Nullable
    @Override
    public Float getCurrentLongitude() {
        return sharedPreferences.getFloat(CURRENT_LONGITUDE, 19.43562383830039f);
    }

    @Override
    public void setCurrentLongitude(Float longitude) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(CURRENT_LONGITUDE, longitude);
        editor.putBoolean(CONFIGURATION_CHANGED, true);
        editor.apply();
    }

    @Nullable
    @Override
    public Integer getSyncInterval() {
        return sharedPreferences.getInt(CURRENT_SYNC_INTERVAL, 5);
    }

    @Override
    public void setSyncInterval(Integer syncInterval) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(CURRENT_SYNC_INTERVAL, syncInterval);
        editor.putBoolean(CONFIGURATION_CHANGED, true);
        editor.apply();
    }

    @Nullable
    @Override
    public WeatherUnits getWeatherUnits() {
        return WeatherUnits.valueOf(sharedPreferences.getString(CURRENT_UNITS, WeatherUnits.METRIC.name()));
    }

    @Override
    public void setWeatherUnits(String units) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(CURRENT_UNITS, units);
        editor.putBoolean(CONFIGURATION_CHANGED, true);
        editor.apply();
    }

    @Override
    public LivePreference<Boolean> configurationChanged() {
        return liveSharedPreferences.getBoolean(CONFIGURATION_CHANGED, false);
    }
}
