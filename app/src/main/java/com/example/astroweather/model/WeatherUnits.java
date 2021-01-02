package com.example.astroweather.model;

public enum WeatherUnits {

    STANDARD("K", "m/s"),
    METRIC("°C", "m/s"),
    IMPERIAL("°F", "mile/s");

    private final String temperatureUnit;
    private final String windSpeedUnit;

    WeatherUnits(String temperatureUnit, String windSpeedUnit) {
        this.temperatureUnit = temperatureUnit;
        this.windSpeedUnit = windSpeedUnit;
    }

    public String getTemperatureUnit() {
        return temperatureUnit;
    }

    public String getWindSpeedUnit() {
        return windSpeedUnit;
    }
}
