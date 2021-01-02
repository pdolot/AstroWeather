package com.example.astroweather.view.fragment.weather.page.currentWeather;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.astroweather.R;
import com.example.astroweather.constants.ApiConstants;
import com.example.astroweather.di.Injector;
import com.example.astroweather.model.weather.Weather;
import com.example.astroweather.util.TextUtil;
import com.example.astroweather.util.TimeUtil;
import com.example.astroweather.util.UnitUtil;
import com.example.astroweather.util.WeatherDataUtil;
import com.example.astroweather.view.PageType;
import com.example.astroweather.view.base.BasePage;
import com.example.astroweather.view.base.adapter.PagerAdapter;
import com.example.astroweather.view.fragment.weather.adapter.WeatherDataAdapter;

import javax.inject.Inject;

public class CurrentWeatherPage extends BasePage {
    @Inject
    UnitUtil unitUtil;

    @Inject
    WeatherDataUtil weatherDataUtil;

    private Weather weather;
    private final WeatherDataAdapter weatherDataAdapter = new WeatherDataAdapter();

    public CurrentWeatherPage(Weather weather) {
        Injector.appComponent.inject(this);
        this.weather = weather;
    }

    @Override
    public String getTitle() {
        return "Aktualna pogoda";
    }

    @Override
    public PageType getPageType() {
        return PageType.CURRENT_WEATHER_PAGE;
    }

    @Override
    public void onBindView(PagerAdapter.ViewHolder viewHolder, int position) {
        Context context = viewHolder.itemView.getContext();
        ImageView weatherIcon = viewHolder.itemView.findViewById(R.id.weatherIcon);
        TextView weatherDescription = viewHolder.itemView.findViewById(R.id.weatherDescription);
        TextView temperature = viewHolder.itemView.findViewById(R.id.temperature);
        TextView dateAndLocation = viewHolder.itemView.findViewById(R.id.dateAndLocation);
        RecyclerView recyclerView = viewHolder.itemView.findViewById(R.id.pageCurrentWeather_recyclerView);
        temperature.setText(unitUtil.getTemperature(weather.getTemp()));
        dateAndLocation.setText(TimeUtil.getDateTime(weather.getDate()) + ", " + weather.getCity());

        Glide.with(context).load(ApiConstants.getIconUrl(weather.getIcon())).into(weatherIcon);
        weatherDescription.setText(TextUtil.capitalize(weather.getDescription()));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(weatherDataAdapter);
        weatherDataAdapter.setData(weatherDataUtil.getWeatherData(weather));

    }
}
