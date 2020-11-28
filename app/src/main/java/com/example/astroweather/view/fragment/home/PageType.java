package com.example.astroweather.view.fragment.home;

import com.example.astroweather.R;

public enum PageType {
    SUN_PAGE(R.layout.page_sun),
    MOON_PAGE(R.layout.page_moon);

    private int layoutResourceId;

    PageType(int layoutResourceId) {
        this.layoutResourceId = layoutResourceId;
    }

    public int getLayoutResourceId() {
        return layoutResourceId;
    }
}
