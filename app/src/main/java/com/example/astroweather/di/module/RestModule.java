package com.example.astroweather.di.module;

import com.example.astroweather.rest.astronomy.AstronomyRestRepository;
import com.example.astroweather.rest.astronomy.AstronomyRetrofit;
import com.example.astroweather.rest.astronomy.AstronomyRestService;
import com.example.astroweather.rest.weather.WeatherRestRepository;
import com.example.astroweather.rest.weather.WeatherRestService;
import com.example.astroweather.rest.weather.WeatherRetrofit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

@Module
public class RestModule {

    @Provides
    @Singleton
    public OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }

    @Provides
    @Singleton
    public AstronomyRetrofit provideAstronomyRetrofit(OkHttpClient okHttpClient) {
        return new AstronomyRetrofit(okHttpClient);
    }

    @Provides
    @Singleton
    public AstronomyRestService provideAstronomyRestService(AstronomyRetrofit astronomyRetrofit){
        return astronomyRetrofit.getRetrofit().create(AstronomyRestService.class);
    }

    @Provides
    @Singleton
    public AstronomyRestRepository provideAstronomyRestRepository(AstronomyRestService astronomyRestService){
        return new AstronomyRestRepository(astronomyRestService);
    }

    @Provides
    @Singleton
    public WeatherRetrofit provideWeatherRetrofit(OkHttpClient okHttpClient) {
        return new WeatherRetrofit(okHttpClient);
    }

    @Provides
    @Singleton
    public WeatherRestService provideWeatherRestService(WeatherRetrofit weatherRetrofit){
        return weatherRetrofit.getRetrofit().create(WeatherRestService.class);
    }

    @Provides
    @Singleton
    public WeatherRestRepository provideWeatherRestRepository(WeatherRestService weatherRestService){
        return new WeatherRestRepository(weatherRestService);
    }
}
