package com.example.astroweather.constants;

public class ApiConstants {
    public static String ASTRONOMY_API_KEY = "5563c9d28a9241b7ac90a41ae11de174";
    public static String ASTRONOMY_BASE_URL = "https://api.ipgeolocation.io/";

    public static String WEATHER_API_KEY = "3bf993b129060a4cb85b2d8ab6020708";
    public static String WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/";
    public static String LANG = "pl";
    public static String EXCLUDE = "hourly,minutely";

    public static String getIconUrl(String icon){
        return "https://openweathermap.org/img/wn/" + icon + "@2x.png";
    }
}
