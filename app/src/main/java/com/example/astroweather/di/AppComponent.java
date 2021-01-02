package com.example.astroweather.di;

import com.example.astroweather.db.repository.WeatherRepository;
import com.example.astroweather.di.module.AppModule;
import com.example.astroweather.di.module.DbModule;
import com.example.astroweather.di.module.RestModule;
import com.example.astroweather.rest.weather.WeatherRestRepository;
import com.example.astroweather.view.component.bottomBar.BottomMenuAdapter;
import com.example.astroweather.view.fragment.astronomy.AstronomyViewModel;
import com.example.astroweather.view.fragment.menu.MenuViewModel;
import com.example.astroweather.view.fragment.menu.adapter.LocationAdapter;
import com.example.astroweather.view.fragment.menu.page.MenuPage;
import com.example.astroweather.view.fragment.weather.WeatherViewModel;
import com.example.astroweather.view.fragment.weather.page.currentWeather.CurrentWeatherPage;
import com.example.astroweather.view.fragment.weather.page.dailyWeather.DailyWeatherPage;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                RestModule.class,
                AppModule.class,
                DbModule.class
        }
)
public interface AppComponent {
    void inject(AstronomyViewModel into);

    void inject(BottomMenuAdapter into);

    void inject(WeatherRestRepository into);

    void inject(WeatherViewModel into);

    void inject(MenuViewModel into);

    void inject(MenuPage into);

    void inject(LocationAdapter into);

    void inject(CurrentWeatherPage into);

    void inject(DailyWeatherPage into);

    void inject(WeatherRepository into);
}
