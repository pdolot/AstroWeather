package com.example.astroweather.view.base.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.astroweather.view.activity.BaseComponents;
import com.example.astroweather.view.base.BaseActions;
import com.example.astroweather.view.base.BaseViewModel;
import com.example.astroweather.view.component.bottomBar.BottomBar;
import com.example.astroweather.view.component.TopBar;

abstract public class BaseFragment<VM extends ViewModel> extends Fragment implements BaseActions {

    private BaseComponents baseComponents;
    public VM viewModel;
    public TopBar topBar;
    public BottomBar bottomBar;

    public abstract Class<VM> getViewModelClass();
    public abstract Integer getMenuPosition();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(getViewModelClass());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getComponents();
        findViews(view);
        setListeners();
        bottomBar.setMenuPosition(getMenuPosition());
    }

    private void getComponents() {
        try {
            baseComponents = (BaseComponents) getContext();
        } catch (Exception e) {
            throw new IllegalStateException("Main activity must implement base interface");
        }

        if (baseComponents != null) {
            topBar = baseComponents.getTopBar();
            bottomBar = baseComponents.getBottomBar();
        }
    }

    @Override
    public void onDestroyView() {
        if (viewModel instanceof BaseViewModel){
            ((BaseViewModel) viewModel).rxDisposer.clear();
        }
        super.onDestroyView();
    }

    @Override
    public void onDetach() {
        baseComponents = null;
        topBar = null;
        bottomBar = null;
        super.onDetach();
    }
}
