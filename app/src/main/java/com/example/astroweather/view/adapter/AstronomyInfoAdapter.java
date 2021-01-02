package com.example.astroweather.view.adapter;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather.R;

import java.util.ArrayList;
import java.util.List;

public class AstronomyInfoAdapter extends RecyclerView.Adapter<AstronomyInfoAdapter.ViewHolder> {

    private List<Pair<String, String>> items = new ArrayList<>();

    public void setData(List<Pair<String, String>> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_astronomy_info, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView label = holder.itemView.findViewById(R.id.label);
        TextView value = holder.itemView.findViewById(R.id.value);

        Pair<String, String> item = items.get(position);
        label.setText(item.first);
        value.setText(item.second);
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
