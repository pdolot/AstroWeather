package com.example.astroweather.view.fragment.weather;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.astroweather.data.LocalStorage;
import com.example.astroweather.db.repository.DailyWeatherRepository;
import com.example.astroweather.db.repository.LocationRepository;
import com.example.astroweather.db.repository.SharedDbRepository;
import com.example.astroweather.db.repository.WeatherRepository;
import com.example.astroweather.di.Injector;
import com.example.astroweather.rest.weather.WeatherRestRepository;
import com.example.astroweather.view.base.BasePage;
import com.example.astroweather.view.base.BaseViewModel;
import com.example.astroweather.view.fragment.weather.page.currentWeather.CurrentWeatherPage;
import com.example.astroweather.view.fragment.weather.page.dailyWeather.DailyWeatherPage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.CompletableObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class WeatherViewModel extends BaseViewModel {
    private final String TAG = this.getClass().getSimpleName();
    @Inject
    WeatherRestRepository weatherRestRepository;
    @Inject
    LocalStorage localStorage;
    @Inject
    LocationRepository locationRepository;
    @Inject
    WeatherRepository weatherRepository;
    @Inject
    DailyWeatherRepository dailyWeatherRepository;
    @Inject
    SharedDbRepository sharedDbRepository;

    public MutableLiveData<List<BasePage>> pages = new MutableLiveData<>();
    public MutableLiveData<String> currentLocation = new MutableLiveData<>();

    private CurrentWeatherPage currentWeatherPage = null;
    private DailyWeatherPage dailyWeatherPage = null;
    private Long currentLocationId = -1L;

    public WeatherViewModel() {
        Injector.appComponent.inject(this);
    }

    private void addPages() {
        ArrayList<BasePage> pages = new ArrayList<>();
        if (currentWeatherPage != null) {
            pages.add(currentWeatherPage);
        }

        if (dailyWeatherPage != null) {
            pages.add(dailyWeatherPage);
        }

        this.pages.postValue(pages);
    }

    public void getCurrentLocation() {
        currentLocationId = localStorage.getCurrentLocation();
        if (!currentLocationId.equals(-1L)) {
            rxDisposer.add(locationRepository.getLocationById(currentLocationId)
                    .subscribe(location -> {
                        currentLocation.postValue(location.getCity());
                        getCurrentWeather(location.getCity());
                    }, error -> {
                        currentLocation.postValue(null);
                    }));
        } else {
            currentLocation.postValue(null);
        }
    }

    private void getCurrentWeather(String city) {
        rxDisposer.add(
                weatherRestRepository.getCurrentWeather(city)
                        .subscribe(weatherResponse -> {
                            sharedDbRepository.insertWeather(weatherResponse)
                                    .subscribe(new CompletableObserver() {
                                        @Override
                                        public void onSubscribe(@NonNull Disposable d) {
                                            rxDisposer.add(d);
                                            Log.v(TAG, "Subscribe weather transaction");
                                        }

                                        @Override
                                        public void onComplete() {
                                            Log.v(TAG, "Complete transaction");
                                            getCurrentWeatherFromDb();
                                        }

                                        @Override
                                        public void onError(@NonNull Throwable e) {
                                            Log.e(TAG, e.getLocalizedMessage());
                                        }
                                    });
                        }, onError -> {
                            getCurrentWeatherFromDb();
                        })
        );
    }

    private void getCurrentWeatherFromDb() {
        rxDisposer.add(
                weatherRepository.getCurrentWeather(currentLocationId)
                        .flatMap(weather -> {
                            currentWeatherPage = new CurrentWeatherPage(weather);
                            return dailyWeatherRepository.getWeatherForNextSevenDays(currentLocationId);
                        })
                        .subscribe(weather -> {
                            dailyWeatherPage = new DailyWeatherPage(weather);
                            addPages();
                        }, error -> {
                            currentWeatherPage = null;
                            dailyWeatherPage = null;
                            addPages();
                        })
        );
    }
}
