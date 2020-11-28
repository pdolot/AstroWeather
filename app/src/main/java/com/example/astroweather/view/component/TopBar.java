package com.example.astroweather.view.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.astroweather.R;
import com.example.astroweather.view.base.BaseActions;

public class TopBar extends ConstraintLayout implements BaseActions {

    private ImageView sideMenu;
    private TopBarListener topBarListener;
    private TextView currentTime;
    private TextView latitude;
    private TextView longitude;

    public TopBar(@NonNull Context context) {
        super(context);
        View.inflate(context, R.layout.view_top_bar, this);
        findViews(this);
        setListeners();
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.view_top_bar, this);
        findViews(this);
        setListeners();
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_top_bar, this);
        findViews(this);
        setListeners();
    }

    public TopBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        View.inflate(context, R.layout.view_top_bar, this);
        findViews(this);
        setListeners();
    }


    @Override
    public void findViews(View view) {
        sideMenu = view.findViewById(R.id.sideMenu);
        currentTime = view.findViewById(R.id.topBar_currentTime);
        latitude = view.findViewById(R.id.topBar_latitude);
        longitude = view.findViewById(R.id.topBar_longitude);
    }

    @Override
    public void setListeners() {
        sideMenu.setOnClickListener(view -> {
            if (topBarListener != null){
                topBarListener.onSideMenuClickListener();
            }
        });
    }

    public void setTopBarListener(TopBarListener topBarListener) {
        this.topBarListener = topBarListener;
    }

    public interface TopBarListener{
        void onSideMenuClickListener();
    }

    public void setCurrentTime(String time){
        currentTime.setText(time);
    }

    public void setLatLng(String lat, String lng){
        latitude.setText(lat);
        longitude.setText(lng);
    }
}
