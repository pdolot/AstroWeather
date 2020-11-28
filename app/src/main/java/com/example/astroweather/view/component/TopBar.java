package com.example.astroweather.view.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.astroweather.R;
import com.example.astroweather.view.base.BaseActions;

public class TopBar extends ConstraintLayout implements BaseActions {

    private ImageView sideMenu;
    private TopBarListener topBarListener;

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
}
