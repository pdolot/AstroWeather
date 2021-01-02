package com.example.astroweather.view.base.adapter;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

abstract public class BaseAdapter<VH extends RecyclerView.ViewHolder, T extends Object> extends RecyclerView.Adapter<VH> {

    private List<T> data;

    public void setData(List<T> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public T getItemAtPosition(int position) {
        if (data != null) {
            return data.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        } else {
            return 0;
        }
    }
}
