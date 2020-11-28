package com.example.astroweather.view.fragment.home;

import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.astroweather.R;
import com.example.astroweather.view.base.BaseFragment;

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
        viewSwapperAdapter.setPages(((HomeViewModel) viewModel).getPages());
    }

    @Override
    public void findViews(View view) {
        recyclerView = view.findViewById(R.id.viewSwapper);
        initRecyclerView();
    }

    public void initRecyclerView() {
        snapHelper = new LinearSnapHelper();
        viewSwapperAdapter = new ViewSwapperAdapter();

        if (getResources().getBoolean(R.bool.isTablet)){
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        }else{
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

    }

}
