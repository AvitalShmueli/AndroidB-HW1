package com.example.androidb_hw1;

public class CupType {
    private String type = "";
    private int capacity = 0;
    private int drawableImg = 0;

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

}
