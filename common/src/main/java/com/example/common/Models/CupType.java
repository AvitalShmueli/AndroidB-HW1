package com.example.common.Models;

import androidx.annotation.NonNull;

public class CupType {
    private String type = "";
    private int capacity = 0;
    private int drawableImg = 0;
    private boolean isSelected = false;
    private String unit = "ml";

    public CupType() {
    }

    public CupType(String type, int capacity, int drawableImg, String unit) {
        this.type = type;
        this.capacity = capacity;
        this.drawableImg = drawableImg;
        this.unit = unit;
    }

    public String getType() {
        return type;
    }

    public CupType setType(String type) {
        this.type = type;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public CupType setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public CupType setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public int getDrawableImg() {
        return drawableImg;
    }

    public CupType setDrawableImg(int drawableImg) {
        this.drawableImg = drawableImg;
        return this;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public CupType setSelected(boolean selected) {
        isSelected = selected;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        return "capacity: "+capacity+ " isSelected? "+String.valueOf(isSelected);
    }
}
