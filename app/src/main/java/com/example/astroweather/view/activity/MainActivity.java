package com.example.astroweather.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.example.astroweather.R;
import com.example.astroweather.view.component.Navigation;
import com.example.astroweather.view.component.TopBar;

public class MainActivity extends AppCompatActivity implements BaseComponents {

    private TopBar topBar;
    private DrawerLayout drawerLayout;
    private Navigation sideMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setListeners();
    }

    private void findViews(){
        topBar = findViewById(R.id.topBar);
        drawerLayout = findViewById(R.id.drawerLayout);
        sideMenu = findViewById(R.id.navigation);
    }

    private void setListeners(){
        topBar.setTopBarListener(() -> {
           drawerLayout.openDrawer(GravityCompat.START, true);
        });
    }


    @Override
    public TopBar getTopBar() {
        return topBar;
    }

    @Override
    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    @Override
    public Navigation getSideMenu() {
        return sideMenu;
    }
}