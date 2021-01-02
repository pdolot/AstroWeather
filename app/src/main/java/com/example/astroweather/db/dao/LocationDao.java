package com.example.astroweather.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.astroweather.model.weather.Location;

import java.util.List;

import io.reactivex.Single;

@Dao
public abstract class LocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract Single<Long> insert(Location location);

    @Query("SELECT * FROM location WHERE isFavorite=:isFavorite")
    public abstract Single<List<Location>> getLocations(Boolean isFavorite);

    @Query("SELECT * FROM location")
    public abstract Single<List<Location>> getAllLocations();

    @Query("SELECT * FROM location WHERE id=:id")
    public abstract Single<Location> getLocationById(Long id);

    @Query("UPDATE location SET isFavorite=:favorite WHERE id=:id")
    public abstract Single<Integer> setAsFavorite(Long id, Boolean favorite);

    @Query("DELETE FROM location WHERE id= :id")
    public abstract Single<Integer> deleteLocation(long id);
}
