package com.example.astroweather.view.fragment.home;

import androidx.lifecycle.MutableLiveData;

import com.example.astroweather.di.Injector;
import com.example.astroweather.model.NavigationData;
import com.example.astroweather.model.api.AstronomyData;
import com.example.astroweather.rest.RestRepository;
import com.example.astroweather.view.base.BasePage;
import com.example.astroweather.view.base.BaseViewModel;
import com.example.astroweather.view.page.moon.MoonPage;
import com.example.astroweather.view.page.sun.SunPage;

import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeViewModel extends BaseViewModel {

    @Inject
    RestRepository restRepository;

    public MutableLiveData<String> currentTime = new MutableLiveData<>();
    public MutableLiveData<List<BasePage>> pages = new MutableLiveData<>();
    public Disposable dataSync;
    private static final String API_KEY = "5563c9d28a9241b7ac90a41ae11de174";

    public HomeViewModel() {
        Injector.appComponent.inject(this);
        this.currentTime.postValue(getCurrentTime());
        rxDisposer.add(
                Observable
                        .interval(1, TimeUnit.SECONDS)
                        .flatMap(f -> Observable.<String>create(s -> {
                            s.onNext(getCurrentTime());
                            s.onComplete();
                        }))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(time -> currentTime.postValue(time))
        );
    }

    public void getData(NavigationData navigationData) {
        if (dataSync != null) {
            rxDisposer.remove(dataSync);
        }

        dataSync = restRepository
                .getSunData(API_KEY, navigationData.getLatitude(), navigationData.getLongitude())
                .repeatWhen(s -> s.delay(navigationData.getSyncInterval(), TimeUnit.MINUTES))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(astronomyData -> {
                    pages.postValue(getPages(astronomyData));
                }, e -> {
                    System.out.println(e.getMessage());
                });

        rxDisposer.add(dataSync);

    }

    private List<BasePage> getPages(AstronomyData astronomyData) {
        List<BasePage> pages = new ArrayList<>();
        pages.add(new SunPage(astronomyData));
        pages.add(new MoonPage(astronomyData));
        return pages;
    }

    private String getCurrentTime() {
        LocalTime current = LocalTime.now();
        return current.toString("HH:mm:ss");
    }
}
