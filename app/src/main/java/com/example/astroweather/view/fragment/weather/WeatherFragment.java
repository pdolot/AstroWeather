package com.example.astroweather.view.fragment.weather;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.astroweather.ConnectivityReceiver;
import com.example.astroweather.R;
import com.example.astroweather.view.base.fragment.PagerFragment;

public class WeatherFragment extends PagerFragment<WeatherViewModel> {
    private TextView currentLocation;
    private ConnectivityReceiver connectivityReceiver = new ConnectivityReceiver();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        viewModel.getCurrentLocation();
        topBar.setTitle("Dane pogodowe");
        viewModel.pages.observe(getViewLifecycleOwner(), pages -> {
            adapter.setPages(pages);
        });
        viewModel.currentLocation.observe(getViewLifecycleOwner(), location -> {
            if (location != null) {
                currentLocation.setText("Dane dla miejscowości:\n\t\t" + location);
            }else{
                currentLocation.setText("Brak lokalizacji. Nie można pobrać pogody");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        connectivityReceiver.connectivityReceiverListener = isConnected -> {
            if (isConnected)
                viewModel.getCurrentLocation();
        };
    }

    @Override
    public void onDestroyView() {
        getActivity().unregisterReceiver(connectivityReceiver);
        super.onDestroyView();
    }

    @Override
    public int recyclerViewId() {
        return R.id.fragmentWeather_recyclerView;
    }

    @Override
    public Class<WeatherViewModel> getViewModelClass() {
        return WeatherViewModel.class;
    }

    @Override
    public Integer getMenuPosition() {
        return 0;
    }

    @Override
    public void findViews(View view) {
        currentLocation = view.findViewById(R.id.location);
    }

    @Override
    public void setListeners() {

    }
}
