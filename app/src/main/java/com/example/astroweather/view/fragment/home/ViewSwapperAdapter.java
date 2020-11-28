package com.example.astroweather.view.fragment.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather.view.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class ViewSwapperAdapter extends RecyclerView.Adapter<ViewSwapperAdapter.ViewHolder> {

    private List<BasePage> pages = new ArrayList<>();

    public void setPages(List<BasePage> pages) {
        this.pages.clear();
        this.pages.addAll(pages);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType < 0){
            return new ViewHolder(new View(parent.getContext()));
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(PageType.values()[viewType].getLayoutResourceId(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        pages.get(position).onBindView(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        if (pages.get(position) != null){
            return pages.get(position).getPageType().ordinal();
        }else{
            return -1;
        }
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
