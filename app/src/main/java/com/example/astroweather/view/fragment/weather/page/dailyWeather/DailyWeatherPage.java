package com.example.astroweather.view.fragment.weather.page.dailyWeather;

import android.content.Context;
import android.util.Pair;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.astroweather.R;
import com.example.astroweather.constants.ApiConstants;
import com.example.astroweather.di.Injector;
import com.example.astroweather.model.weather.DailyWeather;
import com.example.astroweather.util.TextUtil;
import com.example.astroweather.util.TimeUtil;
import com.example.astroweather.util.UnitUtil;
import com.example.astroweather.util.WeatherDataUtil;
import com.example.astroweather.view.PageType;
import com.example.astroweather.view.base.BasePage;
import com.example.astroweather.view.base.adapter.PagerAdapter;
import com.example.astroweather.view.fragment.weather.adapter.DaysAdapter;
import com.example.astroweather.view.fragment.weather.adapter.WeatherDataAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DailyWeatherPage extends BasePage {
    @Inject
    UnitUtil unitUtil;

    @Inject
    WeatherDataUtil weatherDataUtil;

    private Context context;
    private ImageView weatherIcon;
    private TextView weatherDescription;
    private TextView temperature;
    private TextView dateAndLocation;
    private RecyclerView recyclerView;
    private RecyclerView dayRecyclerView;

    private List<DailyWeather> dailyWeathers;
    private final WeatherDataAdapter weatherDataAdapter = new WeatherDataAdapter();
    private final DaysAdapter daysAdapter = new DaysAdapter();

    private int currentDayPosition = 0;

    public DailyWeatherPage(List<DailyWeather> dailyWeathers) {
        Injector.appComponent.inject(this);
        this.dailyWeathers = dailyWeathers;
    }

    @Override
    public String getTitle() {
        return "Prognoza pogody";
    }

    @Override
    public PageType getPageType() {
        return PageType.DAILY_WEATHER_PAGE;
    }

    @Override
    public void onBindView(PagerAdapter.ViewHolder viewHolder, int position) {
        context = viewHolder.itemView.getContext();
        weatherIcon = viewHolder.itemView.findViewById(R.id.weatherIcon);
        weatherDescription = viewHolder.itemView.findViewById(R.id.weatherDescription);
        temperature = viewHolder.itemView.findViewById(R.id.temperature);
        dateAndLocation = viewHolder.itemView.findViewById(R.id.dateAndLocation);
        recyclerView = viewHolder.itemView.findViewById(R.id.pageCurrentWeather_recyclerView);
        dayRecyclerView = viewHolder.itemView.findViewById(R.id.days);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(weatherDataAdapter);

        if (dailyWeathers.size() == 0){
            dayRecyclerView.setLayoutManager(new GridLayoutManager(context, 1));
        }else{
            dayRecyclerView.setLayoutManager(new GridLayoutManager(context, dailyWeathers.size()));
        }

        dayRecyclerView.setAdapter(daysAdapter);

        daysAdapter.setData(getDays());

        daysAdapter.setDaysAdapterListener(p -> {
            daysAdapter.setCurrentPosition(p);
            currentDayPosition = p;
            bindViews();
        });

        bindViews();
    }

    private List<Pair<String, String>> getDays() {
        ArrayList<Pair<String, String>> days = new ArrayList<>();
        for (DailyWeather dailyWeather : dailyWeathers) {
            days.add(new Pair<>(TimeUtil.getDay(dailyWeather.getDate()), TimeUtil.getMonth(dailyWeather.getDate())));
        }
        return days;
    }

    private void bindViews() {
        if (dailyWeathers.size() > 0){
            DailyWeather weather = dailyWeathers.get(currentDayPosition);
            temperature.setText(unitUtil.getTemperature(weather.getTemp_min()) + " | " + unitUtil.getTemperature(weather.getTemp_max()));
            dateAndLocation.setText(TimeUtil.getDate(weather.getDate()) + ", " + weather.getCity());

            Glide.with(context).load(ApiConstants.getIconUrl(weather.getIcon())).into(weatherIcon);
            weatherDescription.setText(TextUtil.capitalize(weather.getDescription()));

            weatherDataAdapter.setData(weatherDataUtil.getWeatherData(weather));
        }
    }
}
