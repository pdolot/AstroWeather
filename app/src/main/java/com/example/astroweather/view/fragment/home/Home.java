package com.example.astroweather.view.fragment.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.astroweather.R;
import com.example.astroweather.model.NavigationData;
import com.example.astroweather.view.base.BaseFragment;
import com.example.astroweather.view.component.Navigation;

public class Home extends BaseFragment {

    private RecyclerView recyclerView;
    private ViewSwapperAdapter viewSwapperAdapter;
    private SnapHelper snapHelper;

    @Override
    public Class<? extends ViewModel> getViewModelClass() {
        return HomeViewModel.class;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        NavigationData navigationData = navigation.getNavigationData();
        ((HomeViewModel) viewModel).getData(navigationData);
        topBar.setLatLng(navigationData.getLatitude(), navigationData.getLongitude());
    }

    @Override
    public void findViews(View view) {
        recyclerView = view.findViewById(R.id.viewSwapper);
        initRecyclerView();
    }

    public void initRecyclerView() {
        snapHelper = new LinearSnapHelper();
        viewSwapperAdapter = new ViewSwapperAdapter();

        if (getResources().getBoolean(R.bool.isTablet)) {
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else {
            switch (getResources().getConfiguration().orientation) {
                case Configuration.ORIENTATION_PORTRAIT:
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
                    break;
                case Configuration.ORIENTATION_LANDSCAPE:
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                    break;
            }
        }
        recyclerView.setAdapter(viewSwapperAdapter);
        snapHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void setListeners() {
        ((HomeViewModel) viewModel).currentTime.observe(getViewLifecycleOwner(), s -> {
            topBar.setCurrentTime(s);
        });

        ((HomeViewModel) viewModel).pages.observe(getViewLifecycleOwner(), pages -> {
            viewSwapperAdapter.setPages(pages);
        });

        navigation.setNavigationListener(navigationData -> {
            topBar.setLatLng(navigationData.getLatitude(), navigationData.getLongitude());
            drawerLayout.closeDrawer(GravityCompat.START, true);
            ((HomeViewModel) viewModel).getData(navigationData);
        });
    }

}
