package com.example.astroweather.util;

import com.example.astroweather.R;
import com.example.astroweather.model.WeatherData;
import com.example.astroweather.model.weather.DailyWeather;
import com.example.astroweather.model.weather.Weather;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataUtil {

    public WeatherDataUtil(UnitUtil unitUtil) {
        this.unitUtil = unitUtil;
    }

    private final UnitUtil unitUtil;

    public static Integer sunriseIcon = R.drawable.ic_sunrise;
    public static String sunriseLabel = "Godzina wschodu słońca";
    public static Integer sunsetIcon = R.drawable.ic_sunset;
    public static String sunsetLabel = "Godzina zachodu słońca";
    public static Integer tempIcon = R.drawable.ic_temperature;
    public static Integer feelTempIcon = R.drawable.ic_feel_temperature;
    public static String feelTempLabel = "Odczuwalna temperatura";
    public static String feelTempMorningLabel = "Odczuwalna temp. - rano";
    public static String feelTempDayLabel = "Odczuwalna temp. - dzień";
    public static String feelTempEveningLabel = "Odczuwalna temp. - wieczór";
    public static String feelTempNightLabel = "Odczuwalna temp. - noc";
    public static String tempMorningLabel = "Temperatura - rano";
    public static String tempDayLabel = "Temperatura - dzień";
    public static String tempEveningLabel = "Temperatura - wieczór";
    public static String tempNightLabel = "Temperatura - noc";
    public static Integer pressureIcon = R.drawable.ic_pressure;
    public static String pressureLabel = "Ciśnienie";
    public static Integer humidityIcon = R.drawable.ic_humidity;
    public static String humidityLabel = "Wilgotność";
    public static Integer cloudIcon = R.drawable.ic_clouds;
    public static String cloudLabel = "Zachmurzenie";
    public static Integer visibilityIcon = R.drawable.ic_visibility;
    public static String visibilityLabel = "Widoczność";
    public static Integer windSpeedIcon = R.drawable.ic_wind;
    public static String windSpeedLabel = "Prędkość wiatru";
    public static Integer windDegreesIcon = R.drawable.ic_wind_degree;
    public static String windDegreesLabel = "Kierunek wiatru";

    public List<WeatherData> getWeatherData(Weather weather) {
        ArrayList<WeatherData> weatherData = new ArrayList<>();
        weatherData.add(new WeatherData(sunriseIcon, sunriseLabel, TimeUtil.getTime(weather.getSunrise())));
        weatherData.add(new WeatherData(sunsetIcon, sunsetLabel, TimeUtil.getTime(weather.getSunset())));
        weatherData.add(new WeatherData(feelTempIcon, feelTempLabel, unitUtil.getTemperature(weather.getFeelsTemp())));
        weatherData.add(new WeatherData(pressureIcon, pressureLabel, getPressure(weather.getPressure())));
        weatherData.add(new WeatherData(humidityIcon, humidityLabel, getPercentValue(weather.getHumidity())));
        weatherData.add(new WeatherData(cloudIcon, cloudLabel, getPercentValue(weather.getClouds())));
        weatherData.add(new WeatherData(visibilityIcon, visibilityLabel, getMeterValue(weather.getVisibility())));
        weatherData.add(new WeatherData(windSpeedIcon, windSpeedLabel, unitUtil.getWindSpeed(weather.getWindSpeed())));
        weatherData.add(new WeatherData(windDegreesIcon, windDegreesLabel, getDegreeValue(weather.getWindDeg())));
        return weatherData;
    }

    public List<WeatherData> getWeatherData(DailyWeather weather) {
        ArrayList<WeatherData> weatherData = new ArrayList<>();
        weatherData.add(new WeatherData(tempIcon, tempMorningLabel, unitUtil.getTemperature(weather.getTemp_morn())));
        weatherData.add(new WeatherData(null, tempDayLabel, unitUtil.getTemperature(weather.getTemp_day())));
        weatherData.add(new WeatherData(null, tempEveningLabel, unitUtil.getTemperature(weather.getTemp_eve())));
        weatherData.add(new WeatherData(null, tempNightLabel, unitUtil.getTemperature(weather.getTemp_night())));
        weatherData.add(new WeatherData(feelTempIcon, feelTempMorningLabel, unitUtil.getTemperature(weather.getFeelsTemp_morn())));
        weatherData.add(new WeatherData(null, feelTempDayLabel, unitUtil.getTemperature(weather.getFeelsTemp_day())));
        weatherData.add(new WeatherData(null, feelTempEveningLabel, unitUtil.getTemperature(weather.getFeelsTemp_eve())));
        weatherData.add(new WeatherData(null, feelTempNightLabel, unitUtil.getTemperature(weather.getFeelsTemp_night())));
        weatherData.add(new WeatherData(sunriseIcon, sunriseLabel, TimeUtil.getTime(weather.getSunrise())));
        weatherData.add(new WeatherData(sunsetIcon, sunsetLabel, TimeUtil.getTime(weather.getSunset())));
        weatherData.add(new WeatherData(pressureIcon, pressureLabel, getPressure(weather.getPressure())));
        weatherData.add(new WeatherData(humidityIcon, humidityLabel, getPercentValue(weather.getHumidity())));
        weatherData.add(new WeatherData(cloudIcon, cloudLabel, getPercentValue(weather.getClouds())));
        weatherData.add(new WeatherData(windSpeedIcon, windSpeedLabel, unitUtil.getWindSpeed(weather.getWindSpeed())));
        weatherData.add(new WeatherData(windDegreesIcon, windDegreesLabel, getDegreeValue(weather.getWindDeg())));
        return weatherData;
    }

    private static String getPressure(Integer pressure){
        return pressure + " hPa";
    }

    private static String getPercentValue(Number value){
        return value + " %";
    }

    private static String getMeterValue(Number value){
        return value + "m";
    }

    private static String getDegreeValue(Number value){
        return value + " °";
    }
}
