package com.example.astroweather.view.base;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public class BaseViewModel extends ViewModel {

    public CompositeDisposable rxDisposer = new CompositeDisposable();

    @Override
    public void onCleared() {
        super.onCleared();
        rxDisposer.dispose();
        rxDisposer = null;
    }
}
