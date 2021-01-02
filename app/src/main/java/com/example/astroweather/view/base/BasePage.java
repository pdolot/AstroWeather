package com.example.astroweather.view.base;

import com.example.astroweather.view.base.adapter.PagerAdapter;
import com.example.astroweather.view.PageType;

abstract public class BasePage {
    abstract public String getTitle();
    abstract public PageType getPageType();
    abstract public void onBindView(PagerAdapter.ViewHolder viewHolder, int position);
}
