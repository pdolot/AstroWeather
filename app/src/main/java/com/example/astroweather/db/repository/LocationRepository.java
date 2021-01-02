package com.example.astroweather.db.repository;

import com.example.astroweather.db.dao.LocationDao;
import com.example.astroweather.model.weather.Location;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LocationRepository {

    private LocationDao locationDao;

    public LocationRepository(LocationDao locationDao) {
        this.locationDao = locationDao;
    }

    public Single<List<Location>> getLocations(Boolean isFavorite) {
        if (isFavorite){
            return locationDao.getLocations(isFavorite)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }else{
            return locationDao.getAllLocations()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }

    public Single<Location> getLocationById(Long id) {
        return locationDao.getLocationById(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Integer> setFavorite(Long id, Boolean isFavorite) {
        return locationDao.setAsFavorite(id, isFavorite)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    public Single<Long> insert(Location location) {
        return locationDao.insert(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Single<Integer> delete(Long id) {
        return locationDao.deleteLocation(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
