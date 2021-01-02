package com.example.astroweather.view.fragment.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.astroweather.R;
import com.example.astroweather.view.base.fragment.PagerFragment;

public class MenuFragment extends PagerFragment<MenuViewModel> {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        topBar.setTitle("Menu");
        viewModel.pages.observe(getViewLifecycleOwner(), pages -> {
            adapter.setPages(pages);
        });
        viewModel.errors.observe(getViewLifecycleOwner(), error -> {
            if (error != null)
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int recyclerViewId() {
        return R.id.fragmentMenu_recyclerView;
    }

    @Override
    public Class<MenuViewModel> getViewModelClass() {
        return MenuViewModel.class;
    }

    @Override
    public Integer getMenuPosition() {
        return 2;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.errors.postValue(null);
    }

    @Override
    public void findViews(View view) {

    }

    @Override
    public void setListeners() {

    }

}
