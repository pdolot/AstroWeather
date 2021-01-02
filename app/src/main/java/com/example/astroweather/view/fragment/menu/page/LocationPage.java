package com.example.astroweather.view.fragment.menu.page;

import android.content.Context;
import android.content.res.ColorStateList;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather.R;
import com.example.astroweather.model.weather.Location;
import com.example.astroweather.view.PageType;
import com.example.astroweather.view.base.BasePage;
import com.example.astroweather.view.base.adapter.PagerAdapter;
import com.example.astroweather.view.fragment.menu.adapter.LocationAdapter;

import java.util.List;

public class LocationPage extends BasePage {
    EditText locationInput;
    Button addLocation;
    RecyclerView recyclerView;
    TextView filterFavorite;
    TextView filterAll;
    LocationListener locationListener;
    LocationAdapter locationAdapter;
    List<Location> locations;
    Boolean favoriteFilterEnable = true;

    public LocationPage(LocationListener locationListener, List<Location> locations, Boolean favoriteFilterEnable) {
        this.locationListener = locationListener;
        locationAdapter = new LocationAdapter(locationListener);
        this.locations = locations;
        this.favoriteFilterEnable = favoriteFilterEnable;
    }

    @Override
    public String getTitle() {
        return "Lokalizacje";
    }

    @Override
    public PageType getPageType() {
        return PageType.LOCATION_PAGE;
    }

    @Override
    public void onBindView(PagerAdapter.ViewHolder viewHolder, int position) {
        Context context = viewHolder.itemView.getContext();
        locationInput = viewHolder.itemView.findViewById(R.id.location);
        addLocation = viewHolder.itemView.findViewById(R.id.addLocation);
        recyclerView = viewHolder.itemView.findViewById(R.id.pageLocation_recyclerView);
        filterFavorite = viewHolder.itemView.findViewById(R.id.filterFavorite);
        filterAll = viewHolder.itemView.findViewById(R.id.filterAll);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(locationAdapter);

        locationAdapter.setData(locations);

        addLocation.setOnClickListener(v -> {
            if (!locationInput.getText().toString().isEmpty())
                locationListener.onAddLocation(locationInput.getText().toString());
        });

        if (favoriteFilterEnable) {
            filterFavorite.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.primary)));
            filterFavorite.setTextColor(ContextCompat.getColor(context, R.color.white));

            filterAll.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.transparent)));
            filterAll.setTextColor(ContextCompat.getColor(context, R.color.textColor));
        } else {
            filterFavorite.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.transparent)));
            filterFavorite.setTextColor(ContextCompat.getColor(context, R.color.textColor));

            filterAll.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(context, R.color.primary)));
            filterAll.setTextColor(ContextCompat.getColor(context, R.color.white));
        }

        filterFavorite.setOnClickListener(v -> {
            locationListener.isFavoriteFilter(true);
        });

        filterAll.setOnClickListener(v -> {
            locationListener.isFavoriteFilter(false);
        });
    }

    public interface LocationListener extends LocationAdapter.LocationAdapterListener {
        void onAddLocation(String city);
        void isFavoriteFilter(Boolean favoriteFilterEnable);
    }
}
