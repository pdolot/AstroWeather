package com.example.astroweather.rest.astronomy;

import com.example.astroweather.constants.ApiConstants;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class AstronomyRetrofit {

    private Retrofit retrofit;

    public AstronomyRetrofit(OkHttpClient okHttpClient) {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.ASTRONOMY_BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
