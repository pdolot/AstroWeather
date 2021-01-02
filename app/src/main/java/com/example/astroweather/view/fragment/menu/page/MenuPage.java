package com.example.astroweather.view.fragment.menu.page;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.astroweather.R;
import com.example.astroweather.data.LocalStorage;
import com.example.astroweather.di.Injector;
import com.example.astroweather.model.WeatherUnits;
import com.example.astroweather.view.PageType;
import com.example.astroweather.view.base.BasePage;
import com.example.astroweather.view.base.adapter.PagerAdapter;

import javax.inject.Inject;

public class MenuPage extends BasePage {
    @Inject
    LocalStorage localStorage;
    private SeekBar sync;
    private TextView syncTime;
    private EditText latitude;
    private EditText longitude;
    private Button save;
    private int syncInterval;
    private View view;
    private RadioGroup radioGroup;

    public MenuPage() {
        Injector.appComponent.inject(this);
        syncInterval = localStorage.getSyncInterval();
    }

    @Override
    public String getTitle() {
        return "Menu";
    }

    @Override
    public PageType getPageType() {
        return PageType.MENU_PAGE;
    }

    @Override
    public void onBindView(PagerAdapter.ViewHolder viewHolder, int position) {
        view = viewHolder.itemView;
        sync = view.findViewById(R.id.navigation_sync);
        syncTime = view.findViewById(R.id.navigation_syncTime);
        save = view.findViewById(R.id.navigation_save);
        latitude = view.findViewById(R.id.navigation_latitude);
        longitude = view.findViewById(R.id.navigation_longitude);
        radioGroup = view.findViewById(R.id.navigation_units);

        sync.setProgress((syncInterval - 5) / 5);
        latitude.setText(String.valueOf(localStorage.getCurrentLatitude()));
        longitude.setText(String.valueOf(localStorage.getCurrentLongitude()));
        syncTime.setText(view.getContext().getString(R.string.sync_time, syncInterval));

        radioGroup.check(getWeatherUnitsId());

        setListeners(view.getContext());
    }


    private int getWeatherUnitsId() {
        WeatherUnits weatherUnits = localStorage.getWeatherUnits();
        if (weatherUnits.equals(WeatherUnits.IMPERIAL)) {
            return R.id.navigation_unitsImperial;
        } else if (weatherUnits.equals(WeatherUnits.STANDARD)) {
            return R.id.navigation_unitsStandard;
        } else {
            return R.id.navigation_unitsMetric;
        }
    }

    private WeatherUnits getSelectedMetric() {
        int index = radioGroup.indexOfChild(view.findViewById(radioGroup.getCheckedRadioButtonId()));
        if (index == 1) {
            return WeatherUnits.STANDARD;
        } else if (index == 2) {
            return WeatherUnits.IMPERIAL;
        } else {
            return WeatherUnits.METRIC;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setListeners(Context context) {

        sync.setOnTouchListener((v, event) -> event.getAction() == MotionEvent.ACTION_DOWN);

        sync.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                syncInterval = progress * 5 + 5;
                syncTime.setText(context.getString(R.string.sync_time, syncInterval));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        save.setOnClickListener(view -> {
            String lat = latitude.getText().toString();
            String lng = longitude.getText().toString();

            if (!lat.isEmpty() && !lng.isEmpty()) {
                localStorage.setCurrentLatitude(Float.parseFloat(lat));
                localStorage.setCurrentLongitude(Float.parseFloat(lng));
                localStorage.setSyncInterval(syncInterval);
                localStorage.setWeatherUnits(getSelectedMetric().name());
            }
        });
    }
}
