package com.example.astroweather.view.fragment.menu.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.astroweather.R;
import com.example.astroweather.data.LocalStorage;
import com.example.astroweather.di.Injector;
import com.example.astroweather.model.weather.Location;
import com.example.astroweather.view.base.adapter.BaseAdapter;

import javax.inject.Inject;

public class LocationAdapter extends BaseAdapter<LocationAdapter.ViewHolder, Location> {

    @Inject
    LocalStorage localStorage;
    private LocationAdapterListener locationAdapterListener;

    public LocationAdapter(LocationAdapterListener locationAdapterListener) {
        this.locationAdapterListener = locationAdapterListener;
        Injector.appComponent.inject(this);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CheckBox current = holder.itemView.findViewById(R.id.currentSelector);
        CheckBox favorite = holder.itemView.findViewById(R.id.favoriteSelector);
        ImageView remove = holder.itemView.findViewById(R.id.removeLocation);
        TextView city = holder.itemView.findViewById(R.id.city);
        Location location = getItemAtPosition(position);
        Boolean isCurrent = localStorage.getCurrentLocation().equals(location.getId());
        city.setText(location.getCity());
        current.setChecked(isCurrent);
        favorite.setChecked(location.getFavorite());
        favorite.setOnCheckedChangeListener((buttonView, isChecked) -> {
            locationAdapterListener.onChangeFavorite(location.getId(), isChecked);
        });

        current.setClickable(!isCurrent);

        current.setOnCheckedChangeListener((buttonView, isChecked) -> {
            locationAdapterListener.onChangeCurrent(location.getId());
        });

        remove.setOnClickListener(v -> {
            if (!isCurrent){
                locationAdapterListener.onRemove(location.getId());
            }else{
                Toast.makeText(holder.itemView.getContext(), "Nie można usunąć. Zmień obecną lokalizacje", Toast.LENGTH_SHORT).show();
            }

        });

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public interface LocationAdapterListener{
        void onChangeFavorite(Long id, Boolean favorite);
        void onRemove(Long id);
        void onChangeCurrent(Long id);
    }
}
