package com.example.astroweather.di;

import com.example.astroweather.App;
import com.example.astroweather.di.module.RestModule;

public class Injector {

    public static AppComponent appComponent;

    public static void init(App app){
        appComponent = DaggerAppComponent.builder()
                .restModule(new RestModule())
                .build();
    }
}
