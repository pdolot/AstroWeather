package com.example.astroweather;

import android.app.Application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.example.astroweather.di.Injector;

public class App extends Application {

    private AppCompatActivity currentActivity;

    @Override
    public void onCreate() {
        super.onCreate();

    }

    public AppCompatActivity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(AppCompatActivity currentActivity) {
        this.currentActivity = currentActivity;
    }
}
