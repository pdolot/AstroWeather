package com.example.astroweather.view.fragment.weather.adapter;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather.R;
import com.example.astroweather.view.base.adapter.BaseAdapter;
import com.google.android.material.card.MaterialCardView;

public class DaysAdapter extends BaseAdapter<DaysAdapter.ViewHolder, Pair<String, String>> {

    private int currentPosition = 0;
    private DaysAdapterListener daysAdapterListener;

    public void setDaysAdapterListener(DaysAdapterListener daysAdapterListener) {
        this.daysAdapterListener = daysAdapterListener;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        MaterialCardView cardView = holder.itemView.findViewById(R.id.card);
        TextView day = holder.itemView.findViewById(R.id.day);
        TextView month = holder.itemView.findViewById(R.id.month);

        Pair<String, String> d = getItemAtPosition(position);
        day.setText(d.first);
        month.setText(d.second);

        if (position == currentPosition) {
            cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.primary));
            day.setTextColor(ContextCompat.getColor(context, R.color.white));
            month.setTextColor(ContextCompat.getColor(context, R.color.white));
        }else{
            cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.white));
            day.setTextColor(ContextCompat.getColor(context, R.color.textColor));
            month.setTextColor(ContextCompat.getColor(context, R.color.textColor));
        }

        holder.itemView.setOnClickListener(v -> {
            if (daysAdapterListener != null){
                daysAdapterListener.onDayClicked(position);
            }
        });
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface DaysAdapterListener{
        void onDayClicked(int position);
    }
}
