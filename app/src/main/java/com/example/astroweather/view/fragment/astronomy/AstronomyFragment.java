package com.example.astroweather.view.fragment.astronomy;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.astroweather.R;
import com.example.astroweather.view.base.fragment.PagerFragment;

public class AstronomyFragment extends PagerFragment<AstronomyViewModel> {
    private TextView lat;
    private TextView lng;

    @Override
    public Class<AstronomyViewModel> getViewModelClass() {
        return AstronomyViewModel.class;
    }

    @Override
    public Integer getMenuPosition() {
        return 1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_astronomy, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topBar.setTitle("Dane astronomiczne");
        setCurrentLatLng();

        viewModel.pages.observe(getViewLifecycleOwner(), pages -> {
            adapter.setPages(pages);
        });

        viewModel.localStorage.configurationChanged().observe(getViewLifecycleOwner(), isChanged -> {
            if (isChanged){
                viewModel.getData();
                setCurrentLatLng();
            }

        });
    }

    private void setCurrentLatLng(){
        lat.setText(getString(R.string.lat, viewModel.localStorage.getCurrentLatitude()));
        lng.setText(getString(R.string.lng, viewModel.localStorage.getCurrentLongitude()));
    }

    @Override
    public void findViews(View view) {
        lat = view.findViewById(R.id.latitude);
        lng = view.findViewById(R.id.longitude);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public int recyclerViewId() {
        return R.id.fragmentAstronomy_recyclerView;
    }

}
