package com.example.astroweather.view.fragment.astronomy.page.moon;

import android.util.Pair;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather.R;
import com.example.astroweather.model.astronomy.AstronomyData;
import com.example.astroweather.view.base.BasePage;
import com.example.astroweather.view.PageType;
import com.example.astroweather.view.base.adapter.PagerAdapter;
import com.example.astroweather.view.adapter.AstronomyInfoAdapter;

import java.util.ArrayList;
import java.util.List;

public class MoonPage extends BasePage {
    private AstronomyInfoAdapter adapter = new AstronomyInfoAdapter();
    private AstronomyData astronomyData;

    public MoonPage(AstronomyData astronomyData) {
        this.astronomyData = astronomyData;
    }

    @Override
    public String getTitle() {
        return "Księżyc";
    }

    @Override
    public PageType getPageType() {
        return PageType.MOON_PAGE;
    }

    @Override
    public void onBindView(PagerAdapter.ViewHolder viewHolder, int position) {
        RecyclerView recyclerView = viewHolder.itemView.findViewById(R.id.pageMoon_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(viewHolder.itemView.getContext()));
        recyclerView.setAdapter(adapter);

        List<Pair<String, String>> data = new ArrayList<>();
        data.add(new Pair("Wschód księżyca",astronomyData.getMoonrise()));
        data.add(new Pair("Azymut księżyca",astronomyData.getMoonAzimuth()));
        data.add(new Pair("Wysokość księżyca",astronomyData.getMoonAltitude()));
        data.add(new Pair("Kąt paralaktyczny księżyca",astronomyData.getMoonParallacticAngle()));
        data.add(new Pair("Odległość księżyca",astronomyData.getMoonDistance()));
        adapter.setData(data);
    }
}
