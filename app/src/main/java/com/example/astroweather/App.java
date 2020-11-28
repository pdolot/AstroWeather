package com.example.astroweather;

import android.app.Application;

import com.example.astroweather.di.Injector;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Injector.init(this);
    }
}
