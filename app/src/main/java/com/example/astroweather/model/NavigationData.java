package com.example.astroweather.model;


public class NavigationData {
    private String latitude;
    private String longitude;
    private int syncInterval;

    public NavigationData(String latitude, String longitude, int syncInterval) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.syncInterval = syncInterval;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public int getSyncInterval() {
        return syncInterval;
    }
}
