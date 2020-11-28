package com.example.astroweather.view.base;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.astroweather.view.activity.BaseComponents;
import com.example.astroweather.view.component.Navigation;
import com.example.astroweather.view.component.TopBar;

abstract public class BaseFragment extends Fragment implements BaseActions{

    private BaseComponents baseComponents;
    public ViewModel viewModel;
    public TopBar topBar;
    public DrawerLayout drawerLayout;
    public Navigation navigation;

    abstract public Class<? extends ViewModel> getViewModelClass();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            baseComponents = (BaseComponents) getContext();
        } catch (Exception e) {
            throw new IllegalStateException("Main activity must implement base interface");
        }

        if (baseComponents != null){
            topBar = baseComponents.getTopBar();
            drawerLayout = baseComponents.getDrawerLayout();
            navigation = baseComponents.getSideMenu();
        }
    }

    @Override
    public void onDetach() {
        topBar = null;
        drawerLayout = null;
        super.onDetach();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(getViewModelClass());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);
        setListeners();
    }
}
