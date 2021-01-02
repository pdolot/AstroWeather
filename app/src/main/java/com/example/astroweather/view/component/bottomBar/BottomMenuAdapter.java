package com.example.astroweather.view.component.bottomBar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather.R;
import com.example.astroweather.di.Injector;
import com.example.astroweather.view.base.adapter.BaseAdapter;

import javax.inject.Inject;

public class BottomMenuAdapter extends BaseAdapter<BottomMenuAdapter.ViewHolder, MenuItem> {
    @Inject
    NavController navController;
    private int currentItem = 0;

    public void setCurrentItem(int currentItem) {
        this.currentItem = currentItem;
        notifyDataSetChanged();
    }

    public int getCurrentItem() {
        return currentItem;
    }

    public BottomMenuAdapter() {
        Injector.appComponent.inject(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bottom_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        MenuItem menuItem = getItemAtPosition(position);
        ImageView icon = holder.itemView.findViewById(R.id.icon);
        TextView label = holder.itemView.findViewById(R.id.label);

        icon.setImageDrawable(ContextCompat.getDrawable(context, menuItem.getIconResId()));
        label.setText(menuItem.getLabel());

        if (position == currentItem){
            icon.setColorFilter(ContextCompat.getColor(context, R.color.primary));
            label.setTextColor(ContextCompat.getColor(context, R.color.primary));
        }else{
            icon.setColorFilter(ContextCompat.getColor(context, R.color.textColor));
            label.setTextColor(ContextCompat.getColor(context, R.color.textColor));
        }

        holder.itemView.setOnClickListener( v -> {
            notifyItemChanged(currentItem);
            currentItem = position;
            notifyItemChanged(currentItem);
            navController.navigate(menuItem.getDestinationId());
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
