package com.example.astroweather.view.fragment.menu;

import androidx.lifecycle.MutableLiveData;

import com.example.astroweather.data.LocalStorage;
import com.example.astroweather.db.repository.LocationRepository;
import com.example.astroweather.di.Injector;
import com.example.astroweather.model.weather.Location;
import com.example.astroweather.rest.weather.WeatherRestRepository;
import com.example.astroweather.view.base.BasePage;
import com.example.astroweather.view.base.BaseViewModel;
import com.example.astroweather.view.fragment.menu.page.LocationPage;
import com.example.astroweather.view.fragment.menu.page.MenuPage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MenuViewModel extends BaseViewModel implements LocationPage.LocationListener {

    @Inject
    WeatherRestRepository weatherRestRepository;
    @Inject
    LocationRepository locationRepository;
    @Inject
    LocalStorage localStorage;

    public MutableLiveData<List<BasePage>> pages = new MutableLiveData<>();
    public MutableLiveData<String> errors = new MutableLiveData<>();
    public MenuPage menuPage = new MenuPage();
    private Boolean favoriteFilterEnable = true;

    public MenuViewModel() {
        Injector.appComponent.inject(this);
        getLocations();
    }

    private List<BasePage> getPages(List<Location> locations) {
        List<BasePage> pages = new ArrayList<>();
        pages.add(new LocationPage(this, locations, favoriteFilterEnable));
        pages.add(menuPage);
        return pages;
    }

    private void checkLocation(String city) {
        rxDisposer.add(weatherRestRepository.checkIfLocationExist(city.trim())
                .flatMap(location -> {
                    localStorage.setCurrentLongitude((float) location.getLongitude());
                    localStorage.setCurrentLatitude((float) location.getLatitude());
                    return locationRepository.insert(location);
                })
                .subscribe(insertedId -> {
                    localStorage.setCurrentLocation(insertedId);
                    getLocations();
                }, error -> {
                    errors.postValue("Lokalizacja nie istnieje");
                    System.out.println(error.getMessage());
                }));

    }

    public void getLocations() {
        rxDisposer.add(
                locationRepository.getLocations(favoriteFilterEnable)
                        .subscribe(locations -> {
                            pages.postValue(getPages(locations));
                        }, error -> {
                            pages.postValue(getPages(null));
                        }));
    }


    @Override
    public void onAddLocation(String city) {
        checkLocation(city);
    }

    @Override
    public void isFavoriteFilter(Boolean favoriteFilterEnable) {
        this.favoriteFilterEnable = favoriteFilterEnable;
        getLocations();
    }

    @Override
    public void onChangeFavorite(Long id, Boolean favorite) {
        rxDisposer.add(locationRepository.setFavorite(id, favorite)
                .subscribe(onSuccess -> getLocations(), onError -> {

                }))
        ;
    }

    @Override
    public void onRemove(Long id) {
        rxDisposer.add(locationRepository.delete(id)
                .subscribe(onSuccess -> getLocations(), onError -> {

                }));
    }

    @Override
    public void onChangeCurrent(Long id) {
        localStorage.setCurrentLocation(id);
        rxDisposer.add(locationRepository.getLocationById(id)
                .subscribe(location -> {
                    localStorage.setCurrentLongitude((float) location.getLongitude());
                    localStorage.setCurrentLatitude((float) location.getLatitude());
                    getLocations();
                }, error -> {
                    System.out.println(error.getMessage());
                }));
    }
}
