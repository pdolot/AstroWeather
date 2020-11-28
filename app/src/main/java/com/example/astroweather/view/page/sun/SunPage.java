package com.example.astroweather.view.page.sun;

import android.util.Pair;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather.R;
import com.example.astroweather.model.api.AstronomyData;
import com.example.astroweather.view.base.BasePage;
import com.example.astroweather.view.fragment.home.PageType;
import com.example.astroweather.view.fragment.home.ViewSwapperAdapter;
import com.example.astroweather.view.page.AstronomyInfoAdapter;

import java.util.ArrayList;
import java.util.List;

public class SunPage extends BasePage {

    private AstronomyInfoAdapter adapter = new AstronomyInfoAdapter();
    private AstronomyData astronomyData;

    public SunPage(AstronomyData astronomyData) {
        this.astronomyData = astronomyData;
    }

    @Override
    public PageType getPageType() {
        return PageType.SUN_PAGE;
    }

    @Override
    public void onBindView(ViewSwapperAdapter.ViewHolder viewHolder, int position) {
        RecyclerView recyclerView = viewHolder.itemView.findViewById(R.id.pageSun_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(viewHolder.itemView.getContext()));
        recyclerView.setAdapter(adapter);

        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair("Wschód słońca",astronomyData.getSunrise()));
        data.add(new Pair("Zachód słońca",astronomyData.getSunset()));
        data.add(new Pair("Azymut słońca",astronomyData.getSunAzimuth()));
        data.add(new Pair("Wysokość słońca",astronomyData.getSunAltitude()));
        data.add(new Pair("Odległość słońca",astronomyData.getSunDistance()));
        adapter.setData(data);
    }
}
