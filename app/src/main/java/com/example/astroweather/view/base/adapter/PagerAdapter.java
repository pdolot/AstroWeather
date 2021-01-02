package com.example.astroweather.view.base.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather.view.PageType;
import com.example.astroweather.view.base.BasePage;

import java.util.ArrayList;
import java.util.List;

public class PagerAdapter extends RecyclerView.Adapter<PagerAdapter.ViewHolder> {

    private List<BasePage> pages = new ArrayList<>();
    private int viewWidth = 0;

    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
    }

    public void setPages(List<BasePage> pages) {
        this.pages.clear();
        this.pages.addAll(pages);
        notifyDataSetChanged();
    }

    public String getPageTitleAt(int position) {
        return pages.get(position).getTitle();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType < 0) {
            return new ViewHolder(new View(parent.getContext()));
        }
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(PageType.values()[viewType].getLayoutResourceId(), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
        layoutParams.width = viewWidth;
        holder.itemView.setLayoutParams(layoutParams);

        pages.get(position).onBindView(holder, position);
    }

    @Override
    public int getItemViewType(int position) {
        if (pages.get(position) != null) {
            return pages.get(position).getPageType().ordinal();
        } else {
            return -1;
        }
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
