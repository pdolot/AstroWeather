package com.example.astroweather.view.base;

import com.example.astroweather.view.fragment.home.PageType;
import com.example.astroweather.view.fragment.home.ViewSwapperAdapter;

abstract public class BasePage {
    abstract public PageType getPageType();
    abstract public void onBindView(ViewSwapperAdapter.ViewHolder viewHolder, int position);
}
