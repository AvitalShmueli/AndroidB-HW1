package com.example.common.Models;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RecordsList {
    public static final String RECORDS_TABLE = "RECORDS";

    private String listName = "";
    private ArrayList<Record> recordsArrayList = new ArrayList<>();
    private int streak = 0;
    private String unit;
    private int dailyGoal;

    public RecordsList() {
    }

    public String getListName() {
        return listName;
    }

    public RecordsList setListName(String listName) {
        this.listName = listName;
        return this;
    }

    public ArrayList<Record> getRecordsArrayList() {
        return recordsArrayList;
    }



    public RecordsList setRecordsArrayList(ArrayList<Record> recordsArrayList) {
        this.recordsArrayList = recordsArrayList;
        return this;
    }

    public RecordsList setRecordArrayList(ArrayList<Record> recordsArrayList) {
        this.recordsArrayList = recordsArrayList;
        return this;
    }

    public int getDailyGoal() {
        return dailyGoal;
    }

    public RecordsList setDailyGoal(int dailyGoal) {
        this.dailyGoal = dailyGoal;
        return this;
    }

    public RecordsList add(Record record) {
        if(!recordsArrayList.isEmpty() && isStreakSaved())
            streak++;
        else streak = 0;
        this.recordsArrayList.add(record);
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public RecordsList setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    @Override
    public String toString() {
        return "RecordsList{" +
                "listName='" + listName + '\'' +
                ", recordsArrayList=" + recordsArrayList +
                ", streak=" + streak +
                ", unit='" + unit + '\'' +
                ", dailyGoal=" + dailyGoal +
                '}';
    }

    public int getSize(){
        return recordsArrayList.size();
    }

    public Record get(int position){
        return recordsArrayList.get(position);
    }

    public Record getTodayRecord() {
        int todayRecordIndex = getIndexOfToday();
        if(todayRecordIndex != -1)
            return recordsArrayList.get(todayRecordIndex);
        return null;
    }

    public int getIndexOfToday(){
       String today = getTodayDateFormatted();
        if(recordsArrayList != null) {
            for (int i = 0; i < recordsArrayList.size(); i++) {
                if (recordsArrayList.get(i).getDate().equals(today))
                    return i;
            }
            return -1;
        }
        return -1;
    }

    public int getCurrentStreak() {
        return streak;
    }

    private String getTodayDateFormatted(){
        Date todayDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String today = dateFormat.format(todayDate);
        Log.d("TEST TIME RecordsList", today);
        return  today;
    }

    private boolean isStreakSaved(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String yesterday = dateFormat.format(cal.getTime());
        boolean isLastRecordWasYesterday = recordsArrayList.get(recordsArrayList.size() - 1).getDate().equals(yesterday);
        boolean isLastStreakWasYesterday;
        if(unit.equals("ml"))
            isLastStreakWasYesterday = recordsArrayList.get(recordsArrayList.size() - 1).getCapacity()/220 >= dailyGoal;
        else  isLastStreakWasYesterday = recordsArrayList.get(recordsArrayList.size() - 1).getCapacity() <= dailyGoal;

        return isLastRecordWasYesterday && isLastStreakWasYesterday;
    }

}
