package com.example.mediaplayer;

public class SingerModel {
    private String name;
    private int imageResId;
    private boolean selected;

    public SingerModel(String name, int imageResId) {
        this.name = name;
        this.imageResId = imageResId;
        this.selected = false;

    }

    public String getName() { return name; }
    public int getImageResId() { return imageResId; }
    public boolean isSelected() { return selected; }
    public void setSelected(boolean selected) { this.selected = selected; }


}
