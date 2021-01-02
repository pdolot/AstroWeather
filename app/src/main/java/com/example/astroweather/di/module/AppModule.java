package com.example.astroweather.di.module;

import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.astroweather.App;
import com.example.astroweather.R;
import com.example.astroweather.data.LocalStorage;
import com.example.astroweather.data.SharedPreferencesLocalStorage;
import com.example.astroweather.util.UnitUtil;
import com.example.astroweather.util.WeatherDataUtil;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private App app;

    public AppModule(App app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public NavHostFragment provideNavHostFragment() {
        return (NavHostFragment) app.getCurrentActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
    }

    @Provides
    @Singleton
    public NavController provideNavController(NavHostFragment navHostFragment) {
        return navHostFragment.getNavController();
    }

    @Provides
    @Singleton
    public LocalStorage provideLocalStorage(){
        return new SharedPreferencesLocalStorage(app.getApplicationContext());
    }

    @Provides
    @Singleton
    public UnitUtil provideUnitUtil(LocalStorage localStorage){
        return new UnitUtil(localStorage);
    }

    @Provides
    @Singleton
    public WeatherDataUtil provideWeatherDataUtil(UnitUtil unitUtil){
        return new WeatherDataUtil(unitUtil);
    }
}
