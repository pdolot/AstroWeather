package com.example.astroweather.view.base.fragment;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.astroweather.R;
import com.example.astroweather.view.base.adapter.PagerAdapter;

public abstract class PagerFragment<VM extends ViewModel> extends BaseFragment<VM> {

    public RecyclerView recyclerView;
    public SnapHelper snapHelper = new LinearSnapHelper();

    public abstract int recyclerViewId();

    public PagerAdapter adapter = new PagerAdapter();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(recyclerViewId());
        recyclerView.setAdapter(adapter);
        setLayoutManager();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    private void addScrollListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager != null) {
                    View snapView = snapHelper.findSnapView(layoutManager);
                    if (snapView != null) {
                        int position = layoutManager.getPosition(snapView);
                        topBar.setTitle(adapter.getPageTitleAt(position));
                    }
                }
            }
        });
    }

    private void setLayoutManager() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
        if (getResources().getBoolean(R.bool.isTablet) || getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            adapter.setViewWidth(screenWidth / 2);
        } else {
            adapter.setViewWidth(screenWidth);
            addScrollListener();
        }
    }
}
