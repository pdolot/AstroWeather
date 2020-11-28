package com.example.astroweather.view.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.astroweather.R;

public class Navigation extends ConstraintLayout {

    public Navigation(@NonNull Context context) {
        super(context);
        View.inflate(context, R.layout.view_navigation, this);
    }

    public Navigation(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.view_navigation, this);
    }

    public Navigation(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_navigation, this);
    }

    public Navigation(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        View.inflate(context, R.layout.view_navigation, this);
    }
}
