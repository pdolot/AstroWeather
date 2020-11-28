package com.example.astroweather.view.fragment.home;

import com.example.astroweather.view.base.BasePage;
import com.example.astroweather.view.base.BaseViewModel;
import com.example.astroweather.view.page.moon.MoonPage;
import com.example.astroweather.view.page.sun.SunPage;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends BaseViewModel {

    public List<BasePage> getPages(){
        List<BasePage> pages = new ArrayList<>();
        pages.add(new SunPage());
        pages.add(new MoonPage());
        return pages;
    }
}
