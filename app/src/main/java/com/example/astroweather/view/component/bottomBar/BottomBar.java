package com.example.astroweather.view.component.bottomBar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather.R;
import com.example.astroweather.view.base.BaseActions;

import java.util.List;

public class BottomBar extends ConstraintLayout implements BaseActions {

    private BottomMenuAdapter bottomMenuAdapter = new BottomMenuAdapter();
    private RecyclerView recyclerView;

    public BottomBar(@NonNull Context context) {
        super(context);
        View.inflate(context, R.layout.view_bottom_bar, this);
        findViews(this);
        setListeners();
    }

    public BottomBar(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.view_bottom_bar, this);
        findViews(this);
        setListeners();
    }

    public BottomBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.view_bottom_bar, this);
        findViews(this);
        setListeners();
    }

    public BottomBar(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        View.inflate(context, R.layout.view_bottom_bar, this);
        findViews(this);
        setListeners();
    }


    @Override
    public void findViews(View view) {
        recyclerView = view.findViewById(R.id.bottomMenu_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(this.bottomMenuAdapter);
    }

    @Override
    public void setListeners() {

    }

    public void setMenuItems(List<MenuItem> menuItems){
        this.bottomMenuAdapter.setData(menuItems);
    }

    public int getMenuPosition(){
        return bottomMenuAdapter.getCurrentItem();
    }

    public void setMenuPosition(int menuPosition) {
        bottomMenuAdapter.setCurrentItem(menuPosition);
    }
}
