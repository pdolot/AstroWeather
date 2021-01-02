package com.example.astroweather.view.fragment.weather.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.astroweather.R;
import com.example.astroweather.model.WeatherData;
import com.example.astroweather.view.base.adapter.BaseAdapter;

public class WeatherDataAdapter extends BaseAdapter<WeatherDataAdapter.ViewHolder, WeatherData> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_data, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ImageView icon = holder.itemView.findViewById(R.id.icon);
        TextView label = holder.itemView.findViewById(R.id.label);
        TextView value = holder.itemView.findViewById(R.id.value);

        WeatherData weatherData = getItemAtPosition(position);

        Glide.with(holder.itemView.getContext()).load(weatherData.getIcon()).into(icon);
        label.setText(weatherData.getLabel());
        value.setText(weatherData.getValue());
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
