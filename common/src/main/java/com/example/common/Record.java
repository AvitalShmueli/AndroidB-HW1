package com.example.common;

import androidx.annotation.Nullable;

public class Record {
    private String date;
    private int ml;

    public Record() {
    }

    public String getDate() {
        return date;
    }

    public Record setDate(String date) {
        this.date = date;
        return this;
    }

    public int getMl() {
        return ml;
    }

    public Record setMl(int ml) {
        this.ml = ml;
        return this;
    }

    public Record addML(int ml){
        this.ml += ml;
        return this;
    }

    public boolean equals(Record other){
        return other.getDate().equals(this.date);
    }

    @Override
    public String toString() {
        return "Record{" +
                "date='" + date + '\'' +
                ", ml=" + ml +
                '}';
    }
}
