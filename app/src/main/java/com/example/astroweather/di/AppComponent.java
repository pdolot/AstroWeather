package com.example.astroweather.di;

import com.example.astroweather.di.module.RestModule;
import com.example.astroweather.view.fragment.home.HomeViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                RestModule.class
        }
)
public interface AppComponent {
    void inject(HomeViewModel into);
}
