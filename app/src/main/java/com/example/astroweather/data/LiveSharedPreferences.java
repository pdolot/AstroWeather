package com.example.astroweather.data;

import android.content.SharedPreferences;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public class LiveSharedPreferences {

    public LiveSharedPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    private SharedPreferences sharedPreferences;
    private final PublishSubject<String> publisher = PublishSubject.create();
    private final SharedPreferences.OnSharedPreferenceChangeListener listener = (sharedPreferences, key) -> publisher.onNext(key);

    private final Observable<String> updates = publisher.doOnSubscribe(s -> {
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);
    }).doOnDispose(() -> {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener);
    });

    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }

    public LivePreference<String> getString(String key, String defaultValue) {
        return new LivePreference<>(updates, sharedPreferences, key, defaultValue);
    }

    public LivePreference<Integer> getInt(String key, Integer defaultValue) {
        return new LivePreference<>(updates, sharedPreferences, key, defaultValue);
    }

    public LivePreference<Boolean> getBoolean(String key, Boolean defaultValue) {
        return new LivePreference<>(updates, sharedPreferences, key, defaultValue);
    }

    public LivePreference<Float> getFloat(String key, Float defaultValue) {
        return new LivePreference<>(updates, sharedPreferences, key, defaultValue);
    }

    public LivePreference<Long> getLong(String key, Long defaultValue) {
        return new LivePreference<>(updates, sharedPreferences, key, defaultValue);
    }
}
