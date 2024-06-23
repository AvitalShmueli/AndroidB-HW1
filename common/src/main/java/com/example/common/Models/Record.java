package com.example.common.Models;

public class Record {
    private String date;
    private int capacity;

    public Record() {
    }

    public String getDate() {
        return date;
    }

    public Record setDate(String date) {
        this.date = date;
        return this;
    }

    public int getCapacity() {
        return capacity;
    }

    public Record setCapacity(int capacity) {
        this.capacity = capacity;
        return this;
    }

    public Record addAmount(int amount){
        this.capacity += amount;
        return this;
    }

    public boolean equals(Record other){
        return other.getDate().equals(this.date);
    }

    @Override
    public String toString() {
        return "Record{" +
                "date='" + date + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
