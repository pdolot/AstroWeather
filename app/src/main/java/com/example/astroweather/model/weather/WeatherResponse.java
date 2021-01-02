package com.example.astroweather.model.weather;

import java.util.List;

public class WeatherResponse {
    private Weather current;
    private List<DailyWeather> daily;

    public Weather getCurrent() {
        return current;
    }

    public void setCurrent(Weather current) {
        this.current = current;
    }

    public List<DailyWeather> getDaily() {
        return daily;
    }

    public void setDaily(List<DailyWeather> daily) {
        this.daily = daily;
    }
}
