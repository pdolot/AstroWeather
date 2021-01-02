package com.example.astroweather.view.fragment.astronomy;

import androidx.lifecycle.MutableLiveData;

import com.example.astroweather.data.LocalStorage;
import com.example.astroweather.di.Injector;
import com.example.astroweather.model.astronomy.AstronomyData;
import com.example.astroweather.rest.astronomy.AstronomyRestRepository;
import com.example.astroweather.view.base.BasePage;
import com.example.astroweather.view.base.BaseViewModel;
import com.example.astroweather.view.fragment.astronomy.page.moon.MoonPage;
import com.example.astroweather.view.fragment.astronomy.page.sun.SunPage;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AstronomyViewModel extends BaseViewModel {

    @Inject
    AstronomyRestRepository astronomyRestRepository;
    @Inject
    LocalStorage localStorage;

    public MutableLiveData<List<BasePage>> pages = new MutableLiveData<>();
    public Disposable dataSync;

    public AstronomyViewModel() {
        Injector.appComponent.inject(this);
        getData();
    }

    public void getData() {
        if (dataSync != null) {
            rxDisposer.remove(dataSync);
        }

        dataSync = astronomyRestRepository
                .getAstronomyData(localStorage.getCurrentLatitude().toString(), localStorage.getCurrentLongitude().toString())
                .repeatWhen(s -> s.delay(localStorage.getSyncInterval(), TimeUnit.MINUTES))
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
}
