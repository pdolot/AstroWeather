package com.example.astroweather.view;

import com.example.astroweather.R;

public enum PageType {
    SUN_PAGE(R.layout.page_sun),
    MOON_PAGE(R.layout.page_moon),
    MENU_PAGE(R.layout.page_menu),
    LOCATION_PAGE(R.layout.page_location),
    CURRENT_WEATHER_PAGE(R.layout.page_current_weather),
    DAILY_WEATHER_PAGE(R.layout.page_daily_weather);

    private int layoutResourceId;

    PageType(int layoutResourceId) {
        this.layoutResourceId = layoutResourceId;
    }

    public int getLayoutResourceId() {
        return layoutResourceId;
    }
}
