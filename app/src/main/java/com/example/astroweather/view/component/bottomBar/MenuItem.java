package com.example.astroweather.view.component.bottomBar;

public class MenuItem {
    private String label;
    private int iconResId;
    private int destinationId;

    public MenuItem(String label, int iconResId, int destinationId) {
        this.label = label;
        this.iconResId = iconResId;
        this.destinationId = destinationId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getIconResId() {
        return iconResId;
    }

    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }

    public int getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(int destinationId) {
        this.destinationId = destinationId;
    }
}
