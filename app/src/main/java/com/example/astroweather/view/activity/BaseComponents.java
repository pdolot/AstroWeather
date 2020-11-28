package com.example.astroweather.view.activity;

import androidx.drawerlayout.widget.DrawerLayout;

import com.example.astroweather.view.component.Navigation;
import com.example.astroweather.view.component.TopBar;

public interface BaseComponents {
    TopBar getTopBar();
    DrawerLayout getDrawerLayout();
    Navigation getSideMenu();
}
