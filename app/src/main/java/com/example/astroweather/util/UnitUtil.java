package com.example.astroweather.util;

import com.example.astroweather.data.LocalStorage;

import java.text.DecimalFormat;

public class UnitUtil {

    public UnitUtil(LocalStorage localStorage) {
        this.localStorage = localStorage;
    }

    private LocalStorage localStorage;

    private static DecimalFormat df2 = new DecimalFormat("#.##");

    public String getTemperature(Double temperature) {
        return df2.format(temperature) + " " + localStorage.getWeatherUnits().getTemperatureUnit();
    }

    public String getWindSpeed(Double windSpeed) {
        return df2.format(windSpeed) + " " + localStorage.getWeatherUnits().getWindSpeedUnit();
    }
}
