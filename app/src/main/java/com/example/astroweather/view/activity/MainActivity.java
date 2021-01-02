package com.example.astroweather.view.activity;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.astroweather.App;
import com.example.astroweather.ConnectivityReceiver;
import com.example.astroweather.R;
import com.example.astroweather.di.Injector;
import com.example.astroweather.view.component.TopBar;
import com.example.astroweather.view.component.bottomBar.BottomBar;
import com.example.astroweather.view.component.bottomBar.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BaseComponents {

    private MainViewModel viewModel;
    private BottomBar bottomBar;
    private TextView error;
    private ConnectivityReceiver connectivityReceiver = new ConnectivityReceiver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((App) getApplication()).setCurrentActivity(this);
        Injector.init((App) getApplication());

        setContentView(R.layout.activity_main);

        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        bottomBar = findViewById(R.id.bottomBar);
        error = findViewById(R.id.error);
        bottomBar.setMenuItems(getMenuItems());
        bottomBar.setMenuPosition(viewModel.menuPosition);
    }

    private List<MenuItem> getMenuItems() {
        List<MenuItem> menuItems = new ArrayList<>();
        menuItems.add(new MenuItem("Pogoda", R.drawable.ic_weathericon, R.id.weatherFragment));
        menuItems.add(new MenuItem("Astro", R.drawable.ic_astro, R.id.astronomyFragment));
        menuItems.add(new MenuItem("Menu", R.drawable.ic_menu, R.id.menuFragment));
        return menuItems;
    }

    @Override
    protected void onResume() {
        super.onResume();
        connectivityReceiver.connectivityReceiverListener = isConnected -> {
            if (isConnected){
                error.setVisibility(View.GONE);
            }else{
                error.setVisibility(View.VISIBLE);
            }
        };
    }

    @Override
    protected void onPause() {
        super.onPause();
        viewModel.menuPosition = bottomBar.getMenuPosition();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(connectivityReceiver);
        super.onDestroy();
    }

    @Override
    public TopBar getTopBar() {
        return findViewById(R.id.topBar);
    }

    @Override
    public BottomBar getBottomBar() {
        return findViewById(R.id.bottomBar);
    }

}