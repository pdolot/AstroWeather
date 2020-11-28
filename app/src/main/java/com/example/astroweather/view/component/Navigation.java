package com.example.astroweather.view.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.astroweather.R;
import com.example.astroweather.view.base.BaseActions;

public class Navigation extends ConstraintLayout implements BaseActions {

    private SeekBar sync;
    private TextView syncTime;

    public Navigation(@NonNull Context context) {
        super(context);
        View.inflate(context, R.layout.view_navigation, this);
        findViews(this);
        setListeners();
    }

    public Navigation(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.view_navigation, this);
        findViews(this);
        setListeners();
    }

    public Navigation(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_navigation, this);
        findViews(this);
        setListeners();
    }

    public Navigation(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        View.inflate(context, R.layout.view_navigation, this);
        findViews(this);
        setListeners();
    }

    @Override
    public void findViews(View view) {
        sync = view.findViewById(R.id.navigation_sync);
        syncTime = view.findViewById(R.id.navigation_syncTime);
    }

    @Override
    public void setListeners() {
        sync.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN){
                return true;
            }
            return false;
        });

        sync.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int p = progress * 5 + 5;
                syncTime.setText(getContext().getString(R.string.sync_time, p));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
