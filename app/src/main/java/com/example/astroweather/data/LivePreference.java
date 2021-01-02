package com.example.astroweather.data;

import android.content.SharedPreferences;

import androidx.lifecycle.MutableLiveData;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LivePreference<T> extends MutableLiveData<T> {

    public LivePreference(Observable<String> updates, SharedPreferences sharedPreferences, String key, T defaultValue) {
        this.updates = updates;
        this.sharedPreferences = sharedPreferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    private final Observable<String> updates;
    private final SharedPreferences sharedPreferences;
    private final String key;
    private final T defaultValue;
    private Disposable disposable = null;

    @Override
    protected void onActive() {
        super.onActive();
        if (sharedPreferences.getAll().get(key) != null) {
            setValue((T) sharedPreferences.getAll().get(key));
        } else {
            setValue(defaultValue);
        }

        disposable = updates.filter(k -> k.equals(key)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {

                    @Override
                    public void onNext(@NonNull String k) {
                        if (sharedPreferences.getAll() != null) {
                            postValue((T) sharedPreferences.getAll());
                        } else {
                            postValue(defaultValue);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        disposable.dispose();
    }
}
