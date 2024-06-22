package com.example.common;

import androidx.annotation.NonNull;

public class CupType {
    private String type = "";
    private int capacity = 0;
    private int drawableImg = 0;
    private boolean isSelected = false;

    public CupType() {
    }

    public CupType(String type, int capacity, int drawableImg) {
        this.type = type;
        this.capacity = capacity;
        this.drawableImg = drawableImg;
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
